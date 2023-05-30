package SeoulBomo.SeoulBomoBe.domain.childCenterInfo.service;

import SeoulBomo.SeoulBomoBe.common.model.Borough;
import SeoulBomo.SeoulBomoBe.common.dto.PageResponse;
import SeoulBomo.SeoulBomoBe.common.exception.StatusCode;
import SeoulBomo.SeoulBomoBe.domain.childCareInfo.model.ChildCareInfo;
import SeoulBomo.SeoulBomoBe.domain.childCareInfo.repositrory.ChildCareInfoRepository;
import SeoulBomo.SeoulBomoBe.domain.childCenterInfo.exception.ChildCenterInfoException;
import SeoulBomo.SeoulBomoBe.domain.childCenterInfo.repository.ChildCenterInfoRepository;
import SeoulBomo.SeoulBomoBe.domain.childCenterInfo.model.CenterType;
import SeoulBomo.SeoulBomoBe.domain.childCenterInfo.model.ChildCenterInfo;
import SeoulBomo.SeoulBomoBe.domain.like.repository.ChildCenterLikeRepository;
import SeoulBomo.SeoulBomoBe.domain.review.repository.ChildCenterReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static SeoulBomo.SeoulBomoBe.domain.childCenterInfo.dto.ChildCenterInfoDto.*;

@Service
public class ChildCenterInfoService {
    @Autowired
    private ChildCenterInfoRepository childCenterInfoRepository;
    @Autowired
    private ChildCareInfoRepository childCareInfoRepository;
    @Autowired
    private ChildCenterReviewRepository childCenterReviewRepository;
    @Autowired
    private ChildCenterLikeRepository childCenterLikeRepository;

    public List<Object> findBoroughList(String borough) {
        Borough name = Borough.getName(borough);
        List<Object> centerInfoList = new ArrayList<>();

        for(CenterType center : CenterType.values()){
            List<ChildCenterInfo> temp = childCenterInfoRepository.findTop3ByBoroughAndCenterType(name, center);
            centerInfoList.add(temp);
        }

        return centerInfoList;
    }

    public List<Object> searchKeywordLists(String keyword) {
        List<ChildCenterInfo> inCenterInfoList = childCenterInfoRepository.findTop3ByAddressORNameContaining(keyword);
        List<ChildCareInfo> inCareInfoList = childCareInfoRepository.findTop3ByAddressORNameContaining(keyword);

        List<Object> lists = new ArrayList<>();
        lists.add(inCenterInfoList);
        lists.add(inCareInfoList);

        return lists;
    }

    public ChildCenterDetailResponse findVerifiedCenterInfo(Long id) {
        ChildCenterInfo centerInfo = childCenterInfoRepository.findById(id)
                .orElseThrow(() -> new ChildCenterInfoException(StatusCode.NOT_FOUND_CHILDCARE));
        return ChildCenterDetailResponse.of(centerInfo, childCenterReviewRepository.countByChildCenterInfo(centerInfo), childCenterLikeRepository.countByChildCenterInfo(centerInfo.getId()));
    }

    public PageResponse<ChildCenterBoroughListResponse> findBoroughCenterList(Pageable pageable, String borough, String centerType) {
        return PageResponse.of(childCenterInfoRepository.findAllByBoroughAndCenterType(pageable, Borough.findByDetail(borough), CenterType.getName(centerType)).map(ChildCenterBoroughListResponse::of));
    }

    public PageResponse<ChildCenterKeywordListResponse> findKeywordCenterList(Pageable pageable, String keyword) {
        if(keyword.isBlank() || keyword == null || keyword.isEmpty() || keyword.equals("")){
            return PageResponse.of(childCenterInfoRepository.findAllByAddressORNameContaining(pageable, null).map(ChildCenterKeywordListResponse::of));
        } else {
            return PageResponse.of(childCenterInfoRepository.findAllByAddressORNameContaining(pageable, keyword).map(ChildCenterKeywordListResponse::of));
        }
    }

    public ChildCenterInfo findChildCenterInfo(Long childCenterInfoId) {
        return childCenterInfoRepository.findById(childCenterInfoId)
                .orElseThrow(() -> new ChildCenterInfoException(StatusCode.NOT_FOUND_CHILDCENTER));
    }
}