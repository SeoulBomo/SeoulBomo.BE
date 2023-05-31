package SeoulBomo.SeoulBomoBe.common.dummy;

import SeoulBomo.SeoulBomoBe.domain.account.model.Account;
import SeoulBomo.SeoulBomoBe.domain.account.model.SocialType;
import SeoulBomo.SeoulBomoBe.domain.account.repository.AccountRepository;
import SeoulBomo.SeoulBomoBe.domain.childCareInfo.repositrory.ChildCareInfoRepository;
import SeoulBomo.SeoulBomoBe.domain.childCenterInfo.repository.ChildCenterInfoRepository;
import SeoulBomo.SeoulBomoBe.domain.like.model.ChildCareLike;
import SeoulBomo.SeoulBomoBe.domain.like.repository.ChildCareLikeRepository;
import SeoulBomo.SeoulBomoBe.domain.like.repository.ChildCenterLikeRepository;
import SeoulBomo.SeoulBomoBe.domain.review.model.ChildCareReview;
import SeoulBomo.SeoulBomoBe.domain.review.model.ChildCenterReview;
import SeoulBomo.SeoulBomoBe.domain.review.repository.ChildCareReviewRepository;
import SeoulBomo.SeoulBomoBe.domain.review.repository.ChildCenterReviewRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
@Slf4j
@Transactional
@RequiredArgsConstructor
public class DummyData {
    private final AccountRepository accountRepository;
    private final ChildCareInfoRepository childCareInfoRepository;
    private final ChildCenterInfoRepository childCenterInfoRepository;
    private final ChildCenterReviewRepository childCenterReviewRepository;
    private final ChildCareReviewRepository childCareReviewRepository;
    private final ChildCenterLikeRepository childCenterLikeRepository;
    private final ChildCareLikeRepository childCareLikeRepository;


    @PostConstruct
    public void dummyData() {
        accountDummy();
        ReivewDummy();
        LikeDummy();
    }

    public void accountDummy() {
        if (accountRepository.count() > 0) {
            log.info("[00] 회원이 이미 존재합니다");
            return;
        }

        String[] names = {"김정우", "이상민", "이준혁", "김민수", "김민준", "김준우", "김지민", "정소민", "김지훈", "류수민"};
        for (int i = 0; i < 10; i++) {
            Account account = Account.builder()
                    .email("dummy" + i + "@seoulbomo.com")
                    .name(names[i])
                    .image("https://ssl.pstatic.net/static/pwe/address/img_profile.png")
                    .role("ROLE_USER")
                    .socialType(SocialType.KAKAO)
                    .build();
            accountRepository.save(account);
        }
    }


