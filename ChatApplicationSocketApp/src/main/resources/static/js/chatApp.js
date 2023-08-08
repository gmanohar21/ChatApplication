$(document).ready(() => {
	
		javaSynch();
	$("#enter").click(() => {
		loginUser();
		var formData={
			name:$("#name").val(),
			online:true
		}
		    $.ajax({
            url: '/chatScreen', // Replace with your server's endpoint
            type: 'POST',
            data: JSON.stringify(formData),
            contentType: 'application/json',
            success: function(response) {
                // Handle success response
                window.location.href = '/chatScreenDisplay';
                console.log('Success:', response);
            },
            error: function(xhr, status, error) {
                // Handle error
                console.log('Error:', status, error);
            }
        });
		 //  
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


