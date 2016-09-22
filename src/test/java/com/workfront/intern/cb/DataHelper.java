package com.workfront.intern.cb;

import com.workfront.intern.cb.common.*;

import java.sql.Timestamp;
import java.util.Random;

/**
 * Random business objects generator.
 */
public class DataHelper {
    private static Random random = new Random();

    /**
     * Creates random manager
     */
    public static Manager createRandomManager() {
        String managerLoginRandom = generateRandomString();
        String managerPassword = generateRandomString(10);
        String avatarPath = "/resources/img/test/" + generateRandomString();

        Manager testManager = new Manager();
        testManager.setLogin(managerLoginRandom);
        testManager.setPassword(managerPassword);
        testManager.setAvatar(avatarPath);

        return testManager;
    }

    /**
     * Creates random tournament
     */
    public static Tournament createRandomTournament() {
        Tournament testTournament = new Tournament();

        String tournamentName = generateRandomString();
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
     * Creates random member
     */
    public static Member createRandomMember() {
        return createRandomMember(0);
    }

    public static Member createRandomMember(int tournamentId) {
        Member testMember = new Member();

        testMember.setAvatar(generateRandomString(10));
        testMember.setParticipantInfo(generateRandomString(10));
        testMember.setName(generateRandomString(10));
        testMember.setSurName(generateRandomString(5));
        testMember.setEmail(generateRandomString(10) + "@gmail.com");
        testMember.setPosition(generateRandomString(5));
        testMember.setTournamentId(tournamentId);

        return testMember;
    }

    /**
     * Creates random team
     */
    public static Team createRandomTeam() {
        Team testTeam = new Team();
        testTeam.setAvatar(generateRandomString(10));
        testTeam.setParticipantInfo(generateRandomString(50));
        testTeam.setTeamName(generateRandomString(10));

        return testTeam;
    }

    /**
     * Creates random photo media
     */
    public static Media createRandomPhotoMedia() {
        Media media = new Media();
        media.setPhoto("https://drive.google.com/drive/my-drive/photo" + generateRandomString() + ".jpg");
        media.setVideo(null);

        return media;
    }

    /**
     * Creates random video media
     */
    public static Media createRandomVideoMedia() {
        Media media = new Media();
        media.setPhoto(null);
        media.setVideo("https://drive.google.com/drive/my-drive/video" + generateRandomString() + ".mp4");

        return media;
    }

    /**
     * Creates random match
     */
    public static Match createRandomMatch() {
        int participantOneId = randomIntGenerate();
        int participantTwoId = randomIntGenerate();
        int scoreParticipantOne = randomIntGenerate();
        int scoreParticipantTwo = randomIntGenerate();
        String matchScore = "8 : 0";

        Match match = new Match();

        match.setParticipantOneId(participantOneId);
        match.setParticipantTwoId(participantTwoId);
        match.setScoreParticipantOne(scoreParticipantOne);
        match.setScoreParticipantOne(scoreParticipantTwo);
        match.setMatchScore(matchScore);

        return match;
    }

    /**
     * Creates random group
     */
    public static Group createRandomGroup() {
        int participantsCount = randomIntGenerate();
        int round = randomIntGenerate();
        int nextRoundParticipants = randomIntGenerate();

        Group group = new Group();

        group.setGroupName(generateRandomString(10));
        group.setParticipantsCount(participantsCount);
        group.setRound(round);
        group.setNextRoundParticipants(nextRoundParticipants);

        return group;
    }

    /**
     * Generates random string of different length
     */
    private static String generateRandomString(int length) {
        int smallCaseLeft = 97; // letter 'a'
        int smallCaseRight = 122; // letter 'z'

        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomLimitedInt = smallCaseLeft + (int) (random.nextFloat() * (smallCaseRight - smallCaseLeft));
            sb.append((char) randomLimitedInt);
        }

        return sb.toString();
    }

    /**
     * Generates random string of concrete length(5 symbols)
     */
    private static String generateRandomString() {
        return generateRandomString(5);
    }

    /**
     * Generates random int
     */
    private static int randomIntGenerate() {
        return (int) (1 + Math.random() * 30);
    }

}
