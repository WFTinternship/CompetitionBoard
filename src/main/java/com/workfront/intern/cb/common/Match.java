package com.workfront.intern.cb.common;

public class Match {
    private int matchId;
    private Group group;
    private Participant participantOneId;
    private Participant participantTwoId;
    private int scoreParticipantOne;
    private int scoreParticipantTwo;

    public Match() {
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Participant getParticipantOneId() {
        return participantOneId;
    }

    public void setParticipantOneId(Participant participantOneId) {
        this.participantOneId = participantOneId;
    }

    public Participant getParticipantTwoId() {
        return participantTwoId;
    }

    public void setParticipantTwoId(Participant participantTwoId) {
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("matchId: ").append(matchId).append("\n");
        sb.append("groupId: ").append(group.getGroupId()).append("\n");
        sb.append("participantFirstId: ").append(participantOneId.getId()).append("\n");
        sb.append("participantSecondId: ").append(participantTwoId.getId()).append("\n");
        sb.append("scoreParticipantFirst: ").append(scoreParticipantOne).append("\n");
        sb.append("scoreParticipantSecond: ").append(scoreParticipantTwo).append("\n");
        sb.append("*******************************************************************************************");
        sb.append("\n");

        return sb.toString();
    }
}
