package SeoulBomo.SeoulBomoBe.domain.like.controller;

import SeoulBomo.SeoulBomoBe.domain.like.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static SeoulBomo.SeoulBomoBe.domain.like.dto.ChildCareLikeDto.*;

@RestController
public class LikeController {

    @Autowired
    private LikeService likeService;

    @PostMapping ("/api/v1/care-info/like")
    public ResponseEntity careInfoLike(@RequestBody ChildCareLikeRequest childCareLikeRequest){
        String result = likeService.childCareInfoLike(childCareLikeRequest);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @PostMapping ("/api/v1/center-info/like")
    public ResponseEntity centerInfoLike(@RequestBody ChildCenterLikeRequest childCenterLikeRequest){
        String result = likeService.childCenterInfoLike(childCenterLikeRequest);
        return new ResponseEntity(result, HttpStatus.OK);
    }
}