package com.workfront.intern.cb.dao;

import com.workfront.intern.cb.common.Media;
import com.workfront.intern.cb.common.custom.exception.FailedOperationException;
import com.workfront.intern.cb.common.custom.exception.ObjectNotFoundException;

import java.util.List;

public interface MediaDao {

    // CREATE
    Media addPhoto(Media media) throws FailedOperationException;
    Media addVideo(Media media) throws FailedOperationException;

    // READ
    Media getMediaById(int id) throws FailedOperationException;
    List<Media> getMediaListByManager(int id) throws FailedOperationException;
    List<Media> getMediaListByTournament(int id) throws FailedOperationException;

    // UPDATE
    Media updatePhoto(int id, Media media) throws FailedOperationException;
    Media updateVideo(int id, Media media) throws FailedOperationException;

    // DELETE
    void deleteMediaById(int id) throws ObjectNotFoundException;
    void deleteAll() throws ObjectNotFoundException;
}
