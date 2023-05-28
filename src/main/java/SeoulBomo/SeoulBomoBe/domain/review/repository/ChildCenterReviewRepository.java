package SeoulBomo.SeoulBomoBe.domain.review.repository;

import SeoulBomo.SeoulBomoBe.domain.account.model.Account;
import SeoulBomo.SeoulBomoBe.domain.childCenterInfo.model.ChildCenterInfo;
import SeoulBomo.SeoulBomoBe.domain.review.model.ChildCenterReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChildCenterReviewRepository extends JpaRepository<ChildCenterReview, Long> {
    List<ChildCenterReview> findAllByChildCenterInfoIdAndIsVisible(Long centerInfoId, boolean isVisible);

    Long countByChildCenterInfo(ChildCenterInfo centerInfo);

    List<ChildCenterReview> findAllByAccountAndIsVisible(Account account, boolean isVisible);
}
