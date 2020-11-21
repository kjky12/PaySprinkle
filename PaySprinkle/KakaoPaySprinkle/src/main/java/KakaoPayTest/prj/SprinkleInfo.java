package KakaoPayTest.prj;

import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.LinkedList;
import java.util.Date;
import java.util.HashMap;

//! 뿌려진 정보
public class SprinkleInfo {
	
	private String m_strToken;		

	//! 뿌린 사용자 ID
	private int m_nSprinkleUserId;
	
	//! 뿌린 금액
	private int m_nSprinkleMoney;
	

	//! 받기 완료된 금액
	private int m_nFinishDivMoney;

	//! Key : 수령해간 유저 ID 목록, Value : 돈
	private HashMap<Integer, Integer> m_mapSprinkleGetUserId;
 
	//! 수령해서 나눌 금액
	private Queue<Integer>		m_queDivMoney;
	
	//! 뿌리기가 시작한 시간!
	private Date m_dateCrate;
	 
	
	/**
	 * @return m_strToken
	 */
	public String getM_strToken() {
		return m_strToken;
	}

	/**
	 * @return m_nSprinkleUserId
	 */
	public int getM_nSprinkleUserId() {
		return m_nSprinkleUserId;
	}

	/**
	 * @return m_nSprinkleMoney
	 */
	public int getM_nSprinkleMoney() {
		return m_nSprinkleMoney;
	}


	/**
	 * @return m_nFinishDivMoney
	 */
	public int getM_nFinishDivMoney() {
		return m_nFinishDivMoney;
	}


	/**
	 * @return m_mapSprinkleGetUserId
	 */
	public HashMap<Integer, Integer> getM_mapSprinkleGetUserId() {
		return m_mapSprinkleGetUserId;
	}


	/**
	 * @return m_dateCrate
	 */
	public Date getM_dateCrate() {
		return m_dateCrate;
	}

	
	public SprinkleInfo(int nSprinkleUserId, int nSprinkleMoney, int nSprinkleDivPersonCnt)	
	{
		this.m_nFinishDivMoney = 0;
		this.m_strToken ="";
		this.m_nSprinkleUserId = nSprinkleUserId;
		this.m_nSprinkleMoney = nSprinkleMoney;
		//this.nSprinkleDivPersonCnt = nSprinkleDivPersonCnt;
		
		this.m_dateCrate = new Date();
		
		this.m_mapSprinkleGetUserId = new HashMap<Integer, Integer>();
			
		this.m_queDivMoney = new LinkedList<Integer>();
		
		//! 뿌리기 건당 분배!
		int nDiv = nSprinkleMoney / nSprinkleDivPersonCnt;
		int nNmg = nSprinkleMoney % nSprinkleDivPersonCnt;		
		
		//! 받는사람이 뿌리는 돈보다 많은경우는 토큰을 만들지 않음..(실패처리)
		if(nDiv == 0)
			return;
		
		for(int v = 0; v < nSprinkleDivPersonCnt; v++)
		{
			//! 최초 수령자가 가장 많은 금액을 가져가도록!
			if(v == 0)
			{
				this.m_queDivMoney.offer(nDiv + nNmg);
			}
			else
			{
				this.m_queDivMoney.offer(nDiv);
			}
		}
		
		//! 토큰을 만든다!
		this.m_strToken = TokenGenerator.generateToken();
	}


	public SprinkleInfo GetInstence(int nUserId)	
	{		
		//! 생성자만 조회가능!
		if(nUserId == this.m_nSprinkleUserId)
			return this;
		
		Date dateCurrent = new Date();
		long nGapTime = dateCurrent.getTime() - this.m_dateCrate.getTime();		
		long nSeconds = TimeUnit.MILLISECONDS.toSeconds(nGapTime);
		
		//! 생성된지 7일 후로는 조회가 안됌
		if(nSeconds > 60 * 60 * 24 * 7)
			return null;
		
		return null;
			
	}

	
	public int GetMoney(int nUserId)	
	{
		//! 뿌린 사용자와 동일하면...
		if(this.m_nSprinkleUserId == nUserId)
			return KaKaoPayErrorDefine.ERROR_CODE_NOT_SPRINKLE_SAME_USR;
		
		Date dateCurrent = new Date();
		long nGapTime = dateCurrent.getTime() - this.m_dateCrate.getTime();		
		long nSeconds = TimeUnit.MILLISECONDS.toSeconds(nGapTime);
		
		//! 생성된지 10분이 지났으면..
		if(nSeconds > 600)
			return KaKaoPayErrorDefine.ERROR_CODE_NOT_SPRINKLE_TIMEOVER;
		
		
		//! 해당 유저가 돈을 가져갔는지 확인!		
		if(m_mapSprinkleGetUserId.containsKey(nUserId) == true)
			return KaKaoPayErrorDefine.ERROR_CODE_NOT_SPRINKLE_REPLAY;
		
		//! 돈이 없으면 리턴!
		if(m_queDivMoney.size() == 0)
			return KaKaoPayErrorDefine.ERROR_CODE_NOT_SPRINKLE_MONEY;


		int nMoney = m_queDivMoney.poll();		
		m_mapSprinkleGetUserId.put(nUserId, nMoney);
		
		this.m_nFinishDivMoney += nMoney; 
		
		return nMoney;
	}

	
}
