package figura.ApiRestaurant.review.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import figura.ApiRestaurant.auth_users.entity.User;
import figura.ApiRestaurant.menu.entity.Menu;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReviewDto {

    private Long id;

    private Long menuId;

    private Long orderId;

    private String userName;

    @NotNull(message = "rating is required")
    @Min(1)
    @Max(10)
    private Integer rating;

    @Size(max = 500, message = "comment cannot exceed 500 characters")
    private String comment;

    private String menuName;

    private LocalDateTime createdAt;
}
