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

    @Override
    public Media addVideo(Media media) throws FailedOperationException {
        return null;
    }

    @Override
    public Media getMediaById(int id) throws FailedOperationException, ObjectNotFoundException {
        return null;
    }

    @Override
    public List<Media> getMediaListByManager(int id) throws FailedOperationException {
        return null;
    }

    @Override
    public List<Media> getMediaListByTournament(int id) throws FailedOperationException {
        return null;
    }

    @Override
    public void updatePhoto(int id, Media media) throws FailedOperationException {

    }

    @Override
    public void updateVideo(int id, Media media) throws FailedOperationException {

    }

    @Override
    public void deleteMediaById(int id) throws ObjectNotFoundException, FailedOperationException {

    }

    @Override
    public void deleteAll() throws FailedOperationException {

    }
}
