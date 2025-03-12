package mallpage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import event.dbconfig;
import shop.m_dbinfo;

//copyright Model (footer) + Login Model (로그인 확인하는 모델)
//모델에서는 PrintWrite 사용xx
public class copyright {
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	String sql = null;
	ArrayList<String> cp = null;
	m_dbinfo db = new m_dbinfo(); //shop 패키지
	String result = null;
	m_member mb = new m_member(); //DTO 선언
	
	//login (loginok.java)
	public String user_login(m_member dto) {
		try {
			this.con = this.db.getConnection();
			this.sql = "select mid,mname,memail from joins where mid = ? and mpass = ?";
			this.ps = this.con.prepareStatement(sql);
			this.ps.setString(1, dto.getMid());
			this.ps.setString(2, dto.getMpass());
			this.rs = this.ps.executeQuery();
			
			if(this.rs.next() == true) { //정상적으로 아이디 및 패스워드가 맞을 경우
				this.result = "ok";
				//m_member mb = new m_member();	// 기존에 사용된 DTO를 초기화 및 새로운 DTO값을 구성
				mb.setMid(this.rs.getString("mid")); //아이디 저장
				mb.setMname(this.rs.getString("mname"));//가입자명
				mb.setMemail(this.rs.getString("memail"));//이메일
			}
		}catch(Exception e) {
			System.out.println(e);
		}finally {
			try {
				this.rs.close();
				this.ps.close();
				this.con.close();
			}catch(Exception e) {
				
			}
		}
		return this.result;
	}
	
	public ArrayList<String> copyright_info() {
		try {
			this.con = this.db.getConnection();
			this.sql = "select * from corp_info";
			this.ps = this.con.prepareStatement(sql);
			this.rs = this.ps.executeQuery();
			this.rs.next();
			this.cp = new ArrayList<String>();
			this.cp.add(this.rs.getString("corp_nm"));
			this.cp.add(this.rs.getString("ceo_nm"));
			this.cp.add(this.rs.getString("corp_addr"));
			this.cp.add(this.rs.getString("corp_tel"));
			this.cp.add(this.rs.getString("corp_time"));
			this.cp.add(this.rs.getString("ceo_email"));
			this.cp.add(this.rs.getString("corp_no"));
			this.cp.add(this.rs.getString("corp_no2"));
			this.cp.add(this.rs.getString("corp_master"));
			this.cp.add(this.rs.getString("corp_domain"));
			
		}catch(Exception e) {
			System.out.println("Database Error");
		}finally {
			try {
				this.rs.close();
				this.ps.close();
				this.con.close();
			}catch(Exception e) {
				System.out.println("Database Error");
			}
		}
		return this.cp;
	}
}

