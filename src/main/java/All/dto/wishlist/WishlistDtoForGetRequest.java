package All.dto.wishlist;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WishlistDtoForGetRequest {
    @JsonProperty("product_name")
    private String productName;

    @JsonProperty("product_description")
    private String productDescription;

    @JsonProperty("product_price")
    private Double productPrice;

    @JsonProperty("product_url")
    private String productUrl;

    @JsonProperty("category_name")
    private String categoryName;
}
