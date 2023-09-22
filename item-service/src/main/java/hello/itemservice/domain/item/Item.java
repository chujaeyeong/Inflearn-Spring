package hello.itemservice.domain.item;

import lombok.Data;

@Data
// 단순히 데이터만 왔다갔다하는 DTO에서는 @Data 어노테이션을 사용해줘도 무방한데, 위험해서 보통은 Getter Setter 를 쓰지 Data는 잘 안쓴다
public class Item {

    private Long id;
    private String itemName;
    private Integer price;
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
