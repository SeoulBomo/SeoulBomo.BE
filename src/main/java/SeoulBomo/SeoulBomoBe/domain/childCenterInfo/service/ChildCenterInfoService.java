package SeoulBomo.SeoulBomoBe.domain.childCenterInfo.service;

import SeoulBomo.SeoulBomoBe.common.Borough;
import SeoulBomo.SeoulBomoBe.common.dto.PageResponse;
import SeoulBomo.SeoulBomoBe.common.exception.StatusCode;
import SeoulBomo.SeoulBomoBe.domain.childCareInfo.model.ChildCareInfo;
import SeoulBomo.SeoulBomoBe.domain.childCareInfo.repositrory.ChildCareInfoRepository;
import SeoulBomo.SeoulBomoBe.domain.childCenterInfo.exception.ChildCenterInfoException;
import SeoulBomo.SeoulBomoBe.domain.childCenterInfo.repository.ChildCenterInfoRepository;
import SeoulBomo.SeoulBomoBe.domain.childCenterInfo.model.CenterType;
import SeoulBomo.SeoulBomoBe.domain.childCenterInfo.model.ChildCenterInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static SeoulBomo.SeoulBomoBe.domain.childCareInfo.dto.ChildCareInfoDto.*;
import static SeoulBomo.SeoulBomoBe.domain.childCenterInfo.dto.BoroughDto.*;

@Service
public class ChildCenterInfoService {

    @Autowired
    private ChildCenterInfoRepository childCenterInfoRepository;

    @Autowired
    private ChildCareInfoRepository childCareInfoRepository;

    public List<Object> findBoroughList(String borough) {
        Borough name = Borough.getName(borough);
        List<Object> centerInfoList = new ArrayList<>();

        for(CenterType center : CenterType.values()){
            List<ChildCenterInfo> temp = childCenterInfoRepository.findTop5ByBoroughAndCenterType(name, center);
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

    public ChildCenterInfo findVerifiedCenterInfo(Long id) {
        Optional<ChildCenterInfo> centerInfo = childCenterInfoRepository.findById(id);
        ChildCenterInfo result = centerInfo.orElseThrow(() ->
                new ChildCenterInfoException(StatusCode.NOT_FOUND_CHILDCENTER));

        return result;
    }

    public PageResponse<ChildCenterBoroughListResponse> findBoroughCenterList(Pageable pageable, ChildCenterBoroughListRequest childCenterBoroughListRequest) {
        Borough borough = Borough.getName(childCenterBoroughListRequest.borough());
        CenterType centerType = CenterType.getName(childCenterBoroughListRequest.centerType());

        return PageResponse.of(childCenterInfoRepository.findAllByBoroughAndCenterType(pageable, borough, centerType).map(ChildCenterBoroughListResponse::of));
    }

    public PageResponse<ChildCenterKeywordListResponse> findKeywordCenterList(Pageable pageable, String keyword) {
        return PageResponse.of(childCenterInfoRepository.findAllByAddressORNameContaining(pageable, keyword).map(ChildCenterKeywordListResponse::of));
    }

    public PageResponse<ChildCareInfoKeywordListResponse> findKeywordInfoList(Pageable pageable, String keyword) {
        return PageResponse.of(childCareInfoRepository.findAllByAddressORNameContaining(pageable, keyword).map(ChildCareInfoKeywordListResponse::of));
    }
}
