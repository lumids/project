package kids.info;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import kids.info.InfoDataBean;
import ch11.logon.LogonDataBean;
import ch13.board.BoardDataBean;
import work.crypt.SHA256;
import work.crypt.BCrypt;
import java.util.ArrayList;
import java.util.List;

public class InfoDBBean {

	// LogonDBBean �쟾�뿭 媛앹껜 �깮�꽦 <- �븳媛쒖쓽 媛앹젣留� �깮�꽦�빐�꽌 怨듭쑀
	private static InfoDBBean instance = new InfoDBBean();

	// LogonDBBean媛앹껜瑜� 由ы꽩�븯�뒗 硫붿냼�뱶
	public static InfoDBBean getInstance() {
		return instance;
	}

	private InfoDBBean() {
	}

	// 而ㅻ꽖�뀡 ���뿉�꽌 而ㅻ꽖�뀡 媛앹껜瑜� �뼸�뼱�궡�뒗 硫붿냼�뱶
	private Connection getConnection() throws Exception {
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource) envCtx.lookup("jdbc/aban");
		return ds.getConnection();
	}

	// �쉶�썝 媛��엯 泥섎━(registerPro.jsp)�뿉�꽌 �궗�슜�븯�뒗 �깉 �젅肄붾뱶 異붽� 硫붿냼�뱶
	public int insertMember(InfoDataBean member) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int x=0;

		try {
			conn = getConnection();

			pstmt = conn.prepareStatement(
					"insert into join_kidscafe(num,cafe_cr_num,cafe_name,cafe_position,cafe_adul_fee,cafe_chi_fee,cafe_phone,cafe_bh_start,cafe_bh_end,cafe_out_chk,cafe_accnum,avr_score,wifi,parking,eat,moim,party,con_join) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			System.out.println(member.getCafe_adul_fee());
			/*pstmt.setInt(1, member.getNum());*/
			pstmt.setInt(1, member.getNum());
			pstmt.setString(2, member.getCafe_cr_num());
			pstmt.setString(3, member.getCafe_name());
			pstmt.setString(4, member.getCafe_position());
			pstmt.setString(5, member.getCafe_adul_fee());
			pstmt.setString(6, member.getCafe_chi_fee());
			pstmt.setString(7, member.getCafe_phone());
			pstmt.setInt(8, member.getCafe_bh_start());
			pstmt.setInt(9, member.getCafe_bh_end());			
			pstmt.setInt(10, 0);
			pstmt.setString(11, "");
			pstmt.setDouble(12, 0);
			pstmt.setString(13, member.getWifi());
			pstmt.setString(14, member.getParking());
			pstmt.setString(15, member.getEat());
			pstmt.setString(16, member.getMoim());
			pstmt.setString(17, member.getParty());
			pstmt.setString(18,"미승인");

			pstmt.executeUpdate();
			
			x=1;
		} catch (Exception ex) {
			x=0;
			ex.printStackTrace();
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}
		return x;
	}
	
	public void insertCon(InfoDataBean member) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			

			pstmt = conn.prepareStatement(
					"insert into kidscafe_contract(num,month_fee,cafe_con_start,cafe_con_end,join_pay) values (?,?,?,?,?)");
			System.out.println(member.getCafe_adul_fee());
			/*pstmt.setInt(1, member.getNum());*/
			pstmt.setInt(1, member.getNum());
			pstmt.setInt(2, 30000);
			pstmt.setTimestamp(3, member.getCafe_con_start());
			pstmt.setTimestamp(4, member.getCafe_con_end());
			pstmt.setInt(5, 50000);
			
			pstmt.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}
	}
	public List<InfoDataBean> getCafe(){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<InfoDataBean> member1=null;
        
        try {
            conn = getConnection();
            
            pstmt = conn.prepareStatement(
            	"select * from join_kidscafe ");
            /*order by reg_date desc*/
            rs = pstmt.executeQuery();
     
            if (rs.next()) {//�빐�떦 �븘�씠�뵒�뿉 ���븳 �젅肄붾뱶媛� 議댁옱
            	//�궗�슜�옄媛� �엯�젰�븳 鍮꾨�踰덊샇�� �뀒�씠釉붿쓽 鍮꾨�踰덊샇媛� 媛숈쑝硫� �닔�뻾
            	member1 = new ArrayList<InfoDataBean>();
            	do{  
            	  InfoDataBean member = new InfoDataBean();//�뜲�씠�꽣���옣鍮� 媛앹껜�깮�꽦
                  member.setCafe_name(rs.getString("cafe_name"));
				  member.setCafe_num(rs.getInt("cafe_num"));
				  member.setCon_join(rs.getString("con_join"));
                  member1.add(member);
            	}while(rs.next());
			}
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
		return member1;//�뜲�씠�꽣 ���옣鍮� 媛앹껜 member 由ы꽩
    }
	
	public int updateCafe(InfoDataBean mem,LogonDataBean member){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs= null;
        int x=-1;
        
        try {
            conn = getConnection();
            
            pstmt = conn.prepareStatement(
                	"select passwd from member where id = ?");
            pstmt.setString(1, member.getId());
            rs = pstmt.executeQuery();
                
            if(rs.next()){//해당 아이디가 있으면 수행
                    pstmt = conn.prepareStatement(
                     "update member set name=?,address=?,tel=?,gender=?,accnum=?,rol=? "+
                     "where id=?");
                    pstmt.setString(1, member.getName());
                    pstmt.setString(2, member.getAddress());
                    pstmt.setString(3, member.getTel());
                    pstmt.setString(4, member.getGender());
                    pstmt.setString(5, member.getAccnum());
                    pstmt.setString(6, member.getRol());
                    pstmt.setString(7, member.getId());
                    
                    pstmt.executeUpdate();
                 
			}
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
        	if (rs != null) try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
        return x;
    }

	public int updateOk(InfoDataBean member){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs= null;
        int x=-1;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(
                	"select * from join_kidscafe where cafe_num = ?");
            pstmt.setInt(1, member.getCafe_num());
            rs = pstmt.executeQuery();
                
            if(rs.next()){//해당 아이디가 있으면 수행
                    pstmt = conn.prepareStatement(
                     "update join_kidscafe set con_join=?"+
                     "where cafe_num=?");                    
                    pstmt.setString(1, "승인");                    
                    pstmt.setInt(2, member.getCafe_num());                    
                    pstmt.executeUpdate();
                    
                   x=1;
			}
            else{
            	x=0;
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
        	if (rs != null) try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
        return x;
    }
	public InfoDataBean getCafeInfo(InfoDataBean member){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        SHA256 sha = SHA256.getInsatnce();
        try {
            conn = getConnection();            
            
            pstmt = conn.prepareStatement(
            	"select * from join_kidscafe,member where cafe_num = ?");
            pstmt.setInt(1, member.getCafe_num());
            rs = pstmt.executeQuery();     
            if (rs.next()) {//해당 아이디에 대한 레코드가 존재            	
                  member = new InfoDataBean();//데이터저장빈 객체생성
                  member.setCafe_name(rs.getString("cafe_name"));
				  member.setCafe_position(rs.getString("cafe_position"));
				  member.setCafe_adul_fee(rs.getString("cafe_adul_fee"));
				  member.setCafe_chi_fee(rs.getString("cafe_chi_fee"));
				  member.setCafe_phone(rs.getString("cafe_phone"));
                  member.setReg_date(rs.getTimestamp("reg_date"));
                  member.setCafe_bh_start(rs.getInt("cafe_bh_start"));
                  member.setCafe_bh_end(rs.getInt("cafe_bh_end"));
                  member.setWifi(rs.getString("wifi"));
                  member.setParking(rs.getString("parking"));
                  member.setEat(rs.getString("eat"));
                  member.setMoim(rs.getString("moim"));
                  member.setParty(rs.getString("party"));
                  member.setCon_join(rs.getString("con_join"));
			}
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
		return member;//데이터 저장빈 객체 member 리턴
    }

}