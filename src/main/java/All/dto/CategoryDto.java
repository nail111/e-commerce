package All.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

    @JsonProperty("category_name")
    @NotNull
    @NotBlank
    private String categoryName;

    @JsonProperty("category_description")
    @NotNull
    @NotBlank
    private String categoryDescription;

    @JsonProperty("category_url")
    @NotNull
    @NotBlank
    private String categoryUrl;
}
