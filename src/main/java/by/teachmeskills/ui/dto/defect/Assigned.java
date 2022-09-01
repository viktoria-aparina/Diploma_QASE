package by.teachmeskills.ui.dto.defect;

public enum Assigned {

    UNASSIGNED("//div[contains(@id, 'option-0')]"),
    VIKTORYIA("//div[contains(@id, 'option-1')]");

    private final String locator;

    Assigned(String locator) {
        this.locator = locator;
    }

    public String getLocator() {
        return locator;
    }
}
