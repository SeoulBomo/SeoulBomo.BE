package SeoulBomo.SeoulBomoBe.domain.childCenterInfo.repository;

import SeoulBomo.SeoulBomoBe.domain.childCenterInfo.model.ChildCenterInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataRepository extends JpaRepository<ChildCenterInfo, Long> {

    @Query(value = "SELECT * FROM child_center_info c WHERE c.borough = :borough AND c.center_type = :type", nativeQuery = true)
    List<ChildCenterInfo> findByBoroughAndCenterType(@Param("borough") String borough, @Param("type") String centerType);

    List<ChildCenterInfo> findByAddressContaining(String keyword);
    List<ChildCenterInfo> findByNameContaining(String keyword);

}