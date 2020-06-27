package KakaoPayTest.prj;



import javax.servlet.http.HttpServletRequest;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping(KaKaoPayRestApiDefine.KAKAOPAY_REST_API_SPRINKLE_PATH)
public class ControllerSprinkle{
	
	final static Logger logger = LoggerFactory.getLogger(ControllerSprinkle.class);
    

    @RequestMapping(value="/", method={RequestMethod.POST })
    public String home(HttpServletRequest request ) throws Exception {
        
    	int nUserId = Integer.parseInt(request.getHeader("X-USER-ID"));
    	String strRoomId = request.getHeader("X-ROOM-ID");
    	int nSprinkleMoney = Integer.parseInt(request.getParameter(KaKaoPayRestApiDefine.KAKAOPAY_REST_API_SPRINKLE_SPRINKLE_MONEY));
    	int nSprinklePersonCnt = Integer.parseInt(request.getParameter(KaKaoPayRestApiDefine.KAKAOPAY_REST_API_SPRINKLE_SPRINKLE_PERSON_CNT));
    	
    	String strToken = App.m_RoomMng.SprinkleMoney(strRoomId, nUserId, nSprinkleMoney, nSprinklePersonCnt);
    	JSONObject json = new JSONObject();
    	
    	int nJsonSize = 0;
    	
    	try {
	    	if(!strToken.isEmpty())
	    	{
	    		json.put("Token", strToken);													nJsonSize++;
	    		
	    		json.put("code", HttpStatus.OK);														nJsonSize++;
	    	}
	    	else
	    	{
	    		json.put("message", "Can't Sprinkle");											nJsonSize++;
	    		
	    		json.put("code", HttpStatus.NOT_FOUND);													nJsonSize++;
	    	}
               
    	}catch (Exception e) {

    		json.put("message", "Error : " + e.getMessage());					nJsonSize++;
    		json.put("code", HttpStatus.NOT_MODIFIED);							nJsonSize++;	
			
			}
                
        return json.toString(nJsonSize);
    }

    
}
