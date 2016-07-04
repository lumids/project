package service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AniInfoDao;
import dao.AniOwn;
import dao.AniOwnDao;

public class aniCenterForm implements CommandProcess{

	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		String id =  (String) session.getAttribute("id");
		
		AniOwnDao aod = AniOwnDao.getInstance();
		List<AniOwn> list = aod.selectGive();
		AniInfoDao aid = AniInfoDao.getInstance();		
		
		request.setAttribute("list", list);
		request.setAttribute("aod", aod);
		request.setAttribute("aid", aid);
		request.setAttribute("id", id);
		return "aniCenter.jsp";
	}
	
}
