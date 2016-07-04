package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Member;
import dao.MemberDao;

public class Login implements CommandProcess{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String id =  request.getParameter("id");
		Member m = new Member();	      
	     m.setId(id);
	     m.setPasswd(request.getParameter("passwd"));
	     MemberDao md = MemberDao.getInstance();
	     int result = md.loginChk(m);
	    
	    request.setAttribute("result", result);
	    session.setAttribute("id",id);
	    
		return "loginPro.jsp";
	}

}
