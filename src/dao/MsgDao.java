package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MsgDao {
   private static MsgDao instance = new MsgDao();

   private MsgDao() {
   }

   public static MsgDao getInstance() {
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

   public int insert(Msg msg) {
      int result = 0, number = 0;
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      String sql = "insert into msg values(?,?,?,?,'n','n','n',null,?,?)"; 
      String sql1 = "select nvl(max(msgNum),0)+1 from msg";
      try {
         conn = getConnection();
         pstmt = conn.prepareStatement(sql1);
         rs = pstmt.executeQuery();
         if (rs.next())
            number = rs.getInt(1);         
         pstmt.close();        
         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, number);
         pstmt.setString(2, msg.getSendId());    
         pstmt.setString(3, msg.getReceiveId());
         pstmt.setTimestamp(4, msg.getSendTime());
         pstmt.setString(5, msg.getMsgSubject());
         pstmt.setString(6, msg.getMsgContent());
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

   public List<Msg> sendSelect(String id) {
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      String sql = "select * from msg where sendId=? and sendDelChk='n' order by msgNum desc";
      List<Msg> list = new ArrayList<>();
      try {
         conn = getConnection();
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, id);
         rs = pstmt.executeQuery();
         while (rs.next()) {
        	 Msg msg = new Msg();        	 
        	 msg.setMsgNum(rs.getInt("msgNum"));
        	 msg.setSendId(rs.getString("sendId"));
        	 msg.setReceiveId(rs.getString("receiveId"));
        	 msg.setSendTime(rs.getTimestamp("sendTime"));
        	 msg.setSendDelChk(rs.getString("sendDelChk"));
        	 msg.setReceiveDelChk(rs.getString("receiveDelChk"));
        	 msg.setReceiveChk(rs.getString("receiveChk"));
        	 msg.setReceiveTime(rs.getTimestamp("receiveTime"));
        	 msg.setMsgSubject(rs.getString("msgSubject"));
        	 msg.setMsgContent(rs.getString("msgContent"));
        	 list.add(msg);
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
   public List<Msg> receiveSelect(String id) {
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      String sql = "select * from msg where receiveId=? and receiveDelChk='n' order by msgNum desc";
	      List<Msg> list = new ArrayList<>();
	      try {
	         conn = getConnection();
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setString(1, id);
	         rs = pstmt.executeQuery();
	         while (rs.next()) {
	        	 Msg msg = new Msg();        	 
	        	 msg.setMsgNum(rs.getInt("msgNum"));
	        	 msg.setSendId(rs.getString("sendId"));
	        	 msg.setReceiveId(rs.getString("receiveId"));
	        	 msg.setSendTime(rs.getTimestamp("sendTime"));
	        	 msg.setSendDelChk(rs.getString("sendDelChk"));
	        	 msg.setReceiveDelChk(rs.getString("receiveDelChk"));
	        	 msg.setReceiveChk(rs.getString("receiveChk"));
	        	 msg.setReceiveTime(rs.getTimestamp("receiveTime"));
	        	 msg.setMsgSubject(rs.getString("msgSubject"));
	        	 msg.setMsgContent(rs.getString("msgContent"));
	        	 list.add(msg);
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
   public Msg selectOne(int msgNum) {
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      String sql = "select * from msg where msgNum=?";

     	 Msg msg = new Msg();
	      try {
	         conn = getConnection();
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setInt(1, msgNum);
	         rs = pstmt.executeQuery();
	         if (rs.next()) {        	 
	        	 msg.setMsgNum(rs.getInt("msgNum"));
	        	 msg.setSendId(rs.getString("sendId"));
	        	 msg.setReceiveId(rs.getString("receiveId"));
	        	 msg.setSendTime(rs.getTimestamp("sendTime"));
	        	 msg.setSendDelChk(rs.getString("sendDelChk"));
	        	 msg.setReceiveDelChk(rs.getString("receiveDelChk"));
	        	 msg.setReceiveChk(rs.getString("receiveChk"));
	        	 msg.setReceiveTime(rs.getTimestamp("receiveTime"));
	        	 msg.setMsgSubject(rs.getString("msgSubject"));
	        	 msg.setMsgContent(rs.getString("msgContent"));
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
	      return msg;
	   }
   public void receiveUpdate(int msgNum, Timestamp time) {
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      String sql = "update msg set receiveChk = 'y',receiveTime=? "
	      		+ "where msgNum=?";

	      try {
	         conn = getConnection();
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setTimestamp(1, time);
	         pstmt.setInt(2, msgNum);
	         pstmt.executeUpdate();
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
   public int delMsg(int msgNum, String delChk) {
	      Connection conn = null;
	      PreparedStatement pstmt = null;	      
	      
	      String sql = "update msg set sendDelChk = 'y' where msgNum=?";
	      String sql2 = "update msg set receiveDelChk = 'y' where msgNum=?";
	      int result = 0;
	      try {
	         conn = getConnection();
	         if(delChk.equals("sendDelChk")){
	        	 pstmt = conn.prepareStatement(sql);
	         }else if(delChk.equals("receiveDelChk")){
	        	 pstmt = conn.prepareStatement(sql2);
	         }
	         pstmt.setInt(1, msgNum);
	         pstmt.executeUpdate();
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
   public int sendTotal(String id) {
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      String sql = "select count(*) from msg where sendId=? and sendDelChk='n'";
	      int total = 0;
	      try {
	         conn = getConnection();
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setString(1, id);
	         rs = pstmt.executeQuery();
	         if (rs.next()) {        	 
	        	  total = rs.getInt(1);
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
   public int resTotal(String id) {
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      String sql = "select count(*) from msg where receiveId=? and receiveDelChk='n'";
	      int total = 0;
	      try {
	         conn = getConnection();
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setString(1, id);
	         rs = pstmt.executeQuery();
	         if (rs.next()) {        	 
	        	  total = rs.getInt(1);	        	  
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
}