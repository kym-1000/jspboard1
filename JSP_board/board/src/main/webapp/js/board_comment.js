async function getCommentListFromServer(bno) { //서버(컨트롤러)에 리스트를 달라고 통신요청 uri 보낸다!!
	try{
		const resp = await fetch('/CMT/list/'+bno);  // /cmt/list/140 보낼 uri
		const cmtlist = await resp.json();   // out.print(jsonData)에서 받는다!
		return cmtlist;
	}catch(error){
		console.log(error);
	}
}

function spreadCommentList(result) { //댓글 list
	console.log(result);
   let div = document.getElementById("Comment");
   div.innerHTML = '';
   for (let i = 0; i < result.length; i++) {
      let html = `<div class="d-flex">`;
      html += `<div class="flex-shrink-0"><img class="rounded-circle" src="/_fileUpLoad/th_${result[i].image_file}" alt="이미지없음" /></div>`;
      html += `<div class="ms-3">`;
      html += `<div class="fw-bold" id="cmtWriter1">${result[i].writer}</div>`;
      html += `${result[i].content}`;
      html += `<div><button type="button" data-cno="${result[i].cno}" data-bno="${result[i].bno}" data-pwd="${result[i].pwd}" 
       class="btn btn-sm btn-outline-danger cmtDelBtn">X</button></div>`;
      html += `</div></div><br>`;
      div.innerHTML += html;
      }
      
}

async function removeCommentFromServer(cnoVal){
	try{
		const url = '/CMT/remove/'+cnoVal;
		const config = {
			method : 'post'
		}
		const resp = await fetch(url,config);
		const result = await resp.text();
		return result;
	}catch(error){
		console.log(error);
	}
}

document.addEventListener('click',(e)=>{
	if(e.target.classList.contains('cmtDelBtn')){
		let cnoVal = e.target.dataset.cno;
		let bnoVal = e.target.dataset.bno;
		let pwdVal = e.target.dataset.pwd;
		var inputPwd = prompt('비밀번호를 입력하세요!');
		console.log(pwdVal+"   "+inputPwd);
		if(inputPwd == pwdVal){
		console.log(cnoVal);
		removeCommentFromServer(cnoVal).then(result =>{
			if(result > 0){
				alert("댓글삭제 성공!!!");
				printCommentList(bnoVal);
				console.log(bnoVal);
			}
		})
		
		}else{
			alert('비밀번호가 맞지 않습니다!');
			return false;
		}
	}
})



function printCommentList(bno){
	getCommentListFromServer(bno).then(result=>{ // cmtlist
		console.log(result);
		if(result.length > 0){
			spreadCommentList(result);
		}else{
			let div = document.getElementById("Comment");
			div.innerText = "comment 가 없습니다";
		}
	})
}


async function postCommentToServer(cmtData){
	try{
		const url ="/CMT/post";
		const config={
			method:'post',
			headers:{
				'Content-Type':'application/json; charset=utf-8'
			},
			body: JSON.stringify(cmtData)
			// 존슨 스트링 타입으로 변환해서 보낸다!
		};
		const resp = await fetch(url, config);
		const result = await resp.text();   // out.print(isOk)
		return result; // postCommentToServer(cmtData).then(result
		
	}catch(error){
		console.log(error);
	}

}


document.getElementById('cmtAddBtn').addEventListener('click',()=>{
	const cmtText = document.getElementById('cmtText').value;
	console.log(cmtText);
	if(cmtText == null || cmtText==''){
		alert('댓글을 입력해주세요.');
		return false;
	}else{
		let cmtData = {
			bno : bnoVal,
			writer : document.getElementById('cmtWriter').value,
			content : cmtText,
			image_file : document.getElementById('cmtimage').value,
			pwd : document.getElementById('cmtPwd').value
		};

		postCommentToServer(cmtData).then(result => {
			if(result > 0){
				alert("댓글등록 성공!!!");
				document.getElementById('cmtText').value = ""; // 성공하면 지워라
			}
			printCommentList(cmtData.bno);
		})

	}

})