# KakaoPaySprinkle
카카오페이 뿌리기 RestApiServer

#######################################################
###제약사항

1. 뿌리기 API
 - 고유 토큰을 생성하였지만 토큰의 개수 제한이 있음(숫자+영문의 조합이 초과되는경우)
 - 이미 생성된 토큰과 생성 토큰을 비교하여 다시 생성하지만 성능이 떨어질 것으로 보임(synchronized를 사용하여 토큰이 늘어날수록....)


2. 받기 API
 - 뿌리기가 호출된 대화방의 사용자인지 식별이 모호하여 HTTP Header로 수신된 X-ROOM-ID를 대화방의 사용자로 인식함


3. 조회 API
 - 뿌린건 7일 이후에 조회 실패를 적용하였지만 토큰 삭제에 대한 관리 기능 구현이 필요


#######################################################
###단위테스트
1. 뿌리기 API
##Request############
POST /Sprinkle/
Host : http://localhost:8080

───────────────
Header[KEY/DESCRIPTION/TYPE]
X-USER-ID/사용자 ID/숫자형태
X-ROOM-ID/사용자 ID/문자형태

───────────────
Params[KEY/DESCRIPTION/TYPE]
SprinkleMoney/뿌릴금액/숫자형태
SprinklePersonCnt/뿌릴인원/숫자형태


##Example############
POST /Sprinkle/?SprinkleMoney=10000&SprinklePersonCnt=5 HTTP/1.1
Host: localhost:8080
X-USER-ID: 1234
X-ROOM-ID: "TEMP"

2. 받기 API
##Request############
PUT /GetSprinkle/
Host: localhost:8080

───────────────
Header[KEY/DESCRIPTION/TYPE]
X-USER-ID/사용자 ID/숫자형태
X-ROOM-ID/사용자 ID/문자형태

───────────────
Params[KEY/DESCRIPTION/TYPE]
token/뿌리기 시 발급받은 토큰/문자형태

##Example############
PUT /GetSprinkle/?token=4bf HTTP/1.1
Host: localhost:8080
X-USER-ID: 12345
X-ROOM-ID: "TEMP"

3. 조회 API
##Request############
PUT /LoadSprinkle/
Host: localhost:8080

───────────────
Header[KEY/DESCRIPTION/TYPE]
X-USER-ID/사용자 ID/숫자형태
X-ROOM-ID/사용자 ID/문자형태

───────────────
Params[KEY/DESCRIPTION/TYPE]
token/뿌리기 시 발급받은 토큰/문자형태

##Example############
GET /LoadSprinkle/?token=4bf HTTP/1.1
Host: localhost:8080
X-USER-ID: 1234
X-ROOM-ID: "TEMP"



#######################################################
###클래스

App.java : 메인 App 클래스
ControllerGetSprinkle.java : 받기 Rest API를 관리 클래스
ControllerLoadSprinkle.java : 조회 Rest API를 관리 클래스
ControllerSprinkle.java : 뿌리기 Rest API를 관리 클래스
KaKaoPayErrorDefine.java : 에러를 정의하는 클래스
KaKaoPayRestApiDefine.java : RestApi 경로 및 Key관리 클래스
RoomInfo.java : 대화방 정보 클래스
RoomMng.java : 대화방 관리 클래스
SprinkleInfo.java : 뿌리기 정보 관리 클래스
TokenGenerator.java 토큰 생성 클래스

#######################################################
###구조

┌────────┐	┌────────┐	┌────────┐
┃     Contorller	┃	┃     Contorller	┃	┃     Contorller	┃
┃    GetSprinkle	┃ 	┃   LoadSprinkle	┃ 	┃      Sprinkle	┃
┗────────┛	┗────────┛	┗────────┛
	┃			┃			┃
	├──────────────────────────┛
	┃
┌────────┐
┃         APP	┃
┗────────┛
	┃
┌────────┐	┌────────┐	┌────────┐
┃     RoomMng	├─1:N─┤      RoomInfo	├─1:N─┤    SprinkleInfo	┃
┗────────┛	┗────────┛	┗────────┛
							┃
						┌────────┐
						┃        Token	┃
						┃     Generator	┃
						┗────────┛




