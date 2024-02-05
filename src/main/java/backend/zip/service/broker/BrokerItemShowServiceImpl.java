package backend.zip.service.broker;

import backend.zip.domain.broker.BrokerItem;
import backend.zip.domain.enums.Role;
import backend.zip.domain.user.User;
import backend.zip.global.exception.brokeritem.BrokerItemException;
import backend.zip.global.exception.user.UserException;
import backend.zip.global.status.ErrorStatus;
import backend.zip.repository.UserRepository;
import backend.zip.repository.broker.BrokerItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static backend.zip.domain.enums.Role.ROLE_USER;

@Service
@RequiredArgsConstructor
public class BrokerItemShowServiceImpl implements BrokerItemShowService {
    private final BrokerItemRepository brokerItemRepository;
    private final UserRepository userRepository;

    @Override
    public List<BrokerItem> findBrokerItemList(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(ErrorStatus.USER_NOT_FOUND));

        if (user.getRole() == ROLE_USER) {
            new UserException(ErrorStatus.USER_NOT_AUTHENTICATION_BROKER);
        }

        List<BrokerItem> brokerItemListByUser = brokerItemRepository.findBrokerItemByUser(userId);

        return brokerItemListByUser;
    }

    @Override
    public BrokerItem findBrokerItem(Long brokerItemId) {
        BrokerItem brokerItem = brokerItemRepository.findById(brokerItemId)
                .orElseThrow(() -> new BrokerItemException(ErrorStatus.BROKER_ITEM_NOT_FOUND));

        return brokerItem;
    }

    @Override
    public void checkBroker(Long userId) {
        User user = userRepository.findById(userId).get();
        if (user.getRole() == ROLE_USER) {
            throw new UserException(ErrorStatus.USER_NOT_AUTHENTICATION_BROKER);
        }
    }
}