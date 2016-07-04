package service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AniInfoDao;
import dao.AniOwn;
import dao.AniOwnDao;
import dao.FarmInfo;
import dao.FarmInfoDao;

public class farmForm implements CommandProcess{

	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String id =  (String) session.getAttribute("id");
		
		String owner = request.getParameter("id");
		AniOwnDao aod = AniOwnDao.getInstance();
		AniInfoDao aid = AniInfoDao.getInstance();
		
		FarmInfoDao fid =FarmInfoDao.getInstance();
		FarmInfo fi = fid.selectOne(owner);

		String pop = request.getParameter("pop");
		if(id!=owner &&pop==null){
			fid.updateHit(owner);
		}
		
		List<AniOwn> list = aod.select(owner);
		
		
		
		request.setAttribute("fi", fi);
		request.setAttribute("aod", aod);
		request.setAttribute("aid", aid);
		request.setAttribute("owner", owner);
		request.setAttribute("list", list);
		request.setAttribute("id", id);
		return "farm.jsp?id="+owner;
	}

}