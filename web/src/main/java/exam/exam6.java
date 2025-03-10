package exam;

import java.util.Scanner;

public class exam6 {

	public static void main(String[] args) {
		String user[][]= {
				{"마동석","01053514423"},
				{"김무열","01010044223"},
				{"박지환","01069822901"},
				{"박지환","01030293901"}
		};
		Scanner sc = new Scanner(System.in);
		System.out.println("고객명을 입력하세요 : ");
		String name = sc.nextLine().replaceAll(" ", ""); //한칸 띄어쓰기 없애기
		System.out.println(name);
		
		int w = 0;
		while(w < user.length) {
			if(name.equals(user[w][0])) {
				System.out.println(user[w][0]+"님 연락쳐"+ user[w][1] + "입니다");
				
			}
			w++;
		}
		
		sc.close();
	}

}
