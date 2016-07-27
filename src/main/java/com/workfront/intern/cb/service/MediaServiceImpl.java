package com.workfront.intern.cb.service;

import com.workfront.intern.cb.common.Media;
import com.workfront.intern.cb.common.custom.exception.FailedOperationException;
import com.workfront.intern.cb.common.custom.exception.ObjectNotFoundException;
import com.workfront.intern.cb.dao.*;
import org.apache.log4j.Logger;

import java.util.List;

public class MediaServiceImpl implements MediaService {
    private static final Logger LOG = Logger.getLogger(MediaServiceImpl.class);

    private MediaDao mediaDao = new MediaDaoImpl(DBManager.getDataSource());

    /**
     * Adds new photo media in db
     */
    @Override
    public Media addPhoto(Media media) throws FailedOperationException {
        try {
            return mediaDao.addPhoto(media);
        } catch (FailedOperationException e) {
            LOG.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Adds new video media in db
     */
    @Override
    public Media addVideo(Media media) throws FailedOperationException {
        try {
            return mediaDao.addVideo(media);
        } catch (FailedOperationException e) {
            LOG.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Gets media by id
     */
    @Override
    public Media getMediaById(int id) throws FailedOperationException, ObjectNotFoundException {
        try {
            return mediaDao.getMediaById(id);
        } catch (ObjectNotFoundException e) {
            throw new RuntimeException(String.format("Media instance with id=%s not found", id));
        } catch (FailedOperationException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Returns all media by manager id
     */
    @Override
    public List<Media> getMediaListByManager(int id) throws FailedOperationException {
        try {
            return mediaDao.getMediaListByManager(id);
        } catch (FailedOperationException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Returns all media by tournament id
     */
    @Override
    public List<Media> getMediaListByTournament(int id) throws FailedOperationException {
        try {
            return mediaDao.getMediaListByTournament(id);
        } catch (FailedOperationException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Updates existing photo in db
     */
    @Override
    public void updatePhoto(int id, Media media) throws FailedOperationException {
        try {
            mediaDao.updatePhoto(id, media);
        } catch (FailedOperationException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Updates existing video in db
     */
    @Override
    public void updateVideo(int id, Media media) throws FailedOperationException {
        try {
            mediaDao.updateVideo(id, media);
        } catch (FailedOperationException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Deletes manager by id
     */
    @Override
    public void deleteMediaById(int id) throws ObjectNotFoundException, FailedOperationException {
        try {
            mediaDao.deleteMediaById(id);
        } catch (ObjectNotFoundException e) {
            throw new RuntimeException(String.format("Media instance with id=%s not found", id));
        } catch (FailedOperationException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Removes all managers
     */
    @Override
    public void deleteAll() throws FailedOperationException {
        try {
            mediaDao.deleteAll();
        } catch (FailedOperationException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
