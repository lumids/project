package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AniInfoDao;
import dao.AniOwnDao;
import dao.FarmInfo;
import dao.FarmInfoDao;

public class myFarmAction implements CommandProcess{

	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String id =  (String) session.getAttribute("id");
		
		
		FarmInfoDao fid =FarmInfoDao.getInstance();
		FarmInfo fi = fid.selectOne(id);
		
		int result = 1;
		if(fi.getFarmNum()<1){
			result = -1;
		}
		
		request.setAttribute("id", id);
		request.setAttribute("result", result);
		return "farmPro.jsp?id="+id;
	}

}