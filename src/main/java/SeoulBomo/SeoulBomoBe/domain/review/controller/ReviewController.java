package SeoulBomo.SeoulBomoBe.domain.review.controller;

import SeoulBomo.SeoulBomoBe.domain.review.dto.ReviewDto.*;
import SeoulBomo.SeoulBomoBe.domain.review.service.ReviewService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/reviews")
@AllArgsConstructor
@Slf4j
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/child-care/{child-care-id}")
    public ResponseEntity<ReviewIdResponse> createChildCareReview(@PathVariable("child-care-id") Long childCareId, @RequestBody ReviewRequest reviewRequest) {
        return ResponseEntity.ok(reviewService.createChildCareReview(childCareId, reviewRequest));
    }

    @PostMapping("/child-center/{child-center-id}")
    public ResponseEntity<ReviewIdResponse> createChildCenterReview(@PathVariable("child-center-id") Long childCenterId, @RequestBody ReviewRequest reviewRequest) {
        return ResponseEntity.ok(reviewService.createChildCenterReview(childCenterId, reviewRequest));
    }

    @PutMapping("/{reviews-id}/child-care")
    public ResponseEntity<ReviewIdResponse> updateChildCareReview(@PathVariable("reviews-id") Long reviewId, @RequestBody ReviewRequest reviewRequest) {
        return ResponseEntity.ok(reviewService.updateChildCareReview(reviewId, reviewRequest));
    }

    @PutMapping("/{reviews-id}/child-center")
    public ResponseEntity<ReviewIdResponse> updateChildCenterReview(@PathVariable("reviews-id") Long reviewId, @RequestBody ReviewRequest reviewRequest) {
        return ResponseEntity.ok(reviewService.updateChildCenterReview(reviewId, reviewRequest));
    }

    @DeleteMapping("/{reviews-id}/child-care")
    public ResponseEntity<ReviewMessage> deleteChildCareReview(@PathVariable("reviews-id") Long reviewId) {
        return ResponseEntity.ok(reviewService.deleteChildCareReview(reviewId));
    }

    @DeleteMapping("/{reviews-id}/child-center")
    public ResponseEntity<ReviewMessage> deleteChildCenterReview(@PathVariable("reviews-id") Long reviewId) {
        return ResponseEntity.ok(reviewService.deleteChildCenterReview(reviewId));
    }

    @GetMapping("child-care/{child-care-id}")
    public ResponseEntity<ReviewListResponse> getReviewListByChildCareInfo(@PathVariable("child-care-id") Long childCareInfoId) {
        return ResponseEntity.ok(reviewService.getReviewListByChildCareInfo(childCareInfoId));
    }

    @GetMapping("child-center/{child-center-id}")
    public ResponseEntity<ReviewListResponse> getReviewListByChildCenterInfo(@PathVariable("child-center-id") Long childCenterInfoId) {
        return ResponseEntity.ok(reviewService.getReviewListByChildCenterInfo(childCenterInfoId));
    }
}
