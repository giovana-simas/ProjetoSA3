const url= 'http://localhost:8080';
let stompClient;
let usuarioselect



 function conectarChat(id){
    console.log("Conectando com o chat...")

    let socket = new SockJS(url + '/chat');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function(frame){
        console.log("Conectado com: " + frame);
        stompClient.subscribe("/topic/mensagens/" + id, function(response){
            let data = JSON.parse(response.body);
            render(data.mensagem, data.fromLogin);
        })
    });
}

function usuarioselecionado(id){
    usuarioselect = id;
    console.log("Usuario selecionado: " + usuarioselect)

}

function enviarMensagem(from, text){
    stompClient.send("/app/chat/" + usuarioselect, {}, JSON.stringify({
        fromLogin: from,
        mensagem: text
    }));
}

