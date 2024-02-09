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
public class RegisterDevices {

    private List<registerDevice> registeringDevices;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class registerDevice {

        private String name;
        private DeviceTypes deviceType;
        private String deviceCategory;
        private String brand;
        private String modelNumber;
        private String serialKey;
        private int powerUsageInWatt;

    }

}
