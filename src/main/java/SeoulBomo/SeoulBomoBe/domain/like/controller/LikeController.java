package SeoulBomo.SeoulBomoBe.domain.like.controller;

import SeoulBomo.SeoulBomoBe.domain.account.model.Account;
import SeoulBomo.SeoulBomoBe.domain.account.service.AccountService;
import SeoulBomo.SeoulBomoBe.domain.like.service.LikeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static SeoulBomo.SeoulBomoBe.domain.like.dto.ChildCareLikeDto.*;

@RestController
@RequestMapping("/api/v1/like")
@AllArgsConstructor
public class LikeController {

    private final LikeService likeService;

    private final AccountService accountService;

    @PostMapping ("/care-info")
    public ResponseEntity careInfoLike(@RequestBody ChildCareLikeRequest childCareLikeRequest){
        String result = likeService.childCareInfoLike(childCareLikeRequest);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @PostMapping ("/center-info")
    public ResponseEntity centerInfoLike(@RequestBody ChildCenterLikeRequest childCenterLikeRequest){
        String result = likeService.childCenterInfoLike(childCenterLikeRequest);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @GetMapping("/me")
    public ResponseEntity myLike(@RequestParam("userId") Long id){
        //Account account = accountService.getCurrentAccount();
        //List<Object> list = likeService.getLikeList(account.getId());
        List<Object> list = likeService.getLikeList(id);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
