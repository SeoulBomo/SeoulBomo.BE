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

    @PostMapping("/care-info/{care-info-id}")
    public ResponseEntity<ReviewIdResponse> createChildCareReview(@PathVariable("care-info-id") Long careInfoId, @RequestBody ReviewRequest reviewRequest) {
        return ResponseEntity.ok(reviewService.createChildCareReview(careInfoId, reviewRequest));
    }

    @PostMapping("/center-info/{center-info-id}")
    public ResponseEntity<ReviewIdResponse> createChildCenterReview(@PathVariable("center-info-id") Long centerInfoId, @RequestBody ReviewRequest reviewRequest) {
        return ResponseEntity.ok(reviewService.createChildCenterReview(centerInfoId, reviewRequest));
    }

    @PutMapping("/{reviews-id}/care-info")
    public ResponseEntity<ReviewIdResponse> updateChildCareReview(@PathVariable("reviews-id") Long reviewId, @RequestBody ReviewRequest reviewRequest) {
        return ResponseEntity.ok(reviewService.updateChildCareReview(reviewId, reviewRequest));
    }

    @PutMapping("/{reviews-id}/center-info")
    public ResponseEntity<ReviewIdResponse> updateChildCenterReview(@PathVariable("reviews-id") Long reviewId, @RequestBody ReviewRequest reviewRequest) {
        return ResponseEntity.ok(reviewService.updateChildCenterReview(reviewId, reviewRequest));
    }

    @DeleteMapping("/{reviews-id}/care-info")
    public ResponseEntity<ReviewMessage> deleteChildCareReview(@PathVariable("reviews-id") Long reviewId) {
        return ResponseEntity.ok(reviewService.deleteChildCareReview(reviewId));
    }

    @DeleteMapping("/{reviews-id}/child-center")
    public ResponseEntity<ReviewMessage> deleteChildCenterReview(@PathVariable("reviews-id") Long reviewId) {
        return ResponseEntity.ok(reviewService.deleteChildCenterReview(reviewId));
    }

    @GetMapping("care-info/{care-info-id}")
    public ResponseEntity<ReviewListResponse> getReviewListByChildCareInfo(@PathVariable("care-info-id") Long careInfoId) {
        return ResponseEntity.ok(reviewService.getReviewListByChildCareInfo(careInfoId));
    }

    @GetMapping("center-info/{center-info-id}")
    public ResponseEntity<ReviewListResponse> getReviewListByChildCenterInfo(@PathVariable("center-info-id") Long centerInfoId) {
        return ResponseEntity.ok(reviewService.getReviewListByChildCenterInfo(centerInfoId));
    }
}
