package by.teachmeskills.api.dto.defect;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = PRIVATE)
@Builder
@Accessors(chain = true)
public class Defect {

    private String title;
    @SerializedName("actual_result")
    private String actualResult;
    private int severity;
    @SerializedName("milestone_id")
    private String milestoneId;
    private List<String> attachments;
    private Object customFields;
    private List<String> tags;
    private Status status;
}
