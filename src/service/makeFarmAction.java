package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AniInfoDao;
import dao.AniOwnDao;
import dao.FarmInfo;
import dao.FarmInfoDao;

public class makeFarmAction implements CommandProcess{

	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		
		String id =  request.getParameter("id");
		String farmName =  request.getParameter("farmName");
		String farmHello =  request.getParameter("farmHello");
		String farmBGCol =  request.getParameter("farmBGCol");
		
		
		FarmInfoDao fid =FarmInfoDao.getInstance();
		FarmInfo fi = new FarmInfo();
		fi.setId(id);
		
		fi.setFarmName(farmName);
		fi.setFarmHello(farmHello);
		fi.setFarmBGCol(farmBGCol);
		int result =  fid.insert(fi);
		
		
		
		request.setAttribute("id", id);
		request.setAttribute("result", result);
		return "farm.do?id="+id;
	}

}