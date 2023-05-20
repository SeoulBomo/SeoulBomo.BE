package SeoulBomo.SeoulBomoBe.domain.childCenterInfo.controller;

import SeoulBomo.SeoulBomoBe.domain.childCenterInfo.service.DataService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class DataController {

    @Autowired
    private DataService dataService;

    @PostMapping("/api/v1/child-center/pre-school")
    public String preSchoolInfo(){
        return dataService.savePreSchoolInfo();
    }

    @PostMapping("/api/v1/child-center/share-center")
    public String shareCenterInfo(){
        return dataService.saveShareCenterInfo();
    }

    @PostMapping("/api/v1/child-center/care-center")
    public String careCenterInfo(){
        return dataService.saveCareCenterInfo();
    }
}
