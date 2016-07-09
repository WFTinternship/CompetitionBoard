package com.workfront.intern.cb.common;

public class Media {
    private int mediaId;
    private String photo;
    private String video;
    private int tournamentId;
    private Manager manager;

    public Media() {
    }

    public int getMediaId() {
        return mediaId;
    }

    public void setMediaId(int mediaId) {
        this.mediaId = mediaId;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public int getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(int tournamentId) {
        this.tournamentId = tournamentId;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("mediaId: ").append(mediaId).append("\n");
        sb.append("photo: ").append(photo).append("\n");
        sb.append("video: ").append(video).append("\n");
        sb.append("tournamentId: ").append(tournamentId).append("\n");
        sb.append("managerId: ").append(manager.getId()).append("\n");
        sb.append("*******************************************************************************************");
        sb.append("\n");

        return sb.toString();
    }
}
