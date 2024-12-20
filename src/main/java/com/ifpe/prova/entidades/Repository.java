package com.ifpe.prova.entidades;

import java.util.ArrayList;
import java.util.List;

public class Repository {
    private List<Candidate> candidates = new ArrayList<>();
    private List<PoliticalParty> entourages = new ArrayList<>();
   
    public Repository() {
        this.populateCandidates();
        this.populatePoliticalParties();
    }
    private void populatePoliticalParties() {
        String[][] parties = {
            {"Progressistas", "11", "PP"},
            {"Partido Democrático Trabalhista", "12", "PDT"},
            {"Partido dos Trabalhadores", "13", "PT"},
            {"Partido Trabalhista Brasileiro", "14", "PTB"},
            {"Movimento Democrático Brasileiro", "15", "MDB"},
            {"Branco", "99", "BRA"},
            {"Nulos", "91", "NUL"}
        };

        for (String[] partyData : parties) {
            PoliticalParty party = new PoliticalParty();
            party.setPartyName(partyData[0]);
            party.setPartyNumber(Integer.parseInt(partyData[1]));
            party.setPartyVotes(0);
            entourages.add(party);
        }
    }
    private void populateCandidates() {
        String[][] candidateData = {
            {"Guilherme Gurgel", "1110"},
            {"Haroldo Hommus", "1120"},
            {"Ivan Istmo", "1130"},
            {"Juliana Justos", "1140"},
            {"Karol Konca", "1150"},
            {"Luciana Lemos", "1210"},
            {"Mário Mendes", "1220"},
            {"Noemi Noruega", "1230"},
            {"Otávio Orlando", "1240"},
            {"Arnaldo Antunes", "1250"},
            {"Bruno Barreto", "1310"},
            {"Carla Camuratti", "1320"},
            {"Daniel Dantas", "1330"},
            {"Emanuella Esteves", "1340"},
            {"Fábio Farias", "1350"},
            {"Pedro Parente", "1410"},
            {"Renata Rueda", "1420"},
            {"Sandra Sá", "1430"},
            {"Túlio Telhada", "1440"},
            {"Ubaldo Uchôa", "1450"},
            {"Vânia Valadares", "1510"},
            {"Camila Cavalcanti", "1520"},
            {"Maria Monteiro", "1530"},
            {"Marcos Monteiro", "1540"},
            {"Gustavo Gomes", "1550"}
        };

        for (String[] candidateInfo : candidateData) {
            Candidate candidate = new Candidate();
            candidate.setCandidateName(candidateInfo[0]);
            candidate.setCandidateNumber(Integer.parseInt(candidateInfo[1]));
            candidate.setVotesNumber(0);
            candidates.add(candidate);
        }
    }
    public Candidate findCandidateByNumber(int candidateNumber) {
        for (Candidate candidate : candidates) {
            if (candidate.getCandidateNumber() == candidateNumber) {
                return candidate; 
            }
        }
        return null; 
    }
    public PoliticalParty findPartyByNumber(int partyNumber) {
        for (PoliticalParty party : entourages) {
            if (party.getPartyNumber() == partyNumber) {
                return party; 
            }
        }
        return null; 
    }
    public void countVote(int voteNumber) {
        if (String.valueOf(voteNumber).length() == 2) {
            boolean partyFound = false;
    
            for (PoliticalParty party : entourages) {
                if (party.getPartyNumber() == voteNumber) {
                    party.setPartyVotes(party.getPartyVotes() + 1);
                    partyFound = true;
                    break;
                }
            }
    
            if (!partyFound) {
                for (PoliticalParty party : entourages) {
                    if (party.getPartyNumber() == 91) {
                        party.setPartyVotes(party.getPartyVotes() + 1);
                        break;
                    }
                }
            }
        } 
        else if (String.valueOf(voteNumber).length() == 4) {
            boolean candidateFound = false;
    
            for (Candidate candidate : candidates) {
                if (candidate.getCandidateNumber() == voteNumber) {
                    candidate.setVotesNumber(candidate.getVotesNumber() + 1);
                    candidateFound = true;
    
                    int partyNumber = Integer.parseInt(String.valueOf(voteNumber).substring(0, 2));
                    for (PoliticalParty party : entourages) {
                        if (party.getPartyNumber() == partyNumber) {
                            party.setPartyVotes(party.getPartyVotes() + 1);
                            break;
                        }
                    }
                    break;
                }
            }
    
            if (!candidateFound) {
                for (PoliticalParty party : entourages) {
                    if (party.getPartyNumber() == 91) {
                        party.setPartyVotes(party.getPartyVotes() + 1);
                        break;
                    }
                }
            }
        } 
        else {
            System.out.println("Número de voto inválido: " + voteNumber);
        }
    }
}
    