package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AniInfo;
import dao.AniInfoDao;
import dao.AniOwn;
import dao.AniOwnDao;

public class myPageAniForm implements CommandProcess{

	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		int ownNum = Integer.parseInt(request.getParameter("ownNum"));
		AniOwnDao aod = AniOwnDao.getInstance();
		AniOwn ao = aod.selectOne(ownNum);
		AniInfoDao aid = AniInfoDao.getInstance();
		AniInfo ai = aid.selectOne(ao.getAniNum());
		
		String owner = aod.selectId(ownNum);
		String owner2 = request.getParameter("owner");
		String id = request.getParameter("id");		
		int ran = (int)(Math.random()*3)+1;
		String hello = "";
		switch (ran) {
			case 1: hello =  ai.getAniHello1(); break;
			case 2: hello =  ai.getAniHello2(); break;
			case 3: hello =  ai.getAniHello3(); break;
		}
		request.setAttribute("ao", ao);
		request.setAttribute("ai", ai);
		request.setAttribute("aid", aid);
		request.setAttribute("hello", hello);
		request.setAttribute("owner", owner);
		request.setAttribute("owner2", owner2);
		request.setAttribute("id", id);
		return "myPageAni.jsp?ownNum="+ownNum;
	}

}
