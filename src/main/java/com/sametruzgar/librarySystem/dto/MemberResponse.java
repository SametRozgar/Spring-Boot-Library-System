package com.sametruzgar.librarySystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class MemberResponse {
    private Long id;
    private String name;
    private String email;
    private List<Long> borrowedBooks;

    public MemberResponse(Long id, String name, String email, List<Long> borrowedBooks) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.borrowedBooks = borrowedBooks;
    }
}
