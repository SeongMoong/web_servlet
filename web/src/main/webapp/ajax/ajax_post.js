function ajax(){	//전송 버튼 클릭시 발동되는 이벤트 함수
	var mid = document.getElementById("mid");
	var memail = document.getElementById("memail");
	if(mid.value == ""){
		alert("아이디를 입력하세요.");
	}
	else{
		//ajax POST 통신을 위함 함수 호출
		this.ajax_post(mid.value, memail.value);
	}
}
//Ajax POST 전송하는 함수
function ajax_post(mid, memail){
	var http, result;		//http : Back-end통신, result : Back-end 제공한 데이터
	http = new XMLHttpRequest();
	http.onreadystatechange = function(){
		if(http.readyState==4 && http.status == 200){
			console.log(this.response);
		}
		else if(http.status==404){
			console.log("경로 오류 발생")
		}
		else if(http.status==405){
			console.log("통신 규격 오류 발생")
		}
	}
	//get 통신
	//http.open("get","./ajax_postok.do?userid="+mid,true);
	//http.send();
	
	//post 통신
	//http.open("post","./ajax_postok.do",true); //비동기 통신을 진행하겠다~
	http.open("post","http://172.30.1.22:8080/web/ajax/ajax_postok.do")
	//ajax post 전송시 content-type 적용하여 urlencoded 적용해야만 정상적으로 백엔드로 값을 보낼 수 있음
	//post는 이줄(아래줄)이 있어야 백으로 날라감(post는 암호화되어있어서)
	http.setRequestHeader("content-type","application/x-www-form-urlencoded");
	//http.send("userid="+mid); //데이터 값을 한개 전송
	http.send("userid="+mid + "&useremail="+memail); //한개 이상 부터 & 사용
}