package consumerTest.Pojos;

import consumerTest.utils.TariffTypes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateAccountNumber {

    private String oldAccountNumber;
    private String label;
    private TariffTypes tariffCode;
    private String updatedAccountNumber;

}
