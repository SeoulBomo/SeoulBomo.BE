package SeoulBomo.SeoulBomoBe.domain.childCareInfo.model;

public enum AgeType {

    INFANT("영유아"),
    KINDERGARTEN("유치원생"),
    ELEMENTARY("초중생"),
    ALL("연령무관");

    private String detail;

    AgeType(String detail) {
        this.detail = detail;
    }

    public static AgeType findByDetail(String detail) {
        return switch (detail) {
            case "영유아" -> INFANT;
            case "유치원생" -> KINDERGARTEN;
            case "초중생" -> ELEMENTARY;
            case "연령무관" -> ALL;
            default -> null;
        };
    }
}