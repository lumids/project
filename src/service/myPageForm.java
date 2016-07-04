package service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.*;

public class myPageForm implements CommandProcess{

	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String id =  (String) session.getAttribute("id");
		
		String owner = request.getParameter("id");
		AniOwnDao aod = AniOwnDao.getInstance();
		List<AniOwn> list = aod.select(owner);
		AniInfoDao aid = AniInfoDao.getInstance();
		AniInfo ai = null;
		if (list != null) {
			for (AniOwn ao : list) {
				ai = aid.selectImg(ao.getAniNum());
			}
		}
		
		PointDao pd = PointDao.getInstance();
		List<Point> list2 = pd.select(id);
		
		MemberDao md = MemberDao.getInstance();
		Member m = md.selectOne(id);
		int warn = md.getWarn(id);
		
		ItemInfoDao iid = ItemInfoDao.getInstance();
		List<ItemInfo> list3 = iid.selectAll();
		ItemDetailDao idd = ItemDetailDao.getInstance();
		List<ItemDetail> list4 = idd.selectList(id);
		
		MapItemGetInfoDao migi = MapItemGetInfoDao.getInstance();
		List<MapItemGetInfo> list5 = migi.selectOne2(id);
		MapItemInfoDao mapItem = MapItemInfoDao.getInstance();
		
		
		
		request.setAttribute("id", id);
		request.setAttribute("owner", owner);
		request.setAttribute("aid", aid);
		request.setAttribute("idd", idd);
		request.setAttribute("iid", iid);
		request.setAttribute("owner", owner);
		request.setAttribute("list", list);
		request.setAttribute("list2", list2);
		request.setAttribute("list3", list3);
		request.setAttribute("list4", list4);
		request.setAttribute("list5", list5);
		request.setAttribute("mapItem", mapItem);
		request.setAttribute("ai", ai);
		request.setAttribute("m", m);
		request.setAttribute("warn", warn);	
		return "myPage.jsp?id="+id;
	}

}