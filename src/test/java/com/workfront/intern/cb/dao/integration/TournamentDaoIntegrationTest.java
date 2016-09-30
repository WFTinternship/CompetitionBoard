package com.workfront.intern.cb.dao.integration;

import com.workfront.intern.cb.BaseTest;
import com.workfront.intern.cb.DataHelper;
import com.workfront.intern.cb.common.Manager;
import com.workfront.intern.cb.common.Tournament;
import com.workfront.intern.cb.common.TournamentFormat;
import com.workfront.intern.cb.common.custom.exception.FailedOperationException;
import com.workfront.intern.cb.common.custom.exception.ObjectNotFoundException;
import com.workfront.intern.cb.dao.ManagerDao;
import com.workfront.intern.cb.dao.TournamentDao;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.List;

import static org.junit.Assert.*;

@SuppressWarnings("SpringJavaAutowiredMembersInspection")
public class TournamentDaoIntegrationTest extends BaseTest {

    // DAO instances
    @Autowired
    private TournamentDao tournamentDao;
    @Autowired
    private ManagerDao managerDao;

    // Test helper objects
    private Manager testManager;
    private Tournament testTournament;

    @Before
    public void beforeTest() throws FailedOperationException, ObjectNotFoundException {
        // Delete all remaining objects
        cleanUp();

        // Initialize random manager instance
        testManager = DataHelper.createRandomManager();
        assertEquals(0, testManager.getId());

        // Save to DB
        managerDao.addManager(testManager);
        assertTrue(testManager.getId() > 0);

        // Initialize random tournament instance
        testTournament = DataHelper.createRandomTournament();
        testTournament.setManagerId(testManager.getId());
        assertEquals(0, testTournament.getTournamentId());

        // Save to DB
        tournamentDao.addTournament(testTournament);
        assertTrue(testTournament.getTournamentId() > 0);

        printTestCaseInfo(this.getClass(), testCase.getMethodName());
    }

    @After
    public void afterTest() throws FailedOperationException, ObjectNotFoundException {
        cleanUp();
    }

    private void cleanUp() throws FailedOperationException {
        tournamentDao.deleteAll();
        managerDao.deleteAll();
    }

    // region <TEST CASES>

    @Test(expected = ObjectNotFoundException.class)
    public void getTournamentById_notFound() throws Exception {
        // Testing method
        Tournament tournament = tournamentDao.getTournamentById(NON_EXISTING_ID);

        assertNull(MESSAGE_TEST_COMPLETED_ERROR, tournament);
    }

    @Test
    public void getTournamentById_found() throws Exception {
        int targetId;
        targetId = testTournament.getTournamentId();

        // Testing method
        Tournament tournament = tournamentDao.getTournamentById(targetId);

        assertNotNull(tournament);
        assertEquals(testTournament.getTournamentId(), tournament.getTournamentId());
        assertEquals(testTournament.getTournamentName(), tournament.getTournamentName());
        assertEquals(testTournament.getStartDate(), tournament.getStartDate());
        assertEquals(testTournament.getEndDate(), tournament.getEndDate());
        assertEquals(testTournament.getLocation(), tournament.getLocation());
        assertEquals(testTournament.getTournamentDescription(), tournament.getTournamentDescription());
        assertEquals(testTournament.getTournamentFormatId(), tournament.getTournamentFormatId());
        assertEquals(testTournament.getManagerId(), tournament.getManagerId());
    }

    @Test
    public void getTournamentListByName_emptyList() throws Exception {
        // Testing method
        tournamentDao.deleteTournamentById(testTournament.getTournamentId());

        String tournamentNameStr = testTournament.getTournamentName();
        List<Tournament> tournamentList = tournamentDao.getTournamentListByName(tournamentNameStr);

        assertNotNull(tournamentList);
        assertEquals(0, tournamentList.size());
    }

    @Test
    public void getTournamentListByName_found() throws Exception {
        String tournamentNameStr;
        tournamentNameStr = testTournament.getTournamentName();

        // Testing method
        List<Tournament> tournamentList = tournamentDao.getTournamentListByName(tournamentNameStr);

        assertNotNull(tournamentList);
        assertEquals(1, tournamentList.size());

        Tournament tournament = tournamentList.get(0);

        assertEquals(testTournament.getTournamentId(), tournament.getTournamentId());
        assertEquals(testTournament.getTournamentName(), tournament.getTournamentName());
        assertEquals(testTournament.getStartDate(), tournament.getStartDate());
        assertEquals(testTournament.getEndDate(), tournament.getEndDate());
        assertEquals(testTournament.getLocation(), tournament.getLocation());
        assertEquals(testTournament.getTournamentDescription(), tournament.getTournamentDescription());
        assertEquals(testTournament.getTournamentFormatId(), tournament.getTournamentFormatId());
        assertEquals(testTournament.getManagerId(), tournament.getManagerId());
    }

    @Test
    public void getTournamentList_emptyList() throws Exception {
        tournamentDao.deleteTournamentById(testTournament.getTournamentId());

        // Testing method
        List<Tournament> tournamentList = tournamentDao.getTournamentList();

        assertNotNull(tournamentList);
        assertEquals(0, tournamentList.size());
    }

