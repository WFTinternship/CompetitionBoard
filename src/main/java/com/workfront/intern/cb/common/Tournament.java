package com.workfront.intern.cb.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Tournament {
    private int tournamentId;
    private String tournamentName;
    private Date startDate;
    private Date endDate;
    private String location;
    private String tournamentDescription;
    private TournamentFormat tournamentFormat;
    private Manager manager;
    private int manager_id;

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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
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

    public TournamentFormat getTournamentFormat() {
        return tournamentFormat;
    }

    public void setTournamentFormat(TournamentFormat tournamentFormat) {
        this.tournamentFormat = tournamentFormat;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public int getManager_id() {
        return manager_id;
    }

    public void setManager_id(int manager_id) {
        this.manager_id = manager_id;
    }

    private static String dateFormated(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
        return dateFormat.format(date);
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
        sb.append("tournamentFormatId: ").append(tournamentFormat.getFormatId()).append("\n");
        sb.append("tournamentManagerId: ").append(manager.getId()).append("\n");
        sb.append("*******************************************************************************************");
        sb.append("\n");

        return sb.toString();
    }

    public void setManager(int manager_id) {
        this.manager_id=manager_id;
    }
}
