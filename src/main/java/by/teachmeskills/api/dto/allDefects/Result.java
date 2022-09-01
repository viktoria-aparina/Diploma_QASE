package by.teachmeskills.api.dto.allDefects;

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
public class Result {

    public int total;
    public int filtered;
    public int count;
    public List<DefectModal> defectModals;
}
