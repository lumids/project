package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AniOwnDao;

public class itemUseAction2 implements CommandProcess{

	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		int itemDetailNum = Integer.parseInt(request.getParameter("itemDetailNum"));
		int itemNum = Integer.parseInt(request.getParameter("itemNum"));
		int ownNum =Integer.parseInt(request.getParameter("ownNum"));
		HttpSession session = request.getSession();
		String id =  (String) session.getAttribute("id");
		AniOwnDao aod = AniOwnDao.getInstance();
		int result = aod.updateStatI(ownNum, itemDetailNum, itemNum);
		
				
		request.setAttribute("itemDetailNum", itemDetailNum);
		request.setAttribute("itemNum", itemNum);
		request.setAttribute("ownNum", ownNum);
		request.setAttribute("result", result);
		request.setAttribute("id", id);
		return "itemUsePro.jsp?id="+id;
	}

}
