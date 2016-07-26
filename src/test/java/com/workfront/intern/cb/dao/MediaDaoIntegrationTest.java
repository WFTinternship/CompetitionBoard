package com.workfront.intern.cb.dao;

import com.workfront.intern.cb.BaseTest;
import com.workfront.intern.cb.common.Manager;
import com.workfront.intern.cb.common.Media;
import com.workfront.intern.cb.common.Tournament;
import com.workfront.intern.cb.common.custom.exception.FailedOperationException;
import com.workfront.intern.cb.common.custom.exception.ObjectNotFoundException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;
import java.util.List;

import static org.junit.Assert.*;

public class MediaDaoIntegrationTest extends BaseTest {

    // DAO instances
    private ManagerDao managerDao;
    private TournamentDao tournamentDao;
    private MediaDao mediaDao;

    // Test helper objects
    private Manager testManager;
    private Tournament testTournament;
    private Media testMedia;

    DataSource dataSource = DBManager.getDataSource();

    @Before
    public void beforeTest() throws FailedOperationException, ObjectNotFoundException {
        managerDao = new ManagerDaoImpl(dataSource);
        tournamentDao = new TournamentDaoImpl(dataSource);
        mediaDao = new MediaDaoImpl(dataSource);

        mediaDao.deleteAll();
        tournamentDao.deleteAll();
        managerDao.deleteAll();

        // Initialize random manager instance
        testManager = createRandomManager();
        assertEquals(0, testManager.getId());

        // Save to DB
        managerDao.addManager(testManager);
        assertTrue(testManager.getId() > 0);

        // Initialize random tournament instance
        testTournament = createRandomTournament();
        testTournament.setManagerId(testManager.getId());
        assertEquals(0, testTournament.getTournamentId());

        // Save to DB
        tournamentDao.addTournament(testTournament);
        assertTrue(testTournament.getTournamentId() > 0);

        // Initialize random media instance
        testMedia = createRandomPhotoMedia();
        testMedia.setManagerId(testManager.getId());
        testMedia.setTournamentId(testTournament.getTournamentId());
        assertEquals(0, testMedia.getMediaId());

        // Save to DB
        mediaDao.addPhoto(testMedia);
        assertTrue(testTournament.getTournamentId() > 0);
    }

    @After
    public void afterTest() throws FailedOperationException, ObjectNotFoundException {
        // Deleting 'media' of manager type field after passed test
        if (testMedia != null) {
            mediaDao.deleteMediaById(testMedia.getMediaId());
        } else {
            System.out.println("WARNING: testMedia was null after test execution");
        }

        // Deleting 'tournament' of manager type field after passed test
        if (testTournament != null) {
            tournamentDao.deleteTournamentById(testTournament.getTournamentId());
        } else {
            System.out.println("WARNING: testTournament was null after test execution");
        }

        // Deleting 'manager' of manager type field after passed test
        if (testManager != null) {
            managerDao.deleteManagerById(testManager.getId());
        } else {
            System.out.println("WARNING: testManager was null after test execution");
        }
    }

    // region <TEST CASES>

    @Test
    public void getMediaById_notFound() throws FailedOperationException {
        // Testing method
        Media media = mediaDao.getMediaById(NON_EXISTING_ID);

        assertNull(MESSAGE_TEST_COMPLETED_ERROR, media);
    }

    @Test
    public void getMediaById_found() throws FailedOperationException {
        int targetId = testMedia.getMediaId();

        // Testing method
        Media media = mediaDao.getMediaById(targetId);

        assertNotNull(media);
        assertEquals(testMedia.getMediaId(), media.getMediaId());
        assertEquals(testMedia.getPhoto(), media.getPhoto());
        assertEquals(testMedia.getTournamentId(), media.getTournamentId());
        assertEquals(testMedia.getManagerId(), media.getManagerId());
    }

    @Test
    public void addPhoto_created() throws FailedOperationException, ObjectNotFoundException {
        // Initialize random tournament instance
        int managerId = testManager.getId();
        int tournamentId = testTournament.getTournamentId();

        // Testing method
        Media media = createRandomPhotoMedia();
        media.setTournamentId(tournamentId);
        media.setManagerId(managerId);
        assertEquals(0, media.getMediaId());

        // Testing method
        mediaDao.addPhoto(media);
        assertTrue(media.getMediaId() > 0);

        mediaDao.deleteMediaById(media.getMediaId());
    }

    @Test
    public void addVideo_created() throws FailedOperationException, ObjectNotFoundException {
        // Initialize random tournament instance
        int managerId = testManager.getId();
        int tournamentId = testTournament.getTournamentId();

        // Testing method
        Media media = createRandomVideoMedia();
        media.setTournamentId(tournamentId);
        media.setManagerId(managerId);
        assertEquals(0, media.getMediaId());

        // Testing method
        mediaDao.addVideo(media);
        assertTrue(media.getMediaId() > 0);

        mediaDao.deleteMediaById(media.getMediaId());
    }

