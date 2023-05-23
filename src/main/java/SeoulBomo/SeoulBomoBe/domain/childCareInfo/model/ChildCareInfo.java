package SeoulBomo.SeoulBomoBe.domain.childCareInfo.model;

import SeoulBomo.SeoulBomoBe.common.Borough;
import SeoulBomo.SeoulBomoBe.domain.like.model.ChildCareLike;
import SeoulBomo.SeoulBomoBe.domain.review.model.ChildCareReview;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private String latitude;
    private String longitude;
    private String address;
    private Boolean isFree;
    private String fee;
    private String startAt;
    private String endAt;
    @Column(length = 500)
    private String infoUrl;
    private String facilityName;

    // 보육 정보(1) - 보육 정보 좋아요(다)
    @JsonIgnore
    @OneToMany(mappedBy = "childCareInfo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChildCareLike> childCareLikes = new ArrayList<>();

    // 보육 정보(1) - 보육 정보 리뷰(다)
    @JsonIgnore
    @OneToMany(mappedBy = "childCareInfo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChildCareReview> childCareReviews = new ArrayList<>();

    public ChildCareInfo(String name, InfoType infoType, Borough borough, AgeType ageType, String latitude, String longitude, String address, String plgrdco, Boolean isFree, String fee, String startAt, String endAt, String infoUrl, String facilityName) {
        this.name = name;
        this.infoType = infoType;
        this.borough = borough;
        this.ageType = ageType;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.isFree = isFree;
        this.fee = fee;
        this.startAt = startAt;
        this.endAt = endAt;
        this.infoUrl = infoUrl;
        this.facilityName = facilityName;
    }
}
