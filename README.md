# Inflearn-Spring
### 📚 인프런 - 김영한 스프링 로드맵 강의 공부 기록

#### 📌 개발 환경
* `Java 8` ➡️ `Java 11` (Spring MVC 2편 강의부터 Java, JDK 버전 변경 완료)
* `SpringBoot v2.7.14`
* `Dependencies` : Spring Web, Thymeleaf, JPA, lombok
* `Test` : JUnit5
* `DB` : H2 Database
* `IDE` : IntelliJ IDEA Ultimate 2023.2
* `Build` : Gradle
* `형상관리` : Sourcetree, Git

#

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

#

#### `02. 스프링 핵심 원리 - 기본편`

<details>
  
  <summary>⚽️ 트러블슈팅</summary>
  
  ##### `섹션 7) 의존관계 지동 주입 - 조회한 빈이 모두 필요할 때, List, Map`
  * **의존관계 자동 주입 (DI) 관련 에러 발생 (BeanNotOfRequiredTypeException)**

    <details>
      <summary>👉 에러 내용 확인</summary>
        <div markdown="1">    
  
          org.springframework.beans.factory.BeanNotOfRequiredTypeException: Bean named 'orderService' is expected to be of type 'hello.core.order.OrderServiceImpl' but was actually of type 'org.springframework.beans.factory.support.NullBean'
         
          at org.springframework.beans.factory.support.AbstractBeanFactory.adaptBeanInstance(AbstractBeanFactory.java:417)
  	      at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:398)
  	      at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:213)
  	      at org.springframework.context.support.AbstractApplicationContext.getBean(AbstractApplicationContext.java:1163)
  	      at hello.core.singleton.ConfigurationSingletonTest.configurationTest(ConfigurationSingletonTest.java:19)
    
    </details>

    * 강의를 잘 듣다가 이상한 곳에서 에러가 터짐 BeanNotOfRequiredTypeException 이라는 에러를 검색해보니 스프링부트가 예상하던 타입의 빈이 등록되지 않아서 터진 에러 (일단 난 처음 봄)
    * ➡️ 'orderService' 라는 이름의 빈이 예상되는 타입인 'hello.core.order.OrderServiceImpl' 으로 예상되었지만, 실제로는 'org.springframework.beans.factory.support.NullBean' 이라는 타입으로 등록되었다.
    * NullBean 이란걸 등록... 한 기억이 설마 설마 하면서 봤더니 AppConfig.java 에서 뭔가 주석으로 막았다는 기억이 스물스물 나기 시작함

      <details>
          <summary>👉 테스트를 위해 수정했던 AppConfig.java 의 orderService 빈 코드 </summary>
            
      ```java
      
        @Bean
        public OrderService orderService() {
            System.out.println("call AppConfig.orderService");
        //  return new OrderServiceImpl(memberRepository(), discountPolicy());
            return null;
        }
      
       ```
      
      </details>
      
    * 이런젠장! 당연히 return 에 null을 넣어놨으니까 이러지 궁시렁궁시렁거리면서 return null 을 다시 주석으로 막고, 위에 주석으로 막고 테스트했던 OrderServiceImpl 코드를 다시 원복시켜줬다
    * 사실 BeanNotOfRequiredTypeException 에러 말고 다른 테스트코드에서는 NullPointerException 에러가 터졌는데 (OrderServiceTest.java의 
 createOrder 테스트 부분) 이것도 OrderService 관련 코드라서, OrderService 의 의존성 주입만 똑바로 고쳐두면 해결될거라 예상함

      <details>
          <summary>👉 에러 해결을 위해 원복한 AppConfig.java 의 orderService 빈 코드 </summary>
            <div markdown="1">    
      
            @Bean
            public OrderService orderService() {
                System.out.println("call AppConfig.orderService");
                return new OrderServiceImpl(memberRepository(), discountPolicy());
                // return null;
            }
      
      </details>


    * ➡️ 성공! 
    * 이건 뭐 성공이라 할 것도 없고... 내가 빈에다가 리턴 제대로 원복 안 시켜둔 잘못이니... 에러 코드를 제대로 못 읽어서 사실 챗지피티한테 설명해달라 했다 ㅎ;;; 아래는 BeanNotOfRequiredTypeException 에러의 원인과, 이 에러가 터지면 의심해봐야 하는 경우의 수를 까먹지 않게 정리하려고 굳이굳이 트러블슈팅으로 남겨봅니다 

        - ##### ` BeanNotOfRequiredTypeException 에러의 발생 원인 `
          1) **빈 등록이 올바르게 되어있지 않은 경우** : 의존성 주입을 위해 @Autowired 또는 @Inject 등의 어노테이션을 사용하여 해당 타입의 빈을 주입받는 클래스가 있는데, 해당 빈이 스프링 컨테이너에 제대로 등록되어 있지 않은 경우에 발생할 수 있다.
          2) **빈 이름 충돌** : 스프링은 빈을 등록할 때, 기본적으로 빈의 이름을 해당 클래스의 이름(첫 글자는 소문자)으로 설정하는데, 만약 다른 곳에서 이미 같은 이름으로 빈을 등록했는데 타입이 다르다면 이러한 충돌로 인해 위와 같은 에러가 발생할 수 있다.
          3) **컴포넌트 스캔 설정 오류** : 스프링에서 @ComponentScan을 사용하여 컴포넌트를 스캔하는 설정이 올바르지 않거나 누락된 경우에도 위와 같은 에러가 발생할 수 있다.
          4) **테스트 환경 설정 오류** : 테스트 클래스에서 스프링 컨텍스트를 초기화하거나 관련된 설정을 정확하게 수행하지 않은 경우에도 이러한 문제가 발생할 수 있다.

        - ##### ` BeanNotOfRequiredTypeException 에러 해결 방법 `
          1) orderService라는 빈이 정확하게 어떻게 등록되어 있는지 확인해보자 (BeanNotOfRequiredTypeException 과 NullPointerException 에러가 터진 테스트코드는 둘 다 orderService 빈 관련 테스트코드였다)
          2) 해당 빈을 주입받는 클래스의 코드를 확인하여, 어떤 방식으로 의존성을 주입받고 있는지 확인하자
          3) 스프링 컴포넌트 스캔 설정 및 테스트 환경 설정을 확인하여 올바르게 설정되어 있는지 확인하자
          4) 다른 빈 등록과의 이름 충돌이 있는지 확인하자 


</details>

