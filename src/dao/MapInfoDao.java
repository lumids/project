package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
public class MapInfoDao {
	private static MapInfoDao instance = new MapInfoDao();
	private MapInfoDao() {}
	public static MapInfoDao getInstance() {
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
	public int insert(MapInfo mi) {
		int result = 0,mapNum = 0;  Connection conn = null; 
		PreparedStatement pstmt = null;  ResultSet rs = null;
		String sql  = "insert into MapInfo values (?,?,?,?)";
		String sql2 = "select nvl(max(mapNum),0)+1 from MapInfo";
		try { conn  = getConnection();
			pstmt = conn.prepareStatement(sql2);
			rs = pstmt.executeQuery();
			if (rs.next()) mapNum = rs.getInt(1);
			mi.setMapNum(mapNum);
			pstmt.close();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mi.getMapNum());
			pstmt.setString(2, mi.getId());
			pstmt.setFloat(3, mi.getX());
			pstmt.setFloat(4, mi.getY());
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
	public MapInfo selectOne(String id) {
		Connection conn = null; 
		MapInfo mi = new MapInfo();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql  = "select * from MapInfo where id=?";
		try { conn  = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()){				
				mi.setId(rs.getString("id"));
				mi.setMapNum(rs.getInt("mapNum"));
				mi.setX(rs.getFloat("x"));
				mi.setY(rs.getFloat("y"));
			}
		}catch(Exception e) { System.out.println(e.getMessage());
		}finally {
			try {if (rs != null) rs.close();
				 if (pstmt != null) pstmt.close();
				 if (conn != null) conn.close();
			}catch(Exception e) {}
		}
		return mi;
	}
	public int update(String id,float x, float y) {
		Connection conn = null; 
		MapInfo mi = new MapInfo();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int mapNum = 0, result = 0;
		String sql  = "select * from MapInfo where id=?";
		String sql2 = "update mapInfo set x = ?, y = ? where id=?";
		String sql3  = "insert into MapInfo values (?,?,?,?)";
		String sql4 = "select nvl(max(mapNum),0)+1 from MapInfo";
		try { conn  = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				System.out.println();
				pstmt.close();
				pstmt = conn.prepareStatement(sql2);
				pstmt.setFloat(1, x);
				pstmt.setFloat(2, y);
				pstmt.setString(3, id);
				pstmt.executeQuery();
				pstmt.close();
				rs.close();
			}else{
				pstmt.close();
				rs.close();
				pstmt = conn.prepareStatement(sql4);
				rs = pstmt.executeQuery();
				if (rs.next()) mapNum = rs.getInt(1);
				mi.setMapNum(mapNum);
				pstmt.close();
				pstmt = conn.prepareStatement(sql3);
				pstmt.setInt(1, mi.getMapNum());
				pstmt.setString(2, id);
				pstmt.setFloat(3, x);
				pstmt.setFloat(4, y);
				result = pstmt.executeUpdate();
			}
		}catch(Exception e) { System.out.println(e.getMessage());
		}finally {
			try {if (rs != null) rs.close();
				 if (pstmt != null) pstmt.close();
				 if (conn != null) conn.close();
			}catch(Exception e) {}
		}
		return result;
	}
}