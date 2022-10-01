package All.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "category_name", nullable = false)
    @JsonProperty("category_name")
    @NotNull
    @NotBlank
    private String categoryName;

    @Column(name = "category_description", nullable = false)
    @JsonProperty("category_description")
    @NotNull
    @NotBlank
    private String categoryDescription;

    @Column(name = "category_url", nullable = false)
    @JsonProperty("category_url")
    @NotNull
    @NotBlank
    private String categoryUrl;

    @CreationTimestamp
    @Column(name = "created_date", updatable = false)
    @JsonProperty("created_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private Date createdDate;

    @UpdateTimestamp
    @Column(name = "updated_date")
    @JsonProperty("updated_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private Date updatedDate;
}
