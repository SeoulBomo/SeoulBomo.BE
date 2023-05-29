package SeoulBomo.SeoulBomoBe.domain.childCareInfo.service;

import SeoulBomo.SeoulBomoBe.common.model.Borough;
import SeoulBomo.SeoulBomoBe.common.dto.PageResponse;
import SeoulBomo.SeoulBomoBe.common.exception.StatusCode;
import SeoulBomo.SeoulBomoBe.domain.childCareInfo.dto.ChildCareInfoDto.*;
import SeoulBomo.SeoulBomoBe.domain.childCareInfo.exception.ChildCareInfoException;
import SeoulBomo.SeoulBomoBe.domain.childCareInfo.model.AgeType;
import SeoulBomo.SeoulBomoBe.domain.childCareInfo.model.ChildCareInfo;
import SeoulBomo.SeoulBomoBe.domain.childCareInfo.model.InfoType;
import SeoulBomo.SeoulBomoBe.domain.childCareInfo.repositrory.ChildCareInfoRepository;
import SeoulBomo.SeoulBomoBe.domain.like.repository.ChildCareLikeRepository;
import SeoulBomo.SeoulBomoBe.domain.review.repository.ChildCareReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChildCareInfoService {

    private final ChildCareInfoRepository childCareInfoRepository;
    private final ChildCareReviewRepository childCareReviewRepository;
    private final ChildCareLikeRepository childCareLikeRepository;

    public PageResponse<ChildCareInfoListResponse> getChildCareInfoList(Pageable pageable, String infoType, String ageType) {
        InfoType info = InfoType.valueOf(infoType);
        AgeType age = AgeType.valueOf(ageType);
        if (age.equals(AgeType.ALL))
            return PageResponse.of(childCareInfoRepository.findAllByInfoType(pageable, info).map(ChildCareInfoListResponse::of));

        return PageResponse.of(childCareInfoRepository.findAllByInfoTypeAndAgeType(pageable, info, age).map(ChildCareInfoListResponse::of));
    }

    public String saveChildCareInfo() {
        for (int j = 0; j < 5; j++) {
            InfoType infoType = InfoType.values()[j];
            try {
                URL url = new URL(infoType.getUrl());
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Content-type", "application/json");
                System.out.println("Response code: " + conn.getResponseCode());
                BufferedReader br;
                if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300)
                    br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                else
                    br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null)
                    sb.append(line);
                br.close();
                conn.disconnect();

                JSONParser parser = new JSONParser();
                JSONObject jsonObject = (JSONObject) parser.parse(sb.toString());
                System.out.println(jsonObject);
                JSONObject infoObj = (JSONObject) jsonObject.get(infoType.getType());
                JSONArray preInfoArray = (JSONArray) infoObj.get("row");

                for (int i = 0; i < preInfoArray.size(); i++) {
                    try {
                        JSONObject temp = (JSONObject) preInfoArray.get(i);
                        String boroughValue = (String) temp.get("ATDRC_NM");
                        Borough borough = Borough.findByDetail(boroughValue);
                        if (borough == null) continue;
                        String ageTypeValue = (String) temp.get("AGE_SE_NM");
                        AgeType ageType = AgeType.findByDetail(ageTypeValue);
                        if (ageType == null) ageType = AgeType.ALL;
                        ChildCareInfo childCareInfo = new ChildCareInfo(
                                (String) temp.get("CLTUR_EVENT_ETC_NM"),
                                infoType,
                                borough,
                                ageType,
                                (String) temp.get("Y_CRDNT_VALUE"),
                                (String) temp.get("X_CRDNT_VALUE"),
                                temp.get("BASS_ADRES") + (String) temp.get("DETAIL_ADRES"),
                                (String) temp.get("PLGRDCO"),
                                (temp.get("RNTFEE_FREE_AT")).equals("Y"),
                                (String) temp.get("RNTFEE"),
                                (String) temp.get("EVENT_PD_BGNDE"),
                                (String) temp.get("EVENT_PD_ENDDE"),
                                (String) temp.get("GUIDANCE_URL"),
                                "EVENT_FCLTY_NM");
                        childCareInfoRepository.save(childCareInfo);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        i++;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "success";
    }

    public ChildCareInfoResponse getChildCareInfo(Long childCareInfoId) {
        ChildCareInfo childCareInfo = findChildCareInfo(childCareInfoId);
        return ChildCareInfoResponse.of(childCareInfo, childCareReviewRepository.countByChildCareInfo(childCareInfo), childCareLikeRepository.countByChildCareInfo(childCareInfo.getId()));
    }

    public PopularChildCareInfoRespose getChildCareInfoListByPopularity() {
        return PopularChildCareInfoRespose.of(childCareInfoRepository.findTop7ByOrderById().stream().map(ChildCareInfoSimpleResponse::of).toList());
    }

    public PageResponse<ChildCareInfoKeywordListResponse> findKeywordInfoList(Pageable pageable, String keyword) {
        return PageResponse.of(childCareInfoRepository.findAllByAddressORNameContaining(pageable, keyword).map(ChildCareInfoKeywordListResponse::of));
    }

    public ChildCareInfo findChildCareInfo(Long childCareInfoId) {
        return childCareInfoRepository.findById(childCareInfoId)
                .orElseThrow(() -> new ChildCareInfoException(StatusCode.NOT_FOUND_CHILDCARE));
    }
}
