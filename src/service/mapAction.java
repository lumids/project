package service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Member;
import dao.MemberDao;

public class mapAction implements CommandProcess{

   @Override
   public String requestPro(HttpServletRequest request, HttpServletResponse response) {

	   MemberDao md = MemberDao.getInstance();
		List<Member> list = md.selectAll();
		
      request.setAttribute("list", list);     
      return "map.jsp";
   }
}