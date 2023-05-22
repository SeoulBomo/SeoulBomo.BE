package SeoulBomo.SeoulBomoBe.domain.childCenterInfo.dto;

import SeoulBomo.SeoulBomoBe.domain.childCenterInfo.model.ChildCenterInfo;
import lombok.Builder;

public class ChildCenterInfoDto {
    public record ChildCenterBoroughListRequest(
            String borough,
            String centerType){
    }

    public record ChildCenterBoroughListResponse(
            Long id,
            String name,
            String borough,
            String address,
            String centerType,
            Double latitude,
            Double longitude
    ){
        @Builder
        public ChildCenterBoroughListResponse {
        }

        public static ChildCenterBoroughListResponse of(ChildCenterInfo childCenterInfo){
            return ChildCenterBoroughListResponse.builder()
                    .id(childCenterInfo.getId())
                    .name(childCenterInfo.getName())
                    .borough(childCenterInfo.getBorough().getDetail())
                    .address(childCenterInfo.getAddress())
                    .centerType(childCenterInfo.getCenterType().getDetail())
                    .latitude(childCenterInfo.getLatitude())
                    .longitude(childCenterInfo.getLongitude())
                    .build();
        }
    }

    public record ChildCenterKeywordListResponse(
            Long id,
            String name,
            String borough,
            String address,
            String centerType,
            Double latitude,
            Double longitude
    ){
        @Builder
        public ChildCenterKeywordListResponse {
        }

        public static ChildCenterKeywordListResponse of(ChildCenterInfo childCenterInfo){
            return ChildCenterKeywordListResponse.builder()
                    .id(childCenterInfo.getId())
                    .name(childCenterInfo.getName())
                    .borough(childCenterInfo.getBorough().getDetail())
                    .address(childCenterInfo.getAddress())
                    .centerType(childCenterInfo.getCenterType().getDetail())
                    .latitude(childCenterInfo.getLatitude())
                    .longitude(childCenterInfo.getLongitude())
                    .build();
        }
    }
}
