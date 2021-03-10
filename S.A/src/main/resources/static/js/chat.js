const url= 'https://localhost:8080';
let stompClient;



function conectarChat(id){
    console.log("Conectando com o chat...")

    let socket = new SockJS(url + '/chat' + id);
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function(frame){
        console.log("Conectado com: " + frame);
        stompClient.subscribe("/topic/mensagens")
    });
}