package mallpage;
//product(상품정보) DTO (setter,getter)
public class dto_product {
int midx;
String pcode, pnm, pmoney, psale, psmoney, pimg, pdate;
//getter만 있는것은 VO, getter,setter가 같이 있는 것은 DTO
//get => getter => DTO에 저장된 값을 가져올 때
public int getMidx() {
	return midx;
}
//set => setter => DTO에 값을 저장할 때 사용
public void setMidx(int midx) {
	this.midx = midx;
}
public String getPcode() {
	return pcode;
}
public void setPcode(String pcode) {
	this.pcode = pcode;
}
public String getPnm() {
	return pnm;
}
public void setPnm(String pnm) {
	this.pnm = pnm;
}
public String getPmoney() {
	return pmoney;
}
public void setPmoney(String pmoney) {
	this.pmoney = pmoney;
}
public String getPsale() {
	return psale;
}
public void setPsale(String psale) {
	this.psale = psale;
}
public String getPsmoney() {
	return psmoney;
}
public void setPsmoney(String psmoney) {
	this.psmoney = psmoney;
}
public String getPimg() {
	return pimg;
}
public void setPimg(String pimg) {
	this.pimg = pimg;
}
public String getPdate() {
	return pdate;
}
public void setPdate(String pdate) {
	this.pdate = pdate;
}
}
