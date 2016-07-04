package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDao {
   private static BoardDao instance = new BoardDao();

   private BoardDao() {
   }

   public static BoardDao getInstance() {
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

   public int insert(Board board) {
      int result = 0, number = 0;
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      String sql = "insert into board4 values(?,?,?,?,?,0,?,?,?,?,'n',?)"; 
      String sql1 = "select nvl(max(boardNum),0)+1 from board4";
      String sql2 = "update board4 set re_step=re_step+1 where ref=? and re_step>?";
      
      try {
         conn = getConnection();
         pstmt = conn.prepareStatement(sql1);
         rs = pstmt.executeQuery();
         if (rs.next())
            number = rs.getInt(1);         
         pstmt.close();
         if(board.getBoardNum()!=0){
            pstmt = conn.prepareStatement(sql2);
            pstmt.setInt(1, board.getRef());
            pstmt.setInt(2, board.getRe_step());
            pstmt.executeUpdate();
            pstmt.close();
            board.setRe_level(board.getRe_level()+1);
            board.setRe_step(board.getRe_step()+1);
         }else board.setRef(number);
         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, number);
         pstmt.setInt(2, board.getAniNum());
         pstmt.setString(3, board.getId());
         pstmt.setString(4, board.getSubject());
         pstmt.setString(5, board.getContent());
         pstmt.setInt(6, board.getRef());
         pstmt.setInt(7, board.getRe_step());
         pstmt.setInt(8, board.getRe_level());
         pstmt.setTimestamp(9, board.getWriteDate());
         pstmt.setString(10, board.getNoticeChk());
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

  
   
   public Board select(int boardNum) {
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      String sql = "select * from board4 where boardNum=?";
      Board board = new Board();
      try {
         conn = getConnection();
         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, boardNum);
         rs = pstmt.executeQuery();
         if (rs.next()) {
             board.setBoardNum(rs.getInt("boardNum"));
             board.setAniNum(rs.getInt("aniNum"));
             board.setId(rs.getString("id"));
             board.setSubject(rs.getString("subject"));
             board.setContent(rs.getString("content"));
             board.setRef(rs.getInt("ref"));            
             board.setRe_step(rs.getInt("re_step"));
             board.setRe_level(rs.getInt("re_level"));
             board.setWriteDate(rs.getTimestamp("writeDate"));
             board.setReadcount(rs.getInt("readcount"));
             board.setDelChk(rs.getString("delChk"));
             board.setNoticeChk(rs.getString("noticeChk"));
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
      return board;
   }
   public List<Board> selectNotice() {
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      String sql = "select * from board4 where noticeChk='y'";
	      List<Board> list = new ArrayList<>();
	      try {
	         conn = getConnection();
	         pstmt = conn.prepareStatement(sql);
	         rs = pstmt.executeQuery();
	         while (rs.next()) {
	        	 Board board = new Board();
	             board.setBoardNum(rs.getInt("boardNum"));
	             board.setAniNum(rs.getInt("aniNum"));
	             board.setId(rs.getString("id"));
	             board.setSubject(rs.getString("subject"));
	             board.setContent(rs.getString("content"));
	             board.setRef(rs.getInt("ref"));            
	             board.setRe_step(rs.getInt("re_step"));
	             board.setRe_level(rs.getInt("re_level"));
	             board.setWriteDate(rs.getTimestamp("writeDate"));
	             board.setReadcount(rs.getInt("readcount"));
	             board.setDelChk(rs.getString("delChk"));
	             board.setNoticeChk(rs.getString("noticeChk"));
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
   public void updateHit(int boardNum) {
      Connection conn = null;
      PreparedStatement pstmt = null;
      String sql = "update board4 set readCount = readCount+1 where boardNum=?";

      try {
         conn = getConnection();
         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, boardNum);
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
   
   public int update(Board board) {
      int result = 0;
      Connection conn = null;
      PreparedStatement pstmt = null;
      String sql = "update board4 set subject=?,content=? where boardNum=?";
      try {
         conn = getConnection();
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, board.getSubject());
         pstmt.setString(2, board.getContent());
         pstmt.setInt(3, board.getBoardNum());
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

   public int delete(int boardNum) {
      int result = 0;
      Connection conn = null;
      PreparedStatement pstmt = null;
      String sql = "update board4 set delChk='y' where boardNum=?";
      
      try {
         conn = getConnection();
         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, boardNum);
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
   
   public int selectTotal(String search, String keyword) {
      Connection conn = null;
      int total=0;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      String sql = "select count(*) from board4";
      String sql0 = " where";
      String sql2 = " content like ?";
      String sql3 = " or subject like ?";
      String sql4 = "";      
      try {
         conn = getConnection();
         if(keyword!=null){
        	if(keyword.equals("all")){
        		sql4 = sql+sql0+sql2+sql3;
        		pstmt = conn.prepareStatement(sql4);
        		pstmt.setString(1, '%'+search+'%');
        		pstmt.setString(2, '%'+search+'%');
        	}else{
        		sql4 = sql+sql0+sql2;
        		pstmt = conn.prepareStatement(sql4);
        		pstmt.setString(1, '%'+search+'%');
        	}
         }else{
        	 sql4 = sql;
        	 pstmt = conn.prepareStatement(sql);
         }
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
   public int selectTotal(int aniNum,String search, String keyword) {
	      Connection conn = null;
	      int total=0;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      String sql = "select count(*) from board4 where aniNum=?";
	      String sql2 = " and content like ?";
	      String sql3 = " or subject like ?";
	      String sql4 = "";
	      try {
	         conn = getConnection();
	         if(keyword!=null){
	         	if(keyword.equals("all")){
	         		sql4 = sql+sql2+sql3;
	         		pstmt = conn.prepareStatement(sql4);
	         		pstmt.setString(2, '%'+search+'%');
	         		pstmt.setString(3, '%'+search+'%');
	         	}else{
	         		sql4 = sql+sql2;
	         		pstmt = conn.prepareStatement(sql4);
	         		pstmt.setString(2, '%'+search+'%');
	         	}
	          }else{
	         	 sql4 = sql;
	         	 pstmt = conn.prepareStatement(sql);
	          }
	         pstmt.setInt(1, aniNum);
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
   public int selectTotal2() {
	      Connection conn = null;
	      int total=0;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      String sql = "select count(*) from board4 where noticeChk='y'";
	      try {
	         conn = getConnection();
	         pstmt = conn.prepareStatement(sql);
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
   public List<Board> selectList(int startRow,int endRow,String search, String keyword) {
	   List<Board> list = new ArrayList<>();
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;       
      String sql0 = "select * from (select rowNum rn, a.* from "
            +"(select * from board4";      
      String sql1 = " where";
      String sql2 = " content like ?";
      String sql3 = " or subject like ?";
      String sql4 = " order by ref desc,re_step) a) "
      +" where rn between ? and ?";
      String sql5 = "";
      try {
         conn = getConnection();
         if(search!=null){
	         if(keyword.equals("all")){	     
	        	 sql5 = sql0+sql1+sql2+sql3+sql4;
	        	 pstmt = conn.prepareStatement(sql5);
	        	 pstmt.setString(1, '%'+search+'%');
	        	 pstmt.setString(2, '%'+search+'%');
	        	 pstmt.setInt(3, startRow);
	             pstmt.setInt(4, endRow);
	         }else if(keyword.equals("con")){
	        	 sql5 = sql0+sql1+sql2+sql4;
	        	 pstmt = conn.prepareStatement(sql5);
	        	 pstmt.setString(1, '%'+search+'%');
	        	 pstmt.setInt(2, startRow);
	             pstmt.setInt(3, endRow);
	         }
         }
         else{
        	 sql5 = sql0+sql4;
        	 pstmt = conn.prepareStatement(sql5);
        	 pstmt.setInt(1, startRow);
             pstmt.setInt(2, endRow);
         }
    	 
         rs = pstmt.executeQuery();
         while (rs.next()) {
            Board board = new Board();
            board.setBoardNum(rs.getInt("boardNum"));
            board.setAniNum(rs.getInt("aniNum"));
            board.setId(rs.getString("Id"));
            board.setSubject(rs.getString("subject"));                     
            board.setContent(rs.getString("content"));
            board.setRef(rs.getInt("ref"));            
            board.setRe_step(rs.getInt("re_step"));
            board.setRe_level(rs.getInt("re_level"));
            board.setWriteDate(rs.getTimestamp("writeDate"));
            board.setReadcount(rs.getInt("readcount"));
            board.setDelChk(rs.getString("delChk"));
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
   public List<Board> selectList(int startRow,int endRow,int aniNum,String search,String keyword) {
	      List<Board> list = new ArrayList<>();
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;	       
	      String sql1 = "select * from (select rowNum rn, a.* from "
	            +"(select * from board4 where aniNum=?";
	      String sql0 = " and( ";
	      String sql000= " and ";
	      String sql2 = " content like ?";
	      String sql00 = ") ";
	      String sql3 = " or subject like ?";
	      String sql4 = " order by ref desc,re_step) a) "
	            +" where rn between ? and ?";
	      String sql5 = "";
	      try {
	         conn = getConnection();
	         if(search!=null){
		         if(keyword.equals("all")){	     
		        	 sql5 = sql1+sql0+sql2+sql3+sql00+sql4;
		        	 pstmt = conn.prepareStatement(sql5);
		        	 pstmt.setString(2, '%'+search+'%');
		        	 pstmt.setString(3, '%'+search+'%');
			         pstmt.setInt(4, startRow);
			         pstmt.setInt(5, endRow);
		         }else if(keyword.equals("con")){
		        	 sql5 = sql1+sql000+sql2+sql4;
		        	 pstmt = conn.prepareStatement(sql5);
		        	 pstmt.setString(2, '%'+search+'%');
			         pstmt.setInt(3, startRow);
			         pstmt.setInt(4, endRow);
		         }
	         }
	         else{
	        	 sql5 = sql1+sql4;
	        	 pstmt = conn.prepareStatement(sql5);
		         pstmt.setInt(2, startRow);
		         pstmt.setInt(3, endRow);
	         }
	         pstmt.setInt(1, aniNum);
	         rs = pstmt.executeQuery();
	         while (rs.next()) {
	            Board board = new Board();
	            board.setBoardNum(rs.getInt("boardNum"));
	            board.setAniNum(rs.getInt("aniNum"));
	            board.setId(rs.getString("Id"));	            
	            board.setSubject(rs.getString("subject"));
	            board.setContent(rs.getString("content"));
	            board.setRef(rs.getInt("ref"));            
	            board.setRe_step(rs.getInt("re_step"));
	            board.setRe_level(rs.getInt("re_level"));
	            board.setWriteDate(rs.getTimestamp("writeDate"));
	            board.setReadcount(rs.getInt("readcount"));
	            board.setDelChk(rs.getString("delChk"));   
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