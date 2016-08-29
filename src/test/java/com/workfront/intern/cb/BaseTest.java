package com.workfront.intern.cb;

import com.workfront.intern.cb.common.*;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;
import java.util.Random;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServiceSpringConfigTest.class)
@ActiveProfiles("testDb")
public class BaseTest {

    protected final int NON_EXISTING_ID = 9999;

    protected final String NON_EXISTING_LOGIN = "adgjOwkJ";

    protected final String TOURNAMENT_NAME = "FIFA 2025";

    protected final String MESSAGE_TEST_COMPLETED_OK = "Test completed successfully!";

    protected final String MESSAGE_TEST_COMPLETED_ERROR = "Test completed with errors :(";

    protected static Random random = new Random();

    /**
     * Creates random manager
     */
    protected static Manager createRandomManager() {
        String managerLoginRandom = generateRandomString();
        String managerPassword = generateRandomString(10);
        String avatarPath = "resources/img/test/" + generateRandomString();

        Manager testManager = new Manager();
        testManager.setLogin(managerLoginRandom);
        testManager.setPassword(managerPassword);
        testManager.setAvatar(avatarPath);

        return testManager;
    }

    /**
     * Creates random tournament
     */
    protected static Tournament createRandomTournament() {
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
    protected static Member createRandomMember() {
        Member testMember = new Member();
        testMember.setAvatar(generateRandomString(10));
        testMember.setParticipantInfo(generateRandomString(10));
        testMember.setName(generateRandomString(10));
        testMember.setSurName(generateRandomString(5));
        testMember.setEmail(generateRandomString(10) + "@gmail.com");
        testMember.setPosition(generateRandomString(5));

        return testMember;
    }

    /**
     * Creates random team
     */
    protected static Team createRandomTeam() {
        Team testTeam = new Team();
        testTeam.setAvatar(generateRandomString(10));
        testTeam.setParticipantInfo(generateRandomString(50));
        testTeam.setTeamName(generateRandomString(10));

        return testTeam;
    }

    /**
     * Creates random photo media
     */
    protected static Media createRandomPhotoMedia() {
        Media media = new Media();
        media.setPhoto("https://drive.google.com/drive/my-drive/photo" + generateRandomString() + ".jpg");
        media.setVideo(null);

        return media;
    }

    /**
     * Creates random video media
     */
    protected static Media createRandomVideoMedia() {
        Media media = new Media();
        media.setPhoto(null);
        media.setVideo("https://drive.google.com/drive/my-drive/video" + generateRandomString() + ".mp4");

        return media;
    }

    /**
     * Creates random match
     */
    protected static Match createRandomMatch() {
        int participantOneId = randomIntGenerate();
        int participantTwoId = randomIntGenerate();
        int scoreParticipantOne = randomIntGenerate();
        int scoreParticipantTwo = randomIntGenerate();

        Match match = new Match();
        match.setParticipantOneId(participantOneId);
        match.setParticipantTwoId(participantTwoId);
        match.setScoreParticipantOne(scoreParticipantOne);
        match.setScoreParticipantOne(scoreParticipantTwo);

        return match;
    }

    /**
     * Creates random group
     */
    protected static Group createRandomGroup() {
        int participantsCount = randomIntGenerate();
        int round = randomIntGenerate();
        int nextRoundParticipants = randomIntGenerate();

        Group group = new Group();
        group.setParticipantsCount(participantsCount);
        group.setRound(round);
        group.setNextRoundParticipnats(nextRoundParticipants);

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