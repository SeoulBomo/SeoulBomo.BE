package SeoulBomo.SeoulBomoBe.domain.like.dto;

public class ChildCareLikeDto {

    public record ChildCareLikeRequest(
            Long userId,
            Long careInfoId
    ){
    }

    public record ChildCenterLikeRequest(
            Long userId,
            Long centerId
    ){
    }
}
