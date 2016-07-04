package re.review;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import ch12.board.BoardDataBean;
import work.crypt.SHA256;
import work.crypt.BCrypt;
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

   //회원 정보 수정 폼(modifyForm.jsp)을 위한 기존 가입 정보를 가져오는 메소드
    public List<LogonDataBean> getMember(){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<LogonDataBean> member1=null;
        
        try {
            conn = getConnection();
            
            pstmt = conn.prepareStatement(
            	"select * from review order by date desc");
            rs = pstmt.executeQuery();
     
            if (rs.next()) {//해당 아이디에 대한 레코드가 존재
            	//사용자가 입력한 비밀번호와 테이블의 비밀번호가 같으면 수행
            	member1 = new ArrayList<LogonDataBean>();
            	do{  
            	  LogonDataBean member = new LogonDataBean();//데이터저장빈 객체생성
                  member.setImg_src(rs.getString("img_src"));
				  member.setCafe_name(rs.getString("cafe_name"));
                  member.setDate(rs.getTimestamp("date"));
                  member.setContent(rs.getString("content"));
                  member.setWriter(rs.getString("writer"));
                  //System.out.println(rs.getString("cafe_name"));
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
    
    public LogonDataBean getBest(){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        LogonDataBean member=null;
        
        try {
            conn = getConnection();
            
            pstmt = conn.prepareStatement(
            	"select * from review order by like_point desc");
            rs = pstmt.executeQuery();
     
            if (rs.next()) {//해당 아이디에 대한 레코드가 존재
            	//사용자가 입력한 비밀번호와 테이블의 비밀번호가 같으면 수행
            	
            	  member = new LogonDataBean();//데이터저장빈 객체생성
                  member.setImg_src(rs.getString("img_src"));
                  member.setImg_src2(rs.getString("img_src2"));
                  member.setImg_src3(rs.getString("img_src3"));
				  member.setCafe_name(rs.getString("cafe_name"));
                  member.setDate(rs.getTimestamp("date"));
                  member.setContent(rs.getString("content"));
                  member.setWriter(rs.getString("writer"));
                  member.setWhere_(rs.getString("where_"));
                  //System.out.println(rs.getString("cafe_name"));
                 
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