package com.devtop.studentsorter.model;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@RequiredArgsConstructor
@ToString
public class Student {
    @NonNull
    private String name;
    @NonNull
    private double mark;
}
