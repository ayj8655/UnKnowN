<img src="https://user-images.githubusercontent.com/48272857/59177021-b8e64f00-8b95-11e9-98d5-9e3147ea8258.png">  

 - 스마트 모바일 프로그래밍 2조 프로젝트 (UnKnowN)
---
# 주제를 선정하게 된 이유
<div>
<img src="https://user-images.githubusercontent.com/48272857/59177151-1d091300-8b96-11e9-8e2e-f083d993f425.png">
<img src="https://user-images.githubusercontent.com/48272857/59177153-1e3a4000-8b96-11e9-99e3-0ad131934872.png">
 </div><br>  
 [표1. 미아발생 원인 및 발생장소]
<br>
위 표의 결과와 같이 미아가 발생하는 원인의 86.2%는 보호자의 부주의와 관련되어 있음을 시사하고 있다. 
<br>달리 말해, 보호자의 부주의를 막기 위한 보조기술이 적용된다면 미아방지를 비약적으로 줄일 수 있다. 
이 프로젝트는 이러한 보조기술로 많은 소비자들이 통신요금 없고 낮은 가격에 사용 가능한 새로운 미아방지 기술을 제시하였고 이 기술을 놀이공원에 적용하였을때 사용할 앱과 하드웨어를 제작하였다.

---

# 간략 기능 소개
  1. 어플을 이용한 미아 방지 팔찌
    - 직접 모델링한 팔찌안에 bluetooth 모듈, NFC 태그와 건전지로 구성하였다.     
    NFC 태그로 어플과 연동 후 bluetooth 통신으로 거리를 측정한다. 거리가 멀어지면 알람이 울리며 특정 번호로 연락할 수 있다.

  2. 종이 티켓을 전자화 
  
  3. 편리한 관리
    - 앱에서 관리할 수 있는 관리자 모드 구현
    
      * 공지사항 작성 기능
      * 사용자가 팔찌 반납 시 정보 초기화 기능
  
  4. 다국어 지원 (언어 추가 가능)
    - 현재 한국어, 영어, 일어, 중국어 구현
    
  5. 놀이기구 지도
    - 아이의 키에 맞는 놀이기구를 표시하고 자세한 정보를 확인

  6. QR코드  
  <img src="https://user-images.githubusercontent.com/48484742/59318197-1e0a8380-8d01-11e9-91b1-72f0afbc36ed.jpg"> <br> 
  놀이공원에 온 손님이 미아방지팔찌를 원하는 경우 미아방지팔찌에 맞는 어플을 부모의 핸드폰에 다운로드 하기 위해 놀이공원 측에서 QR코드를 제공한다. QR코드는 네이버에서 제공하는 네이버QR코드를 이용하여 만들었다.
---
# 하드웨어 
* 구성 
  1. 블루투스 모듈  
  <center><img src="https://user-images.githubusercontent.com/48484742/59264651-8bc19b80-8c7e-11e9-93b3-00fe51714850.jpg" width="150" height="150"></center><br>
  아이와의 거리를 계산하기위해 비콘 기능을 가진 블루투스 모듈을 사용하였다. 모듈은 HM-10을 사용하였다.  
  비콘 기능이 없는 블루투스 모듈의 경우 RSSI값을 받아오는데에 있어 어려움이 있고 배터리소모가 많아 비효율적이기 때문에 비콘 기능이 있는 블루투스 모듈을 사용하였다.
  
  2. 배터리  
  <center><img src="https://user-images.githubusercontent.com/48484742/59264686-a09e2f00-8c7e-11e9-9774-d218b6b5fbd4.jpg" width="150" height="150"></center><br>
  미아방지 팔찌의 유지보수 증대를 위해 CR2450 수은전지를 사용하며 이는 620mAh의 용량을 가진다. 
  
  3. NFC  
  <center><img src="https://user-images.githubusercontent.com/48484742/59266598-f96fc680-8c82-11e9-9f37-7758277827bd.jpg" width="150"></center><br>
  NFC에 부여된 고유 ID값을 이용해 놀이공원이 소유한 티켓인지를 구분한다. 고유 ID값은 데이터베이스에 저장되어 있으며 등록된 NFC를 휴대폰에 태그 한 경우에만 어플리케이션 사용이 가능하다.
  
  4. 아두이노  
  <center><img src="https://user-images.githubusercontent.com/48484742/59267009-06d98080-8c84-11e9-8fa1-db883677eb76.png" width="50%" height="50%"></center><br>
  놀이공원의 티켓 검사 자동화를 위해 Arduino Uno (R3) 호환보드, RFID Rc522모듈, 서보모터를 사용하여 구현했다. NFC를 RFID에 태그하게 되면 서보모터가 작동하며 문이 열리게 된다.
  
 5. 케이스    
  -1차 모델   
  <img src="https://user-images.githubusercontent.com/48273766/59301890-a40cd700-8ccd-11e9-99b6-98dac746faa3.png" width="40%" height="30%">  
  -2차 모델  
  <img src="https://user-images.githubusercontent.com/48273766/59302736-935d6080-8ccf-11e9-8d98-4c5c6b736f46.png" width="40%" height="30%">   
  -3차 모델  
   <img src="https://user-images.githubusercontent.com/48273766/59303341-f56a9580-8cd0-11e9-9ae5-029817283fef.png" width="40%" height="30%">  
  -배터리 모듈  
   <img src="https://user-images.githubusercontent.com/48273766/59303522-56926900-8cd1-11e9-8cf3-1ea0ecaf027c.png"  width="40%" height="30%">  
  -최종 팔찌 모델  
  <img src="https://user-images.githubusercontent.com/48273766/59303948-49c24500-8cd2-11e9-9274-9b91ce5de6df.png"  width="40%">
  <img src="https://user-images.githubusercontent.com/48272857/59179677-ca335980-8b9d-11e9-91dc-a0ebfb733aec.png">  
  사용자를 비롯한 관리자의 유지보수와 지속가능성 증대를 위해 왼쪽 그림과 같은 디바이스를 3D모델링을 통해 고안하였다. 이 디바이스는 배터리, 블루투스, NFC를 장착하여 사용하는 모델이다.  사용자는 디바이스에 벨크로를 연결하여 착용한다.
  
---

# 소프트웨어
* 구성 
  1. APACHE  
  HTTP서버로 워낙 다양한 추가기능에, 구축이 쉽다는 이유 때문에 많이 쓰고 있다. 또한 APM을 이용하여 웹 서버를 구축하는 방식이 보편화되어 있어서 초보자도 손쉽게 구축이 가능하다.  
  (APM : Apache, Php, MySQL)
  2. PHP  
  안드로이드는 보안상의 이유로 직접적인 외부 라이브러리와 연동할 수 없게 막아놓았다. 그렇기 때문에  PHP를 중간 매개로 이용하여 데이터를 가져와야 한다.
  3. MySQL  
  데이터베이스는 Oracle, MySQL, MSSQL등 많은 종류가 존재한다. 그 중에서도 2019년 6월 기준으로 Oracle과 MySQL이 가장 많이 사용된다.
  이 프로젝트에서는 간단한 기능구현에 적합하고 무료로 제공되는 MySQL을 이용하여 데이터베이스를 구축해보았다.
  4. 안드로이드 스튜디오  
  안드로이드 스튜디오는 구글에서 출시하였고 Android의 공식 IDE이다.
  풍부한 코드 편집, 디버깅, 테스트 및 프로파일링 도구를 비롯한 맞춤형 도구를 제공한다. 어플리케이션 개발에 필수적이다.
  5. Visual Code
  GitHub의 READEME.md 파일을 작성하기 위해 사용하였다.
  
---
  
# 사전 설정 및 환경 구축
### 1. 블루투스 설정 [ **필수** ]
블루투스 모듈을 아두이노 UNO를 통해서 초기화해줘야 한다. (모듈 HM-10를 기반으로 진행하였다.)

1. HM-10의 초기 설정은 다음과 같다.
```sh
● Name : HMSoft
● Baud rate : 9600
● 패리티 비트 : 0
● 전송 비트 : 8
● Stop 비트 : 1
● 비밀번호(Pin code) : 000000
```
2. 블루투스 모듈을 다음과 같이 아두이노에 연결한다.  
  
