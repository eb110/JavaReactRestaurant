package figura.ApiRestaurant.menu.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MenuDto {

    private Long id;

    @NotBlank(message = "menu name is required")
    private String name;

    private String description;

    @NotNull(message = "price is required")
    @Positive(message = "price must be a positive value")
    private BigDecimal price;

    private String imageUrl;

    @NotNull(message = "category ID is required")
    private Long categoryId;

    private List<ReviewDto> reviews;
}
