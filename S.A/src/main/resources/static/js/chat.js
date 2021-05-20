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
            if (usuarioselect==data.fromLogin){
                render(data.mensagem, data.fromLogin);
            }else{
                $('#msgNova'+ data.fromLogin).attr('hidden', false)
            }

        })
    });
}

function usuarioselecionado(id){
    usuarioselect = id;
    msgHiddenChat()
    $('#msgNova'+ usuarioselect).attr('hidden', true)
    nomeUsuario = $('#'+id).text();
    console.log("Usuario selecionado: " + usuarioselect)
    $('#usuarioChat').html('');
    $('#usuarioChat').append('Chat com ' + nomeUsuario);
    $('#chat-ul').empty();

    puxa(id);

}

function puxa (id){
    var mensagem = {
        id:document.querySelector('#id').value,
        fromLogin:document.querySelector('#login').value,
        hrmsg:document.querySelector('#hrmsg').value,
        mensagem:document.querySelector('#mensagem').value,
        chat:document.querySelector('#chatId').value
    }

    var formData = new FormData();

    let invocation = new XMLHttpRequest();

    formData.append("mensagem1", JSON.stringify(mensagem));
    console.log(id + "             " + document.getElementById(id).value)


    invocation.responseType = "json"
    invocation.open("GET", "/chat/list/" + document.getElementById(id).value, true)
    invocation.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            mensagem = invocation.response

            for (var i=0;i< mensagem.length ;i++){
                mensagem[i].hrmsg = parseInt(mensagem[i].hrmsg.substr(11,2))-3 + ":" + mensagem[i].hrmsg.substr(14,2);
                console.log(mensagem[i].mensagem)
                renderdb(mensagem[i].mensagem, mensagem[i].fromLogin, mensagem[i].hrmsg, usuarioConectado);
            }


        }
    };

    invocation.send();






}


function enviarMensagem(from, text, dataMsg){
    stompClient.send("/app/chat/" + usuarioselect, {}, JSON.stringify({
        fromLogin: from,
        mensagem: text,
        hrmsg: dataMsg
    }));
}

