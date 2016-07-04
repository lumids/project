package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDao;
import dao.reportReasonDao;

public class processRepAction implements CommandProcess{

   @Override
   public String requestPro(HttpServletRequest request, HttpServletResponse response) {
	   String reportedId = request.getParameter("reportedId"); 
		String reporterId = request.getParameter("reporterId");
		int reportReaNum = Integer.parseInt(request.getParameter("reportReaNum"));
		int reBoardNum = Integer.parseInt(request.getParameter("reBoardNum"));
		String content = request.getParameter("content");	
		reportReasonDao rrd = reportReasonDao.getInstance();
		MemberDao md = MemberDao.getInstance();
		
		//System.out.println("reBaordNum : "+reBoardNum);
		
		request.setAttribute("reportedId", reportedId);
		request.setAttribute("reporterId", reporterId);
		request.setAttribute("reportReaNum", reportReaNum);
		request.setAttribute("reBoardNum", reBoardNum);
		request.setAttribute("content", content);
		request.setAttribute("rrd", rrd);
		request.setAttribute("md", md);
			return "processRep.jsp";
   }
}