package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AniOwn;
import dao.AniOwnDao;
import dao.Board;
import dao.BoardDao;

public class leaderAction implements CommandProcess{

	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		
		int ownNum = Integer.parseInt(request.getParameter("ownNum"));
		String id = request.getParameter("id");
		String leadChk = request.getParameter("leadChk");
		AniOwnDao aod = AniOwnDao.getInstance();
		
		int result = aod.updateLead(ownNum,id,leadChk);
		
		request.setAttribute("ownNum", ownNum);
		request.setAttribute("result", result);
		return "myPageAni.do?ownNum="+ownNum;
	}

}
