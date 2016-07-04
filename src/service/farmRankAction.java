package service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AniInfoDao;
import dao.AniOwn;
import dao.AniOwnDao;
import dao.FarmInfo;
import dao.FarmInfoDao;

public class farmRankAction implements CommandProcess {
public String requestPro(HttpServletRequest request, HttpServletResponse response) {
	
	FarmInfoDao fif = FarmInfoDao.getInstance();
	List<FarmInfo> list = fif.selectRList();
	
	request.setAttribute("list", list);
	request.setAttribute("fif", fif);
		return "farmRank.jsp";
	}
}
