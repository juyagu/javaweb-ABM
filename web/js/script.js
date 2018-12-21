var obj;
function modificarRegistro(id){
    if(window.XMLHttpRequest){
	obj = new window.XMLHttpRequest();
    }else if(window.ActiveXObject){
	obj = new window.ActiveXObject("Microsoft.XMLHTTP");
    }else{
	alert("El navegador no soporta AJAX");
	return 0;
    }
    
    obj.onreadystatechange = callback;
    obj.open("GET","/ABM/action?id=" + id)
    obj.send(null);
}

function callback(){
    if(obj.readyState === 4 && obj.status === 200){
	
	document.querySelector('#form-modificar').classList.remove('hide');
	//console.log(JSON.parse(obj.responseText));
	var producto = JSON.parse(obj.responseText);
	document.querySelector("#mod-presentacion").value = producto.presentacion;
	document.querySelector("#mod-descripcion").value = producto.descripcion;
	document.querySelector("#mod-precio").value = producto.precio;
	document.querySelector("#mod-cantidad").value = producto.cantidad;
	
	var nodo = document.createElement("input");
	nodo.type = "hidden";
	nodo.value=producto.id;
	nodo.name = "id";
	document.querySelector("#ocultos").innerHTML = "";
	document.querySelector("#ocultos").appendChild(nodo);
    }
}

document.querySelector(".btn-modificar").addEventListener("click",modificarFormulario);

function modificarFormulario(){
    document.querySelector("#form-modificar").submit();
}