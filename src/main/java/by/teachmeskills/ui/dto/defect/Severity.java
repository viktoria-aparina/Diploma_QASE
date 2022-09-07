package by.teachmeskills.ui.dto.defect;

public enum Severity {

    not_set("//div[contains(@id, 'option-0')]"),
    blocker("//div[contains(@id, 'option-1')]"),
    critical("//div[contains(@id, 'option-2')]"),
    major("//div[contains(@id, 'option-3')]"),
    normal("//div[contains(@id, 'option-4')]"),
    minor("//div[contains(@id, 'option-5')]"),
    trivial("//div[contains(@id, 'option-6')]");

    private final String locator;

    Severity(String locator) {
        this.locator = locator;
    }

    public String getLocator() {
        return locator;
    }
}
