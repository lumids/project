package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ReBoardDao;

public class reDeleteAction implements CommandProcess{

	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		
		int reBoardNum = Integer.parseInt(request.getParameter("reBoardNum"));
		int RpageNum = Integer.parseInt(request.getParameter("RpageNum"));
		int boardNum = Integer.parseInt(request.getParameter("boardNum"));
		
		ReBoardDao rbd = ReBoardDao.getInstance();
		int result =  rbd.delete(reBoardNum);
		
		request.setAttribute("RpageNum", RpageNum);
		request.setAttribute("reBoardNum", reBoardNum);
		request.setAttribute("result", result);
		return "view.do?boardNum="+boardNum+"&RpageNum="+RpageNum;
	}

}
