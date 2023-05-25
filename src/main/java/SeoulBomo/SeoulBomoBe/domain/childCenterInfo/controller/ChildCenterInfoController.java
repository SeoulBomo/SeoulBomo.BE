package SeoulBomo.SeoulBomoBe.domain.childCenterInfo.controller;

import SeoulBomo.SeoulBomoBe.common.dto.PageResponse;
import SeoulBomo.SeoulBomoBe.domain.childCenterInfo.service.ChildCenterInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static SeoulBomo.SeoulBomoBe.domain.childCenterInfo.dto.ChildCenterInfoDto.*;

@RestController
@RequestMapping("/api/v1/child-center-info")
public class ChildCenterInfoController {

    @Autowired
    private ChildCenterInfoService childCenterInfoService;

    @GetMapping("/{child-center-info-id}")
    public ResponseEntity getChildCenterInfo(@PathVariable("child-center-info-id") Long id) {
        ChildCenterDetailResponse info = childCenterInfoService.findVerifiedCenterInfo(id);
        return new ResponseEntity(info, HttpStatus.OK);
    }

    @GetMapping("/list/borough")
    public ResponseEntity getBoroughCenterList(
            @PageableDefault Pageable pageable,
            @RequestParam("borough") String borough,
            @RequestParam("center-type") String centerType
    ) {
        PageResponse<ChildCenterBoroughListResponse> result = childCenterInfoService.findBoroughCenterList(pageable, borough, centerType);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @GetMapping("/list/keyword")
    public ResponseEntity getKeyWordCenterList(
            @PageableDefault Pageable pageable,
            @RequestParam("keyword") String keyword
    ) {
        PageResponse<ChildCenterKeywordListResponse> result = childCenterInfoService.findKeywordCenterList(pageable, keyword);

        return new ResponseEntity(result, HttpStatus.OK);
    }
}
