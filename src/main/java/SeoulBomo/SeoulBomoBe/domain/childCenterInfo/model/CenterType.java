package SeoulBomo.SeoulBomoBe.domain.childCenterInfo.model;

public enum CenterType {
    CARE_CENTER("우리동네키움센터"),
    SHARE_CENTER("공동육아나눔터"),
    PRE_SCHOOL("어린이집");

    private String detail;

    CenterType(String detail) {
        this.detail = detail;
    }
}