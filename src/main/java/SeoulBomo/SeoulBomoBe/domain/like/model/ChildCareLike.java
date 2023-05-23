package SeoulBomo.SeoulBomoBe.domain.like.model;

import SeoulBomo.SeoulBomoBe.domain.account.model.Account;
import SeoulBomo.SeoulBomoBe.domain.childCareInfo.model.ChildCareInfo;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE child_care_like SET deleted = true WHERE child_care_like_id=?")
//@Where(clause = "deleted=false")
public class ChildCareLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "child_care_like_id")
    private Long id;

    // 보육 정보 좋아요(다) - 회원(1)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    // 보육 정보 좋아요(다) - 보육 정보(1)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "child_care_info_id")
    private ChildCareInfo childCareInfo;

    private boolean deleted = Boolean.FALSE;

    public ChildCareLike(Account account, ChildCareInfo childCareInfo){
        this.account = account;
        this.childCareInfo = childCareInfo;
    }

}
