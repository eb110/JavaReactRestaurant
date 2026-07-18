package figura.ApiRestaurant.order.repository;

import figura.ApiRestaurant.order.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    @Query("SELECT CASE WHEN COUNT(oi) > 0 THEN true ELSE false END FROM OrderItem oi " +
        "WHERE oi.order.id = :oderId AND oi.menu.id = :menuId")
    boolean existsByOrderIdAndMenuId(
        @Param("orderId") Long oderId,
        @Param("menuId") Long menuId);
}
