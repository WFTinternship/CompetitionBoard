package com.workfront.intern.cb.dao;

import com.workfront.intern.cb.common.Member;

import java.util.List;

public interface MemberDao {
    Member getMemberById(int id);
    List<Member> getAllMembers();
    void updateMember(Member member);
    void deleteMember(Member member);
}
