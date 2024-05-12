package Enums;

public enum Data {
    GUEST_USER("Guest"),
    EASY("easy"),
    MEDIUM("medium"),
    HARD("hard"),
    ;

    private final String value;

    Data(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
