package SeoulBomo.SeoulBomoBe.domain.childCareInfo.repositrory;

import SeoulBomo.SeoulBomoBe.domain.childCareInfo.model.ChildCareInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChildCareInfoRepository extends JpaRepository<ChildCareInfo, Long> {
}
