package SeoulBomo.SeoulBomoBe.domain.review.repository;

import SeoulBomo.SeoulBomoBe.domain.account.model.Account;
import SeoulBomo.SeoulBomoBe.domain.childCareInfo.model.ChildCareInfo;
import SeoulBomo.SeoulBomoBe.domain.review.model.ChildCareReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChildCareReviewRepository extends JpaRepository<ChildCareReview, Long> {
    List<ChildCareReview> findAllByChildCareInfoIdAndIsVisible(Long careInfoId, boolean isVisible);

    List<ChildCareReview> findAllByAccount(Account account);

    Long countByChildCareInfo(ChildCareInfo childCareInfo);
}

