package SeoulBomo.SeoulBomoBe.domain.childCenterInfo.model;

import SeoulBomo.SeoulBomoBe.common.Borough;
import SeoulBomo.SeoulBomoBe.domain.like.model.ChildCenterLike;
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
public class ChildCenterInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "child_center_info_id")
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private CenterType centerType;
    @Enumerated(EnumType.STRING)
    private Borough borough;
    private String address;
    @Enumerated(EnumType.STRING)
    private PreschoolType preschoolType;
    private String contactNumber;
    private String homepage;
    private Integer classNum;
    private Integer playgroundNum;
    private Integer cctvNum;
    private Integer teacherNum;
    private Double latitude;
    private Double longitude;
    private Boolean isSchoolBus;
    private Boolean isFree;
    private String fee;
    private Boolean isSatOpen;
    @Enumerated(EnumType.STRING)
    private ServiceType serviceType;

    // 아동 센터 정보(1) - 아동 센터 좋아요(다)
    @OneToMany(mappedBy = "childCenterInfo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChildCenterLike> childCenterLikes = new ArrayList<>();

    // 아동 센터 정보(1) - 아동 센터 리뷰(다)
    @OneToMany(mappedBy = "childCenterInfo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChildCenterReview> childCenterReviews = new ArrayList<>();
}