package SeoulBomo.SeoulBomoBe.common;

import lombok.Getter;

@Getter
public enum Borough { // 자치구

    GANGNAM("강남구"),
    GANGDONG("강동구"),
    GANGBUK("강북구"),
    GANGSEO("강서구"),
    GWANAK("관악구"),
    GWANGJIN("광진구"),
    GURO("구로구"),
    GEUMCHEON("금천구"),
    NOWON("노원구"),
    DOBONG("도봉구"),
    DONGDAEMUN("동대문구"),
    DONGJAK("동작구"),
    MAPO("마포구"),
    SEODAEMUN("서대문구"),
    SEOCHO("서초구"),
    SEONGBUK("성북구"),
    SONGPA("송파구"),
    YANGCHEON("양천구"),
    YEONGDEUNGPO("은평구"),
    YONGSAN("용산구"),
    EUNPYEONG("은평구"),
    JONGNO("종로구"),
    JUNG("중구"),
    JUNGNANG("중랑구");

    private String detail;

    Borough(String detail) {
        this.detail = detail;
    }

    public static Borough findByDetail(String detail) {
        return switch (detail) {
            case "강남구" -> GANGNAM;
            case "강동구" -> GANGDONG;
            case "강북구" -> GANGBUK;
            case "강서구" -> GANGSEO;
            case "관악구" -> GWANAK;
            case "광진구" -> GWANGJIN;
            case "구로구" -> GURO;
            case "금천구" -> GEUMCHEON;
            case "노원구" -> NOWON;
            case "도봉구" -> DOBONG;
            case "동대문구" -> DONGDAEMUN;
            case "동작구" -> DONGJAK;
            case "마포구" -> MAPO;
            case "서대문구" -> SEODAEMUN;
            case "서초구" -> SEOCHO;
            case "성북구" -> SEONGBUK;
            case "송파구" -> SONGPA;
            case "양천구" -> YANGCHEON;
            case "영등포구" -> YEONGDEUNGPO;
            case "용산구" -> YONGSAN;
            case "은평구" -> EUNPYEONG;
            case "종로구" -> JONGNO;
            case "중구" -> JUNG;
            case "중랑구" -> JUNGNANG;
            default -> null;
        };
    }

}