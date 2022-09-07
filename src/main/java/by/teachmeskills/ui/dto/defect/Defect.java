package by.teachmeskills.ui.dto.defect;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.File;

import static lombok.AccessLevel.PRIVATE;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = PRIVATE)
@Builder
@Accessors(chain = true)
public class Defect {

    private String title;
    private String actualResult;
    private Severity severity;
    private Assigned assigned;
    private String milestone;
    private String tag;
    private File attachment;
}
