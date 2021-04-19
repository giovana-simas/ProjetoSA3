const compararSenha = function(){
    senha1 = document.getElementById("senha1").value;
    senha2 = document.getElementById("senha2").value;
    if(senha1!==senha2){
        alert("as senhas não batem")
        $("#salvar").prop('disabled', true);
        
    }else{
        $("#salvar").prop('disabled', false);
        
    }
 }

 const editarPerfil = function(){

    $('#editNome').attr('readonly', false);

    $('#editSalvar').attr('hidden', false);
    $('#cancelar').attr('hidden', false);
     $('#editar').attr('hidden', true);

 }

 function cancelarEditar(){
    window.history.back();
    console.error("Aaaa")
    $('#editNome').attr('readonly', true);
    $('#editSalvar').attr('hidden', true);
    $('#cancelar').attr('hidden', true);
    $('#editar').attr('hidden', false);

}

function verificaEmail(){
    var email = document.getElementById("emailCadastro").value;
    var aEmail = email.split('');
    var verificado=false;
    console.log(aEmail);
    for (var i=0;i<aEmail.length;i++){
        if(aEmail[i] == "@"){
            verificado = true;
            $("#salvar").prop('disabled', false);
        }
    }
    if (!verificado){
        alert("email invalido")
        $("#salvar").prop('disabled', true);

    }

}