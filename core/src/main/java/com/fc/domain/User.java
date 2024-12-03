package com.fc.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class User {
    private long id;
    private String name;
    private String profileImageUrl;

}
