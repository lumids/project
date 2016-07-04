package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ReBoard;
import dao.ReBoardDao;

public class reContentAction2 implements CommandProcess{

	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		
		int reBoardNum = Integer.parseInt(request.getParameter("reBoardNum"));
		int RpageNum = Integer.parseInt(request.getParameter("RpageNum"));
		String content = request.getParameter("content");
		int boardNum = Integer.parseInt(request.getParameter("boardNum"));
		
		ReBoardDao rbd = ReBoardDao.getInstance();
		int result = rbd.update(reBoardNum,content);
		
		request.setAttribute("reBoardNum", reBoardNum);
		request.setAttribute("RpageNum", RpageNum);
		request.setAttribute("content", content);
		request.setAttribute("result", result);
		
		return "view.do?boardNum="+boardNum+"&RpageNum="+RpageNum;
	}

}
