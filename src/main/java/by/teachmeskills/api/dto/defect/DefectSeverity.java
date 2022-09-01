package by.teachmeskills.api.dto.defect;

import java.util.Arrays;

public enum DefectSeverity {

    UNDEFINED(0),
    BLOCKER(1),
    CRITICAL(2),
    MAJOR(3),
    NORMAL(4),
    MINOR(5),
    TRIVIAL(6);

    private final int code;

    DefectSeverity(int number) {
        this.code = number;
    }

    public int getCode() {
        return code;
    }

    public DefectSeverity getSeverityByCode(int code) {
        return Arrays.stream(DefectSeverity.values())
                .filter(s -> s.getCode() == code)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("There is no severity with code" + code));
    }
}
