package com.workfront.intern.cb.dao;

import com.workfront.intern.cb.common.Member;
import com.workfront.intern.cb.common.Participant;

import java.util.List;

public interface MemberDao {
    Member getMemberById(int id);
    List<Member> getMemberList();
    boolean addMember(Member member);
    boolean updateMember(int memberId, String name, String surName, String position, String email, int participantId);
    boolean deleteMember(Member member);
}
