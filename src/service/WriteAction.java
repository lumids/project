package service;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Board;
import dao.BoardDao;
import dao.MemberDao;
import dao.Point;
import dao.PointDao;

public class WriteAction implements CommandProcess{

   @Override
   public String requestPro(HttpServletRequest request, HttpServletResponse response) {

      
      Board board = new Board();
      
      board.setBoardNum(Integer.parseInt(request.getParameter("boardNum")));
      board.setId(request.getParameter("id"));
      board.setAniNum(Integer.parseInt(request.getParameter("aniNum")));
      board.setSubject(request.getParameter("subject"));
      board.setContent(request.getParameter("content"));
      board.setRef(Integer.parseInt(request.getParameter("ref")));            
      board.setRe_step(Integer.parseInt(request.getParameter("re_step")));
      board.setRe_level(Integer.parseInt(request.getParameter("re_level")));
      if(request.getParameter("noticeChk")==null){
    	  board.setNoticeChk("n");
      }else{
    	  board.setNoticeChk(request.getParameter("noticeChk"));
      }
      Timestamp time = new Timestamp(System.currentTimeMillis());
      board.setWriteDate(time);
      String pageNum = request.getParameter("pageNum");
      BoardDao bd = BoardDao.getInstance();
      int result = bd.insert(board);
      
      PointDao pd = PointDao.getInstance();
	   Point po = new Point();
	   po.setGetPoint(50);
	   po.setPointGetReason("±€ ¿€º∫");
	   po.setId(request.getParameter("id"));
	   pd.insert(po);
	   MemberDao md = MemberDao.getInstance();
	   md.updatePoint(po);

      request.setAttribute("pageNum", pageNum);
      request.setAttribute("result", result);
      
      return "write.jsp";
   }
}