package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Msg;
import dao.MsgDao;

public class delMsgAction implements CommandProcess {
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {

		String[] msgNum = request.getParameterValues("msgNum");
		String[] array;
		String delChk = request.getParameter("delChk");
		MsgDao md = MsgDao.getInstance();
		Msg m = null;
		for (String a : msgNum) {
			array = a.split(",");
			for (String b : array) {
				int msgNum1 = Integer.parseInt(b);
				int result = md.delMsg(msgNum1, delChk);

			}

		}
		return "msg.jsp";
	}
}
