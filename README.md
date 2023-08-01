# Inflearn-Spring
### 📚 인프런 - 김영한 스프링 강의 공부 기록

#### 1. 개발 환경
* `Java 8`
* `SpringBoot v2.7.14`
* `Dependencies` : Spring Web, Thymeleaf, JPA
* `Test` : JUnit5
* `DB` : H2 Database
* `IDE` : IntelliJ IDEA Ultimate 2023.2
* `Build` : Gradle
* `형상관리` : Sourcetree, Git

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
        <img width="1030" alt="스크린샷 2023-07-29 오전 1 25 25" src="https://github.com/chujaeyeong/Inflearn-Spring/assets/123634960/cb282fe1-82ac-45a3-9e06-7bf374b1ed91">


        ➡️ 성공!
        * 경로 수동 설정 이전에 Gradle JVM을 JAVA_HOME 라고 설정 하고 터미널에서 직접 빌드 해봤는데, 잘 되긴 했음. (Gradle JVM 변경 기록: Project SDK 1.8 -> Java 1.8 -> Java Home -> add SDK 로 JVM 경로 수동설정한 Oracle OpenJDK 1.8)
        * 그러나 이렇게 바꾸니 IDE 밖에서는 jar 파일로 빌드가 잘 되는데, 멀쩡하게 잘 되던 IDE 내에서 빌드가 안 되기 시작함...(어떤 선택을 하고싶어짐)
        * 근데 그 순간! 친절한 인텔리제이가 너 JVM 다시 설정해봐 라고 하길래 음 ㅇㅋ 하고 수동설정 한 게 신의 한 수 였다... 이게되네... (이 시점에서 Gradle JVM 경로를 수동으로 설정한것임)
        * 아마 기존에 잡혀있던 Gradle JVM 경로였던 1.8 도 제대로 안 잡혀 있었던 것 같음.
          다음에 빌드 안 되면 자바 버전 + JDK 버전 터미널에서 확인 한 뒤에 인텔리제이에서 Gradle JVM 을 수동으로 경로를 잡아주는게 제일 정확할 것 같다.
        * 도움이 되었던 정보 : https://m.blog.naver.com/kaylee51/221722379602 / https://dev-emmababy.tistory.com/139
       
      <br>
      
      <br>

      <br>


  ##### `섹션 2) 회원 관리 예제 - 백엔드 개발 : 회원 리포지토리 테스트 케이스 작성`
  * **assertThat 쓰고싶은데 왜 자동완성이 안되냐고요!!!!**

      <details>
        <summary>👉 에러 내용 확인</summary>
        <img width="992" alt="스크린샷 2023-07-30 오전 1 46 19" src="https://github.com/chujaeyeong/Inflearn-Spring/assets/123634960/873511a2-c1e3-44d6-9f3d-918c040615f2">
        ➡️  Cannot resolve symbol assertThat 이러면서 에러메시지 등장... 대충 'assertThat' 이 뭔 말인지 모르겠삼 이라는 뜻
      </details>

      * 💡 Assertions.assertEquals(member, result); 이라고 쓰고 테스트 해도 되는데, 좀 더 직관적인 코드라서 assertThat을 쓰는 것 같다... 라는 나의 추측
      * 뭐 암튼 안되니까 추가해보자
      
      <br>
      
      * 1️⃣ 첫 번째 시도 : 클래스 윗단에 바로 import static org.assertj.core.api.Assertions.assertThat; 넣어주기 ➡️ 성공!
      * ‼️ 추가 : Settings -> File and Code Templete -> Code 탭에서 JUnit5 Test Class 클릭 후
        여기다가 기존에 있는 import 아래에 import static org.assertj.core.api.Assertions.*; 를 추가해주자
        <img width="1026" alt="스크린샷 2023-07-30 오전 12 45 21" src="https://github.com/chujaeyeong/Inflearn-Spring/assets/123634960/f8620f7a-4da7-4be6-83cc-b0d8617e56ba">

         ➡️  이렇게 추가해주면 바로 assertThat 을 사용할 수 있다고는 하는데, 나는 지금 테스트 코드를 작성 중에 추가한거라 바로바로 적용이 안된건지 그냥 지맘대로 적용 안 시켜주는지는 잘 모르겠다만
        귀찮다면 그냥 코드 상단에 static 하나 import 해주고 바로 사용하도록하자... 
    * 도움이 되었던 정보 : https://sinau.tistory.com/90 

           
      <br>
      
      <br>

      <br>


  ##### `섹션 6) 스프링 DB 접근 기술 : 스프링 데이터 JPA`
  * **Autowired 불가, 하나만 선택해서 스프링 빈으로 등록하길 바란다~**
    
    <details>
      <summary>👉 에러 내용 확인</summary>
      <img width="1088" alt="스크린샷 2023-08-01 오전 2 42 59" src="https://github.com/chujaeyeong/Inflearn-Spring/assets/123634960/5e6ca76b-d99d-4caf-ba14-2b6554deb357">

      ➡️  memoryMemberRepository 와 springDataJpaRepository 가 둘 다 스프링 빈으로 등록되어있다... 하나만 등록할 수 있음! 이라는 뜻 
    </details>
  
      * 💡 SpringConfig.java 에서 memoryMemberRepository 주입한건 다 주석으로 막았는데 왜 등록되었다고 하는지 생각의 늪에 빠졌다
      
      <br>
      
      * 1️⃣ 첫 번째 시도 : MemoryMemberRepository.java 에 붙어있던 @Repository 어노테이션 제거 ➡️ 성공!
        * MemoryMemberRepository.java 코드는 H2 DB 연결하기 전에 사용하던 메모리 저장용 코드라서 스프링 빈으로 등록된 상태를 고쳐줘야했다
          (springDataJpaRepository 가 정상적으로 주입될 수 있도록)
        * 근데 SpringConfig에 MemoryMemberRepository 사용과 관련된 Bean은 다 주석으로 막아놨는데 왜 둥록되어있나 봤더니
          @Repository 어노테이션을 사용하면 SpringConfig에 Bean으로 수동 등록하지 않아도 스프링이 자동으로 스프링 빈으로 등록해버려서, @Autowired 시점에 스프링 입장에서는
          먼저 등록된 MemoryMemberRepository 말고는 등록을 받을 수가 없었던 것이다
        * 그래서 MemoryMemberRepository 에 붙어있는 @Repository 어노테이션까지 제거해야 스프링 빈을 다른걸로 등록할 수 있었다는것
        * 추가로, 이건 인텔리제이의 짜잘한 버그같은데, MemoryMemberRepository.java 코드를 수정해줬는데도 여전히 SpringConfig 코드의 @Autowired 관련 에러가 표시되길래
          뭐지? 하고 그냥 테스트 코드를 돌려봤는데 딱히 에러 없이 잘 동작했다... 인텔리제이를 종료했다가 키니까 SpringConfig 코드에 에러는 사라졌다 ㅎ... 어쨌던 햅삐엔딩!
        * 도움이 되었던 정보 : https://www.inflearn.com/questions/111653
          (로드맵상 다음 회차 강의인 "스프링 핵심 원리 - 기본편" 에서 중복된 스프링 빈이 있을 때 다양한 해결 방법을 알려주신다함... 넵!)


