package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AniOwnDao;

public class pickAniAction implements CommandProcess{

   @Override
   public String requestPro(HttpServletRequest request, HttpServletResponse response) {

      String id = request.getParameter("id");
      int ownNum = Integer.parseInt(request.getParameter("ownNum"));
      
      AniOwnDao aod = AniOwnDao.getInstance();
      String owner = aod.selectId(ownNum);
     
      int result = -1;
      if(!owner.equals(id)){
    	  result = aod.updateAniOwn(id,ownNum);
      }
      request.setAttribute("result", result);
      request.setAttribute("id", id);
      return "aniOwnChk.jsp";
   }
}