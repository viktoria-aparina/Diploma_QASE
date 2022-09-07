package by.teachmeskills.ui.dto.project;

import lombok.*;
import lombok.experimental.Accessors;

import static lombok.AccessLevel.PRIVATE;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = PRIVATE)
@Builder
@Accessors(chain = true)
public class Project {

    private String name;
    private String code;
    private String description;
    private ProjectAccess accessLevel;
}
