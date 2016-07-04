package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
public class reportDao {
	private static reportDao instance = new reportDao();
	private reportDao() {}
	public static reportDao getInstance() {
		return instance;
	}
	private Connection getConnection() {
		Connection conn = null;
		try { Context ctx = new InitialContext();
			DataSource ds = (DataSource)
				ctx.lookup("java:comp/env/jdbc/OracleDB");
			conn = ds.getConnection();
		}catch(Exception e) {System.out.println(e.getMessage());}
		return conn;
	}
	public int insert(report report) {
		int result = 0,repReaNum = 0;  Connection conn = null; 
		PreparedStatement pstmt = null;  ResultSet rs = null;
		String sql  = "insert into report values (?,?,sysdate,'n',?,?,?,?)";
		String sql2 = "select nvl(max(repReaNum),0)+1 from report";
		try { conn  = getConnection();
			pstmt = conn.prepareStatement(sql2);
			rs = pstmt.executeQuery();
			if (rs.next()) repReaNum = rs.getInt(1);			
			pstmt.close();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, report.getReportReaNum());
			pstmt.setInt(2, repReaNum);
			pstmt.setString(3, report.getReporterId());
			pstmt.setString(4, report.getReportedId());
			pstmt.setString(5, report.getContent());
			pstmt.setInt(6, report.getReBoardNum());
			result = pstmt.executeUpdate();
		}catch(Exception e) { System.out.println(e.getMessage());
		}finally {
			try {if (rs != null) rs.close();
				 if (pstmt != null) pstmt.close();
				 if (conn != null) conn.close();
			}catch(Exception e) {}
		}
		return result;
	}
	public List<report> selectAll() {
		Connection conn = null; 
		List<report> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;	
		String sql = "select * from report where repHandChk='n' order by repReaNum desc";
		
		try { conn  = getConnection();
			pstmt = conn.prepareStatement(sql);			
			rs = pstmt.executeQuery();
			while(rs.next()){
				report re = new report();
				re.setReportReaNum(rs.getInt("reportReaNum"));
				re.setRepReaNum(rs.getInt("repReaNum"));
				re.setRepDate(rs.getDate("repDate"));
				re.setRepHandChk(rs.getString("repHandChk"));
				re.setReportedId(rs.getString("reportedId"));
				re.setReporterId(rs.getString("reporterId"));
				re.setContent(rs.getString("content"));
				re.setReBoardNum(rs.getInt("reBoardNum"));
				list.add(re);
			}
		}catch(Exception e) { System.out.println(e.getMessage());
		}finally {
			try {if (rs != null) rs.close();
				 if (pstmt != null) pstmt.close();
				 if (conn != null) conn.close();
			}catch(Exception e) {}
		}
		return list;
	}
	public int updateChk(int reportReaNum){
		int result = 0; Connection conn = null;
		PreparedStatement pstmt = null; 
		String sql="update report set repHandChk='y' where reportReaNum=?";
		try { conn = getConnection();
			pstmt  = conn.prepareStatement(sql);			
			pstmt.setInt(1, reportReaNum);
			result = pstmt.executeUpdate();
		}catch(Exception e) {System.out.println(e.getMessage());
		}finally {
			try{
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
			}catch(Exception e){}
		}
		return result;
	}
	
}