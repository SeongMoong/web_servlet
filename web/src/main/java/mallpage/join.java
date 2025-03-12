package mallpage;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/mallpage/join.do")
public class join extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//copyright 정보 Model
	//copyright cr = new copyright(); //copyright 에서 받아오기위함 //계속사용해야하는 문제점이 있음 (추상클래스 사용)
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ArrayList<String> cpdata = this.cr.copyright_info(); //copyright 에서 받아오는값
		
		footers ft = new footers();
		//ft.fts();
		request.setAttribute("cpdata", ft.cpdata); //copyright 에서 받아오는값

		RequestDispatcher rd = request.getRequestDispatcher("./join.jsp");
		rd.forward(request, response);
	}

}
class footers extends ab_footer{ //footer전용 class
public footers() {
	this.fts();
}
	
	@Override
public void fts() {
	super.fts();
}
}