package dao;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
public class AniInfoDao {
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
	private static AniInfoDao instance = new AniInfoDao();
	   private static SqlSession session;	
		static {
		    try {
		      Reader reader = 
		    	Resources.getResourceAsReader("configuration.xml");
		      SqlSessionFactory sf = new SqlSessionFactoryBuilder()
		    		 .build(reader);
		      session = sf.openSession(true);
		      reader.close(); 
		    } catch (Exception e) { System.out.println("sqlMap¿À·ù"); }
		}
	   private AniInfoDao() {
	   }

	   public static AniInfoDao getInstance() {
	      return instance;
	   }
	public int insert(AniInfo pi) {
		int result = 0,aniNum = 0;				
		try {
			aniNum = (int)session.selectOne("selectNum");
			pi.setAniNum(aniNum);			
			result = session.insert("insertAniInfo",pi);
		}catch(Exception e) { System.out.println(e.getMessage());
		}
		return result;
	}
	public List<AniInfo> selectAll(String chk) {
		HashMap<String, String> hm = new HashMap<>();
		hm.put("chk", chk);
		List<AniInfo> list = new ArrayList<>();	
		try {
			list = session.selectList("selectListAniInfo",hm);   
			
	}catch(Exception e) { System.out.println(e.getMessage());
	}
	return list;
}
	public String selectName(int aniNum) {
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
				ai.setAniPic2(rs.getString("aniPic2"));
				ai.setAniName1(rs.getString("aniname1"));
				ai.setAniName2(rs.getString("aniname2"));
				ai.setAniNum(rs.getInt("aniNum"));
				ai.setAniMaxStat1(rs.getInt("aniMaxStat1"));
				ai.setAniMaxStat2(rs.getInt("aniMaxStat2"));
				ai.setAniPrice(rs.getInt("aniPrice"));
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
	public AniInfo selectOne(int aniNum) {
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
				ai.setAniNum(rs.getInt("aniNum"));
				ai.setAniName1(rs.getString("aniName1"));
				ai.setAniName2(rs.getString("aniName2"));
				ai.setAniHello1(rs.getString("aniHello1"));
				ai.setAniHello2(rs.getString("aniHello2"));
				ai.setAniHello3(rs.getString("aniHello3"));
				ai.setAniPic1(rs.getString("aniPic1"));
				ai.setAniPic2(rs.getString("aniPic2"));
				ai.setAniMaxStat1(rs.getInt("aniMaxStat1"));
				ai.setAniMaxStat2(rs.getInt("aniMaxStat2"));
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
	public AniInfo selectOwn(int aniNum) {
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
				ai.setAniNum(rs.getInt("aniNum"));
				ai.setAniName1(rs.getString("aniName1"));
				ai.setAniName2(rs.getString("aniName2"));
				ai.setAniHello1(rs.getString("aniHello1"));
				ai.setAniHello2(rs.getString("aniHello2"));
				ai.setAniHello3(rs.getString("aniHello3"));
				ai.setAniPic1(rs.getString("aniPic1"));
				ai.setAniPic2(rs.getString("aniPic2"));
				ai.setAniMaxStat1(rs.getInt("aniMaxStat1"));
				ai.setAniMaxStat2(rs.getInt("aniMaxStat2"));
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
}