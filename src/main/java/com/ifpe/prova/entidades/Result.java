package com.ifpe.prova.entidades;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Result {
    private List<Candidate> candidates = new ArrayList<>();
    private List<PoliticalParty> entourages = new ArrayList<>();


    public Result(List<Candidate> candidates, List<PoliticalParty> entourages) {
    this.candidates = candidates;
    this.entourages = entourages;
    }

    public List<Candidate> getCandidates() {
        return candidates;
    }


    public void setCandidates(List<Candidate> candidates) {
        this.candidates = candidates;
    }


    public List<PoliticalParty> getEntourages() {
        return entourages;
    }


    public void setEntourages(List<PoliticalParty> entourages) {
        this.entourages = entourages;
    }
}

    