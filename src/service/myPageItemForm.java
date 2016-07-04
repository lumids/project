package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AniInfo;
import dao.AniInfoDao;
import dao.AniOwn;
import dao.AniOwnDao;
import dao.ItemDetail;
import dao.ItemDetailDao;
import dao.ItemInfo;

public class myPageItemForm implements CommandProcess{

	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		int itemNum = Integer.parseInt(request.getParameter("itemNum"));
		int itemDetailNum = Integer.parseInt(request.getParameter("itemDetailNum"));
		ItemDetailDao idd = ItemDetailDao.getInstance();
		ItemInfo iif = idd.selectOne(itemNum);
		
		
		request.setAttribute("iif", iif);
		request.setAttribute("itemDetailNum", itemDetailNum);
		request.setAttribute("itemNum", itemNum);
		return "myPageItem.jsp?itemNum="+itemNum;
	}

}
