package SeoulBomo.SeoulBomoBe.domain.like.repository;

import SeoulBomo.SeoulBomoBe.domain.like.model.ChildCenterLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChildCenterLikeRepository extends JpaRepository<ChildCenterLike, Long> {
    Optional<ChildCenterLike> findByAccount_IdAndChildCenterInfo_Id(Long userId, Long infoId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE child_center_like SET deleted = false WHERE child_center_like_id = :id", nativeQuery = true)
    void updateDeletedFalse(Long id);

    boolean existsByAccount_IdAndChildCenterInfo_Id(Long userId, Long infoId);

    @Query(value = "SELECT count(case when deleted = 'false' then 1 end) FROM child_center_like WHERE child_center_info_id = :centerId", nativeQuery = true)
    Long countByChildCenterInfo(Long centerId);

    List<ChildCenterLike> findAllByAccount_Id(Long userId);
}
