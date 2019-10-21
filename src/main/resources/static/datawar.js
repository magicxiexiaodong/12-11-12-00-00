$(function(){
	var socket = new SockJS('/datawar/room');
	stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/datawar-room/list', function (data) {
        	console.log(data);
        });
    });
});