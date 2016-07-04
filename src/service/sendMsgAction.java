package service;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Msg;
import dao.MsgDao;

public class sendMsgAction implements CommandProcess {
public String requestPro(HttpServletRequest request, HttpServletResponse response) {
	String sendId = request.getParameter("sendId");
	String receiveId = request.getParameter("receiveId");
	String msgSubject = request.getParameter("msgSubject");
	String msgContent = request.getParameter("msgContent");
	
	Timestamp sendTime = new Timestamp(System.currentTimeMillis());
	
	Msg msg = new Msg();
	msg.setSendId(sendId);
	msg.setReceiveId(receiveId);
	msg.setMsgSubject(msgSubject);
	msg.setMsgContent(msgContent);
	msg.setSendTime(sendTime);
	
	MsgDao md = MsgDao.getInstance();
	md.insert(msg);
	
		return "msgPop.jsp";
	}
}
