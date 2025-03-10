package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class pollok extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//method="get"
	//PrintWriter => Controller에서 종료 (결과값에 대해서 처리)
	//RequestDispatcher => Controller => view (jsp) 결과를 처리
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); //Front-end 한글깨짐
		//response.setCharacterEncoding("utf-8");
		//response.setContentType("text/html");
		response.setContentType("text/html;charset=utf-8");
		
		//동일한 name => radio : 여러개 오브젝트 중에 한가지만 선택
		//getParameter : get,post (name전달됨 갑 동일함)
		// request.getParameter : 무조건 Front-end에서 값이 이관 되어야 함(값이 없으면 catch로 바로 넘어감)
		String subject = request.getParameter("subject");
		String etc[] = request.getParameterValues("etc"); //같은이름 name을 가진 checkbox
		//ArrayList<String> etc = new ArrayList<String>(Arrays.asList(request.getParameterValues("etc")));
		PrintWriter pw = response.getWriter();
		try {
			/*
			int w = 0;
			while(w< etc.length) {
				request.setAttribute("etc"+w, etc[w]);
				w++;
			}
			*/ //java에서 반복문을 사용하지 않고 jsp에서 사용해야함 값을 보내는데 어려움? 불가능?
			//System.out.println(Arrays.toString(etc));
			System.out.println(etc);
			request.setAttribute("subject", subject); //view에 출력 메소드
			request.setAttribute("etc", etc); //원시배열
			//request.setAttribute("abc", null);
			//request.setAttribute("abc", null);
			RequestDispatcher rd = request.getRequestDispatcher("./pollok.jsp");
			rd.forward(request, response);
			
		}
		catch(Exception e) {
			pw.write("<script>"
					+"alert('아이디 및 패스워드를 다시 확인하세요');"
					+ "</script>");
		}finally {
			pw.close();
		}
	}

}
