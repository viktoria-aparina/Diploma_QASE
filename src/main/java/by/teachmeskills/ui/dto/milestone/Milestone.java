package by.teachmeskills.ui.dto.milestone;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Milestone {

    private String name;
    private String description;
    private Status status;
    private String date;
}
