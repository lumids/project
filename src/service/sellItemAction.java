package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MapItemGetInfoDao;
import dao.MapItemInfo;
import dao.MapItemInfoDao;
import dao.Member;
import dao.MemberDao;
import dao.PointDao;

public class sellItemAction implements CommandProcess{

	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		int mapItemNum = Integer.parseInt(request.getParameter("mapItemNum"));
		String id = request.getParameter("id");
		
		MapItemGetInfoDao migi = MapItemGetInfoDao.getInstance();
		int result = migi.updateGetChk(mapItemNum,id);
		
		MapItemInfoDao miid = MapItemInfoDao.getInstance();
		MapItemInfo mii = miid.selectOne2(mapItemNum);
		
		PointDao pd = PointDao.getInstance();
		dao.Point point = new  dao.Point();
		point.setGetPoint(mii.getMapItemPrice());
		point.setId(id);
		point.setPointGetReason("이벤트 아이템 판매");
		pd.insert(point);

		MemberDao md = MemberDao.getInstance();
		md.updatePoint(point);
		
		request.setAttribute("result", result);
		return "myPage.do?id="+id;
	}

}
