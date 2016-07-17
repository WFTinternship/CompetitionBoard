package com.workfront.intern.cb;

import com.workfront.intern.cb.common.*;

import java.sql.Timestamp;
import java.util.Random;

public class BaseTest {

    protected final int NON_EXISTING_ID = 9999;

    protected final String NON_EXISTING_LOGIN = "vJRLG8Z523sajdsad02";

    protected final String GENERIC_PASSWORD = "123456";

    protected final String MESSAGE_TEST_COMPLETED_OK = "Test completed successfully!";

    protected final String MESSAGE_TEST_COMPLETED_ERROR = "Test completed with errors :(";

    protected static Random random = new Random();

    /**
     * Creates manager
     */
    protected static Manager createRandomManager() {
        String managerLoginRandom = "user_" + System.currentTimeMillis();
        String managerPassword = "123456";

        Manager testManager = new Manager();
        testManager.setLogin(managerLoginRandom);
        testManager.setPassword(managerPassword);

        return testManager;
    }

    /**
     * Creates tournament
     */
    protected static Tournament createRandomTournament() {
        Tournament testTournament = new Tournament();

        String tournamentName = "THE BEST OF IF THE BEST";
        Timestamp startDate = Timestamp.valueOf("2020-08-08 10:00:00");
        Timestamp endDate = Timestamp.valueOf("2020-08-08 20:00:00");
        String location = "Yerevan, Armenia";
        String tournamentDescription = "Tournament begins gentlemen, welcome";

        int formatIdx = 1 + random.nextInt(1);
        TournamentFormat tournamentFormat = TournamentFormat.getTournamentFormatById(formatIdx);

        // Sets specific data to testTournament
        testTournament.setTournamentName(tournamentName);
        testTournament.setStartDate(startDate);
        testTournament.setEndDate(endDate);
        testTournament.setLocation(location);
        testTournament.setTournamentDescription(tournamentDescription);
        testTournament.setTournamentFormatId(tournamentFormat.getFormatId());

        return testTournament;
    }

    /**
     * Creates member
     */
    protected static Member createRandomMember() {
        Member testMember = new Member();
        testMember.setName("name_");
        testMember.setSurName("surname_");
        testMember.setPosition("developer");
        testMember.setEmail(System.currentTimeMillis() + "@gmail.com");

        return testMember;
    }


    /**
     * Creates team
     */
    //todo


    /**
     * Creates photo media
     */
    protected static Media createRandomPhotoMedia() {
        Media media = new Media();
        media.setPhoto("photo_");

        return media;
    }

    /**
     * Creates photo media
     */
    protected static Media createRandomVideoMedia() {
        Media media = new Media();
        media.setVideo("video_");

        return media;
    }

    /**
     * Creates match
     */
    protected static Match createRandomMatch() {
        int participantOneId = 1;
        int participantTwoId = 2;
        int scoreParticipantOne = 10;
        int scoreParticipantTwo = 20;

        Match match = new Match();
        match.setParticipantOneId(participantOneId);
        match.setParticipantTwoId(participantTwoId);
        match.setScoreParticipantOne(scoreParticipantOne);
        match.setScoreParticipantOne(scoreParticipantTwo);

        return match;
    }

    /**
     * Creates group
     */
    protected static Group createRandomGroup() {
        int participantsCount = 30;
        int round = 5;
        int nextRoundParticipants = 30;

        Group group = new Group();
        group.setParticipantsCount(participantsCount);
        group.setRound(round);
        group.setNextRoundParticipnats(nextRoundParticipants);

        return group;
    }
}
