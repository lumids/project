package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
public class ItemDetailDao {
	private static ItemDetailDao instance = new ItemDetailDao();
	private ItemDetailDao() {}
	public static ItemDetailDao getInstance() {
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
	public int insert(ItemDetail i) {
		int result = 0,itemDetailNum = 0;  Connection conn = null; 
		PreparedStatement pstmt = null;  ResultSet rs = null;
		String sql  = "insert into ItemDetail values (?,?,?,'n')";
		String sql2 = "select nvl(max(itemDetailNum),0)+1 from ItemDetail";
		try { conn  = getConnection();
			pstmt = conn.prepareStatement(sql2);
			rs = pstmt.executeQuery();
			if (rs.next()) itemDetailNum = rs.getInt(1);
			i.setItemDetailNum(itemDetailNum);
			pstmt.close();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, i.getItemDetailNum());
			pstmt.setInt(2, i.getItemNum());
			pstmt.setString(3, i.getId());
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
	public List<ItemDetail> selectList(String id) {
		Connection conn = null; 
		List<ItemDetail> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql  = "select * from ItemDetail where id=? and itemUseChk='n' order by itemDetailNum desc";
		try { conn  = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()){
				ItemDetail i = new ItemDetail();
				i.setItemNum(rs.getInt("itemNum"));
				i.setItemDetailNum(rs.getInt("itemDetailNum"));
				i.setId(rs.getString("id"));
				i.setItemUseChk(rs.getString("itemUseChk"));
				list.add(i);
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
	/*public String selectName(int aniNum) {
		Connection conn = null; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String name="";
		AniInfo ai = new AniInfo();
		String sql  = "select * from aniInfo where aniNum=?";
		try { conn  = getConnection();
			pstmt = conn.prepareStatement(sql);
			
				pstmt.setInt(1,aniNum);
			
				rs = pstmt.executeQuery();
				if(rs.next()){
					
					name = ai.setAniName1(rs.getString("aniName1"));
					System.out.println("1 "+ ai.getAniName1());
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
	public String selectName2(int ownNum) {
		Connection conn = null; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String name="";
		AniInfo ai = new AniInfo();
		String sql  = "select * from aniInfo where ownNum=?";
		try { conn  = getConnection();
			pstmt = conn.prepareStatement(sql);
			
				pstmt.setInt(1,ownNum);
			
				rs = pstmt.executeQuery();
				if(rs.next()){
					
					name = ai.setAniName1(rs.getString("aniName1"));
					System.out.println("1 "+ ai.getAniName1());
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
	public AniInfo selectImg(int aniNum) {
		Connection conn = null; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		AniInfo ai = new AniInfo();
		String sql  = "select * from aniInfo where aniNum=?";
		try { conn  = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, aniNum);
			rs = pstmt.executeQuery();
			while(rs.next()){				
				ai.setAniPic1(rs.getString("aniPic1"));
				ai.setAniName1(rs.getString("aniname1"));
				ai.setAniNum(rs.getInt("aniNum"));
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
	public List<AniInfo> selectImg2(int aniNum) {
		Connection conn = null; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<AniInfo> list = new ArrayList<>();
		String sql  = "select * from aniInfo where aniNum=?";
		try { conn  = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, aniNum);
			rs = pstmt.executeQuery();
			while(rs.next()){
				AniInfo ai = new AniInfo();
				ai.setAniPic1(rs.getString("aniPic1"));
				ai.setAniName1(rs.getString("aniname1"));
				ai.setAniNum(rs.getInt("aniNum"));
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
	*/
	public ItemInfo selectOne(int itemNum) {
		Connection conn = null; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ItemInfo ii = new ItemInfo();
		String sql  = "select * from ItemInfo where itemNum=?";
		try { conn  = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, itemNum);
			rs = pstmt.executeQuery();
			while(rs.next()){
				ii.setItemNum(rs.getInt("itemNum"));
				ii.setItemPic(rs.getString("itemPic"));
				ii.setItemName(rs.getString("itemName"));
				ii.setItemStat(rs.getInt("itemStat"));
				ii.setItemPrice(rs.getInt("itemPrice"));		
			}
		}catch(Exception e) { System.out.println(e.getMessage());
		}finally {
			try {if (rs != null) rs.close();
				 if (pstmt != null) pstmt.close();
				 if (conn != null) conn.close();
			}catch(Exception e) {}
		}
		return ii;
	}
}
