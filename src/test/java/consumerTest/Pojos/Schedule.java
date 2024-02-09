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
public class Schedule {

    private String title;
    private String schedulingDate;
    private List<SchedulingPeriod> schedulingPeriods;
    private String repetitionMode;
    private String expireDate;
    private List<String> selectedPowerConsumerIdentities;
    private boolean isDraft;

}
