package SeoulBomo.SeoulBomoBe.domain.childCenterInfo.service;

import SeoulBomo.SeoulBomoBe.common.Borough;
import SeoulBomo.SeoulBomoBe.domain.childCenterInfo.repository.DataRepository;
import SeoulBomo.SeoulBomoBe.domain.childCenterInfo.model.CenterType;
import SeoulBomo.SeoulBomoBe.domain.childCenterInfo.model.ChildCenterInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {

    @Autowired
    private DataRepository dataRepository;

    public List<Object> findBoroughList(String borough) {
        String name = String.valueOf(Borough.getName(borough));
        List<Object> centerInfoList = new ArrayList<>();

        for(CenterType type : CenterType.values()){
            String center = String.valueOf(type);
            List<ChildCenterInfo> temp = dataRepository.findByBoroughAndCenterType(name, center);
            centerInfoList.add(temp);
        }

        return centerInfoList;
    }

    public List<Object> searchKeywordLists(String keyword) {
        List<ChildCenterInfo> inAddressList = dataRepository.findByAddressContaining(keyword);
        List<ChildCenterInfo> inNameList = dataRepository.findByNameContaining(keyword);

        List<Object> lists = new ArrayList<>();
        lists.add(inAddressList);
        lists.add(inNameList);

        return lists;
    }
}
