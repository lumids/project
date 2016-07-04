package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AniOwn;
import dao.AniOwnDao;
import dao.Board;
import dao.BoardDao;

public class statAction implements CommandProcess{

	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		
		int ownNum = Integer.parseInt(request.getParameter("ownNum"));
		int stat =  Integer.parseInt(request.getParameter("stat"));
		AniOwnDao aod = AniOwnDao.getInstance();
		
		aod.updateStat(ownNum,stat);
		
		request.setAttribute("ownNum", ownNum);
		return "myPageAni.do?ownNum="+ownNum;
	}

}
