# Session 활용 데이터 전달 활용 프로젝트
* Session을 활용하여, 회원 정보를 저장 및 활용을 연습하는 미니 프로젝트
* 회원가입, 로그인, 회원 정보 조회, 회원 정보 수정, 회원 탈퇴(비활성) 기능 구현
* JSP와 Servlet을 활용하여 구성


## Login

![Login](https://github.com/user-attachments/assets/b8371860-763f-47da-b514-8cc4413af137)

* 홈페이지의 시작점인 로그인 페이지, 비정상적인 접속을 통해 (세션이 없거나 유효하지 않으면)은 해당 페이지로 돌아옴

![LoginRequired](https://github.com/user-attachments/assets/3104ef0b-b72f-4de8-b8ee-437958b2c674)

* 기본적으로 ID, PW는 필수사항으로 미기재 시 로그인 기능이 활성화 되지 않음.

![LoginProcess1](https://github.com/user-attachments/assets/2942a8b1-85b0-4adb-815b-be0a58f22bc1)
![LoginProcess2](https://github.com/user-attachments/assets/e2fbc376-3c5c-4030-9516-f33b1c64b2ec)
![LoginProcess3](https://github.com/user-attachments/assets/3103e29d-d666-4018-8664-ee514b43001e)
![LoginProcess4](https://github.com/user-attachments/assets/f364adc3-d76e-433b-8b4f-37079986470a)

* 첨부 이미지처럼 로그인을 시도하면 LoginServlet으로 이동하여 작업을 진행.
* DAOImplement의 SeleteByUserId 메소드를 통해 입력한 userid를 통해 DB에서 userid, password를 서치를 실행
* 그 후 Servlet의 doPost에서 입력한 id, pw를 파라미터로 얻은 후 해당 메소드를 실행해 데이터를 비교
* id와 pw가 일치하면 userid로 세션을 생성한 후 alert으로 메세지 출력 후 main.jsp로 이동함
* id나 pw가 일치하지 않는다면 로그인 실패 alert를 출력한 후 이전 페이지(login.jsp)로 다시 이동.


![InactiveMemeber](https://github.com/user-attachments/assets/fc8d8eba-7adf-4097-bc87-ae9ae3caa741)

* 추후 탈퇴 기능을 설명하겠지만, 기본적으로 탈퇴(비활성화)된 회원은 로그인 단계에서 세션을 생성하지 않음.