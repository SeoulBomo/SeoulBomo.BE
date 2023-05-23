package SeoulBomo.SeoulBomoBe.domain.like.service;

import SeoulBomo.SeoulBomoBe.common.exception.StatusCode;
import SeoulBomo.SeoulBomoBe.domain.account.AccountRepository;
import SeoulBomo.SeoulBomoBe.domain.account.exception.AccountException;
import SeoulBomo.SeoulBomoBe.domain.account.model.Account;
import SeoulBomo.SeoulBomoBe.domain.childCareInfo.exception.ChildCareInfoException;
import SeoulBomo.SeoulBomoBe.domain.childCareInfo.model.ChildCareInfo;
import SeoulBomo.SeoulBomoBe.domain.childCareInfo.repositrory.ChildCareInfoRepository;
import SeoulBomo.SeoulBomoBe.domain.childCenterInfo.exception.ChildCenterInfoException;
import SeoulBomo.SeoulBomoBe.domain.childCenterInfo.model.ChildCenterInfo;
import SeoulBomo.SeoulBomoBe.domain.childCenterInfo.repository.ChildCenterInfoRepository;
import SeoulBomo.SeoulBomoBe.domain.like.exception.LikeException;
import SeoulBomo.SeoulBomoBe.domain.like.model.ChildCareLike;
import SeoulBomo.SeoulBomoBe.domain.like.model.ChildCenterLike;
import SeoulBomo.SeoulBomoBe.domain.like.repository.ChildCareLikeRepository;
import SeoulBomo.SeoulBomoBe.domain.like.repository.ChildCenterLikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static SeoulBomo.SeoulBomoBe.domain.like.dto.ChildCareLikeDto.*;

@Service
public class LikeService {

    @Autowired
    private ChildCareLikeRepository childCareLikeRepository;

    @Autowired
    private ChildCenterLikeRepository childCenterLikeRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ChildCareInfoRepository childCareInfoRepository;

    @Autowired
    private ChildCenterInfoRepository childCenterInfoRepository;

    public String childCareInfoLike(ChildCareLikeRequest childCareLikeRequest) {
        Long userId = childCareLikeRequest.userId();
        Long infoId = childCareLikeRequest.careInfoId();

        boolean likeInfo = childCareLikeRepository.existsByAccount_IdAndChildCareInfo_Id(userId, infoId);

        if(!likeInfo){ //정보가 없는 경우
            Account account = accountRepository.findById(userId)
                    .orElseThrow(() -> new AccountException(StatusCode.NOT_FOUND_ACCOUNT));
            ChildCareInfo childCareInfo = childCareInfoRepository.findById(infoId)
                    .orElseThrow(() -> new ChildCareInfoException(StatusCode.NOT_FOUND_CHILDCARE));
            childCareLikeRepository.save(new ChildCareLike(account, childCareInfo));
        }
        else if(likeInfo){ //정보가 있는 경우
            ChildCareLike childCareLike = childCareLikeRepository.findByAccount_IdAndChildCareInfo_Id(userId, infoId)
                    .orElseThrow(() -> new LikeException(StatusCode.NOT_FOUND_LIKE));
            if(childCareLike.isDeleted()){ // deleted 값이 true인 경우
                childCareLikeRepository.updateDeletedFalse(childCareLike.getId());
            }
            else if(!childCareLike.isDeleted()){ // deleted 값이 false인 경우
                childCareLikeRepository.deleteById(childCareLike.getId());
            }
        }
        return "SUCCESS";
    }

    public String childCenterInfoLike(ChildCenterLikeRequest childCenterLikeRequest) {
        Long userId = childCenterLikeRequest.userId();
        Long centerId = childCenterLikeRequest.centerId();

        boolean centerInfo = childCenterLikeRepository.existsByAccount_IdAndChildCenterInfo_Id(userId, centerId);

        if(!centerInfo){ //정보가 없는 경우
            Account account = accountRepository.findById(userId)
                    .orElseThrow(() -> new AccountException(StatusCode.NOT_FOUND_ACCOUNT));
            ChildCenterInfo childCenterInfo = childCenterInfoRepository.findById(centerId)
                    .orElseThrow(() -> new ChildCenterInfoException(StatusCode.NOT_FOUND_CHILDCENTER));
            childCenterLikeRepository.save(new ChildCenterLike(account, childCenterInfo));
        }
        else if(centerInfo){ //정보가 있는 경우
            ChildCenterLike childCenterLike = childCenterLikeRepository.findByAccount_IdAndChildCenterInfo_Id(userId, centerId)
                    .orElseThrow(() -> new LikeException(StatusCode.NOT_FOUND_LIKE));
            if(childCenterLike.isDeleted()){ // deleted 값이 true인 경우
                childCenterLikeRepository.updateDeletedFalse(childCenterLike.getId());
            }
            else if(!childCenterLike.isDeleted()){ // deleted 값이 false인 경우
                childCenterLikeRepository.deleteById(childCenterLike.getId());
            }
        }
        return "SUCCESS";
    }
}
