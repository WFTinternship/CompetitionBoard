package com.workfront.intern.cb.dao;

import com.workfront.intern.cb.common.Member;
import java.util.List;

public interface MemberDao {
    Member getMemberById(int id);
    List<Member> getMemberList();
    boolean addMember(Member member);
    boolean updateMember(Member member);
    boolean deleteMemberById(int id);
}
