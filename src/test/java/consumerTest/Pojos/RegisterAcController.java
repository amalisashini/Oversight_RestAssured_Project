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
public class RegisterAcController {

    private List<registerAcCon> registeringAcControllers;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class registerAcCon {

        private String name;
        private String serialKey;
        private String linkedAcId;

    }

}
