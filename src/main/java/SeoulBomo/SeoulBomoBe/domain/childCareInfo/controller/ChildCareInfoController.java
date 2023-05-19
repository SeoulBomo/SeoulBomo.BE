package SeoulBomo.SeoulBomoBe.domain.childCareInfo.controller;

import SeoulBomo.SeoulBomoBe.domain.childCareInfo.service.ChildCareInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
