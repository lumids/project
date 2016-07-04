package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class msgPopAction implements CommandProcess {
public String requestPro(HttpServletRequest request, HttpServletResponse response) {
	String sendId = request.getParameter("sendId");
	String receiveId = request.getParameter("receiveId");
	
	request.setAttribute("sendId", sendId);
	request.setAttribute("receiveId", receiveId);
		return "msgPop.jsp";
	}
}
