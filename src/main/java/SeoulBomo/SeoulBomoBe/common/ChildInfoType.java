package SeoulBomo.SeoulBomoBe.common;

import lombok.Getter;

@Getter
public enum ChildInfoType {
    CHILDCAREINFO("childCareInfo"),
    CHILDCENTERINFO("childCenterInfo");

    private String detail;

    ChildInfoType(String detail) {
        this.detail = detail;
    }
}
