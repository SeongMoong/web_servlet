package mallpage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/mallpage/joinok.do")
public class joinok extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	PrintWriter pw = null;   

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.pw = response.getWriter();
		String mid = request.getParameter("mid");
		String mpass = request.getParameter("mpass"); 
		String mname = request.getParameter("mname");
		String memail = request.getParameter("memail");
		String mtel = request.getParameter("mtel");
		//checkbox 값이 안들어가면 null값으로 들어감
		String event_email = request.getParameter("event_email");
		String event_sms = request.getParameter("event_sms");
		
		if(event_email==null) {
			event_email = "N";
		}
		if(event_sms==null) {
			event_sms = "N";
		}
		
		//DTO(setter 메소드에 Front-end가 전달한 값을 모두 이관함)
		m_member dto = new m_member();
		//dto.setMid(request.getParameter("mid")); 한번에 끝내는 방법
		dto.setMid(mid);
		dto.setMpass(mpass);
		dto.setMname(mname);
		dto.setMemail(memail);
		dto.setMtel(mtel);
		dto.setEvent_email(event_email);
		dto.setEvent_sms(event_sms);
		//DTO 값을 insert Model 전달함
		Integer result = new insert_join().insert_member(dto); //insert_join.java로 던진값
		if(result > 0) {
			this.pw.write("<script>"
					+ "alert('정상가입');"
					+ "location.href='./login.do';"
					+ "</script>");
		}
		else {
			this.pw.write("<script>"
					+ "alert('시스템점검으로 인하여 가입이 안되었습니다.');"
					+ "history.go(-1);"
					+ "</script>");
		}
		this.pw.close();
	}

}
