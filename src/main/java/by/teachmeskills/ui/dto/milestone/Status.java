package by.teachmeskills.ui.dto.milestone;

public enum Status {

    ACTIVE("//div[contains(@id, 'option-0')]"),
    COMPLETED("//div[contains(@id, 'option-1')]");

    private final String locator;

    Status(String locator) {
        this.locator = locator;
    }

    public String getLocator() {
        return locator;
    }
}
