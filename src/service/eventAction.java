package service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MapItemInfo;
import dao.MapItemInfoDao;

public class eventAction implements CommandProcess {
public String requestPro(HttpServletRequest request, HttpServletResponse response) {

	MapItemInfoDao miid = MapItemInfoDao.getInstance();
	List<MapItemInfo> list = miid.selectList();
	int total =  miid.selectTotal();
	
	
	request.setAttribute("list", list);
	request.setAttribute("total", total);
		return "event.jsp";
	}
}
