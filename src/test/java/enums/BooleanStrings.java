package enums;

public enum BooleanStrings {

    TRUE("true"),
    FALSE("false");

    private final String booleanStringValue;

    private BooleanStrings(String booleanStringValue) {
        this.booleanStringValue = booleanStringValue;
    }

    @Override
    public String toString() {
        return booleanStringValue;
    }

}
