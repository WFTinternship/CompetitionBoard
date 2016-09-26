package com.workfront.intern.cb.web.util;

public class Params {

    // Main Pages
    public static final String PAGE_INDEX = "index";
    public static final String PAGE_SIGN_UP = "secure/sign-up";
    public static final String PAGE_LOG_IN = "secure/log-in";
    public static final String PAGE_CONTACT = "contact/contact-us";

    //Tournaments pages
    public static final String PAGE_TOURNAMENT = "tournament/tournament";
    public static final String PAGE_ADD_TOURNAMENT = "tournament/add-tournament";
    public static final String PAGE_ALL_AVAILABLE_TOURNAMENTS = "tournament/all-tournaments";

    //Search result page
    public static final String PAGE_SEARCH_RESULT = "search-result";

    //Participants pages - MEMBER
    public static final String PAGE_PARTICIPANTS = "participant/participant";
    public static final String PAGE_PARTICIPANTS_MIRROR = "participant/participant-mirror";
    public static final String PAGE_ADD_MEMBER = "participant/add-member";

    //Participants pages - TEAM
    public static final String PAGE_ADD_TEAM = "participant/add-team";




    //Participants pages
    public static final String PAGE_ALL_GROUPS = "group/all-group";
    public static final String PAGE_GROUPS = "group/group";
    public static final String PAGE_ADD_GROUP = "group/add-group";

    //Groups pages
    public static final String PAGE_ASSIGN_TO_GROUP = "group/assign-participant-to-group";

    //Matches pages
    public static final String PAGE_ALL_MATCH = "match/all-match";
    public static final String PAGE_MATCH = "match/match";




    //Medias pages
    public static final String PAGE_MEDIA = "/media.jsp";


    //Errors pages
    public static final String PAGE_ERROR_401 = "error/error401";
    public static final String PAGE_ERROR_404 = "error/error404";
    public static final String PAGE_ERROR_500 = "error/error500";


    // Forms parameters, new manager
    public static final String FORM_PARAM_SIGN_IN = "userNameSignIn";
    public static final String FORM_PARAM_SIGN_IN_PASSWORD = "passwordSignIn";

    // Forms parameters, existing manager
    public static final String FORM_PARAM_LOG_IN = "usernameLogin";
    public static final String FORM_PARAM_LOG_IN_PASSWORD = "passwordLogin";

    // Entries id
    public static final String FORM_PARAM_MANAGER_ID = "manager_id";
    public static final String FORM_PARAM_TOURNAMENT_ID = "tournament_id";
    public static final String FORM_PARAM_MEDIA_ID = "media_id";
    public static final String FORM_PARAM_GROUP_ID = "group_id";
    public static final String FORM_PARAM_MATCH_ID = "match_id";

    // Forms parameters, tournament
    public static final String FORM_PARAM_TOURNAMENT_NAME = "tournament_name";
    public static final String FORM_PARAM_TOURNAMENT_START_DATE = "start_date";
    public static final String FORM_PARAM_TOURNAMENT_END_DATE = "end_date";
    public static final String FORM_PARAM_TOURNAMENT_LOCATION = "location";
    public static final String FORM_PARAM_TOURNAMENT_DESCRIPTION = "tournament_description";
    public static final String FORM_PARAM_TOURNAMENT_FORMAT_ID = "tournament_format_id";
    public static final String FORM_PARAM_TOURNAMENT_MANAGER_ID = "manager_id";

    // Forms parameters, group
    public static final String FORM_PARAM_PARTICIPANTS_COUNT = "participants_count";
    public static final String FORM_PARAM_ROUND = "round";
    public static final String FORM_PARAM_NEXT_ROUND_PARTICIPANTS = "next_round_participants";

    // Forms parameters, match
    public static final String FORM_PARAM_PARTICIPANT_1_ID = "participant_1_id";
    public static final String FORM_PARAM_PARTICIPANT_2_ID = "participant_2_id";
    public static final String FORM_PARAM_SCORE_PARTICIPANT_1 = "score_participant_1";
    public static final String FORM_PARAM_SCORE_PARTICIPANT_2 = "score_participant_2";

    // Forms parameters, media
    public static final String FORM_PARAM_PHOTO = "photo";
    public static final String FORM_PARAM_VIDEO = "video";

    // Forms parameters, participant
    public static final String FORM_PARAM_PARTICIPANT_ID = "participant_id";
    public static final String FORM_PARAM_PARTICIPANT_AVATAR = "avatar";
    public static final String FORM_PARAM_PARTICIPANT_INFO = "participant_info";

    // Forms parameters, member
    public static final String FORM_PARAM_MEMBER_NAME = "name";
    public static final String FORM_PARAM_MEMBER_SURNAME = "surname";
    public static final String FORM_PARAM_MEMBER_POSITION = "position";
    public static final String FORM_PARAM_MEMBER_EMAIL = "email";

    // Forms parameters, team
    public static final String FORM_PARAM_TEAM_NAME = "team_name";
}
