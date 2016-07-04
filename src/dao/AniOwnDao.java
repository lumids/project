package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class AniOwnDao {
	private static AniOwnDao instance = new AniOwnDao();

	private AniOwnDao() {
	}

	public static AniOwnDao getInstance() {
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

	public int insert(AniOwn ao,String lead) {
		int result = 0, ownNum = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		if(lead.equals("y")){
			 sql = "insert into AniOwn values (?,?,?,0,0,'y','y',?)";
		}else{
			 sql = "insert into AniOwn values (?,?,?,0,0,'n','y',?)";
		}
		
		String sql2 = "select nvl(max(ownNum),0)+1 from AniOwn";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql2);
			rs = pstmt.executeQuery();
			if (rs.next())
				ownNum = rs.getInt(1);
			ao.setOwnNum(ownNum);
			pstmt.close();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ao.getOwnNum());
			pstmt.setString(2, ao.getId());
			pstmt.setInt(3, ao.getAniNum());
			pstmt.setString(4, ao.getAniNaming());
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

	public List<AniOwn> select(String id) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		List<AniOwn> list = new ArrayList<>();
		String sql = "select * from AniOwn where id=? and aniOwnChk='y'";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				AniOwn ao = new AniOwn();
				ao.setAniNum(rs.getInt("aniNum"));
				ao.setId(rs.getString("id"));
				ao.setOwnNum(rs.getInt("ownNum"));
				ao.setNowStat1(rs.getInt("nowStat1"));
				ao.setNowStat2(rs.getInt("nowStat2"));
				ao.setAniNaming(rs.getString("aniNaming"));
				ao.setLeadChk(rs.getString("leadChk"));
				list.add(ao);
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

	public AniOwn selectOne(int ownNum) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		AniOwn ao = new AniOwn();
		String sql = "select * from aniOwn where ownNum=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ownNum);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ao.setAniNum(rs.getInt("aniNum"));
				ao.setOwnNum(rs.getInt("ownNum"));
				ao.setAniNaming(rs.getString("aniNaming"));
				ao.setLeadChk(rs.getString("leadChk"));
				ao.setNowStat1(rs.getInt("nowStat1"));
				ao.setNowStat2(rs.getInt("nowStat2"));
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
		return ao;
	}

	public List<AniOwn> selectOne2(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<AniOwn> list = new ArrayList<>();
		String sql = "select * from aniOwn where id=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				AniOwn ao = new AniOwn();
				ao.setAniNum(rs.getInt("aniNum"));
				ao.setOwnNum(rs.getInt("ownNum"));
				ao.setAniNaming(rs.getString("aniNaming"));
				ao.setLeadChk(rs.getString("leadChk"));
				ao.setNowStat1(rs.getInt("nowStat1"));
				ao.setNowStat2(rs.getInt("nowStat2"));
				list.add(ao);
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

	public void updateName(String aniNaming, int ownNum) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "update aniOwn set aniNaming = ? where ownNum=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, aniNaming);
			pstmt.setInt(2, ownNum);
			int result = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}
	}
	public int updateAniOwn(String id, int ownNum) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "update aniOwn set id = ?,aniOwnChk='y' where ownNum=?";
		int result = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, ownNum);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}
		return result;
	}

	public void updateStat(int ownNum, int stat) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "update aniOwn set nowStat1 = nowStat1+1 where ownNum=?";
		String sql2 = "update aniOwn set nowStat2 = nowStat2+1 where ownNum=?";
		try {
			conn = getConnection();
			if (stat == 1) {
				pstmt = conn.prepareStatement(sql);
			} else if (stat == 2) {
				pstmt = conn.prepareStatement(sql2);
			}
			pstmt.setInt(1, ownNum);
			int result = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}
	}

	public int updateStatI(int ownNum, int itemDetailNum, int itemNum) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		int itemStat = 0;
		ResultSet rs = null;
		String sql = "update aniOwn set nowStat1 = nowStat1+? where ownNum=?";
		String sql2 = "select itemStat from itemInfo where itemNum=?";
		String sql3 = "update itemDetail set itemUseChk = 'y' where itemDetailNum=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql3);
			pstmt.setInt(1, itemDetailNum);
			pstmt.executeQuery();
			pstmt.close();
			pstmt = conn.prepareStatement(sql2);
			pstmt.setInt(1, itemNum);
			rs = pstmt.executeQuery();
			if (rs.next())
				itemStat = rs.getInt(1);
			pstmt.close();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, itemStat);
			pstmt.setInt(2, ownNum);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}
		return result;
	}

	public int updateLead(int ownNum, String id, String leadChk) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "update aniOwn set leadChk = 'n' where id=?";
		String sql2 = "update aniOwn set leadChk = 'y' where id=? and ownNum=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.executeQuery();
			pstmt.close();
			if (leadChk.equals("n")) {
				pstmt = conn.prepareStatement(sql2);
				pstmt.setString(1, id);
				pstmt.setInt(2, ownNum);
				result = pstmt.executeUpdate();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}
		return result;
	}

	public int updateOwn(int ownNum) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "update aniOwn set aniOwnChk = 'n',leadChk='n' where ownNum=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ownNum);
			pstmt.executeQuery();			
			pstmt.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}
		return result;
	}

	public String selectId(int ownNum) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		AniOwn ao = new AniOwn();
		String owner = "";
		String sql = "select * from aniOwn where ownNum=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ownNum);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				owner = ao.setId(rs.getString("id"));
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
		return owner;
	}
	
	public List<AniOwn> selectOwnList(String id) {
		Connection conn = null;
		List<AniOwn> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from AniOwn where id=? and aniOwnChk='y'";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				AniOwn ao = new AniOwn();
				ao.setAniNum(rs.getInt("aniNum"));
				ao.setAniNaming(rs.getString("aniNaming"));
				ao.setOwnNum(rs.getInt("ownNum"));
				ao.setNowStat1(rs.getInt("nowStat1"));
				ao.setNowStat2(rs.getInt("nowStat2"));
				list.add(ao);
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
	public List<AniOwn> selectRList() {
		Connection conn = null;
		List<AniOwn> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from AniOwn where aniOwnChk='y' order by nowStat1+nowStat2 desc";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				AniOwn ao = new AniOwn();
				ao.setAniNum(rs.getInt("aniNum"));
				ao.setAniNaming(rs.getString("aniNaming"));
				ao.setOwnNum(rs.getInt("ownNum"));
				ao.setNowStat1(rs.getInt("nowStat1"));
				ao.setNowStat2(rs.getInt("nowStat2"));
				ao.setId(rs.getString("id"));
				list.add(ao);
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

	public List<AniOwn> selectGive() {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		List<AniOwn> list = new ArrayList<>();
		String sql = "select * from AniOwn where aniOwnChk='n'";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				AniOwn ao = new AniOwn();
				ao.setAniNum(rs.getInt("aniNum"));
				ao.setId(rs.getString("id"));
				ao.setOwnNum(rs.getInt("ownNum"));
				ao.setNowStat1(rs.getInt("nowStat1"));
				ao.setNowStat2(rs.getInt("nowStat2"));
				ao.setAniNaming(rs.getString("aniNaming"));
				ao.setLeadChk(rs.getString("leadChk"));
				ao.setAniOwnChk(rs.getString("aniOwnChk"));
				list.add(ao);
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
}
