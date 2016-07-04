package ch18.review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class reviewDBBean {

	private static reviewDBBean instance = new reviewDBBean();
	//.jsp페이지에서 DB연동빈인 reviewDBBean클래스의 메소드에 접근시 필요
	public static reviewDBBean getInstance() {
		return instance;
	}

	private reviewDBBean() {}

	//커넥션풀로부터 Connection객체를 얻어냄
	private Connection getConnection() throws Exception {
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource)envCtx.lookup("jdbc/aban");
		return ds.getConnection();
	}
	public List<reviewDataBean> getMember(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<reviewDataBean> member1=null;

		try {
			conn = getConnection();

			pstmt = conn.prepareStatement(
					"select * from review order by date desc");
			rs = pstmt.executeQuery();

			if (rs.next()) {//해당 아이디에 대한 레코드가 존재
				//사용자가 입력한 비밀번호와 테이블의 비밀번호가 같으면 수행
				member1 = new ArrayList<reviewDataBean>();
				do{  
					reviewDataBean member = new reviewDataBean();//데이터저장빈 객체생성
					member.setDate(rs.getTimestamp("date"));
					member.setSubject(rs.getString("subject"));
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

	//review테이블에 글을 추가(inset문)<=writePro.jsp페이지에서 사용
	public void insertReview(reviewDataBean review) 
			throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql="";
		try{
		
		conn=getConnection();
		
		// 쿼리를 작성
		sql = "insert into review(writer,content,title,passwd,date,img_src,img_src2,img_src3,";
		sql+="cafe_name,where_,like_point,grademark) values(?,?,?,?,?,?,?,?,?,?,?,?)";

		pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, review.getWriter());
		pstmt.setString(2, review.getContent());
		pstmt.setString(3, review.getTitle());
		pstmt.setString(4, review.getPasswd());
		pstmt.setTimestamp(5, review.getDate());
		pstmt.setString(6, review.getImg_src());
		pstmt.setString(7, review.getImg_src2());
		pstmt.setString(8, review.getImg_src3());
		pstmt.setString(9, review.getCafe_name());
		pstmt.setString(10, review.getWhere_());
		pstmt.setInt(11, review.getLike_point());
		pstmt.setInt(12, review.getGrademark());
		
		
		pstmt.executeUpdate();
		
	  } catch(Exception ex) {
          ex.printStackTrace();
      } finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
          if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
          if (conn != null) try { conn.close(); } catch(SQLException ex) {}
      }
	}
}


