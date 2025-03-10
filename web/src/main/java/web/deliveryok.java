package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class deliveryok extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	PrintWriter pw = null;
	//HttpServletRequest request;
	//HttpServletResponse response;
	/*// 즉시실행 완성된 코드는 아님 더배우면 복잡해져서 이정도 있다는 정도
	 * public deliveryok() {
		try {
			this.request.setCharacterEncoding("utf-8");
			this.response.setContentType("text/html;charset=utf-8");
			this.pw = this.response.getWriter();
		}catch(Exception e) {
				}
	}*/
	//즉시실행 메소드(만들때 Servlet으로 만듬 => class랑 다름) : 일반 class에서 사용하는 형태와는 구조 다릅니다. 
    public void deliveryok(HttpServletRequest request, HttpServletResponse response) { //action에서 해당 class 실행하면 즉시 메소드
    	try {
    		request.setCharacterEncoding("utf-8"); 
    		response.setContentType("text/html;charset=utf-8");
    		this.pw = response.getWriter();
    		/*
    		 Servlet에서 request.setCharacterEncoding("utf-8");을 사용하고싶으면
    		 public void deliveryok(HttpServletRequest request, HttpServletResponse response)
    		 로 사용해야함. 즉시실행안(public deliveryok())에 HttpServletReqest사용을 못함
    		*/
    	}catch(Exception e) {
    		System.out.println("해당 URL 경로를 올바르지 않습니다.");
    	}    	
}


    //매개변수명을 변경하여 request 또는 response를 다른 이름으로 변경 가능함
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 this.deliveryok(request,response);//void
		 String mname = request.getParameter("mname");
		 String mtel = request.getParameter("mtel");
		 String memail = request.getParameter("memail");
		 String maddress = request.getParameter("maddress");
		 //클래스 배엻하는 이유는 setAttribute 너무 많이 생성하여 jsp로 전달시 getAttreibute 많아짐
		 ArrayList<String>data = new ArrayList<String>();
		 data.add(mname);
		 data.add(mtel);
		 data.add(memail);
		 data.add(maddress);
		 
		 //클래스 배열을 jsp로 전달
		 request.setAttribute("data", data);
		 
		 RequestDispatcher rd = request.getRequestDispatcher("./deliveryok.jsp");
		 rd.forward(request, response);
		 
	}

}
