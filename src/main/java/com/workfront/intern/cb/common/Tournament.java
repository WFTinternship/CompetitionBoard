package com.workfront.intern.cb.common;

import java.sql.Timestamp;

public class Tournament {
    private int tournamentId;
    private String tournamentName;
    private Timestamp startDate;
    private Timestamp endDate;
    private String location;
    private String tournamentDescription;
    private int tournamentFormatId;
    private int managerId;

    public Tournament() {
    }

    public int getTournamentFormatId() {
        return tournamentFormatId;
    }

    public void setTournamentFormatId(int tournamentFormatId) {
        this.tournamentFormatId = tournamentFormatId;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public int getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(int tournamentId) {
        this.tournamentId = tournamentId;
    }

    public String getTournamentName() {
        return tournamentName;
    }

    public void setTournamentName(String tournamentName) {
        this.tournamentName = tournamentName;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTournamentDescription() {
        return tournamentDescription;
    }

    public void setTournamentDescription(String tournamentDescription) {
        this.tournamentDescription = tournamentDescription;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("tournamentId: ").append(tournamentId).append("\n");
        sb.append("tournamentName: ").append(tournamentName).append("\n");
        sb.append("startDate: ").append(startDate).append("\n");
        sb.append("endDate: ").append(endDate).append("\n");
        sb.append("location: ").append(location).append("\n");
        sb.append("tournamentDescription: ").append(tournamentDescription).append("\n");
//        sb.append("tournamentFormatId: ").append(tournamentFormat.getFormatId()).append("\n");
        sb.append("*******************************************************************************************");
        sb.append("\n");

        return sb.toString();
    }
}
