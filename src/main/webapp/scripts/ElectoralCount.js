document.querySelector("#votacao").onclick = function(){
    window.location.href = "index.html";
}
function resultado() {
    let xhr = new XMLHttpRequest();
    xhr.open("GET", `webresources/ballotbox/resultado/`, false);
    xhr.send();
    if (xhr.status < 400) {
        const parser = new DOMParser();
        const xmlDoc = parser.parseFromString(xhr.responseText, "text/xml");
        return xmlDoc;
    }
    return null;  
}
window.onload = function () {
    const xmlDoc = resultado();
    const candidatos = xmlDoc.getElementsByTagName("candidatos");
    const partidos = xmlDoc.getElementsByTagName("partidos");
    const bodyCandidatos = document.getElementById("bodyCandidatos");
    const bodyPartidos = document.getElementById("bodyPartidos");

    for (let i = 0; i < candidatos.length; i++) {
        const candidato = candidatos[i];
        const nome = candidato.getElementsByTagName("nome")[0].textContent;
        const numero = candidato.getElementsByTagName("numero")[0].textContent;
        const qtdVotos = candidato.getElementsByTagName("qtdvoto")[0].textContent;
        const condicao = candidato.getElementsByTagName("condicao")[0].textContent;

        let linha = bodyCandidatos.insertRow();
        linha.innerHTML = `<td>${nome}</td><td>${numero}</td><td>${qtdVotos}</td>`;
        if (condicao === "eleito") {
            linha.style.backgroundColor = "gray";
        }
    }

    for (let j = 0; j < partidos.length; j++) {
        const partido = partidos[j];
        const nome = partido.getElementsByTagName("nome")[0].textContent;
        const sigla = partido.getElementsByTagName("sigla")[0].textContent;
        const numero = partido.getElementsByTagName("numero")[0].textContent;
        const qtdVotos = partido.getElementsByTagName("qtdvoto")[0].textContent;

        let linha = bodyPartidos.insertRow();
        linha.innerHTML = `<td>${nome}</td><td>${sigla}</td><td>${numero}</td><td>${qtdVotos}</td>`;
    }
};
