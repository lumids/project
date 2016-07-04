package ch11.logon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import ch13.board.BoardDataBean;
import kids.info.InfoDataBean;
import work.crypt.SHA256;
import work.crypt.BCrypt;
import java.util.ArrayList;
import java.util.List;
public class LogonDBBean {
	
	//LogonDBBean 전역 객체 생성 <- 한개의 객제만 생성해서 공유
    private static LogonDBBean instance = new LogonDBBean();
    
    //LogonDBBean객체를 리턴하는 메소드
    public static LogonDBBean getInstance() {
        return instance;
    }
    
    private LogonDBBean() {}
    
    //커넥션 풀에서 커넥션 객체를 얻어내는 메소드
    private Connection getConnection() throws Exception {
        Context initCtx = new InitialContext();
        Context envCtx = (Context) initCtx.lookup("java:comp/env");
        DataSource ds = (DataSource)envCtx.lookup("jdbc/aban");
        return ds.getConnection();
    }
 
    public List<LogonDataBean> getMem(){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<LogonDataBean> member1=null;
        
        try {
            conn = getConnection();
            
            pstmt = conn.prepareStatement(
            	"select * from member ");
            /*order by reg_date desc*/
            rs = pstmt.executeQuery();
     
            if (rs.next()) {//�빐�떦 �븘�씠�뵒�뿉 ���븳 �젅肄붾뱶媛� 議댁옱
            	//�궗�슜�옄媛� �엯�젰�븳 鍮꾨�踰덊샇�� �뀒�씠釉붿쓽 鍮꾨�踰덊샇媛� 媛숈쑝硫� �닔�뻾
            	member1 = new ArrayList<LogonDataBean>();
            	do{  
            	  LogonDataBean member = new LogonDataBean();//�뜲�씠�꽣���옣鍮� 媛앹껜�깮�꽦
                  member.setId(rs.getString("id"));
				  member.setName(rs.getString("name"));
				  member.setReg_date(rs.getTimestamp("reg_date"));
				  member.setAddress(rs.getString("address"));
				  member.setTel(rs.getString("tel"));
				  member.setGender(rs.getString("gender"));
				  member.setRol(rs.getString("rol"));
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
    //회원 가입 처리(registerPro.jsp)에서 사용하는  새 레코드 추가 메소드
    public void insertMember(LogonDataBean member){
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        SHA256 sha = SHA256.getInsatnce();
        try {
            conn = getConnection();
            
            String orgPass = member.getPasswd();
            String shaPass = sha.getSha256(orgPass.getBytes());
        	String bcPass = BCrypt.hashpw(shaPass, BCrypt.gensalt());
        	
            pstmt = conn.prepareStatement(
            	"insert into member values (?,?,?,?,?,?,?,?,?,?,?,?)");
            pstmt.setInt(1, member.getNum());
            pstmt.setString(2, member.getId());
            pstmt.setString(3, bcPass);
            pstmt.setString(4, member.getName());
            pstmt.setTimestamp(5, member.getReg_date());
            pstmt.setString(6, member.getAddress());
            pstmt.setString(7, member.getTel());
            pstmt.setString(8, member.getGender());
            pstmt.setString(9, member.getAccnum());
            pstmt.setInt(10, 0);         
            pstmt.setInt(11, 0);
            pstmt.setString(12, member.getRol());
            
            
            pstmt.executeUpdate();
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
    }
    
    public void insertInfo(String a, String b, String c){
        Connection conn = null;
        PreparedStatement pstmt = null;
        LogonDataBean member=null;
        try {
            conn = getConnection();        	
            pstmt = conn.prepareStatement(
            	"insert into ex1 values (?,?,?)");
            member = new LogonDataBean();
            pstmt.setString(1, a);
            pstmt.setString(2, b);
            pstmt.setString(3, c);
            pstmt.executeUpdate();
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
    }
    public void insertInfo2(String b, String ac){
        Connection conn = null;
        PreparedStatement pstmt = null;
        LogonDataBean member=null;
        try {
            conn = getConnection();        	
            pstmt = conn.prepareStatement(
            	"insert into ex2 values (?,?)");
            member = new LogonDataBean();            
            pstmt.setString(1,b);
            pstmt.setString(2, ac);
            pstmt.executeUpdate();
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
    }
 
	//로그인 폼 처리(loginPro.jsp)페이지의 사용자 인증 처리 및
    //회원정보수정/탈퇴를 사용자인증(memberCheck.jsp)에서 사용하는 메소드
    public int userCheck(String id, String passwd){
		Connection conn = null;
        PreparedStatement pstmt = null;
		ResultSet rs= null;
		int x=-1;
        
		SHA256 sha = SHA256.getInsatnce();
		try {
            conn = getConnection();
            
            String orgPass = passwd;
            String shaPass = sha.getSha256(orgPass.getBytes());
        	
            pstmt = conn.prepareStatement(
            	"select passwd from member where id = ?");
            pstmt.setString(1, id);
            rs= pstmt.executeQuery();

			if(rs.next()){//해당 아이디가 있으면 수행
				String dbpasswd= rs.getString("passwd"); 
				if(BCrypt.checkpw(shaPass,dbpasswd))
					x= 1; //인증성공
				else
					x= 0; //비밀번호 틀림
			}else//해당 아이디 없으면 수행
				x= -1;//아이디 없음
			
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
		return x;
	}
    
    //아이디 중복 확인 (confirmId.jsp)에서 아이디의 중복 여부를 확인하는 메소드
	public int confirmId(String id) {
		Connection conn = null;
        PreparedStatement pstmt = null;
		ResultSet rs= null;
		int x=-1;
        
		try {
            conn = getConnection();
            
            pstmt = conn.prepareStatement(
            	"select id from member where id = ?");
            pstmt.setString(1, id);
            rs= pstmt.executeQuery();

			if(rs.next())//아이디 존재
				x= 1; //같은 아이디 있음
			else
				x= -1;//같은 아이디 없음
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
		return x;
	}

	
   //회원 정보 수정 폼(modifyForm.jsp)을 위한 기존 가입 정보를 가져오는 메소드
    public LogonDataBean getMember(String id, String passwd){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        LogonDataBean member=null;
        
        SHA256 sha = SHA256.getInsatnce();
        try {
            conn = getConnection();
            
            String orgPass = passwd;
            String shaPass = sha.getSha256(orgPass.getBytes());
            
            pstmt = conn.prepareStatement(
            	"select * from member where id = ?");
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
     
            if (rs.next()) {//해당 아이디에 대한 레코드가 존재
            	
                  member = new LogonDataBean();//데이터저장빈 객체생성
                  member.setId(rs.getString("id"));
				  member.setName(rs.getString("name"));
                  member.setReg_date(rs.getTimestamp("reg_date"));
                  member.setAddress(rs.getString("address"));
                  member.setTel(rs.getString("tel"));
                  member.setGender(rs.getString("gender"));
                  member.setAccnum(rs.getString("accnum"));
                  member.setRol(rs.getString("rol"));
				
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
    public LogonDataBean getMember1(String id){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        LogonDataBean member=null;
        
        try {
            conn = getConnection();
            
            pstmt = conn.prepareStatement(
            	"select * from member where id = ?");
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();     
            if (rs.next()) {//해당 아이디에 대한 레코드가 존재            	
                  member = new LogonDataBean();//데이터저장빈 객체생성
                  member.setNum(rs.getInt("num"));
				  member.setName(rs.getString("name"));
                  member.setTel(rs.getString("tel"));				
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
    
    public List<LogonDataBean> getInfo(String id){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List <LogonDataBean> member1=null;
        
        
        try {
            conn = getConnection();
                 
            pstmt = conn.prepareStatement(
            	"select * from member where id like %?%");
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
     
            if (rs.next()) {//해당 아이디에 대한 레코드가 존재
            member1= new ArrayList<LogonDataBean>();
            do{
            LogonDataBean member = new LogonDataBean();
                  member = new LogonDataBean();//데이터저장빈 객체생성
                  member.setId(rs.getString("id"));
				  member.setName(rs.getString("name"));
                  member.setReg_date(rs.getTimestamp("reg_date"));
                  member.setAddress(rs.getString("address"));
                  member.setTel(rs.getString("tel"));
                  member.setGender(rs.getString("gender"));
                  member.setAccnum(rs.getString("accnum"));
                  member.setRol(rs.getString("rol"));
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
		return member1;//데이터 저장빈 객체 member 리턴
    }
    
    public LogonDataBean getName(String id){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        LogonDataBean member=null;
        try {
            conn = getConnection();
           
            
            pstmt = conn.prepareStatement(
            	"select * from member,join_kidscafe,kidscafe_contract where id = ?");
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
     
            if (rs.next()) {//해당 아이디에 대한 레코드가 존재
            	//사용자가 입력한 비밀번호와 테이블의 비밀번호가 같으면 수행
				
                  member = new LogonDataBean();//데이터저장빈 객체생성
                  member.setId(rs.getString("id"));
				  member.setName(rs.getString("name"));
                  member.setReg_date(rs.getTimestamp("reg_date"));
                  member.setAddress(rs.getString("address"));
                  member.setTel(rs.getString("tel"));
                  member.setGender(rs.getString("gender"));
                  member.setAccnum(rs.getString("accnum"));
                  member.setRol(rs.getString("rol"));
                  member.setCafe_name(rs.getString("cafe_name"));
                  member.setNum(rs.getInt("num"));
                  member.setCafe_con_start(rs.getTimestamp("cafe_con_start"));
                  member.setJoin_cafe(rs.getString("join_cafe"));
				
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
    
    public LogonDataBean getBest(String id){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        LogonDataBean member=null;
                
        try {
            conn = getConnection();           
            pstmt = conn.prepareStatement(
            	"select * from member where id = ?");
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
     
            if (rs.next()) {//해당 아이디에 대한 레코드가 존재
            	//사용자가 입력한 비밀번호와 테이블의 비밀번호가 같으면 수행
				
                  member = new LogonDataBean();//데이터저장빈 객체생성
                  member.setRol(rs.getString("rol"));
                  member.setJoin_cafe(rs.getString("join_cafe"));
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
    
    //회원 정보 수정 처리(modifyPro.jsp)에서 회원 정보 수정을 처리하는 메소드
    public int updateMember(LogonDataBean member){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs= null;
        int x=-1;
        
        SHA256 sha = SHA256.getInsatnce();
        try {
            conn = getConnection();
            
            String orgPass = member.getPasswd();
            String shaPass = sha.getSha256(orgPass.getBytes());
            
            pstmt = conn.prepareStatement(
                	"select passwd from member where id = ?");
            pstmt.setString(1, member.getId());
            rs = pstmt.executeQuery();
                
            if(rs.next()){//해당 아이디가 있으면 수행
				String dbpasswd= rs.getString("passwd"); 
				if(BCrypt.checkpw(shaPass,dbpasswd)){
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
                    x= 1;//회원정보 수정 처리 성공
				}else
					x= 0;//회원정보 수정 처리 실패
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
    
    public int update(LogonDataBean member,String id){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs= null;
        int x=-1;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(
                	"select * from member where id = ?");
            pstmt.setString(1, member.getId());
            rs = pstmt.executeQuery();
                
            if(rs.next()){//해당 아이디가 있으면 수행
                    pstmt = conn.prepareStatement(
                     "update member set join_cafe=?"+
                     "where id=?");
                    pstmt.setString(1, "승인");
                    pstmt.setString(2, id);                    
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
    
    
    //회원 탈퇴 처리(deletePro.jsp)에서 회원 정보를 삭제하는 메소드
    public int deleteMember(String id, String passwd){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs= null;
        int x=-1;
        
        SHA256 sha = SHA256.getInsatnce();
        try {
			conn = getConnection();
			
			String orgPass = passwd;
            String shaPass = sha.getSha256(orgPass.getBytes());

            pstmt = conn.prepareStatement(
            	"select passwd from member where id = ?");
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            
			if(rs.next()){
				String dbpasswd= rs.getString("passwd"); 
				if(BCrypt.checkpw(shaPass,dbpasswd)){
					pstmt = conn.prepareStatement(
            	      "delete from member where id=?");
                    pstmt.setString(1, id);
                    pstmt.executeUpdate();
					x= 1;//회원탈퇴처리 성공
				}else
					x= 0;//회원탈퇴 처리 실패
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
}
