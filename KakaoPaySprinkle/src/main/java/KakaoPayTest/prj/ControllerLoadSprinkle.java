package KakaoPayTest.prj;


import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(KaKaoPayRestApiDefine.KAKAOPAY_REST_API_LOAD_PATH)
public class ControllerLoadSprinkle {

    final static Logger logger = LoggerFactory.getLogger(ControllerLoadSprinkle.class);

    @RequestMapping(value="/", method={ RequestMethod.GET})
    public String home(HttpServletRequest request ) throws Exception {
        
    	int nUserId = Integer.parseInt(request.getHeader("X-USER-ID"));
    	String strRoomId = request.getHeader("X-ROOM-ID");
    	String strSprinkleInfoToken = request.getParameter(KaKaoPayRestApiDefine.KAKAOPAY_REST_API_LOAD_SPRINKLE_INFO_TOKEN);    	
    	
    	SprinkleInfo SprinkleInfoTmp = App.m_RoomMng.GetSprinkleInfo(strRoomId, nUserId, strSprinkleInfoToken);
    	
    	JSONObject json = new JSONObject();
    	
    	int nJsonSize = 0;
    	
    	try {
    	
		    	if(SprinkleInfoTmp != null)
		    	{
		    		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    		String strDate = transFormat.format(SprinkleInfoTmp.getM_dateCrate());    		
		    		json.put("Date", strDate);																nJsonSize++;
		    		
		    		json.put("SprinkleMoney", Integer.toString(SprinkleInfoTmp.getM_nSprinkleMoney()));		nJsonSize++;
		    		
		    		json.put("FinishDivMoney", Integer.toString(SprinkleInfoTmp.getM_nFinishDivMoney()));	nJsonSize++;
		
		    		json.put("SprinkleGetUserId", SprinkleInfoTmp.getM_mapSprinkleGetUserId());				nJsonSize++;
		    		
		    		json.put("code", HttpStatus.OK);														nJsonSize++;
		    		
		    	}
		    	else
		    	{
		    		json.put("message", "Can't find SprinkleInfo");											nJsonSize++;
		    		
		    		json.put("code", HttpStatus.NOT_FOUND);													nJsonSize++;
		    	}
    	
	    	}catch (Exception e) {
	
	    		json.put("message", "Error : " + e.getMessage());					nJsonSize++;
	    		json.put("code", HttpStatus.NOT_MODIFIED);							nJsonSize++;	
	    			
	    	}
    	
        //json.put("Money", nMoney);
    	
    	
                
        return json.toString(nJsonSize);
    }

    
}
