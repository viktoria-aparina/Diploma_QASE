package by.teachmeskills.ui.dto.defect;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.File;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Defect {

    String title;
    String actualResult;
    Severity severity;
    Assigned assigned;
    String milestone;
    String tag;
    File attachment;
}
