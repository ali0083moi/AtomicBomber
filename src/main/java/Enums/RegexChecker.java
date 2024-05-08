package Enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum RegexChecker {
    USERNAME_CHECK("[0-9a-zA-Z_]+"),
    PASSWORD_CHECK("[a-zA-Z0-9@#$^&!]+(?=[@#$^&!])+[a-zA-Z0-9@#$^&!]+"),
    GUEST_USERNAME_CHECK("Guest[0-9]+"),
    ;

    private final String regex;

    RegexChecker(String regex) {
        this.regex = regex;
    }

    public Matcher getMatherMatches(String input) {
        Matcher matcher = Pattern.compile(this.regex).matcher(input);
        if (matcher.matches()) {
            return matcher;
        }
        return null;
    }

    public Matcher getMatherFind(String input) {
        Matcher matcher = Pattern.compile(this.regex).matcher(input);
        if (matcher.find()) {
            return matcher;
        }
        return null;
    }

    public String getRegex() {
        return regex;
    }
}
