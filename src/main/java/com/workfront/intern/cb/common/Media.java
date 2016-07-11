package com.workfront.intern.cb.common;

public class Media {
    private int mediaId;
    private String photo;
    private String video;
    private Tournament tournament;
    private Manager manager;

    public Media() {
    }


    public Media(String photo, String video, Tournament tournament, Manager manager) {
        this.photo = photo;
        this.video = video;
        this.tournament = tournament;
        this.manager = manager;
    }

    public Media(int mediaId, String photo, String video, Tournament tournament, Manager manager) {
        this.mediaId = mediaId;
        this.photo = photo;
        this.video = video;
        this.tournament = tournament;
        this.manager = manager;
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

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
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
        sb.append("tournamentId: ").append(tournament.getTournamentId()).append("\n");
        sb.append("managerId: ").append(manager.getId()).append("\n");
        sb.append("*******************************************************************************************");
        sb.append("\n");

        return sb.toString();
    }
}
