package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.reportReasonDao;

public class addReasonAction implements CommandProcess {
public String requestPro(HttpServletRequest request, HttpServletResponse response) {
	String reportReaVal = request.getParameter("reportReaVal");	
	reportReasonDao rrd = reportReasonDao.getInstance();
	int result =  rrd.insert(reportReaVal);
	
	request.setAttribute("result", result);
		return "myPageM.jsp";
	}
}
