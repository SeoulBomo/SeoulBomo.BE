package SeoulBomo.SeoulBomoBe.domain.childCenterInfo.model;

import SeoulBomo.SeoulBomoBe.common.Borough;
import SeoulBomo.SeoulBomoBe.common.model.BaseEntity;
import SeoulBomo.SeoulBomoBe.domain.like.model.ChildCenterLike;
import SeoulBomo.SeoulBomoBe.domain.review.model.ChildCenterReview;
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
public class ChildCenterInfo extends BaseEntity {
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
    @JsonIgnore
    @OneToMany(mappedBy = "childCenterInfo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChildCenterLike> childCenterLikes = new ArrayList<>();

    // 아동 센터 정보(1) - 아동 센터 리뷰(다)
    @JsonIgnore
    @OneToMany(mappedBy = "childCenterInfo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChildCenterReview> childCenterReviews = new ArrayList<>();

    public ChildCenterInfo(String sigunname, CenterType center, String crtypename, String crname,
                           String craddr, String crtelno, String crhome, String nrtrroomcnt, String plgrdco,
                           String cctvinstlcnt, String chcrtescnt, String la, String lo, String schoolbus,
                           String free, String satopen, String fee, String services) {
        this.borough = Borough.getName(sigunname);
        this.centerType = center;
        this.preschoolType = PreschoolType.getType(crtypename);
        this.name = crname;
        this.address = craddr;
        this.contactNumber = crtelno;
        this.homepage = crhome;
        this.classNum = Integer.valueOf(nrtrroomcnt);
        this.playgroundNum = Integer.valueOf(plgrdco);
        this.cctvNum = Integer.valueOf(cctvinstlcnt);
        this.teacherNum = Integer.valueOf(chcrtescnt);
        this.latitude = Double.valueOf(la);
        this.longitude = Double.valueOf(lo);
        this.isSchoolBus = change(schoolbus);
        this.isFree = change(free);
        this.isSatOpen = change(satopen);
        this.fee = fee;
        this.serviceType = ServiceType.getType(services);
    }

    public static Boolean change(String s){
        if(s.equals("Y") || s.equals("y")) return true;
        else return false;
    }
}