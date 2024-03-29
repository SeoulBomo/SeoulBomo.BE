package SeoulBomo.SeoulBomoBe.domain.childCareInfo.controller;

import SeoulBomo.SeoulBomoBe.common.dto.PageResponse;
import SeoulBomo.SeoulBomoBe.domain.childCareInfo.dto.ChildCareInfoDto.*;
import SeoulBomo.SeoulBomoBe.domain.childCareInfo.service.ChildCareInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/child-care-info")
@RequiredArgsConstructor
@Slf4j
public class ChildCareInfoController {

    private final ChildCareInfoService childCareInfoService;

    @PostMapping("/data")
    public ResponseEntity<String> saveChildCareInfo() {
        return ResponseEntity.ok(childCareInfoService.saveChildCareInfo());
    }

    @GetMapping("/list")
    public ResponseEntity<PageResponse<ChildCareInfoListResponse>> getChildCareInfoList(
            @PageableDefault Pageable pageable,
            @RequestParam(value = "info-type") String infoType,
            @RequestParam(value = "age-type") String ageType
    ) {
        return ResponseEntity.ok(childCareInfoService.getChildCareInfoList(pageable, infoType, ageType));
    }

    @GetMapping("/{child-care-info-id}")
    public ResponseEntity<ChildCareInfoResponse> getChildCareInfo(@PathVariable("child-care-info-id") Long childCareInfoId) {
        return ResponseEntity.ok(childCareInfoService.getChildCareInfo(childCareInfoId));
    }

    @GetMapping("/popularity")
    public ResponseEntity<PopularChildCareInfoRespose> getChildCareInfoListByPopularity() {
        return ResponseEntity.ok(childCareInfoService.getChildCareInfoListByPopularity());
    }

    @GetMapping("/list/keyword")
    public ResponseEntity<PageResponse<ChildCareInfoKeywordListResponse>> getKeyWordCareInfoList(
            @PageableDefault Pageable pageable,
            @RequestParam("keyword") String keyword
    ){
        return ResponseEntity.ok(childCareInfoService.findKeywordInfoList(pageable, keyword));
    }
}
