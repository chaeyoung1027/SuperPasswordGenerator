# SuperPasswordGenerator
> ### Kotlin을 활용한 비밀번호 생성 앱
> 
* 개발 기간 : 2022.09.15~2022.12.1

## 프로젝트 소개
> SuperPasswordGenerator은 랜덤 비밀번호 생성 앱 입니다. </br>
> 비밀번호의 길이와 여러 조건들을 선택해 사진이 원하는 강한 비밀번호를 생성할 수 있습니다.</br>

## Features
* `비밀번호 생성` : PasswordGenerate class
  * 비밀번호 길이와 영어, 특수문자, 숫자의 포함 유무를 선택할 수 있습니다.
  * 생성하기를 눌러 조건에 맞는 비밀번호를 생성합니다.
  * 저장하기 버튼을 눌러 아이디, 사용 사이트를 작성 후 랜덤 생성된 비밀번호를 저장할 수 있습니다.
* `비밀번호 목록` : PasswordSave class
  * 저장한 비밀번호의 정보를 확인 할 수 있습니다. 
  * 오른쪽 위에 있는 연필 아이콘을 클릭해 수정과 삭제가 가능합니다.

## Built With
* <img src="https://img.shields.io/badge/Kotlin-7F52FF?style=flat-square&logo=Kotlin&logoColor=white"/>
* <img src="https://img.shields.io/badge/Android Studio-3DDC84?style=flat-square&logo=Android Studio&logoColor=white"/>

## 실행화면
- ### 메인화면<br/>
   #### - 버튼을 눌러 비밀번호 생성하기와 저장목록으로 이동할 수 있습니다.
![image](https://user-images.githubusercontent.com/83990991/205028669-95d31e7c-c191-4051-9123-53df19e3965f.png)
- ### 비밀번호 생성화면<br/>
   #### - 최소/최대 글자, 영어/특수문자/숫자 포함여부 선택 후 비밀번호를 생성합니다.
![image](https://user-images.githubusercontent.com/83990991/205026615-34bdc241-653b-4502-8099-cb423d43992e.png)
- ### 비밀번호 저장화면<br/>
   #### - 생성한 비밀번호가 뜨고 아이디와 사용할 사이트를 추가로 작성 후 저장하기 버튼을 누릅니다.
![image](https://user-images.githubusercontent.com/83990991/205029018-e6da0bd6-9fab-4e56-a601-c566eddd22b1.png) <br/>
- ### 비밀번호 목록화면<br/>
   #### - 저장한 비밀번호들이 뜨고 오른쪽 위 쓰레기통 아이콘을 눌러 비밀번호를 지울 수 있습니다.
   #### - 쓰레기통 아이콘 옆 연필 아이콘을 눌러 비밀번호를 수정화면으로 이동할 수 있습니다.
![image](https://user-images.githubusercontent.com/83990991/205029081-e1666b6c-6d78-417b-a190-ff6380390cfd.png) <br/>
- ### 비밀번호 수정화면<br/>
   #### - 비밀번호를 원하는 것으로 바꾼 후 수정하기를 눌러 수정이 가능합니다.
![image](https://user-images.githubusercontent.com/83990991/205029172-4055e684-1874-46e8-9fd8-ae0ed229d0f8.png) <br/>


## 💻Develpoed by
- [인소리](https://github.com/Insori) / DATABASE, 화면만들기
- [임채영](https://github.com/chaeyoung1027) / 화면만들기, 이미지/아이콘 제작
