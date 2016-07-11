package com.workfront.intern.cb.dao;

import com.workfront.intern.cb.common.Match;

import java.util.List;

public interface MatchDao {
    Match getMatchById(int id);
    List<Match> getMatchList();

}
