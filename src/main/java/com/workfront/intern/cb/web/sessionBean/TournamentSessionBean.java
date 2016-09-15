package com.workfront.intern.cb.web.sessionBean;

import com.workfront.intern.cb.common.Tournament;
import com.workfront.intern.cb.service.ManagerService;
import com.workfront.intern.cb.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;

public class TournamentSessionBean {
    @Autowired
    ManagerService managerService;

    @Autowired
    TournamentService tournamentService;



    public TournamentSessionBean() {
    }

    public Tournament getTournamnetById(){
        return null;
    }
}
