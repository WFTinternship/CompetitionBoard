package com.workfront.intern.cb.service.servicespringprofile.integration;

import com.workfront.intern.cb.BaseTest;
import com.workfront.intern.cb.common.Manager;
import com.workfront.intern.cb.common.Media;
import com.workfront.intern.cb.common.Tournament;
import com.workfront.intern.cb.service.ManagerService;
import com.workfront.intern.cb.service.MediaService;
import com.workfront.intern.cb.service.TournamentService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class MediaServiceIntegrationTest extends BaseTest {

    // DAO instances
    @Autowired
    private ManagerService managerService;

    @Autowired
    private TournamentService tournamentService;

    @Autowired
    private MediaService mediaService;

    // Test helper objects
    private Manager testManager;
    private Tournament testTournament;
    private Media testMedia;

    @Before
    public void beforeTest() throws Exception {
//        managerService = new ManagerServiceImpl();
//        tournamentService = new TournamentServiceImpl();
//        mediaService = new MediaServiceImpl();

        // Delete all remaining objects
        cleanUp();

        // Initialize random manager instance
        testManager = createRandomManager();
        assertEquals(0, testManager.getId());

        // Save to DB
        managerService.addManager(testManager);
        assertTrue(testManager.getId() > 0);

        // Initialize random tournament instance
        testTournament = createRandomTournament();
        testTournament.setManagerId(testManager.getId());
        assertEquals(0, testTournament.getTournamentId());

        // Save to DB
        tournamentService.addTournament(testTournament);
        assertTrue(testTournament.getTournamentId() > 0);

        // Initialize random media instance
        testMedia = createRandomPhotoMedia();
        testMedia.setManagerId(testManager.getId());
        testMedia.setTournamentId(testTournament.getTournamentId());
        assertEquals(0, testMedia.getMediaId());

        // Save to DB
        mediaService.addPhoto(testMedia);
        assertTrue(testTournament.getTournamentId() > 0);
    }

    @After
    public void afterTest() throws Exception {
        cleanUp();
    }

    private void cleanUp() throws Exception {
        mediaService.deleteAll();
        tournamentService.deleteAll();
        managerService.deleteAll();
    }

    // region <TEST CASES>

    @Test(expected = RuntimeException.class)
    public void getMediaById_notFound() throws Exception {
        // Testing method
        Media media = mediaService.getMediaById(NON_EXISTING_ID);
        assertNull(MESSAGE_TEST_COMPLETED_ERROR, media);
    }

    @Test
    public void getMediaById_found() throws Exception {
        int targetId;
        targetId = testMedia.getMediaId();

        // Testing method
        Media media = mediaService.getMediaById(targetId);

        assertNotNull(media);
        assertEquals(testMedia.getMediaId(), media.getMediaId());
        assertEquals(testMedia.getPhoto(), media.getPhoto());
        assertEquals(testMedia.getTournamentId(), media.getTournamentId());
        assertEquals(testMedia.getManagerId(), media.getManagerId());
    }

    @Test
    public void addPhoto_created() throws Exception {
        // Initialize random tournament instance
        int managerId;
        managerId = testManager.getId();
        int tournamentId = testTournament.getTournamentId();

        // Testing method
        Media media = createRandomPhotoMedia();
        media.setTournamentId(tournamentId);
        media.setManagerId(managerId);
        assertEquals(0, media.getMediaId());

        // Testing method
        mediaService.addPhoto(media);
        assertTrue(media.getMediaId() > 0);

        mediaService.deleteMediaById(media.getMediaId());
    }

    @Test
    public void addVideo_created() throws Exception {
        // Initialize random tournament instance
        int managerId;
        managerId = testManager.getId();
        int tournamentId = testTournament.getTournamentId();

        // Testing method
        Media media = createRandomVideoMedia();
        media.setTournamentId(tournamentId);
        media.setManagerId(managerId);
        assertEquals(0, media.getMediaId());

        // Testing method
        mediaService.addVideo(media);
        assertTrue(media.getMediaId() > 0);

        mediaService.deleteMediaById(media.getMediaId());
    }

    @Test
    public void getMediaByManagerList_emptyList() throws Exception {
        int managerId;
        managerId = testManager.getId();

        mediaService.deleteMediaById(testMedia.getMediaId());

        // Testing method
        List<Media> mediaList = mediaService.getMediaListByManager(managerId);

        assertNotNull(mediaList);
        assertEquals(0, mediaList.size());
    }

    @Test
    public void getMediaByManagerList_found() throws Exception {
        int managerId;
        managerId = testManager.getId();

        // Testing method
        List<Media> mediaList = mediaService.getMediaListByManager(managerId);

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
        mediaService.deleteMediaById(testMedia.getMediaId());

        // Testing method
        int tournamentId;
        tournamentId = testTournament.getTournamentId();
        List<Media> mediaList = mediaService.getMediaListByTournament(tournamentId);

        assertNotNull(mediaList);
        assertEquals(0, mediaList.size());
    }

    @Test
    public void getMediaByTournamentList_found() throws Exception {
        int tournamentId = testTournament.getTournamentId();
        List<Media> mediaList = mediaService.getMediaListByTournament(tournamentId);

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
        int managerId = testManager.getId();
        int tournamentId = testTournament.getTournamentId();
        int mediaId = testMedia.getMediaId();

        Media media = createRandomPhotoMedia();
        media.setPhoto("photo_updated");
        media.setTournamentId(tournamentId);
        media.setManagerId(managerId);

        // Updates specific tournament in db
        mediaService.updatePhoto(mediaId, media);
        List<Media> mediaList = mediaService.getMediaListByManager(managerId);
        testMedia = mediaList.get(0);

        assertEquals(testMedia.getPhoto(), media.getPhoto());
        assertEquals(testMedia.getVideo(), media.getVideo());
        assertEquals(testMedia.getTournamentId(), media.getTournamentId());
        assertEquals(testMedia.getManagerId(), media.getManagerId());
    }

    @Test
    public void updateVideo() throws Exception {
        int managerId = testManager.getId();
        int tournamentId = testTournament.getTournamentId();
        int mediaId = testMedia.getMediaId();

        Media media = createRandomVideoMedia();
        media.setVideo("video_updated");
        media.setTournamentId(tournamentId);
        media.setManagerId(managerId);

        // Updates specific tournament in db
        mediaService.updateVideo(mediaId, media);

        List<Media> mediaList = mediaService.getMediaListByManager(managerId);
        testMedia = mediaList.get(0);

        assertEquals(testMedia.getPhoto(), media.getPhoto());
        assertEquals(testMedia.getVideo(), media.getVideo());
        assertEquals(testMedia.getTournamentId(), media.getTournamentId());
        assertEquals(testMedia.getManagerId(), media.getManagerId());
    }

    @Test(expected = RuntimeException.class)
    public void deleteMediaById_notFound() throws Exception {
        mediaService.deleteMediaById(NON_EXISTING_ID);
    }

    @Test
    public void deleteMedia_deleted() throws Exception {
        mediaService.deleteMediaById(testMedia.getMediaId());
    }

    @Test
    public void deleteAll() throws Exception {
        mediaService.deleteAll();

        int managerId = testManager.getId();
        List<Media> mediaList = mediaService.getMediaListByManager(managerId);

        assertEquals(0, mediaList.size());
    }

    // endregion
}