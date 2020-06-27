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
@RequestMapping(KaKaoPayRestApiDefine.KAKAOPAY_REST_API_GET_PATH)
public class ControllerGetSprinkle {

    final static Logger logger = LoggerFactory.getLogger(ControllerGetSprinkle.class);

    @RequestMapping(value="/", method={ RequestMethod.PUT})
    public String home(HttpServletRequest request ) throws Exception {
        
    	int nUserId = Integer.parseInt(request.getHeader("X-USER-ID"));
    	String strRoomId = request.getHeader("X-ROOM-ID");
    	String strGetMoneyToken = request.getParameter(KaKaoPayRestApiDefine.KAKAOPAY_REST_API_GET_MONEY_TOKEN);    	
    	
    	
    	JSONObject json = new JSONObject();
    	int nJsonSize = 0;
    	
    	
    	
    	try {

        	int nMoney = App.m_RoomMng.GetSprinkleMoney(strRoomId, nUserId, strGetMoneyToken);
        	if(nMoney > 0)
        	{
        		json.put("Money", nMoney);        				nJsonSize++;
            	json.put("code", HttpStatus.OK);				nJsonSize++;	        		
        	}
        	else
        	{
        		String strMess = "";
        		        		
        		switch(nMoney)
            	{
            	case KaKaoPayErrorDefine.ERROR_CODE_NOT_ROOM:
            		strMess = "Can't find room";
            		break;

            	case KaKaoPayErrorDefine.ERROR_CODE_NOT_TOKEN:
            		strMess = "Can't find Sprinkle token";
            		break;

            	case KaKaoPayErrorDefine.ERROR_CODE_NOT_SPRINKLE_SAME_USR:
            		strMess = "Money sprinkled by the same user";
            		break;

            	case KaKaoPayErrorDefine.ERROR_CODE_NOT_SPRINKLE_TIMEOVER :
            		strMess = "Sprinkle timeout";
            		break;

            	case KaKaoPayErrorDefine.ERROR_CODE_NOT_SPRINKLE_MONEY:
            		strMess = "Exhausted sprinkle money";
            		break;

            	case KaKaoPayErrorDefine.ERROR_CODE_NOT_SPRINKLE_REPLAY	:
            		strMess = "Already received";
            		break;
            		default :
            			strMess = "Not identified";
            		break;
            	}
        	
        		json.put("message", strMess);        				nJsonSize++;
            	json.put("code", HttpStatus.OK);				nJsonSize++;	        		

        		
    		} 
        	
    		}catch (Exception e) {

    		json.put("message", "Error : " + e.getMessage());					nJsonSize++;
    		json.put("code", HttpStatus.NOT_MODIFIED);							nJsonSize++;	
    			
    		}
                
        return json.toString(nJsonSize);
    }

    
}
