package service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Msg;
import dao.MsgDao;

public class msgAction implements CommandProcess {
public String requestPro(HttpServletRequest request, HttpServletResponse response) {
	
	String id = request.getParameter("id");
	
	MsgDao md = MsgDao.getInstance();
	List<Msg> list = md.sendSelect(id);
	List<Msg> list2 = md.receiveSelect(id);
	int total = md.sendTotal(id);
	int total2 = md.resTotal(id);
	
	request.setAttribute("list", list);
	request.setAttribute("list2", list2);
	request.setAttribute("id", id);
	request.setAttribute("total", total);
	request.setAttribute("total2", total2);
		return "msg.jsp";
	}
}
