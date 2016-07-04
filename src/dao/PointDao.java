package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class PointDao {
   private static PointDao instance = new PointDao();

   private PointDao() {
   }

   public static PointDao getInstance() {
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

   public int insert(Point point) {
      int result = 0, number = 0;
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      String sql = "insert into point values(?,?,sysdate,?,?)"; 
      String sql1 = "select nvl(max(pointNum),0)+1 from point";
      try {
         conn = getConnection();
         pstmt = conn.prepareStatement(sql1);
         rs = pstmt.executeQuery();
         if (rs.next())
            number = rs.getInt(1);         
         pstmt.close();        
         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, number);
         pstmt.setString(2, point.getId());    
         pstmt.setInt(3, point.getGetPoint());
         pstmt.setString(4, point.getPointGetReason());         
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

   public List<Point> select(String id) {
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      String sql = "select * from point where id=? order by pointNum desc";
      List<Point> list = new ArrayList<>();
      try {
         conn = getConnection();
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, id);
         rs = pstmt.executeQuery();
         while (rs.next()) {
        	 Point point = new Point();        	 
        	 point.setPointNum(rs.getInt("pointNum"));
        	 point.setId(rs.getString("id"));
        	 point.setPointGetReason(rs.getString("pointGetReason"));
        	 point.setPointGetTime(rs.getDate("pointGetTime"));
        	 point.setGetPoint(rs.getInt("getPoint"));
        	 list.add(point);
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