<img src="https://user-images.githubusercontent.com/48272857/57022991-d279aa00-6c6b-11e9-902b-9abeacd4cd55.png" width="300px">


# 손잡아 (SONZABA)
 - 스마트 모바일 프로그래밍 2조 프로젝트 (UnKnowN)
 
# 간략 소개
  1. 어플을 이용한 미아 방지 팔찌
    - 직접 모델링한 팔찌안에 bluetooth 모듈, NFC 태그와 건전지로 구성하였다.     
    NFC 태그로 어플과 연동 후 bluetooth 통신으로 거리를 측정한다.

  2. 종이 티켓을 전자화 
  
  3. 편리한 관리
      - 앱에서 관리할 수 있는 관리자 모드 구현
      
        * 공지사항 작성 기능
        * 사용자가 팔찌 반납 시 정보 초기화 기능
  
  4. 다국어 지원 (언어 추가 가능)
      - 현재 한국어, 영어, 일어, 중국어 구현


# 사용전에 
앱을 위한 서버가 필요합니다.  
Mysql + phpmyadmin을 지원하는 WampServer를 이용하면 손쉽게 서버를 구축할 수 있습니다.

WampServer | http://www.wampserver.com/en/

### 서버 구축 
- #### 필수 
1. https://sourceforge.net/projects/wampserver/files/ 에 접속하여 초록색 버튼 Download Latest Version을 클릭하면 최신 버전의 WampServer가 다운로드 됩니다.

2. 설치 중간에 웹브라우저와 편집기에 대한 설정을 물어보는데 인터넷 익스플로러와 노트패드를 기본으로 쓸땐 '예'를 누르고 진행하면 됩니다.

(설치과정에 방화벽 앱 허용 창이 뜰때 자신의 인터넷이 개인 네트워크라면 개인에 체크, 다른 인터넷 환경에서도 서버를 사용하려면 공용에도 체크해줘야 합니다.)

3. 정상적으로 설치가 완료되고 실행됬다면 오른쪽 밑 트레이 아이콘에 녹색 아이콘이 뜰것입니다.

4. 아이콘을 왼쪽 클릭한 후 메뉴에서 MySQL > MySQL console을 선택합니다.

5. 사용자 이름을 입력하는 창이 뜨는데 그대로 OK 누릅니다.
   그 후 Enter password : 가 콘솔창에 뜨는데 엔터를 치면 다음으로 넘어갑니다.
  
6. root 계정의 비밀번호를 다음과 같이 입력하여 변경합니다. 
```sh
SET PASSWORD FOR root@localhost = PASSWORD('변경할 비밀번호');
```
(계정명과 비밀번호는 phpMyAdmin에 접속할 때와 php파일에서 사용합니다.)

7. 콘솔에 quit 입력하여 콘솔창을 종료합니다.

8. 트레이 아이콘을 왼쪽버튼 클릭하고 메뉴에서 phpMyAdmin를 선택하여 접속합니다.

9. 방금전에 설정했던 비밀번호로 root 계정에 로그인하여 정상적인 접근이 가능한지 확인합니다.

- 이제 외부의 접속을 허가하는 설정입니다.

10. 트레이 아이콘 왼쪽 클릭하면 보이는 메뉴에서 Apache > httpd-vhosts.conf 를 선택합니다.

11. 다음과 같이 Require local -> Require all granted 로 설정합니다. 
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
12. 저장 후, 설정을 적용하기 위해 트레이 아이콘 왼쪽 클릭하면 보이는 메뉴에서 Restart All Services를 선택합니다.

13. 윈도우 10 환경에서 추가 설정을 해주어야 합니다.
제어판 > 네트워크 및 인터넷 > 네트워크 및 공유 센터 > Windows Defender 방어벽 > 고급 설정으로 이동합니다.

14. 왼쪽 목록에 인바운드 규칙을 누르고 오른쪽 작업에서 새 규칙을 선택합니다.

15. 포트 체크 > TCP 체크, 특정 로컬 포트에 '80' 입력 > 연결 허용 > 개인, 공용 체크 > 이름은 원하는 이름으로 설정 후 마침 

이 설정은 공유기의 네트워크를 사용하는 다른 PC나 스마트폰에서 웹서버에 접속할 수 있도록 합니다.

16. 다음 링크에서 php 파일들을 다운받아서 wampServer 내의 www폴더에 넣습니다. (메뉴에서 www 클릭)

 --- 링크 ---
 
 
(이후부터는 프로젝트 환경을 따라가고 있습니다.)

17. Mysql 콘솔을 다시 열어서 로그인 후 데이터베이스(test)를 생성합니다.  
```sh
mysql> create database test DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
```
18. 데이터베이스 선택 후 테이블을 생성합니다.
```sh
mysql > use test;
Database changed

mysql > CREATE TABLE person(
    id INT(11) NOT NULL AUTO_INCREMENT,
    serial VARCHAR(20) NOT NULL,
    name VARCHAR2(10),
    age INT(5),
    phone INT(11),
    PRIMARY KEY(id)
)

mysql > CREATE TABLE notice(
    id INT(11) NOT NULL AUTO_INCREMENT,
    content VARCHAR2(255) NOT NULL,
    date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(id)
)
Query Ok, ... 
```
mysql > show tables로 테이블이 잘 생성되었는지 확인합니다.

19. dbcon.php을 수정하여 다음 내용을 자신의 환경에 맞게 수정합니다.
```sh
    $host = 'localhost';
    $username = '~~'; # MySQL 계정 아이디
    $password = '~~'; # MySQL 계정 패스워드
    $dbname = 'test'; # DATABASE 이름
```
20. Lobby, NFC, Profile Activity의 'IP_ADDRESS' 를 본인 ip로 변경합니다.

wampServer 설치와 설정은 php파일 코드는 다음 사이트의 도움을 받았습니다.  
  
  개인 환경에 따라 차이가 있을 수 있고 해결법은 출처에서 확인할 수 있습니다.  
  
  자세한 내용은 출처에서 확인하세요.  
  
  출처 : 멈춤보단 천천히라도  
https://webnautes.tistory.com/1206  
https://webnautes.tistory.com/828
