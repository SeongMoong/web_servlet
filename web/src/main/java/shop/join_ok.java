package shop;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class join_ok extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con = null; //Database연결 (SQL Qery문을 실행시키기 위함)
	//service(doGet + doPost 두개 다 받음) => 보안이 약하다는 문제점이 있음(테스트용도로 사용)
	/*
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("POST,GET 모두 받는");

		
	}
	*/
	/*//기본 구조(문법)
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		m_dbinfo db = new m_dbinfo();
		try {
			this.con = db.getConnection();
		}catch(Exception e) {
			System.out.println("DB연결 실패");
		}finally {
			try {
				this.con.close();
				
			}catch(Exception e){
				System.out.println("DB가 올바르게 해제 되지 않았습니다.");
			}
		}
	}
	*/
	PrintWriter pw = null;
	PreparedStatement ps = null;
	//회원가입 진행
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.pw = response.getWriter();
		m_dbinfo db = new m_dbinfo();
		try {
			this.con = db.getConnection();
			String spart = request.getParameter("spart");
			String sid = request.getParameter("sid");
			String spw = request.getParameter("spw");
			String snm = request.getParameter("snm");
			String stel = request.getParameter("stel");
			String semail = request.getParameter("semail");
			
			spw = new m_md5().md5_code(spw); //문자 패스워드를 MD5 암호화
			String sql = ""; //Database Query문
			if(spart.equals("P")) { //개인회원
				sql = "insert into shop_member values ('0',?,?,?,?,?,?,null,now())";
				this.ps = this.con.prepareStatement(sql);
				this.ps.setString(1, spart);
				this.ps.setString(2, sid);
				this.ps.setString(3, spw);
				this.ps.setString(4, snm);
				this.ps.setString(5, stel);
				this.ps.setString(6, semail);
				int result = this.ps.executeUpdate();
				if(result > 0) {
					this.pw.write("<script>"
							+ "alert('회원가입이 정상적으로 완료 되었습니다.');"
							+ "location.href='./login.jsp';"
							+ "</script>");
				}
				//System.out.println(spw); //암호화된 spw
			}else { //사업자 회원
				String sno = request.getParameter("sno");
				sql = "insert into shop_member values ('0',?,?,?,?,?,?,?,now())";
				this.ps = this.con.prepareStatement(sql);
				this.ps.setString(1, spart);
				this.ps.setString(2, sid);
				this.ps.setString(3, spw);
				this.ps.setString(4, snm);
				this.ps.setString(5, stel);
				this.ps.setString(6, semail);
				this.ps.setString(7, sno);
				int result = this.ps.executeUpdate();
				
				if(result > 0) {
					this.pw.write("<script>"
							+ "alert('사업자 회원가입이 정상적으로 완료 되었습니다.');"
							+ "location.href='./login.html';"
							+ "</script>");
				}
			}
		}
		catch(java.sql.SQLIntegrityConstraintViolationException ie) { //DB에 unique가 중복값이 발생했을 때
			this.pw.write("<script>"
					+ "alert('연락처 및 이메일이 중복 되어서 가입이 취소 되었습니다.');"
					+ "hisotry.go(-1);"
					+ "</script>");
		}
		catch(Exception e) {
			
		}
		finally {
			try {
				this.ps.close();
				this.con.close();
				this.pw.close();				
			}catch(Exception e) {
				System.out.println(e);
			}
		}
	}
	

}
