package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
public class reportReasonDao {
	private static reportReasonDao instance = new reportReasonDao();
	private reportReasonDao() {}
	public static reportReasonDao getInstance() {
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
	public int insert(String reportReaVal) {
		int result = 0,reportReaNum = 0;  Connection conn = null; 
		PreparedStatement pstmt = null;  ResultSet rs = null;
		String sql  = "insert into reportReason values (?,?)";
		String sql2 = "select nvl(max(reportReaNum),0)+1 from reportReason";
		try { conn  = getConnection();
			pstmt = conn.prepareStatement(sql2);
			rs = pstmt.executeQuery();
			if (rs.next()) reportReaNum = rs.getInt(1);			
			pstmt.close();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reportReaNum);
			pstmt.setString(2, reportReaVal);
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
	public List<reportReason> selectAll() {
		Connection conn = null; 
		List<reportReason> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;	
		String sql = "select * from reportReason order by reportReaNum desc";
		
		try { conn  = getConnection();
			pstmt = conn.prepareStatement(sql);			
			rs = pstmt.executeQuery();
			while(rs.next()){
				reportReason rr = new reportReason();
				rr.setReportReaNum(rs.getInt("reportReaNum"));
				rr.setReportReaVal(rs.getString("reportReaVal"));
				list.add(rr);
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
	public String getValue(int reportReaNum) {
		Connection conn = null; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String name="";
		reportReason r = new reportReason();
		String sql  = "select * from reportReason where reportReaNum=?";
		try { conn  = getConnection();
			pstmt = conn.prepareStatement(sql);			
				pstmt.setInt(1,reportReaNum);			
				rs = pstmt.executeQuery();
				if(rs.next()){					
					name = r.setReportReaVal(rs.getString("reportReaVal"));					
				}
			
		}catch(Exception e) { System.out.println(e.getMessage());
		}finally {
			try {if (rs != null) rs.close();
				 if (pstmt != null) pstmt.close();
				 if (conn != null) conn.close();
			}catch(Exception e) {}
		}
		return name;
	}
}