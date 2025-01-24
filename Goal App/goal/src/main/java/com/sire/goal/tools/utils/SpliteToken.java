package com.sire.goal.tools.utils;

import org.springframework.stereotype.Component;

@Component
public class SpliteToken {
    public String split(String token) {
        return token.substring(7, token.length());
    }
}
