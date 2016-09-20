package com.workfront.intern.cb.dao.integration;

import com.workfront.intern.cb.BaseTest;
import com.workfront.intern.cb.common.Manager;
import com.workfront.intern.cb.common.Media;
import com.workfront.intern.cb.common.Tournament;
import com.workfront.intern.cb.common.custom.exception.ObjectNotFoundException;
import com.workfront.intern.cb.dao.ManagerDao;
import com.workfront.intern.cb.dao.MediaDao;
import com.workfront.intern.cb.dao.TournamentDao;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

@SuppressWarnings("SpringJavaAutowiredMembersInspection")
public class MediaDaoIntegrationTest extends BaseTest {

    // DAO instances
    @Autowired
    private MediaDao mediaDao;
    @Autowired
    private ManagerDao managerDao;
    @Autowired
    private TournamentDao tournamentDao;

    // Test helper objects
    private Manager testManager;
    private Tournament testTournament;
    private Media testMedia;

    @Before
    public void beforeTest() throws Exception {
        // Delete all remaining objects
        cleanUp();

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
    public void afterTest() throws Exception {
        cleanUp();
    }

    private void cleanUp() throws Exception {
        mediaDao.deleteAll();
        tournamentDao.deleteAll();
        managerDao.deleteAll();
    }

    // region <TEST CASES>

    @Test(expected = ObjectNotFoundException.class)
    public void getMediaById_notFound() throws Exception {
        // Testing method
        Media media = mediaDao.getMediaById(NON_EXISTING_ID);
        assertNull(MESSAGE_TEST_COMPLETED_ERROR, media);
    }

    @Test
    public void getMediaById_found() throws Exception {
        int id = testMedia.getMediaId();

        // Testing method
        Media media = mediaDao.getMediaById(id);

        assertNotNull(media);
        assertEquals(testMedia.getMediaId(), media.getMediaId());
        assertEquals(testMedia.getPhoto(), media.getPhoto());
        assertEquals(testMedia.getTournamentId(), media.getTournamentId());
        assertEquals(testMedia.getManagerId(), media.getManagerId());
    }

    @Test
    public void addPhoto_created() throws Exception {
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
    public void addVideo_created() throws Exception {
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
    public void getMediaByManagerList_emptyList() throws Exception {
        int managerId = testManager.getId();

        mediaDao.deleteMediaById(testMedia.getMediaId());

        // Testing method
        List<Media> mediaList = mediaDao.getMediaListByManager(managerId);

        assertNotNull(mediaList);
        assertEquals(0, mediaList.size());
    }

    @Test
    public void getMediaByManagerList_found() throws Exception {
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
    public void getMediaByTournamentList_emptyList() throws Exception {
        mediaDao.deleteMediaById(testMedia.getMediaId());

        // Testing method
        int tournamentId = testTournament.getTournamentId();
        List<Media> mediaList = mediaDao.getMediaListByTournament(tournamentId);

        assertNotNull(mediaList);
        assertEquals(0, mediaList.size());
    }

    @Test
    public void getMediaByTournamentList_found() throws Exception {
        int tournamentId;
        tournamentId = testTournament.getTournamentId();
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
    public void updatePhoto() throws Exception {
        // Testing method
        int managerId;
        managerId = testManager.getId();
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

        assertEquals(testMedia.getPhoto(), media.getPhoto());
        assertEquals(testMedia.getVideo(), media.getVideo());
        assertEquals(testMedia.getTournamentId(), media.getTournamentId());
        assertEquals(testMedia.getManagerId(), media.getManagerId());
    }

    @Test
    public void updateVideo() throws Exception {
        int managerId;
        managerId = testManager.getId();
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

    @Test(expected = ObjectNotFoundException.class)
    public void deleteMediaById_notFound() throws Exception {
        mediaDao.deleteMediaById(NON_EXISTING_ID);
    }

    @Test
    public void deleteMedia_deleted() throws Exception {
        mediaDao.deleteMediaById(testMedia.getMediaId());
    }

    @Test
    public void deleteAll() throws Exception {
        mediaDao.deleteAll();

        int managerId = testManager.getId();
        List<Media> mediaList = mediaDao.getMediaListByManager(managerId);

        assertEquals(0, mediaList.size());
    }

    // endregion
}
