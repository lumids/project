package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MapItemInfoDao {
	private static MapItemInfoDao instance = new MapItemInfoDao();

	private MapItemInfoDao() {
	}

	public static MapItemInfoDao getInstance() {
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

	public int insert(MapItemInfo mii) {
		int result = 0, mapItemNum = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "insert into MapItemInfo values (?,?,'y',?,?)";
		String sql2 = "select nvl(max(mapItemNum),0)+1 from MapItemInfo";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql2);
			rs = pstmt.executeQuery();
			if (rs.next())
				mapItemNum = rs.getInt(1);
			mii.setMapItemNum(mapItemNum);
			pstmt.close();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mii.getMapItemNum());
			pstmt.setString(2, mii.getMapItemName());
			pstmt.setInt(3, mii.getMapItemPrice());
			pstmt.setString(4, mii.getMapItemPic());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage()+"5");
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

	public MapItemInfo selectOne2(int mapItemNum) {
		Connection conn = null;
		MapItemInfo mi = new MapItemInfo();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from MapItemInfo where mapItemNum=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mapItemNum);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				mi.setMapItemNum(rs.getInt("mapItemNum"));
				mi.setMapItemName(rs.getString("mapItemName"));
				mi.setMapEventChk(rs.getString("mapEventChk"));
				mi.setMapItemPrice(rs.getInt("mapItemPrice"));
				mi.setMapItemPic(rs.getString("mapItemPic"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage()+"3");
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
	public int selectTotal() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int total = 0;
		String sql = "select count(*) from MapItemInfo where mapEventChk='y'";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				total =  rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage()+"2");
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
		return total;
	}

	public List<MapItemInfo> selectList() {
		List<MapItemInfo> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from MapItemInfo where mapEventChk='y'";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MapItemInfo mi = new MapItemInfo();
				mi.setMapItemNum(rs.getInt("mapItemNum"));
				mi.setMapItemName(rs.getString("mapItemName"));
				mi.setMapEventChk(rs.getString("mapEventChk"));
				mi.setMapItemPrice(rs.getInt("mapItemPrice"));
				mi.setMapItemPic(rs.getString("mapItemPic"));
				list.add(mi);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage()+"1");
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
				if (rs != null)
					rs.close();
			} catch (Exception e) {
			}
		}
		return list;
	}
}