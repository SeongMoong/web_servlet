package mallpage;

import java.util.ArrayList;

public abstract class ab_footer {
	//copyright 정보 Model
	copyright cr = new copyright(); //copyright 에서 받아오기위함 //계속사용해야하는 문제점이 있음 (추상클래스 사용)
	ArrayList<String> cpdata = null;  //copyright 에서 받아오는값	
	public void fts() {
		this.cpdata = cr.copyright_info();
		//System.out.println(cpdata);
	}
}
