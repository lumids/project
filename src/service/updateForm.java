package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Board;
import dao.BoardDao;

public class updateForm implements CommandProcess {

	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		int boardNum = Integer.parseInt(request.getParameter("boardNum"));
		String pageNum = request.getParameter("pageNum");
		BoardDao bd = BoardDao.getInstance();
		Board board = bd.select(boardNum);
		
		request.setAttribute("board", board);
		request.setAttribute("pageNum", pageNum);
		return "updateForm.jsp";
	}

}
