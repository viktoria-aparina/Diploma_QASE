package by.teachmeskills.api.dto.defect.response;

import by.teachmeskills.api.dto.allDefects.Tag;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = PRIVATE)
@Builder
@Accessors(chain = true)
public class Result {

    private int id;
    private String title;
    @SerializedName("actual_result")
    private String actualResult;
    private String status;
    @SerializedName("milestone_id")
    private Object milestoneId;
    @SerializedName("project_id")
    private int projectId;
    private String severity;
    @SerializedName("member_id")
    private int memberId;
    private List<Object> attachments;
    @SerializedName("custom_fields")
    private List<Object> customFields;
    @SerializedName("external_data")
    private String externalData;
    @SerializedName("resolved_at")
    private Date resolvedAt;
    private String created;
    private String updated;
    @SerializedName("created_at")
    private Date createdAt;
    @SerializedName("updated_at")
    private Date updatedAt;
    private List<Tag> tags;
}
