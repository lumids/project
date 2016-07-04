package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MapItemGetInfoDao {
	private static MapItemGetInfoDao instance = new MapItemGetInfoDao();

	private MapItemGetInfoDao() {
	}

	public static MapItemGetInfoDao getInstance() {
		return instance;
	}

	private Connection getConnection() {
		Connection conn = null;
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/OracleDB");
			conn = ds.getConnection();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}

	public int insert(int mapItemGetNum, String id) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "insert into MapItemGetInfo values (?,?,'y')";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, mapItemGetNum);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}
		return result;
	}

	public MapItemGetInfo playChk(String id, int mapItemNum) {
		Connection conn = null;
		MapItemGetInfo mi = new MapItemGetInfo();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from MapItemGetInfo where mapItemNum=?"
				+ " and id=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mapItemNum);
			pstmt.setString(2, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				mi.setMapItemNum(rs.getInt("mapItemNum"));
				mi.setMapItemGetChk(rs.getString("mapItemGetChk"));
				mi.setId(rs.getString("id"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}
		return mi;
	}

	public List<MapItemGetInfo> selectOne2(String id) {
		Connection conn = null;
		List<MapItemGetInfo> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from MapItemGetInfo where id=? and mapItemGetChk='y'";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MapItemGetInfo mi = new MapItemGetInfo();
				mi.setMapItemNum(rs.getInt("mapItemNum"));
				mi.setId(rs.getString("id"));
				mi.setMapItemGetChk(rs.getString("mapItemGetChk"));
				list.add(mi);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}
		return list;
	}
	public int updateGetChk(int mapItemNum,String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		String sql = "update mapItemGetInfo set mapItemGetChk='n'"
				+ " where mapItemNum=? and id=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mapItemNum);
			pstmt.setString(2, id);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}
		return result;
	}	

}