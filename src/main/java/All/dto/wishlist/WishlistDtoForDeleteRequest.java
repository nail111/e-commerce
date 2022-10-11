package All.dto.wishlist;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WishlistDtoForDeleteRequest {

    @JsonProperty("product_name")
    private String productName;
}
