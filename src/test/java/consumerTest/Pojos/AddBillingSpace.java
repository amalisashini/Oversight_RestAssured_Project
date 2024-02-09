package consumerTest.Pojos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public class AddBillingSpace {

        private String spaceClusterLabel;
        private String rootSpaceName;
        private String accountNumber;

    }

