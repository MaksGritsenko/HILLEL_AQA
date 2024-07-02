package org.example.testdata;

public enum Users {

    VALID_USER("tomsmith", "SuperSecretPassword!"),
    INVALID_USER("", "");

    private final String username;
    private final String password;

    Users(String userName, String userPassword) {
        this.username = userName;
        this.password = userPassword;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
