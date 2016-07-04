package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ItemDetail;
import dao.ItemDetailDao;
import dao.Member;
import dao.MemberDao;
import dao.Point;
import dao.PointDao;

public class Buy2 implements CommandProcess{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		String id = request.getParameter("id");
		int itemPrice = Integer.parseInt(request.getParameter("itemPrice"));
		MemberDao md = MemberDao.getInstance();
		Member m = md.selectOne(id);
		int result = 55;
		if(m.getPointSum()>=itemPrice){
			ItemDetail iid = new ItemDetail();
		      iid.setId(request.getParameter("id"));
		      iid.setItemNum(Integer.parseInt(request.getParameter("itemNum")));
		      ItemDetailDao idd = ItemDetailDao.getInstance();
		      result = idd.insert(iid);
		      
		    
		      PointDao pd = PointDao.getInstance();
		      Point point = new Point();
		      point.setId(id);
			  point.setGetPoint(-itemPrice);
			  point.setPointGetReason("아이템 구매");
		      
			  pd.insert(point);
		    md.updatePoint(point);
		}		
	      
	    request.setAttribute("result", result);
		return "shopItemPro.jsp?point="+m.getPointSum();
	}

}
