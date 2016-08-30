package com.workfront.intern.cb.service.servicespringprofile.unit;

import com.workfront.intern.cb.BaseTest;
import com.workfront.intern.cb.common.Media;
import com.workfront.intern.cb.common.custom.exception.FailedOperationException;
import com.workfront.intern.cb.dao.MediaDao;
import com.workfront.intern.cb.dao.MediaDaoImpl;
import com.workfront.intern.cb.service.MediaService;
import com.workfront.intern.cb.service.MediaServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.Whitebox;

import javax.sql.DataSource;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SuppressWarnings("unchecked")
public class MediaServiceUnitTest extends BaseTest {
    protected DataSource dataSource;
    private MediaDao mediaDao;
    private Media testMediaPhoto;
    private Media testMediaVideo;
    private MediaService mediaService;

    @Before
    public void beforeTest() throws Exception {
        mediaDao = Mockito.mock(MediaDaoImpl.class);
        mediaService = new MediaServiceImpl();
        Whitebox.setInternalState(mediaService, "mediaDao", mediaDao);

        testMediaPhoto = createRandomPhotoMedia();
        testMediaVideo = createRandomVideoMedia();
    }

    @After
    public void afterTest() {
    }

    // region <TEST CASES>

    @Test(expected = RuntimeException.class)
    public void addPhoto_DAOError() throws Exception {
        when(mediaDao.addPhoto(testMediaPhoto)).thenThrow(FailedOperationException.class);
        mediaService.addPhoto(testMediaPhoto);
    }

    @Test()
    public void addPhoto_DAOSuccess() throws Exception {
        mediaService.addPhoto(testMediaPhoto);
        verify(mediaDao).addPhoto(testMediaPhoto);
    }

    @Test(expected = RuntimeException.class)
    public void addVideo_DAOError() throws Exception {
        when(mediaDao.addVideo(testMediaVideo)).thenThrow(FailedOperationException.class);
        mediaService.addVideo(testMediaVideo);
    }

    @Test()
    public void addVideo_DAOSuccess() throws Exception {
        mediaService.addVideo(testMediaVideo);
        verify(mediaDao).addVideo(testMediaVideo);
    }

    @Test(expected = RuntimeException.class)
    public void getMediaById_DAOError() throws Exception {
        when(mediaDao.getMediaById(NON_EXISTING_ID)).thenThrow(FailedOperationException.class);
        mediaService.getMediaById(NON_EXISTING_ID);
    }

    @Test()
    public void getMediaById_DAOSuccess() throws Exception {
        mediaService.getMediaById(NON_EXISTING_ID);
        verify(mediaDao).getMediaById(NON_EXISTING_ID);
    }

    @Test(expected = RuntimeException.class)
    public void getMediaListByManager_DAOError() throws Exception {
        when(mediaDao.getMediaListByManager(NON_EXISTING_ID)).thenThrow(FailedOperationException.class);
        mediaService.getMediaListByManager(NON_EXISTING_ID);
    }

    @Test()
    public void getMediaListByManager_DAOSuccess() throws Exception {
        mediaService.getMediaListByManager(NON_EXISTING_ID);
        verify(mediaDao).getMediaListByManager(NON_EXISTING_ID);
    }

    @Test(expected = RuntimeException.class)
    public void getMediaListByTournament_DAOError() throws Exception {
        when(mediaDao.getMediaListByTournament(NON_EXISTING_ID)).thenThrow(FailedOperationException.class);
        mediaService.getMediaListByTournament(NON_EXISTING_ID);
    }

    @Test()
    public void getMediaListByTournament_DAOSuccess() throws Exception {
        mediaService.getMediaListByTournament(NON_EXISTING_ID);
        verify(mediaDao).getMediaListByTournament(NON_EXISTING_ID);
    }

    @Test(expected = RuntimeException.class)
    public void updatePhoto_DAOError() throws Exception {
        doThrow(FailedOperationException.class).when(mediaDao).updatePhoto(NON_EXISTING_ID, testMediaPhoto);
        mediaService.updatePhoto(NON_EXISTING_ID, testMediaPhoto);
    }

    @Test()
    public void updatePhoto_DAOSuccess() throws Exception {
        mediaService.updatePhoto(NON_EXISTING_ID, testMediaPhoto);
        verify(mediaDao).updatePhoto(NON_EXISTING_ID, testMediaPhoto);
    }

    @Test(expected = RuntimeException.class)
    public void updateVideo_DAOError() throws Exception {
        doThrow(FailedOperationException.class).when(mediaDao).updateVideo(NON_EXISTING_ID, testMediaVideo);
        mediaService.updateVideo(NON_EXISTING_ID, testMediaVideo);
    }

    @Test()
    public void updateVideo_DAOSuccess() throws Exception {
        mediaService.updateVideo(NON_EXISTING_ID, testMediaVideo);
        verify(mediaDao).updateVideo(NON_EXISTING_ID, testMediaVideo);
    }

    @Test(expected = RuntimeException.class)
    public void deleteMediaById_DAOError() throws Exception {
        doThrow(FailedOperationException.class).when(mediaDao).deleteMediaById(NON_EXISTING_ID);
        mediaService.deleteMediaById(NON_EXISTING_ID);
    }

    @Test()
    public void deleteMediaById_DAOSuccess() throws Exception {
        mediaService.deleteMediaById(NON_EXISTING_ID);
        mediaService.deleteMediaById(NON_EXISTING_ID);
    }

    @Test(expected = RuntimeException.class)
    public void deleteAll_DAOError() throws Exception {
        doThrow(FailedOperationException.class).when(mediaDao).deleteAll();
        mediaService.deleteAll();
    }

    @Test()
    public void deleteAll_DAOSuccess() throws Exception {
        mediaService.deleteAll();
        verify(mediaDao).deleteAll();
    }

    // endregion
}