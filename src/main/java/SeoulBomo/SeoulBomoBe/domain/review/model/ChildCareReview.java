package SeoulBomo.SeoulBomoBe.domain.review.model;

import SeoulBomo.SeoulBomoBe.common.model.BaseEntity;
import SeoulBomo.SeoulBomoBe.domain.account.model.Account;
import SeoulBomo.SeoulBomoBe.domain.childCareInfo.model.ChildCareInfo;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChildCareReview extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "child_care_review_id")
    private Long id;
    private String content;

    // 보육 정보 리뷰(다) - 회원(1)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id")
    private Account account;

    // 보육 정보 리뷰(다) - 보육 정보(1)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "child_care_info_id")
    private ChildCareInfo childCareInfo;

    public void update(String content) {
        this.content = content;
    }
}
