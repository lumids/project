package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class renameAction implements CommandProcess{

	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		
		String aniNaming = request.getParameter("aniNaming");		
		int ownNum = Integer.parseInt(request.getParameter("ownNum"));
		
		request.setAttribute("aniNaming", aniNaming);
		request.setAttribute("ownNum", ownNum);
		return "rename.jsp";
	}

}
