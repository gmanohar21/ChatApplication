$(document).ready(() => {
	
		javaSynch();
	$("#enter").click(() => {
		loginUser();
		window.location.href = 'chatScreen.html';
	});

	$("#send").on("click", () => {
		sendMsg();
	});

});
function loginUser() {
	var messageElement = document.getElementById('message');
	var name = $('#name').val();
	if (name === "" || name === null) {
		messageElement.textContent = 'Invalid credentials. Please try again.';
	} else {
		localStorage.setItem("name",name );
	}

}

function sendMsg() {
	var jsonObj = {
		name: localStorage.getItem("name"),
		content: $("#msg").val()
	}
	stompClient.send("/app/message", {}, JSON.stringify(jsonObj));

}

function javaSynch() {
	/*for connecting to socket*/
	var socket = new SockJS("/server");
	stompClient = Stomp.over(socket)
	/*for connecting to Stomp Client*/
	stompClient.connect({}, function(frame) {
		console.log("connected " + frame);

		//Subscribe Stomp client
		stompClient.subscribe("/target/return", function(response) {
			chatMessage(JSON.parse(response.body));
			$("#msg").val('');
		});
	});
}


function chatMessage(responseMsg) {
	$("#msgTable").prepend(`<tr><td><b>${responseMsg.name} :</b>${responseMsg.content}</td></tr>`);
}


