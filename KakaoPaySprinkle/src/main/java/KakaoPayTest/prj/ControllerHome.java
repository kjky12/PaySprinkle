package KakaoPayTest.prj;

import javax.servlet.http.HttpServletRequest;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;



//! REST(Representational State Transfer) 서비스를 제공하기 위한 컨트롤러를 지정하는 어노테이션
@RestController
@RequestMapping("/")
public class ControllerHome {

    final static Logger logger = LoggerFactory.getLogger(ControllerHome.class);
    
    @RequestMapping(value="/", method={ RequestMethod.GET, RequestMethod.POST })
    public String home( HttpServletRequest request ) throws Exception {
        
    	
    	JSONObject json = new JSONObject();
        
        json.put("success", true);
        json.put("data", 10);
        //json.put(null, 10);
                
        return json.toString(4);
    }

    
}
