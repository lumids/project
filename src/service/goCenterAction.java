package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AniOwnDao;

public class goCenterAction implements CommandProcess {
	
	public String requestPro(HttpServletRequest request, HttpServletResponse response){		
		int ownNum = Integer.parseInt(request.getParameter("ownNum"));
		String id = request.getParameter("id");
		
		AniOwnDao aod = AniOwnDao.getInstance();
		int aniCenter = aod.updateOwn(ownNum);
		
		request.setAttribute("id", id);
		request.setAttribute("aniCenter", aniCenter);
		return "myPage.do?id="+id;
	}

}
