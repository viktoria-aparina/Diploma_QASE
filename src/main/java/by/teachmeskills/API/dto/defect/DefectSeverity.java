package by.teachmeskills.API.dto.defect;

public enum DefectSeverity {

    NOT_SET("NOT_SET"),
    BLOCKER("1"),
    CRITICAL("2"),
    MAJOR("3"),
    NORMAL("4"),
    MINOR("5"),
    TRIVIAL("6");

    private final String number;

    DefectSeverity(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }
}
