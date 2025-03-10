package review;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class noticeok extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    //HttpServletRequest : 데이터 전달 통신 규격 interface
	//HttpServletResponse : 결과에 대한 응답 통신 규격 interface
	PrintWriter pw = null;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		try {
			this.pw = response.getWriter();
			String se = request.getParameter("se");
			if(se.equals("korea.com")) {
				String subject = request.getParameter("subject");
				String writer = request.getParameter("writer");
				String wtext = request.getParameter("wtext");
				
				request.setAttribute("subject", subject);
				request.setAttribute("writer", writer);
				request.setAttribute("wtext", wtext);
				
				RequestDispatcher rd = request.getRequestDispatcher("/review/noticeview.jsp");
				rd.forward(request, response);
				
			}else {
				this.pw.write("<script>"
						+ "alert('올바른접근이 아님');"
						+ "history.go(-1);"
						+ "</script");
			}
		}catch(Exception e) {
			
		}finally {
			
		}
	}

}
