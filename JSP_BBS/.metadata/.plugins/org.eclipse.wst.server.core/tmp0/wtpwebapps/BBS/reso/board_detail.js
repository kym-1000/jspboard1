async function getCommentListFromServer(bindex) { //서버(컨트롤러)에 리스트를 달라고 통신요청 uri 보낸다!!
	try{
		const resp = await fetch('/cmt/list/'+bindex);  // /cmt/list/140 보낼 uri
		const cmtlist = await resp.json();   // out.print(jsonData)에서 받는다!
		return cmtlist; 
	}catch(error){
		console.log(error);
	}
}

function spreadCommentList(result) { //댓글 list
	console.log(result);
   let div = document.getElementById("accordionExample");
   div.innerHTML = '';
   for (let i = 0; i < result.length; i++) {
      let html = '<div class="accordion-item">';
      html += `<h2 class="accordion-header" id="heading${i}">`;
      html += `<button class="accordion-button" type="button" data-bs-toggle="collapse" 
         data-bs-target="#collapse${i}" aria-expanded="true" aria-controls="collapse${i}">`;
      html += `${result[i].cno}, ${result[i].bindex}, ${result[i].writer}</button></h2>`;
      html += `<div id="collapse${i}" class="accordion-collapse collapse show" `;
      html += `aria-labelledby="heading${i}" data-bs-parent="#accordionExample">`;
      html += `<div class="accordion-body">`;
      html += `<button type="button" data-cno="${result[i].cno}" data-bindex="${result[i].bindex}" 
             class="btn btn-sm btn-outline-warning cmtModBtn">%</button>`;
      html += `<button type="button" data-cno="${result[i].cno}" data-bindex="${result[i].bindex}"
            class="btn btn-sm btn-outline-danger cmtDelBtn">X</button><br>`;
      html += `<input type="text" class="form=control" id="cmtText1" value="${result[i].content}"><br>`;
      html += `${result[i].reg_at}`;
      html += `</div></div></div>`;
      div.innerHTML += html;
      }
}


async function removeCommentFromServer(cnoval){
	try{
		const url = '/cmt/remove/'+cnoval;
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

async function updateCommentFromServer(cnoval,cmtText1){
	try{
		const url = '/cmt/update';
		const config = {
				method : 'post',
				headers : {
					'Content-Type':'application/json; charset=utf-8'
				},
				body : JSON.stringify({cno:cnoval ,content:cmtText1})
		} 
		const resp = await fetch(url,config);
		const result = await resp.text();
		return result;
	}catch(error){
		console.log(error);
	}
	
	
}


document.addEventListener('click',(e)=>{
	if(e.target.classList.contains('cmtModBtn')){ 
		let cnoval = e.target.dataset.cno;
		console.log(cnoval);
		let div= e.target.closest('div'); // 타겟을 기준으로 가장 가까운 div 찾기 
		let cmtText1 = div.querySelector('#cmtText1').value;
		updateCommentFromServer(cnoval,cmtText1).then(result =>{
			if(result > 0){
				alert("댓글수정 성공");
				printCommentList(bindexval);
			}
		})
	}
	if(e.target.classList.contains('cmtDelBtn')){
		let cnoval = e.target.dataset.cno;
		let bindexval = e.target.dataset.bindex;
		console.log(cnoval);
		removeCommentFromServer(cnoval).then(result =>{
			if(result > 0){
				alert("댓글삭제 성공!!!");
				printCommentList(bindexval);
				console.log(bindexval);
			}
		})
	}
})



function printCommentList(bindex){
	getCommentListFromServer(bindex).then(result=>{ // cmtlist
		console.log(result);
		if(result.length > 0){
			spreadCommentList(result);
		}else{
			let div = document.getElementById("accordionExample");
			div.innerText = "comment 가 없습니다.";
		}
	})
}

async function postCommentToServer(cmtData){
	try{
		const url ="/cmt/post";
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
			bindex : bindexVal,
			writer : document.getElementById('cmtWriter').value,
			content : cmtText
		};

		postCommentToServer(cmtData).then(result => {
			if(result > 0){
				alert("댓글등록 성공!!!");
				document.getElementById('cmtText').value = ""; // 성공하면 지워라
			}
			printCommentList(cmtData.bindex);
		})

	}

})