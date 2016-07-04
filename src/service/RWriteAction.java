package service;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Member;
import dao.MemberDao;
import dao.Point;
import dao.PointDao;
import dao.ReBoard;
import dao.ReBoardDao;

public class RWriteAction implements CommandProcess{

   @Override
   public String requestPro(HttpServletRequest request, HttpServletResponse response) {

      String id = request.getParameter("id");
      ReBoard rb = new ReBoard();
      MemberDao md = MemberDao.getInstance();
      Member mem = md.selectOne(id);
      int result = -5;
      String RpageNum="";
      int boardNum=0;
      int pageNum=0;
      if(mem.getWarnCount()<3){
      int rRef = 0, rRe_step = 0, rRe_level = 0;
		RpageNum = request.getParameter("RpageNum");
		if(request.getParameter("reBoardNum")!=null){/* 답변글일경우 */	
			int reBoardNum = Integer.parseInt(request.getParameter("reBoardNum"));
			ReBoardDao rbd = ReBoardDao.getInstance();
			rb = rbd.select(reBoardNum);
			rRef = rb.getRRef();
			rRe_step = rb.getRRe_step();					   
			rRe_level = rb.getRRe_level();
			
			 rb.setRRef(rRef);            
		     rb.setRRe_step(rRe_step);
		     rb.setRRe_level(rRe_level);
		}else{
			rb.setRRef(rRef);
			rb.setRRe_step(rRe_step);
			rb.setRRe_level(rRe_level);
		}
      boardNum = Integer.parseInt(request.getParameter("boardNum"));
      rb.setBoardNum(boardNum);      
      rb.setId(request.getParameter("id"));
      rb.setContent(request.getParameter("content"));
    
  	
      Timestamp time = new Timestamp(System.currentTimeMillis());
      rb.setRWriteDate(time);
      ReBoardDao rbd = ReBoardDao.getInstance();
      result = rbd.insert(rb);
            
      
      PointDao pd = PointDao.getInstance();
	   Point po = new Point();
	   po.setGetPoint(10);
	   po.setPointGetReason("댓글 작성");
	   po.setId(request.getParameter("id"));
	   pd.insert(po);
	   md = MemberDao.getInstance();
	   md.updatePoint(po);
      
      }
      request.setAttribute("RpageNum", RpageNum);
      request.setAttribute("boardNum", boardNum);
      request.setAttribute("pageNum", pageNum);
      request.setAttribute("result", result);
      
      return "Rwrite.jsp";
   }
}