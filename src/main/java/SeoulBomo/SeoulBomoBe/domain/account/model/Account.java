package SeoulBomo.SeoulBomoBe.domain.account.model;

import SeoulBomo.SeoulBomoBe.domain.like.model.ChildCareLike;
import SeoulBomo.SeoulBomoBe.domain.like.model.ChildCenterLike;
import SeoulBomo.SeoulBomoBe.domain.review.model.ChildCareReview;
import SeoulBomo.SeoulBomoBe.domain.review.model.ChildCenterReview;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long id;

    private String name;
    private String email;

    private String role = "ROLE_USER";

    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    private String image;

    // 회원(1) - 보육 정보 좋아요(다)
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChildCareLike> childCareLikes = new ArrayList<>();

    // 회원(1) - 아동 센터 좋아요(다)
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChildCenterLike> childCenterLikes = new ArrayList<>();

    // 회원(1) - 보육 정보 리뷰(다)
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChildCareReview> childCareReviews = new ArrayList<>();

    // 회원(1) - 아동 센터 리뷰(다)
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChildCenterReview> childCenterReviews = new ArrayList<>();

    public Account update(String name, String profileImage) {
        this.name = name;
        this.image = profileImage;
        return this;
    }

    public static Account toEntity(String name, String email, SocialType socialType, String image) {
        return Account.builder()
                .name(name)
                .email(email)
                .socialType(socialType)
                .image(image)
                .build();
    }
}
