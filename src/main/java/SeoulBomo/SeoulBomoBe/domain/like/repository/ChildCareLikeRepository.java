package SeoulBomo.SeoulBomoBe.domain.like.repository;

import SeoulBomo.SeoulBomoBe.domain.like.model.ChildCareLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChildCareLikeRepository extends JpaRepository<ChildCareLike, Long> {
    Optional<ChildCareLike> findByAccount_IdAndChildCareInfo_Id(Long userId, Long infoId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE child_care_like SET deleted = false WHERE child_care_like_id = :id", nativeQuery = true)
    void updateDeletedFalse(Long id);

    boolean existsByAccount_IdAndChildCareInfo_Id(Long userId, Long infoId);

    @Query(value = "SELECT count(case when deleted = 'false' then 1 end) FROM child_care_like WHERE child_care_info_id = :infoId", nativeQuery = true)
    Long countByChildCareInfo(Long infoId);

    List<ChildCareLike> findAllByAccountIdAndDeleted(Long userId, Boolean deleted);

}
