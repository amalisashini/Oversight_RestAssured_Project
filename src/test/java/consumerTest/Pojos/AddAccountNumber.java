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
public class AddAccountNumber {

    private String label;
    private String accountNumber;
    private TariffTypes tariffCode;
}
