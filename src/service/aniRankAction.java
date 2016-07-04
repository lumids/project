package service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AniInfoDao;
import dao.AniOwn;
import dao.AniOwnDao;

public class aniRankAction implements CommandProcess {
public String requestPro(HttpServletRequest request, HttpServletResponse response) {
	
	AniOwnDao aod = AniOwnDao.getInstance();
	AniInfoDao aid = AniInfoDao.getInstance();
	List<AniOwn> list = aod.selectRList();
	
	request.setAttribute("list", list);
	request.setAttribute("aod", aod);
	request.setAttribute("aid", aid);
		return "aniRank.jsp";
	}
}
