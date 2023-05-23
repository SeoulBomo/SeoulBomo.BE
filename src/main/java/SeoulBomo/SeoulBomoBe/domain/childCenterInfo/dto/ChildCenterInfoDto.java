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

    public record ChildCenterDetailResponse(
            Long id,
            String name,
            String centerType,
            String borough,
            String address,
            String preschoolType,
            String contactNumber,
            String homepage,
            Integer classNum,
            Integer playgroundNum,
            Integer cctvNum,
            Integer teacherNum,
            Double latitude,
            Double longitude,
            Boolean isSchoolBus,
            Boolean isFree,
            String fee,
            Boolean isSatOpen,
            String serviceType,
            Long reviewCount,
            Long likeCount
    ){
        @Builder
        public ChildCenterDetailResponse {
        }

        public static ChildCenterDetailResponse of(ChildCenterInfo childCenterInfo, Long reviewCount, Long likeCount){
            return ChildCenterDetailResponse.builder()
                    .id(childCenterInfo.getId())
                    .name(childCenterInfo.getName())
                    .centerType(childCenterInfo.getCenterType().getDetail())
                    .borough(childCenterInfo.getBorough().getDetail())
                    .address(childCenterInfo.getAddress())
                    .preschoolType(childCenterInfo.getPreschoolType().getDetail())
                    .cctvNum(childCenterInfo.getCctvNum())
                    .playgroundNum(childCenterInfo.getPlaygroundNum())
                    .teacherNum(childCenterInfo.getTeacherNum())
                    .latitude(childCenterInfo.getLatitude())
                    .longitude(childCenterInfo.getLongitude())
                    .isSchoolBus(childCenterInfo.getIsSchoolBus())
                    .isFree(childCenterInfo.getIsFree())
                    .fee(childCenterInfo.getFee())
                    .isSatOpen(childCenterInfo.getIsSatOpen())
                    .serviceType(childCenterInfo.getServiceType().getDetail())
                    .likeCount(likeCount)
                    .reviewCount(reviewCount)
                    .build();

        }
    }
}
