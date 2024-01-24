package jpabook.JPAshop2.api;

import jpabook.JPAshop2.domain.Address;
import jpabook.JPAshop2.domain.Order;
import jpabook.JPAshop2.domain.OrderStatus;
import jpabook.JPAshop2.repository.OrderRepository;
import jpabook.JPAshop2.repository.OrderSearch;
import jpabook.JPAshop2.repository.order.simplequery.OrderSimpleQueryDto;
import jpabook.JPAshop2.repository.order.simplequery.OrderSimpleQueryRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * xToOrder(ManyToOne, OneToOne)
 * Order
 * Order -> Member
 * Order -> Delivery
 */
@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

    private final OrderRepository orderRepository;
    private final OrderSimpleQueryRepository orderSimpleQueryRepository;

    /**
     * V1
     * 엔티티를 직접 노출
     * 단점 1. 양방향 연관관계 무한 루프 문제 발생 : 양방향 연관관계 한 쪽에 @JsonIgnore 어노테이션으로 끊어줘야한다
     * 단점 2. 프록시 관련 에러 : 1번으로 해결해도 500 에러 발생 -> Hibernate5Modul 을 스프링 빈으로 등록하면 해결됨 & LAZY=null 처리
     */
    @GetMapping("/api/v1/simple-orders")
    public List<Order> ordersV1() {
        List<Order> all = orderRepository.findAllByString(new OrderSearch());
        for (Order order : all) {
            order.getMember().getName(); // Lazy 강제 초기화
            order.getDelivery().getAddress(); // Lazy 강제 초기화
        }
        return all;
    }

    /**
     * V2
     * 엔티티를 조회해서 DTO로 변환 (fetch join은 사용 X)
     * 단점 : 지연 로딩으로 쿼리 N번 호출로 성능 이슈 발생 (쿼리가 총 1+N+N 번 나감)
     */
    @GetMapping("/api/v2/simple-orders")
    public List<SimpleOrderDto> ordersV2() {
        List<Order> orders = orderRepository.findAllByString(new OrderSearch());
        List<SimpleOrderDto> result = orders.stream()
                .map(o -> new SimpleOrderDto(o))
                .collect(Collectors.toList());

        return result;
    }

    /**
     * V3
     * 엔티티를 조회해서 DTO로 변환 (fetch join 사용 O) -> fetch join으로 쿼리 1번으로 호출
     */
    @GetMapping("/api/v3/simple-orders")
    public List<SimpleOrderDto> ordersV3() {
        List<Order> orders = orderRepository.findAllWithMemberDelivery();
        List<SimpleOrderDto> result = orders.stream()
                .map(o -> new SimpleOrderDto(o))
                .collect(Collectors.toList());

        return result;
    }

    /**
     * V4
     * JPA에서 DTO로 바로 조회 -> 쿼리 1번 호출, select 철에서 원하는 데이터만 선택해서 조회
     */
    @GetMapping("/api/v4/simple-orders")
    public List<OrderSimpleQueryDto> ordersV4() {
        return orderSimpleQueryRepository.findOrderDtos();
    }


    @Data
    static class SimpleOrderDto {
        private Long orderId;
        private String name;
        private LocalDateTime orderDate; // 주문시간
        private OrderStatus orderStatus;
        private Address address;

        public SimpleOrderDto(Order order) {
            orderId = order.getId();
            name = order.getMember().getName();
            orderDate = order.getOrderDate();
            orderStatus = order.getStatus();
            address = order.getDelivery().getAddress();
        }

    }
}
