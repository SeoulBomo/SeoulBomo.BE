package SeoulBomo.SeoulBomoBe.domain.review.repository;

import SeoulBomo.SeoulBomoBe.domain.review.model.ChildCenterReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChildCenterReviewRepository extends JpaRepository<ChildCenterReview, Long> {
    List<ChildCenterReview> findAllByChildCenterInfoId(Long childCareInfoId);
}