<details>
    
  <summary>✏️ 학습 내용 정리</summary>
  
  * ##### `스프링 핵심 원리와 객체 지향 설계` : 다형성 - 역할과 구현의 분리, SOLID 법칙 (OCP 원칙, DIP), DI 컨테이너
  * ##### `좋은 객체 지향 설계의 5가지 원칙(SOLID) 중 3가지 관점에 대해`
    * SPR 단일 책임 원칙 : 한 클래스는 하나의 책임만 가져야 함
    * DIP 의존관계 역전 원칙 : 추상화에 의존, 구체화에 의존하게되면 안 됨. 의존성 주입은 이 원칙을 따르는 방법 중 하나
    * OCP 원칙 : 소프트웨어 요소는 확장에는 열려 있으나, 변경에는 닫혀 있어야 한다
  * ##### `의존관계 주입, DI 컨테이너(IoC 컨테이너)`
    * 정적인 클래스 의존관계에서는 코드만 보고 의존관계를 쉽게 파악할 수 있지만, 실제 어떤 객체가 주입되어 실행되는지는 알 수 없다
    * 애플리케이션 실행 시점에서 외부에서 실제 구현 객체를 생성하고, 클라이언트에 전달해서 클라이언트와 서버의 실제 의존관계가 연결되는 것을 **의존관계 주입** 이라고 한다
    * <img width="500" alt="image" src="https://github.com/chujaeyeong/Inflearn-Spring/assets/123634960/43c34c11-c830-42cf-b465-3a2f929086e6">
    * 의존관계 주입을 사용하는 이유 : 클라이언트 코드를 변경하지 않고, 클라이언트가 호출하는 대상의 타입 인스턴스를 변경할 수 있다. (ex. 할인 정책이 바뀌면 맞는 할인 정책 인스턴스만 슥삭 바꿔주면 되는것) 의존관계 주입을 사용하면 정적인 클래스 의존관계를 변경하지 않고, 동적인 객체 인스턴스 의존관계를 쉽게 변경할 수 있다. 
    * 객체 생성과 관리, 그리고 의존관계를 연결해주는 역할을 담당하는 것을 DI 컨테이너 라고 한다 (= IoC 컨테이너, 어셈블러, 오브젝트 팩토리)

  * ##### `스프링 핵심 기능`
    * 스프링 컨테이너(ApplicationContext) , 스프링 빈 
      * 스프링 컨테이너를 만드는 방법의 종류 (xml, 어노테이션 기반의 자바 설정 클래스 -> 요즘은 xml 코드를 잘 안 봐서 어노테이션으로 하는게 보편적)
      * 자바 코드로 스프링 빈을 등록하면, 생성자를 호출하면서 의존관계 주입도 한 번에 처리된다.
      * 스프링 컨테이너에서 스프링 빈이 잘 등록 되었는지 확인하는 과정을 학습함.

    * 싱글톤
      * 스프링은 싱글톤 기반 프레임워크, 태생이 기업용 온라인 서비스 기술을 지원하기 위해 탄생해서, 여러 고객이 동시에 요청하는 상황을 생각해보면 싱글톤이 훨씬 효율적이고 메모리 낭비가 덜 하기 때문에 싱글톤 패턴을 사용해서 개발을 진행
      * 싱글톤 패턴의 정의 : 클래스의 인스턴스가 딱 1개만 생성되는 것을 보장하는 디자인 패턴, 그래서 객체 인스턴스를 2개 이상 생성하지 못하도록 막아야 한다. (public 생성자 말고 private 생성자를 사용해서 외부에서 임의로 new 키워드를 사용하지 못하도록 막는 것)
      * 근데, 싱글톤 패턴을 구현하다보면 DIP, OCP원칙을 위반할 가능성이 높기 때문에, 싱글톤 패턴의 문제점을 해결하기 위해 스프링 컨테이너를 활용하는 것이다. 스프링 컨테이너는 싱글톤 패턴을 굳이 적용하지 않아도, 객체 인스턴스를 스프링 컨테이너가 싱글톤으로 관리한다. 스프링 컨테이너가 싱글톤 컨테이너 역할을 하는데, 이렇게 싱글톤 객체를 생성하고 관리하는 기능을 싱글톤 레지스트리 라고 한다.
      * 스프링 컨테이너를 사용해서 DIP, OCP, 테스트, private 생성자로부터 자유롭게 싱글톤을 사용할 수 있다!
      * <img width="500" alt="image" src="https://github.com/chujaeyeong/Inflearn-Spring/assets/123634960/c6e38b96-b749-425a-89fa-cde4b8d17a1a">
      * 싱글톤 방식의 주의점 : 상태 유지(stateful) 설계 말고 무상태(stateless) 로 설계해야함. 특정 클라이언트에 의존적인 필드가 있으면 안 되고, 특정 클라이언트가 값을 변경할 수 있는 필드가 있으면 안된다. 가급적 읽기만 가능해야 하고, 필드 대신에 자바에서 공유되지 않는 지역변수, 파라미터, ThreadLocal 등을 사용해야함. (‼️ 스프링 빈의 필드에 공유 값을 설정하면 큰 장애가 발생할 수 있음)
      * @Bean 만 사용해도 스프링이 스프링 빈으로 등록해주지만, 싱글톤을 보장해주지 않기 때문에, 스프링 설정 정보는 항상 @Configuration 어노테이션을 사용하는 것이 보편적

    * 컴포넌트 스캔
      * 컴포넌트 스캔이란? : xml 이나 @Bean 을 사용해서 스프링 빈을 직접 등록하는거 말고, 설정 정보 없이도 자동으로 스프링 빈으로 등록하는 기능, 컴포넌트 스캔은 @Autowired 어노테이션을 통해 의존관계를 자동으로 주입하는 기능도 제공한다.
      * 이름도 자동으로 지정해서 등록하는데, 앞글자만 소문자로 하고 낙타표기방식 지켜서 스프링 빈으로 등록시킨다
      * 주의사항 : 자동 등록된 빈과 수동 등록된 빈이 충돌하면, 수동 등록된 빈이 우선권을 가진다. (수동 빈이 자동 빈을 오버라이딩 함, 원래 로그 한 줄 남고 따로 터지는 오류 없이 실행되지만, 이런게 꼬여서 나중에 애매한 버그로 난리나는 경우가 있어서 최근 스프링부트에서는 수동 빈과 자동 빈 충돌나면 오류가 발생되도록 기본 값을 바꾸었다)

    * 의존관계 자동 주입
      * 다양한 의존관계 주입 방법 : **생성자 주입**, 수정자 주입 (setter 주입), 필드 주입, 일반 메서드 주입
      * 보통 생성자 주입으로 의존관계 자동 주입을 한다. (@Autowired 어노테이션 활용 방법, ‼️ 생성자가 딱 하나 있으면 @Autowired 를 생략해도 의존관계가 자동으로 주입된다. 물론 스프링 빈에만 해당되는 사항)
      * 롬복 라이브러리가 제공하는 @RequiredArgsConstructor 을 활용하면, final이 붙은 필드를 모아서 생성자를 자동으로 만들어 준다. (코드를 안 적어도 실제 호출이 가능함)
      * 보통은 편리한 자동 기능을 기본으로 사용하지만, 기술 지원 객체는 수동 등록해서 코드만 보고 어떤 의존관계 주입인지 확인할 수 있도록 하는게 좋다. 다형성을 적극 활용하는 비즈니스 로직은 수동 등록을 고민해보자

    * 빈 생명주기 콜백
      * 객체의 초기화와 종료 작업이 필요한 이유 : 데이터베이스 커넥션 풀이나, 네트워크 소켓처럼 애플리케이션 시작 시점에 필요한 연결을 미리 해두고, 애플리케이션 종료 시점에 연결을 모두 종료하는 작업을 진행하려면 객체 초기화 & 종료 작업이 필요함. 스프링 빈의 생명주기 (라이프사이클) 을 알면 스프링이 제공하는 다양한 생명주기 콜백을 사용할 수 있다.
      * 스프링 빈의 이벤트 라이프사이클 : 스프링 컨테이너 생성 ➡️ 스프링 빈 생성 ➡️ 의존관계 주입 ➡️ 초기화 콜백 ➡️ 사용 ➡️ 소멸전 콜백 ➡️ 스프링 종료
      * 스프링이 지원하는 빈 생명주기 콜백 : 인터페이스(InitializingBean, DisposableBean) / 설정 정보에 초기화 메서드, 종료 메서드 지정 / @PostConstruct, @PreDestroy 애노테이션 지원 (최신 스프링에서 가장 권장하는 방법, but 외부 라이브러리에는 적용하지 못함, 외부 라이브러리 초기화 및 종료가 필요하면 @Bean의 initMethod, destoryMethod를 사용하는걸 권장함)

    * 빈 스코프
      * 빈 스코프란? : 스프링 빈은 기본적으로 싱글톤 스코프로 생성되는데, 이거 덕분에 우리는 스프링 빈이 스프링 컨테이너의 시작과 함께 생성되어서 스프링 컨테이너가 종료될 때까지 유지되는 것이다. 스코프는 번역 그대로 "빈이 존재할 수 있는 범위" 를 뜻한다.
      * 스프링이 지원하는 다양한 스코프 : 싱글톤, 프로토타입, 웹 관련 스코프 (request, session, application)
      * 프로토타입 빈의 특징 : 스프링 컨테이너는 프로토타입 빈을 생성, 의존관계 주입, 초기화 까지만 관리하고, 이후 과정은 관리하지 않는다. 그래서 프로토타팁 빈은 @PreDestory 같은 종료 메서드를 호출하지 않는다.
      * 대부분 싱글톤 빈으로 문제 해결을 할 수 있어서, 프로토타입 빈을 직접 사용하는 일은 매우 드물다
      * 웹 스코프 : 웹 스코프는 웹 환경에서만 동작하고, 프로토타입과는 다르게 스프링이 해당 스코프의 종료 시점까지 관리한다. 따라서 종료 메서드가 호출된다
      * <img width="500" alt="image" src="https://github.com/chujaeyeong/Inflearn-Spring/assets/123634960/b8bcfbe0-dfa5-4ff8-ab85-64d7c5d52cea">
      
      * Provider (ObjectProvider) 를 활용하면 ObjectProvider.getObject() 를 호출하는 시점까지 request scope 빈의 생성을 지연시킬 수 있다. Controller와 Service 에서 각각 ObjectProvider.getObject() 을 호출해도 같은 HTTP 요청이면 같은 스프링 빈이 반환됨.
      * 프록시 방식을 사용하면, Provider 방식을 활용하는 것 보다 코드를 더 간결하게 구성할 수 있다.
      * <img width="500" alt="image" src="https://github.com/chujaeyeong/Inflearn-Spring/assets/123634960/2c7a4adf-b50c-4305-90e9-f48bd6aea125">
      * CGLIB라는 라이브러리로 내 클래스를 상속 받은 가짜 프록시 객체를 만들어서 주입한다. 가짜 프록시 객체는 요청이 오면, 그때 내부에서 진짜 빈을 요청하는 위임 로직이 들어있다. 가짜 프록시 객체는 원본 클래스를 상속 받아서 만들어졌기 때문에, 이 객체를 사용하는 클라이언트 입장에서는 사실 원본인지 아닌지도 모르게 동일하게 사용할 수 있다. (다형성)
      * 프록시의 장점 : 프록시 객체 적분에 클라이언트는 마치 싱글톤 빈을 사용하듯이 편리하게 request scope를 사용할 수 있다. 진짜 객체 조회를 꼭 필요한 시점까지 지연처리하는것이 가능하다. 또한, 꼭 웹 스코프가 아니어도 프록시는 사용할 수 있다.
      * 프록시 사용의 주의점 : 프록시는 마치 싱글톤을 사용하는 것 같지만, 다르게 동작하기 때문에 결국 사용에 주의가 필요함. 무분별한 프록시 스코프 사용은 유지보수에 어려움을 줄 수 있다. 


</details>

<details>
    
  <summary>📖 수강 후기</summary>
  
  * 객체 지향 설계를 기본으로, 스프링의 원리와 스프링이 제공하는 핵심 기능을 학습할 수 있었다.
  * 싱글톤 컨테이너나 의존성 주입같이 어렴풋이 알고 있던 핵심 원리는 완전하게 익혔고, 빈 생명주기나 콜백, 스코프에 대해서는 이번에 처음 배웠는데, 이런 실무에서 자주 사용하는 기능까지 깊이 있는 학습하여 스프링의 전체 이해도를 높일 수 있었다. 😎 
      
      
</details>

#

#### `03. 모든 개발자를 위한 HTTP 웹 기본지식`

