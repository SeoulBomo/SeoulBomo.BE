package SeoulBomo.SeoulBomoBe.domain.review.controller;

import SeoulBomo.SeoulBomoBe.domain.account.model.Account;
import SeoulBomo.SeoulBomoBe.domain.account.service.AccountService;
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

    private final AccountService accountService;
    private final ReviewService reviewService;

    @PostMapping()
    public ResponseEntity<ReviewIdResponse> createReview(@RequestBody CreateReviewRequest createReviewRequest) {
        Account account = accountService.getCurrentAccount();
        return ResponseEntity.ok(reviewService.createReview(account, createReviewRequest));
    }

    @PutMapping("/{reviews-id}")
    public ResponseEntity<ReviewIdResponse> updateReview(
            @PathVariable("reviews-id") Long reviewsId,
            @RequestBody UpdateReviewRequest updateReviewRequest) {
        Account account = accountService.getCurrentAccount();
        return ResponseEntity.ok(reviewService.updateReview(account, reviewsId, updateReviewRequest));
    }

    @DeleteMapping("/{reviews-id}")
    public ResponseEntity<ReviewMessage> deleteReview(
            @PathVariable("reviews-id") Long reviewsId,
            @RequestParam("target-type") String targetType
    ) {
        Account account = accountService.getCurrentAccount();
        return ResponseEntity.ok(reviewService.deleteReview(account, reviewsId, targetType));
    }

    @GetMapping("/care-info/{care-info-id}")
    public ResponseEntity<ReviewListResponse> getReviewListByChildCareInfo(@PathVariable("care-info-id") Long careInfoId) {
        Account account = accountService.getCurrentAccount();
        return ResponseEntity.ok(reviewService.getReviewListByChildCareInfo(account, careInfoId));
    }

    @GetMapping("/center-info/{center-info-id}")
    public ResponseEntity<ReviewListResponse> getReviewListByChildCenterInfo(@PathVariable("center-info-id") Long centerInfoId) {
        Account account = accountService.getCurrentAccount();
        return ResponseEntity.ok(reviewService.getReviewListByChildCenterInfo(account, centerInfoId));
    }

    @GetMapping("/me")
    public ResponseEntity<MyReviewListResponse> getReviewListByAccount() {
        Account account = accountService.getCurrentAccount();
        return ResponseEntity.ok(reviewService.getReviewListByAccount(account));
    }
}
