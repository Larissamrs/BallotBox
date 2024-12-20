class GUI {
    constructor() {
        this.index = 0;
        this.number = 0; 
    }
    addNumber(num) { 
        if (this.index <= 4) { 
            let spans = document.querySelectorAll("h2 span");
            spans[this.index++].textContent = num;
        }
    }
    correct() {
        let spans = document.querySelectorAll("h2 span");
        for (let span of spans) {
            span.innerHTML = "&nbsp;";
        }
        this.index = 0;
        this.writeMessage("");
    }
    writeMessage(msg) { 
        let p = document.querySelector("#name");
        p.textContent = msg;
    }
    async go(evt) {
        let input = evt.currentTarget;
        let h2 = document.querySelector("h2");
        switch (input.value) {
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                this.addNumber(input.value);
                let text = h2.textContent.split(' ').join('').trimEnd();
                this.number = parseInt(text);
                this.writeMessage("");
                if (text.length === 2) { 
                    const partido = await politicalParty(this.number);
                    if (partido) {
                        this.writeMessage(`Partido: ${partido.partyName}`);
                    } else {
                        this.writeMessage("Partido inexistente.");
                    }
                }
                if (text.length === 4) {
                    const candidato = await candidate(this.number);
                    if (candidato) {
                        this.writeMessage(`Candidato: ${candidato.getElementsByTagName("candidateName")[0].textContent}`);
                    } else {
                        this.writeMessage("Candidato inexistente.");
                    }
                }
                break;
            case 'Branco':
                this.writeMessage("Branco");
                this.correct();
                this.number = 99;
                break;
            case 'Corrige':
                this.correct();
                break;
            case 'Confirma':
                this.registerEvents(false);
                const voto = votar(this.number);
                this.writeMessage(voto);
                console.log(voto)
                setTimeout(() => {
                    this.correct();
                    this.registerEvents(true);
                }, 2000);
                break;
        }
    }
    registerEvents(reg) {
        let botoes = document.querySelectorAll("input:enabled[type='button']");
        botoes.forEach(e => reg ? e.onclick = this.go.bind(this) : e.onclick = null);
    }
    init(){
        this.registerEvents(true);
    }
}
let bb = new GUI();
bb.init();
async function politicalParty(politicalpartyId) {
    const endpoint = `prova/eleicao/politicalparty/${politicalpartyId}`;
    try {
        const response = await fetch(endpoint, { method: "GET" });
        if (!response.ok) {
            console.log(`Erro na requisição: ${response.status} - ${response.statusText}`);
            return null;
        }
        return response.json();
    } catch (e) {
        console.log("Partido não encontrado:", e);
        return null; 
    }
}
async function candidate(candidateId) {
    const endpoint = `prova/eleicao/candidate/${candidateId}`;
    try {
        const response = await fetch(endpoint, { method: "GET" });
        if (!response.ok) {
            console.log(`Erro na requisição: ${response.status} - ${response.statusText}`);
            return null;
        }
        const xmlString = await response.text();
        const parser = new DOMParser();
        const xmlDocument = parser.parseFromString(xmlString, "application/xml");
        if (xmlDocument.getElementsByTagName("parsererror").length > 0) {
            console.log("Erro ao processar XML.");
            return null;
        }

        return xmlDocument;
    } catch (e) {
        console.log("Candidato não encontrado:", e);
        return null; 
    }
}
function votar(votarId) {
    const xhr = new XMLHttpRequest();
    xhr.open("POST", `prova/eleicao/votar`, false); 
    xhr.setRequestHeader("Content-Type", "application/json");

    const data = JSON.stringify({ voto: votarId });
    xhr.send(data);

    if (xhr.status === 200) {
        const response = JSON.parse(xhr.responseText);
        return response.mensagem; 
    } else {
        console.error("Erro ao enviar voto:", xhr.status, xhr.statusText);
        return null; 
    }
}