<details>
  
  <summary>✏️ 학습 내용 정리</summary>

  * ##### `1) 인터넷 네트워크`
    * 클라이언트와 서버는 각 IP 주소를 가진다. IP는 인터넷 프로토콜 역할을 함. (지정한 IP 주소에 패킷 이라는 통신 단위로 데이터 전달)
    * 클라이언트는 서버에세 전송하고자 하는 데이터를 출발지 IP, 목적지 IP 등의 정보로 구성된 IP 패킷으로 한 번 감싸서 서버로 전달하는 형식
    * IP 프로토콜의 한계 : 비연결성, 비신뢰성, 프로그램 구분
    
    <br>
    
    * TCP, UDP는 인터넷 프로토콜 스택의 4계층에서 전송계층 을 담당
    * <img width="500" alt="image" src="https://github.com/chujaeyeong/Inflearn-Spring/assets/123634960/11b5ab02-2f82-48bb-bf14-ee71d5fdd623">
    * 서버에 전송하고자 하는 데이터와 IP 패킷 사이에 TCP 세그먼트를 추가해서 전달 (출발지 PORT, 목적지 PORT, 전송 제어, 순서, 검증 정보 등)
    * TCP(전송 제어 프로토콜, Transmission Control Protocol) 특징 : 연결 지향 (TCP 3 way handshake, 가상연결), 데이터 전달 보증, 순서 보장, 신뢰할 수 있는 프로토콜 ➡️ 현재는 대부분 TCP를 사용
    * UDP(사용자 데이터그램 프로토콜, User Datagram Protocol) 특징 : 하얀 도화지에 비유(기능이 거의 없다는 뜻), 연결 지향 X, 데이터 전달 보증 X, 순서 보장 X, 데이터 전달 및 순서가 보장되지 않지만, 단순하고 빠름 ➡️ IP와 거의 같고, PORT, 체크섬 정도만 추가, 애플리케이션에서 추가 작업이 필요함.
    
    <br>
    
    * PORT의 종류 (0 ~ 65535 : 할당 가능 / 0 ~ 1023 : 잘 알려진 포트, 사용하지 않는 것이 좋음)
      * FTP : 20, 21
      * TELNET : 23
      * HTTP : 80
      * HTTPS : 443
    
    <br>
    
    * DNS (도메인 네임 시스템, Domain Name System) : 도메인 명을 IP 주소로 변환한 전화번호부 같은 역할
  
  <br>
  
  * ##### `2) URI와 웹 브라우저 요청 흐름`
    * <img width="500" alt="image" src="https://github.com/chujaeyeong/Inflearn-Spring/assets/123634960/ac17136f-f5e0-4304-95d6-00096fb49910">
    * <img width="500" alt="image" src="https://github.com/chujaeyeong/Inflearn-Spring/assets/123634960/6415b7fc-1620-4406-8ca2-d504126f7d22">
    * URI와 URL은 같은 의미라고 보면 되고, URN은 리소스에 이름을 부여한 것 (위치는 변할 수 있지만, 이름은 변하지 않는다)
    * URL의 전체 문법 구성 : scheme://[userinfo@]host[:port][/path][?query][#fragment]
    
    <br>

    * 웹 브라우저 요청 흐름 - HTTP 메시지 전송
    * <img width="500" alt="image" src="https://github.com/chujaeyeong/Inflearn-Spring/assets/123634960/162554b4-d99a-4d9e-aa9c-7d7cc9361833">
    * 서버에 HTTP 메시지가 도착하면, 서버는 HTTP 응답 메시지를 다시 클라이언트한테 보내준다

  <br>

  * ##### `3) HTTP`
    * HTTP 메시지에 모든 것을 담아서 전송 : HTML, TEXT / image, 음성, 영상, 파일 / JSON, XML (API)
    * HTTP 메시지에는 거의 모든 형태의 데이터 전송 가능, 서버간에 데이터를 주고 받을 때도 대부분 HTTP를 사용
    * HTTP/1.1 버전이 가장 많이 사용됨, HTTP/1.1, HTTP/2 까지 TCP, HTTTP/3는 UDP
    * HTTP의 특징
      * 클라이언트 서버 구조 : Reauest Response 구조, 클라이언트는 서버에 요청을 보내고, 서버의 응답을 대기함. 서버가 요청에 대한 결과를 만들어서 클라이언트에게 응답
      * 무상태 프로토콜 (Stateless) : 서버가 클라이언트 상태를 보존하지 않는다. (장점 : 응답 서버(ex 점원)을 쉽게 바꿀 수 있어서 서버 확장성이 높고 (수평확장, 스케일아웃), 무한한 서버 증설이 가능 / 단점 : 클라이언트가 추가 데이터를 전송해야됨) <-> 상태 유지 (Stateful) : 중간에 서버가 바뀌면 상태 정보가 서버간 공유가 안되기 때문에 정보를 미리 저장해야된다. 일반적으로 브라우저 쿠키와 서버 세션 등을 사용해서 상태 유지, 상태 유지는 최소한만 사용하는것이 좋다. 
      * 비연결성(connectionless) : HTTP는 기본이 연결을 유지하지 않는 모델. 비연결성으로 서버 자원을 매우 효율적으로 사용할 수 있음. but TCP/IP 연결을 새로 맺어야하고, 그래서 3 way handshake 시간이 추가되며, 웹 브라우저로 사이트를 요청하면 HTML, 자바스크립트, css, 추가 이미지 등 수많은 자원이 함께 다운로드되는데, 이런 한계는 HTTP 지속 연결(Persistent Connections)로 문제 해결, HTTP/2와 HTTP/3에서 더 많은 최적화가 진행됨
      * HTTP 메시지
        * HTTP 메시지의 구성
        * <img width="500" alt="image" src="https://github.com/chujaeyeong/Inflearn-Spring/assets/123634960/987bb943-1e58-4d24-9b9a-42c456ec4f3f">
      * 단순함, 확장 가능

	<br>
	
  * ##### `4) HTTP 메서드의 종류` 
    * GET : 리소스 조회, 서버에 전달하고 싶은 데이터는 query(쿼리 파라미터, 쿼리 스프링)을 통해서 전달, ‼️ 메시지 바디를 사용해서 데이터를 전달할 수 있지만, 지원하지 않는 곳이 많아서 권장하지 않음. 
		* POST : 요청 데이터를 처리, 메시지 바디를 통해 서버로 요청 데이터를 전달함. 서버는 요청 데이터를 처리하고, 메시지 바디를 통해 들어온 데이터를 처리하는 모든 기능을 수행. 주로 전달된 데이터로 신규 리소스 등록, 프로세스 처리에 사용 ➡️ 다른 메서드로 처리하기 애매한 경우 (ex. JSON으로 조회 데이터를 넘겨야 하는데, GET 메서드를 사용하기 어려운 경우) POST 메서드를 사용하면 된다. 
		* PUT : 리소스를 대체, 해당 리소스가 없으면 생성 (덮어쓰기 라고 생각하면 편함) ‼️ 클라이언트가 리소스를 식별함. 클라이언트가 리소스 위치를 알고 URI를 지정하는 것이 POST 메서드와의 차이점. 
		* PATCH : 리소스 부분 변경, PUT은 이전 리소스를 아예 삭제하고 갱신한다면, PATCH는 데이터가 변경된 일부분만 갱신함. 
		* DELETE : 리소스 삭제
		* <img width="500" alt="image" src="https://github.com/chujaeyeong/Inflearn-Spring/assets/123634960/1b842a80-569e-42d7-9fa1-78110a8a562d">

	<br>

  * ##### `5) HTTP 메서드의 활용`
    * HTML Form 전송은 GET, POST 메서드만 지원해서 PUT, PATCH, DELETE 메서드의 기능이 필요하다면 GET과 POST를 적절하게 활용하는 것이 필요
		* Content-Type : multipart/form-data, 파일 업로드 같은 바이너리 데이터 전송 시 사용, 다른 종류의 여러 파일과 폼의 내용 함께 전송이 가능함
		* HTTP API 설계 예시
			* HTTP API 컬렉션 : POST 기반 등록 ex) 회원 관리 API 제공, 서버가 리소스 URI 결정
			* HTTP API 스토어 : PUT 기반 등록 ex) 정적 컨텐츠 관리, 원격 파일 관리, 클라이언트가 리소스 URI 결정
			* HTTP FORM 사용 : 웹페이지 회원 관리, 순수 HTML + HTML Form 사용 (GET, POST만 지원)
		
	<br>
 
  * ##### `6) HTTP 상태코드`
    * 상태코드 란? : 클라이언트가 보낸 요청의 처리 상태를 응답에서 알려주는 기능
		* 1xx (Informational) : 요청이 수신되어 처리 중 (거의 사용하지 않음)
		* 2xx (Successful) : 요청 정상 처리
			* 200 OK : 요청 성공
			* 201 Created : 요청이 성공해서 새로운 리소스가 생성됨
			* 202 Accepted : 요청이 접수되었으나 처리가 완료되지 않았음
			* 204 No Content : 서버가 요청을 성공적으로 수행했지만, 응답 페이로드 본문에 보낼 데이터가 없음. 
		* 3xx (Redirection) : 요청을 완료하려면 추가 행동이 필요 (3xx 응답 결과에 Location 헤더가 있으면, Location 위치로 자동 이동 ➡️ 리다이렉트)
			* **영구 리다이렉션** 301 Moved Permanently : 리다이렉트시 요청 메서드가 GET 으로 변하고, 본문이 제거될 수 있음 (MAY)
			* **영구 리다이렉션** 308 Permanent Redirect : 301과 기능은 같은데, 리다이렉트시 요청 메서드와 본문 유지 (처음 POST를 보내면, 리다이렉트도 POST 유지)
			* **일시적인 리다이렉션** 302 Found : 리다이렉트시 요청 메서드가 GET으로 변하고, 본문이 제거될 수 있음 (MAY)
			* **일시적인 리다이렉션** 307 Temporary Redirect : 302와 기능은 같은데, 리다이렉트시 요청 메서드와 본문 유지 (요청 메서드를 변경하면 안된다, MUST NOT)
			* 303 See Other : 302와 기능은 같은데, 리다이렉트시 요청 메서드가 GET으로 변경됨
			* 304 Not Modified : 캐시를 목적으로 사용, 304 응답은 응답에 메시지 바디를 포함하면 안된다 (로컬 캐시를 사용해야 하므로), 조건부 GET, HEAD 요청시 사용함. 
			* ➡️ 307, 303 사용을 권장하지만 현실적으로 이미 많은 애플리케이션 라이브러리들이 302를 기본값으로 사용함
			* ➡️ 자동 리다이렉션시 GET으로 변해도 되면 그냥 302를 사용해도 큰 문제는 없음 
		* 4xx (Client Error) : 클라이언트 오류, 잘못된 문법 등으로 서버가 요청을 수행할 수 없음 (오류의 원인이 클라이언트에 있다)
			* 400 Bad Request : 클라이언트가 잘못된 요청을 해서 서버가 요청을 처리할 수 없음, 요청 구문, 메시지 등등 오류, 클라이언트는 요청 내용을 다시 검토하고, 보내야함
			* 401 Unauthorized : 클라이언트가 해당 리소스에 대한 인증이 필요함, 401 오류 발생 시 응답에 WWW-Authenticate 헤더와 함께 인증 방법을 설명해야함
			* 403 Forbidden : 서버가 요청을 이해했지만 승인을 거부함, 주로 인증 자격 증명은 있지만, 접근 권한이 불충분한 경우에 발생
			* 404 Not Found : 요청 리소스가 서버에 없어서 찾을 수 없음, 또는 클라이언트가 권한이 부족한 리소스에 접근할 때 해당 리소스를 숨기고 싶을 때
		* 5xx (Server Error) : 서버 오류, 서버가 정상 요청을 처리하지 못함, 서버에 문제가 있기 때문에 재시도 하면 성공할 수도 있음
			* 500 Internal Server Error : 서버 문제로 오류 발생, 애매하면 500 오류
			* 503 Service Unavailable : 서버가 일시적인 과부화 또는 예정된 작업으로 잠시 요청을 처리할 수 없음. Retry-After 헤더 필드로 얼마 뒤에 복구되는지 보낼 수도 있음
     
	<br>
 
  * ##### `7) HTTP 헤더 1`
    * HTTP 헤더 란? : HTTP 전송에 필요한 모든 부가정보를 담고 있음 (ex. 메시지 바디의 내용, 메시지 바디의 크기, 압축, 인증, 요청 클라이언트, 서버 정보, 캐시 관리 정보 등등 ...)
    * HTTP 표준 헤더는 너무 많아서 쓰기 전 서치 필요, 필요시 임의의 헤더 추가 가능
    * ‼️ HTTP 표준이 1999년 RFC2616 에서 2014년 RFC723x 로 변경되서 쓰기 전에 표준이 RFC723x 맞는지 확인 필수
    * RFC723x 의 변화
    	* 엔티티(Entity) ➡️ 표현(Representation = representation Metadata + Representation Data)

    * HTTP message body의 구성
    	* <img width="500" alt="image" src="https://github.com/chujaeyeong/Inflearn-Spring/assets/123634960/b59cedb1-7f0e-4362-afa5-a3bcad131780">

    	* Content-Type : 표현 데이터의 형식, 미디어 타입, 문자 인코딩 등의 정보로 구성
     	* Content-Encoding : 표현 데이터의 압축 방식, 표현 데이터를 압축하기 위해 사용함, 데이터를 전달하는 곳에서 압축 후 인코딩 헤더 추가, 데이터를 읽는 쪽에서 인코딩 헤더의 정보로 압축 해제
      * Content-Language : 표현 데이터의 자연 언어, 표현 데이터의 자연 언어를 표현함
      * Content-Length : 표현 데이터의 길이, 바이트 단위, Transfer-Encoding(전송 코딩)을 사용하면 Content-Length를 사용하면 안 됨
      * 표현 헤더는 전송, 응답 둘 다 사용
      
    * 협상 (콘텐츠 네고시에이션) : 클라이언트가 선호하는 표현 요청
    	* Accept : 클라이언트가 선호하는 미디어 타입 전달
     	* Accept-Charset : 클라이언트가 선호하는 문자 인코딩
      * Accept-Encoding : 클라이언트가 선호하는 압축 인코딩
      * Accept-Language : 클라이언트가 선호하는 자연 언어
      * 협상 헤더는 요청시에만 사용
      * 협상 우선순위 : 0~1 중에서 클 수록 높은 우선순위 / 구체적인 것을 우선 / 구체적인 것을 기준으로 미디어 타입을 맞춤
      
    * HTTP 헤더의 전송 방식 : Transfer-Encoding / Range, Content-Range, 단순 전송, 압축 전송, 분할 전송, 범위 전송
    	* HTTP 헤더의 일반 정보
     	* From : 유저 에이전트의 이메일 정보, 일반적으로는 잘 사용하지 않고 검색 엔진 같은 곳에서 주로 사용하며, 요청에서 사용함
      * Referer : 이전 웹 페이지 주소, A -> B로 이동하는 경우 B를 요청할 때 Referer: A 를 포함해서 요청, Referer를 사용해서 유입 경로 분석 가능, 요청에서 사용함
      * User-Agent : 유저 에이전트 애플리케이션 정보, 어떤 종류의 브라우저에서 장애가 발생하는지 파악 가능, 요청에서 사용함
      * Server : 요청을 처리하는 오리진 서버의 소프트웨어 정보, 응답에서 사용함
      * Date : 메시지가 발생한 날짜와 시간, 응답에서 사용함

    * HTTP 헤더의 특별한 정보
    	* Host : 요청한 호스트 정보(도메인), 요청에서 사용함 (필수), 하나의 서버가 여러 도메인을 처리해야 할 때나 하나의 IP 주소에 여러 도메인이 적용되어 있을 때 사용
     	* Location : 페이지 리다이렉션, 웹 브라우저는 3xx 응답의 결과에 Location 헤더가 있으면, Location 위치로 자동 이동 (리다이렉트)
      * Allow : 허용 가능한 HTTP 메서드, 405 (Method Not Allowed) 에서 응답에 포함해야함, GET, HEAD, PUT
      * Retry-After : 유저 에이전트가 다음 요청을 하기까지 기다려야 하는 시간, 503 (Service Unavailable) 에서 서비스가 언제까지 불능인지 알려줄 수 있음
      
    * 인증
    	* Authorization : 클라이언트 인증 정보를 서버에 전달
     	* WWW-Authenticate : 리소스 접근시 필요한 인증 방법 정의, 401 Unauthorized 응답과 함께 사용
      
    * 쿠키
    	* Set-Cookie : 서버에서 클라이언트로 쿠키 전달(응답)
     	* Cookie : 클라이언트가 서버에서 받은 쿠키를 저장하고, HTTP 요청시 서버로 전달
      * 쿠키 사용처 : 사용자 로그인 세션 관리, 광고 정보성 트레킹 (요즘 많이 사용)
      * 쿠키 정보는 항상 서버에 전송됨, 쿠키 저장으로 네트워크 트래픽 추가 유발, 최소한의 정보만 사용 (세션 ID, 인증 토큰), 서버에 전송하지 않고, 웹 브라우저 내부에 데이터를 저장하고 싶으면 웹 스토리지 참고
      * ‼️ 보안에 민감한 데이터는 저장하면 안 됨 (주민번호, 신용카드 번호 등등)
      * 쿠키는 생명 주기를 가진다 (만료일이나 초 단위로 지정), 세션 쿠키 (만료 날짜를 생략하면 브라우저 종료시까지만 유지) or 영속 쿠키 (만료 날짜를 입력하면 해당 날짜까지 유지)
      * 쿠키의 명시 : 명시한 문서 기준 도메인 + 서브 도메인 포함
      * 쿠키의 생략 : 현재 문서 기준 도메인만 적용
      * 쿠키의 경로 : path=/home 이면 이 경로를 포함한 하위 경로 페이지만 쿠키 접근, 일반적으로 path=/ 루트로 지정함
      * 쿠키의 보안
      	* Secure : 원래 쿠키는 http, https 를 구분하지 않고 전송, 그런데 Secure를 적용하면 https인 경우에만 전송함
       	* HttpOnly : XSS 공격 방지를 위해 자바스크립트에서는 접근 불가 (document.cookie), HTTP 전송에만 사용
        * SameSite : XSRF 공격 방지를 위해 요청 도메인과 쿠키에 설정된 도메인이 같은 경우만 쿠키 전송
      
	<br>
 
  * ##### `8) HTTP 헤더 2 - 캐시와 조건부 요청`
    * 캐시가 없을 때
    	* 데이터가 변경되지 않아도 계속 네트워크를 통해서 데이터를 다운로드 받아야 함.
     	* 인터넷 네트워크는 매우 느리고 비싸기 때문에 비효율적, 브라우저 로딩 속도가 느림 ➡️ 느린 사용자 경험	

    * 캐시 적용
    	* 캐시를 적용해서 캐시 가능 시간동안 네트워크를 사용하지 않아도 됨.
     	* 비싼 네트워크 사용량을 줄일 수 있어 효율적, 브라우저 로딩 속도가 빨라짐 ➡️ 빠른 사용자 경험	

    * 캐시 시간 초과
    	* 캐시 유효 시간이 초과되면, 서버를 통해 데이터를 다시 조회하고, 캐시를 갱신함
     	* 이때, 다시 네트워크 다운로드가 발생한다.
				* 캐시의 시간이 초과되면 서버에서 기존 데이터를 변경 or 서버에서 기존 데이터를 변경하지 않음 이렇게 2가지 상황이 발생
				* 캐시 유효 시간이 초과해도, 서버의 데이터가 생힌되지 않으면 304 Not Modified + 헤더 메타 정보만 응답 (바디X)
				* 클라이언트는 서버가 보낸 응답 헤더 정보로 캐시의 메타정보를 갱신하고, 클라이언트는 캐시에 저장되어 있는 데이터를 재활용함.
				* 결과적으로 네트워크 다운로드가 발생하지만, 용량이 적은 헤더 정보만 다운로드 하기 때문에 매우 실용적인 해결책

    * 검증 헤더와 조건부 요청
    	* 검증 헤더 란? : 캐시 데이터와 서버 데이터가 같은지 검증하는 데이터, Last-Modified, ETag
     	* 조건부 요청 헤더
      	* 검증 헤더로 조건에 따른 분기
      	* If-Modified-Since : Last-Modified 사용 / 단점 : 1초 미만 단위로 캐시 조정이 불가능, 날짜 기반의 로직을 사용, 데이터를 수정해서 날짜가 다르지만,
					같은 데이터를 수정해서 데이터 결과가 똑같은 경우 또는 서버에서 별도의 캐시 로직을 관리하고 싶은 경우에는 사용이 곤란
      	* If-None-Match : ETag 사용 (캐시용 데이터에 임의의 고유한 버전 이름을 달아두고, 데이터가 변경되면 이 이름을 바꾸어서 변경함 (Hash를 다시 생성),
					단순하게 ETag만 보내서 같으면 유지, 다르면 다시 받기) ➡️ 캐시 제어 로직을 서버에서 완전히 관리, 클라이언트는 단순이 이 값을 서버에 제공함 (클라이언트는 캐시 메커니즘을 모름)
      	* 조건이 만족하면 200 OK
      	* 조건이 만족하지 않으면 304 Not Modified

    * 캐시 제어 헤더
    	* Cache-Control : 캐시 제어하는 지시어
       	* max-age : 캐시 유효 시간, 초 단위
       	* no-cache : 데이터는 캐시해도 되지만, 항상 원(origin) 서버에 검증하고 사용
       	* no-store : 데이터에 민감한 정보가 있으므로 저장하면 안됨 (메모리에서 사용하고 최대한 빨리 삭제)
       	* public : 응답이 public 캐시에 저장되어도 됨
       	* private : 응답이 해당 사용자만을 위한 것임, private 캐시에 저장해야 함 (기본값)
       	* s-maxage : 프록시 캐시에만 적용되는 max-age
       	* Age: 60 (HTTP 헤더) : 오리진 서버에서 응답 후 프록시 캐시 내에 머문 시간(초)
    	* Pragma : 캐시 제어(하위 호환), no-cache, HTTP 1.0 하위 호환
    	* Expires : 캐시 만료일 지정(하위 호환), 캐시 만료일을 정확한 날짜로 지정, HTTP 1.0 부터 사용 but 지금은 더 유연한 Cache-Control: max-age 권장 (Cache-Control: max-age와 함께 사용하면 Expires는 무시)	

    * 캐시 무효화
    	* 확실한 캐시 무효화 응답 ➡️ **Cache-Control**: no-cache, no-store, must-revalidate, Pragma: no-cache
     	* no-cache vs must-revalidate <br>
        <img width="600" alt="image" src="https://github.com/chujaeyeong/Inflearn-Spring/assets/123634960/ef0b9cec-f8a0-4c36-b797-02152d224157"> <br>
				<img width="600" alt="image" src="https://github.com/chujaeyeong/Inflearn-Spring/assets/123634960/f0453be7-5223-46d8-9bd8-0b6f499d7aef"> <br>
				<img width="600" alt="image" src="https://github.com/chujaeyeong/Inflearn-Spring/assets/123634960/cb769407-1247-4b11-8b29-bfdd842f4d8c">


