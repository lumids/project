package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MapInfoDao;

public class updateMapAction implements CommandProcess{

   @Override
   public String requestPro(HttpServletRequest request, HttpServletResponse response) {

	   String id = request.getParameter("id");
	   Float x = Float.parseFloat(request.getParameter("x"));
	   Float y = Float.parseFloat(request.getParameter("y"));

	   MapInfoDao mid = MapInfoDao.getInstance();
	   int result = mid.update(id,x,y);
			   
           
      return "myPage.do?id="+id;
   }
}