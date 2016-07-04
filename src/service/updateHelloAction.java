package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.FarmInfoDao;

public class updateHelloAction implements CommandProcess{

	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		
		String id = request.getParameter("id");
		String farmHello = request.getParameter("farmHello");

		FarmInfoDao fif = FarmInfoDao.getInstance();
		fif.updateHello(id,farmHello);
		return "farm.do?id="+id;
	}

}
