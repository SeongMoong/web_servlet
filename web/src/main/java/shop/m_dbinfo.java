package shop;
//Database, 사용자 아이디, 사용자 패스워드
import java.sql.Connection;
import java.sql.DriverManager;
//Database의 기본 메소드명 : getConnection (이 이름을 사용하지 않아도됨)
public class m_dbinfo {
	//해당 패키지(shop)에서만 사용하려면 protected
	//static 을 사용해야만 connection이 유지가 됨
	public static Connection getConnection() throws Exception {
		
		String db_driver = "com.mysql.cj.jdbc.Driver"; //db라이브러리 가져오기
		String db_url = "jdbc:mysql://localhost:3306/mrp"; //db 경로 연결
		String db_user = "project";//mysql에 접속하는 사용자
		String db_passwd = "a123456"; //mysql에 접속하는 사용자의 패스워드

		//실제 Database에 연결을 하는 명령어
		Class.forName(db_driver);
		Connection con = DriverManager.getConnection(db_url,db_user,db_passwd);
		System.out.println(con);
		return con; //데이터 베이스가 정상적으로 연결이 되었는지 확인결과를 return함
	}
	
}
