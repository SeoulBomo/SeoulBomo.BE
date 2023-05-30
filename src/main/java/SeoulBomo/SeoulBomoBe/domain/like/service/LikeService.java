package SeoulBomo.SeoulBomoBe.domain.like.service;

import SeoulBomo.SeoulBomoBe.common.exception.StatusCode;
import SeoulBomo.SeoulBomoBe.domain.account.exception.AccountException;
import SeoulBomo.SeoulBomoBe.domain.account.model.Account;
import SeoulBomo.SeoulBomoBe.domain.account.repository.AccountRepository;
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
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static SeoulBomo.SeoulBomoBe.domain.like.dto.ChildCareLikeDto.*;

@Service
@AllArgsConstructor
public class LikeService {

    private final ChildCareLikeRepository childCareLikeRepository;

    private final ChildCenterLikeRepository childCenterLikeRepository;

    private final AccountRepository accountRepository;

    private final ChildCareInfoRepository childCareInfoRepository;

    private final ChildCenterInfoRepository childCenterInfoRepository;

    public String childCareInfoLike(ChildCareLikeRequest childCareLikeRequest) {
        Long userId = childCareLikeRequest.userId();
        Long infoId = childCareLikeRequest.careInfoId();

        boolean likeInfo = childCareLikeRepository.existsByAccount_IdAndChildCareInfo_Id(userId, infoId);
        String result = "";

        if(!likeInfo){ //정보가 없는 경우
            Account account = accountRepository.findById(userId)
                    .orElseThrow(() -> new AccountException(StatusCode.NOT_FOUND_ACCOUNT));
            ChildCareInfo childCareInfo = childCareInfoRepository.findById(infoId)
                    .orElseThrow(() -> new ChildCareInfoException(StatusCode.NOT_FOUND_CHILDCARE));
            childCareLikeRepository.save(new ChildCareLike(account, childCareInfo));
            result = "스크랩 저장 완료";
        }
        else if(likeInfo){ //정보가 있는 경우
            ChildCareLike childCareLike = childCareLikeRepository.findByAccount_IdAndChildCareInfo_Id(userId, infoId)
                    .orElseThrow(() -> new LikeException(StatusCode.NOT_FOUND_LIKE));
            if(childCareLike.isDeleted()){ // deleted 값이 true인 경우
                childCareLikeRepository.updateDeletedFalse(childCareLike.getId());
                result = "스크랩 저장 완료";
            }
            else if(!childCareLike.isDeleted()){ // deleted 값이 false인 경우
                childCareLikeRepository.deleteById(childCareLike.getId());
                result = "스크랩 취소 완료";
            }
        }
        return result;
    }

    public String childCenterInfoLike(ChildCenterLikeRequest childCenterLikeRequest) {
        Long userId = childCenterLikeRequest.userId();
        Long centerId = childCenterLikeRequest.centerId();

        boolean likeInfo = childCenterLikeRepository.existsByAccount_IdAndChildCenterInfo_Id(userId, centerId);
        String result = "";

        if(!likeInfo){ //정보가 없는 경우
            Account account = accountRepository.findById(userId)
                    .orElseThrow(() -> new AccountException(StatusCode.NOT_FOUND_ACCOUNT));
            ChildCenterInfo childCenterInfo = childCenterInfoRepository.findById(centerId)
                    .orElseThrow(() -> new ChildCenterInfoException(StatusCode.NOT_FOUND_CHILDCENTER));
            childCenterLikeRepository.save(new ChildCenterLike(account, childCenterInfo));
            result = "스크랩 저장 완료";
        }
        else if(likeInfo){ //정보가 있는 경우
            ChildCenterLike childCenterLike = childCenterLikeRepository.findByAccount_IdAndChildCenterInfo_Id(userId, centerId)
                    .orElseThrow(() -> new LikeException(StatusCode.NOT_FOUND_LIKE));
            if(childCenterLike.isDeleted()){ // deleted 값이 true인 경우
                childCenterLikeRepository.updateDeletedFalse(childCenterLike.getId());
                result = "스크랩 저장 완료";
            }
            else if(!childCenterLike.isDeleted()){ // deleted 값이 false인 경우
                childCenterLikeRepository.deleteById(childCenterLike.getId());
                result = "스크랩 취소 완료";
            }
        }
        return result;
    }

    public List<Object> getLikeList(Long userId) {
        List<ChildCenterLike> centerLikeList = childCenterLikeRepository.findAllByAccountIdAndDeleted(userId, false);
        List<ChildCenterInfo> centerList = new ArrayList<>();
        System.out.println(centerLikeList.size());
        for(ChildCenterLike info : centerLikeList){
            ChildCenterInfo childCenterInfo = childCenterInfoRepository.findById(info.getChildCenterInfo().getId())
                    .orElseThrow(() -> new ChildCenterInfoException(StatusCode.NOT_FOUND_CHILDCENTER));
            centerList.add(childCenterInfo);
        }

        List<ChildCareLike> careLikeList = childCareLikeRepository.findAllByAccountIdAndDeleted(userId, false);
        List<ChildCareInfo> careInfoList = new ArrayList<>();
        System.out.println(careLikeList.size());

        for(ChildCareLike info : careLikeList){
            ChildCareInfo childCareInfo = childCareInfoRepository.findById(info.getChildCareInfo().getId())
                    .orElseThrow(() -> new ChildCenterInfoException(StatusCode.NOT_FOUND_CHILDCARE));
            careInfoList.add(childCareInfo);
        }

        List<Object> result = new ArrayList<>();
        result.add(centerList);
        result.add(careInfoList);

        return result;
    }
}
