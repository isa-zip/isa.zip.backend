package backend.zip.domain.broker;

import backend.zip.domain.common.BaseEntity;
import backend.zip.domain.enums.ItemStatus;
import backend.zip.domain.item.ItemContent;
import backend.zip.domain.item.ItemImage;
import backend.zip.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
//@DynamicInsert
//@DynamicUpdate
public class BrokerItem extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "broker_item_id")
    private Long brokerItemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "address") //이 주소에는 추가로 ..동
    private String address;

    @Column(name = "dong")
    private String dong;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Enumerated(EnumType.STRING) //매물 상태 그 때 뭐뭐 하기로 했죠....?
    private ItemStatus itemStatus;

    @OneToOne(mappedBy = "brokerItem",cascade = CascadeType.ALL)
    private ItemContent itemContent;

    @OneToOne(mappedBy = "brokerItem", cascade = CascadeType.ALL)
    private ItemImage itemImage;

}
