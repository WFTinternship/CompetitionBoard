package com.workfront.intern.cb.dao;

import com.workfront.intern.cb.common.Media;

import java.util.List;

public interface MediaDao {
    // CREATE
    boolean addPhoto(Media media);

    boolean addVideo(Media media);

    // READ
    List<Media> getMediaByManager(int id);

    List<Media> getMediaByTournament(int id);

    // UPDATE
    boolean updatePhoto(Media media);

    boolean updateVideo(Media media);

    // DELETE
    boolean deleteMedia(int id);
    boolean deleteAll();


}