    public void ReivewDummy() {
        if (childCenterReviewRepository.count() > 0 && childCareReviewRepository.count() > 0) {
            log.info("[01] 리뷰가 이미 존재합니다");
            return;
        }
        Account account1 = accountRepository.findById(1L).get();
        Account account2 = accountRepository.findById(2L).get();
        Account account3 = accountRepository.findById(3L).get();
        Account account4 = accountRepository.findById(4L).get();
        Account account5 = accountRepository.findById(5L).get();
        Account account6 = accountRepository.findById(6L).get();
        Account account7 = accountRepository.findById(7L).get();
        Account account8 = accountRepository.findById(8L).get();
        Account account9 = accountRepository.findById(9L).get();
        Account account10 = accountRepository.findById(10L).get();

        for (int i = 1; i < 1482; i = i + 2) {
            ChildCareReview childCareReview = ChildCareReview.builder()
                    .account(account1)
                    .childCareInfo(childCareInfoRepository.findById((long) i).get())
                    .content("우와 좋은 정보 감사합니당!")
                    .build();
            childCareReviewRepository.save(childCareReview);
        }
        for (int i = 2; i < 1482; i = i + 2) {
            ChildCareReview childCareReview = ChildCareReview.builder()
                    .account(account2)
                    .childCareInfo(childCareInfoRepository.findById((long) i).get())
                    .content("오 여기 다음에 가봐야겠네요 :)")
                    .build();
            childCareReviewRepository.save(childCareReview);
        }
        for (int i = 1; i < 30; i = i + 3) {
            ChildCareReview childCareReview = ChildCareReview.builder()
                    .account(account3)
                    .childCareInfo(childCareInfoRepository.findById((long) i).get())
                    .content("여기 되게 괜찮더라구요 추천합니당")
                    .build();
            childCareReviewRepository.save(childCareReview);
        }
        for (int i = 2; i < 30; i = i + 3) {
            ChildCareReview childCareReview = ChildCareReview.builder()
                    .account(account4)
                    .childCareInfo(childCareInfoRepository.findById((long) i).get())
                    .content("어린아이 데리고 가기 좋은 곳이네요!!")
                    .build();
            childCareReviewRepository.save(childCareReview);
        }
        for (int i = 3; i < 30; i = i + 3) {
            ChildCareReview childCareReview = ChildCareReview.builder()
                    .account(account5)
                    .childCareInfo(childCareInfoRepository.findById((long) i).get())
                    .content("그냥 가볍게 다녀오기 좋았어요!!")
                    .build();
            childCareReviewRepository.save(childCareReview);
        }
        for (int i = 1; i < 10; i++) {
            ChildCareReview childCareReview = ChildCareReview.builder()
                    .account(account6)
                    .childCareInfo(childCareInfoRepository.findById((long) i).get())
                    .content("여기 주말에 사람많아요 ㅜㅜ")
                    .build();
            childCareReviewRepository.save(childCareReview);
        }
        for (int i = 1; i < 10; i++) {
            ChildCareReview childCareReview = ChildCareReview.builder()
                    .account(account7)
                    .childCareInfo(childCareInfoRepository.findById((long) i).get())
                    .content("good~~!")
                    .build();
            childCareReviewRepository.save(childCareReview);
        }
        for (int i = 1; i < 10; i = i + 2) {
            ChildCareReview childCareReview = ChildCareReview.builder()
                    .account(account10)
                    .childCareInfo(childCareInfoRepository.findById((long) i).get())
                    .content("애들이랑 가야겠다")
                    .build();
            childCareReviewRepository.save(childCareReview);
        }
        for (int i = 2; i < 10; i = i + 2) {
            ChildCareReview childCareReview = ChildCareReview.builder()
                    .account(account8)
                    .childCareInfo(childCareInfoRepository.findById((long) i).get())
                    .content("좋았습니다~")
                    .build();
            childCareReviewRepository.save(childCareReview);
        }
        for (int i = 1; i < 10; i = i + 3) {
            ChildCareReview childCareReview = ChildCareReview.builder()
                    .account(account9)
                    .childCareInfo(childCareInfoRepository.findById((long) i).get())
                    .content("난 그냥 그랬음...")
                    .build();
            childCareReviewRepository.save(childCareReview);
        }
        for (int i = 1; i < 500; i++) {
            ChildCenterReview childCenterReview = ChildCenterReview.builder()
                    .account(account10)
                    .childCenterInfo(childCenterInfoRepository.findById((long) i).get())
                    .content("친절하시구 좋아용!")
                    .build();
            childCenterReviewRepository.save(childCenterReview);
        }
        for (int i = 600; i < 1200; i++) {
            ChildCenterReview childCenterReview = ChildCenterReview.builder()
                    .account(account1)
                    .childCenterInfo(childCenterInfoRepository.findById((long) i).get())
                    .content("여기 괜찮더라구요")
                    .build();
            childCenterReviewRepository.save(childCenterReview);
        }
        for (int i = 1; i < 500; i++) {
            ChildCenterReview childCenterReview = ChildCenterReview.builder()
                    .account(account10)
                    .childCenterInfo(childCenterInfoRepository.findById((long) i).get())
                    .content("친절하시구 좋아용!")
                    .build();
            childCenterReviewRepository.save(childCenterReview);
        }
        for (int i = 1; i < 50; i = i + 2) {
            ChildCenterReview childCenterReview = ChildCenterReview.builder()
                    .account(account2)
                    .childCenterInfo(childCenterInfoRepository.findById((long) i).get())
                    .content("깔끔해요")
                    .build();
            childCenterReviewRepository.save(childCenterReview);
        }

        for (int i = 1; i < 50; i = i + 2) {
            ChildCenterReview childCenterReview = ChildCenterReview.builder()
                    .account(account3)
                    .childCenterInfo(childCenterInfoRepository.findById((long) i).get())
                    .content("무난무난 합니다 :)")
                    .build();
            childCenterReviewRepository.save(childCenterReview);
        }
    }

    public void LikeDummy() {
        if (childCareLikeRepository.count() > 0 && childCenterLikeRepository.count() > 0) {
            log.info("[02] 좋아요가 이미 존재합니다");
        }
        for (int i = 8; i > 0; i--) {
            for (int j = i; j > 0; j--) {
                ChildCareLike childCareLike = ChildCareLike.builder()
                        .account(accountRepository.findById((long) i).get())
                        .childCareInfo(childCareInfoRepository.findById((long) j).get())
                        .build();
                childCareLikeRepository.save(childCareLike);
            }
        }
    }
}


