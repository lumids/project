package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Board;
import dao.BoardDao;
import dao.MemberDao;
import dao.Point;
import dao.PointDao;
import dao.ReBoard;
import dao.ReBoardDao;

public class RWriteAction2 implements CommandProcess{

   @Override
   public String requestPro(HttpServletRequest request, HttpServletResponse response) {

	   int reBoardNum = Integer.parseInt(request.getParameter("reBoardNum"));
		int RpageNum = Integer.parseInt(request.getParameter("RpageNum"));
		int boardNum = Integer.parseInt(request.getParameter("boardNum"));
		String id = request.getParameter("id");
		
		request.setAttribute("reBoardNum", reBoardNum);
		request.setAttribute("RpageNum", RpageNum);
		request.setAttribute("boardNum", boardNum);
		request.setAttribute("id", id);
		return "reWrite.jsp";
   }
}