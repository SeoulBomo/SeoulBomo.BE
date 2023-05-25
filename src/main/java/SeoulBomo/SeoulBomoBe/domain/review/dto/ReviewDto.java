package SeoulBomo.SeoulBomoBe.domain.review.dto;

import SeoulBomo.SeoulBomoBe.domain.account.model.Account;
import SeoulBomo.SeoulBomoBe.domain.childCareInfo.model.ChildCareInfo;
import SeoulBomo.SeoulBomoBe.domain.childCenterInfo.model.ChildCenterInfo;
import SeoulBomo.SeoulBomoBe.domain.review.model.ChildCareReview;
import SeoulBomo.SeoulBomoBe.domain.review.model.ChildCenterReview;
import lombok.Builder;

import java.util.List;

public class ReviewDto {

    public record ReviewRequest(
            String content
    ) {
        @Builder
        public ReviewRequest {
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

    public record ReviewResponse(
            Long id,
            String name,
            String content,
            String createdAt
    ) {
        @Builder
        public ReviewResponse {
        }

        public static ReviewResponse ofChildCenterReviewList(ChildCenterReview childCenterReview) {
            return ReviewResponse.builder()
                    .id(childCenterReview.getId())
                    .name(childCenterReview.getAccount().getName())
                    .content(childCenterReview.getContent())
                    .createdAt(childCenterReview.getCreatedAt())
                    .build();
        }

        public static ReviewResponse ofChildCareReviewList(ChildCareReview childCareReview) {
            return ReviewResponse.builder()
                    .id(childCareReview.getId())
                    .name(childCareReview.getAccount().getName())
                    .content(childCareReview.getContent())
                    .createdAt(childCareReview.getCreatedAt())
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