</details>


<details>
    
  <summary>📖 수강 후기</summary>

  <br>
  
  * 1️⃣ 위대하신 스프링부트님님님을 모십니다... 일단 스프링부트 프로젝트 생성부터 눈깔 튀어나오기시작, 프로젝트 안에 깃이그노어 파일까지 들어가있는거보고 경악을 금치 못했다
  (https://start.spring.io/ 사용) 물론 스프링에서도 어느정도 다 되지만 왜 스프링부트 스프링부트 하는지 약간 조금 이해가 되기 시작했다 
  * 2️⃣ 국비 수강하면서 배웠던거 복습해서 넘 좋았습니다 (그래... 나 완전히 까먹진 않았구나...) / AOP 기술은 이번에 배워서 나중가서도 유용하게 써먹을 수 있을 것 같다는 생각!
  * 3️⃣ 정처기 공부하면서 개발공부는 손을 놨었는데 머리 풀기 좋았당 굿, 역시 뭔가 투닥투닥 치면서 공부하는게 제일 재미있다 
  * 4️⃣ 객체지향 개념은 아직은 아 이거군 감으로는 다 아는데 뭔가 정확하게 (남한테 PT하듯 술술 설명하는 정도?) 아직도 잘 모르는 것 같아서 담번 강의 들으면서 Java의 정석도 같이 병행해서 공부하려고함
  * 5️⃣ 테스트코드 작성하는거 이번에 처음 해봤는데 이거 진짜 습관 잘 들여야 될 것 같음,,, 매번 실행해보고 어 이거 안되네 투닥투닥 고치고 그랬는데 테스트코드의 중요성을 알게됨 
  * 6️⃣ 완강 고생했다 재영아~ 시작이 반이다 👊
      
      
</details>



      
