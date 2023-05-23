package SeoulBomo.SeoulBomoBe.domain.like.model;

import SeoulBomo.SeoulBomoBe.domain.childCenterInfo.model.ChildCenterInfo;
import SeoulBomo.SeoulBomoBe.domain.account.model.Account;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE child_center_like SET deleted = true WHERE child_center_like_id=?")
public class ChildCenterLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "child_center_like_id")
    private Long id;

    // 아동 센터 좋아요(다) - 회원(1)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    // 아동 센터 좋아요(다) - 아동 센터 정보(1)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "child_center_info_id")
    private ChildCenterInfo childCenterInfo;

    private boolean deleted = Boolean.FALSE;

    public ChildCenterLike(Account account, ChildCenterInfo childCenterInfo){
        this.account = account;
        this.childCenterInfo = childCenterInfo;
    }
}
