package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AniOwnDao;

public class renameAction2 implements CommandProcess{

	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		
		String aniNaming = request.getParameter("aniNaming");		
		int ownNum = Integer.parseInt(request.getParameter("ownNum"));
		
		AniOwnDao aod = AniOwnDao.getInstance();
		aod.updateName(aniNaming,ownNum);
		
		
		request.setAttribute("ownNum", ownNum);
		return "myPageAni.do?ownNum="+ownNum;
	}

}
