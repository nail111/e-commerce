package All.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {

    @NotNull
    @NotBlank
    @JsonProperty("product_name")
    private String productName;

    @NotNull
    @NotBlank
    @JsonProperty("product_description")
    private String productDescription;

    @NotNull
    @NotBlank
    @JsonProperty("product_url")
    private String productUrl;

    @NotNull
    @Min(value = 1)
    @JsonProperty("product_price")
    private Double productPrice;

    @NotNull
    @NotBlank
    @JsonProperty("category_name")
    private String categoryName;
}
