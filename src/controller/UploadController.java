package controller;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AniInfo;
import dao.ItemInfo;
import service.InsertPds;
import service.SelectList;
@WebServlet("*.action")
public class UploadController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String uri  = request.getRequestURI();
		String path = request.getContextPath();
		/*System.out.println("uri : "+uri); //ÀüÃ¼
		System.out.println("path : "+path); */
		
		//uri : /ch15/pds/UploadController.action
		//path : /ch15
		String command = uri.substring(path.length()+1);
		if (command.equals("pds/upload.action")) {
			InsertPds ipd = new InsertPds();
			int result = ipd.insert(request);
			if (result > 0 ) response.sendRedirect("success.jsp");
			else response.sendRedirect("insertForm.jsp");
		} else if(command.equals("pds/list.action")){
			SelectList sl = new SelectList();
			List<AniInfo> list = sl.selectAll();
			request.setAttribute("list", list);
			RequestDispatcher rd = request.getRequestDispatcher("list.jsp");
			rd.forward(request, response);
		}else if (command.equals("pds/upload2.action")) {
			InsertPds ipd = new InsertPds();
			int result = ipd.insert2(request);
			if (result > 0 ) response.sendRedirect("success2.jsp");
			else response.sendRedirect("insertItemForm.jsp");
		} else if(command.equals("pds/list2.action")){
			SelectList sl = new SelectList();
			List<ItemInfo> list = sl.selectAll2();
			request.setAttribute("list", list);
			RequestDispatcher rd = request.getRequestDispatcher("list2.jsp");
			rd.forward(request, response);
		}else if (command.equals("pds/upload3.action")) {
			InsertPds ipd = new InsertPds();
			int result = ipd.insert3(request);
			if (result > 0 ) response.sendRedirect("success3.jsp");
			else response.sendRedirect("openEventForm.jsp");
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}
}