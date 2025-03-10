package admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class movieok extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		try {
			String muser = request.getParameter("muser");
			String mtel = request.getParameter("mtel");
			String mmovie = request.getParameter("mmovie");
			String mdate = request.getParameter("mdate");
			
			ArrayList<String>data = new ArrayList<String>();
			data.add(muser);
			data.add(mtel);
			data.add(mmovie);
			data.add(mdate);
			request.setAttribute("data", data);

			
			RequestDispatcher rd = request.getRequestDispatcher("./movie.jsp");
			rd.forward(request, response);
		}catch(Exception e) {
			System.out.println("오류");
		}
	}

}
