package com.workfront.intern.cb.service;

import com.workfront.intern.cb.common.Media;

import java.util.List;

public interface MediaService {

    // CREATE
    Media addPhoto(Media media);
    Media addVideo(Media media);

    // READ
    Media getMediaById(int id);
    List<Media> getMediaListByManager(int id);
    List<Media> getMediaListByTournament(int id);

    // UPDATE
    void updatePhoto(int id, Media media);
    void updateVideo(int id, Media media);

    // DELETE
    void deleteMediaById(int id);
    void deleteAll();
}
