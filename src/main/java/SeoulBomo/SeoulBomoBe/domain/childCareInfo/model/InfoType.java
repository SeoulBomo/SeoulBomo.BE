package SeoulBomo.SeoulBomoBe.domain.childCareInfo.model;

public enum InfoType {

    CULTURE_EVENT("문화행사"),
    OFFICES("관공서"),
    LIBRARY("도서관"),
    EXPERIENCE_FACILITY("체험시설"),
    OUTDOOR_FACILITY("야외시설"),
    MEDICAL_FACILITY("의료시설");

    private String detail;

    InfoType(String detail) {
        this.detail = detail;
    }
}