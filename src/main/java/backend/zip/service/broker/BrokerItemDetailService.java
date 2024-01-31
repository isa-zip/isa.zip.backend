package backend.zip.service.broker;

import backend.zip.domain.broker.BrokerItem;
import backend.zip.domain.item.ItemContent;
import backend.zip.domain.item.ItemImage;
import backend.zip.dto.brokeritem.request.AddBrokerItemDetailsRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BrokerItemDetailService {
    public ItemContent saveBrokerItemContentDetails(AddBrokerItemDetailsRequest addBrokerItemDetailsRequest,BrokerItem brokerItem);


    public List<ItemImage> saveBrokerItemImageDetails(MultipartFile[] brokerItemImg, BrokerItem brokerItem);

}
