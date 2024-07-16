package testdata;

public enum AlertMessage {

    SUCCESS_LOGIN_MESSAGE("You logged into a secure area!\n×"),
    SUCCESS_LOGOUT_MESSAGE("You logged out of the secure area!\n×"),
    WARNING_USERNAME_MESSAGE("Your username is invalid!\n×"),
    WARNING_PASSWORD_MESSAGE("Your password is invalid!\n×");

    private final String message;

    AlertMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
