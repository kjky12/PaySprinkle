package KakaoPayTest.prj;

import java.util.HashMap;





public class RoomMng {

	//! 방정보를 관리한다. Key : 방 ID, Value : 방정보
	HashMap<String, RoomInfo> m_mapRoomInfo;

	//! 토큰정보를  -> 관리할 필요가 없어보이네..?
	HashMap<String, String> m_mapTokenRoom;
	
	
	RoomMng()
	{		
		m_mapRoomInfo = new HashMap<String, RoomInfo>();
		
		//RoomInfo	RoomInfoTemp = new RoomInfo("ABCD");
		//m_mapRoomInfo.put("TEST", RoomInfoTemp);
	}
	
	public synchronized String SprinkleMoney(String strRoomId, int nUserId, int nSprinkleMoney, int nSprinklePersonCnt)
	{	
		String strToken = "";
		//! 방의 유무를 확인한다.
		if(this.m_mapRoomInfo.containsKey(strRoomId) == true)
		{
			strToken = this.m_mapRoomInfo.get(strRoomId).SprinkleMoney(nUserId, nSprinkleMoney, nSprinklePersonCnt);
		}
		else //! 없는경우 새로 만들어준다.
		{
			RoomInfo	RoomInfoTemp = new RoomInfo(strRoomId);			
			strToken = RoomInfoTemp.SprinkleMoney(nUserId, nSprinkleMoney, nSprinklePersonCnt);
			
			//! 새로 생성된 방을 변수에 입력한다.
			this.m_mapRoomInfo.put(strRoomId, RoomInfoTemp);
		}
		
		return strToken;
	}
	
	public synchronized int GetSprinkleMoney(String strRoomId, int nUserId, String strToken)
	{		
		int nMoney = 0;
		if(m_mapRoomInfo.containsKey(strRoomId) == true)
		{
			nMoney = m_mapRoomInfo.get(strRoomId).GetSprinkleMoney(nUserId, strToken);
		}
		else
			nMoney = KaKaoPayErrorDefine.ERROR_CODE_NOT_ROOM;
		
		return nMoney;
	}
	
	public synchronized SprinkleInfo GetSprinkleInfo(String strRoomId, int nUserId, String strToken)
	{		
		if(m_mapRoomInfo.containsKey(strRoomId) == true)
			return m_mapRoomInfo.get(strRoomId).GetSprinkleInfo(nUserId, strToken);
		
		return null;
	}
	
	
	public int GetRoomSize()
	{
		return this.m_mapRoomInfo.size();
	}
	
}
