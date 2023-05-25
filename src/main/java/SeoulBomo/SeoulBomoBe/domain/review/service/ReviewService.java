package SeoulBomo.SeoulBomoBe.domain.review.service;

import SeoulBomo.SeoulBomoBe.common.exception.StatusCode;
import SeoulBomo.SeoulBomoBe.domain.account.model.Account;
import SeoulBomo.SeoulBomoBe.domain.account.service.AccountService;
import SeoulBomo.SeoulBomoBe.domain.childCareInfo.service.ChildCareInfoService;
import SeoulBomo.SeoulBomoBe.domain.childCenterInfo.service.ChildCenterInfoService;
import SeoulBomo.SeoulBomoBe.domain.review.dto.ReviewDto.*;
import SeoulBomo.SeoulBomoBe.domain.review.exception.ReviewException;
import SeoulBomo.SeoulBomoBe.domain.review.model.ChildCareReview;
import SeoulBomo.SeoulBomoBe.domain.review.model.ChildCenterReview;
import SeoulBomo.SeoulBomoBe.domain.review.repository.ChildCareReviewRepository;
import SeoulBomo.SeoulBomoBe.domain.review.repository.ChildCenterReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class ReviewService {
    private final AccountService accountService;
    private final ChildCenterInfoService childCenterInfoService;
    private final ChildCareInfoService childCareInfoService;

    private final ChildCareReviewRepository childCareReviewRepository;
    private final ChildCenterReviewRepository childCenterReviewRepository;

    public ReviewIdResponse createChildCareReview(Long careInfoId, ReviewRequest reviewRequest) {
        Account account = accountService.getCurrentAccount();
        ChildCareReview childCareReview = reviewRequest.toChildCareReview(childCareInfoService.findChildCareInfo(careInfoId), account);
        childCareReviewRepository.save(childCareReview);
        return ReviewIdResponse.of(childCareReview.getId());
    }

    public ReviewIdResponse createChildCenterReview(Long centerInfoId, ReviewRequest reviewRequest) {
        Account account = accountService.getCurrentAccount();
        ChildCenterReview childCenterReview = reviewRequest.toChildCenterReview(childCenterInfoService.findChildCenterInfo(centerInfoId), account);
        childCenterReviewRepository.save(childCenterReview);
        return ReviewIdResponse.of(childCenterReview.getId());
    }

    @Transactional
    public ReviewIdResponse updateChildCareReview(Long reviewId, ReviewRequest reviewRequest) {
        ChildCareReview childCareReview = findChildCareReview(reviewId);
        checkChildCareReview(childCareReview);
        childCareReview.update(reviewRequest.content());
        childCareReviewRepository.save(childCareReview);
        return ReviewIdResponse.of(childCareReview.getId());
    }

    @Transactional
    public ReviewIdResponse updateChildCenterReview(Long reviewId, ReviewRequest reviewRequest) {
        ChildCenterReview childCenterReview = findChildCenterReview(reviewId);
        checkChildCenterReview(childCenterReview);
        childCenterReview.update(reviewRequest.content());
        childCenterReviewRepository.save(childCenterReview);
        return ReviewIdResponse.of(childCenterReview.getId());
    }

    @Transactional
    public ReviewMessage deleteChildCareReview(Long reviewId) {
        ChildCareReview childCareReview = findChildCareReview(reviewId);
        checkChildCareReview(childCareReview);
        childCareReview.softDelete();
        return ReviewMessage.of(reviewId + ", Delete Success");
    }

    @Transactional
    public ReviewMessage deleteChildCenterReview(Long reviewId) {
        ChildCenterReview childCenterReview = findChildCenterReview(reviewId);
        checkChildCenterReview(childCenterReview);
        childCenterReview.softDelete();
        return ReviewMessage.of(reviewId + ", Delete Success");
    }

    public ReviewListResponse getReviewListByChildCareInfo(Long careInfoId) {
        return ReviewListResponse.of(childCareReviewRepository.findAllByChildCareInfoId(careInfoId)
                .stream().map(ReviewResponse::ofChildCareReviewList).toList());
    }

    public ReviewListResponse getReviewListByChildCenterInfo(Long centerInfoId) {
        return ReviewListResponse.of(childCenterReviewRepository.findAllByChildCenterInfoId(centerInfoId).stream()
                .map(ReviewResponse::ofChildCenterReviewList).toList());
    }

    public ChildCareReview findChildCareReview(Long reviewId) {
        return childCareReviewRepository.findById(reviewId).orElseThrow(() -> new ReviewException(StatusCode.NOT_FOUND_REVIEW));
    }

    public ChildCenterReview findChildCenterReview(Long reviewId) {
        return childCenterReviewRepository.findById(reviewId).orElseThrow(() -> new ReviewException(StatusCode.NOT_FOUND_REVIEW));
    }

    public void checkChildCareReview(ChildCareReview childCareReview) {
        Account account = accountService.getCurrentAccount();
        if (!childCareReview.getAccount().equals(account)) throw new ReviewException(StatusCode.NOT_REVIEW_WRITER);
        if (!childCareReview.isVisibility()) throw new ReviewException(StatusCode.ALREADY_DELETED_REVIEW);
    }

    public void checkChildCenterReview(ChildCenterReview childCenterReview) {
        Account account = accountService.getCurrentAccount();
        if (!childCenterReview.getAccount().equals(account)) throw new ReviewException(StatusCode.NOT_REVIEW_WRITER);
        if (!childCenterReview.isVisibility()) throw new ReviewException(StatusCode.ALREADY_DELETED_REVIEW);
    }
}
