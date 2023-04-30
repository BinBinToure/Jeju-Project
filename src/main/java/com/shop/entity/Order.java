package com.shop.entity;

import com.shop.constant.OrderStatus;
import lombok.Getter;
import lombok.Setter;
import org.apache.catalina.LifecycleState;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")             // order 명령아가 존재하기 때문
@Getter @Setter
public class Order extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "email")     // 한명의 회원은 여러번 주문이 가능하므로 주문 엔티티 기준에서 다대일 단방향 매핑
    private Member member;

    private LocalDateTime orderDate;        // 주문일

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;        // 주문 상태

    // 주문 상품 엔티티와 일대다 매핑, 외래키(order_id)가 order_item 테이블에 있으므로 연관 관계의 주인은 OrderItem 엔티티 
    // order 엔티티가 주인이 아니므로 "mappedBy" 속성으로 연관 관계의 주인을 설정
    // 속성값 "order" 적은 이유 : OrderItem에 있는 Order에 의해 관리된다는 의미
    // 즉, 연관 관계의 주인 필드인 order를 mappedBy의 값으로 세팅한 것
    // 부모 엔티티의 영혹성 상태 변화를 자식 엔티티에 모두 전이하는 CascadeTypeAll 옵션을 설정 ( 부모 - One, 자식 - Many )
    // Order 엔티티가 삭제(저장)되었을때 연관되어 있는 OrderItem 엔티티가 함께 삭제(저장)됨
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL,
            orphanRemoval = true, fetch = FetchType.LAZY)
    private List<OrderItem> orderItems = new ArrayList<>();

    public void addOrderItem(OrderItem orderItem) {     // orderItems에는 주문 상품 정보 담음, orderItem 객체를 order 객체의 orderItems에 추가
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }
    // p299
    public static Order createOrder(Member member, List<OrderItem> orderItemList) {
        Order order = new Order();
        order.setMember(member);
        for(OrderItem orderItem : orderItemList) {
            order.addOrderItem(orderItem);
        }
        order.setOrderStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDateTime.now());
        return order;
    }

    public int getTotalPrice() {
        int totalPrice = 0;
        for(OrderItem orderItem : orderItems) {
            totalPrice += orderItem.getTotalPrice();
        }

        return totalPrice;
    }

    public void cancelOrder() {
        this.orderStatus = OrderStatus.CANCEL;

        for(OrderItem orderItem : orderItems) {
            orderItem.cancel();
        }
    }
}
