package mallpage;
//DTO (회원가입, 로그인에 사용하는) - getter,setter
public class m_member { //DTO=>model에서 값을 받을 수 있게
	String mid,mpass,mname,memail,mtel,event_email,event_sms;

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getMpass() {
		return mpass;
	}

	public void setMpass(String mpass) {
		this.mpass = mpass;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getMemail() {
		return memail;
	}

	public void setMemail(String memail) {
		this.memail = memail;
	}

	public String getMtel() {
		return mtel;
	}

	public void setMtel(String mtel) {
		this.mtel = mtel;
	}

	public String getEvent_email() {
		return event_email;
	}

	public void setEvent_email(String event_email) {
		this.event_email = event_email;
	}

	public String getEvent_sms() {
		return event_sms;
	}

	public void setEvent_sms(String event_sms) {
		this.event_sms = event_sms;
	}
}
