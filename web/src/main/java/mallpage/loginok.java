package mallpage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/mallpage/loginok.do")
public class loginok extends HttpServlet {
	private static final long serialVersionUID = 1L;
    PrintWriter pw = null;
    m_member mb = new m_member(); //DTO를 선언함 //select로 넘김
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		this.pw = response.getWriter();

		this.mb.setMid(request.getParameter("mid"));
		this.mb.setMpass(request.getParameter("mpass"));
		
		copyright cr = new copyright();
		
		//Controller => Model로 DTO로 값을 전송
		String result = cr.user_login(this.mb);
		
		//Model에서 DTO를 생성한 값을 Controller에서 받는 역할
		m_member mb2 = cr.mb; //Model에서 적용된 DTO 값을 받아서 Session
		
		//this.mb = new m_member();
		//System.out.println(this.mb.getMname());
		System.out.println(mb2.getMname());
		
		if(result == "ok") {
			//DTO를 활용하여 Session을 생성
			//HttpSession : Controller (loginok, logout)에서만 설정
			HttpSession session = request.getSession();
			session.setAttribute("mid", mb2.getMid());
			session.setAttribute("mname", mb2.getMname());
			session.setAttribute("memail", mb2.getMemail());
			
			//DTO를 활용하여 Session을 생성
			this.pw.print("<script>"
					+ "alert('로그인 하셨습니다');"
					+ "location.href='./index.do';"
					+ "</script>");
		}
		else {
			this.pw.print("<script>"
					+ "alert('아이디 및 패스워드를 확인하세요');"
					+ "history.go(-1);"
					+ "</script>");
		}
		this.pw.close();
	}

}
