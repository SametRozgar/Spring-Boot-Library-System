package com.sametruzgar.librarySystem.service;

import com.sametruzgar.librarySystem.dto.MemberRequest;
import com.sametruzgar.librarySystem.dto.MemberResponse;
import com.sametruzgar.librarySystem.model.Member;

import java.util.List;

public interface MemberService {
    List<MemberResponse> getAllMembers();
    MemberResponse getMemberById(Long id);
    MemberResponse addMember(MemberRequest memberRequest);
    MemberResponse updateMember(Long id, MemberRequest memberRequest);
    void deleteMember(Long id);
    Member findMemberById(Long id); // Internal kullanım için
    List<Member> findMembersWithBook(Long bookId); // Kitap ödünç alma/teslim için
}
