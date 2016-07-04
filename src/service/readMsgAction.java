package service;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Msg;
import dao.MsgDao;

public class readMsgAction implements CommandProcess {
public String requestPro(HttpServletRequest request, HttpServletResponse response) {
	
	int msgNum = Integer.parseInt(request.getParameter("msgNum"));
	String id = request.getParameter("id");
	
	MsgDao md = MsgDao.getInstance();
	Msg msg = md.selectOne(msgNum);
	
	Timestamp time = new Timestamp(System.currentTimeMillis());
	if(msg.getReceiveChk().equals("n ") && id.equals(msg.getReceiveId())){
		md.receiveUpdate(msgNum,time);
	}	
	request.setAttribute("msg", msg);
	request.setAttribute("id", id);
		return "readMsg.jsp";
	}
}
