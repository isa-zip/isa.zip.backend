package backend.zip.domain.broker;

import backend.zip.domain.common.BaseEntity;
import backend.zip.domain.enums.ItemStatus;
import backend.zip.domain.item.ItemContent;
import backend.zip.domain.item.ItemImage;
import backend.zip.domain.user.User;
import backend.zip.global.exception.brokeritem.BrokerItemException;
import backend.zip.global.status.ErrorStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
//@Setter
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

    @Column(name = "address")
    private String address;

    @Column(name = "road_address")
    private String roadAddress;

    @Column(name = "dong")
    private String dong;

    @Column(name = "road_dong")
    private String roadDong;

    @Column(name = "post_number")
    private String postNumber;

    @Column(name = "x")
    private Double x;

    @Column(name = "y")
    private Double y;

    @Enumerated(EnumType.STRING) //매물 상태 그 때 뭐뭐 하기로 했죠....?
    private ItemStatus itemStatus;

    @OneToOne(mappedBy = "brokerItem",cascade = CascadeType.ALL)
    private ItemContent itemContent;

    @OneToMany(mappedBy = "brokerItem", cascade = CascadeType.ALL)
    private List<ItemImage> itemImages; // BrokerItem과 연결된 이미지들의 리스트

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "broker_option_id")
    private BrokerOption brokerOption;

    public void setDetails(List<ItemImage> itemImages, ItemContent itemContent) {
        this.itemImages = itemImages;
        this.itemContent = itemContent;
    }

    public void setBrokerOption(BrokerOption brokerOption) {
        if (brokerOption == null) {
            throw new BrokerItemException(ErrorStatus.BROKER_ITEM_NOT_FOUND);
        }
        this.brokerOption = brokerOption;
    }

    public void updateAddressDetail(String address, String roadAddress, String dong, String roadDong, String postNumber, Double x, Double y) {

        this.address = address;
        this.roadAddress = roadAddress;
        this.dong = dong;
        this.roadDong = roadDong;
        this.postNumber = postNumber;
        this.x = x;
        this.y = y;
    }

    public void updateItemContent(ItemContent itemContent) {
        this.itemContent = itemContent;
    }

    public void updateItemImages(List<ItemImage> itemImages) {
        this.itemImages = itemImages;
    }

    public void updateOptions(BrokerOption brokerOption) {
        this.brokerOption = brokerOption;
    }

}

