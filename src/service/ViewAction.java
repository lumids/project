package service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Board;
import dao.BoardDao;
import dao.ReBoard;
import dao.ReBoardDao;

public class ViewAction implements CommandProcess{

	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		String id =  (String) session.getAttribute("id");
		int boardNum = Integer.parseInt(request.getParameter("boardNum"));
		String pageNum = request.getParameter("pageNum");
		BoardDao bd = BoardDao.getInstance();
		bd.updateHit(boardNum);
		Board board = bd.select(boardNum);
		
		int rowPerPage = 10;
		int pagePerBlock = 10;
		   
		   String RpageNum = request.getParameter("RpageNum");
		   if(RpageNum == null || RpageNum.equals("")||RpageNum.equals("null")) RpageNum = "1";
		   int   nowPage = Integer.parseInt(RpageNum);
		   ReBoardDao rbd = ReBoardDao.getInstance();
		   int total = rbd.selectTotal(boardNum);
		   int totalPage = (int)Math.ceil((double)total/rowPerPage);
		   int startRow = (nowPage - 1) * rowPerPage + 1; 
		   int endRow = startRow + rowPerPage - 1;
		   int totalBlk = (int)Math.ceil((double)totalPage/pagePerBlock);
		   int startPage = (nowPage-1)/10*10+1;
		   int endPage = startPage+pagePerBlock - 1;
		   
		   //토탈페이지보다 페이지가 많을시 더 보이면안되니깐
		   if(endPage>totalPage) endPage=totalPage;		   
		   total = total-startRow+1;
		   List<ReBoard> list = rbd.selectList(startRow,endRow,boardNum);
		
		request.setAttribute("boardNum", boardNum);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("RpageNum", RpageNum);
		request.setAttribute("board", board);
		request.setAttribute("rbd", rbd);
		
		request.setAttribute("rowPerPage", rowPerPage);
	   request.setAttribute("pagePerBlock", pagePerBlock);
	   request.setAttribute("pageNum", pageNum);
	   request.setAttribute("nowPage", nowPage);
	   request.setAttribute("totalBlk", totalBlk);
	   request.setAttribute("startPage", startPage);
	   request.setAttribute("endPage", endPage);
	   request.setAttribute("totalPage", totalPage);
	   request.setAttribute("list", list);
	   request.setAttribute("total", total);
	   request.setAttribute("id", id);
		return "view.jsp";
	}

}
