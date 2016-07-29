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
        String managerLoginRandom = randomStringGenerate();
        String managerPassword = randomStringGenerate();

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
        testMember.setAvatar(randomStringGenerate());
        testMember.setParticipantInfo(randomStringGenerate());
        testMember.setName(randomStringGenerate());
        testMember.setSurName(randomStringGenerate());
        testMember.setEmail(randomStringGenerate());
        testMember.setPosition(randomStringGenerate());

        return testMember;
    }

    /**
     * Creates team
     */
    protected static Team createRandomTeam() {
        Team testTeam = new Team();
        testTeam.setAvatar("avatar");
        testTeam.setParticipantInfo("bla bla bla");
        testTeam.setTeamName("team name");

        return testTeam;
    }

    /**
     * Creates media photo
     */
    protected static Media createRandomPhotoMedia() {
        Media media = new Media();
        media.setPhoto("photo_");
        media.setVideo(null);

        return media;
    }

    /**
     * Creates media video
     */
    protected static Media createRandomVideoMedia() {
        Media media = new Media();
        media.setPhoto(null);
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
    private static String randomStringGenerate() {
        // letter 'a'
        int smallCaseLeft = 97;

        // letter 'z'
        int smallCaseRight = 122;

        int generateStringLength = 10;

        StringBuilder sb = new StringBuilder(generateStringLength);
        for (int i = 0; i < generateStringLength; i++) {
            int randomLimitedInt = smallCaseLeft + (int) (new Random().nextFloat() * (smallCaseRight - smallCaseLeft));
            sb.append((char) randomLimitedInt);
        }

        return sb.toString();
    }
}
