package consumerTest.Pojos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UnlinkDevice {

    private String linkedPowerConsumerId;
    private List<String> excludedSemiAutomatedSchedules;

}
