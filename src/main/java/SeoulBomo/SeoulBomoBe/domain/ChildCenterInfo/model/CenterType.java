package SeoulBomo.SeoulBomoBe.domain.ChildCenterInfo.model;

public enum CenterType {
    CARE_CENTER("우리동네키움센터"),
    SHARE_CENTER("공동육아나눔터"),
    PRE_SCHOOL("어린이짐");

    private String detail;

    CenterType(String detail) {
        this.detail = detail;
    }
}
