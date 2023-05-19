package SeoulBomo.SeoulBomoBe.domain.childCareInfo.repositrory;

import SeoulBomo.SeoulBomoBe.domain.childCareInfo.model.AgeType;
import SeoulBomo.SeoulBomoBe.domain.childCareInfo.model.ChildCareInfo;
import SeoulBomo.SeoulBomoBe.domain.childCareInfo.model.InfoType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChildCareInfoRepository extends JpaRepository<ChildCareInfo, Long> {
    Page<ChildCareInfo> findAllByInfoTypeAndAgeType(Pageable pageable, InfoType infoType, AgeType ageType);

    Page<ChildCareInfo> findAllByInfoType(Pageable pageable, InfoType infoType);

    List<ChildCareInfo> findTop7ByOrderById();
}
