package KakaoPayTest.prj;

import java.util.HashMap;

public class RoomInfo {

	//! 방 식별값
	private String m_strRoomId;
	
	
	//! 뿌리기 목록 정보! Key : Token, Value : 뿌리기 정보
	private HashMap<String, SprinkleInfo> m_mapSprinkleInfo;
	
	
	RoomInfo(String strRoomId)
	{
		//! 방 식별값 입력!
		this.m_strRoomId = strRoomId;
		
		this.m_mapSprinkleInfo = new HashMap<String, SprinkleInfo>();
	}
	
	//! 돈 뿌리기!!
	String SprinkleMoney(int nSprinkleUserId, int nSprinkleMoney, int nSprinkleDivPersonCnt)
	{
		SprinkleInfo SprinkleInfoTemp = new SprinkleInfo(nSprinkleUserId, nSprinkleMoney, nSprinkleDivPersonCnt);
		
		//! 토큰을 받아온다.
		String strToken = SprinkleInfoTemp.getM_strToken();
		
		//! 토큰이 없으면 만들지 않음
		if(!strToken.isEmpty())
		{
			//! 돈뿌리기 정보를 Key토큰, 뿌리기정보로 변수로 관리한다.
			m_mapSprinkleInfo.put(strToken, SprinkleInfoTemp);			
		}
		
		
		return strToken;
	}
	
	public int GetSprinkleMoney(int nUserId, String strToken)
	{
		int nMoney = 0;
		
		if(m_mapSprinkleInfo.containsKey(strToken) == true)
			nMoney = m_mapSprinkleInfo.get(strToken).GetMoney(nUserId);
		else
			nMoney = KaKaoPayErrorDefine.ERROR_CODE_NOT_TOKEN;
		
		return nMoney;
	}
	
	public SprinkleInfo GetSprinkleInfo(int nUserId, String strToken)
	{
		if(m_mapSprinkleInfo.containsKey(strToken) == true)
			return m_mapSprinkleInfo.get(strToken).GetInstence(nUserId);
		
		return null;
	}
}
