package service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.report;
import dao.reportDao;
import dao.reportReason;
import dao.reportReasonDao;

public class myPageMForm implements CommandProcess{

	public String requestPro(HttpServletRequest request, HttpServletResponse response) {		
		
		reportReasonDao rrd = reportReasonDao.getInstance();
		List<reportReason> list = rrd.selectAll();
		
		reportDao rd = reportDao.getInstance();
		List<report> list2 = rd.selectAll();
		
		request.setAttribute("list", list);
		request.setAttribute("list2", list2);
		request.setAttribute("rrd", rrd);
		return "myPageM.jsp";
	}

}