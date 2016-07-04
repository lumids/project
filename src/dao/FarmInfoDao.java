package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
public class FarmInfoDao {
	private static FarmInfoDao instance = new FarmInfoDao();
	private FarmInfoDao() {}
	public static FarmInfoDao getInstance() {
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
	public int insert(FarmInfo fi) {
		int result = 0,farmNum = 0;  Connection conn = null; 
		PreparedStatement pstmt = null;  ResultSet rs = null;
		String sql  = "insert into FarmInfo values (?,?,?,?,0,0,?)";
		String sql2 = "select nvl(max(farmNum),0)+1 from FarmInfo";
		try { conn  = getConnection();
			pstmt = conn.prepareStatement(sql2);
			rs = pstmt.executeQuery();
			if (rs.next()) farmNum = rs.getInt(1);
			fi.setFarmNum(farmNum);
			pstmt.close();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fi.getFarmNum());
			pstmt.setString(2, fi.getId());
			pstmt.setString(3, fi.getFarmHello());
			pstmt.setString(4, fi.getFarmName());
			pstmt.setString(5, fi.getFarmBGCol());
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
	public FarmInfo selectOne(String id) {
		Connection conn = null; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		FarmInfo ai = new FarmInfo();
		String sql  = "select * from FarmInfo where id=?";
		try { conn  = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				ai.setFarmNum(rs.getInt("farmNum"));
				ai.setId(rs.getString("id"));
				ai.setFarmHello(rs.getString("farmHello"));
				ai.setFarmName(rs.getString("farmName"));
				ai.setFarmCount(rs.getInt("farmCount"));
				ai.setFarmPop(rs.getInt("farmPop"));
				ai.setFarmBGCol(rs.getString("farmBGCol"));
			}
		}catch(Exception e) { System.out.println(e.getMessage());
		}finally {
			try {if (rs != null) rs.close();
				 if (pstmt != null) pstmt.close();
				 if (conn != null) conn.close();
			}catch(Exception e) {}
		}
		return ai;
	}
	public List<FarmInfo> selectRList() {
		Connection conn = null; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<FarmInfo> list = new ArrayList<>();
		String sql  = "select * from FarmInfo order by farmPop desc";
		try { conn  = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				FarmInfo ai = new FarmInfo();
				ai.setFarmNum(rs.getInt("farmNum"));
				ai.setId(rs.getString("id"));
				ai.setFarmHello(rs.getString("farmHello"));
				ai.setFarmName(rs.getString("farmName"));
				ai.setFarmCount(rs.getInt("farmCount"));
				ai.setFarmPop(rs.getInt("farmPop"));
				ai.setFarmBGCol(rs.getString("farmBGCol"));
				list.add(ai);
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
	public int updateHello(String id,String farmHello) {
		Connection conn = null; 
		PreparedStatement pstmt = null;
		int result = 0;
		String sql  = "update farmInfo set farmHello = ? where id=?";
		try { conn  = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, farmHello);
			pstmt.setString(2, id);
			result = pstmt.executeUpdate();
		}catch(Exception e) { System.out.println(e.getMessage());
		}finally {
			try {
				 if (pstmt != null) pstmt.close();
				 if (conn != null) conn.close();
			}catch(Exception e) {}
		}
		return result;
	}
	public void updateHit(String id) {
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      String sql = "update farmInfo set farmCount = farmCount+1 where id=?";

	      try {
	         conn = getConnection();
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setString(1, id);
	         int result = pstmt.executeUpdate();
	      } catch (Exception e) {
	         System.out.println(e.getMessage());
	      } finally {
	    	  try{
	 	         if (pstmt != null)
	 	            pstmt.close();
	 	         if (conn != null)
	 	            conn.close();
	     	 } catch(Exception e){}
	      }
	   }
	public void updatePop (String id) {
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      String sql = "update farmInfo set farmPop = farmPop+1 where id=?";

	      try {
	         conn = getConnection();
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setString(1, id);
	         int result = pstmt.executeUpdate();
	      } catch (Exception e) {
	         System.out.println(e.getMessage());
	      } finally {
	    	  try{
	 	         if (pstmt != null)
	 	            pstmt.close();
	 	         if (conn != null)
	 	            conn.close();
	     	 } catch(Exception e){}
	      }
	   }
}