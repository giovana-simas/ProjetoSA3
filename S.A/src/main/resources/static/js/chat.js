const url= 'http://localhost:8080';
let stompClient;
let usuarioselect
let usuarioConectado

function msgHiddenChat(){
    if(usuarioselect == null){
        $('.hiddenChat').attr('hidden', true);
        $('.hiddenChatMsg').attr('hidden', false);
        
    }else{
        $('.hiddenChat').attr('hidden', false);
        $('.hiddenChatMsg').attr('hidden', true);
    }
}

 function conectarChat(id){
    usuarioConectado = id
    console.log("Conectando com o chat...")
    msgHiddenChat()

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
    msgHiddenChat()
    nomeUsuario = $('#usuarioname').text();
    console.log("Usuario selecionado: " + usuarioselect)
    $('#usuarioChat').html('');
    $('#usuarioChat').append('Chat com ' + nomeUsuario);

}

function enviarMensagem(from, text){
    stompClient.send("/app/chat/" + usuarioselect, {}, JSON.stringify({
        fromLogin: from,
        mensagem: text,
        hrmsg: dataMsg
    }));
}

