package by.teachmeskills.api.dto.defect;

import java.util.Arrays;

public enum Severity {

    undefined(0),
    blocker(1),
    critical(2),
    major(3),
    normal(4),
    minor(5),
    trivial(6);

    private final int code;

    Severity(int number) {
        this.code = number;
    }

    public int getCode() {
        return code;
    }

    public static Severity getSeverityByCode(int code) {
        return Arrays.stream(Severity.values())
                .filter(s -> s.getCode() == code)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("There is no severity with code" + code));
    }
}
