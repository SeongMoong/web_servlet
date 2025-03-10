package event;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class event_infook extends HttpServlet {
	private static final long serialVersionUID = 1L;
    Connection con = null;
    PrintWriter pw = null;
    Statement st = null;
    dbconfig db = new dbconfig();
    public event_infook() {
    	try {
    		//this.con = new dbconfig().info(); //한줄
    		//System.out.println("Database 연결성공");
    		//dbconfig db = new dbconfig(); //두줄
    		//this.con = db.info();
    	}catch(Exception e) {
    		System.out.println("Database 연결실패");
    	}
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String ename = request.getParameter("ename");
		String etel = request.getParameter("etel");
		String email = request.getParameter("email");
		String ememo = request.getParameter("ememo");
		String info1 = request.getParameter("info1");
		String info2 = request.getParameter("info2");
		
		
		//SQL Query문 (DDL) - select, insert, update, delete
		String sql = "insert into event (eidx,ename,etel,email,info1,info2,ememo,ejoin)"
				+ "values('0',+'"+ename+"','"+etel+"','"+email+"','"+info1+"','"+info2+"','"+ememo+"',now())";
		this.pw = response.getWriter();
		try {
			//SQL 문법을 실행시키는 클래스(기초)
			this.con = this.db.info();
			this.st = this.con.createStatement();
			int result = this.st.executeUpdate(sql); //executeUpdate (insert,update,delete)
			if(result > 0) {
				this.pw.write("<script>"
						+ "alert('정상적으로 이벤트에 참여 되었습니다.');"
						+ "location.href='./event_info.html';"
						+ "</script>");
			}
			else {
				this.pw.write("<script>"
						+ "alert('비정상적으로 이벤트에 참여 되었습니다.');"
						+ "location.href='./event_info.html';"
						+ "</script>");
			}

		}catch(Exception e) {
			System.out.println("SQL문법 오류!!");
			System.out.println(e);

		}finally { //정상적인 Database 모두 핸들링 후 역순으로 close를 순차적으로 적용해야함
			try {
				this.pw.close();
				this.st.close();
				this.con.close();			//Database 연결끊기	
	
			}catch(Exception e) {
				System.out.println("Database가 올바르게 종료되지않았습니다.");
			}			
		}
	}

}