    @Test
    public void getTournamentList_found() throws Exception {
        // Testing method
        List<Tournament> tournamentList;
        tournamentList = tournamentDao.getTournamentList();

        assertNotNull(tournamentList);
        assertEquals(1, tournamentList.size());

        Tournament tournament = tournamentList.get(0);

        assertEquals(testTournament.getTournamentId(), tournament.getTournamentId());
        assertEquals(testTournament.getTournamentName(), tournament.getTournamentName());
        assertEquals(testTournament.getStartDate(), tournament.getStartDate());
        assertEquals(testTournament.getEndDate(), tournament.getEndDate());
        assertEquals(testTournament.getLocation(), tournament.getLocation());
        assertEquals(testTournament.getTournamentDescription(), tournament.getTournamentDescription());
        assertEquals(testTournament.getTournamentFormatId(), tournament.getTournamentFormatId());
        assertEquals(testTournament.getManagerId(), tournament.getManagerId());
    }

    @Test
    public void getTournamentListByManager_emptyList() throws Exception {
        // Testing method
        tournamentDao.deleteTournamentById(testTournament.getTournamentId());

        int targetId;
        targetId = testManager.getId();
        List<Tournament> tournamentList = tournamentDao.getTournamentListByManager(targetId);

        assertNotNull(tournamentList);
        assertEquals(0, tournamentList.size());
    }

    @Test
    public void getTournamentListByManager_found() throws Exception {
        int targetId;
        targetId = testManager.getId();

        // Testing method
        List<Tournament> tournamentList = tournamentDao.getTournamentListByManager(targetId);

        assertNotNull(tournamentList);
        assertEquals(1, tournamentList.size());

        Tournament tournament = tournamentList.get(0);

        assertEquals(testTournament.getTournamentId(), tournament.getTournamentId());
        assertEquals(testTournament.getTournamentName(), tournament.getTournamentName());
        assertEquals(testTournament.getStartDate(), tournament.getStartDate());
        assertEquals(testTournament.getEndDate(), tournament.getEndDate());
        assertEquals(testTournament.getLocation(), tournament.getLocation());
        assertEquals(testTournament.getTournamentDescription(), tournament.getTournamentDescription());
        assertEquals(testTournament.getTournamentFormatId(), tournament.getTournamentFormatId());
        assertEquals(testTournament.getManagerId(), tournament.getManagerId());
    }

    @Test
    public void addTournament_created() throws Exception {
        // Initialize random tournament instance
        int targetId;
        targetId = testManager.getId();

        // Testing method
        Tournament tournament = DataHelper.createRandomTournament();
        tournament.setManagerId(targetId);
        assertEquals(0, tournament.getTournamentId());

        // Testing method
        tournamentDao.addTournament(tournament);
        assertTrue(tournament.getTournamentId() > 0);

        tournamentDao.deleteTournamentById(tournament.getTournamentId());
    }

    @Test
    public void updateTournament() throws Exception {
        // Tournament new data
        String nameUpdate = "UPDATED, THE BEST OF IF THE BEST";
        Timestamp startDateUpdate = Timestamp.valueOf("2020-08-08 10:00:00");
        Timestamp endDateUpdated = Timestamp.valueOf("2020-08-08 20:00:00");
        String locationUpdated = "Yerevan, Armenia";
        String descriptionUpdated = "UPDATED, Tournament begins gentlemen, welcome";

        int targetId = testManager.getId();
        int tournamentId = testTournament.getTournamentId();

        Tournament tournament = DataHelper.createRandomTournament();

        tournament.setTournamentId(tournamentId);
        tournament.setTournamentName(nameUpdate);
        tournament.setStartDate(startDateUpdate);
        tournament.setEndDate(endDateUpdated);
        tournament.setLocation(locationUpdated);
        tournament.setTournamentDescription(descriptionUpdated);
        tournament.setTournamentFormatId(TournamentFormat.ROUND_ROBBIN.getFormatId());
        tournament.setManagerId(targetId);

        tournamentDao.updateTournament(tournamentId, tournament);
        testTournament = tournamentDao.getTournamentById(tournamentId);

        assertEquals(testTournament.getTournamentId(), tournament.getTournamentId());
        assertEquals(testTournament.getTournamentName(), tournament.getTournamentName());
        assertEquals(testTournament.getStartDate(), tournament.getStartDate());
        assertEquals(testTournament.getEndDate(), tournament.getEndDate());
        assertEquals(testTournament.getLocation(), tournament.getLocation());
        assertEquals(testTournament.getTournamentDescription(), tournament.getTournamentDescription());
        assertEquals(testTournament.getTournamentFormatId(), tournament.getTournamentFormatId());
        assertEquals(testTournament.getManagerId(), tournament.getManagerId());
    }

    @Test(expected = ObjectNotFoundException.class)
    public void deleteTournamentById_notFound() throws Exception {
        tournamentDao.deleteTournamentById(NON_EXISTING_ID);
    }

    @Test
    public void deleteTournamentById_found() throws Exception {
        tournamentDao.deleteTournamentById(testTournament.getTournamentId());
    }

    @Test
    public void deleteAll() throws Exception {
        tournamentDao.deleteAll();

        List<Tournament> tournamentList = tournamentDao.getTournamentList();

        assertEquals(0, tournamentList.size());
    }

    // endregion
}