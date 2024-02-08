package backend.zip.service.match;

import backend.zip.domain.broker.BrokerItem;
import backend.zip.domain.match.Matching;
import backend.zip.domain.user.UserItem;
import backend.zip.global.exception.brokeritem.BrokerItemException;
import backend.zip.global.exception.user.UserException;
import backend.zip.global.status.ErrorStatus;
import backend.zip.repository.MatchRepository;
import backend.zip.repository.UserItemRepository;
import backend.zip.repository.broker.BrokerItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class MatchServiceImpl implements MatchService {
    private final MatchRepository matchRepository;
    private final BrokerItemRepository brokerItemRepository;
    private final UserItemRepository userItemRepository;


    @Override // 매칭 후보 등록
    public List<Matching> matchBrokerItemsToUserItem(Long userItemId, List<Long> brokerItemId) {
        List<BrokerItem> brokerItemList = brokerItemId.stream()
                .map(itemId -> brokerItemRepository.findById(itemId)
                        .orElseThrow(() -> new BrokerItemException(ErrorStatus.BROKER_ITEM_NOT_FOUND)))
                .collect(Collectors.toList());

        List<Matching> matchingList = new ArrayList<>();
        for (BrokerItem brokerItem : brokerItemList) {
            Matching matching = Matching.createMatching(userItemRepository.findById(userItemId).get(), brokerItem);
            matchingList.add(matching);
            matchRepository.save(matching);
        }
        return matchingList;
    }

    public void unmatchBrokerItems() {
        // 매칭 후보 삭제
    }

    public void matchCompleteBrokerItems() {
        // 매칭 완료
    }

    public void matchUserItems() {
        // 매칭 전체 조회
    }
}
