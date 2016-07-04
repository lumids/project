package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AniOwn;
import dao.AniOwnDao;
import dao.MapInfo;
import dao.MapInfoDao;
import dao.Member;
import dao.MemberDao;
import dao.Point;
import dao.PointDao;

public class Join implements CommandProcess{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 Member m = new Member();	      
	      m.setId(request.getParameter("id"));
	      m.setPasswd(request.getParameter("passwd"));
	      m.setNickname(request.getParameter("nickname"));
	      MemberDao md = MemberDao.getInstance();
	      int result = md.insert(m);
	      
	      
	      AniOwn ao = new AniOwn();
	      ao.setId(request.getParameter("id"));
	      ao.setAniNum(Integer.parseInt(request.getParameter("aniNum")));
	      ao.setAniNaming(request.getParameter("aniNaming"));
	      AniOwnDao aod = AniOwnDao.getInstance();
	      int result2 = aod.insert(ao,"y");
	      
	      MapInfo mi = new MapInfo();
	      mi.setId(request.getParameter("id"));
	      mi.setX(Float.parseFloat(request.getParameter("x")));
	      mi.setY(Float.parseFloat(request.getParameter("y")));
	      MapInfoDao mid = MapInfoDao.getInstance();
	      int result3 = mid.insert(mi);
	      
	      Point po = new Point();
	      po.setId(request.getParameter("id"));
	      po.setPointGetReason("회원 가입");
	      po.setGetPoint(500);
	      PointDao pd = PointDao.getInstance();
	      int result4 = pd.insert(po);
	      md.updatePoint(po);
	      
	      
	      
	      request.setAttribute("result", result);
	      request.setAttribute("result2", result2);
	      request.setAttribute("result3", result3);
	      request.setAttribute("result4", result4);
		return "joinPro.jsp";
	}

}
