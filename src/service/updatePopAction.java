package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.FarmInfoDao;

public class updatePopAction implements CommandProcess{

	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
	
		String id = request.getParameter("id");
		String pop = "pop";
		FarmInfoDao fif = FarmInfoDao.getInstance();
		fif.updatePop(id);
		
		return "farm.do?id="+id+"pop="+pop;
	}

}
