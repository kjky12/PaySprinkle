package KakaoPayTest.prj;

public class KaKaoPayRestApiDefine {

	//////////////////////////////////////////////////////////
	//! 뿌리기 API 경로
    public static final String KAKAOPAY_REST_API_SPRINKLE_PATH  = "/Sprinkle/";
    //! 뿌리기 수신파라미터 - 금액 Key
    public static final String KAKAOPAY_REST_API_SPRINKLE_SPRINKLE_MONEY  = "SprinkleMoney";
    //! 뿌리기 수신파라미터 - 나눌 사람 수 
    public static final String KAKAOPAY_REST_API_SPRINKLE_SPRINKLE_PERSON_CNT  = "SprinklePersonCnt";
    
    //////////////////////////////////////////////////////////////
	//! 받기 API 경로
    public static final String KAKAOPAY_REST_API_GET_PATH  = "/GetSprinkle/";
    //! 받기 수신파라미터 - 토큰 Key
    public static final String KAKAOPAY_REST_API_GET_MONEY_TOKEN  = "token";

    
    //////////////////////////////////////////////////////////////
	//! 조회 API 경로
    public static final String KAKAOPAY_REST_API_LOAD_PATH  = "/LoadSprinkle/";
    //! 조회 수신파라미터 - 토큰 Key
    public static final String KAKAOPAY_REST_API_LOAD_SPRINKLE_INFO_TOKEN  = "token";

	
}
