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
import dao.report;
import dao.reportDao;
import dao.reportReasonDao;

public class processRepAction2 implements CommandProcess{

   @Override
   public String requestPro(HttpServletRequest request, HttpServletResponse response) {
	   String id = request.getParameter("id");
	   int reportReaNum = Integer.parseInt(request.getParameter("reportReaNum"));
	   String chk = request.getParameter("chk");
	   int reBoardNum = Integer.parseInt(request.getParameter("reBoardNum"));
		
	   ReBoardDao rbd = null;
	   if(chk.equals("y")){
		    rbd = ReBoardDao.getInstance();
		   rbd.updateWarn(reBoardNum);
	   }
		MemberDao md = MemberDao.getInstance();
		md.updateWarn(id);
		reportDao rd = reportDao.getInstance();
		rd.updateChk(reportReaNum);
		
			return "processRep.jsp";
   }
}