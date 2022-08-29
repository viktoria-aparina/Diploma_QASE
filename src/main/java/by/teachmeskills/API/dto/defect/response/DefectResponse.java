package by.teachmeskills.API.dto.defect.response;

import com.google.gson.annotations.Expose;
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
public class DefectResponse {

    @Expose
    private boolean status;
    Result result;
    private String errorMessage;
    private List<ErrorFields> errorFields;
}
