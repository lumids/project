package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ReBoardDao {
   private static ReBoardDao instance = new ReBoardDao();

   private ReBoardDao() {
   }

   public static ReBoardDao getInstance() {
      return instance;
   }

   public Connection getConnection() {
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

   public int insert(ReBoard rbd) {
      int result = 0, number = 0;
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      String sql = "insert into ReBoard values(?,?,?,?,?,?,?,?,'n')"; 
      String sql1 = "select nvl(max(ReBoardNum),0)+1 from ReBoard";
      String sql2 = "update reBoard set rRe_step=rRe_step+1 where rRef=? and rRe_step>?";
      
      try {
         conn = getConnection();
         pstmt = conn.prepareStatement(sql1);
         rs = pstmt.executeQuery();
         if (rs.next())
            number = rs.getInt(1);         
         pstmt.close();
         if(rbd.getReBoardNum()!=0){
            pstmt = conn.prepareStatement(sql2);
            pstmt.setInt(1, rbd.getRRef());
            pstmt.setInt(2, rbd.getRRe_step());
            pstmt.executeUpdate();
            pstmt.close();
            rbd.setRRe_level(rbd.getRRe_level()+1);
            rbd.setRRe_step(rbd.getRRe_step()+1);
         }else rbd.setRRef(number);
         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, number);
         pstmt.setInt(2, rbd.getBoardNum());
         pstmt.setString(3, rbd.getId());
         pstmt.setString(4, rbd.getContent());
         pstmt.setInt(5, rbd.getRRef());
         pstmt.setInt(6, rbd.getRRe_step());
         pstmt.setInt(7, rbd.getRRe_level());
         pstmt.setTimestamp(8, rbd.getRWriteDate());
         result = pstmt.executeUpdate();
      } catch (Exception e) {
         System.out.println(e.getMessage());
      } finally {
    	 try{
	         if (pstmt != null)
	            pstmt.close();
	         if (conn != null)
	            conn.close();
	         if (rs != null)
	            rs.close();
    	 } catch(Exception e){}
      }
      return result;
   }

   
   public ReBoard select(int ReboardNum) {
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      String sql = "select * from reBoard where ReboardNum=?";
      ReBoard rbd = new ReBoard();
      try {
         conn = getConnection();
         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, ReboardNum);
         rs = pstmt.executeQuery();
         if (rs.next()) {
        	 rbd.setReBoardNum(rs.getInt("reBoardNum"));
        	 rbd.setBoardNum(rs.getInt("boardNum"));
        	 rbd.setId(rs.getString("id"));
        	 rbd.setContent(rs.getString("content"));
        	 rbd.setRRef(rs.getInt("rRef"));            
        	 rbd.setRRe_step(rs.getInt("rRe_step"));
        	 rbd.setRRe_level(rs.getInt("rRe_level"));
        	 rbd.setRWriteDate(rs.getTimestamp("RWriteDate"));
        	 rbd.setRDelChk(rs.getString("RDelChk"));
         }
      } catch (Exception e) {
         System.out.println(e.getMessage());
      } finally {
    	  try{
 	         if (pstmt != null)
 	            pstmt.close();
 	         if (conn != null)
 	            conn.close();
 	         if (rs != null)
 	            rs.close();
     	 } catch(Exception e){}
      }
      return rbd;
   }
   public int selectR(int boardNum) {
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      int result = 0;
	      String sql = "select count(reBoardNum) from reBoard where boardNum=?";
	      ReBoard rbd = new ReBoard();
	      try {
	         conn = getConnection();
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setInt(1, boardNum);
	         rs = pstmt.executeQuery();
	         if (rs.next()) {
	        	 result = rs.getInt(1);
	         }
	      } catch (Exception e) {
	         System.out.println(e.getMessage());
	      } finally {
	    	  try{
	 	         if (pstmt != null)
	 	            pstmt.close();
	 	         if (conn != null)
	 	            conn.close();
	 	         if (rs != null)
	 	            rs.close();
	     	 } catch(Exception e){}
	      }
	      return result;
	   }
 
   public int update(int reBoardNum,String content) {
      int result = 0;
      Connection conn = null;
      PreparedStatement pstmt = null;
      String sql = "update reBoard set content=? where reBoardNum=?";
      try {
         conn = getConnection();
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, content);
         pstmt.setInt(2, reBoardNum);
         result = pstmt.executeUpdate();
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
      return result;
   }
   public int updateWarn(int reBoardNum) {
	      int result = 0;
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      String sql = "update reBoard set rDelChk='w' where reBoardNum=?";
	      try {
	         conn = getConnection();
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setInt(1, reBoardNum);
	         result = pstmt.executeUpdate();
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
	      return result;
	   }

   public int delete(int reBoardNum) {
      int result = 0;
      Connection conn = null;
      PreparedStatement pstmt = null;
      String sql = "update reBoard set rDelChk='y' where reBoardNum=?";
      
      try {
         conn = getConnection();
         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, reBoardNum);
         result = pstmt.executeUpdate();
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
      return result;
   }
   
   public int selectTotal(int boardNum) {
      Connection conn = null;
      int total=0;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      String sql = "select count(*) from reBoard where boardNum=?";
      try {
         conn = getConnection();
         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, boardNum);
         rs = pstmt.executeQuery();
         if (rs.next()) {
            total=rs.getInt(1);
         }
      } catch (Exception e) {
         System.out.println(e.getMessage());
      } finally {
    	  try{
 	         if (pstmt != null)
 	            pstmt.close();
 	         if (conn != null)
 	            conn.close();
 	         if (rs != null)
 	            rs.close();
     	 } catch(Exception e){}
      }
      return total;
   }
   public List<ReBoard> selectList(int startRow,int endRow,int boardNum) {
      List<ReBoard> list = new ArrayList<>();
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
       
      String sql = "select * from (select rowNum rn, a.* from "
            +"(select * from ReBoard order by Rref desc,Rre_step) a) "
            +" where rn between ? and ? and boardNum=?";  
      try {
         conn = getConnection();
         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, startRow);
         pstmt.setInt(2, endRow);
         pstmt.setInt(3, boardNum);
         rs = pstmt.executeQuery();
         while (rs.next()) {
        	ReBoard board = new ReBoard();
        	board.setReBoardNum(rs.getInt("reBoardNum"));
            board.setBoardNum(rs.getInt("boardNum"));
            board.setId(rs.getString("Id"));
            board.setContent(rs.getString("content"));
            board.setRRef(rs.getInt("rRef"));            
            board.setRRe_step(rs.getInt("rRe_step"));
            board.setRRe_level(rs.getInt("rRe_level"));
            board.setRWriteDate(rs.getTimestamp("rWriteDate"));
            board.setRDelChk(rs.getString("rDelChk"));
            list.add(board);
         }
      } catch (Exception e) {
         System.out.println(e.getMessage());
      } finally {
    	  try{
 	         if (pstmt != null)
 	            pstmt.close();
 	         if (conn != null)
 	            conn.close();
 	         if (rs != null)
 	            rs.close();
     	 } catch(Exception e){}
      }
	return list;
   }
}