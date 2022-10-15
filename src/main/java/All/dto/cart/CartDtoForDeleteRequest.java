package All.dto.cart;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDtoForDeleteRequest {

    @NotNull
    @NotBlank
    @JsonProperty("product_name")
    private String productName;

}
