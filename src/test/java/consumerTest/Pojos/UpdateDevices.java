package consumerTest.Pojos;

import consumerTest.utils.DeviceTypes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateDevices {

    private String id;
    private String name;
    private DeviceTypes deviceType;
    private String deviceCategory;
    private String brand;
    private String modelNumber;
    private String serialKey;
    private int powerUsageInWatt;

}
