var linhaAutor
linhaAutor = 1

function replicaAutor() {

	var camposAutor = new Array()
	camposAutor[0] = "autor";
	document.getElementsByTagName("a1")[0].innerHTML += "<br><a2>" + document.getElementsByTagName("a2")[0].innerHTML + "</a2>";
	div_nova = document.getElementsByTagName("a2")[linhaAutor]
	campos_novos = div_nova.getElementsByTagName("select");

    for(i = 0; i < campos_novos.length; i++) {
		campos_novos[i].id = camposAutor[i]+"_"+linhaAutor;
		campos_novos[i].name = camposAutor[i]+"_"+linhaAutor;
    }
    linhaAutor++;
}

var linhaReu
linhaReu = 1

function replicaReu() {

	var camposReu = new Array()
	camposReu[0] = "reu";
	document.getElementsByTagName("r1")[0].innerHTML += "<br><r2>" + document.getElementsByTagName("r2")[0].innerHTML + "</r2>";
	div_nova = document.getElementsByTagName("r2")[linhaReu]
	campos_novos = div_nova.getElementsByTagName("select");

    for(i=0;i<campos_novos.length;i++) {
		campos_novos[i].id = camposReu[i]+"_"+linhaReu;
		campos_novos[i].name = camposReu[i]+"_"+linhaReu;
    }
    
    linhaReu++;
    
}