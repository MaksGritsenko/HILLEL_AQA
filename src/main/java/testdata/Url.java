package testdata;

public enum Url {

    HOME_URL("https://the-internet.herokuapp.com/"),
    LOGIN_URL("https://the-internet.herokuapp.com/login"),
    SECURE_URL("https://the-internet.herokuapp.com/secure"),
    AUTORISATION_SAUCEDEMO_URL("https://www.saucedemo.com/"),
    ADD_REMOVE_ELEMENTS("https://the-internet.herokuapp.com/add_remove_elements/"),
    INVENTORY_SAUCEDEMO_URL("https://www.saucedemo.com/inventory.html");

    private final String url;

    Url(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
