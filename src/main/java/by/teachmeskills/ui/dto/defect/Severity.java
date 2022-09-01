package by.teachmeskills.ui.dto.defect;

public enum Severity {

    NOT_SET("//div[contains(@id, 'option-0')]"),
    BLOCKER("//div[contains(@id, 'option-1')]"),
    CRITICAL("//div[contains(@id, 'option-2')]"),
    MAJOR("//div[contains(@id, 'option-3')]"),
    NORMAL("//div[contains(@id, 'option-4')]"),
    MINOR("//div[contains(@id, 'option-5')]"),
    TRIVIAL("//div[contains(@id, 'option-6')]");

    private final String locator;

    Severity(String locator) {
        this.locator = locator;
    }

    public String getLocator() {
        return locator;
    }
}
