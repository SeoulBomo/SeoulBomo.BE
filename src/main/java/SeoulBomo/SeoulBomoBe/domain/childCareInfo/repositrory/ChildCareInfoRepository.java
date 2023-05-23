package SeoulBomo.SeoulBomoBe.domain.childCareInfo.repositrory;

import SeoulBomo.SeoulBomoBe.domain.childCareInfo.model.AgeType;
import SeoulBomo.SeoulBomoBe.domain.childCareInfo.model.ChildCareInfo;
import SeoulBomo.SeoulBomoBe.domain.childCareInfo.model.InfoType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChildCareInfoRepository extends JpaRepository<ChildCareInfo, Long> {
    Page<ChildCareInfo> findAllByInfoTypeAndAgeType(Pageable pageable, InfoType infoType, AgeType ageType);

    Page<ChildCareInfo> findAllByInfoType(Pageable pageable, InfoType infoType);

    List<ChildCareInfo> findTop7ByOrderById();

    @Query(value = "SELECT * FROM child_care_info c WHERE (c.name LIKE %:keyword% OR c.address LIKE %:keyword%)", nativeQuery = true)
    Page<ChildCareInfo> findAllByAddressORNameContaining(Pageable pageable, String keyword);

    @Query(value = "SELECT * FROM child_care_info c WHERE (c.name LIKE %:keyword% OR c.address LIKE %:keyword%) LIMIT 3", nativeQuery = true)
    List<ChildCareInfo> findTop3ByAddressORNameContaining(String keyword);
}
