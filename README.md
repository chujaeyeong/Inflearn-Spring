# Inflearn-Spring
### 📚 인프런 - 김영한 스프링 강의 공부 기록

#### 1. 개발 환경
* Java 8
* SpringBoot v2.7.14
* Dependencies: Spring Web, Thymeleaf
* IntelliJ IDEA Ultimate 2023.2
* 빌드 : Gradle
* 형상관리 : Sourcetree, Git

<br>

#### `01. 스프링 입문 - 코드로 배우는 스프링 부트, 웹 MVC, DB 접근 기술`

<details>
  
  <summary>⚽️ 트러블슈팅</summary>
  
  ##### `섹션 1) 프로젝트 환경 설정 : 빌드하고 실행하기`
  * **인텔리제이 IDE 내에서는 빌드가 정상적으로 진행되는데, 터미널에서 빌드가 되지 않는 문제 발생**
    
      <details>
        <summary>👉 에러 내용 확인</summary>
        <div markdown="1">    
      	
        JAVA_HOME is set to an invalid directory: /Library/Java/JavaVirtualMachines/jdk1.8.0_291.jdk/Contents/Home 
            
        Please set the JAVA HOME variable in your environment to match the 
        location of your Java installation.
    
      </details>

      * 💡 컴퓨터에 세팅되어있는 JDK 버전은 1.8.0.361 인데 왜 1.8.0.291로 되어있는걸까...
        
      <br>
      
      * 1️⃣ 첫 번째 시도 : 인텔리제이 Settings (구 preferences) > Build Tools > Gradle 에서, Gradle JVM 을 1.8 로 변경해주고,
        자바 컴파일 설정과 Project Structure 설정에서 모두 SDK가 자바 버전 1.8.0_361로 되어있는지, 자바 Language level 이 8로 되어있는지 확인해주었다. ➡️ 실패, 이 설정은 맞는 것 같은데, 근본적인 해결 방법이 아니었음.
            
      <br>
      
      * 2️⃣ 두 번째 시도 : 터미널에서 직접 JDK 경로를 수정해주었다. 1.8.0.291 -> 1.8.0.361 ➡️  실패, 경로가 안 맞긴 했다... 이것도 필요하긴 했는데 이렇게 해놓고도 콘솔 빌드 할라니까 동일한 에러가 나왔고, IDE에서는 빌드가 멀쩡하게 잘 되는 요상한 문제가 계속됨.
   
      <br>
      
      * 3️⃣ 세 번째 시도 : 인텔리제이 세팅 + 프로젝트 스트럭쳐 세팅 + JDK 경로 직접 변경 후, 1.8로 변경했던 Gradle JVM 의 경로를 수동 설졍해줌
        <img width="1030" alt="image" src="https://github.com/chujaeyeong/Inflearn-Spring/assets/123634960/8b9e5f7a-60d8-45e6-8471-d32d169472d1">

        ➡️ 성공!
        * 경로 수동 설정 이전에 Gradle JVM을 JAVA_HOME 라고 설정 하고 터미널에서 직접 빌드 해봤는데, 잘 되긴 했음.
        * 그러나 이전에 잘 되던 IDE 내에서 빌드가 안 되기 시작했고... 인텔리제이가 너 JVM 다시 설정해봐 라고 하길래 음 ㅇㅋ 하고 수동설정 한 게 신의 한 수 였음... 이게되네...
        * 아마 기존에 잡혀있던 Gradle JVM 경로였던 1.8 도 제대로 안 잡혀 있었던 것 같음.
          다음에 빌드 안 되면 자바 버전 + JDK 버전 터미널에서 확인 한 뒤에 인텔리제이에서 Gradle JVM 을 수동으로 경로를 잡아주는게 제일 정확할 것 같다.
        * 도움이 되었던 정보 : https://m.blog.naver.com/kaylee51/221722379602 / https://dev-emmababy.tistory.com/139 
        

