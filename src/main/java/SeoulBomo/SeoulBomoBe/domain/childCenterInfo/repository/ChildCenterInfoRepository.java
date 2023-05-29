package SeoulBomo.SeoulBomoBe.domain.childCenterInfo.repository;

import SeoulBomo.SeoulBomoBe.common.model.Borough;
import SeoulBomo.SeoulBomoBe.domain.childCenterInfo.model.CenterType;
import SeoulBomo.SeoulBomoBe.domain.childCenterInfo.model.ChildCenterInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChildCenterInfoRepository extends JpaRepository<ChildCenterInfo, Long> {

    List<ChildCenterInfo> findTop3ByBoroughAndCenterType(Borough borough,CenterType centerType);

    Page<ChildCenterInfo> findAllByBoroughAndCenterType(Pageable pageable, Borough borough, CenterType centerType);

    @Query(value = "SELECT * FROM child_center_info c WHERE (c.name LIKE %:keyword% OR c.address LIKE %:keyword%)", nativeQuery = true)
    Page<ChildCenterInfo> findAllByAddressORNameContaining(Pageable pageable, String keyword);

    @Query(value = "SELECT * FROM child_center_info c WHERE (c.name LIKE %:keyword% OR c.address LIKE %:keyword%) LIMIT 3", nativeQuery = true)
    List<ChildCenterInfo> findTop3ByAddressORNameContaining(String keyword);

}