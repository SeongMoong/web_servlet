package mallpage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import shop.m_dbinfo;

//회원가입 처리 Model
public class insert_join {
	Connection con = null;
	PreparedStatement ps = null;
	String sql = null;
	m_dbinfo db = new m_dbinfo(); //shop 패키지
	//m_member mb = new m_member(); //DTO 이거 안불러와도 값을 받을 수 있음
	Integer result = null;
	public Integer insert_member(m_member m) {
		try {
			this.con = this.db.getConnection();
			this.sql = "insert into joins values ('0',?,?,?,?,?,?,?,now())";
			this.ps = this.con.prepareStatement(sql);
			//DTO에서 getter 이용해서 DB에 저장
			this.ps.setString(1, m.getMid());
			this.ps.setString(2, m.getMpass());
			this.ps.setString(3, m.getMname());
			this.ps.setString(4, m.getMemail());
			this.ps.setString(5, m.getMtel());
			this.ps.setString(6, m.getEvent_email());
			this.ps.setString(7, m.getEvent_sms());
			
			this.result = this.ps.executeUpdate();
			
		}catch(Exception e) {
			this.result = null;
			System.out.println(e);
		}finally {
			try {
				this.ps.close();
				this.con.close();
			}catch(Exception e) {
				this.result = null;
				System.out.println(e);
			}
		}
		return this.result;
		
		//System.out.println(m.getMname());
	}
	
}
