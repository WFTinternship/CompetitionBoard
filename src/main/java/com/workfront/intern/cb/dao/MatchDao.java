package com.workfront.intern.cb.dao;

import com.workfront.intern.cb.common.Match;

import java.util.List;

public interface MatchDao {

    Match getMatchByGroupId(int id);

    List<Match> getMatchList();
}
