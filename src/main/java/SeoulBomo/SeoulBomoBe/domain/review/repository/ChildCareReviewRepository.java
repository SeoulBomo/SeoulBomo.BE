package SeoulBomo.SeoulBomoBe.domain.review.repository;

import SeoulBomo.SeoulBomoBe.domain.review.model.ChildCareReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChildCareReviewRepository extends JpaRepository<ChildCareReview, Long> {
    List<ChildCareReview> findAllByChildCareInfoId(Long childCareInfoId);
}

