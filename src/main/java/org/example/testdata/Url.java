package org.example.testdata;

public enum Url {

    HOME_URL("https://the-internet.herokuapp.com/"),
    LOGIN_URL("https://the-internet.herokuapp.com/login"),
    SECURE_URL("https://the-internet.herokuapp.com/secure");

    private final String url;

    Url(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