    @Test
    public void getMediaByManagerList_emptyList() throws ObjectNotFoundException, FailedOperationException {
        int managerId = testManager.getId();

        mediaDao.deleteMediaById(testMedia.getMediaId());

        // Testing method
        List<Media> mediaList = mediaDao.getMediaListByManager(managerId);

        assertNotNull(mediaList);
        assertEquals(0, mediaList.size());
    }

    @Test
    public void getMediaByManagerList_found() throws FailedOperationException {
        int managerId = testManager.getId();

        // Testing method
        List<Media> mediaList = mediaDao.getMediaListByManager(managerId);

        assertNotNull(mediaList);
        assertEquals(1, mediaList.size());

        Media media = mediaList.get(0);

        assertEquals(testMedia.getMediaId(), media.getMediaId());
        assertEquals(testMedia.getPhoto(), media.getPhoto());
        assertEquals(testMedia.getVideo(), media.getVideo());
        assertEquals(testMedia.getTournamentId(), media.getTournamentId());
    }

    @Test
    public void getMediaByTournamentList_emptyList() throws ObjectNotFoundException, FailedOperationException {
        mediaDao.deleteMediaById(testMedia.getMediaId());

        // Testing method
        int tournamentId = testTournament.getTournamentId();
        List<Media> mediaList = mediaDao.getMediaListByTournament(tournamentId);

        assertNotNull(mediaList);
        assertEquals(0, mediaList.size());
    }

    @Test
    public void getMediaByTournamentList_found() throws FailedOperationException {
        int tournamentId = testTournament.getTournamentId();
        List<Media> mediaList = mediaDao.getMediaListByTournament(tournamentId);

        assertNotNull(mediaList);
        assertEquals(1, mediaList.size());

        Media media = mediaList.get(0);

        assertEquals(testMedia.getMediaId(), media.getMediaId());
        assertEquals(testMedia.getPhoto(), media.getPhoto());
        assertEquals(testMedia.getVideo(), media.getVideo());
        assertEquals(testMedia.getTournamentId(), media.getTournamentId());
        assertEquals(testMedia.getMediaId(), media.getMediaId());
    }

    //TODO
    @Test
    public void updatePhoto() throws FailedOperationException {
        // Testing method
        int managerId = testManager.getId();
        int tournamentId = testTournament.getTournamentId();
        int mediaId = testMedia.getMediaId();

        Media media = createRandomPhotoMedia();
        media.setPhoto("photo_updated");
        media.setTournamentId(tournamentId);
        media.setManagerId(managerId);

        // Updates specific tournament in db
        mediaDao.updatePhoto(mediaId, media);
        List<Media> mediaList = mediaDao.getMediaListByManager(managerId);
        testMedia = mediaList.get(0);

//        assertTrue(updated);
        assertEquals(testMedia.getPhoto(), media.getPhoto());
        assertEquals(testMedia.getVideo(), media.getVideo());
        assertEquals(testMedia.getTournamentId(), media.getTournamentId());
        assertEquals(testMedia.getManagerId(), media.getManagerId());
    }

    //TODO
    @Test
    public void updateVideo() throws FailedOperationException {
        // Testing method
        int managerId = testManager.getId();
        int tournamentId = testTournament.getTournamentId();
        int mediaId = testMedia.getMediaId();

        Media media = createRandomVideoMedia();
        media.setVideo("video_updated");
        media.setTournamentId(tournamentId);
        media.setManagerId(managerId);

        // Updates specific tournament in db
        mediaDao.updateVideo(mediaId, media);
        List<Media> mediaList = mediaDao.getMediaListByManager(managerId);
        testMedia = mediaList.get(0);

        assertEquals(testMedia.getPhoto(), media.getPhoto());
        assertEquals(testMedia.getVideo(), media.getVideo());
        assertEquals(testMedia.getTournamentId(), media.getTournamentId());
        assertEquals(testMedia.getManagerId(), media.getManagerId());
    }

    //TODO
    @Test
    public void deleteMediaById_notFound() throws ObjectNotFoundException {
        mediaDao.deleteMediaById(NON_EXISTING_ID);

    }

    //TODO
    @Test
    public void deleteMedia_deleted() throws ObjectNotFoundException {
        mediaDao.deleteMediaById(testMedia.getMediaId());

    }

    //TODO
    @Test
    public void deleteAll() throws ObjectNotFoundException, FailedOperationException {
        mediaDao.deleteAll();

        int managerId = testManager.getId();
        List<Media> mediaList = mediaDao.getMediaListByManager(managerId);
        assertEquals(0, mediaList.size());
    }

    // endregion
}
