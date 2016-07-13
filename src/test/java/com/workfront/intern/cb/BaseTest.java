package com.workfront.intern.cb;

import com.workfront.intern.cb.common.Manager;
import com.workfront.intern.cb.common.Tournament;
import com.workfront.intern.cb.common.TournamentFormat;

import java.sql.Timestamp;

public class BaseTest {

    protected final int NON_EXISTING_ID = 9999;

    protected final String NON_EXISTING_LOGIN = "vJRLG8Z523sajdsad02";

    protected final String GENERIC_PASSWORD = "123456";

    protected final String MESSAGE_TEST_COMPLETED_OK = "Test completed successfully!";

    protected final String MESSAGE_TEST_COMPLETED_ERROR = "Test completed with errors :(";


    /**
     * Creates manager
     */
    protected static Manager createRandomManager() {
        //TODO change names of fields to small
        String MANAGER_LOGIN = "user_test";
        String MANAGER_PASSWORD = "123456";

        Manager testManager = new Manager();
        testManager.setLogin(MANAGER_LOGIN);
        testManager.setPassword(MANAGER_PASSWORD);

        return testManager;
    }

    /**
     * Creates tournament
     */
    protected static Tournament createRandomTournament() {
        Tournament testTournament = new Tournament();

        //TODO change names of fields to small
        String tournamentName = "THE BEST OF IF THE BEST";
        Timestamp startDate = Timestamp.valueOf("2020-08-08 10:00:00");
        Timestamp endDate = Timestamp.valueOf("2020-08-08 20:00:00");
        String location = "Yerevan, Armenia";
        String tournamentDescription = "Tournament begins gentlemen, welcome";
        int tournamentFormatId = TournamentFormat.OLYMPIC.getFormatId();
        int managerId = createRandomManager().getId();

        testTournament.setTournamentName(tournamentName);
        testTournament.setStartDate(startDate);
        testTournament.setEndDate(endDate);
        testTournament.setLocation(location);
        testTournament.setTournamentDescription(tournamentDescription);
        testTournament.setTournamentFormatId(tournamentFormatId);
        testTournament.setManagerId(managerId);


        return testTournament;
    }


}
