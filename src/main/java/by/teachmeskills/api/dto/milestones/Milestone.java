package by.teachmeskills.api.dto.milestones;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import static lombok.AccessLevel.PRIVATE;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = PRIVATE)
@Builder
@Accessors(chain = true)
public class Milestone {

    private String title;
    private String description;
    private String status;
}
