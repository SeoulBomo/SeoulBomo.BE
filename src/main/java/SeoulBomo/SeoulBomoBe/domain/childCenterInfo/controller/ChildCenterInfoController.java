package SeoulBomo.SeoulBomoBe.domain.childCenterInfo.controller;

import SeoulBomo.SeoulBomoBe.common.dto.PageResponse;
import SeoulBomo.SeoulBomoBe.domain.childCenterInfo.model.ChildCenterInfo;
import SeoulBomo.SeoulBomoBe.domain.childCenterInfo.service.ChildCenterInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static SeoulBomo.SeoulBomoBe.domain.childCareInfo.dto.ChildCareInfoDto.*;
import static SeoulBomo.SeoulBomoBe.domain.childCenterInfo.dto.ChildCenterInfoDto.*;

@RestController
public class ChildCenterInfoController {

    @Autowired
    private ChildCenterInfoService childCenterInfoService;

    @GetMapping("/api/v1/search/borough-center")
    public ResponseEntity getBoroughCenter(@RequestParam("borough") String borough){
        List<Object> boroughList = childCenterInfoService.findBoroughList(borough);
        return new ResponseEntity(boroughList, HttpStatus.OK);
    }

    @GetMapping("/api/v1/search/keyword")
    public ResponseEntity getKeyWordCenter(@RequestParam("keyword") String keyword){
        List<Object> keywordList = childCenterInfoService.searchKeywordLists(keyword);
        return new ResponseEntity(keywordList, HttpStatus.OK);
    }

    @GetMapping("/api/v1/child-center/detail")
    public ResponseEntity getChildCenterInfo(@RequestParam("id") Long id){
        ChildCenterInfo info = childCenterInfoService.findVerifiedCenterInfo(id);
        return new ResponseEntity(info, HttpStatus.OK);
    }

    @GetMapping("/api/v1/borough/center-list")
    public ResponseEntity getBoroughCenterList(
            @PageableDefault Pageable pageable,
            @RequestBody ChildCenterBoroughListRequest childCenterBoroughListRequest
            ){
        PageResponse<ChildCenterBoroughListResponse> result =
                childCenterInfoService.findBoroughCenterList(pageable, childCenterBoroughListRequest);

        return new ResponseEntity(result, HttpStatus.OK);
    }

    @GetMapping("/api/v1/keyword/center-list")
    public ResponseEntity getKeyWordCenterList(
            @PageableDefault Pageable pageable,
            @RequestParam("keyword") String keyword
    ){
        PageResponse<ChildCenterKeywordListResponse> result = childCenterInfoService.findKeywordCenterList(pageable, keyword);

        return new ResponseEntity(result, HttpStatus.OK);
    }

    @GetMapping("/api/v1/keyword/care-info-list")
    public ResponseEntity getKeyWordCareInfoList(
            @PageableDefault Pageable pageable,
            @RequestParam("keyword") String keyword
    ){
        PageResponse<ChildCareInfoKeywordListResponse> result = childCenterInfoService.findKeywordInfoList(pageable, keyword);

        return new ResponseEntity(result, HttpStatus.OK);
    }

}
