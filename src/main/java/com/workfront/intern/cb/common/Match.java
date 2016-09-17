package com.workfront.intern.cb.common;

public class Match {
    private int matchId;
    private int groupId;
    private int participantOneId;
    private int participantTwoId;
    private int scoreParticipantOne;
    private int scoreParticipantTwo;
    private String matchScore;

    public Match() {
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getParticipantOneId() {
        return participantOneId;
    }

    public void setParticipantOneId(int participantOneId) {
        this.participantOneId = participantOneId;
    }

    public int getParticipantTwoId() {
        return participantTwoId;
    }

    public void setParticipantTwoId(int participantTwoId) {
        this.participantTwoId = participantTwoId;
    }

    public int getScoreParticipantOne() {
        return scoreParticipantOne;
    }

    public void setScoreParticipantOne(int scoreParticipantOne) {
        this.scoreParticipantOne = scoreParticipantOne;
    }

    public int getScoreParticipantTwo() {
        return scoreParticipantTwo;
    }

    public void setScoreParticipantTwo(int scoreParticipantTwo) {
        this.scoreParticipantTwo = scoreParticipantTwo;
    }

    public String getMatchScore() {
        return matchScore;
    }

    public void setMatchScore(String matchScore) {
        this.matchScore = matchScore;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("matchId: ").append(matchId).append("\n");
        sb.append("groupId: ").append(getGroupId()).append("\n");
        sb.append("participantFirstId: ").append(getParticipantOneId()).append("\n");
        sb.append("participantSecondId: ").append(getParticipantTwoId()).append("\n");
        sb.append("scoreParticipantFirst: ").append(scoreParticipantOne).append("\n");
        sb.append("scoreParticipantSecond: ").append(scoreParticipantTwo).append("\n");
        sb.append("matchesScore: ").append(matchScore).append("\n");
        sb.append("*******************************************************************************************");
        sb.append("\n");

        return sb.toString();
    }
}