</details> 

<details>
    
  <summary>📖 수강 후기</summary>
  
  * 이전에 프로젝트를 진행하면서 어렴풋이 알고 있었던 HTTP 상태 코드의 의미를 포함해서 HTTP에 대한 전반적인 개념과 활용 방안에 대해 공부할 수 있었다.
  * 특히 어떤 정보를 담아야하고 네트워크 콘솔창에서 어떤 메시지를 읽어야 문제를 해결할 수 있는지 알게 되었다.  
      
</details>

#

#### `04. 스프링 MVC 1편 - 백엔드 웹 개발 핵심 기술`

<details>
  
  <summary>⚽️ 트러블슈팅</summary>
  
  ##### `섹션 2) 서블릿`
  * **Java 8 환경에서 asIterator() 메서드를 사용할 수 없을 때 대안책**
    * 먼저, **asIterator()** 메서드의 쓰임새
      * Enumeration 객체를 Java 8의 스트림(Stream) API와 함께 사용할 수 있도록 변환해주는 도우미 메서드
      * Java 8 이전에는 Enumeration을 이용하여 컬렉션 요소를 순회하는 것이 일반적이었다. 그러나 Java 8부터는 스트림(Stream) API가 도입되었는데, 스트림을 사용하면 컬렉션 데이터를 보다 편리하게 처리할 수 있게됨.
      * asIterator() 메서드는 Java 9부터 추가된 메서드. Enumeration을 스트림으로 변환하기 위한 목적으로 도입되었으며, 스트림을 사용하면 데이터를 처리하는데 있어서 병렬 처리 등의 장점을 활용할 수 있고, 간결하고 가독성 있는 코드를 작성할 수도 있다

    * Java 8 환경에서는 asIterator() 메서드를 사용할 수가 없다. (메서드 조회 자체가 안 됨) 그럼 어떻게 해야되는가... 코드가 살짝 길어지지만 asIterator() 메서드가 추가되기 전부터 쓰인 방식인 Enumeration 을 사용해서 코드를 작성하면 된다
    
    ```java   
      	
      private void printHeaders(HttpServletRequest request) {
        System.out.println("--- Headers - start ---");

        // Java 8 환경에서도 사용할 수 있는 Enumeration 메서드를 활용한 헤더 정보 출력 코드 
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            System.out.println(headerName + ": " + request.getHeader(headerName));
        }

        // Java 9 이후 환경에서 사용할 수 있는 asIterator() 메서드를 활용한 헤더 정보 출력 코드 
        // request.getHeaderNames().asIterator()
        //         .forEachRemaining(headerName -> System.out.println(headerName + ": " + request.getHeader(headerName)));
    
        System.out.println("--- Headers - end ---");
        System.out.println();
      }

    ```

    * 두 코드의 출력 형식은 완전히 똑같다. 그치만 asIterator() 메서드를 활용한 코드가 훨씬 직관적이고 간결한 것을 볼 수 있다
    * ➡️ JPA 강의 들을때부턴 진짜 물러설 수 없을 것이기 때문에... 자바 버전 업그레이드를 진짜 미루지 않고 진행해야 할 것 같다 ㅎ...

