package SeoulBomo.SeoulBomoBe.domain.review.dto;

import SeoulBomo.SeoulBomoBe.common.ChildInfoType;
import SeoulBomo.SeoulBomoBe.domain.account.model.Account;
import SeoulBomo.SeoulBomoBe.domain.childCareInfo.model.ChildCareInfo;
import SeoulBomo.SeoulBomoBe.domain.childCenterInfo.model.ChildCenterInfo;
import SeoulBomo.SeoulBomoBe.domain.review.model.ChildCareReview;
import SeoulBomo.SeoulBomoBe.domain.review.model.ChildCenterReview;
import lombok.Builder;

import java.util.List;

public class ReviewDto {

    public record CreateReviewRequest(
            String targetType,
            Long targetId,
            String content
    ) {
        @Builder
        public CreateReviewRequest {
        }

        public ChildCenterReview toChildCenterReview(ChildCenterInfo childCenterInfo, Account account) {
            return ChildCenterReview.builder()
                    .childCenterInfo(childCenterInfo)
                    .account(account)
                    .content(content)
                    .build();
        }

        public ChildCareReview toChildCareReview(ChildCareInfo childCareInfo, Account account) {
            return ChildCareReview.builder()
                    .childCareInfo(childCareInfo)
                    .account(account)
                    .content(content)
                    .build();
        }
    }

    public record UpdateReviewRequest(
            String targetType,
            String content
    ) {
        @Builder
        public UpdateReviewRequest {
        }
    }

    public record ReviewResponse(
            Long id,
            String name,
            String content,
            String createdAt,
            Boolean isWriter
    ) {
        @Builder
        public ReviewResponse {
        }

        public static ReviewResponse ofChildCenterReview(ChildCenterReview childCenterReview, Boolean isWriter) {
            return ReviewResponse.builder()
                    .id(childCenterReview.getId())
                    .name(childCenterReview.getAccount().getName())
                    .content(childCenterReview.getContent())
                    .createdAt(childCenterReview.getCreatedAt())
                    .isWriter(isWriter)
                    .build();
        }

        public static ReviewResponse ofChildCareReview(ChildCareReview childCareReview, Boolean isWriter) {
            return ReviewResponse.builder()
                    .id(childCareReview.getId())
                    .name(childCareReview.getAccount().getName())
                    .content(childCareReview.getContent())
                    .createdAt(childCareReview.getCreatedAt())
                    .isWriter(isWriter)
                    .build();
        }
    }

    public record ReviewListResponse(
            List<ReviewResponse> list
    ) {
        @Builder
        public ReviewListResponse {
        }

        public static ReviewListResponse of(List<ReviewResponse> list) {
            return new ReviewListResponse(list);
        }
    }

    public record MyReviewResponse(
            Long id,
            String name,
            String content,
            String createdAt,
            String targetType,
            Long targetId
    ) {
        @Builder
        public MyReviewResponse {
        }

        public static MyReviewResponse ofMyChildCenterReview(ChildCenterReview childCenterReview) {
            return MyReviewResponse.builder()
                    .id(childCenterReview.getId())
                    .name(childCenterReview.getAccount().getName())
                    .content(childCenterReview.getContent())
                    .createdAt(childCenterReview.getCreatedAt())
                    .targetType(ChildInfoType.CHILDCENTERINFO.getDetail())
                    .targetId(childCenterReview.getChildCenterInfo().getId())
                    .build();
        }

        public static MyReviewResponse ofMyChildCareReview(ChildCareReview childCareReview) {
            return MyReviewResponse.builder()
                    .id(childCareReview.getId())
                    .name(childCareReview.getAccount().getName())
                    .content(childCareReview.getContent())
                    .createdAt(childCareReview.getCreatedAt())
                    .targetType(ChildInfoType.CHILDCAREINFO.getDetail())
                    .targetId(childCareReview.getChildCareInfo().getId())
                    .build();
        }
    }

    public record MyReviewListResponse(
            List<MyReviewResponse> list
    ) {
        @Builder
        public MyReviewListResponse {
        }

        public static MyReviewListResponse of(List<MyReviewResponse> list) {
            return new MyReviewListResponse(list);
        }
    }

    public record ReviewIdResponse(
            Long id
    ) {
        @Builder
        public ReviewIdResponse {
        }

        public static ReviewIdResponse of(Long id) {
            return new ReviewIdResponse(id);
        }
    }

    public record ReviewMessage(
            String message
    ) {
        @Builder
        public ReviewMessage {
        }

        public static ReviewMessage of(String message) {
            return new ReviewMessage(message);
        }
    }
}
