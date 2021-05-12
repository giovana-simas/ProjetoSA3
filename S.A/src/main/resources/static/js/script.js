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

     var some = $('#editNome').attr('readonly');
     some = !some;

    $('#editNome').attr('readonly', some);
     $('#editAjuda').attr('readonly', some);
     $('#editAjuda').attr('hidden', some);

     $('#editSexo').attr('hidden', some);
     $('#editDescricao').attr('readonly', some);
    $('#editSalvar').attr('hidden', some);
    $('#cancelar').attr('hidden', some);
     $('#editar').attr('hidden', !some);


 }

 function sugerirMateria(){
    var some = $('#flutuante').attr('hidden');
    some = !some;
     $('#flutuante').attr('hidden', some);
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

function closeBar(){
    if ($("#filtro").is(":hidden")) {
        $( "#filtro" ).show()
        $( "#iconMenu").hide()

    }else{
        $( "#filtro" ).hide()
        $( "#iconMenu" ).show();
    }
       
}


function logout(){
    $("#logout").submit();
    $("#logout").attr('hidden', true);

}
