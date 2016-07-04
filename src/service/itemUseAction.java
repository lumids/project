package service;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.*;

public class itemUseAction implements CommandProcess{

	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		int itemDetailNum = Integer.parseInt(request.getParameter("itemDetailNum"));
		int itemNum = Integer.parseInt(request.getParameter("itemNum"));
		String id = request.getParameter("id");
		AniOwnDao aod = AniOwnDao.getInstance();
		List<AniOwn> list = aod.selectOwnList(id);
		AniInfoDao aid = AniInfoDao.getInstance();
		List<AniInfo> ai = aid.selectAll("all");		
		
		
		request.setAttribute("ai", ai);
		request.setAttribute("id", id);
		request.setAttribute("aid", aid);
		request.setAttribute("aod", aod);
		request.setAttribute("list", list);
		request.setAttribute("itemDetailNum", itemDetailNum);
		request.setAttribute("itemNum", itemNum);
		return "itemUse.jsp?itemDetailNum="+itemDetailNum;
	}

}
