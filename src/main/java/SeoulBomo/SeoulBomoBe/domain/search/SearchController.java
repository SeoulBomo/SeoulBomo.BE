package SeoulBomo.SeoulBomoBe.domain.search;

import SeoulBomo.SeoulBomoBe.domain.childCenterInfo.service.ChildCenterInfoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/search")
@AllArgsConstructor
public class SearchController {

    private ChildCenterInfoService childCenterInfoService;

    @GetMapping("borough-center")
    public ResponseEntity getBoroughCenter(@RequestParam("borough") String borough){
        List<Object> boroughList = childCenterInfoService.findBoroughList(borough);
        return new ResponseEntity(boroughList, HttpStatus.OK);
    }

    @GetMapping("/keyword")
    public ResponseEntity getKeyWordCenter(@RequestParam("keyword") String keyword){
        List<Object> keywordList = childCenterInfoService.searchKeywordLists(keyword);
        return new ResponseEntity(keywordList, HttpStatus.OK);
    }

}
