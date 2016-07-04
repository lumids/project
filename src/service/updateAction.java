package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Board;
import dao.BoardDao;

public class updateAction implements CommandProcess {
public String requestPro(HttpServletRequest request, HttpServletResponse response) {
	String pageNum = request.getParameter("pageNum");
	BoardDao bd = BoardDao.getInstance();
	Board board = new Board();
	board.setSubject(request.getParameter("subject"));
    board.setId(request.getParameter("id"));
    board.setContent(request.getParameter("content"));
    board.setBoardNum(Integer.parseInt(request.getParameter("boardNum")));
	int result = bd.update(board);
	int boardNum = Integer.parseInt(request.getParameter("boardNum"));
	
	request.setAttribute("boardNum", boardNum);
	request.setAttribute("pageNum", pageNum);
    request.setAttribute("result", result);
		return "updatePro.jsp";
	}
}
