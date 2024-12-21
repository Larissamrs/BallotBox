package com.ifpe.prova.entidades;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Candidate {
    private String candidateName;
    private int candidateNumber;
    private int votesNumber;
    private boolean elected;

    public boolean isElected() {
        return elected;
    }

    public void setElected(boolean elected) {
        this.elected = elected;
    }

    public Candidate() {
        
    }
    
    public Candidate(String candidateName, int candidateNumber, int votesNumber) {
        this.candidateName = candidateName;
        this.candidateNumber = candidateNumber;
        this.votesNumber = votesNumber;
    }
    public String getCandidateName() {
        return candidateName;
    }
    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }
    public int getCandidateNumber() {
        return candidateNumber;
    }
    public void setCandidateNumber(int candidateNumber) {
        this.candidateNumber = candidateNumber;
    }
    public int getVotesNumber() {
        return votesNumber;
    }
    public void setVotesNumber(int votesNumber) {
        this.votesNumber = votesNumber;
    }
}

    