<center><img src="https://miro.medium.com/max/700/1*wh7iuFpmXctFGUWvhhaNpA.png" width="500px">  </center>

3. 아래 코드를 아두이노 스케치에 입력 후 업로드한다.
```
#include <SoftwareSerial.h>
#define BT_RX 0
#define BT_TX 1
 
SoftwareSerial HM10(BT_RX,BT_TX);  // RX핀(0번)은 HM10의 TX에 연결 
                                   // TX핀(1번)은 HM10의 RX에 연결  
void setup() {  
  Serial.begin(9600);
  HM10.begin(9600);
}
void loop() {
  if (HM10.available()) {
    Serial.write(HM10.read());
  }
  if (Serial.available()) {
    HM10.write(Serial.read());
  }
}
```
4. 시리얼 모니터를 띄우고 밑에 옵션 설정을 'line ending 없음'으로 바꾼 후 AT를 입력한다. 결과로 OK가 출력되는지 확인한다. OK가 안뜬다면 선이 반대로 연결되었거나, 업로드가 안됬을 가능성이 높다.

5. 시리얼 모니터를 통해 다음과 같이 설정한한다. (본 예시는 프로젝트 설정을 따라간다.)
```
>> AT+RENEW // 공장초기화
>> AT+RESET // 모듈 재부팅
>> AT+NAMEUnKnowN // 모듈의 이름 설정 "UnKnowN"
>> AT+ADVI5 // 신호 송출 주기를 5로 설정
>> AT+ADTY3 // 전원 절약을 위해 설정
>> AT+RESET // 모듈 재부팅
```

6. 위의 과정으로 프로젝트에 맞는 블루투스 모듈 설정이 완료되었다.

---
### 2. Nfc 기반의 티켓기능 [ **필수** ]  
미아방지 팔찌에 Nfc기능을 이용해 팔찌를 놀이기구의 문에 태그시 놀이기구의 문이 개방되는 것을 보여주기 위해 아두이노 서보모터 제어를 하여 구현했다.
1. 라이브러리를 사용하기 위해서 다음 코드를 입력해준다.
```csharp
#include <Servo.h>  // 서보 라이브러리
#include <SPI.h>    // RFID를 위한 SPI 라이브러리
#include <MFRC522.h>// RFID 라이브러리
```

2. 서보 모터 PIN을 6번 PIN으로  RFID모듈의 SDA, RST PIN을 각각 10, 9 PIN으로로 정의해준 다음 아래 연결도와 같이 서보모터와 RFID모듈, 우노 보드를 연결한다. ( 나머지 RFID PIN은 SPI 라이브러리를 사용하기에 별도의 설정이 필요없다. )

