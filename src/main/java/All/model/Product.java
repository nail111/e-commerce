package All.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private UUID id;

    @Column(name = "product_name")
    @JsonProperty("product_name")
    @NotNull
    @NotBlank
    private String productName;

    @Column(name = "product_description")
    @JsonProperty("product_description")
    @NotNull
    @NotBlank
    private String productDescription;

    @Column(name = "product_price")
    @JsonProperty("product_price")
    @NotNull
    @Min(value = 1)
    private Double productPrice;

    @Column(name = "product_url")
    @JsonProperty("product_url")
    @NotNull
    @NotBlank
    private String productUrl;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    @CreationTimestamp
    @Column(name = "created_date", updatable = false)
    @JsonProperty("created_date")
    private Date createdDate;

    @UpdateTimestamp
    @Column(name = "updated_date")
    @JsonProperty("updated_date")
    private Date updatedDate;
}
