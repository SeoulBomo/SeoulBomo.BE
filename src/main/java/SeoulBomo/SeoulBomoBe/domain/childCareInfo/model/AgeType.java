package SeoulBomo.SeoulBomoBe.domain.childCareInfo.model;

public enum AgeType {

    INFANT("영유아"),
    KINDERGARTEN("유치원생"),
    ELEMENTARY("초중생");

    private String detail;

    AgeType(String detail) {
        this.detail = detail;
    }

}