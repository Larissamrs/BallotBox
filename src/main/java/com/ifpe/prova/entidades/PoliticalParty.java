package com.ifpe.prova.entidades;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PoliticalParty {
    private String partyName;
    private String partyAcronym;
    private int partyNumber;
    private int partyVotes;

    public String getPartyAcronym() {
        return partyAcronym;
    }

    public void setPartyAcronym(String partyAcronym) {
        this.partyAcronym = partyAcronym;
    }   
    
    public PoliticalParty() {
    }

    public PoliticalParty(String partyName, String partyAcronym, int partyNumber, int partyVotes) {
        this.partyName = partyName;
        this.partyAcronym = partyAcronym;
        this.partyNumber = partyNumber;
        this.partyVotes = partyVotes;
    }

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

    public int getPartyNumber() {
        return partyNumber;
    }

    public void setPartyNumber(int partyNumber) {
        this.partyNumber = partyNumber;
    }

    public int getPartyVotes() {
        return partyVotes;
    }

    public void setPartyVotes(int partyVotes) {
        this.partyVotes = partyVotes;
    }
}