</details> 

<details>

  <summary>✏️ 학습 내용 정리</summary>

  ##### `섹션 1) 웹 애플리케이션의 이해`
  * 웹 서버와 웹 애플리케이션의 정의와 차이
    * 웹 서버 : HTTP 기반으로 동작하고, 정적 리소스를 제공한다. (정적 파일, HTML, CSS, JS, 이미지, 영상) ex. NGINX, APACHE
    * <img width="500" alt="image" src="https://github.com/chujaeyeong/Inflearn-Spring/assets/123634960/8fd7b268-5719-429d-a12c-4fb25090f187">
    * 웹 애플리케이션 서버(Web Application Server, WAS) : HTTP 기반으로 동작하는건 웹 서버와 같으나 다양한 서버 내 알고리즘, 비즈니스 로직, DB 조회 등 클라이언트 요청에 따라 동적인 컨텐츠를 제공하는 서버, 프로그램 을 말한다
    * <img width="500" alt="image" src="https://github.com/chujaeyeong/Inflearn-Spring/assets/123634960/33e1dba5-b7fd-4f15-86aa-ef105c203caf">
    * 웹 애플리케이션 서버도 정적 리소스 제공함 + 동적 컨텐트 제공을 포함한다
    * 그럼 WAS 만 쓰면 되지 웹 서버는 왜 쓰는가? : 정적인 콘텐츠는 웹 서버로 빠르게 처리하고, DB나 중요한 로직 수행은 WAS 로 분산시켜 서버 부하를 줄이는 용도로 사용한다
    * 정적 리소스가 많으면 웹 서버를 증설시키고, 애플리케이션 리소스가 많이 사용되면 WAS 를 증설하는 방향으로 효율적인 서버 관리를 진행하면 된다
    * 정적 리소스만 제공하는 웹 서버는 잘 안 죽으나, 애플리케이션 로직이 동작하는 WAS는 잘 죽기 때문에 WAS나 DB 서버에서 문제가 발생하면 웹 서버에서 오류 화면을 제공이 가능하다
  
  <br>
  
  * 서블릿 (Servlet) 의 이해
    * 서블릿(Servlet) : 동적 웹 페이지를 만들 때 사용되는 자바 기반의 웹 애플리케이션 프로그래밍 기술이다. 
    * 서블릿은 웹 요청과 응답의 흐름을 개발자가 메서드 호출만으로 체계적으로 다룰 수 있게 해준다 
    * HTTP 요청 정보를 편리하게 사용할 수 있는 HttpServletRequest / HTTP 응답 정보를 편리하게 제공할 수 있는 HttpServletResponse
    * <img width="500" alt="image" src="https://github.com/chujaeyeong/Inflearn-Spring/assets/123634960/cfde6c43-521b-4c34-ae45-c6a072910e09">
    
      * 1. WAS는 Request (HttpServletRequest), Response (HttpServletResponse) 객체를 새로 만들어서 서블릿 객체를 호출한다
        2. 개발자는 Request 객체에서 HTTP 요청 정보를 편하게 꺼내서 사용한다
        3. 개발자는 Response 객체에서 HTTP 응답 정보를 편리하게 입력할 수 있다
        4. WAS는 Response 객체에 담겨있는 내용으로 HTTP 응답을 생성한다
  
  <br>

  * 서블릿 컨테이너
    * 톰캣터럼 서블릿을 지원하는 WAS 를 서블릿 컨테이너 라고 한다.
    * 서블릿 컨테이너는 서블릿 객체를 생성 ~ 초기화 ~ 호출 ~ 종료 하는 생명주기를 관리하는 역할
    * <img width="500" alt="image" src="https://github.com/chujaeyeong/Inflearn-Spring/assets/123634960/65107162-0090-463a-bd0c-d10f8a4ee321">

    * 서블릿 객체는 싱글톤으로 관리한다
      * 고객의 요청이 올 때마다 계속 객체를 생성하는 것은 비효율적이다 ➡️ 최초 로딩 시점에 서블릿 객체를 미리 만들어두고 재활용 하는 방식 
      * 모든 고객 요청은 동일한 서블릿 객체 인스턴스에 접근
      * Member 와 같은 공유 변수 사용에 유의
      * 서블릿 컨테이너 종료 시 함께 종료
    * JSP도 서블릿으로 변환 되어서 사용, 동시 요청을 위한 멀티 쓰레드 처리 또한 서블릿 컨테이너에서 지원한다

  <br>

  * 쓰레드 (Thread)
    * 쓰레드 : 애플리케이션 코드를 하나하나 순차적으로 실행하는 것 (자바 메인 메서드를 처음 실행할 시 main 이라는 이름의 쓰레드가 실행된다)
    * 쓰레드가 없다면 자바 애플리케이션 실행이 불가능하고, 쓰레드는 한 번에 하나의 코드 라인만 수행하며, 동시 처리가 필요하면 쓰레드를 추가로 생성한다
    * 근데 하나의 쓰레드만 생성해서 쓰고 있는데 다른 연결 처리가 들어오는 경우에는 두 요청 다 쓰레드를 사용 못 하는 상황이 발생, 신규 스레드 생성으로 대처하는 방법이 있음 (요청마다 쓰레드 생성)
    * <img width="500" alt="image" src="https://github.com/chujaeyeong/Inflearn-Spring/assets/123634960/31297c84-0ec8-4729-bd19-1dd4b5425fce">

      * 요청마다 쓰레드 생성 - 장점 : 동시 요청 처리 가능 / CPU, 메모리 리소스가 허용할 때 까지 처리 가능 / 하나의 쓰레드가 지연되도 나머지 쓰레드 정상 동작
      * 요청마다 쓰레드 생성 - 단점 : 쓰레드는 생성 비용이 매우 비싼데 고객 요청 들어올 떄 마다 생성하면 응답 속도 저하의 요인이 됨 / 컨텍스트 스위칭 비용 발생 / 고객 요청 증가로 쓰레드 생성이 많아지면 CPU, 메모리 리소스의 임계점 초과로 서버가 다운 발생

  <br>
  
  * 쓰레드 풀
  * 요청마다 쓰레드 생성의 단점을 보완
  * <img width="500" alt="image" src="https://github.com/chujaeyeong/Inflearn-Spring/assets/123634960/fdc66ef1-84cb-43b9-abeb-ef60bca996a2">

  * 쓰레드 풀 사용 방식
    * 톰캣은 최대 200개의 쓰레드를 쓰레드 풀에 넣어놓고 사용, 관리
    * 쓰레드가 필요한 상황 발생 시, 쓰레드를 생성하는 것이 아닌 쓰레드 풀에서 꺼내서 사용하고 종료 시 쓰레드 풀에 반환
    * 쓰레드가 모두 사용중이고, 쓰레드 풀에도 사용 가능한 쓰레드가 없다면 ➡️ 요청 거절 또는 특정 숫자만큼 대기하도록 설정 가능
  * 쓰레드 풀의 장점
    * 쓰레드가 풀 안에 미리 생성 상태라서, 쓰레드를 생성 및 종료하는 비용 (CPU 사용) 절약 및 응답 시간 속도가 빠름
    * 생성 가능한 쓰레드의 최대치가 있으므로 너무 많은 요청이 들어와도 기존 요청은 안전하게 처리할 수 있다.
  * 쓰레드 풀의 주요 튜닝 포인트는 최대 쓰레드이다. (max thread, 서버 튜닝)
  * 너무 낮게 설정 시 ➡️ 서버 리소스는 여유롭지만, 클라이언트는 금방 응답 지연
  * 너무 높게 설정 시 ➡️ 가용 리소스 (CPU, 메모리) 임계점 초과로 서버 다운 발생
  * 방안 : 클라우드면 일단 서버를 늘리고, 이후에 튜닝 or 클라우드가 아니라면 열심히 튜닝하자
  * 성능 테스트 : 최대한 실제 서비스와 유사하게 성능 테스트 사도 후 쓰레드 풀의 적정 숫자를 찾자 (apache ab, nGrinder)

  <br>

  * WAS의 멀티 쓰레드 지원
    * 멀티 쓰레드에 대한 부분은 WAS가 처리하므로 개발자가 멀티 쓰레드 관련 코드를 신경 쓰지 않아도 된다 (싱글 쓰레드 프로그래밍하듯이 코드 개발)
    * but, 싱글톤 객체 (서블릿, 스프링 빈)은 주의해서 사용하자
  
  <br>
  
  * 서버 사이드 렌더링 (SSR)
    * <img width="500" alt="image" src="https://github.com/chujaeyeong/Inflearn-Spring/assets/123634960/0a0d8fa4-b88f-4046-9d0f-6945259b2a6c">

    * HTML 최종 결과를 서버에서 만들어서 웹 브라우저에 전달
    * 주로 정적인 화면에 사용한다. (JSP, 타임리프)
    * SSR을 사용하더라도, 자바스크립트를 사용해서 화면 일부를 동적으로 변경하는 것은 가능

  <br>

  ##### `섹션 2) 서블릿`
  <details>
    
  <summary>HttpServletRequest 기본 사용법</summary>
  
  ```java

    // Header 모든 정보
    private void printHeaders(HttpServletRequest request) {
      System.out.println("--- Headers - start ---");

      request.getHeaderNames().asIterator()
               .forEachRemaining(headerName -> System.out.println(headerName + ": " + request.getHeader(headerName)));
  
      System.out.println("--- Headers - end ---");
      System.out.println();
    }
    
    // Header 편리한 조회
    private void printHeaderUtils(HttpServletRequest request) {
      System.out.println("--- Header 편의 조회 start ---");
      System.out.println("[Host 편의 조회]");
      System.out.println("request.getServerName() = " + request.getServerName()); //Host 헤더
      System.out.println("request.getServerPort() = " + request.getServerPort()); //Host 헤더

      System.out.println();

      System.out.println("[Accept-Language 편의 조회]");
      request.getLocales().asIterator()
              .forEachRemaining(locale -> System.out.println("locale = " + locale));
      System.out.println("request.getLocale() = " + request.getLocale());

      System.out.println();

      System.out.println("[cookie 편의 조회]");
      if (request.getCookies() != null) {
        for (Cookie cookie : request.getCookies()) {
          System.out.println(cookie.getName() + ": " + cookie.getValue());}
      }

      System.out.println();

      System.out.println("[Content 편의 조회]");
      System.out.println("request.getContentType() = " + request.getContentType());
      System.out.println("request.getContentLength() = " + request.getContentLength());
      System.out.println("request.getCharacterEncoding() = " + request.getCharacterEncoding());
      System.out.println("--- Header 편의 조회 end ---");
      System.out.println();
    }
  
  ```
    
  </details>

  * HTTP 요청 데이터
    * GET - 쿼리 파라미터
      * /url?username=hello&age=20
      * 메시지 바디 없이, URL의 쿼리 파라미터에 데이터를 포함해서 전달 ex) 검색 필터, 페이징 등에서 많이 사용하는 방식
    * POST - HTML Form
      * content-type: application/x-www-form-urlencoded
      * 메시지 바디에 쿼리 파라미터 형식으로 전달 username=hello&age=20 ex) 회원가입, 상품 주문, HTML Form 사용
    * API 메시지 바디 - 단순 텍스트 
      * JSON, XML, TEXT
      * 데이터 형식은 주로 JSON 사용
      * POST, PUT, PATCH
  
  <br>

  <details>
    
  <summary>HttpServletResponse 기본 사용법</summary>
    
  ```java
    
      /**
      * http://localhost:8080/response-header
      *
      */
      @WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
      public class ResponseHeaderServlet extends HttpServlet {
          @Override
          protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
              //[status-line]
              response.setStatus(HttpServletResponse.SC_OK); //200
              
              //[response-headers]
              response.setHeader("Content-Type", "text/plain;charset=utf-8");
              response.setHeader("Cache-Control", "no-cache, no-store, mustrevalidate");
              response.setHeader("Pragma", "no-cache");
              response.setHeader("my-header","hello");

              //[Header 편의 메서드]
              content(response);
              cookie(response);
              redirect(response);

              //[message body]
              PrintWriter writer = response.getWriter();
              writer.println("ok");
          }
      }

      // Content 편의 메서드 
      private void content(HttpServletResponse response) {
          //Content-Type: text/plain;charset=utf-8
          //Content-Length: 2
          //response.setHeader("Content-Type", "text/plain;charset=utf-8");
          response.setContentType("text/plain");
          response.setCharacterEncoding("utf-8");
          //response.setContentLength(2); //(생략시 자동 생성)
      }

      // Cookie 편의 메서드 
      private void cookie(HttpServletResponse response) {
          //Set-Cookie: myCookie=good; Max-Age=600;
          //response.setHeader("Set-Cookie", "myCookie=good; Max-Age=600");
          Cookie cookie = new Cookie("myCookie", "good");
          cookie.setMaxAge(600); //600초
          response.addCookie(cookie);
      }

      // redirect 편의 메서드
      private void redirect(HttpServletResponse response) throws IOException {
          //Status Code 302
          //Location: /basic/hello-form.html
          //response.setStatus(HttpServletResponse.SC_FOUND); //302
          //response.setHeader("Location", "/basic/hello-form.html");
          response.sendRedirect("/basic/hello-form.html");
      }
    
  ```
      
  </details>

  * HTTP 응답 데이터
    * 단순 텍스트 응답 (write.print("ok);)
    * HTML 응답
    * HTML API - Message Body JSON 응답 

  
  <br>
  
  ##### `섹션 3) 서블릿, JSP, MVC 패턴`
  * MVC 패턴의 개요
    * 너무 많은 역할 <br>
      하나의 서블릿이나 JSP 만으로 비즈니스 로직과 뷰 렌더링까지 모두 처리하게되면, 너무 많은 역할을 하게 되고 ➡️ JSP에 오만 코드가 다 들어있어서 유지보수가 힘들어짐 <br>
      ex. 비즈니스 로직을 호출하는 부분에 변경이 발생해도 JSP코드를 뜯어야되고, UI를 변경할 일이 있어도 비즈니스 코드가 함께 들어있는 JSP 코드를 뜯어야되는 골때리는 상황이 발생함 <br>
      JSP코드에 너무 많은 역할이 들어있으면 안 된다.

    * 변경된 라이프 사이클 <br>
      진짜 문제는, **UI와 비즈니스 담당 로직 사이 변경 라이프 사이클이 다르다는 점이다** <br>
      그래서 서로 변경에 영향을 주지 않는 두 코드는 분리하는게 유지보수에 좋다. (물론 UI가 많이 바뀌면 비즈니스 로직도 함께 변경되는 경우도 있다)

    * 기능 특화 <br>
      특히 JSP 같은 뷰 템플릿 화면을 렌더링 하는데 최적화 되어 있기 때문에, 이 부분의 업무만 담당하게 하는 것이 가장 효과적이다.

    * MVC 패턴 (Model View Controller)
      * MVC 패턴은 지금까지 학습한 것 처럼 하나의 서블릿이나, JSP로 처리하던 것을 컨트롤러(Controller) 와 뷰(View) 라는 영역으로 서로 역할을 나눈 것을 말한다. (웹 애플리케이션은 보통 이 MVC 패턴을 사용함)
      * 컨트롤러 : HTTP 요청을 받아서 파라미터를 검증하고, 비즈니스 로직을 실행한다. 그리고 뷰에 전달할 결과 데이터를 조회해서 모델에 담는다.
      * 모델 : 뷰에 출력할 데이터를 담아둔다. 뷰가 필요한 데이터를 모두 모델에 담아서 전달해주는 덕분에 뷰는 비즈니스 로직이나 데이터 접은을 몰라도 되고, 화면을 렌더링 하는 일에 집중할 수 있다.
      * 뷰 : 모델에 담겨있는 데이터를 사용해서 화면을 그리는 일에 집중한다. 여기서는 HTML을 생성하는 부분을 말한다.
      * <img width="500" alt="image" src="https://github.com/chujaeyeong/Inflearn-Spring/assets/123634960/911454b6-8f37-420c-96e6-dc209149e16f">
      * <img width="500" alt="image" src="https://github.com/chujaeyeong/Inflearn-Spring/assets/123634960/a2cbb127-f02f-4b7a-a14b-5d9b174578ac">
      * <img width="500" alt="image" src="https://github.com/chujaeyeong/Inflearn-Spring/assets/123634960/c745c89e-e2e7-4e09-9b6b-83fda05fed32">

      * ‼️ 컨트롤러에 비즈니스 로직을 함께 두면 컨트롤러의 역할이 너무 비대해져서 보통은 비즈니스 로직을 서비스(Service) 계층을 만들어서 처리한다. 그리고 컨트롤러는 비즈니스 로직이 있는 서비스 계층을 호출하는 역할을 담당한다.

    * /WEB-INF
      * 이 경로 안에 JSP가 있으면 외부에서 직접 JSP를 호출할 수 없다. 우리가 기대하는 것은 항상 컨트롤러를 통해서 JSP를 호출하는 것이다

    * redirect vs forward
      * 리다이렉트는 실제 클라이언트(웹 브라우저)에 응답이 나갔다가, 클라이언트가 redirech 경로로 다시 요청한다. 따라서 클라이언트가 인지할 수 있고, URL 경로도 실제로 변경된다. (호출 2번)
      * 반면 포워드는 서버 내부에서 일어나는 호출이기 때문에 클라이언트가 전혀 인지하지 못한다. (호출 1번)

    <br>
    
    * MVC 패턴의 한계
      * 포워드 중복 : View로 이동하는 코드가 항상 중복 호출되어야 한다. 물론 이 부분을 메서드로 공통화해도 되지만, 해당 메서드도 항상 직접 호출해야 한다.
      * ViewPath에 중복 : jsp가 아닌 thymleaf 같은 다른 뷰로 변경해야 한다면 path에 .jsp 가 붙어서 이거때문에 전체 코드를 다 변경해야 한다.
      * 사용하지 않는 코드 : HttpServetRequest 와 HttpServletResponse 를 사용할 수도 있지만, 사용하지 않는 경우도 있는데 항상 넣어야되서 테스트 케이스 작성이 좀 난감해진다.
      * ‼️ 공통 처리가 어렵다 <br>
        기능이 복잡해질수록 컨트롤러에서 콩통으로 처리해야 하는 부분이 점점 더 많이 증가할 것이다. 단순히 공통 기능을 메서드로 뽑으면 될 것 같지만, <br>
        결과적으로 해당 메서드를 항상 호출해야 하고, 실수로 호출하지 않으면 문제가 될 것이다. 그리고 호출 자체도 중복이다.

    * 해결 방법 ➡️ 스프링 MVC의 프론트 컨트롤러 (Front Controller)
      * 위의 문제를 해결하려면 컨트롤러 호출 전에 먼저 공통 기능을 처리해야한다. 소위 **수문장 역할** 을 하는 기능이 필요하다.
      * 프론트 컨트롤러 패턴을 도입하면 이런 문제를 깔끔하게 해결할 수 있다. (입구를 하나로!) 스프링 MVC 의 핵심도 바로 이 프론트 컨트롤러에 있다. 
  
  <br>
  
  ##### `섹션 4) MVC 프레임워크 만들기`
  * 프론트 컨트롤러 도입 및 v1 ~ v5 를 거쳐 점진적으로 프레임워크를 발전시킬 것이다
  * <img width="500" alt="image" src="https://github.com/chujaeyeong/Inflearn-Spring/assets/123634960/e1ebb6a3-981e-406f-9562-270f95ef1711">
  
  * 프론트 컨트롤러 도입 후 <br>
    <img width="500" alt="image" src="https://github.com/chujaeyeong/Inflearn-Spring/assets/123634960/1f8da6b5-9cf2-45e8-b86e-758a925e7e6d">

  * 프론트 컨트롤러 패턴 특징
    * 프론트 컨트롤러 서블릿 하나로 클라이언트의 요청을 받음
    * 프론트 컨트롤러가 요청에 맞는 컨트롤러를 찾아서 호출
    * 입구를 하나로!
    * 공통 처리 기능
    * 프론트 컨트롤러를 제외한 나머지 컨트롤러는 서블릿을 사용하지 않아도 된다
    * ‼️ 스프링 웹 MVC의 핵심이 바로 프론트 컨트롤러, 스프링 웹 MVC의 DispatcherServlet 이 프론트 컨트롤러 패턴으로 구현되어 있음
  
  <br>
  
  * v1 - 프론트 컨트롤러 도입 : 기존 구조를 최대한 유지하면서 프론트 컨트롤러 도입
    * <img width="500" alt="image" src="https://github.com/chujaeyeong/Inflearn-Spring/assets/123634960/0f11c783-85f4-4872-a716-d71ec98c60bd">

    * 프론트 컨트롤러 분석
    * urlPatterns <br>
      urlPatterns = "/front-controller/v1/*" : /front-controller/v1 를 포함한 하위 모든 요청은 이 서블릿에서 받아들인다. <br>
      ex.  /front-controller/v1 , /front-controller/v1/a , /front-controller/v1/a/b
    * controllerMap <br>
      key : 매핑 URL <br>
      value : 호출된 컨트롤러 <br>
    * service() <br>
      먼저 requestURI 를 조회해서 실제 호출할 컨트롤러를 controllerMap 에서 찾는다. 만약 없다면 404(SC_NOT_FOUND) 상태 코드를 반환한다. <br>
      컨트롤러를 찾고 controller.process(request, response); 을 호출해서 해당 컨트롤러를 실행한다.
    * JSP <br>
      JSP는 이전 MVC에서 사용했던 것을 그대로 사용할 것이다.
  
  * v2 - View 분리 : 단순 반복되는 뷰 로직 분리 
    * 모든 컨트롤러에 뷰로 이동하는 부분에 중복이 있고, 깔끔하지 않다. 별도로 뷰를 처리하는 객체를 만들어서 중복 코드를 삭제한다
    * <img width="500" alt="image" src="https://github.com/chujaeyeong/Inflearn-Spring/assets/123634960/9ae06808-d88a-4dfb-b999-12cdbe22ba9a">
  
  * v3 - Model 추가 : 서블릿 종속성 제거 및 뷰 이름 중복 제거 
    * 서블릿 종속 제거 <br>
      컨트롤러 입장에서 HttpServletRequest, HttpServletResponse이 꼭 필요할까? <br>
      요청 파라미터 정보는 자바의 Map으로 대신 넘기도록 하면 지금 구조에서는 컨트롤러가 서블릿 기술을 몰라도 동작할 수 있다. <br>
      그리고 request 객체를 Model로 사용하는 대신에 별도의 Model 객체를 만들어서 반환하면 된다. <br>
      우리가 구현하는 컨트롤러가 서블릿 기술을 전혀 사용하지 않도록 변경해보자. 이렇게 하면 구현 코드도 매우 단순해지고, 테스트 코드 작성이 쉽다. 
    * 뷰 이름 중복 제거 <br>
      컨트롤러에서 지정하는 뷰 이름에 중복이 있는 것을 확인할 수 있다. 컨트롤러는 뷰의 논리 이름을 반환하고, 실제 물리 위치의 이름은 프론트 컨트롤러에서 처리하도록 단순화하자. <br>
      이렇게 해두면 향후 뷰의 폴더 위치가 함께 이동해도 프론트 컨트롤러만 고치면 된다.
    * /WEB-INF/views/new-form.jsp ➡️ new-form <br>
      /WEB-INF/views/save-result.jsp ➡️ save-result <br>
      /WEB-INF/views/members.jsp ➡️ members

    * <img width="500" alt="image" src="https://github.com/chujaeyeong/Inflearn-Spring/assets/123634960/52393a73-6331-429e-9744-b9d6a6c25a2e">

    * ModelView <br>
      지금까지 컨트롤러에서 서블릿에 종속적인 HttpServletRequest를 사용했다. 그리고 Model도 request.setAttribute() 를 통해 데이터를 저장하고 뷰에 전달했다. <br>
      서블릿의 종속성을 제거하기 위해 Model을 직접 만들고, 추가로 View 이름까지 전달하는 객체를 만들어보자. <br>
      (이번 버전에서는 컨트롤러에서 HttpServletRequest를 사용할 수 없다. 따라서 직접 request.setAttribute() 를 호출할 수 도 없다. 따라서 Model이 별도로 필요하다.) <br>
      참고로 ModelView 객체는 다른 버전에서도 사용하므로 패키지를 frontcontroller 에 둔다.

    * 뷰 리졸버 (viewResolver) <br>
      컨트롤러가 반환한 논리 뷰 이름을 실제 물리 뷰 경로로 변경한다. 그리고 실제 물리 경로가 있는 MyView 객체를 반환한다. <br>
      뷰 객체의 render() 는 모델 정보도 함께 받는다. JSP는 request.getAttribute() 로 데이터를 조회하기 때문에, 모델의 데이터를 꺼내서 request.setAttribute() 로 담아둔다. <br>
      JSP로 포워드 해서 JSP를 렌더링 한다.
  
  * v4 - 단순하고 실용적인 컨트롤러 : 구현 입장에서 ModelView를 직접 행성해서 반환하지 않도록 편리한 인터페이스 제공 
    * 앞서 만든 v3 컨트롤러는 서블릿 종속성을 제거하고 뷰 경로의 중복을 제거하는 등, 잘 설계된 컨트롤러이다. <br>
      그런데 실제 컨트톨러 인터페이스를 구현하는 개발자 입장에서 보면, 항상 ModelView 객체를 생성하고 반환해야 하는 부분이 조금은 번거롭다. <br>
      좋은 프레임워크는 아키텍처도 중요하지만, 그와 더불어 실제 개발하는 개발자가 단순하고 편리하게 사용할 수 있어야 한다. **소위 실용성이 있어야 한다.**
    * <img width="500" alt="image" src="https://github.com/chujaeyeong/Inflearn-Spring/assets/123634960/9d70a62a-496f-4583-9ccf-a828d478ea09">
    * 기본적인 구조는 v3와 같으나, 대신 컨트롤러가 ModelView를 반환하지 않고, ViewName만 반환한다.
    * 변경점 정리
      * 기존 구조에서 모델을 파라미터로 넘김
      * 뷰의 논리 이름을 반환
    * ‼️ 프레임워트나 공통 기능이 수고로워야 사용하는 개발자가 편리해진다
  
  * v5 - 유연한 컨트롤러 : 어댑터를 추가해서 프레임워크를 유연하고 확장성 있게 설계 
    * 어댑터 패턴 적용
      만약 어떤 개발자는 ControllerV3 방식으로 개발하고 싶고, 어떤 개발자는 ControllerV4 방식으로 개발하고 싶다면 어떻게 해야할까? <br>
      지금까지 우리가 개발한 프론트 컨트롤러는 한가지 방식의 컨트롤러 인터페이스만 사용할 수 있다. ControllerV3 , ControllerV4 는 완전히 다른 인터페이스이다. 따라서 호환이 불가능하다. <br>
      마치 v3는 110v이고, v4는 220v 전기 콘센트 같은 것이다. <br>
      ➡️ 이럴 때 사용하는 것이 바로 어댑터이다. 어댑터 패턴을 사용해서 프론트 컨트롤러가 다양한 방식의 컨트롤러를 처리할 수 있도록 변경해보자. <br>
    * <img width="500" alt="image" src="https://github.com/chujaeyeong/Inflearn-Spring/assets/123634960/ab3ea19c-27f9-4a40-86ec-a6dd67ab6d69">

    * 핸들러 어댑터 <br>
      중간에 어댑터 역할을 하는 어댑터가 추가되었는데 이름이 핸들러 어댑터이다. 여기서 어댑터 역할을 해주는 덕분에 다양한 종류의 컨트롤러를 호출할 수 있다. <br>
    * 핸들러 <br>
      컨트롤러의 이름을 더 넓은 범위인 핸들러로 변경했다. 그 이유는 이제 어댑터가 있기 때문에 꼭 컨트롤러의 개념 뿐만 아니라 어떠한 것이든 해당하는 종류의 어댑터만 있으면 다 처리할 수 있기 때문이다. <br>

    * 컨트롤러(Controller) ➡️ 핸들러(Handler) <br>
      이전에는 컨트롤러를 직접 매핑해서 사용했다. 그런데 이제는 어댑터를 사용하기 때문에, 컨트롤러 뿐만 아니라 어댑터가 지원하기만 하면, 어떤 것이라도 URL에 매핑해서 사용할 수 있다. <br>
      그래서 이름을 컨트롤러에서 더 넒은 범위의 핸들러로 변경했다. <br>
    * 매핑 정보 <br>
      private final Map<String, Object> handlerMappingMap = new HashMap<>(); <br>
      매핑 정보의 값이 ControllerV3 , ControllerV4 같은 인터페이스에서 아무 값이나 받을 수 있는 Object 로 변경되었다. <br>
    * 어댑터 호출 <br>
      ModelView mv = adapter.handle(request, response, handler); <br>
      어댑터의 handle(request, response, handler) 메서드를 통해 실제 어댑터가 호출된다. 어댑터는 handler(컨트롤러)를 호출하고 그 결과를 어댑터에 맞추어 반환한다. <br>
      ControllerV3HandlerAdapter 의 경우 어댑터의 모양과 컨트롤러의 모양이 유사해서 변환 로직이 단순하다.


  <br>
  
  ##### `섹션 5) 스프링 MVC - 구조 이해`
  * 직접 만든 MVC 프레임워크 구조와 Spring MVC 구조 비교
  * 1. 직접 만든 MVC 프레임워크 구조 <br>
       <img width="500" alt="image" src="https://github.com/chujaeyeong/Inflearn-Spring/assets/123634960/d70633ce-f2ac-494a-9225-da1fc32cab2a">

    2. Spring MVC 구조 <br>
       <img width="500" alt="image" src="https://github.com/chujaeyeong/Inflearn-Spring/assets/123634960/3e85e1d4-13eb-478f-bacd-bfedc953a9cb">

  * 직접 만든 프레임워크 ➡️ 스프링 MVC 비교 <br>
    FrontController ➡️ DispatcherServlet <br>
    handlerMappingMap ➡️ HandlerMapping <br>
    MyHandlerAdapter ➡️ HandlerAdapter <br>
    ModelView ➡️ ModelAndView <br>
    viewResolver ➡️ ViewResolver <br>
    MyView ➡️ View

  * DispatcherServlet 구조 살펴보기
    ```java
    org.springframework.web.servlet.DispatcherServlet
    ```
    스프링 MVC의 프론트 컨트롤러가 바로 디스패처 서블릿(DispatcherServlet)이다. ➡️ 스프링 MVC 의 핵심

    * DispacherServlet 서블릿 등록 <br>
      DispacherServlet 도 부모 클래스에서 HttpServlet 을 상속 받아서 사용하고, 서블릿으로 동작한다. <br>
      DispatcherServlet ➡️ FrameworkServlet ➡️ HttpServletBean ➡️ HttpServlet <br>
      스프링 부트는 DispacherServlet 을 서블릿으로 자동으로 등록하면서 모든 경로( urlPatterns="/" )에 대해서 매핑한다. <br>
      (참고: 더 자세한 경로가 우선순위가 높다. 그래서 기존에 등록한 서블릿도 함께 동작한다.)

    * 요청 흐름
      1. 서블릿이 호출되면 HttpServlet 이 제공하는 serivce() 가 호출된다.
      2. 스프링 MVC는 DispatcherServlet 의 부모인 FrameworkServlet 에서 service() 를 오버라이드 해두었다.
      3. FrameworkServlet.service() 를 시작으로 여러 메서드가 호출되면서
      4. DispacherServlet.doDispatch() 가 호출된다.

    <br>

    <details>
      <summary>📌 DispacherServlet.doDispatch() 코드 보기</summary>
      
      ```java
      
      protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpServletRequest processedRequest = request;
        HandlerExecutionChain mappedHandler = null;
        ModelAndView mv = null;

        // 1. 핸들러 조회
        mappedHandler = getHandler(processedRequest); 
        if (mappedHandler == null) {
            noHandlerFound(processedRequest, response);
            return; 
        }

        //2.핸들러 어댑터 조회-핸들러를 처리할 수 있는 어댑터
    
        HandlerAdapter ha = getHandlerAdapter(mappedHandler.getHandler());
    
        // 3. 핸들러 어댑터 실행 -> 4. 핸들러 어댑터를 통해 핸들러 실행 -> 5. ModelAndView 반환
        mv = ha.handle(processedRequest, response, mappedHandler.getHandler());
    
        processDispatchResult(processedRequest, response, mappedHandler, mv, dispatchException);
	
    }
  
    private void processDispatchResult(HttpServletRequest request, HttpServletResponse response, HandlerExecutionChain mappedHandler, ModelAndView mv, Exception exception) throws Exception {

        // 뷰 렌더링 호출
        render(mv, request, response);
    }
    
    protected void render(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) throws Exception {
        View view;
        String viewName = mv.getViewName(); 

        //6. 뷰 리졸버를 통해서 뷰 찾기, 7.View 반환
        view = resolveViewName(viewName, mv.getModelInternal(), locale, request);

        // 8. 뷰 렌더링
        view.render(mv.getModelInternal(), request, response);
    }
    
    ```
    
    </details>

  * Spring MVC 구조
    * <img width="500" alt="image" src="https://github.com/chujaeyeong/Inflearn-Spring/assets/123634960/380f0eb5-b2bc-41c7-b7ce-2c962df39dad">

    * 동작 순서 
      1. **핸들러 조회** : 핸들러 매핑을 통해 요청 URL에 매핑된 핸들러(컨트롤러)를 조회한다.
      2. **핸들러 어댑터 조회** : 핸들러를 실행할 수 있는 핸들러 어댑터를 조회한다.
      3. **핸들러 어댑터 실행** : 핸들러 어댑터를 실행한다.
      4. **핸들러 실행** : 핸들러 어댑터가 실제 핸들러를 실행한다.
      5. **ModelAndView 반환** : 핸들러 어댑터는 핸들러가 반환하는 정보를 ModelAndView로 변환해서 반환한다.
      6. **viewResolver 호출** : 뷰 리졸버를 찾고 실행한다. (JSP의 경우: InternalResourceViewResolver 가 자동 등록되고, 사용된다.)
      7. **View 반환** : 뷰 리졸버는 뷰의 논리 이름을 물리 이름으로 바꾸고, 렌더링 역할을 담당하는 뷰 객체를 반환한다. (JSP의 경우 InternalResourceView(JstlView) 를 반환하는데, 내부에 forward() 로직이 있다.)
      8. **뷰 렌더링** : 뷰를 통해서 뷰를 렌더링 한다.

    * 주요 인터페이스 목록
      * 핸들러 매핑 : org.springframework.web.servlet.HandlerMapping
      * 핸들러 어댑터 : org.springframework.web.servlet.HandlerAdapter
      * 뷰 리졸버 : org.springframework.web.servlet.ViewResolver
      * 뷰 : org.springframework.web.servlet.View
      
  * Spring MVC 시작하기
    * @RequestMapping : 스프링에서 주로 사용하는 에노테이션 기반의 컨트롤러를 지원하는 핸들러 매핑과 어댑터 (실무에셔 99.9% 사용)
    * RequestMappingHandlerMapping 은 스프링 빈 중에서 @RequestMapping 또는 @Controller 가 클래스 레벨에 붙어 있는 경우에 매핑 정보로 인식한다.
  
  * Spring MVC 실용적인 방식 적용하기
    * Model 파라미터 : save() , members() 를 보면 Model을 파라미터로 받는 것을 확인할 수 있다. 스프링 MVC도 이런 편의 기능을 제공한다.
    * ViewName 직접 반환 : 뷰의 논리 이름을 반환할 수 있다.
    * @RequestParam 사용 <br>
      스프링은 HTTP 요청 파라미터를 @RequestParam 으로 받을 수 있다. <br>
      @RequestParam("username") 은 request.getParameter("username") 와 거의 같은 코드라 생각하면 된다. <br>
      물론 GET 쿼리 파라미터, POST Form 방식을 모두 지원한다. <br>
    * @RequestMapping @GetMapping, @PostMapping <br>
      @RequestMapping 은 URL만 매칭하는 것이 아니라, HTTP Method도 함께 구분할 수 있다.
      ex. URL이 /new-form 이고, HTTP Method가 GET인 경우를 모두 만족하는 매핑을 하려면 다음과 같이 처리하면 된다.

      ```java
        @RequestMapping(value = "/new-form", method = RequestMethod.GET)
      ```
      ➡️ 이것을 @GetMapping , @PostMapping 으로 더 편리하게 사용할 수 있다. (Get, Post, Put, Delete, Patch 모두 애노테이션 사용 가능)

	<br>
 
  ##### `섹션 6) 스프링 MVC - 기본 기능`
  * 요청 매핑
  	* @RestController <br>
   		@Controller 는 반환 값이 String 이면 뷰 이름으로 인식된다. 그래서 뷰를 찾고 뷰가 랜더링 된다. <br>
		 	@RestController 는 반환 값으로 뷰를 찾는 것이 아니라, HTTP 메시지 바디에 바로 입력한다. <br>
			따라서 실행 결과로 ok 메세지를 받을 수 있다. @ResponseBody 와 관련이 있는데, 뒤에서 더 자세히 설명한다.

  	* @RequestMapping("/hello-basic") <br>
   		/hello-basic URL 호출이 오면 이 메서드가 실행되도록 매핑한다. <br>
     	대부분의 속성을 배열[] 로 제공하므로 다중 설정이 가능하다. {"/hello-basic", "/hello-go"}

  	* HTTP 메서드
   		@RequestMapping 에 method 속성으로 HTTP 메서드를 지정하지 않으면 HTTP 메서드와 무관하게 호출된다. <br>
			➡️ 모두 허용 GET, HEAD, POST, PUT, PATCH, DELETE

  	* 최근 HTTP API는 다음과 같이 리소스 경로에 식별자를 넣는 스타일을 선호한다. <br>
	 		/mapping/userA <br>
			/user/1 <br>
	 
  	* @RequestMapping 은 URL 경로를 템플릿화 할 수 있는데, @PathVariable 을 사용하면 매칭 되는 부분을 편리하게 조회할 수 있다.
   	* @PathVariable 의 이름과 파라미터 이름이 같으면 생략할 수 있다.

  * HTTP 요청
  	* HttpServletRequest
   	* HttpServletResponse
    * HttpMethod : HTTP 메서드를 조회한다. org.springframework.http.HttpMethod
    * Locale : Locale 정보를 조회한다.
    * @RequestHeader MultiValueMap<String, String> headerMap : 모든 HTTP 헤더를 MultiValueMap 형식으로 조회한다.
    * @RequestHeader("host") String host : 특정 HTTP 헤더를 조회한다. <br>
			필수 값 여부 속성 : required <br>
			기본 값 속성 : defaultValue <br>
    * @CookieValue(value = "myCookie", required = false) String cookie : 특정 쿠키를 조회한다. <br>
	 		필수 값 여부 속성 : required <br>
			기본 값 속성 : defaultValue <br>

  * HTTP 요청 파라미터 - 쿼리 파라미터, HTML Form
  	* GET - 쿼리 파라미터
   		* /url?username=hello&age=20
     	* 메시지 바디 없이, URL의 쿼리 파라미터에 데이터를 포함해서 전달
      * 예) 검색, 필터, 페이징등에서 많이 사용하는 방식

  	* POST - HTML Form
	  	* content-type: application/x-www-form-urlencoded
    	* 메시지 바디에 쿼리 파리미터 형식으로 전달 username=hello&age=20
     	* 예) 회원 가입, 상품 주문, HTML Form 사용		

  	* HTTP message body에 데이터를 직접 담아서 요청
   		* HTTP API에서 주로 사용, JSON, XML, TEXT
     	* 데이터 형식은 주로 JSON 사용
      * POST, PUT, PATCH






 
 	<br>
 	
  ##### `섹션 7) 스프링 MVC - 스프링 MVC 웹 페이지 만들기`
  
</details>

<details>

  <summary>📖 수강 후기</summary>
  

  
</details>

#

#### `05. 스프링 MVC 2편 - 백엔드 웹 개발 활용 기술`

<details>
  
  <summary>✏️ 학습 내용 정리</summary>

  ##### `섹션 1) 타임리프 - 기본 기능`



  ##### `섹션 2) 타임리프 - 스프링 통합과 폼`



  ##### `섹션 3) 메시지, 국제화`



  ##### `섹션 4) 검증 1 - Validation`



  ##### `섹션 5) 검증2 - Bean Validation`



  ##### `섹션 6) 로그인 처리 1 - 쿠키, 세션`



  ##### `섹션 7) 로그인 처리 2 - 필터, 인터셉터`



  ##### `섹션 8) 예외 처리와 오류 페이지`



  ##### `섹션 9) API 예외처리`



  ##### `섹션 10) 스프링 타입 컨버터`



  ##### `섹션 11) 파일 업로드`
  
  
</details>

<details>
  
  <summary>📖 수강 후기</summary>


  
</details>
