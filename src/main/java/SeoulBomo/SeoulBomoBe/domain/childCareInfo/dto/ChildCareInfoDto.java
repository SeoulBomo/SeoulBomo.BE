package SeoulBomo.SeoulBomoBe.domain.childCareInfo.dto;

import SeoulBomo.SeoulBomoBe.domain.childCareInfo.model.ChildCareInfo;
import lombok.Builder;

import java.util.List;


public class ChildCareInfoDto {
    public record ChildCareInfoListRequest(
            String infoType,
            String ageType
    ) {
    }

    public record ChildCareInfoListResponse(
            Long id,
            String name,
            String infoType,
            String borough,
            String ageType,
            String latitude,
            String longitude,
            String address,
            Boolean isFree,
            String fee,
            String startAt,
            String endAt,
            String infoUrl,
            String facilityName
    ) {
        @Builder
        public ChildCareInfoListResponse {
        }

        public static ChildCareInfoListResponse of(ChildCareInfo childCareInfo) {
            return ChildCareInfoListResponse.builder()
                    .id(childCareInfo.getId())
                    .name(childCareInfo.getName())
                    .infoType(childCareInfo.getInfoType().getDetail())
                    .borough(childCareInfo.getBorough().getDetail())
                    .ageType(childCareInfo.getAgeType().getDetail())
                    .latitude(childCareInfo.getLatitude())
                    .longitude(childCareInfo.getLongitude())
                    .address(childCareInfo.getAddress())
                    .isFree(childCareInfo.getIsFree())
                    .fee(childCareInfo.getFee())
                    .startAt(childCareInfo.getStartAt())
                    .endAt(childCareInfo.getEndAt())
                    .infoUrl(childCareInfo.getInfoUrl())
                    .facilityName(childCareInfo.getFacilityName())
                    .build();
        }
    }

    public record ChildCareInfoResponse(
            Long id,
            String name,
            String infoType,
            String borough,
            String ageType,
            String latitude,
            String longitude,
            String address,
            Boolean isFree,
            String fee,
            String startAt,
            String endAt,
            String infoUrl,
            String facilityName,
            Long reviewCount,
            Long likeCount
    ) {
        @Builder
        public ChildCareInfoResponse {
        }

        public static ChildCareInfoResponse of(ChildCareInfo childCareInfo, Long reviewCount, Long likeCount) {
            return ChildCareInfoResponse.builder()
                    .id(childCareInfo.getId())
                    .name(childCareInfo.getName())
                    .infoType(childCareInfo.getInfoType().getDetail())
                    .borough(childCareInfo.getBorough().getDetail())
                    .ageType(childCareInfo.getAgeType().getDetail())
                    .latitude(childCareInfo.getLatitude())
                    .longitude(childCareInfo.getLongitude())
                    .address(childCareInfo.getAddress())
                    .isFree(childCareInfo.getIsFree())
                    .fee(childCareInfo.getFee())
                    .startAt(childCareInfo.getStartAt())
                    .endAt(childCareInfo.getEndAt())
                    .infoUrl(childCareInfo.getInfoUrl())
                    .facilityName(childCareInfo.getFacilityName())
                    .reviewCount(reviewCount)
                    .likeCount(likeCount)
                    .build();
        }
    }

    public record ChildCareInfoSimpleResponse(
            Long id,
            String name
    ) {
        @Builder
        public ChildCareInfoSimpleResponse {
        }

        public static ChildCareInfoSimpleResponse of(ChildCareInfo childCareInfo) {
            return ChildCareInfoSimpleResponse.builder()
                    .id(childCareInfo.getId())
                    .name(childCareInfo.getName())
                    .build();
        }
    }

    public record PopularChildCareInfoRespose(
            List<ChildCareInfoSimpleResponse> list
    ) {
        @Builder
        public PopularChildCareInfoRespose {
        }

        public static PopularChildCareInfoRespose of(List<ChildCareInfoSimpleResponse> list) {
            return new PopularChildCareInfoRespose(list);
        }
    }

    public record ChildCareInfoKeywordListResponse(
            Long id,
            String name,
            String borough,
            String address,
            String latitude,
            String longitude
    ){
        @Builder
        public ChildCareInfoKeywordListResponse {
        }

        public static ChildCareInfoKeywordListResponse of(ChildCareInfo childCareInfo){
            return ChildCareInfoKeywordListResponse.builder()
                    .id(childCareInfo.getId())
                    .name(childCareInfo.getName())
                    .borough(childCareInfo.getBorough().getDetail())
                    .address(childCareInfo.getAddress())
                    .latitude(childCareInfo.getLatitude())
                    .longitude(childCareInfo.getLongitude())
                    .build();
        }
    }
}
