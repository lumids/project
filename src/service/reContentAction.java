package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class reContentAction implements CommandProcess{

	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		
		int reBoardNum = Integer.parseInt(request.getParameter("reBoardNum"));
		int RpageNum = Integer.parseInt(request.getParameter("RpageNum"));
		String content = request.getParameter("content");
		int boardNum = Integer.parseInt(request.getParameter("boardNum"));
		
		request.setAttribute("reBoardNum", reBoardNum);
		request.setAttribute("RpageNum", RpageNum);
		request.setAttribute("content", content);
		request.setAttribute("boardNum", boardNum);
		return "reContent.jsp";
	}

}
