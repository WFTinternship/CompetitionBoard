package com.workfront.intern.cb.dao;

import com.workfront.intern.cb.common.Media;

import java.util.List;

public interface MediaDao {
    List<Media> getMediaByManager(int id);

    List<Media> getMediaByTournament(int id);

    boolean addPhoto(Media media);

    boolean addVideo(Media media);

    boolean deleteMedia(Media media);
}
