package SeoulBomo.SeoulBomoBe.domain.childCareInfo.model;

import SeoulBomo.SeoulBomoBe.common.Borough;
import SeoulBomo.SeoulBomoBe.domain.like.model.ChildCareLike;
import SeoulBomo.SeoulBomoBe.domain.review.model.ChildCareReview;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChildCareInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "child_care_info_id")
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private InfoType infoType;
    @Enumerated(EnumType.STRING)
    private Borough borough;
    @Enumerated(EnumType.STRING)
    private AgeType ageType;
    private Double latitude;
    private Double longitude;
    private String address;
    private Boolean isFree;
    private String fee;
    private String startAt;
    private String endAt;
    private String infoUrl;
    private String facilityName;

    // 보육 정보(1) - 보육 정보 좋아요(다)
    @OneToMany(mappedBy = "childCareInfo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChildCareLike> childCareLikes = new ArrayList<>();

    // 보육 정보(1) - 보육 정보 리뷰(다)
    @OneToMany(mappedBy = "childCareInfo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChildCareReview> childCareReviews = new ArrayList<>();
}
