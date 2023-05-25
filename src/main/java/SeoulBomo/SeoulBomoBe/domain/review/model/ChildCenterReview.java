package SeoulBomo.SeoulBomoBe.domain.review.model;

import SeoulBomo.SeoulBomoBe.common.model.BaseEntity;
import SeoulBomo.SeoulBomoBe.domain.childCenterInfo.model.ChildCenterInfo;
import SeoulBomo.SeoulBomoBe.domain.account.model.Account;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChildCenterReview extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "child_center_review_id")
    private Long id;
    private String content;

    // 아동 센터 리뷰(다) - 회원(1)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    // 아동 센터 리뷰(다) - 아동 센터 정보(1)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "child_center_info_id")
    private ChildCenterInfo childCenterInfo;

    public void update(String content) {
        this.content = content;
    }
}
