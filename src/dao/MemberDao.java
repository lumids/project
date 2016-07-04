package dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.sql.*;
import javax.naming.*;
public class MemberDao {
	private static MemberDao instance; // = new MemberDao();
	private MemberDao() {}
	public static MemberDao getInstance() { // Singleton
		if (instance==null) instance = new MemberDao();
		return instance;
	}
	public Connection getConnection() {
		Connection conn = null;
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource)
				ctx.lookup("java:comp/env/jdbc/OracleDB");
			conn = ds.getConnection();
		}catch(Exception e) {System.out.println(e.getMessage());}
		return conn;
	}
	public int idChk(String id){
		int result = 0; Connection conn = null;
		PreparedStatement pstmt = null; ResultSet rs = null;
		String sql = "select id from member3 where id = ?";
		try {
			conn = getConnection();
			pstmt  = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) result = 1; 
		}catch(Exception e) {System.out.println(e.getMessage());
		}finally {
			try{
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch(Exception e){}
		}
		return result;
	}
	public int nickChk(String nickname){
		int result = 0; Connection conn = null;
		PreparedStatement pstmt = null; ResultSet rs = null;
		String sql = "select id from member3 where nickname = ?";
		try {
			conn = getConnection();
			pstmt  = conn.prepareStatement(sql);
			pstmt.setString(1, nickname);
			rs = pstmt.executeQuery();
			if (rs.next()) result = 1; 
		}catch(Exception e) {System.out.println(e.getMessage());
		}finally {
			try{
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch(Exception e){}
		}
		return result;
	}
	public int insert(Member mem){
		int result = 0; Connection conn = null;
		PreparedStatement pstmt = null; 
		ResultSet rs = null;
		String sql="insert into member3 values(?,?,?,0,'n',sysdate,?)";
		try { conn = getConnection();			
			pstmt  = conn.prepareStatement(sql);
			pstmt.setString(1, mem.getId());
			pstmt.setString(2, mem.getPasswd());
			pstmt.setString(3, mem.getNickname());
			pstmt.setInt(4, mem.getPointSum());
			result = pstmt.executeUpdate();
		}catch(Exception e) {System.out.println(e.getMessage());
		}finally {			
			try{
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch(Exception e){}
		}
		return result;
	}
	public int loginChk(Member m){
		int result = 0; Connection conn = null;
		PreparedStatement pstmt = null; ResultSet rs = null;
		String sql = "select passwd from member3 where id = ?";
		try { conn = getConnection();
			pstmt  = conn.prepareStatement(sql);
			pstmt.setString(1, m.getId());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String dbPass = rs.getString(1);
				if (dbPass.equals(m.getPasswd())) result = 1;
				else result = 0;
			} else result = -1;
		}catch(Exception e) {System.out.println(e.getMessage());
		}finally {
			try{
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			}catch(Exception e){}
		}
		return result;
	}
	public int delete(String id) throws SQLException {
		int result = 0; Connection conn = null;
		PreparedStatement pstmt = null; 
		String sql="delete from member2 where id=?";
		try { conn = getConnection();
			pstmt  = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			result = pstmt.executeUpdate();
		}catch(Exception e) {System.out.println(e.getMessage());
		}finally {			
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		}
		return result;
	}
	public List<Member> selectAll() {
		Connection conn = null;
		PreparedStatement pstmt = null; ResultSet rs = null;
		String sql = "select * from member3";
		List<Member> list = new ArrayList<>();		
		try { conn = getConnection();
			pstmt  = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Member mem = new Member();
				mem.setId(rs.getString("id"));
				mem.setNickname(rs.getString("nickname"));
				mem.setReg_date(rs.getDate("reg_date"));
				list.add(mem);
			} 
		}catch(Exception e) {System.out.println(e.getMessage());
		}finally {
			try{
			if (rs != null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
			}catch(Exception e){}
		}
		return list;
	}
	public Member selectOne(String id)  {
		Connection conn = null;
		PreparedStatement pstmt = null; ResultSet rs = null;
		String sql = "select * from member3 where id=?";
		Member mem = new Member();		
		try { conn = getConnection();
			pstmt  = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {				
				mem.setId(rs.getString("id"));				
				mem.setNickname(rs.getString("nickname"));
				mem.setReg_date(rs.getDate("reg_date"));
				mem.setPointSum(rs.getInt("pointSum"));
				mem.setWarnCount(rs.getInt("warnCount"));
			} 
		}catch(Exception e) {System.out.println(e.getMessage());
		}finally {
			try{
			if (rs != null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
			}catch(Exception e){}
		}
		return mem;
	}
	
	public int updatePoint(Point point) {
		int result = 0; Connection conn = null;
		PreparedStatement pstmt = null; 
		String sql="update member3 set pointSum = nvl(pointSum,0)+? where id=?";
		try { conn = getConnection();			
			pstmt  = conn.prepareStatement(sql);			
			pstmt.setInt(1, point.getGetPoint());
			pstmt.setString(2, point.getId());
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
	public int getWarn(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null; ResultSet rs = null;
		String sql = "select * from member3 where id = ?";
		Member mem = new Member();
		int count = 0;
		try { conn = getConnection();
			pstmt  = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				count =  rs.getInt("warnCount");
			} 
		}catch(Exception e) {System.out.println(e.getMessage());
		}finally {
			try{
			if (rs != null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
			}catch(Exception e){}
		}
		return count;
	}
	public int updateWarn(String id){
		int result = 0; Connection conn = null;
		PreparedStatement pstmt = null; 
		String sql="update member3 set warnCount=warnCount+1 where id=?";
		try { conn = getConnection();
			pstmt  = conn.prepareStatement(sql);			
			pstmt.setString(1, id);
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
	/*public List<Member> selectList(int startRow,int endRow) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null; ResultSet rs = null;
		// String sql = "select * from member2";
		String sql = "select * from (select rowNum rn, a.* from "
				+"(select * from member2) a) "
				+" where rn between ? and ?"; 
		List<Member> list = new ArrayList<>();		
		try { conn = getConnection();
			pstmt  = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Member mem = new Member();
				mem.setId(rs.getString("id"));				
				mem.setName(rs.getString("name"));
				mem.setAddress(rs.getString("address"));
				mem.setTel(rs.getString("tel"));
				mem.setReg_date(rs.getDate("reg_date"));
				list.add(mem);
			} 
		}catch(Exception e) {System.out.println(e.getMessage());
		}finally {
			if (rs != null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		}
		return list;
	}
	public int selectTotal() throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null; ResultSet rs = null;
		String sql = "select count(*) from member2";
		int total = 0;	
		try { conn = getConnection();
			pstmt  = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				total = rs.getInt(1);
			} 
		}catch(Exception e) {System.out.println(e.getMessage());
		}finally {
			if (rs != null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		}
		return total;
	}*/
}


