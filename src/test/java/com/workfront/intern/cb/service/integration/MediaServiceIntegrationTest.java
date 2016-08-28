package com.workfront.intern.cb.service.integration;

import com.workfront.intern.cb.BaseTest;
import com.workfront.intern.cb.common.Manager;
import com.workfront.intern.cb.common.Media;
import com.workfront.intern.cb.common.Tournament;
import com.workfront.intern.cb.common.custom.exception.ObjectNotFoundException;
import com.workfront.intern.cb.service.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

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
        managerService = new ManagerServiceImpl();
        tournamentService = new TournamentServiceImpl();
        mediaService = new MediaServiceImpl();

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

    @Ignore
    @Test
    public void getMediaById_found() throws Exception {
    }

    @Ignore
    @Test
    public void addPhoto_created() throws Exception {
    }

    @Ignore
    @Test
    public void addVideo_created() throws Exception {
    }

    @Ignore
    @Test
    public void getMediaByManagerList_emptyList() throws Exception {
    }

    @Ignore
    @Test
    public void getMediaByManagerList_found() throws Exception {
    }

    @Ignore
    @Test
    public void getMediaByTournamentList_emptyList() throws Exception {
    }

    @Ignore
    @Test
    public void getMediaByTournamentList_found() throws Exception {
    }

    @Ignore
    @Test
    public void updatePhoto() throws Exception {
    }

    @Ignore
    @Test
    public void updateVideo() throws Exception {
    }

    @Ignore
    @Test(expected = ObjectNotFoundException.class)
    public void deleteMediaById_notFound() throws Exception {
    }

    @Ignore
    @Test
    public void deleteMedia_deleted() throws Exception {
    }

    @Ignore
    @Test
    public void deleteAll() throws Exception {
    }

    // endregion
}