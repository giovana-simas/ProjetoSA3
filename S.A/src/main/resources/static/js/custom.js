//Custom.js
let $chatHistory;
let $button;
let $textarea;
let $chatHistoryList;

function init() {
    cacheDOM();
    bindEvents();
}

function bindEvents() {
    $button.on('click', addMessage.bind(this));
    $textarea.on('keyup', addMessageEnter.bind(this));
}


function cacheDOM() {
    $chatHistory = $('.chat-history');
    $button = $('#sendBtn');
    $textarea = $('#message-to-send');
    $chatHistoryList = $chatHistory.find('ul');
}

function render(message, userName) {
    scrollToBottom();
    // responses
    var templateResponse = Handlebars.compile($("#message-response-template").html());
    var contextResponse = {
        response: message,
        time: getCurrentTime(),
        userName: userName
    };

    setTimeout(function () {
        $chatHistoryList.append(templateResponse(contextResponse));
        scrollToBottom();
    }.bind(this), 200);
}

function renderdb(message, userName, time, usurioConectado) {
    scrollToBottom();
    // responses

    if (userName != usurioConectado){
        var templateResponse = Handlebars.compile($("#message-response-template").html());
        var contextResponse = {
            response: message,
            time: time
        };
    }

    if (userName == usurioConectado){
        var templateResponse = Handlebars.compile($("#message-template").html());
        var contextResponse = {
            messageOutput: message,
            time: time
        };
    }


    setTimeout(function () {
        $chatHistoryList.append(templateResponse(contextResponse));
        scrollToBottom();
    }.bind(this), 200);
}

function sendMessage(message) {
    let username = usuarioConectado
    let dataMsg = getCurrentData();
    console.log(dataMsg)
    enviarMensagem(username, message, dataMsg);
    scrollToBottom();
    if (message.trim() !== '' || message.trim() != null || message.trim() != "\n" || message.trim() != "" || message !== '' || message != null || message != "\n" || message != "") {
        var template = Handlebars.compile($("#message-template").html());
        var context = {
            messageOutput: message,
            time: getCurrentTime(),
            toUserName: usuarioselect
        };

        $chatHistoryList.append(template(context));
        scrollToBottom();
        $textarea.val('');
    }
}

function scrollToBottom() {
    $chatHistory.scrollTop($chatHistory[0].scrollHeight);
}

function getCurrentTime() {
    return new Date().toLocaleTimeString().replace(/([\d]+:[\d]{2})(:[\d]{2})(.*)/, "$1$3");
}


function getCurrentData() {
    now = new Date()
    data = (now.getFullYear()+"-"+(now.getMonth()+1)+"-"+now.getDate()+ "T" + now.toLocaleTimeString());
    if(now.getMonth()<10&&now.getDate()<10){
        var data = data.substr(0,5) + "0" + data.substr(5,2) + "0" +data.substr(7)
    }
    else if(now.getDate()<10){
        var data = data.substr(0,5) + "" + data.substr(5,2) + "0" +data.substr(7)
    } else if(now.getMonth()+1<10){
        var data = data.substr(0,5) + "0" + data.substr(5,2) + "" +data.substr(7)    }
        data += "-03:00"
        //data = "2021-04-07T19:34"
    return data;
}

function addMessage() {
    if($textarea.val() !== '' || $textarea.val() != null || $textarea.val() != "\n" ||$textarea.val() != ""){
        sendMessage($textarea.val());
    }

}

function addMessageEnter(event) {
    // enter was pressed
    if (event.keyCode === 13) {
        addMessage();
    }
}

init();

