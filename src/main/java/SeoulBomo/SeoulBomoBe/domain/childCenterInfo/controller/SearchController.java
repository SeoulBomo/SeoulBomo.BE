package SeoulBomo.SeoulBomoBe.domain.childCenterInfo.controller;

import SeoulBomo.SeoulBomoBe.domain.childCenterInfo.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping("/api/v1/search/borough-center-list")
    public ResponseEntity getBoroughCenter(@RequestParam("borough") String borough){
        List<Object> boroughList = searchService.findBoroughList(borough);
        return new ResponseEntity(boroughList, HttpStatus.OK);
    }

    @GetMapping("/api/v1/search/keyword")
    public ResponseEntity getKeyWordList(@RequestParam("keyword") String keyword){
        List<Object> keywordList = searchService.searchKeywordLists(keyword);
        return new ResponseEntity(keywordList, HttpStatus.OK);
    }

}
