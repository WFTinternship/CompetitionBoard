package com.workfront.intern.cb.dao;

import com.workfront.intern.cb.common.Media;

import java.util.List;

public interface MediaDao {
    // CREATE
    boolean addPhoto(Media media);

    boolean addVideo(Media media);

    // READ
    List<Media> getMediaListByManager(int id);

    List<Media> getMediaListByTournament(int id);

    // UPDATE
    boolean updatePhoto(int id, Media media);

    boolean updateVideo(int id, Media media);

    // DELETE
    boolean deleteMedia(int id);

    boolean deleteAll();


}
