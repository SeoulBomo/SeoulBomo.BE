package SeoulBomo.SeoulBomoBe.domain.childCenterInfo.model;

import lombok.Getter;

@Getter
public enum PreschoolType {

    PUBLIC("국공립"),
    PRIVATE("민간"),
    WORK("직장"),
    HOME("가정"),
    COLLABORATION("협동"),
    SOCIAL("사회복지법인"),
    ORGANIZATION("법인/단체 등");

    private String detail;

    PreschoolType(String detail) {
        this.detail = detail;
    }

    public static PreschoolType getType(String name) {
        for(PreschoolType p : values()){
            if(p.detail.equals(name)){
                return p;
            }
        }
        return null;
    }
}