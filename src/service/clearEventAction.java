package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AniOwnDao;
import dao.MapItemGetInfo;
import dao.MapItemGetInfoDao;

public class clearEventAction implements CommandProcess {
	
	public String requestPro(HttpServletRequest request, HttpServletResponse response){		
		HttpSession session = request.getSession();
		String id =  (String) session.getAttribute("id");
		
		int mapItemNum = Integer.parseInt(request.getParameter("mapItemNum"));
		
		MapItemGetInfoDao migi = MapItemGetInfoDao.getInstance();		
		migi.insert(mapItemNum,id); 
		
		
		return "event.do";
	}

}
