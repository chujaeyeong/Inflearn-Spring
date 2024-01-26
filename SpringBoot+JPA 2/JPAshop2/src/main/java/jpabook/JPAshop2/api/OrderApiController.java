package jpabook.JPAshop2.api;

import jpabook.JPAshop2.domain.Address;
import jpabook.JPAshop2.domain.Order;
import jpabook.JPAshop2.domain.OrderItem;
import jpabook.JPAshop2.domain.OrderStatus;
import jpabook.JPAshop2.repository.OrderRepository;
import jpabook.JPAshop2.repository.OrderSearch;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class OrderApiController {

    private final OrderRepository orderRepository;


    /**
     * V1 : 엔티티 직접 노출
     * Hibernate5JakartaModule 등록 및 LAZY = null 처리 (난 LAZY = null은 안 함...), '
     * 양방향 관계 무한 재귀 발생하는건 엔티티 양방향 설정한 코드 윗줄에 @JsonIgnore 어노테이션 추가하면됨 (이것도 안 함)
     */
    @GetMapping("/api/v1/orders")
    public List<Order> ordersV1() {
        List<Order> all = orderRepository.findAllByString(new OrderSearch());
        for (Order order : all) {
            order.getMember().getName();
            order.getDelivery().getAddress();

            List<OrderItem> orderItems = order.getOrderItems();
            orderItems.stream().forEach(o -> o.getItem().getName());
        }

        return all;
    }

    /**
     * V2 : 엔티티를 조회해서 DTO로 변환 (페치 조인 적용 X)
     * 트랜잭션 안에서 지연 로딩 필요, 너무 많은 SQL 쿼리가 날라가는 문제 있음 (페치 조인으로 성능 최적화 필요)
     */
    @GetMapping("/api/v2/orders")
    public List<OrderDto> ordersV2() {
        List<Order> orders = orderRepository.findAllByString(new OrderSearch());
        List<OrderDto> collect = orders.stream()
                .map(o -> new OrderDto(o))
                .collect(Collectors.toList());

        return collect;
    }

    @Getter
    static class OrderDto {

        private Long orderId;
        private String name;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;
        private Address address;
        private List<OrderItemDto> orderItems;

        public OrderDto(Order order) {
            orderId = order.getId();
            name = order.getMember().getName();
            orderDate = order.getOrderDate();
            orderStatus = order.getStatus();
            address = order.getDelivery().getAddress();
            orderItems = order.getOrderItems().stream()
                    .map(orderItem -> new OrderItemDto(orderItem))
                    .collect(Collectors.toList());
        }
    }

    @Getter
    static class OrderItemDto {

        private String itemName; // 상품명
        private int orderPrice; // 주문가격
        private int count; // 주문수량

        public OrderItemDto(OrderItem orderItem) {
            itemName = orderItem.getItem().getName();
            orderPrice = orderItem.getOrderPrice();
            count = orderItem.getCount();
        }
    }

}
