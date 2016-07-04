package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AniOwn;
import dao.AniOwnDao;
import dao.Member;
import dao.MemberDao;
import dao.Point;
import dao.PointDao;

public class Buy implements CommandProcess{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String id = request.getParameter("id");
		int aniPrice = Integer.parseInt(request.getParameter("aniPrice"));
		MemberDao md = MemberDao.getInstance();
		Member m = md.selectOne(id);
		int result = 55;
		
		if(m.getPointSum()>=aniPrice){
		AniOwn ao = new AniOwn();
	      ao.setId(request.getParameter("id"));
	      ao.setAniNum(Integer.parseInt(request.getParameter("aniNum")));
	      ao.setAniNaming(request.getParameter("aniNaming"));
	      AniOwnDao aod = AniOwnDao.getInstance();
	      result = aod.insert(ao,"n");
	      
	      PointDao pd = PointDao.getInstance();
	      Point point = new Point();
	      point.setId(id);
		  point.setGetPoint(-aniPrice);
		  point.setPointGetReason("포켓몬 구매");
	      
		  pd.insert(point);
	    md.updatePoint(point);
		} 
	      request.setAttribute("result", result);
		return "shopPro.jsp?point="+m.getPointSum();
	}

}
