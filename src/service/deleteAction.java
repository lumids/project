package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDao;

public class deleteAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		int boardNum = Integer.parseInt(request.getParameter("boardNum"));
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		BoardDao bd = BoardDao.getInstance();
		int result = bd.delete(boardNum);
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("result", result);
		return "deletePro.jsp";
	}

}
