package SeoulBomo.SeoulBomoBe.domain.childCareInfo.model;

import lombok.Getter;

@Getter
public enum InfoType {
    CULTURE_EVENT
            (
                    "문화행사",
                    "http://openapi.seoul.go.kr:8088/6542755249616c733434616f756779/json/TnFcltySttusInfo2001/1/300/",
                    "TnFcltySttusInfo2001"
            ),
    LIBRARY
            (
                    "도서관",
                    "http://openapi.seoul.go.kr:8088/6542755249616c733434616f756779/json/TnFcltySttusInfo10073/1/300/",
                    "TnFcltySttusInfo10073"
            ),
    EXPERIENCE_FACILITY
            (
                    "체험시설",
                    "http://openapi.seoul.go.kr:8088/6542755249616c733434616f756779/json/TnFcltySttusInfo10072/1/300/",
                    "TnFcltySttusInfo10072"
            ),
    OUTDOOR_FACILITY
            (
                    "야외시설",
                    "http://openapi.seoul.go.kr:8088/6542755249616c733434616f756779/json/TnFcltySttusInfo10071/1/300/",
                    "TnFcltySttusInfo10071"
            ),
    MEDICAL_FACILITY
            (
                    "의료시설",
                    "http://openapi.seoul.go.kr:8088/6542755249616c733434616f756779/json/TnFcltySttusInfo10074/1/300/",
                    "TnFcltySttusInfo10074"
            );

    private String detail;
    private String url;
    private String type;

    InfoType(String detail, String url, String type) {
        this.detail = detail;
        this.url = url;
        this.type = type;
    }

    public static InfoType findByDetail(String detail) {
        return switch (detail) {
            case "문화행사" -> CULTURE_EVENT;
            case "도서관" -> LIBRARY;
            case "체험시설" -> EXPERIENCE_FACILITY;
            case "야외시설" -> OUTDOOR_FACILITY;
            case "의료시설" -> MEDICAL_FACILITY;
            default -> null;
        };
    }
}