package SeoulBomo.SeoulBomoBe.domain.review.service;

import SeoulBomo.SeoulBomoBe.common.model.ChildInfoType;
import SeoulBomo.SeoulBomoBe.common.exception.StatusCode;
import SeoulBomo.SeoulBomoBe.domain.account.model.Account;
import SeoulBomo.SeoulBomoBe.domain.account.repository.AccountRepository;
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

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ReviewService {
    private final ChildCenterInfoService childCenterInfoService;
    private final ChildCareInfoService childCareInfoService;

    private final ChildCareReviewRepository childCareReviewRepository;
    private final ChildCenterReviewRepository childCenterReviewRepository;

    private final AccountRepository accountRepository;

    public ReviewIdResponse createReview(Account account, CreateReviewRequest createReviewRequest) {
        if (createReviewRequest.targetType().equalsIgnoreCase(ChildInfoType.CHILDCAREINFO.toString())) {
            ChildCareReview childCareReview = createReviewRequest.toChildCareReview(childCareInfoService.findChildCareInfo(createReviewRequest.targetId()), account);
            childCareReviewRepository.save(childCareReview);
            return ReviewIdResponse.of(childCareReview.getId());
        } else {
            ChildCenterReview childCenterReview = createReviewRequest.toChildCenterReview(childCenterInfoService.findChildCenterInfo(createReviewRequest.targetId()), account);
            childCenterReviewRepository.save(childCenterReview);
            return ReviewIdResponse.of(childCenterReview.getId());
        }
    }

    @Transactional
    public ReviewIdResponse updateReview(Account account, Long reviewId, UpdateReviewRequest updateReviewRequest) {
        if (updateReviewRequest.targetType().equalsIgnoreCase(ChildInfoType.CHILDCAREINFO.toString())) {
            ChildCareReview childCareReview = findChildCareReview(reviewId);
            checkChildCareReview(account, childCareReview);
            childCareReview.update(updateReviewRequest.content());
            childCareReviewRepository.save(childCareReview);
            return ReviewIdResponse.of(childCareReview.getId());
        } else {
            ChildCenterReview childCenterReview = findChildCenterReview(reviewId);
            checkChildCenterReview(account, childCenterReview);
            childCenterReview.update(updateReviewRequest.content());
            childCenterReviewRepository.save(childCenterReview);
            return ReviewIdResponse.of(childCenterReview.getId());
        }
    }

    @Transactional
    public ReviewMessage deleteReview(Account account, Long reviewId, String targetType) {
        if (targetType.equalsIgnoreCase(ChildInfoType.CHILDCAREINFO.toString())) {
            ChildCareReview childCareReview = findChildCareReview(reviewId);
            checkChildCareReview(account, childCareReview);
            childCareReview.softDelete();
        } else {
            ChildCenterReview childCenterReview = findChildCenterReview(reviewId);
            checkChildCenterReview(account, childCenterReview);
            childCenterReview.softDelete();
        }
        return ReviewMessage.of(reviewId + ", Delete Success");
    }

    public ReviewListResponse getReviewListByChildCareInfo(Account account, Long careInfoId) {
        return ReviewListResponse.of(childCareReviewRepository.findAllByChildCareInfoIdAndIsVisible(careInfoId, true)
                .stream().map(it -> ReviewResponse.ofChildCareReview(it, checkWriter(account, it.getAccount()))).toList());
    }

    public ReviewListResponse getReviewListByChildCenterInfo(Account account, Long centerInfoId) {
        return ReviewListResponse.of(childCenterReviewRepository.findAllByChildCenterInfoIdAndIsVisible(centerInfoId, true)
                .stream().map(it -> ReviewResponse.ofChildCenterReview(it, checkWriter(account, it.getAccount()))).toList());
    }

    public ChildCareReview findChildCareReview(Long reviewId) {
        return childCareReviewRepository.findById(reviewId).orElseThrow(() -> new ReviewException(StatusCode.NOT_FOUND_REVIEW));
    }

    public ChildCenterReview findChildCenterReview(Long reviewId) {
        return childCenterReviewRepository.findById(reviewId).orElseThrow(() -> new ReviewException(StatusCode.NOT_FOUND_REVIEW));
    }

    public void checkChildCareReview(Account account, ChildCareReview childCareReview) {
        if (childCareReview.getAccount().getId() != account.getId())
            throw new ReviewException(StatusCode.NOT_REVIEW_WRITER);
        if (!childCareReview.isVisible()) throw new ReviewException(StatusCode.ALREADY_DELETED_REVIEW);
    }

    public void checkChildCenterReview(Account account, ChildCenterReview childCenterReview) {
        if (childCenterReview.getAccount().getId() != account.getId())
            throw new ReviewException(StatusCode.NOT_REVIEW_WRITER);
        if (!childCenterReview.isVisible()) throw new ReviewException(StatusCode.ALREADY_DELETED_REVIEW);
    }

    public MyReviewListResponse getReviewListByAccount(Account account) {
        List<MyReviewResponse> reviewResponseList = new ArrayList<>();
        reviewResponseList.addAll(childCareReviewRepository.findAllByAccountAndIsVisible(account, true).stream()
                .map(MyReviewResponse::ofMyChildCareReview).toList());
        reviewResponseList.addAll(childCenterReviewRepository.findAllByAccountAndIsVisible(account, true).stream()
                .map(MyReviewResponse::ofMyChildCenterReview).toList());
        reviewResponseList.sort((o1, o2) -> o2.createdAt().compareTo(o1.createdAt()));
        return MyReviewListResponse.of(reviewResponseList);
    }

    public boolean checkWriter(Account currentAccount, Account writer) {
        return currentAccount.getId() == writer.getId();
    }
}
