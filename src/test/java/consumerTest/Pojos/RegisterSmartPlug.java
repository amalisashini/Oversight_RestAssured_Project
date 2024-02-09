package consumerTest.Pojos;

import consumerTest.utils.DeviceTypes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterSmartPlug {

    private List<registerPlug> registeringSmartPlugs;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class registerPlug {

        private String name;
        private String serialKey;
        private String linkedDeviceId;

    }

}
