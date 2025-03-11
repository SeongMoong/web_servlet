package ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ajax_postok extends HttpServlet {

	private static final long serialVersionUID = 1L;
       PrintWriter pw = null;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Ajax 통신 필수 코드 (CORS 해결방법)
		/*
		 CORS 를 해결하기 위해서 사용하는 방식의 코드이며, 해당 Origin, Credentials
		 를 사용하여 도메인이 다르게 접근하더라도 Ajax 통신이 되도록 허락을 하는 명령어
		 "*" 
		 */
		
		response.addHeader("Access-Control-Allow-Origin","*"); //* 모든ip 도메인을 다받겠다~
		response.addHeader("Access-Control-Allow-Credentials","true");
		
		String userid = request.getParameter("userid");
		String useremail = request.getParameter("useremail");
		System.out.println(userid);
		System.out.println(useremail);
		String msg=""; //ok : 정상 아이디, no : 아이디 오류, error : Back-end오류
		
		this.pw = response.getWriter();
		
		if(userid.equals("hong") && useremail.equals("hong@nate.com")) {
			msg = "no";
		}
		else {
			msg = "ok";
		}
		this.pw.print(msg);	//Front-end에게 보내는 값
	}

}
