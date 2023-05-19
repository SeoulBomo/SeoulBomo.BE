package SeoulBomo.SeoulBomoBe.common.article;

import SeoulBomo.SeoulBomoBe.domain.childCenterInfo.model.ChildCenterInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataRepository extends JpaRepository<ChildCenterInfo, Long> {

}