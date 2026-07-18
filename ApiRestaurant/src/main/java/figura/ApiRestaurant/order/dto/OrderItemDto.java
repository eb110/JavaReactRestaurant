package figura.ApiRestaurant.order.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import figura.ApiRestaurant.menu.dto.MenuDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderItemDto {

    private Long id;

    private int quantity;

    private Long menuId;

    private MenuDto menu;

    private BigDecimal pricePerUnit;

    private BigDecimal subtotal;
}
