package review;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class cook extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PrintWriter pw =null;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.pw = response.getWriter();
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8"); 
		try {
			String[] data = {"A1316B1004", "C4024A0096", "B1987C3136"};
			String mid = request.getParameter("mid");
			String mnum = request.getParameter("mnum");
			String mad = request.getParameter("mad");
			
			request.setAttribute("mid", mid);
			request.setAttribute("mnum", mnum);
			request.setAttribute("mad", mad);
			if(mnum.equals(data[0]) || mnum.equals(data[1])||mnum.equals(data[2])) {
				
				RequestDispatcher rd = request.getRequestDispatcher("/review/co.jsp");
				rd.forward(request, response);
			}
			else {
				this.pw.write("<script>"
						+ "alert('해당 쿠폰번호를 확인하세요');"
						+ "history.go(-1)"
						+ "</script>");
			}
			
		}catch(Exception e) {
			System.out.println(e);
		}finally {
			this.pw.close();
		}
	}

}
