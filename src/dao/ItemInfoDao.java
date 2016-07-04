package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
public class ItemInfoDao {
	private static ItemInfoDao instance = new ItemInfoDao();
	private ItemInfoDao() {}
	public static ItemInfoDao getInstance() {
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
	public int insert(ItemInfo i) {
		int result = 0,itemNum = 0;  Connection conn = null; 
		PreparedStatement pstmt = null;  ResultSet rs = null;
		String sql  = "insert into ItemInfo values (?,?,?,?,?)";
		String sql2 = "select nvl(max(itemNum),0)+1 from itemInfo";
		try { conn  = getConnection();
			pstmt = conn.prepareStatement(sql2);
			rs = pstmt.executeQuery();
			if (rs.next()) itemNum = rs.getInt(1);
			i.setItemNum(itemNum);
			pstmt.close();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, i.getItemNum());
			pstmt.setString(2, i.getItemPic());
			pstmt.setString(3, i.getItemName());
			pstmt.setInt(4, i.getItemStat());
			pstmt.setInt(5, i.getItemPrice());
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
	public List<ItemInfo> selectAll() {
		Connection conn = null; 
		List<ItemInfo> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql  = "select * from itemInfo order by itemNum desc";
		try { conn  = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				ItemInfo i = new ItemInfo();
				i.setItemNum(rs.getInt("itemNum"));
				i.setItemName(rs.getString("itemName"));
				i.setItemPic(rs.getString("itemPic"));
				i.setItemStat(rs.getInt("itemStat"));
				i.setItemPrice(rs.getInt("itemPrice"));
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
	}*/
	public ItemInfo selectImg(int itemNum) {
		Connection conn = null; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ItemInfo ai = new ItemInfo();
		String sql  = "select * from ItemInfo where itemNum=?";
		try { conn  = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, itemNum);
			rs = pstmt.executeQuery();
			while(rs.next()){				
				ai.setItemPic(rs.getString("itemPic"));
				ai.setItemName(rs.getString("itemName"));
				ai.setItemPrice(rs.getInt("itemPrice"));
				ai.setItemStat(rs.getInt("itemStat"));	
				ai.setItemNum(rs.getInt("itemNum"));
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
	/*
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
	}*/
	public ItemInfo selectOne(int itemNum) {
		Connection conn = null; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ItemInfo id = new ItemInfo();
		String sql  = "select * from ItemInfo where itemNum=?";
		try { conn  = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, itemNum);
			rs = pstmt.executeQuery();
			while(rs.next()){
				id.setItemNum(rs.getInt("itemNum"));
				id.setItemName(rs.getString("itemName"));
				id.setItemPic(rs.getString("itemPic"));
				id.setItemStat(rs.getInt("itemStat"));
				id.setItemPrice(rs.getInt("itemPrice"));
			}
		}catch(Exception e) { System.out.println(e.getMessage());
		}finally {
			try {if (rs != null) rs.close();
				 if (pstmt != null) pstmt.close();
				 if (conn != null) conn.close();
			}catch(Exception e) {}
		}
		return id;
	}
}