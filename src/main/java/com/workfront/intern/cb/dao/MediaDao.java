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
    Media getMediaById(int id) throws ObjectNotFoundException, FailedOperationException;
    List<Media> getMediaListByManager(int managerId) throws FailedOperationException;
    List<Media> getMediaListByTournament(int tournamentId) throws FailedOperationException;

    // UPDATE
    void updatePhoto(int id, Media media) throws ObjectNotFoundException, FailedOperationException;
    void updateVideo(int id, Media media) throws ObjectNotFoundException, FailedOperationException;

    // DELETE
    void deleteMediaById(int id) throws ObjectNotFoundException, FailedOperationException;
    void deleteAll() throws FailedOperationException;
}
