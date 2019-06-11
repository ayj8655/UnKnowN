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
  <center><img src="https://user-images.githubusercontent.com/48484742/59266598-f96fc680-8c82-11e9-9f37-7758277827bd.jpg"></center><br>
  NFC에 부여된 고유 ID값을 이용해 놀이공원이 소유한 티켓인지를 구분한다. 고유 ID값은 데이터베이스에 저장되어 있으며 등록된 NFC를 휴대폰에 태그 한 경우에만 어플리케이션 사용이 가능하다.
  
  4. 아두이노  
  <center><img src="https://user-images.githubusercontent.com/48484742/59267009-06d98080-8c84-11e9-8fa1-db883677eb76.png" width="50%" height="50%"></center><br>
  놀이공원의 티켓 검사 자동화를 위해 Arduino Uno (R3) 호환보드, RFID Rc522모듈, 서보모터를 사용하여 구현했다. NFC를 RFID에 태그하게 되면 서보모터가 작동하며 문이 열리게 된다.
  
  5. 케이스  
  <center><img src="https://user-images.githubusercontent.com/48272857/59179677-ca335980-8b9d-11e9-91dc-a0ebfb733aec.png"></center><br>
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

5. 시리얼 모니터를 통해 다음과 같이 설정한한다. (본 예시는 프로젝트 설정을 따라간다)
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

### 2. 서버 구축 [ **필수** ]

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

 --- 링크 ---
 
 
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

### 3. Google Map API
QR코드에 있는 어플을 다운받으면 앱 기능 중 지도보기가 정상적으로 사용가능하지만 따로 코드를 돌리는 경우 API키를 수정해야한다.
따라서 직접 구글 API를 써야하는 경우 밑의 방법으로 사용한다.

1. 다음 링크에 접속 후 프로젝트 만들기를 클릭한다. (프로젝트 이름은 원하는 이름으로 입한다.)  
https://console.developers.google.com/apis/library 
<img src="https://user-images.githubusercontent.com/48484742/59256055-177efc00-8c6e-11e9-8005-ef4691340e57.png"> <br>

2. 생성된 프로젝트로 들어간 후 'API 및 서비스 사용 설정'을 클릭한다.
<img src="https://user-images.githubusercontent.com/48484742/59256349-b73c8a00-8c6e-11e9-89ac-a74e69163c6f.png"> <br>

3. Maps SDK for Android를 검색하고 선택한다.
<img src="https://user-images.githubusercontent.com/48484742/59256385-cde2e100-8c6e-11e9-975f-1c6a95e0b31c.png"> <br>

4. 사용 설정을 클릭하여 활성화하고 인증 설정을 위해 '사용자 인증 정보'를 선택한다
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
### 2. 인트로 슬라이드
### 3. NFC 태그
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
1. RSSI
2. 쓰레드
3. 서비스
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
따라서 본 프로젝트는 AlertDialogActivity.java파일을 새로 만들어 PendingIntent를 통해 알림을 구현한다.  

- ndroidManifast.xml에서 액티비티의 테마를 다음과 같이 바꿔준다.
```
//AndroidManifast.xml
<activity
            android:name="com.example.SONZABA.AlertDialogActivity"
            android:theme="@android:style/Theme.Dialog">
```
- AlertDialogActivity.java파일을 새로 만들고 레이아웃 구성한 후 알림을 위해 AudioManager, MediaPlayer를 이용한 다음과 같은 코드를 작성한다.
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
```
- 화면이 꺼져도 서비스가 돌면서 알림이 뜰 수 있게 하기위해 다음의 코드를 추가한다.
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
- 다이얼로그가 서비스에서 띄워져야하기 때문에 MyIntentService.java에서 dialog()함수를 만들어 실행한다.
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
```
public class MyIntentService extends IntentService {
    //생략...
    public void settingScanCallback1() {
        call1 = new ScanCallback() {
            @Override
            public void onScanResult(int callbackType, final ScanResult result) {
                //생략...
                if(result.getScanRecord().getDeviceName()!= null && result.getScanRecord().getDeviceName().equals("UnKnowN")){
                if (rssi_avg < sa.bluetooth_intensity_far && flag==false) {
                    //생략...
                    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
                    notificationManager.notify(notificationId, builder.build());
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
```
### 7. 로비화면
1. 공지사항  
-  로비 중앙에 공지사항이 표시되고 1분 간격으로 서버로부터 업데이트 받는다.  
<img src="https://user-images.githubusercontent.com/48272857/59277314-f254b280-8c9a-11e9-9668-8d439c398347.jpg" width="250px"/> 
2. 언어설정  
국내에서만 국한되는 것이 아닌 세계화 동향에 맞춰 많은 수요가 예상되는 주요나라들의 언어기능을 추가하여 외국인들의 사용성 및 접근성을 높였다. 

<div>
<center>
<img src="https://user-images.githubusercontent.com/48484742/59271663-3772e780-8c8f-11e9-8d3c-28dc907c9ce0.png" width="200">
<img src="https://user-images.githubusercontent.com/48484742/59277240-cdf8d600-8c9a-11e9-9330-ad73f451526f.png" width="200">
</center>
</div>  

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
4. 관리자 모드  
- 상단 좌측을 더블클릭한다.  
<img src="https://user-images.githubusercontent.com/48272857/59276127-aacd2700-8c98-11e9-8c2a-c2e383cba313.jpg" width="200px"/>
- 사전 설정된 비밀번호나 따로 설정한 비밀번호를 입력한다.  
- '사용자 데이터 리셋' 버튼으로 사용자가 팔찌를 반납했을 경우 초기화가 가능하다.  
- '공지사항 작성' 버튼으로 로비에 표시되는 공지사항을 작성할 수 있다.  
- 결과창에 기능들의 작동결과가 출력된다.  
<img src="https://user-images.githubusercontent.com/48272857/59276126-aacd2700-8c98-11e9-8cb7-78270b231a85.jpg" width="200px"/>

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
