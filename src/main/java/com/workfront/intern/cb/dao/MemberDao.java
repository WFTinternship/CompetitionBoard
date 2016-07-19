package com.workfront.intern.cb.dao;

import com.workfront.intern.cb.common.Member;

import java.util.List;

public interface MemberDao {
    // CREATE
    boolean addMember(Member member);

    // READ
    Member getMemberById(int id);
    List<Member> getMemberList();

    // UPDATE
    boolean updateMember(Member member);

    // DELETE
    boolean deleteMember(int id);
    boolean deleteAll();
}
