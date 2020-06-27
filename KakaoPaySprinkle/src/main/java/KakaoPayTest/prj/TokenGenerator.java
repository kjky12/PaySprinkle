package KakaoPayTest.prj;

import java.util.ArrayList;
import java.util.UUID;

public class TokenGenerator {
	
	 private static ArrayList<String> listGenToken = new ArrayList<String>();
     
     public synchronized static String generateToken() 
     {
    	 UUID one1 = UUID.randomUUID();
    	 String strToken = one1.toString().substring(0,3);
    	 
    	 //! 리스트에 있는 토큰의 경우 다시 생성!
    	 while(listGenToken.contains(strToken) == true)
    	 {
    		 strToken = UUID.randomUUID().toString().substring(0,3);
    	 }    	 
    	 
    	 return strToken;
     }
	
     
     public synchronized static boolean DelToken(String strToken) 
     {
    	 return listGenToken.remove(strToken);
     }
}
