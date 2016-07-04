package service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.reportReason;
import dao.reportReasonDao;

public class addReportAction implements CommandProcess {
public String requestPro(HttpServletRequest request, HttpServletResponse response) {
	String reportedId = request.getParameter("reportedId");
	String reporterId = request.getParameter("reporterId");	
	String content = request.getParameter("content");
	int reBoardNum = Integer.parseInt(request.getParameter("reBoardNum"));
	int boardNum = Integer.parseInt(request.getParameter("boardNum"));
	
	reportReasonDao rrd = reportReasonDao.getInstance();
	List<reportReason> list = rrd.selectAll();
	
	request.setAttribute("list", list);
	request.setAttribute("reportedId", reportedId);
	request.setAttribute("reporterId", reporterId);
	request.setAttribute("reBoardNum", reBoardNum);
	request.setAttribute("boardNum", boardNum);
	request.setAttribute("content", content);
		return "reportAdd.jsp";
	}
}
