package exam;

import java.util.ArrayList;
import java.util.Arrays;

public class exam8 extends ex8_box {
	@Override
	public void even() {
		ArrayList<Integer> number = new ArrayList<Integer>(Arrays.asList(data));
		//number.remove(3);
		int w = 0;
		while(w < number.size()) { //배열 remove를 하면 index번호가 바뀜
			if(number.get(w)% 2 == 1) { //홀수값 조건은 배열에서 삭제
				number.remove(w);
				w = 0;			//배열값 삭제 되면 노드변화로 처음부터 다시 검토
			}
			else {
				w++;		//해당 조건(홀수)가 아니면 +1증가시켜서 배열 검토
							//Override는 재귀함수 사용이 어려움(안씀)
			}
		}
		System.out.println(number);
}
	@Override
	public void odd() {
		
	}
	public static void main(String[] args) {
		new exam8().even();
		new exam8().odd();
	}
}
//추상 클래스 
abstract class ex8_box {
	Integer data[] = {3,5,2,1,6,7,8,9,10,4};
	public void sum() {
		System.out.println(this.data);
	}
	abstract public void even();
	abstract public void odd();
}
//짝수, 홀수 if() => add