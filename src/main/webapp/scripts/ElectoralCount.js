document.addEventListener("DOMContentLoaded", () => {
    fetchElectionResults();
});

function fetchElectionResults() {
    const xhr = new XMLHttpRequest();
    xhr.open("GET", "prova/eleicao/result", true); 
    xhr.setRequestHeader("Accept", "application/xml");

    xhr.onload = function () {
        if (xhr.status === 200) {
            const xmlData = xhr.responseXML;
            console.log(xmlData);
            updateTables(xmlData);
        } else {
            console.error("Erro ao buscar resultados:", xhr.statusText);
        }
    };

    xhr.onerror = function () {
        console.error("Erro na conexão com o servidor.");
    };

    xhr.send();
}

function updateTables(xmlData) {
    const candidatesTableBody = document.querySelector("table:nth-of-type(1) tbody");
    const partiesTableBody = document.querySelector("table:nth-of-type(2) tbody");

    const candidates = xmlData.getElementsByTagName("candidates");
    const parties = xmlData.getElementsByTagName("entourages");

    // Atualizar tabela de candidatos
    Array.from(candidates).forEach(candidate => {
        const name = candidate.getElementsByTagName("candidateName")[0].textContent;
        const number = candidate.getElementsByTagName("candidateNumber")[0].textContent;
        const votes = candidate.getElementsByTagName("votesNumber")[0].textContent;
        const isElected = candidate.getElementsByTagName("elected")[0].textContent === "true";

        const row = document.createElement("tr");
        row.style.backgroundColor = isElected ? "green" : "red";

        row.innerHTML = `
            <td>${name}</td>
            <td>${number}</td>
            <td>${votes}</td>
        `;
        candidatesTableBody.appendChild(row);
    });

    // Atualizar tabela de partidos
    Array.from(parties).forEach(party => {
        const name = party.getElementsByTagName("partyName")[0].textContent;
        const number = party.getElementsByTagName("partyNumber")[0].textContent;
        const votes = party.getElementsByTagName("partyVotes")[0].textContent;

        const row = document.createElement("tr");

        row.innerHTML = `
            <td>${name}</td>
            <td>${number}</td>
            <td>${votes}</td>
        `;
        partiesTableBody.appendChild(row);
    });
}
