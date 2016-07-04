package service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AniInfo;
import dao.AniInfoDao;
import dao.Board;
import dao.BoardDao;

public class WriteForm implements CommandProcess{

	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String id =  (String) session.getAttribute("id");
		
		int boardNum = 0, ref = 0, re_step = 0, re_level = 0;
		String pageNum = request.getParameter("pageNum");
		if(request.getParameter("boardNum")!=null){/* 답변글일경우 */
			boardNum = Integer.parseInt(request.getParameter("boardNum"));
			BoardDao bd = BoardDao.getInstance();
			Board board = bd.select(boardNum);
			ref = board.getRef();
			re_step = board.getRe_step();
			re_level = board.getRe_level();
		}
		 AniInfoDao aid = AniInfoDao.getInstance();
		 List<AniInfo> list = aid.selectAll("all");
		
		
		request.setAttribute("boardNum", boardNum);
		request.setAttribute("ref", ref);
		request.setAttribute("re_step", re_step);
		request.setAttribute("re_level", re_level);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("list", list);
		request.setAttribute("id", id);
		return "writeForm.jsp";
	}
	
}