![Image and Preview Themes on the toolbar](https://user-images.githubusercontent.com/48374494/59271252-63da3400-8c8e-11e9-8822-e5d840dd4ac9.PNG)

3. 서보모터와 RFID 라이브러리를 생성한다.
```csharp
Servo myservo;
MFRC522 rfid(SS_PIN, RST_PIN);
```
4. 아두이노 코드에 Nfc의 uid를 등록시킨다.

5. 팔찌를 NFC모듈에 태그해주었을 때 먼저 PICC 타입을 읽어와 MIFARE 방식인 것을 확인한 후에 등록되 있지 않은 UID를 가진 경우에는 서버모터가 작동하지않고 사전에 등록되 있는 UID를 가진 경우에는 서보모터가 90도 회전하여 3초 딜레이를 가진 후에 0도로 다시 돌아온다.

출처:https://blog.naver.com/chandong83/220920828631  

---
### 3. 서버 구축 [ **필수** ]

- 서버 구축 및 설정  
앱을 위한 서버가 필요하다.
Mysql + phpmyadmin을 지원하는 WampServer를 이용하면 손쉽게 서버를 구축할 수 있다.  
  
  WampServer | http://www.wampserver.com/en/ 에서 다운로드 가능하다.

1. https://sourceforge.net/projects/wampserver/files/ 에 접속하여 초록색 버튼 Download Latest Version을 클릭하면 최신 버전의 WampServer가 다운로드 된다.

2. 설치 중간에 웹브라우저와 편집기에 대한 설정을 물어보는데 인터넷 익스플로러와 노트패드를 기본으로 쓸땐 '예'를 누르고 진행하면 된다.  
  
    (설치과정에 방화벽 앱 허용 창이 뜰때 자신의 인터넷이 개인 네트워크라면 개인에 체크, 다른 인터넷 환경에서도 서버를 사용하려면 공용에도 체크해줘야 한다.)

3. 정상적으로 설치가 완료되고 실행됬다면 오른쪽 밑 트레이 아이콘에 녹색 아이콘이 뜰 것이다.

4. 아이콘을 왼쪽 클릭한 후 메뉴에서 MySQL > MySQL console을 선택한다.

5. 사용자 이름을 입력하는 창이 뜨는데 그대로 OK 누른다.
   그 후 Enter password : 가 콘솔창에 뜨는데 엔터를 치면 다음으로 넘어간다.
  
6. root 계정의 비밀번호를 다음과 같이 입력하여 변경한다. 
```sh
SET PASSWORD FOR root@localhost = PASSWORD('변경할 비밀번호');
```  
(계정명과 비밀번호는 phpMyAdmin에 접속할 때와 php파일에서 사용한다.)

7. 콘솔에 quit 입력하여 콘솔창을 종료한다.

8. 트레이 아이콘을 왼쪽버튼 클릭하고 메뉴에서 phpMyAdmin를 선택하여 접속한다.

9. 방금전에 설정했던 비밀번호로 root 계정에 로그인하여 정상적인 접근이 가능한지 확인한다.

- 이제 외부의 접속을 허가하는 설정이다.

10. 트레이 아이콘 왼쪽 클릭하면 보이는 메뉴에서 Apache > httpd-vhosts.conf 를 선택한다.

11. 다음과 같이 Require local -> Require all granted 로 설정한다. 
```sh 
# Virtual Hosts
#
<VirtualHost *:80>
  ServerName localhost
  ServerAlias localhost
  DocumentRoot "${INSTALL_DIR}/www"
  <Directory "${INSTALL_DIR}/www/">
    Options +Indexes +Includes +FollowSymLinks +MultiViews
    AllowOverride All
    Require all granted
  </Directory>
</VirtualHost>
```
12. 저장 후, 설정을 적용하기 위해 트레이 아이콘 왼쪽 클릭하면 보이는 메뉴에서 Restart All Services를 선택한다.

13. 윈도우 10 환경에서 추가 설정을 해주어야 한다.
제어판 > 네트워크 및 인터넷 > 네트워크 및 공유 센터 > Windows Defender 방어벽 > 고급 설정으로 이동한다.

14. 왼쪽 목록에 인바운드 규칙을 누르고 오른쪽 작업에서 새 규칙을 선택한다.

15. 포트 체크 > TCP 체크, 특정 로컬 포트에 '80' 입력 > 연결 허용 > 개인, 공용 체크 > 이름은 원하는 이름으로 설정 후 마친다.  

  - 이 설정은 공유기의 네트워크를 사용하는 다른 PC나 스마트폰에서 웹서버에 접속할 수 있도록 한다.

16. 다음 링크에서 php 파일들을 다운받아서 wampServer 내의 www폴더에 넣는다. (메뉴에서 www 클릭)  

<a href="https://drive.google.com/open?id=1rCrviGAQhRPAexc7suhNIqkP7Sxg4FfQ">다운로드</a>
 
 
(이후부터는 프로젝트 환경을 따라가고 있다.)

17. Mysql 콘솔을 다시 열어서 로그인 후 데이터베이스(test)를 생성한다.  
```sh
mysql> create database test DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
```
18. test 데이터베이스 선택 후 테이블을 생성한다.
```sh
mysql > use test;
Database changed
```
person 테이블 생성  
```sh
mysql > CREATE TABLE person(
    id INT(11) NOT NULL AUTO_INCREMENT,
    serial VARCHAR(20) NOT NULL,
    name VARCHAR2(10),
    age INT(5),
    phone INT(11),
    PRIMARY KEY(id)
)
Query Ok, ... 
```
notice 테이블 생성
```sh
mysql > CREATE TABLE notice(
    id INT(11) NOT NULL AUTO_INCREMENT,
    content VARCHAR2(255) NOT NULL,
    date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(id)
)
Query Ok, ... 
```
mysql > show tables로 테이블이 잘 생성되었는지 확인한다.

19. dbcon.php을 수정하여 다음 내용을 자신의 환경에 맞게 수정한다.  
프로젝트는 괄호안의 값으로 설정되어 있다.
```sh
    $host = '본인서버아이피'; # 또는 localhost(개인망)
    $username = 'root1'; # MySQL 계정 아이디 
    $password = '1234'; # MySQL 계정 패스워드
    $dbname = 'test'; # DATABASE 이름
```
20. Lobby, NFC, Profile Activity의 'IP_ADDRESS' 를 본인 ip로 변경한다.

21. phpMyAdmin에 설정해둔 계정으로 접속한 후 person 테이블에 찾아낸 nfc 고유번호를 serial속성에 추가한다. (id는 자동 갱신된다.)  
다음 링크는 위의 데이터베이스 sql 파일이다.  
<a href="https://drive.google.com/open?id=1wcrcRolH00Ufjj1id6zKa2Gi-5Lhxn2G">다운로드</a>

---

### 주요 코드 설명
### (안드로이드 <-> PHP 서버 통신)
- HttpURLConnection을 이용한 서버 연결   
serverURL는 실행할 php주소를 입력한다. postParameters는 php에 전달해줄 값이다.
```java
// ShowActivity.java의 GetData
@Override
        protected String doInBackground(String... params) {
            String serverURL = "http://" + IP_ADDRESS + "/query.php";
            // DEVICE NUMBER
            String postParameters = "id="+params[0];

            try {
                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                // 생략
```
 - JSONObject와 JSONArray을 이용한 JSON Parsing  
 JSONObject로 php에서 넘어온 JSON형태의 데이터를 입력한다.
 JSONArray로 Parsing을 진행한다.
```java
// ShowActivity.java의 onPostExecute
try {
        JSONObject jsonObject = new JSONObject(mJsonString);
        JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);
        for(int i=0;i<jsonArray.length();i++) {

        JSONObject item = jsonArray.getJSONObject(i);

        nameView.setText(item.getString(lb.TAG_NAME));
        // 생략
```

wampServer 설치와 설정, php코드는 다음 사이트의 도움을 받았다.   

개인 환경에 따라 차이가 있을 수 있고 해결법은 출처에서 확인할 수 있다.  
  
자세한 내용은 출처에서 확인할 수 있다.   

출처 : 멈춤보단 천천히라도  
https://webnautes.tistory.com/1206  
https://webnautes.tistory.com/828

---

### 4. Google Map API
QR코드에 있는 어플을 다운받으면 앱 기능 중 지도보기가 정상적으로 사용가능하지만 따로 코드를 돌리는 경우 API키를 수정해야한다.
따라서 직접 구글 API를 써야하는 경우 밑의 방법으로 사용한다.

1. 다음 링크에 접속 후 프로젝트 만들기를 클릭한다. (프로젝트 이름은 원하는 이름으로 입한다.)  
https://console.developers.google.com/apis/library 
<img src="https://user-images.githubusercontent.com/48484742/59256055-177efc00-8c6e-11e9-8005-ef4691340e57.png"> <br>

2. 생성된 프로젝트로 들어간 후 'API 및 서비스 사용 설정'을 클릭한다.
<img src="https://user-images.githubusercontent.com/48484742/59256349-b73c8a00-8c6e-11e9-89ac-a74e69163c6f.png"> <br>

3. Maps SDK for Android를 검색하고 선택한다.
<img src="https://user-images.githubusercontent.com/48484742/59256385-cde2e100-8c6e-11e9-975f-1c6a95e0b31c.png"> <br>

4. 사용 설정을 클릭하여 활성화하고 인증 설정을 위해 '사용자 인증 정보'를 선택한다.
<img src="https://user-images.githubusercontent.com/48484742/59256494-0b476e80-8c6f-11e9-8519-92de214b0d6d.png"> <br>

5. 사용자 인증 정보 만들기를 클릭 > API 키를 클릭 > 키 제한을 누른다. 이 때 생성된 API키는 AndroidManifest.xml에 사용이 된다.
<img src="https://user-images.githubusercontent.com/48484742/59256577-3631c280-8c6f-11e9-8168-50d8a1fc4d5b.png"> <br>

6. '이름'은 원하는대로 설정하고 '애플리케이션 제한사항'은 Android 앱을 선택하고 'API 제한사항'은 키 제한을 선택한 후 Maps SDK for Android 를 선택한다.  
<center><img src="https://user-images.githubusercontent.com/48484742/59256609-45187500-8c6f-11e9-98dd-0126f8d44861.png"></center> <br>

7. Android 앱의 사용량 제한 '항목추가'를 누르게 되면 다음 화면이 뜨게 되고 SHA-1 인증서 지문을 얻기 위한 과정부터 진행한다.  윈도우키 + R을 누른 후 cmd를 입력하고 엔터를 눌러서 명령 프롬프트 창을 열고 다음 명령을 입력한다.
<center><img src="https://user-images.githubusercontent.com/48484742/59256696-6da06f00-8c6f-11e9-8494-241bc09956e8.png"></center> <br>

```sh
"C:\Program Files\Android\Android Studio\jre\bin\keytool" -list -v -keystore "%USERPROFILE%\.android\debug.keystore" -alias androiddebugkey -storepass android -keypass android
```
- 안드로이드 스튜디오가 기본경로가 아닐 시 경로를 수정하여 입력한다. 
- 다음과 같은 오류가 생길 경우 간단한 안드로이드 프로젝트를 생성하여 빌드 후, 안드로이드 디바이스에 설치하면 해결된다.
```
keytool 오류: java.lang.Exception: 키 저장소 파일이 존재하지 않음: C:\Users\webnautes\.android\debug.keystore
```
8.  인증서 지문에 있는 SHA1 문자열을 복사한다.
<img src="https://user-images.githubusercontent.com/48484742/59256723-801aa880-8c6f-11e9-9569-d7099c05fb3a.png"> <br>

9.  인터넷 창으로 돌아가서 어플리케이션 제한사항에서 'Android 앱'을 선택하고 항목 추가를 클릭한다.

10. Google Maps Android API를 사용할 안드로이드 프로젝트의 패키지 이름과 방금 복사한 SHA1 문자열을 입력하고 완료를 누른다.
(프로젝트를 그대로 사용하는 경우 com.example.SONZABA를 패키지 이름으로 하면 된다.)

11. 5번에서 생성된 API키를 복사한다.

12. 마지막으로 AndroidManifest.xml 파일에서 meta-data 부분을 다음과 같이 수정한다.  

```
<meta-data
            android:name="com.google.android.geo.API_KEY"
            android:value="API 키"/>
```
  
출처 : 멈춤보단 천천히라도   
https://webnautes.tistory.com/647

---
# 기능 구현
### 1. 권한 허용 알림  
<img src="https://user-images.githubusercontent.com/48484742/59296598-512d2280-8cc1-11e9-9241-0a6ed773cb03.jpg" width="200"> <br>  

어플을 실행하는데 있어 반드시 필요한 권한들을 허용하도록 다이얼로그를 띄운다.
- AndroidManifest.xml파일에 필요한 permission을 부여한다.
```
//AndroidManifest.xml
<uses-permission android:name="android.permission.CALL_PHONE" />
<uses-permission android:name="android.permission.INTERNET" />
...
//생략...
```
- SplashActivity.java에서 권한을 모두 허용했는지 확인한다. 허용하지 않은 권한만 요청한다. isUsing은 NFC태그를 어플 첫 사용시에만 사용하도록 하기위한 변수다. 이를 사용하지 않는다면 if문 안의 내용만 사용하면 된다.
```
//SplashActivity.java
public class SplashActivity extends Activity {
    final int PERMISSION = 1;
    @Override
        protected void onCreate(Bundle savedInstanceState) {
            int permissionCheck= ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
            //생략...
            if(permissionCheck == PackageManager.PERMISSION_GRANTED && ...){
                if (isUsing==false) {
                    Intent intent = new Intent(this, WelcomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
             else {
                // Start Granting Permission From User
                ActivityCompat.requestPermissions(this, new String[]{ Manifest.permission.CAMERA,...}, PERMISSION);
            }
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode){
            case PERMISSION:
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED &&...)
                {
                    if (isUsing==false) {
                        startActivity(new Intent(this, WelcomeActivity.class));
                        finish();
                    }
                }
                else {
                    finish();
                }
                break;
        }
    }
    //생략...
}
```
### 2. 인트로 슬라이드

인트로 슬라이드에 대한 설명은 다음과 같다.

* 사용자가 앱을 처음 실행할 때 **가이드** 역할을 한다.

* **화면을 옆으로 넘기거나 NEXT 버튼**을 누르면 **다음화면**으로 넘어간다.

* **SKIP버튼**을 누르면 바로 **인트로 슬라이드를 종료**할 수 있다.

* 인트로 슬라이드의 마지막 화면에는 **START버튼**으로 **앱을 시작**할 수 있다.

* 인트로 슬라이드는 다음과 같이 실행된다.


<img src="https://user-images.githubusercontent.com/48484193/59270364-3f7d5800-8c8c-11e9-9003-6170578794d6.gif" width="250px"/>

---
#### 2.1 인트로 슬라이드 구현하기 

* **자바 클래스**는 `WelcomeActivity`, `MyPagerAdapter`을 생성하였다.  
![캡처](https://user-images.githubusercontent.com/48484193/59276220-db14c580-8c98-11e9-914c-0dad1bc0d7f8.PNG)

* **레이아웃**은 슬라이드 할 만큼 생성하면 된다.  
![캡처2](https://user-images.githubusercontent.com/48484193/59276568-8160cb00-8c99-11e9-8d5d-a9c751901893.PNG)



##### 2.1.1 레이아웃(Layout)

레이아웃은 크게 두가지로 보면 된다.  
**activity_welcome**과 **slider**부분이다.  

###### * **activiy_welcome**
  
먼저 **activity_welcome**에서 주의할 점은  
```
<android.support.v4.view.ViewPager
    android:id="@+id/view_pager"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
```
*ViewPager* 이다. 

*ViewPager* 는 페이지가 슬라이드 되어 좌우로 넘어갈 수 있도록 해주는 기능이다. 
주의해야 할 점은 *ViewPager* 는 support v4에서 제공하므로, *프래그먼트도 이에 맞춰주어야 한다.  


> **프래그먼트:** 하나의 액티비티가 여러 개의 화면을 가지도록 만들기위해 고안된 개념이다.  
다양한 크기의 화면을 가진 모바일 환경이 늘어남에 따라  하나의 디스플레이 안에서 다양한 화면 요소들을 보여주고 싶은 요구가 생기게 되었다.   
대표적인 예로 태블릿 PC를 들 수 있다.


출처: https://tedrepository.tistory.com/5 [Ted's IT Repository]  

**activity_welcome**을 완성하면 인트로 슬라이드의 하단부분이 완성된다.  
![캡처3](https://user-images.githubusercontent.com/48484193/59280399-7eb5a400-8ca0-11e9-9daa-2e5abdad8fbe.PNG)  

###### * **slider** 

**slider**에서는 인트로 슬라이드의 화면 하나하나를 보여준다.  
```
<ImageView
 android:src="@drawable/p1"
```
*ImageView*에는 인트로 슬라이드에 띄우고 싶은 이미지를 넣어주면 된다.  
이미지는 res->drawble에 이미지파일을 넣으면 @drawable/ 로 불러올 수 있다.   

*TextView*에는 해당 이미지에 맞는 설명을 넣으면 된다.  
<img src="https://user-images.githubusercontent.com/48484193/59283705-f20de480-8ca5-11e9-94b6-cd4ab2d036ca.PNG" width="200"><br>  
#### 2.1.2 자바 클래스(Java Class)
자바 클래스에는 **MyPagerAdapter**, **WelcomeActivity**가 있다.  

###### * **MyPagerAdapter**  
* PagerAdapter를 구현할 때 오버라이드 해야 하는 메소드

| 메소드    | 설명       |
| ------------ | ------------|
| **getCount**()  | 사용 가능한 뷰의 갯수를 리턴.|
| **isViewFromObject**(View view, Object object)  | 페이지뷰가 특정 키 객체(key object)와 연관되는지 여부. |
| **instantiateItem**(ViewGroup container, int position)    |position에 해당하는 페이지 생성. |
| **destroyItem**(ViewGroup container, int position, Object object) | position 위치의 페이지 제거.  |

###### * **WelcomeActivity**
```
btnSkip = findViewById(R.id.btn_skip);
btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isUsing == false)
                    startMainActivity();
                else
                    startActivity(new Intent(WelcomeActivity.this, LobbyActivity.class));
                finish();
            }
        });
```
*SKIP* 버튼에 관한 내용이다.  
*SKIP* 버튼을 눌렀을 때 NFC태그를 하지 않았을 시에는 *NFCActivity*를 시작하고, NFC태그를 했을시에는 *LobbyActivity*를 시작한다.
```
btnNext = findViewById(R.id.btn_next);
btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentPage = viewPager.getCurrentItem()+1;
                if(currentPage < layouts.length) {
                    viewPager.setCurrentItem(currentPage);
                } else {
                    startMainActivity();
                }
            }
        });
```

*NEXT* 버튼에 관한 내용이다.  
*NEXT* 버튼을  눌렀을 때 현재 페이지에 *ViewPager*에 아이템을 추가한 다음페이지가 나오게되고, 마지막 페이지에는 버튼을 눌러 앱을 시작한다. 
```
layouts = new int[]{R.layout.slider_1, R.layout.slider_2, R.layout.slider_3, R.layout.slider_4 ... 생략
```
*layout*을 배열로 생성해 인트로 슬라이더의 화면을 넣어준다.  
참고로 페이지의 순서는 배열에 넣는 순서와 동일하다.
### 2.2 동작 방식
1. **getCount**() 메소드 : *ViewPager*의 전체 페이지 수 결정.  
뷰페이저에 포함된 전체 페이지 수는  getCount()  메소드의 리턴 값으로 결정됩니다.  **getCount**() 는 개발자가 직접 오버라이드하여 작성하는 메소드이므로 **전체 페이지 수는 개발자가 결정**할 수 있다. 
```
public int getCount() {
        return layouts.length;
    }
```
본 코드에서는 layout 배열의 길이로 리턴해주었다.

2. **ViewPager**의 페이지 생성과 관리 :
*ViewPager*는 항상 현재 페이지를 기준으로 좌/우 하나씩 존재한다. 즉, **현재 페이지를 포함하여 최대 세 개의 페이지를 생성 및 관리**한다. 

3. **instantiateItem**() 메소드 : **화면에 표시할 페이지뷰 생성**한다.
 파라미터로 전달된 position에 해당하는 페이지를 생성한 다음, 또 다른 파라미터로 전달된 컨테이너(=뷰페이저 객체)에 생성된 페이지뷰를 추가하면 된다.
 ```
 public Object instantiateItem(@NonNull ViewGroup container, int position) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(layouts[position], container, false);
        container.addView(v);
        return v;
    }
 ```
 4. **isViewFromObject()** 메소드 : 페이지뷰가 키 객체와 연관되는지 확인한다. 뷰페이저 내부적으로 관리되는 키 객체(key object)와 페이지뷰가 연관되는지 여부를 확인하는 역할을 수행한다.  
 
 그 이유는 *ViewPager*는 내부적으로 각 페이지를 뷰(View)로써 직접 관리하지 않고, 각 페이지와 연관되는 **키 객체**(key object)로 관리하기 때문이다.
 > **키 객체** 는 특별한 타입의 클래스 객체를 말하는 것은 아니고, 페이지 참조 및 식별을 위해 사용되는  Object  타입 객체를 말한다.
키(key)는 ArrayList로 관리되는데, 이는 페이지의 위치와 관계없이 지정된 페이지를 추적하고 고유하게 식별하게 해준다.
 ```
 public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
 ```
 5. **destroyItem()** 메소드 : 생성한 페이지뷰를 제거한다.  
 ```
 public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        View v = (View) object;
        container.removeView(v);
    }
 ```
 * *ViewPager*의 페이지를 생성하고 유지하는 방법  
 <img src="https://user-images.githubusercontent.com/48484193/59297858-360fe200-8cc4-11e9-90ad-fe914b82a13d.PNG" width="400"><br>  
 
 출처 : https://recipes4dev.tistory.com/148
### 3. NFC 태그
등록된 디바이스만 사용하기위해 NFC태그를 이용해 사용자를 구분한다. 
- 휴대폰이 NFC기능을 지원하는지 확인 후 NFC가 활성화가 안되어있으면 Dialog를 띄워 활성화를 요구한다. 어플을 재시작 후 태그를 올바로 진행하면 onNewIntent(Intent intent)가 호출된다.
 ```
 //NFCActivity.class
 public class NFCActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //생략
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if(nfcAdapter!=null)
        {
            if (!nfcAdapter.isEnabled())
            {
                show();
            }
            Intent intent = new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        }
    }
}
```
- NFC가 활성화 되면 태그가 되기를 기다린다. NFC태그가 이뤄지면 태그된 NFC의 ID를 가져온다. 이 ID는 데이터베이스에 등록되어있는지 확인 후(task) 어플 사용 가능 여부가 결정된다. 
```
//NFCActivity.class
public class NFCActivity extends AppCompatActivity {
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        if (tag != null) {
            GetData task = new GetData();
            // NFC 고유번호를 이용하여 사용하게 될 디바이스의 DB id 배정
            byte[] tagId = tag.getId();
            tid = toHexString(tagId);
            task.execute(tid);
        }
    }
}
```
### 4. 프로필 입력
- 아이 정보를 입력한다. 입력 정보는 이름, 나이, 보호자 전화번호이며 person 테이블에 값이 저장된다. 프로필 보기 창에서 데이터베이스를 통해 위 값들을 받는다.  
<img src="https://user-images.githubusercontent.com/48272857/59279191-72c8e280-8c9e-11e9-84fe-e76585ab2d60.jpg" width="250px"/><br>  
- 다음처럼 php로 사용자 분별을 위한 id와 사용자가 입력한 데이터인 name, age, phone이 전달된다.
```java
//ProfileActivity.java
@Override
        protected String doInBackground(String... params) {
            id = params[1];
            name = params[2];
            age = params[3];
            phone = params[4];
            String serverURL = params[0];
            String postParameters = "id=" + id + "&name=" + name + "&age=" + age + "&phone=" + phone;
            //생략...
```
### 5. 아이와의 거리 확인
###### 5.1.1 블루투스 Rssi 얻기 위한 예상 시나리오.
1. 앱 실행시 명시적으로 창을 띄워 블루투스 활성화 시키기.
2. 리스트 뷰를 활용하여 블루투스 기기 검색.
3. 블루투스 페어링을 통해 지속적인 Rssi 값을 얻으려 함.  

  _<u>RSSI : Received Signal Strength Indicator.</u>_
 
###### 5.1.2 시나리오의 문제점.
<img src = "https://user-images.githubusercontent.com/48505947/59268806-76ea0580-8c88-11e9-8396-6705bc20493b.png"></img><br>

- Rssi 값은 블루투스 장치를 검색 할 때만 가져올 수 있다.
- Back에서 장치검색을 계속 실행하여 Rssi를 불러와도 엄청난 배터리가 소모된다.

###### 5.1.3 초기 시나리오의 문제점 해결방안.

<img src = "https://user-images.githubusercontent.com/48505947/59269470-1a87e580-8c8a-11e9-9e20-2a53eeb946ac.png"></img><br>

- 장치와의 페어링이 불필요하다.
- UUID + Major + Minor + Measured Power(RSSI) 디바이스에 전송됨.  

### 5.2 비콘 RSSI 얻는 법
###### 5.2.1 Permission 설정.
 ```
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
<uses-permission android:name="android.permission.BLUETOOTH_ADMIN" />
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
<uses-permission android:name="android.permission.BLUETOOTH" />
 ```

 - BluetoothLeScanner.startScan(ScanCallback) 을 사용하기 위해 추가.
 >[StartScan 가이드 바로가기](https://developer.android.com/reference/android/bluetooth/le/BluetoothLeScanner.html#startScan(android.bluetooth.le.ScanCallback))
 
 - 블루투스 기능을 사용하기 위해서 BLUETOOTH를 선언해야 한다.
 >[Bluetooth 가이드 바로가기](https://developer.android.com/reference/android/Manifest.permission.html?hl=ko#BLUETOOTH)
 
 ###### 5.2.2 블루투스 권한 설정
 ```
 //ShowActivity.class
 if (bluetoothAdapter == null) {
                //핸드폰이 블루투스를 지원하지 않을 경우.
        } else {
            if (bluetoothAdapter.isEnabled()) {
                //Bluetooth StartScan 생략. 
                }
            } else {
                //명시적 블루투스 권환 허용창 
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            }
        }
```
1. bluetoothAdapter == null
 - 블루투스를 지원하지 않는 핸드폰일 경우를 확인한다.

2. bluetoothAdapter.isEnabled()
 - 블루투스가 활성화된 상태일 경우를 확인한다.

3. 블루투스를 허용할지 사용자에게 명시적으로 보여준다.
```
//ShowActivity.class
Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
```
- startActivityForResult()로 전달된 REQUEST_ENABLE_BT 상수는 시스템이 requestCode 매개변수로서 onActivityResult() 구현에서 개발자에게 다시 전달하는 지역적으로 정의된 0보다 큰 정수다.

4. 블루투스 활성화 후
``` 
//ShowActivity.class
protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            // 사용자가 확인신호를 누름
            if (bluetoothAdapter.isEnabled()) {
                // 블루투스 활성화 상태인지 체크한다.
                //StartScan 생략.
            }
        }
```
 
 - 사용자가 블루투스 활성화 '확인'을 누르면 resultcode == RESULT_OK로 넘어온다.
 - 블루투스 활성화에 성공하면 액티비티가 onActivityResult() 콜백에서 RESULT_OK 결과 코드를 수신한다. 오류 때문에 블루투스를 활성화하지 못한 경우(또는 사용자가 "No"를 선택한 경우) 결과 코드는 RESULT_CANCELED이다.
 
>[Bluetooth 가이드 바로가기](https://developer.android.com/guide/topics/connectivity/bluetooth?hl=ko)
 
 ###### 5.2.3 ScanCallback 클래스를 이용한 RSSI 얻기
 ```
 //ShowActivity.class
  new ScanCallback() {
            @Override
            public void onScanResult(int callbackType, final ScanResult result) {
                super.onScanResult(callbackType, result);
                if (result.getDevice().getName() != null && result.getDevice().getName().equals("UnKnowN")) {
                    rssi = result.getRssi();
                    distance = calculateAccuracy(-59, rssi);
                    //생략.
                    }
                }
 ```
 
 1. ScanCallBack 클래스 메소드인 onScanResult를 사용한다.
 2. onScanResult(int callbackType, final ScanResult result) 파라미터 ScanResult 클래스를 사용한다.
 3. ScanResult 클래스 메소드인 getRssi()를 호출하여 Rssi를 받아온다.
 
 
### 5.3 Thread
###### 5.3.1 runOnUiThread
```
//ShowActivity.class
public void settingScanCallback() {
        call = new ScanCallback() {
            @Override
            public void onScanResult(int callbackType, final ScanResult result) {
                super.onScanResult(callbackType, result);
                if (result.getDevice().getName() != null && result.getDevice().getName().equals("UnKnowN")) {
                    rssi = result.getRssi();
                    distance = calculateAccuracy(-59, rssi);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Rssi.setText("Distance :" + " " + (Math.round(distance * 10) / 10.0) + " " + "(M)");
                            deviceName.setText("DeviceName : " + " " + result.getDevice().getName());
                            address.setText("Address : " + " " + result.getDevice().getAddress());
                            //생략..
                            }
                        });
                    }
                }
            }
        }
```
 - ScanCallback의 콜백함수가  지속적인 BLE정보를 불러온다.
 - 지속적으로 값을 TextView로 보여주기 위해서 runOnUiThread를 사용하였다.
 
 ### 5.4 Service
 ###### 5.4.1 서비스란?
  - 백그라운드에서 실행되는 작업을 수행할 수 있는 애플리케이션 구성 요소이며 사용자 인터페이스를 제공하지 않는다.
  - 즉, 어플리케이션 화면을 나가도 RSSI를 지속적으로 받기 위해 사용한다.
  >[Service 가이드 바로가기](https://developer.android.com/guide/components/services?hl=ko)
  
  ###### 5.4.2 서비스 클래스 생성 및 서비스 동작.
  1. AndroidManifest.xml에 다음 코드를 추가한다.
  ```
  <service
            android:name=".MyIntentService"
            android:exported="false"
            android:theme="@android:style/Theme.Dialog" />
  ```            
  2. MyIntentService.class 생성.
  
 ``` 
  //MyIntentService.class
  public class MyIntentService extends IntentService {
    //생략..
    public MyIntentService() {
        super("MyIntentService");
    }
    //생략..
    @Override
    public void onCreate() {
        super.onCreate();
        //생략..
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //생략..
        return super.onStartCommand(intent, flags, startId);
    }


    @SuppressLint("MissingPermission")
    @Override
    protected void onHandleIntent(Intent intent) {
        //생략..
    }
}
```
 - 서비스가 여러 개의 요청을 동시에 처리하지 않아도 되는 경우에는 IntentService를 사용하는 것이 좋다.
 - 결론적으로 작업을 수행하기 위해 onHandleIntent()를 구현하기만 하면 된다.
 >[Service 가이드 바로가기](https://developer.android.com/guide/components/services?hl=ko)
 
 >[Service 순환도 바로가기](https://t1.daumcdn.net/cfile/tistory/264BF04A5950B5D21A)
 
 
 
 3. onHandleIntent() 구현.
 ```
 public class MyIntentService extends IntentService {
 //생략
 public static volatile Boolean scanBLE;
 //생략
 protected void onHandleIntent(Intent intent) {
        BluetoothLeScanner scanner = bluetoothAdapter.getBluetoothLeScanner();
        if(scanBLE == true){
            settingScanCallBack1();
            scanner.startScan(call1);
            Log.d("LJH", "SERVICE START");
        }else{
            scanner.stopScan(call1);
            Log.d("LJH", "SERVICE STOP");
        }
    }
    //생략
}
```    
 - onHandleIntent에서 ScanCallback 콜백 함수를 호출하여 어플리케이션 화면 밖에서도 동작이 된다. 
 - scanBLE가 true면 ScanCallback 콜백 시작, false면 ScanCallback 콜백 종료.  
 
 4. 서비스 시작.
 ```
 //ShowActivity.class
 protected void onStop() {
        super.onStop();
        //생략
        MyIntentService.scanBLE = true;
        setServiceIntent();
        startService(serviceIntent);
        Log.d("LJH", "서비스 스타트!!!!");
    }
```    
 - MyIntentService.class의 변수인 scanBLE = true로 초기화를 하여 서비스를 시작한다.
 
 5. 서비스 종료. 
 ```
 //ShowActivity.class
 protected void onRestart() {
        super.onRestart();
        MyIntentService.scanBLE = false;
        setServiceIntent();
        startService(serviceIntent);
        Log.d("LJH", "ScanBle False야, Stop Service");
        //생략
    }
 ```
  - ShowActivity.class 화면으로 다시 돌아오면 MyIntentService.class의 변수인 scanBLE = false로 초기화를 하여 서비스를 종료한다.
  
 ### 5.5 Rssi 가공 및 실험결과
  5.5.1 Rssi 노이즈 보정  
  <img src="https://user-images.githubusercontent.com/48273766/59305831-f9e57d00-8cd5-11e9-805a-ee7728995737.png" width="50%" height="50%"><br>
  통신모듈의 특성상 신호값을 받아오는데 있어 노이즈가 발생하게 된다. 이러한 갑작스러운 변화값을 보정하기 위한 Size=5의 큐를 생성하며 이 큐전체의 원소값을 상기 식을 이용하여 Rssi의 평균을 구해 신호값을 안정화한다.  
  5.5.2 Rssi 기반의 거리 추정  
  <img src="https://user-images.githubusercontent.com/48273766/59306345-fef6fc00-8cd6-11e9-9021-0eefdfe96fa5.png" width="50%" height="50%"><br>
  상기 식은 사용자와 팔찌와의 거리를 수식화하기 위해 측정된 Rssi 값을 거리로 변환하는 식을 나타낸다.
  RSSI의 단위는 dBm이며 수신기에서 측정된 값이다.<br>
  n = 2(In free space)로 경로손실 지수를 나타내며 는 송신기와 수신기 사이의 거리가 1m일 때 수신 신호 세기를 나타낸다. 상기 식을 이용하여 Rssi를 미터(m) 단위의 거리로 변환가능하다.  
  5.5.3 Rssi 측정결과  
  <img src="https://user-images.githubusercontent.com/48273766/59306658-b7bd3b00-8cd7-11e9-9f89-e4fe5c55a7bc.png" width="50%" height="50%"><br>
  위 표를 볼 때 블루투스 모듈을 팔찌 안에 넣고 측정했을시 오차율이 증가하는 것으로 보인다. 이는 모듈이 팔찌에 의해 간섭이 발생된 결과이며 이는 의 값의 변경으로 보정된다.   
  5.5.4 Rssi 기반의 거리 추정 정확도  
  <img src="https://user-images.githubusercontent.com/48273766/59306897-4336cc00-8cd8-11e9-993e-ab0f159b0f0a.png" width="50%" height="50%"><br>
  상기 Rssi 기반의 거리추정 수식을 적용하여 추정된 거리별 정확도이다. 평균 정확도는 팔찌 케이스 미적용시, 적용시 각각 86.97%, 90.52%로 정확도는 Rssi 하나로는 정확도는 다소 낮으나 사용자가 참고하는 수준임을 고려할 때 양호한 수치이다. 또한, 상기 수식의 txPower 상수를 보정함으로써 오차율을 감소시킬수 있다.
  
### 6. 다이얼로그 알림  

<div>
<center>
<img src="https://user-images.githubusercontent.com/48484742/59280671-e966df80-8ca0-11e9-9905-7f950f823caa.jpg" width="200">
<img src="https://user-images.githubusercontent.com/48484742/59282129-5c715580-8ca3-11e9-9864-3e64e6c79b97.png" width="200">
<img src="https://user-images.githubusercontent.com/48484742/59281370-1bc50c80-8ca2-11e9-94d9-a7e6c36b8cbf.png" width="200">
</center>
</div>

아이(디바이스)와의 거리가 멀어지면 다이얼로그 알림이 소리와 함께 뜨게된다. 다이얼로그에는 '전화걸기'버튼과 '취소'버튼이 있는데 '전화걸기'버튼을 누르면 전화연결 화면으로 연결된다. 이 때 관리소 전화번호가 띄워져 있다.  
하나의 액티비티 내에서 구현한 다이얼로그는 서비스를 통해 띄워 줄 수 없었는데 그 이유는 안드로이드 개발자들이 다른 어플리케이션 실행도중 (게임 혹은 다른어플) 갑자기 다이얼로그가 뜨게될 경우 때문에 막아 놓은 것이다.  
따라서 본 프로젝트는 AlertDialogActivity.java파일을 새로 만들어 PendingIntent를 통해 알림을 구현한다. 원하는 상황에서만 다이얼로그를 띄우기 위해 PendingIntent를 사용한다.  

- AlertDialogActivity.java파일을 새로 만들고 레이아웃 구성한 후 알림을 위해 AudioManager, MediaPlayer를 이용한 다음과 같은 코드를 작성한다.
```
//MyIntentService
public class MyIntentService extends IntentService {
    MediaPlayer mediaPlayer;
    //생략...
    public void onCreate() {
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.spacealarm);
        //생략...
    }
}
```
- AndroidManifast.xml에서 액티비티의 테마를 다음과 같이 바꿔준다.
```
//AndroidManifast.xml
<activity
            android:name="com.example.SONZABA.AlertDialogActivity"
            android:theme="@android:style/Theme.Dialog">
```
- 미디어 볼륨 및 음악을 설정, 실행하고 '전화걸기'버튼과 '취소'버튼 이벤트를 처리한다. 미디어 파일은 app -> res우클릭 -> New -> Android Resource Directory클릭 -> Resource type을 raw로 바꾼 후 OK를 누르면 생성되는 폴더에 넣는다.
```
//AlertDialogActivity.java
public class AlertDialogActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        AudioManager mAudioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 10, AudioManager.FLAG_PLAY_SOUND);
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.spacealarm);
        mediaPlayer.start();
        
        setContentView(R.layout.activity_alert_dialog);
        Button goToLobby = (Button)findViewById(R.id.gotolobby);
        Button Call = (Button)findViewById(R.id.call);
        
        goToLobby.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.stop();
                mediaPlayer.release();
                finish();
            }
        });

        Call.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.stop();
                mediaPlayer.release();
                String tel = "tel:01023459527";
                startActivity(new Intent("android.intent.action.DIAL", Uri.parse(tel)));
                finish();
            }
        });
        //생략...
    }
}
```
- 배경 터치를 막기위해 다음과 같은 코드를 추가한다.
```
//AlertDialogActivity.java
public class AlertDialogActivity extends Activity {
    //생략...
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }
}
```
- 화면이 꺼져도 서비스가 돌면서 다이얼로그 알림이 뜰 수 있게 하기위해 다음의 코드를 추가한다.
```
//AndroidManifest.xml
<uses-permission android:name="android.permission.WAKE_LOCK"/>
```
```
//AlertDialogActivity.java
public class AlertDialogActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON// Screen을 켜진 상태로 유지
                    | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD// Keyguard를 해지
                    | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON// Screen On
                    | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);// Lock 화면 위로 실행
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (MyIntentService.wakeLock.isHeld())
            MyIntentService.wakeLock.release();
    }
}
```
```
//MyIntentService.java
public class MyIntentService extends IntentService {
    public static PowerManager.WakeLock wakeLock;
    //생략...
    @Override
    public void onCreate() {
        wakeLock=pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,"DD:DDDD");//슬립상태에 빠진 핸드폰의 CPU만 킨다
        wakeLock.acquire();
        //생략...
    }
```
- 다이얼로그가 서비스에서 띄워져야하기 때문에 MyIntentService.java에서 dialog()함수를 만들어 실행한다.  이전에 앱에서 마지막으로 보여졌던 액티비티가 배경으로 깔리는 현상을 막기위해 FLAG_ACTIVITY_NEW_TASK, FLAG_ACTIVITY_MULTIPLE_TASK 플래그를 추가한다.
```
private void dialog(){
        Bundle bun = new Bundle();

        bun.putString("notiTitle",getString(R.string.lost_child));
        bun.putString("notiMessage",getString(R.string.call_manager));
        
        Intent popupIntent = new Intent(this, AlertDialogActivity.class);
        popupIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        popupIntent.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        popupIntent.putExtras(bun);
        
        PendingIntent pie= PendingIntent.getActivity(this, 0, popupIntent, 0);
        
        try {
            pie.send();
        } catch (PendingIntent.CanceledException e) {
            e.printStackTrace();
        }
    }
```
- dialog()함수는 서비스 내에서 거리가 멀어졌을 때 불리며 계속 알림이 울리는걸 방지하기 위해 Handler를 이용해 딜레이를 준다.
```
//MyIntentService.java
public class MyIntentService extends IntentService {
    public static PowerManager.WakeLock wakeLock;
    //생략...
    public void settingScanCallback1() {
        call1 = new ScanCallback() {
            @Override
            public void onScanResult(int callbackType, final ScanResult result) {
                //생략...
                if(result.getScanRecord().getDeviceName()!= null && result.getScanRecord().getDeviceName().equals("UnKnowN")){
                    if (rssi_avg < sa.bluetooth_intensity_far && flag==false) {
                        //생략...
                        flag=true;
                        dialog();
                        new Handler().postDelayed(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                flag=false;
                            }
                        }, 60000);//60000 = 1분
                    }
                }
            }
        }
    }
}
```
### 7. 로비화면
1. 공지사항  

<img src="https://user-images.githubusercontent.com/48272857/59277314-f254b280-8c9a-11e9-9668-8d439c398347.jpg" width="250px"/>   
-  로비 중앙에 공지사항이 표시되고 1분 간격으로 서버로부터 업데이트 받는다.  

```
// METHOD TO UPDATE NOTICE TEXTVIEW
    public void UpdateNotice()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Thread.interrupted())
                    try {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                new GetNotice().execute();
                            }
                        });
                        Thread.sleep(60000); // EXECUTE METHOD EVERY MINUTE
                    }
                    catch (InterruptedException e) { Log.d(TAG, "UpdateNotice : ", e); }
            }
        }).start();
    }
```


2. 언어설정  

<div>
<img src="https://user-images.githubusercontent.com/48484742/59271663-3772e780-8c8f-11e9-8d3c-28dc907c9ce0.png" width="200">
<img src="https://user-images.githubusercontent.com/48484742/59277240-cdf8d600-8c9a-11e9-9330-ad73f451526f.png" width="200">
</div>  

국내에서만 국한되는 것이 아닌 세계화 동향에 맞춰 많은 수요가 예상되는 주요나라들의 언어기능을 추가하여 외국인들의 사용성 및 접근성을 높였다.    

- 각 언어에 맞는 string파일을 만든다. 그림과 같이 app->res->values->strings경로로 들어가 strings폴더를 우클릭 하고 new->values resouces file를 선택한다. Available qualifiers 목록중에 Locale을 선택해주면 다국적 언어 리스트가 나오며 만들고자 하는 언어를 선택한다. 하드코딩으로 작성해야 언어 설정이 가능하다.  

- mymenu.xml 파일을 만들고 Item을 이용해 어플 내에서 버튼을 통해 언어변경이 가능하도록 한다.  
```
//LobbyActivity.java
@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case R.id.lang_change_btn:
                change_lang();
                break;
            //생략...
        }
        return true;
    }
```  
- 언어 변경 후 앱을 재실행 했을 때 언어 설정이 유지가 되지 않는 문제점이 발생하는데 sharedpreferences를 사용해 xml파일에 필요한 데이터를 저장하여 쉽게 읽고 쓰게 해주어 해당 문제점을 해결 할 수 있다.  
```
//SplashActivity
@Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
        // Load Current Language in App
        try {
            setLanguage();
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //생략...
    }
    
    private void setLanguage()
    {
        SharedPreferences langSp = getSharedPreferences("CommonPrefs",MODE_PRIVATE);
        String lang = langSp.getString("Language", Locale.getDefault().getLanguage());
        if (lang.equalsIgnoreCase(""))
            return;
        myLocale = new Locale(lang);
        Locale.setDefault(myLocale);
        android.content.res.Configuration config = new android.content.res.Configuration();
        config.setLocale(myLocale);
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
    }
```  
3. 지도  
<img src="https://user-images.githubusercontent.com/48484742/59299268-2e057180-8cc7-11e9-8321-2d0a234d9cae.jpg" width="200"> <br>
아이의 키에 맞는 놀이기구를 보여준다. 마커 클릭시 자세한 정보를 확인할 수 있게 url을 연결한다. seekbar를 이용해 키를 조절하고 버튼을 통해 적용시켜 지도에 보여준다.  
- 구글지도를 띄우기 위해 fragment를 사용한다.
```
//activity_map.xml
<LinearLayout
    <fragment
        android:id="@+id/map"
        class="com.google.android.gms.maps.MapFragment"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_weight="1"/>
        //생략...
</LinearLayout>
```
- 키에 따라 놀이기구를 GoogleMarker로 표시한다. 놀이기구의 좌표(위도,경도), 이름, 제한 키, url정보를 markerOption을 통해 저장하고 지도에 보여준다.  
```
public class MapActivity extends AppCompatActivity implements OnMapReadyCallback{
    //생략...
    MarkerOptions markerOptions = new MarkerOptions();
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            FragmentManager fragmentManager = getFragmentManager();
            MapFragment mapFragment = (MapFragment)fragmentManager                .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
            //생략...
        }
        @Override
        public void onMapReady(final GoogleMap map) {
            list = new double[]{37.290163, 127.201815, ...};
            name = new String[]{"T 익스프레스", ...};
            att_url = new String[]{"http://www.everland.com/mobile/everland/favorite/attraction/22.el", ...}
            height = new int[]{129, ...}
            
            for(int i =0; i < list.length; i+=2) {
            locations.add(new LatLng(list[i],list[i+1]));
            }
            for(LatLng location : locations) {
                markerOptions.position(location);
                markerOptions.title(name[cnt]);
                if(height[cnt] < child)
                    map.addMarker(markerOptions);;
            }
            sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            //생략...
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        map.clear();
                        for(LatLng location : locations) {
                            markerOptions.position(location);
                            markerOptions.title(name[cnt]);
                            if(height[cnt] < child)
                                map.addMarker(markerOptions);
                            //생략...
                        }
                        //생략...
                    }
                });
            }
        });
        map.setOnInfoWindowClickListener(infoWindowClickListener);
        //생략...
    }
    GoogleMap.OnInfoWindowClickListener infoWindowClickListener = new GoogleMap.OnInfoWindowClickListener() {
        @Override
        public void onInfoWindowClick(Marker marker) {
            //생략...
            Intent intent = new Intent(Intent.ACTION_VIEW);
            Uri uri = Uri.parse(att_url[cnt]);
            intent.setData(uri);
            startActivity(intent);
        }
    };
}
```
4. 관리자 모드  
- 상단 좌측을 더블클릭한다.  
<img src="https://user-images.githubusercontent.com/48272857/59276127-aacd2700-8c98-11e9-8c2a-c2e383cba313.jpg" width="200px"/>
- 사전 설정된 비밀번호나 따로 설정한 비밀번호를 입력한다.  
- '사용자 데이터 리셋' 버튼으로 사용자가 팔찌를 반납했을 경우 초기화가 가능하다.  
- '공지사항 작성' 버튼으로 로비에 표시되는 공지사항을 작성할 수 있다.  
- 결과창에 기능들의 작동결과가 출력된다.  
<img src="https://user-images.githubusercontent.com/48272857/59276126-aacd2700-8c98-11e9-8cb7-78270b231a85.jpg" width="200px"/>

5. 시장성  
우선적으로 높은 입장객 수를 보유한 놀이공원에 본 연구결과인 팔찌를 공급했을 때 높은 수익이 기대된다. 현재 놀이공원의 한해 방문객 수는 다음과 같다.  
<img src="https://user-images.githubusercontent.com/48273766/59314754-aaad4580-8cf1-11e9-9308-e20eafbad2e9.png" width="500px"/>  
상단 자료와 같이 국내 롯데월드와 에버랜드의 한해 총 방문객수는 1500만명으로 추산되고 있으며 국내 다른 놀이공원을 비롯한 대형광장에서 사용될 수 있는 점을 고려할 때 방문객 수는 이를 넘어설 것을 추정되며 국내 8세이하 유아 통계를 볼 때 약 240만명으로 추산되며 통계청, 인구동향조사, 통계청, 2019.05.29기준
 이들 중 만6~9세의 스마트폰 보급률이 50.6% 미디어통계포털, 미디어 기기 보유(휴대폰), 미디어통계포털, 2018년 기준임을 고려할 때 7세까지의 잠재적 소비자는 약 120만명으로 추산된다.<br>
- 팔찌 단가<br>
블루투스 비콘 HM-10: 약 3000원<br>
NFC: 약 250원<br>
3D 모델비용: 약 330원<br>
스트랩: 약 100원<br>
대당 개발수익비: 약 3600원<br>
총액: 약 7280원<br>
대량생산이 이루어질 때 대당 단가는 상기 금액보다 감소되며 개발수익비는 증가되며 개발수익비는 약 50%로 가정하여 계산하였다.<br>
놀이공원 기준: 7280*41000=약 3억<br>
국내 미취학 아동 기준: 7280*120만=약 87억<br>
현재 국내에서는 약 90억 정도의 시장규모를 보이며 출산율을 고려해볼 때 매년 약 40만명의 잠재적 소비자가 지속적으로 발생한다. 국내의 대표적인 놀이공원 두곳을 기준으로 작성되었고 국내시장은 제한적인 특성을 보여주고 있다.<br>
미국과 영국은 매년 각각 약 46만명, 11만명의 미아가 발생하고 있으며 이는 미아 문제가 국내만이 아니며 전세계의 사회적 문제임을 보여준다. 
이는 세계시장에 충분한 시장성을 가졌음을 의미한다.  <br>
REFERENCE: International Centre for missing & exploited children, Missing Children’s Statistics, International Centre for missing & exploited children.

---

### 동작 동영상  
1. 전자티켓  
[![](http://img.youtube.com/vi/3Bs-gfSAk9Q/0.jpg)](http://www.youtube.com/watch?v=3Bs-gfSAk9Q "")  
2. 프로필 보기까지  
[![](http://img.youtube.com/vi/sAEyqkDJjhM/0.jpg)](http://www.youtube.com/watch?v=sAEyqkDJjhM "")
3. 언어 변경  
[![](http://img.youtube.com/vi/vG_SQjo8ly0/0.jpg)](http://www.youtube.com/watch?v=vG_SQjo8ly0 "")
4. 관리자모드, 공지사항  
[![](http://img.youtube.com/vi/C4wrD8I8SPE/0.jpg)](http://www.youtube.com/watch?v=C4wrD8I8SPE "")
5. 아이정보초기화  
[![](http://img.youtube.com/vi/FRgbE2feLNc/0.jpg)](http://www.youtube.com/watch?v=FRgbE2feLNc "")
6. 지도보기   
[![](http://img.youtube.com/vi/f485b8O0mVw/0.jpg)](http://www.youtube.com/watch?v=f485b8O0mVw "")
7. 다이얼로그 (서비스x)  
[![](http://img.youtube.com/vi/eTKeifIiATo/0.jpg)](http://www.youtube.com/watch?v=eTKeifIiATo "")
8. 다이얼로그 (서비스o)  
[![](http://img.youtube.com/vi/6b-bvjCXdik/0.jpg)](http://www.youtube.com/watch?v=6b-bvjCXdik "")
9. 앱 실사용   
[![](http://img.youtube.com/vi/j0Zvgoz5r-Y/0.jpg)](http://www.youtube.com/watch?v=j0Zvgoz5r-Y "")

---

### 개발 관련

- 사용한 프로그램  
<a href="https://developer.android.com/studio">
<img src="https://cdn1.iconfinder.com/data/icons/iconza-circle-social/64/697039-android-48.png"></a>
<a href="https://code.visualstudio.com">
<img src="https://cdn1.iconfinder.com/data/icons/iconza-circle-social/64/697034-windows-visual-studio-48.png"></a>
<a href="https://www.phpmyadmin.net">
<img src="https://a.fsdn.com/allura/p/phpmyadmin/icon?1470008162?&w=90"></a>
<a href="https://www.mysql.com/">
<img src="https://cdn4.iconfinder.com/data/icons/logos-brands-5/24/mysql-48.png"></a>
<a href="https://httpd.apache.org">
<img src="https://a.fsdn.com/allura/p/apachehttpportable/icon?1419722295?&w=90"></a>

- 참고 사이트  
<a href="https://medium.com/wasd/%EC%95%84%EB%91%90%EC%9D%B4%EB%85%B8%EB%A1%9C-%EB%B9%84%EC%BD%98%EC%9D%84-%EA%B5%AC%ED%98%84%ED%95%B4%EB%B3%B4%EC%9E%90-fc7a8c223eec">블루투스 모듈 설정</a>  
<a href="https://webnautes.tistory.com/">tistory 블로그 - 멈춤보단 천천히라도</a>

- 표 출처  
최소영, 최재필, 유세원, 한규빈 (2018). 미아 사례조사를 통한 행태 및 환경적 측면에서의 미아 발생원인 도출 연구. 대한건축학회 학술발표대회 논문집, 38(1), 123-124
