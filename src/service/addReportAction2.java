package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.report;
import dao.reportDao;

public class addReportAction2 implements CommandProcess {
public String requestPro(HttpServletRequest request, HttpServletResponse response) {
	String reportedId = request.getParameter("reportedId");
	String reporterId = request.getParameter("reporterId");	
	int reportReaNum = Integer.parseInt(request.getParameter("reportReaNum"));
	int reBoardNum = Integer.parseInt(request.getParameter("reBoardNum"));
	/*int boardNum = Integer.parseInt(request.getParameter("boardNum"));*/
	String content = request.getParameter("content");
	System.out.println(reBoardNum);
	
	report re = new report();
	re.setReportedId(reportedId);
	re.setReporterId(reporterId);
	re.setReportReaNum(reportReaNum);
	re.setContent(content);
	re.setReBoardNum(reBoardNum);
	reportDao rd = reportDao.getInstance();
	int result = rd.insert(re);
	

	request.setAttribute("reBoardNum", reBoardNum);
	/*request.setAttribute("boardNum", boardNum);*/
		return "reportAdd.jsp";
	}
}
