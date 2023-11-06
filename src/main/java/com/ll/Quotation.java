package com.ll;

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
    private String quotation;
}
