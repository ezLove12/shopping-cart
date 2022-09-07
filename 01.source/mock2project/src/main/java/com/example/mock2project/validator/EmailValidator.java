package com.example.mock2project.validator;

import org.springframework.stereotype.Component;

import java.util.function.Predicate;

@Component
public class EmailValidator implements Predicate<String> {
    private final String EMAIL_PATTERN = "\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";


    @Override
    public boolean test(String s) {
        return s.matches(EMAIL_PATTERN);
    }
}
