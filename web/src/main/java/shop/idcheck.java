package shop;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.xdevapi.Result;

public class idcheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con = null;
	
	//ajax 통신을 받는 메소드 (아이디 중복체크)
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String msg = ""; //Front-end에게 결과값을 보내는 변수명
		//Back-end가 Front-end에게 결과값을 통보하는 역할
		PrintWriter pw = response.getWriter();
		//Ajax로 Front-end가 보낸 데이터를 받는 역할
		try {
			String id = request.getParameter("sid");
			if(id.equals("")) {
				msg = "error";
			}else {
				m_dbinfo db = new m_dbinfo();
				this.con = db.getConnection(); //Database 연결시작
				/*
				 sql quey문 작성 방법
				 1. select => ResultSet, executeQuery
				 2. insert, update,delete => int, executeUpdate
				 */
				//select sid from shop_member where sid = '변수명';
				String sql = "select count(1) as ctn from shop_member where sid='"+ id+ "'";
				//Statement : Database에 쿼리문을 작성할 수 있도록 사용하는 메소드
				//createStatement : create, alter, drop 요즘엔 보안때문에 안사용하긴함
				Statement st = this.con.createStatement(); //두문장다 필드로 빼고 finally에 close를 하는 것이 정석
				ResultSet rs = st.executeQuery(sql); //ResultSet = executeQuery 결과값을 받는 역할 (select)
				
				//반복문을 사용하지 않았을 경우
				if(rs.next() == true) {//정상적으로 Query문이 작동 했을 경우
					if(rs.getString("ctn").equals("0")) { //해당 데이터가 없을 때
						msg = "ok";
					}else {//검색한 데이터가 있을 경우
						msg = "no";
					}
				}
				rs.close();
				st.close();
			}	
				/*//반복문 사용했을 경우
				while(rs.next()) { //rs.next() : 결과값에 대해서 반복문이 실행(true, false)
					System.out.println(rs.getString("ctn"));	
				} //필수적으로 while문 사용해야함
				*/
			pw.write(msg);
		}catch(NullPointerException ne) { //Front-end가 올바른 값을 전달하지 않을 경우
			msg="error";
			pw.write(msg); //ok : 사용가능 아이디, no : 사용불능 아이디, error: 데이터 오류
		}catch (Exception e) {
			msg = "DBS error";
			pw.write(msg);
		}finally {
			pw.close();	
		}
	}
}
