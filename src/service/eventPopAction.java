package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MapInfo;
import dao.MapInfoDao;
import dao.MapItemGetInfo;
import dao.MapItemGetInfoDao;
import dao.MapItemInfo;
import dao.MapItemInfoDao;

public class eventPopAction implements CommandProcess {
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");

		int mapItemNum = Integer.parseInt(request.getParameter("mapItemNum"));

		int result = -1;
		MapItemInfoDao miid = MapItemInfoDao.getInstance();
		MapItemGetInfoDao migi = MapItemGetInfoDao.getInstance();
		MapItemGetInfo mig = migi.playChk(id, mapItemNum);
		MapItemInfo mi = null;
		MapItemInfo mi2 = null;
		MapInfo info = null;
		float x = (float) (Math.random() * 1) / 1000;
		float y = (float) (Math.random() * 1) / 1000;
		if (mig.getMapItemGetChk() == null || !mig.getMapItemGetChk().equals("n ")||
				!mig.getMapItemGetChk().equals("y ")) {
			mi2 = miid.selectOne2(mapItemNum);

			MapInfoDao mid = MapInfoDao.getInstance();
			info = mid.selectOne(id);
			result = 0;

		}
		request.setAttribute("result", result);
		request.setAttribute("mi2", mi2);
		request.setAttribute("x", x);
		request.setAttribute("y", y);
		request.setAttribute("info", info);

		return "eventPop.jsp";
	}
}
