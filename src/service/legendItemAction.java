package service;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.*;

public class legendItemAction implements CommandProcess{

	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		int mapItemNum = Integer.parseInt(request.getParameter("mapItemNum"));
		String id = request.getParameter("id");
		
		MapItemInfoDao miid = MapItemInfoDao.getInstance();
		MapItemInfo item = miid.selectOne2(mapItemNum);
		
		
		request.setAttribute("item", item);
		request.setAttribute("id", id);
		request.setAttribute("miid", miid);
		return "legendItem.jsp?mapItemNum="+mapItemNum+"&id="+id;
	}

}
