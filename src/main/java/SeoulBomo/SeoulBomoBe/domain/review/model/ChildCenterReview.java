package SeoulBomo.SeoulBomoBe.domain.review.model;

import SeoulBomo.SeoulBomoBe.domain.ChildCenterInfo.model.ChildCenterInfo;
import SeoulBomo.SeoulBomoBe.domain.account.model.Account;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChildCenterReview {
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
}
