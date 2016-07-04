package service;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.*;

public class ListAction implements CommandProcess{

	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		int rowPerPage = 10;
		int pagePerBlock = 10;
		   
		   String pageNum = request.getParameter("pageNum");
		   if(pageNum == null || pageNum.equals("")||pageNum.equals("null")) pageNum = "1";
		   int   nowPage = Integer.parseInt(pageNum);
		   BoardDao bd = BoardDao.getInstance();
		   int total = 0;
		   int total2 = bd.selectTotal2();
		   
		   int totalPage = 0;		   
		   int startRow = 0; 
		   int endRow = 0;
		   
		   int  aniNum = (request.getParameter("aniNum") == null) ? 0 : Integer.parseInt(request.getParameter("aniNum"));
		   List<Board> list = null;
		   
		   String search = request.getParameter("search");
		   String keyword = request.getParameter("keyword");
		   int totalBlk = 0;
		   int startPage = 0;
		   int endPage = 0;
		   if(aniNum<=0 ){
			   startRow = (nowPage - 1) * rowPerPage + 1; 
			   endRow = startRow + rowPerPage - 1;		
			   list = bd.selectList(startRow,endRow,search,keyword);
			   total = bd.selectTotal(search,keyword);
			   totalPage = (int)Math.ceil((double)total/rowPerPage); 
			   
			   totalBlk = (int)Math.ceil((double)totalPage/pagePerBlock);
			   startPage = (nowPage-1)/10*10+1;
			   endPage = startPage+pagePerBlock - 1;
			   
			   //토탈페이지보다 페이지가 많을시 더 보이면안되니깐
			   if(endPage>totalPage) endPage=totalPage;		   
			   total = total-startRow+1;
			   
		   }else{
			   startRow = (nowPage - 1) * rowPerPage + 1; 
			   endRow = startRow + rowPerPage - 1;
			   
			   list = bd.selectList(startRow, endRow, aniNum,search,keyword);
			   total = bd.selectTotal(aniNum,search,keyword);
			   totalPage = (int)Math.ceil((double)total/rowPerPage);
			   
			   totalBlk = (int)Math.ceil((double)totalPage/pagePerBlock);
			   startPage = (nowPage-1)/10*10+1;
			   endPage = startPage+pagePerBlock - 1;
			   
			   //토탈페이지보다 페이지가 많을시 더 보이면안되니깐
			   if(endPage>totalPage) endPage=totalPage;		   
			   total = total-startRow+1;
		   }
		   
		   AniInfoDao aid = AniInfoDao.getInstance();
		   ReBoardDao rbd = ReBoardDao.getInstance();
		   List<Board> list2 = bd.selectNotice();
		   List<AniInfo> list3 = aid.selectAll("all");
		   
		   
		   request.setAttribute("rowPerPage", rowPerPage);
		   request.setAttribute("pagePerBlock", pagePerBlock);
		   request.setAttribute("pageNum", pageNum);
		   request.setAttribute("nowPage", nowPage);
		   request.setAttribute("totalBlk", totalBlk);
		   request.setAttribute("startPage", startPage);
		   request.setAttribute("endPage", endPage);
		   request.setAttribute("totalPage", totalPage);
		   request.setAttribute("list", list);
		   request.setAttribute("list2", list2);
		   request.setAttribute("list3", list3);
		   request.setAttribute("total", total);
		   request.setAttribute("total2", total2);
		   request.setAttribute("aid", aid);
		   request.setAttribute("rbd", rbd);
		   request.setAttribute("aniNum", aniNum);
		   request.setAttribute("keyword", keyword);
		   request.setAttribute("search", search);
		return "list.jsp";
	}

}
