package SeoulBomo.SeoulBomoBe.domain.childCenterInfo.service;

import SeoulBomo.SeoulBomoBe.domain.childCenterInfo.model.CenterType;
import SeoulBomo.SeoulBomoBe.domain.childCenterInfo.model.ChildCenterInfo;
import SeoulBomo.SeoulBomoBe.domain.childCenterInfo.repository.ChildCenterInfoRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

@Service
public class DataService {

    @Autowired
    private ChildCenterInfoRepository childCenterInfoRepository;

    public String savePreSchoolInfo() {
        String result = "";

        try{
            URL url = new URL("http://openapi.seoul.go.kr:8088/"
                    + "4c776b6964646b6535394f5965664a"
                    + "/json/ChildCareInfo/1/1000/");

            BufferedReader br;
            br = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            result = br.readLine();

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(result);
            JSONObject preSchoolInfo = (JSONObject) jsonObject.get("ChildCareInfo");
            JSONArray preInfoArray = (JSONArray) preSchoolInfo.get("row");

            for(int i=0; i<preInfoArray.size(); i++){
                try{
                    JSONObject temp = (JSONObject) preInfoArray.get(i);
                    ChildCenterInfo childCenterInfo = new ChildCenterInfo(
                            (String)temp.get("SIGUNNAME"),
                            CenterType.PRE_SCHOOL,
                            (String)temp.get("CRTYPENAME"),
                            (String)temp.get("CRNAME"),
                            (String)temp.get("CRADDR"),
                            (String)temp.get("CRTELNO"),
                            (String)temp.get("CRHOME"),
                            (String)temp.get("NRTRROOMCNT"),
                            (String)temp.get("PLGRDCO"),
                            (String)temp.get("CCTVINSTLCNT"),
                            (String)temp.get("CHCRTESCNT"),
                            (String)temp.get("LA"),
                            (String)temp.get("LO"),
                            "N",
                            "N",
                            "N",
                            "0",
                            (String)temp.get("CRSPEC")
                    );
                    childCenterInfoRepository.save(childCenterInfo);
                } catch (NumberFormatException e){
                    e.printStackTrace();
                    i++;
                } catch (Exception e){
                    throw e;
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public String saveShareCenterInfo(){
        String result = "";

        try{
            URL url = new URL("http://openapi.seoul.go.kr:8088/"
                    + "4c776b6964646b6535394f5965664a"
                    + "/json/TnFcltySttusInfo1004/1/1000/");

            BufferedReader br;
            br = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            result = br.readLine();

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(result);
            JSONObject shareCenterInfo = (JSONObject) jsonObject.get("TnFcltySttusInfo1004");
            JSONArray shareInfoArr = (JSONArray) shareCenterInfo.get("row");

            for(int i=0; i<shareInfoArr.size(); i++){
                try{
                    JSONObject temp = (JSONObject) shareInfoArr.get(i);
                    ChildCenterInfo childCenterInfo = new ChildCenterInfo(
                            (String)temp.get("ATDRC_NM"),
                            CenterType.SHARE_CENTER,
                            null,
                            (String)temp.get("FCLTY_NM"),
                            (String)temp.get("BASS_ADRES") + (String)temp.get("DETAIL_ADRES"),
                            "0",
                            (String)temp.get("SITE_URL"),
                            "0",
                            "0",
                            "0",
                            "0",
                            (String)temp.get("Y_CRDNT_VALUE"),
                            (String)temp.get("X_CRDNT_VALUE"),
                            "N",
                            (String)temp.get("RNTFEE_FREE_AT"),
                            (String)temp.get("SAT_OPER_AT"),
                            (String)temp.get("RNTFEE"),
                            null
                    );
                    childCenterInfoRepository.save(childCenterInfo);
                } catch (NumberFormatException e){
                    e.printStackTrace();
                    i++;
                } catch (Exception e){
                    throw e;
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public String saveCareCenterInfo(){
        String result = "";

        try{
            URL url = new URL("http://openapi.seoul.go.kr:8088/"
                    + "4c776b6964646b6535394f5965664a"
                    + "/json/TnFcltySttusInfo1001/1/1000/");

            BufferedReader br;
            br = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            result = br.readLine();

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(result);
            JSONObject careCenterInfo = (JSONObject) jsonObject.get("TnFcltySttusInfo1001");
            JSONArray careInfoArr = (JSONArray) careCenterInfo.get("row");

            for(int i=0; i<careInfoArr.size(); i++){
                try{
                    JSONObject temp = (JSONObject) careInfoArr.get(i);
                    ChildCenterInfo childCenterInfo = new ChildCenterInfo(
                            (String)temp.get("ATDRC_NM"),
                            CenterType.CARE_CENTER,
                            null,
                            (String)temp.get("FCLTY_NM"),
                            (String)temp.get("BASS_ADRES") + (String)temp.get("DETAIL_ADRES"),
                            "0",
                            null,
                            "0",
                            "0",
                            "0",
                            "0",
                            (String)temp.get("Y_CRDNT_VALUE"),
                            (String)temp.get("X_CRDNT_VALUE"),
                            "N",
                            (String)temp.get("RNTFEE_FREE_AT"),
                            (String)temp.get("SAT_OPER_AT"),
                            (String)temp.get("RNTFEE"),
                            null
                    );
                    childCenterInfoRepository.save(childCenterInfo);
                } catch (NumberFormatException e){
                    e.printStackTrace();
                    i++;
                } catch (Exception e){
                    throw e;
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}