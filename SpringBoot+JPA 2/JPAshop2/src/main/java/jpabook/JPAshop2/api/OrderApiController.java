package jpabook.JPAshop2.api;

import jpabook.JPAshop2.domain.Address;
import jpabook.JPAshop2.domain.Order;
import jpabook.JPAshop2.domain.OrderItem;
import jpabook.JPAshop2.domain.OrderStatus;
import jpabook.JPAshop2.repository.OrderRepository;
import jpabook.JPAshop2.repository.OrderSearch;
import jpabook.JPAshop2.repository.order.query.OrderFlatDto;
import jpabook.JPAshop2.repository.order.query.OrderItemQueryDto;
import jpabook.JPAshop2.repository.order.query.OrderQueryDto;
import jpabook.JPAshop2.repository.order.query.OrderQueryRepository;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@RestController
@RequiredArgsConstructor
public class OrderApiController {

    private final OrderRepository orderRepository;
    private final OrderQueryRepository orderQueryRepository;

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


    /**
     * V3 : 페치 조인을 사용해서 쿼리 날라가는 휫수를 최적화
     * 근데 결과를 보면 결과값이 뻥튀기됨 (row 수 증가, DB애 직접 쿼리를 입력해서 확인해봐도 데이터가 두배가됨)
     * 페치 조인 할 때 distinct (중복제거) 하면 해결됨
     * 하지만... 컬렉션 페치 조인은 페이징이 안 됨... 아예 페이징 쿼리가 안 나감...
     * firstResult/maxResults specified with collection fetch; applying in memory 라는 WARN 레벨 로그가 찍힘
     * 일대다 연관관계를 맺었을 때, 이거 아니면 상관없긴함
     */
    @GetMapping("/api/v3/orders")
    public List<OrderDto> ordersV3() {
        List<Order> orders = orderRepository.findAllWithItem();
        List<OrderDto> collect = orders.stream()
                .map(o -> new OrderDto(o))
                .collect(Collectors.toList());

        return collect;
    }


    /**
     * V3.1 : 페이징 한계 돌파
     * 페이징 + 컬렉션을 쓰고 싶다면
     * 1. application.yml 에서 default_batch_fetch_size: nnn 설정 (맥시멈 1000개, 권장: 100~1000)
     * 2. offset, limit 설정
     * 이러면 쿼리 호출 수가 1+N -> 1+1 로 최적화된다 (짱!)
     */
    @GetMapping("/api/v3.1/orders")
    public List<OrderDto> ordersV3_page(@RequestParam(value = "offset", defaultValue = "0") int offset,
                                        @RequestParam(value = "limit", defaultValue = "100") int limit) {

        List<Order> orders = orderRepository.findAllWithMemberDelivery(offset, limit);

        List<OrderDto> result = orders.stream()
                .map(o -> new OrderDto(o))
                .collect(Collectors.toList());

        return result;
    }


    /**
     * V4 : JPA에서 DTO 직접 조회
     * 쿼리 -> 루트 1번, 컬랙션 N번 실행
     * ToOne(N:1, 1:1) 관계들을 먼저 조회하고, ToMany(1:N) 관계는 각각 별도 처리
     * ToOne 관계는 조인해도 데이터 row 수가 증가하지 않고 (데이터 뻥튀기 ㄴㄴ), ToMany(1:N) 관계는 조인하면 row 수가 증가 (데이터 뻥튀기 발생) 하니까 OrderQueryRepository 에서 각각 따로 처리
     */
    @GetMapping("/api/v4/orders")
    public List<OrderQueryDto> ordersV4() {
        return orderQueryRepository.findOrderQueryDtos();
    }


    /**
     * V5 : JPA에서 DTO 직접 조회 - 컬렉션 조회 최적화
     * 쿼리 -> 루트 1번, 컬렉션 1번
     * ToOne 관계들을 먼저 조회하고, 여기서 얻은 식별자 orderId로 ToMany 관계인 OrderItem을 한꺼번에 조회
     * MAP을 사용해서 성능 향상 (O(1))
     */
    @GetMapping("/api/v5/orders")
    public List<OrderQueryDto> ordersV5() {
        return orderQueryRepository.findAllByDto_optimization();
    }


    /**
     * V6 : JPA에서 DTO로 직접 조회, 플랫 데이터 최적화
     * 쿼리를 1번만 날리는 방법
     * 쿼리는 한 번 날리지만, 조인으로 인해 DB에서 애플리케이션에 전달하는 데이터에 중복 데이터가 추가되므로 상황에 따라서 V5보다 더 느릴 수 있다.
     * 그리고 애플리케이션에서 추가 작업이 일어남
     * 페이징 불가능!!
     */
    @GetMapping("/api/v6/orders")
    public List<OrderQueryDto> ordersV6() {
        List<OrderFlatDto> flats = orderQueryRepository.findAllByDto_flat();

        return flats.stream().collect(groupingBy(o -> new OrderQueryDto(o.getOrderId(), o.getName(), o.getOrderDate(), o.getOrderStatus(), o.getAddress()),
                        mapping(o -> new OrderItemQueryDto(o.getOrderId(), o.getItemName(), o.getOrderPrice(), o.getCount()), toList())))
                .entrySet().stream()
                .map(e -> new OrderQueryDto(e.getKey().getOrderId(), e.getKey().getName(), e.getKey().getOrderDate(), e.getKey().getOrderStatus(), e.getKey().getAddress(), e.getValue()))
                .collect(toList());
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
