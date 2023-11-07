package com.ll.domain.quotation;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Quotation {
    @NonNull
    @Getter
    private int id;
    @NonNull
    @Getter
    private String authorName;
    @NonNull
    @Getter
    private String content;

    public void modifyQuotation(String newAuthorName, String newContent) {
        this.authorName = newAuthorName;
        this.content = newContent;
    }
}
