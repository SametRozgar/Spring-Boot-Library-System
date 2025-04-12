package com.sametruzgar.librarySystem.service.impl;

import com.sametruzgar.librarySystem.dto.MemberRequest;
import com.sametruzgar.librarySystem.dto.MemberResponse;
import com.sametruzgar.librarySystem.model.Member;
import com.sametruzgar.librarySystem.service.MemberService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class MemberServiceImpl implements MemberService {

    private final List<Member> members = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    @Override
    public List<MemberResponse> getAllMembers() {
        return members.stream()
                .map(this::mapToMemberResponse)
                .toList();
    }

    @Override
    public MemberResponse getMemberById(Long id) {
        Member member = findMemberById(id);
        return mapToMemberResponse(member);
    }

    @Override
    public MemberResponse addMember(MemberRequest memberRequest) {
        Member member = new Member();
        member.setId(idCounter.getAndIncrement());
        member.setName(memberRequest.getName());
        member.setEmail(memberRequest.getEmail());
        member.setBorrowedBooks(new ArrayList<>());

        members.add(member);
        return mapToMemberResponse(member);
    }

    @Override
    public MemberResponse updateMember(Long id, MemberRequest memberRequest) {
        Member member = findMemberById(id);
        member.setName(memberRequest.getName());
        member.setEmail(memberRequest.getEmail());
        return mapToMemberResponse(member);
    }

    @Override
    public void deleteMember(Long id) {
        Member member = findMemberById(id);
        members.remove(member);
    }

    @Override
    public Member findMemberById(Long id) {
        return members.stream()
                .filter(member -> member.getId().equals(id))
                .findFirst()
                .orElseThrow();
    }

    @Override
    public List<Member> findMembersWithBook(Long bookId) {
        return members.stream()
                .filter(member -> member.getBorrowedBooks().contains(bookId))
                .toList();
    }

    private MemberResponse mapToMemberResponse(Member member) {
        return new MemberResponse(
                member.getId(),
                member.getName(),
                member.getEmail(),
                member.getBorrowedBooks()
        );
    }
}
