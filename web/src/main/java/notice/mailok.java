package notice;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.m_dbinfo;

public class mailok extends HttpServlet {
	private static final long serialVersionUID = 1L;
    Connection con = null;
    PreparedStatement ps =null;
    PrintWriter pw = null;
    
    m_dbinfo db = new m_dbinfo();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.pw = response.getWriter();
		
		try {
			this.con = this.db.getConnection();
			String to_name = request.getParameter("to_name"); //성함
			String to_mail = request.getParameter("to_mail"); //메일주소
			String subject = request.getParameter("subject"); //제목
			String context = request.getParameter("context"); //내용
			
			String sql ="";
			int result = 0;
			
			sql = "insert into work (nidx,to_name,to_mail,subject,context,ndate)"
					+ "values ('0',?,?,?,?,now())";
			this.ps = this.con.prepareStatement(sql);
			this.ps.setString(1, to_name);
			this.ps.setString(2, to_mail);
			this.ps.setString(3, subject);
			this.ps.setString(4, context);
			
			if(result > 0) {
				this.pw.write("<script>"
						+ "alert('메일이 전송되었습니다');"
						+ "location.href='./mailok2.do';"
						+ "</script>");
			}
			else {
				this.pw.write("<script>"
						+ "alert('오류 발생이요');"
						+ "history.go(-1);"
						+ "</script>");
			}
			
		}catch(Exception e){
			System.out.println(e);
			this.pw.write("<script>"
					+ "alert('db오류요');"
					+ "history.go(-1);"
					+ "</script>");
		}finally {
			try {
				this.ps.close();
				this.con.close();
				this.pw.close();
			}catch(Exception e) {

			}
		}
	}

}
