package SeoulBomo.SeoulBomoBe.domain.childCenterInfo.model;

import lombok.Getter;

@Getter
public enum CenterType {
    CARE_CENTER("우리동네키움센터"),
    SHARE_CENTER("공동육아나눔터"),
    PRE_SCHOOL("어린이집");

    private String detail;

    CenterType(String detail) {
        this.detail = detail;
    }

    public static CenterType getName(String name){
        for(CenterType c : values()){
            if(c.detail.equals(name)){
                return c;
            }
        }
        return null;
    }
}