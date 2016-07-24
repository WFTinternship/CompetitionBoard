package com.workfront.intern.cb.dao;

import com.workfront.intern.cb.BaseTest;
import com.workfront.intern.cb.common.Manager;
import com.workfront.intern.cb.common.Media;
import com.workfront.intern.cb.common.Tournament;
import com.workfront.intern.cb.common.util.StringHelper;
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
    public void beforeTest() {
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
    public void afterTest() {
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
    public void getMediaById_notFound() {
        // Testing method
        Media media = mediaDao.getMediaById(NON_EXISTING_ID);

        assertNull(MESSAGE_TEST_COMPLETED_ERROR, media);
    }

    @Test
    public void getMediaById_found() {
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
    public void addPhoto_created() {
        // Initialize random tournament instance
        int managerId = testManager.getId();
        int tournamentId = testTournament.getTournamentId();

        // Testing method
        Media media = createRandomPhotoMedia();
        media.setTournamentId(tournamentId);
        media.setManagerId(managerId);
        assertEquals(0, media.getMediaId());

        // Testing method
        boolean added = mediaDao.addPhoto(media);

        assertTrue(added);
        assertTrue(media.getMediaId() > 0);

        mediaDao.deleteMediaById(media.getMediaId());
    }

    @Test
    public void addVideo_created() {
        // Initialize random tournament instance
        int managerId = testManager.getId();
        int tournamentId = testTournament.getTournamentId();

        // Testing method
        Media media = createRandomVideoMedia();
        media.setTournamentId(tournamentId);
        media.setManagerId(managerId);
        assertEquals(0, media.getMediaId());

        // Testing method
        boolean added = mediaDao.addVideo(media);

        assertTrue(added);
        assertTrue(media.getMediaId() > 0);

        mediaDao.deleteMediaById(media.getMediaId());
    }

    @Test
    public void getMediaByManagerList_emptyList() {
        int managerId = testManager.getId();

        boolean deleted = mediaDao.deleteMediaById(testMedia.getMediaId());
        assertTrue(deleted);

        // Testing method
        List<Media> mediaList = mediaDao.getMediaListByManager(managerId);

        assertNotNull(mediaList);
        assertEquals(0, mediaList.size());
    }

    @Test
    public void getMediaByManagerList_found() {
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
    public void getMediaByTournamentList_emptyList() {
        boolean deleted = mediaDao.deleteMediaById(testMedia.getMediaId());

        assertTrue(deleted);

        // Testing method
        int tournamentId = testTournament.getTournamentId();
        List<Media> mediaList = mediaDao.getMediaListByTournament(tournamentId);

        assertNotNull(mediaList);
        assertEquals(0, mediaList.size());
    }

    @Test
    public void getMediaByTournamentList_found() {
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

    @Test
    public void updatePhoto() {
        // Testing method
        int managerId = testManager.getId();
        int tournamentId = testTournament.getTournamentId();
        int mediaId = testMedia.getMediaId();

        Media media = createRandomPhotoMedia();
        media.setPhoto("photo_updated");
        media.setTournamentId(tournamentId);
        media.setManagerId(managerId);

        // Updates specific tournament in db
        boolean updated = mediaDao.updatePhoto(mediaId, media);
        List<Media> mediaList = mediaDao.getMediaListByManager(managerId);
        testMedia = mediaList.get(0);

        assertTrue(updated);
        assertEquals(testMedia.getPhoto(), media.getPhoto());
        assertEquals(testMedia.getVideo(), media.getVideo());
        assertEquals(testMedia.getTournamentId(), media.getTournamentId());
        assertEquals(testMedia.getManagerId(), media.getManagerId());
    }

    @Test
    public void updateVideo() {
        // Testing method
        int managerId = testManager.getId();
        int tournamentId = testTournament.getTournamentId();
        int mediaId = testMedia.getMediaId();

        Media media = createRandomVideoMedia();
        media.setVideo("video_updated");
        media.setTournamentId(tournamentId);
        media.setManagerId(managerId);

        // Updates specific tournament in db
        boolean updated = mediaDao.updateVideo(mediaId, media);
        List<Media> mediaList = mediaDao.getMediaListByManager(managerId);
        testMedia = mediaList.get(0);

        assertTrue(updated);
        assertEquals(testMedia.getPhoto(), media.getPhoto());
        assertEquals(testMedia.getVideo(), media.getVideo());
        assertEquals(testMedia.getTournamentId(), media.getTournamentId());
        assertEquals(testMedia.getManagerId(), media.getManagerId());
    }

    @Test
    public void deleteMediaById_notFound() {
        boolean deleted = mediaDao.deleteMediaById(NON_EXISTING_ID);

        assertFalse(deleted);
    }

    @Test
    public void deleteMedia_deleted() {
        boolean deleted = mediaDao.deleteMediaById(testMedia.getMediaId());

        assertTrue(deleted);
    }

    @Test
    public void deleteAll() {
        boolean deleteAll = mediaDao.deleteAll();
        assertTrue(deleteAll);

        int managerId = testManager.getId();
        List<Media> mediaList = mediaDao.getMediaListByManager(managerId);
        assertEquals(0, mediaList.size());
    }

    // endregion
}
