# Inflearn-Spring
### 📚 인프런 - 김영한 스프링 로드맵 강의 공부 기록

#### 📌 개발 환경
* `Java 8` ➡️ `Java 11` ➡️ `Java 17`
* `SpringBoot v2.7.14` ➡️ `SpringBoot v3.2.0`
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
      * 스프링이 지원하는 빈 생명주기 콜백 : 인터페이스(InitializingBean, DisposableBean) / 설정 정보에 초기화 메서드, 종료 메서드 지정 / @PostConstruct, @PreDestroy 어노테이션 지원 (최신 스프링에서 가장 권장하는 방법, but 외부 라이브러리에는 적용하지 못함, 외부 라이브러리 초기화 및 종료가 필요하면 @Bean의 initMethod, destoryMethod를 사용하는걸 권장함)

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
       	* ➡️ 결과적으로 네트워크 다운로드가 발생하지만, 용량이 적은 헤더 정보만 다운로드 하기 때문에 매우 실용적인 해결책

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
      ➡️ 이것을 @GetMapping , @PostMapping 으로 더 편리하게 사용할 수 있다. (Get, Post, Put, Delete, Patch 모두 어노테이션 사용 가능)

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
  
  * HTTP 요청 파라미터  - @RequestParam 
    * 스프링이 제공하는 @RequestParam 을 사용하면 요청 파라미터를 매우 편리하게 사용할 수 있다. 
    * @RequestParam : 파라미터 이름으로 바인딩 
    * @ResponseBody : View 조회를 무시하고, HTTP message body에 직접 해당 내용 입력 
    * @RequestParam 의 name(value) 속성이 파라미터 이름으로 사용 
      * @RequestParam("username") String memberName ➡️ request.getParameter("username")
      * HTTP 파라미터 이름이 변수 이름과 같으면 name 생략 가능, String, int, Integer 등의 단순 타입이면 @RequestParam 에노테이션도 생략 가능 
      * @RequestParam 에노테이션을 생략하면 스프링 내부에서 required = false 를 적용한다. 
    * 파라미터 값이 없는 경우 defaultValue를 사용하면 기본값을 적용할 수 있다. 이 떄는 이미 기본값이 있기 때문에 required 는 의미 없고, 빈 문자의 경우에도 설정한 기본 값이 적용된다 (null을 받을 수 있음)
  
  * HTTP 요청 파라미터 - @ModelAttribute
    * 실제 개발을 하면 요청 파라미터를 받아서 필요한 객체를 만들고, 그 객체에 값을 넣어주어야 한다.
    * 스프링은 이 과정을 완전히 자동화해주는 @ModelAttribute 기능을 제공한다 
      * HelloData 객체 생성 
      * 요청 파라미터 이름으로 HelloData 객체의 프로퍼티를 찾는다. 그리고 해당 프로퍼티의 setter 를 호출해서 파라미터의 값을 입력 (바인딩) 한다. 
      * ex. 파라미터 이름이 username 이면 setUsername() 메서드를 찾아서 호출하면서 값을 입력한다. 
      * 프로퍼티 : 객체에 getUsername(), setUsername() 메서드가 있으면, 이 객체는 username 이라는 프로퍼티를 갑지고 있다. username 프로퍼티 앖을 변경하면 setUsername() 이 호출되고, 조회하면 getUsername() 이 호출된다. 
      * 바인딩 오류 : age = abc 처럼 숫자가 들어가야 할 곳에 문자를 넣으면 BindException 이 발생한다. 검증 처리를 통해 이 오류를 처리할 수 있다. 
    * @ModelAttribute 생략할 수 있다. 그런데 @RequestParam 도 생략할 수 있으니 혼란이 발생할 수 있다. 
    * 스프링은 해당 생력 시 다음과 같은 규칙을 적용한다. 
      * String, int, Integer 같은 단순 타입 = @RequestParam
      * 나머지 = @ModelAttribute (argument resolver 로 지정해둔 타입 외)
  
  * HTTP 요청 메시지 
    * 스프링 MVC 는 다음 파라미터를 지원한다. 
    * InputStream (Reader) : HTTP 요청 메시지 바디의 내용을 직접 조회
    * OutputStream (Writer): HTTP 응답 메시지의 바디에 직접 결과 출력
    * HttpEntity : HTTP header, body 정보를 편리하게 조회 (메시지 바디 정보를 직접 조회, 요청 파라미터를 조회하는 기능과 관계 없음 @RequestParam X, @ModelAttribute X), 응답에서도 사용 가능 (메시지 바디 정보 직접 반환, 헤더 정보 포함 가능, view 조회 X)
    * RequestEntity : HttpMethod, url 정보가 추가, 요청에서 사용
    * ResponseEntity : HTTP 상태 코드 설정 가능, 응답에서 사용, return new ResponseEntity<String>("Hello World", responseHeaders,HttpStatus.CREATED)
    * 참고 : 스프링MVC 내부에서 HTTP 메시지 바디를 읽어서 문자나 객체로 변환해서 전달해주는데, 이때 HTTP 메시지 컨버터( HttpMessageConverter )라는 기능을 사용한다. 이것은 조금 뒤에 HTTP 메시지 컨버터에서 자세히 설명한다. 
    * @RequestBody : @RequestBody 를 사용하면 HTTP 메시지 바디 정보를 편리하게 조회할 수 있다. 참고로 헤더 정보가 필요하다면 HttpEntity 를 사용하거나 @RequestHeader 를 사용하면 된다. 이렇게 메시지 바디를 직접 조회하는 기능은 요청 파라미터를 조회하는 @RequestParam, @ModelAttribute 와는 전혀 관계가 없다.
      * 요청 파라미터 vs HTTP 메시지 바디 
      * 요청 파라미터를 조회하는 기능 : @RequestParam, @ModelAttribute
      * HTTP 메시지 바디를 직접 조회하는 기능 : @RequestBody (@RequestBody 를 사용하면 응답 결과를 HTTP 메시지 바디에 직접 담아서 전달할 수 있다. 물론 이 경우에도 view 를 사용하지 않는다. )
  
  <br>
  
  * HTTP 응답 
    * 스프링 (서버) 에서 응답 데이터를 만드는 방법은 크게 3가지다. 
    * 1. 정적 리소스 : ex. 웹 브라우저에 정적인 HTML, css, js 를 제공할 떄는 **정적 리소스**를 사용한다.
      2. 뷰 템플릿 사용 : ex. 웹 브라우저에 동적인 HTML을 제공할 때는 뷰 템플릿을 사용한다. 
      3. HTTP 메시지 사용 : HTTP API를 제공하는 경우에는 HTML이 아니라 데이터를 전달해야 하므로, HTTP 메시지 바디에 JSON 같은 형식으로 데이터를 실어 보낸다. 
    * HTML 이나 뷰 템플릿을 사용해도 HTTP 응답 메시지 바디에 HTML 데이터가 담겨서 전달된다.
  
  * 요청 매핑 핸들러 어댑터 구조 
    * <img width="500" alt="image" src="https://github.com/chujaeyeong/Inflearn-Spring/assets/123634960/94fc8e62-fb19-4a91-a4a0-a948bacf9ff0">

    * <img width="500" alt="image" src="https://github.com/chujaeyeong/Inflearn-Spring/assets/123634960/5ad78157-86d8-4317-80d1-72f377894e67">

    * 모든 비밀은 에노테이션 기반의 컨트롤러, 그러니까 @RequestMapping 을 처리하는 핸들러 어댑터인 RequestMappingHandlerAdapter(요청 매핑 핸들러 어댑터) 에 있다. 
    * RequestMappingHandlerAdapter 은 ArgumentResolver 를 호출해서 컨트롤러(핸들러)가 필요로 하는 다양한 파라미터의 값(객체) 을 생성한다. 그리고 이렇게 파라미터 값이 모두 준비되면 컨트롤러를 호출하면서 값을 넘겨준다. (정확히는 HandlerMethodArgumentResolver 인데, ArgumentResolver 라고 줄여 부른다)
    *  스프링은 30개가 넘는 ArgumentResolver 를 기본으로 제공한다. 
    *  HandlerMethodReturnValueHandler 를 줄여서 ReturnValueHandler 라 부른다. ArgumentResolver 와 비슷한데, 이것은 응답 값을 변환하고 처리한다. 
    *  컨트롤러에서 String으로 뷰 이름을 반환해도, 동작하는 이유가 바로 ReturnValueHandler 덕분이다.스프링은 10여개가 넘는 ReturnValueHandler 를 지원한다. (ex. ModelAndView , @ResponseBody , HttpEntity , String)
 
 * HTTP 메시지 컨버터 
   * <img width="500" alt="image" src="https://github.com/chujaeyeong/Inflearn-Spring/assets/123634960/e7141aa7-fc05-40e0-afde-552c7efd4915">

   * 요청 : @RequestBody 를 처리하는 ArgumentResolver 가 있고, HttpEntity 를 처리하는 ArgumentResolver 가 있다. 이 ArgumentResolver 들이 HTTP 메시지 컨버터를 사용해서 필요한 객체를 생성하는 것이다.
   * 응답 : @ResponseBody 와 HttpEntity 를 처리하는 ReturnValueHandler 가 있다. 그리고 여기에서 HTTP 메시지 컨버터를 호출해서 응답 결과를 만든다.
   * 스프링 MVC는 @RequestBody @ResponseBody 가 있으면 RequestResponseBodyMethodProcessor (ArgumentResolver), HttpEntity 가 있으면 HttpEntityMethodProcessor (ArgumentResolver)를 사용한다. 
   * 스프링은 다음을 모두 인터페이스로 제공한다. 따라서 필요하면 언제든지 기능을 확장할 수 있다. 
     * HandlerMethodArgumentResolver
     * HandlerMethodReturnValueHandler
     * HttpMessageConverter
   * 스프링이 필요한 대부분의 기능을 제공하기 때문에 실제 기능을 확장할 일이 많지는 않다. 기능 확장은 WebMvcConfigurer 를 상속 받아서 스프링 빈으로 등록하면 된다. 실제 자주 사용하지는 않으니 실제 기능 확장이 필요할 때 WebMvcConfigurer 를 검색해보자.

 	<br>
 	
  ##### `섹션 7) 스프링 MVC - 스프링 MVC 웹 페이지 만들기`
  * 웹페이지 요구사항 분석 
    * 상품 도메인 모델 : 상품 ID / 상품명 / 가격 / 수량 
    * 상품 관리 기능 : 상품 목록 / 상품 상세 / 상품 등록 / 상품 수정 
    * <img width="500" alt="image" src="https://github.com/chujaeyeong/Inflearn-Spring/assets/123634960/285d5456-5c23-452e-aa89-02146bad613a">
  
  * 업무 분배 
    * 디자이너 : 요구사항에 맞도록 디자인하고, 디자인 결과물을 웹 퍼블리셔에게 넘겨준다. 
    * 웹 퍼블리셔 : 디자이너에서 받은 디자인을 기반으로 HTML, CSS 를 만들어 개발자에게 제공한다. 
    * 백엔드 개발자 : 디자이너, 웹 퍼블리셔를 통해서 HTML 화면이 나오기 전까지 시스템을 설계하고, 핵심 비즈니스 모델을 개발한다. 이후 HTML이 나오면 HTML을 뷰 템플릿으로 변환해서 동적으로 화면들, 웹 화면의 흐름을 제어한다. 
    * ※ React, Vue.js 같은 웹 클라이언트 기술을 사용하고, 웹 프론트엔드 개발자가 별도로 있으면, 웹 프론트엔드 개발자가 웹 퍼블리셔 역할까지 포함해서 하는 경우도 있다.웹 클라이언트 기술을 사용하면, 웹 프론트엔드 개발자가 HTML을 동적으로 만드는 역할과 웹 화면의 흐름을 담당한다. 이 경우 백엔드 개발자는 HTML 뷰 템플릿을 직접 만지는 대신에, HTTP API를 통해 웹 클라이언트가 필요로 하는 데이터와 기능을 제공하면 된다.
  
  * 타임리프 (Thymeleaf)
    * 타임리프는 순수 HTML 파일을 웹 브라우저에서 열어도 내용을 확인할 수 있고, 서버를 통해 뷰 템플릿을 거치면 동적으로 변경된 결과를 확인할 수 있다. JSP를 생각해보면, JSP 파일은 웹 브라우저에서 그냥 열면 JSP 소스코드와 HTML이 뒤죽박죽 되어서 정상적인 확인이 불가능하다. 오직 서버를 통해서 JSP를 열어야 한다. <br>
    이렇게 순수 HTML을 그대로 유지하면서 뷰 템플릿도 사용할 수 있는 타임리프의 특징을 네츄럴 템플릿(natural templates)이라 한다.
  
  * PRG (Post/Redirect/Get)
    * 웹 브라우저의 새로 고침은 마지막에 서버에 전송한 데이터를 다시 전송한다. 상품 등록 폼에서 데이터를 입력하고 저장을 선택하면 POST /add + 상품 데이터를 서버로 전송한다. <br> 
    이 상태에서 새로 고침을 또 선택하면 마지막에 전송한 POST /add + 상품 데이터를 서버로 다시 전송하게 된다. 그래서 내용은 같고, ID만 다른 상품 데이터가 계속 쌓이게 된다.
    * <img width="500" alt="image" src="https://github.com/chujaeyeong/Inflearn-Spring/assets/123634960/92666897-9a02-43dd-a13a-6eb9416f7045">

    * 웹 브라우저의 새로 고침은 마지막에 서버에 전송한 데이터를 다시 전송한다. 새로 고침 문제를 해결하려면 상품 저장 후에 뷰 템플릿으로 이동하는 것이 아니라, 상품 상세 화면으로 리다이렉트를 호출해주면 된다. <br>
    웹 브라우저는 리다이렉트의 영향으로 상품 저장 후에 실제 상품 상세 화면으로 다시 이동한다. 따라서 마지막에 호출한 내용이 상품 상세 화면인 GET /items/{id} 가 되는 것이다. 이후 새로고침을 해도 상품 상세 화면으로 이동하게 되므로 새로 고침 문제를 해결할 수 있다. 
    * ➡️ 이런 문제 해결 방식을 **PRG (Post/Redirect/Get)** 라고 한다. 
    * ※ "redirect:/basic/items/" + item.getId() redirect에서 +item.getId() 처럼 URL에 변수를 더해서 사용하는 것은 URL 인코딩이 안되기 때문에 위험하다. 다음에 설명하는 RedirectAttributes 를 사용하자.
  
  * RedirectAttributes
    * 리다이렉트 할 때 간단히 status=true 를 추가해보자. 그리고 뷰 템플릿에서 이 값이 있으면, 저장되었습니다. 라는 메시지를 출력해보자.
    * RedirectAttributes 를 사용하면 URL 인코딩도 해주고, pathVarible, 쿼리 파라미터까지 처리해준다.
      * redirect:/basic/items/{itemId} 
      * pathVariable 바인딩: {itemId} 
      * 나머지는 쿼리 파라미터로 처리: ?status=true 

<br>

  
</details>

<details>

  <summary>📖 수강 후기</summary>

  * 스프링 MVC 를 적용하는 과정을 서블릿부터 자세하게 학습할 수 있었다. 
  * 스프링 프론트 컨트롤러를 도입하는 과정을 controller 코드 업그레이드 과정으로 학습했다. MVC 패턴을 도입해야 되는건 알고 있었지만, 각 기능이 어떤 부분을 담당하는지는 잘 몰랐는데 이번에 MVC 패턴의 핵심 기능에 대해서 자세하게 알 수 있었다 

<br>

</details>

#

#### `05. 스프링 MVC 2편 - 백엔드 웹 개발 활용 기술`

<details>
  
  <summary>✏️ 학습 내용 정리</summary>

  ##### `섹션 1) 타임리프 - 기본 기능`

  * 타임리프 (Thymleaf) 특징 
    * **서버 사이드 HTML 렌더링 (SSR)** <br>
    타임리프는 백엔드 서버에서 HTML을 동적으로 렌더링 하는 용도로 사용된다. 
    * **네추럴 템플릿** <br>
    타임리프는 순수 HTML을 최대한 유지하는 특징이 있다. 타임리프로 작성한 파일은 HTML을 유지하기 때문에, 웹 브라우저에서 파일을 직접 열어도 내용을 확인할 수 있고, 서버를 통해 뷰 템플릿을 거치면 동적으로 변경된 결과를 확인할 수 있다. <br>
    JSP를 포함한 다른 뷰 템플릿들은 해당 파일을 열면 JSP 소스코드와 HTML이 뒤죽박죽 섞여서 웹 브라우저에서 정상적인 HTML 결과를 확인할 수 없다. 오직 서버를 통해 JSP가 렌더링 되고 HTML 응답 결과를 받아야 화면을 확인할 수 있다. <br>
    반면에 타임리프로 작성된 파일은 해당 파일을 그대로 웹 브라우저에서 열어도 정상적인 HTML 결과를 확인할 수 있다. 물론 이 경우 동적으로 결과가 렌더링 되지는 않는다. 하지만 HTML 마크업 결과가 어떻게 되는지 파일만 열어도 바로 확인할 수 있다. <br>
    이렇게 순수 HTML을 그대로 유지하면서 뷰 템플릿도 사용할 수 있는 타임리프의 특징을 **네추럴 템플릿 (natural templates)** 라고 한다. 
    * **스프링 통합 지원** <br>
    타임리프는 스프링과 자연스럽게 통합되고, 스프링의 다양한 기능을 편리하게 사용할 수 있도록 지원한다.
  
  * 타임리프 사용 선언 방법 
    ```html
    <html xmlns:th="http://www.thymeleaf.org"> 
    ```

  <details>
  <summary>타임리프 기본 표현식</summary>

    • 간단한 표현:
      ◦ 변수 표현식: ${...}
      ◦ 선택 변수 표현식: *{...}
      ◦ 메시지 표현식: #{...}
      ◦ 링크 URL 표현식: @{...}
      ◦ 조각 표현식: ~{...}

    • 리터럴
      ◦ 텍스트: 'one text', 'Another one!',…
      ◦ 숫자: 0, 34, 3.0, 12.3,…
      ◦ 불린: true, false
      ◦ 널: null
      ◦ 리터럴 토큰: one, sometext, main,…

    • 문자 연산:
      ◦ 문자 합치기: +
      ◦ 리터럴 대체: |The name is ${name}|

    • 산술 연산:
      ◦ Binary operators: +, -, *, /, %
      ◦ Minus sign (unary operator): -

    • 불린 연산:
      ◦ Binary operators: and, or
      ◦ Boolean negation (unary operator): !, not

    • 비교와 동등:
      ◦ 비교: >, <, >=, <= (gt, lt, ge, le)
      ◦ 동등 연산: ==, != (eq, ne)

    • 조건 연산:
      ◦ If-then: (if) ? (then)
      ◦ If-then-else: (if) ? (then) : (else)
      ◦ Default: (value) ?: (defaultvalue)

    • 특별한 토큰:
      ◦ No-Operation: _

  </details>
  
  <br>

  <br>

  ##### `섹션 2) 타임리프 - 스프링 통합과 폼`

  * 타임리프는 스프링 없이도 동작하지만, 스프링 통합을 위한 다양한 기능을 편리하게 제공한다. <br>
  그리고 이런 부분은 스프링으로 백엔드를 개발하는 개발자 입장에서 타임리프를 선택하는 하나의 이유가 된다. 

  * 스프링 통합으로 추가되는 기능들 
    * 스프링의 SpringEL 문법 통합 
    * ${@myBean.doSomething()} 처럼 스프링 빈 호출 지원 
    * 편리한 폼 관리를 위한 추가 속성 
      * th:object (기능 강화, 폼 커맨드 객체 선택) 
      * th:field, th:errors, th:errorclass 
    * 폼 컴포넌트 기능 
      * checkbox, radio, button, List 등을 편리하게 사용할 수 있는 기능 지원
    * 스프링의 메시지, 국제화 기능의 편리한 통합 
    * 스프링의 검증, 오류 처리 통합 
    * 스프링의 변환 서비스 통합 
  
  * 타임리프 관련 설정용 스프링 빈 자동 등록 방법 (build.gradle 에 추가)
    ```
    implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'
    ```

  * 입력 폼 처리 
    * th:object : 커맨드 객체를 지정한다. 
    * *{...} : 선택 변수 식이라고 한다. th:object 에서 선택한 객체에 접근한다. 
    * th:field : HTML 태그의 id, name, value 속성을 자동으로 처리해준다. 
      ```
      렌더링 전
      <input type="text" th:field="*{itemName}" /> 

      랜더링 후 
      <input type="text" id="itemName" name="itemName" th:value="*{itemName}" />
      ```
      * th:field="*{itemName}" <br>
        * *{itemName} 는 선택 변수 식을 사용했는데, ${item.itemName} 과 같다. 앞서 th:object 로 item 을 선택했기 때문에 선택 변수 식을 적용할 수 있다.
        * th:field 는 id, name, value 속성을 모두 자동으로 만들어준다. 
          * id : th:field 에서 지정한 변수 이름과 같다. id="itemName"
          * name : th:field 에서 지정한 변수 이름과 같다. name="itemName" 
          * value : th:field 에서 지정한 변수의 값을 사용한다. value="" 
        * 참고로 id 속성을 제거해도 th:field가 자동으로 만들어주긴 한다. 

  <br>

  <br>
  
  ##### `섹션 3) 메시지, 국제화`

  * 메시지 
    * 기획자가 화면에 보이는 문구가 마음에 들지 않는다고, '상품명' 이라는 단어를 모두 '상품이름' 으로 고쳐달라고 하면 어떻게 해야할까? 여러 화면에 보이는 상품명, 가격, 수량 등 label에 있는 단어를 변경하려면 그 단어가 있는 화면을 다 찾아다니면서 변경해야한다.
    * 이런 불상사를 막기 위해 메시지 관리용 파일 (message.properties) 을 만들고 각 HTML들은 다음과 같이 해당 데이터를 key 값으로 불러서 사용하는 것을 메시지 라고 한다. <br>
      ```
      message.properties
      item=상품
      item.id=상품 ID
      item.itemName=상품명
      item.price=가격
      item.quantity=수량

      addForm.html
      <label for="itemName" th:text="#{item.itemName}"></label>

      editForm.html
      <label for="itemName" th:text="#{item.itemName}"></label>
      ```
    
  * 국제화
    * 메시지에서 설명한 메시지 관리용 파일 (message.properties) 을 각 나라별로 별도로 관리하면 서비스를 국제화 할 수 있다. <br>
      ```
      messages_en.properties
      item=Item
      item.id=Item ID
      item.itemName=Item Name
      item.price=price
      item.quantity=quantity

      messages_ko.properties
      item=상품
      item.id=상품 ID
      item.itemName=상품명
      item.price=가격
      item.quantity=수량
      ```
    
    * 한국에서 접근한 것인지, 영어 사용 국가에서 접근한 것인지 인식하는 방법은 HTTP accept-language 헤더 값을 사용하거나, 사용자가 직접 언어를 선택하도록 하고 쿠키 등을 사용해서 처리하면 된다. 
  
  * 메시지와 국제화 기능을 직접 구현할 수도 있지만, 스프링은 기본적인 메시지와 국제화 기능을 모두 제공한다. 그리고 타임리프도 스프링이 제공하는 메시지와 국제화 기능을 편리하게 통합해서 제공한다. 

  <br>

  * 스프링 메시지 소스 설정 
    * 메시지 관리 기능을 사용하려면 스프링이 제공하는 MessageSource 를 스프링 빈으로 등록하면 되는데. MessageSource는 인터페이스다. 따라서 구현체인 ResourceBundleMessageSource 를 스프링 빈으로 등록하면 된다. 
    
    <br>

    * 스프링 메시지 소스 설정 직접 등록 방법
      ```java
      @Bean
      public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("messages", "errors");
        messageSource.setDefaultEncoding("utf-8");
        return messageSource;
      }
      ```
      * basenames : 설정 파일의 이름을 지정한다.
      * messages 로 지정하면 messages.properties 파일을 읽어서 사용한다.
      * 추가로 국제화 기능을 적용하려면 messages_en.properties, messages_ko.properties 와 같이 파일명 마지막에 언어 정보를 주면된다. 만약 찾을 수 있는 국제화 파일이 없으면 messages.properties (언어정보가 없는 파일명)를 기본으로 사용한다.
      * 파일의 위치는 /resources/messages.properties 에 두면 된다.
      * 여러 파일을 한번에 지정할 수 있다. 여기서는 messages, errors 둘을 지정했다. 
      * defaultEncoding : 인코딩 정보를 지정한다. utf-8 을 사용하면 된다. 
    
    * 스프링 부트로 자동 등록 방법
      * 스프링 부트를 사용하면 스프링 부트가 MessageSource를 자동으로 스프링 빈으로 등록한다. 
      * application.properties 파일에서 해당 코드를 추가하면 된다. 
        ```
        spring.messages.basename=messages,config.i18n.messages 
        ```
      * 스프링 부트 메시지 소스 기본 값 : spring.messages.basename=messages
      * MessageSource를 스프링 빈으로 등록하지 않고, 스프링 부트와 관련된 별도의 설정을 하지 않으면 message 라는 이름으로 기본 등록된다. <br>
      따라서 messages_en.properties, messages_ko.properties, messages.properties 파일만 등록하면 자동으로 인식된다. 
    
  * 메시지 파일 만들기 
    * ‼️ 주의 파일명은 message 가 아니라 messages 다 (마지막 s 붙이는거 꼭 기억하기)
    * 위치 : /resources/messages.properties

      ```
      messages.properties
      label.item=상품
      label.item.id=상품 ID
      label.item.itemName=상품명
      label.item.price=가격
      label.item.quantity=수량
      page.items=상품 목록
      page.item=상품 상세
      page.addItem=상품 등록
      page.updateItem=상품 수정
      button.save=저장
      button.cancel=취소
      ```
    
  * 타임리프에 메시지 적용 방법 
    * 타임리프의 메시지 표현식 #{...} 을 사용하면 스프링의 메시지를 편리하게 조회할 수 있다. <br>
    예를 들어 상품 이라는 이름을 조회하려면 #{label.item} 이라고 하면 된다. 
      
      ```
      페이지 이름에 적용
      기존 : <h2>상품 등록 폼</h2>
      변경 : <h2 th:text="#{page.addItem}">상품 등록</h2>

      레이블에 적용 
      기존 : <label for="itemName">상품명</label> 
      변경 : <label for="itemName" th:text="#{label.item.itemName}">상품명</label>

      버튼에 적용 
      기존 : <button type="submit">상품 등록</button>
      변경 : <button type="submit" th:text="#{button.save}">저장</button>
      ```

  * 웹 애플리케이션에 국제화 적용하기 
    * messages_en.properties 파일에 영어 메시지를 추가하기 

      ```
      label.item=Item
      label.item.id=Item ID
      label.item.itemName=Item Name
      label.item.price=price
      label.item.quantity=quantity

      page.items=Item List
      page.item=Item Detail
      page.addItem=Item Add
      page.updateItem=Item Update

      button.save=Save
      button.cancel=Cancel
      ```
    * 웹 브라우저의 언어 설정 값을 변경해서 국제화 적용을 확인해보기 (크롬 브라우저 -> 설정 -> 언어, 우선순위 변경) <br>
    웹 브라우저의 언어 설정 값을 변경하면 요청 시 Accept-Language의 값이 변경된다. 
    * Accept-Language 는 클라이언트가 서버에 기대하는 언어 정보를 담아서 요청하는 HTTP 요청 헤더이다. 
    
    <br>
    
    * 스프링의 국제화 메시지 선택 
      * 앞서 MessageSource 테스트에서 보았듯이 메시지 기능은 Locale 정보를 알아야 언어를 선택할 수 있다. 결국 스프링도 Locale 정보를 알아야 언어를 선택할 수 있는데, 스프링은 언어 선택시 기본으로 Accept- Language 헤더의 값을 사용한다.
      * 스프링은 Locale 선택 방식을 변경할 수 있도록 LocaleResolver 라는 인터페이스를 제공하는데, 스프링 부트는 기본으로 Accept-Language 를 활용하는 AcceptHeaderLocaleResolver 를 사용한다.
      * 만약 Locale 선택 방식을 변경하려면 LocaleResolver 의 구현체를 변경해서 쿠키나 세션 기반의 Locale 선택 기능을 사용할 수 있다. 예를 들어서 고객이 직접 Locale 을 선택하도록 하는 것이다. (관련해서 LocaleResolver 를 검색하면 수 많은 예제가 나오니 필요한 분들은 참고하자.)

  <br>

  <br>

  ##### `섹션 4) 검증 1 - Validation`
  
  * 추가된 요구사항 정리
    * 타입 검증 
      * 가격, 수량에 문자가 들어가면 검증 오류 처리 
    
    * 필드 검증 
      * 상품명: 필수, 공백 X
      * 가격 : 1000원 이상, 1백만원 이하 
      * 수량 : 최대 9999 

    * 특정 필드의 범위를 넘어서는 검증 
      * 가격 * 수량의 합은 10,000원 이상 

  <br>

  * 클라이언트 검증과 서버 검증의 차이
    * 클라이언트 검증은 조작할 수 있으므로 보안에 취약하다. 
    * 서버만으로 검증하면, 즉각적인 고객 사용성이 부족해진다. 
    * 둘을 적절히 섞어서 사용하되, 최종적으로 서버 검증은 필수 
    * API 방식을 사용하면 API 스펙을 잘 정의해서 검증 오류를 API 응답 결과에 잘 남겨주어야 함 

<br>

  * 검증 처리 직접 개발 - v1
    * <img width="500" alt="image" src="https://github.com/chujaeyeong/Inflearn-Spring/assets/123634960/abfd4214-965f-44c1-9ae8-746c69a81b53">
    * 1. 사용자가 상품 등록 폼에서 정상 범위의 데이터 입력
    * 2. 서버에서 검증 로직을 통과하고, 상품 저장 
    * 3. 상품 상세 화면으로 리다이렉트 

    <br>

    * <img width="500" alt="image" src="https://github.com/chujaeyeong/Inflearn-Spring/assets/123634960/609d79f9-a232-4763-9983-d4593c1cd3f8">
    * 1. 사용자가 검증 범위를 넘어서는 데이터 입력 
    * 2. 서버 검증 로직 실패 
    * 3. 사용자게에 상품 등록 폼을 다시 보여주고, 어떤 값을 잘못 입력했는지 알림 



    <details>
    <summary>ValidationItemControllerV1</summary>

      ```java
      @PostMapping("/add")
      public String addItem(@ModelAttribute Item item, RedirectAttributes
      redirectAttributes, Model model) {
          //검증 오류 결과를 보관
          Map<String, String> errors = new HashMap<>();
          
          //검증 로직
          if (!StringUtils.hasText(item.getItemName())) {
              errors.put("itemName", "상품 이름은 필수입니다.");
          }
          
          if (item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1000000) {
              errors.put("price", "가격은 1,000 ~ 1,000,000 까지 허용합니다.");
              
          }
          if (item.getQuantity() == null || item.getQuantity() >= 9999) {
              errors.put("quantity", "수량은 최대 9,999 까지 허용합니다.");
          }
          
          //특정 필드가 아닌 복합 룰 검증
          if (item.getPrice() != null && item.getQuantity() != null) {
          int resultPrice = item.getPrice() * item.getQuantity();

              if (resultPrice < 10000) {
                  errors.put("globalError", "가격 * 수량의 합은 10,000원 이상이어야 합니다. 현재 값 = " + resultPrice);
              }
          }
          
          //검증에 실패하면 다시 입력 폼으로
          if (!errors.isEmpty()) {
              model.addAttribute("errors", errors);
              return "validation/v1/addForm";
          }
          
          //성공 로직
          Item savedItem = itemRepository.save(item);
          redirectAttributes.addAttribute("itemId", savedItem.getId());
          redirectAttributes.addAttribute("status", true);
          return "redirect:/validation/v1/items/{itemId}";
      }
      ```

    </details>

    <br>

    * 글로벌 오류 메시지

      ```html
      <div th:if="${errors?.containsKey('globalError')}">
        <p class="field-error" th:text="${errors['globalError']}">전체 오류 메시지</p>
      </div>
      ```

      * 오류메시지는 errors 에 내용이 있을 때만 출력하면 된다. 
      * 타임리프의 th:if 를 사용하면, 조건에 만족할 때만 해당 HTML 태그를 출력 
      * errors?. 는 errors 가 null 일때 NullPointerException 이 발생하는 대신, null을 반환하는 문법 th:if 에서 null 은 실패로 처리되므로 오류 메시지가 출력되지 않음 

    * 필드 오류 메시지

      ```html
      <input type="text" th:classappend="${errors?.containsKey('itemName')} ? 'fielderror' : _" class="form-control">
      ```

      * classappend : 해당 필드에 오류가 있으면 field-error라는 클래스 정보를 더함
      * 값이 없으면 _(No-Operation)을 사용하여 아무 일도 하지 않음 

    <br>

    * 검증 직접 처리 문제점 
      * 뷰 템플릿에서 중복된 코드가 많음 
      * 타입 오류 처리 불가능 <br>
      Item 의 price, quantity 같은 숫자 필드는 타입이 Integer 이므로 문자 타입으로 설정하는 것이 불가능 <br>
      숫자 타입에 문자가 들어오면 오류가 발생하나 이러한 오류는 스프링MVC에서 컨트롤러에 진입하기도 전에 예외가 발생하기 때문에, 컨트롤러가 호출되지도 않고, 400 예외가 발생
      * Item의 price에 문자를 입력하는 것처럼 타입 오류가 발생해도 고객이 입력한 문자를 화면에 남겨야 함 <br>
      만약 컨트롤러가 호출된다고 가정해도 Item의 price는 Integer이므로 문자를 보관할 수가 없음 <br>
      결국 문자는 바인딩이 불가능하므로 고객이 입력한 문자가 사라지게 되고, 고객은 본인이 어떤 내용을 입력해서 오류가 발생했는지 해하기 어려움 <br>
      ➡️ **스프링이 제공하는 검증 오류 처리방법 (BindingResult) 를 사용**

    <br>

  * `BindingResult`
    * 스프링이 제공하는 검증 오류를 보관하는 객체 
    * BindingResult 가 있으면 @ModelAttribute 에 데이터 바인딩 시 오류가 발생해도 컨트롤러 호출 
    * ex. @ModelAttribute 에 바인딩 시 타입 오류가 발생하면? 
      * BindingResult 가 없으면 400 오류가 발생하면서 컨트롤러가 호출되지 않고, 오류 페이지로 이동 
      * BindingResult 가 있으면 오류 정보 (FieldError) 를 BindingResult에 담아서 컨트롤러를 정상 호출 
    * BindingResult 는 검증할 대상 바로 다음에 와야한다. 순서가 중요하다. 예를 들어서, @ModelAttribute Item item, 바로 다음에 BindingResult 가 와야함 
    * BindingResult 는 Model 에 자동으로 포함됨 

    <br>

    * BindingResult 에 검증 오류를 적용하는 3가지 방법 
      1. @ModelAttribute 의 객체에 타입 오류 등으로 바인딩이 실패하는 경우, 스프링이 FieldError 를 생성해서 BindingResult 에 포함 
      2. 개발자가 직접 적용 
      3. Validator 사용 
  
  <br>

  * `BindingResult 와 Errors`
    * org.springframework.validation.Errors
    * org.springframework.validation.BindingResult
    * BindingResult 는 인터페이스고, Errors 인터페이스를 상속 
    * 실제 넘어오는 구현체는 BeanPropertyBindingResult 라는 것인데, 둘 다 구현하고 있으므로 BindingResult 대신에 Erroes 를 사용 가능 
    * Errors 인터페이스는 단순한 오류 저장과 조회 기능을 제공
    * BindingResult는 여기에 더해서 추가적인 기능들을 제공
    * addError()도 BindingResult가 제공
    * 주로 관례상 BindingResult를 많이 사용한다. 


    <details>
    <summary>ValidationItemControllerV2</summary>

      ```java
        @PostMapping("/add")
        public String addItemV1(@ModelAttribute Item item, BindingResult bindingResult,
                              RedirectAttributes redirectAttributes, Model model) {
            // 검증 오류 결과를 보관
            // 검증 로직
            if (!StringUtils.hasText(item.getItemName())) {
                bindingResult.addError(new FieldError("item", "itemName", "상품 이름은 필수입니다."));
            }
            if (item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() >1000000) {
                bindingResult.addError(new FieldError("item", "price", "가격은 1,000 ~ 1,000,000 까지 허용합니다."));
            }
            if (item.getQuantity() == null || item.getQuantity() >= 9999) {
                bindingResult.addError(new FieldError("item", "quantity", "수량은 최대 9,999 까지 허용합니다."));
            }

            // 특정 필드가 아닌 복합 룰 검증
            if (item.getPrice() != null && item.getQuantity() != null) {
                int resultPrice = item.getPrice() * item.getQuantity();
                if (resultPrice < 10000) {
                    bindingResult.addError(new ObjectError("item", "가격 * 수량의 합은 10,000원 이상이어야 합니다. 현재 값 = " + resultPrice));
                }
            }

            // 검증에 실패하면 다시 입력 폼으로
            if (bindingResult.hasErrors()) {
                log.info("bindingResult = {}", bindingResult);
                return "validation/v2/addForm";
            }

            Item savedItem = itemRepository.save(item);
            redirectAttributes.addAttribute("itemId", savedItem.getId());
            redirectAttributes.addAttribute("status", true);
            return "redirect:/validation/v2/items/{itemId}";
        }

        @PostMapping("/add")
        public String addItemV2(@ModelAttribute Item item, BindingResult bindingResult,
                                    RedirectAttributes redirectAttributes, Model model) {
                // 검증 오류 결과를 보관
                // 검증 로직
                if (!StringUtils.hasText(item.getItemName())) {
                    bindingResult.addError(new FieldError("item", "itemName", item.getItemName(),
                            false, null, null, "상품 이름은 필수입니다."));
                }
                if (item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() >1000000) {
                    bindingResult.addError(new FieldError("item", "price", item.getPrice(),
                            false, null, null,"가격은 1,000 ~ 1,000,000 까지 허용합니다."));
                }
                if (item.getQuantity() == null || item.getQuantity() >= 9999) {
                    bindingResult.addError(new FieldError("item", "quantity", item.getQuantity(),
                            false, null, null,"수량은 최대 9,999 까지 허용합니다."));
                }

                // 특정 필드가 아닌 복합 룰 검증
                if (item.getPrice() != null && item.getQuantity() != null) {
                    int resultPrice = item.getPrice() * item.getQuantity();
                    if (resultPrice < 10000) {
                        bindingResult.addError(new ObjectError("item", null, null,
                                "가격 * 수량의 합은 10,000원 이상이어야 합니다. 현재 값 = " + resultPrice));
                    }
                }

                // 검증에 실패하면 다시 입력 폼으로
                if (bindingResult.hasErrors()) {
                    log.info("bindingResult = {}", bindingResult);
                    return "validation/v2/addForm";
                }

                Item savedItem = itemRepository.save(item);
                redirectAttributes.addAttribute("itemId", savedItem.getId());
                redirectAttributes.addAttribute("status", true);
                return "redirect:/validation/v2/items/{itemId}";
            }
      ```

    </details>

    <br>

    * 필드 오류 - FieldError
    ```java
      if (!StringUtils.hasText(item.getItemName())) {
      bindingResult.addError(new FieldError("item", "itemName", "상품 이름은 필수입니다."));
      }
    ```

    ```java
      public FieldError(String objectName, String field, String defaultMessage) {}
    ```

    <br>

    * 글로벌 오류 - ObjectError
    ```java
      bindingResult.addError(new ObjectError("item", "가격 * 수량의 합은 10,000원 이상이어야 합니다. 현재 값 = " + resultPrice));
    ```

    ```java
    public ObjectError(String objectName, String defaultMessage) {}
    ```

    <br>

    * 파라미터 목록 
      * objectName : 오류가 발생한 객체 이름
      * defaultMessage : 오류 기본 메시지
      * field : 오류 필드
      * rejectedValue : 사용자가 입력한 값(거절된 값)
      * bindingFailure : 타입 오류 같은 바인딩 실패인지, 검증 실패인지 구분 값
      * codes : 메시지 코드
      * arguments : 메시지에서 사용하는 인자
      * defaultMessage : 기본 오류 메시지

    <br>

    <details>
    <summary>addForm.html</summary>

      ```hmtl
        <form action="item.html" th:action th:object="${item}" method="post">
            <div th:if="${#fields.hasGlobalErrors()}">
                <p class="field-error" th:each="err : ${#fields.globalErrors()}" th:text="${err}">글로벌 오류 메시지</p>
            </div>
            <div>
                <label for="itemName" th:text="#{label.item.itemName}">상품명</label>
                <input type="text" id="itemName" th:field="*{itemName}"
                      th:errorclass="field-error" class="form-control" placeholder="이름을 입력하세요">
                <div class="field-error" th:errors="*{itemName}">상품명 오류</div>
            </div>
            <div>
                <label for="price" th:text="#{label.item.price}">가격</label>
                <input type="text" id="price" th:field="*{price}"
                      th:errorclass="field-error" class="form-control" placeholder="가격을 입력하세요">
                <div class="field-error" th:errors="*{price}">가격 오류</div>
            </div>
            <div>
                <label for="quantity" th:text="#{label.item.quantity}">수량</label>
                <input type="text" id="quantity" th:field="*{quantity}"
                      th:errorclass="field-error" class="form-control" placeholder="수량을 입력하세요">
                <div class="field-error" th:errors="*{quantity}">수량 오류</div>
            </div>

            <hr class="my-4">

            <div class="row">
                <div class="col">
                    <button class="w-100 btn btn-primary btn-lg" type="submit" th:text="#{button.save}">상품 등록</button>
                </div>
                <div class="col">
                    <button class="w-100 btn btn-secondary btn-lg"
                            onclick="location.href='items.html'"
                            th:onclick="|location.href='@{/validation/v2/items}'|"
                            type="button" th:text="#{button.cancel}">취소</button>
                </div>
            </div>

        </form>
      ```
    </details>

    <br>

    * 타임리프 스프링 검증 오류 통합 기능 <br>
    타임리프는 스프링의 BindingResult 를 활용해서 편리하게 검증 오류를 표현하는 기능을 제공한다.
      * #fields : #fields 로 BindingResult 가 제공하는 검증 오류에 접근할 수 있다.
      * th:errors : 해당 필드에 오류가 있는 경우에 태그를 출력한다. th:if 의 편의 버전이다.
      * th:errorclass : th:field 에서 지정한 필드에 오류가 있으면 class 정보를 추가한다.

    <br>

    * 필드 오류 처리 
    ```html
      <input type="text" id="itemName" th:field="*{itemName}"
      th:errorclass="field-error" class="form-control" placeholder="이름을
      입력하세요">
      <div class="field-error" th:errors="*{itemName}">
        상품명 오류
      </div>
    ```

    <br>

    * 글로벌 오류 처리 
    ```html
      <div th:if="${#fields.hasGlobalErrors()}">
        <p class="field-error" th:each="err : ${#fields.globalErrors()}" th:text="${err}">전체 오류 메시지</p>
      </div>
    ```

  <br>

  * 오류 발생 시 사용자 입력값 유지 
    * new FieldError("item", "price", item.getPrice(), false, null, null, "가격은 1,000 ~ 1,000,000 까지 허용합니다.")
    * 사용자의 입력 데이터가 컨트롤러의 @ModelAttribute 에 바인딩되는 시점에 오류가 발생하면 모델 객체에 사용자 입력 값을 유지하기 어려움
    * 예를 들어서 가격에 숫자가 아닌 문자가 입력된다면 가격은 Integer 타입이므로 문자를 보관할 수 있는 방법이 없음
    * 오류가 발생한 경우 사용자 입력 값을 보관하는 별도의 방법이 필요
    * FieldError는 오류 발생시 사용자 입력 값을 저장하는 기능을 제공

  <br>

  * 타임리프의 사용자 입력 값 유지
    * th:field="*{price}" 타임리프의 th:field는 매우 똑똑하게 동작하는데, 정상 상황에는 모델 객체의 값을 사용하지만, 오류가 발생하면 FieldError 에서 보관한 값을 사용해서 값을 출력

  <br>

  * 스프링의 바인딩 오류 처리 
    * 타입 오류로 바인딩에 실패하면 스프링은 FieldError를 생성하면서 사용자가 입력한 값을 보관
    * 해당 오류를 BindingResult에 담아서 컨트롤러를 호출한다. 
    * 따라서 타입 오류 같은 바인딩 실패 시에도 사용자의 오류 메시지를 정상 출력

  <br>

  * `오류 코드와 메시지 처리`
    * errors 메시지 파일 생성 - application.properties
      ```
      spring.messages.basename=messages,errors
      ```
    
    * errors.properties
      ```
      required.item.itemName=상품 이름은 필수입니다.
      range.item.price=가격은 {0} ~ {1} 까지 허용합니다.
      max.item.quantity=수량은 최대 {0} 까지 허용합니다.
      totalPriceMin=가격 * 수량의 합은 {0}원 이상이어야 합니다. 현재 값 = {1}
      ```

    * ValidationItemControllerV2 - addItemV3 일부 
      ```java
      //range.item.price=가격은 {0} ~ {1} 까지 허용합니다.
      new FieldError("item", "price", item.getPrice(), false, new String[]
      {"range.item.price"}, new Object[]{1000, 1000000}
      ```

    * codes : required.item.itemName를 사용해서 메시지 코드를 지정
      * 메시지 코드는 하나가 아니라 배열로 여러 값을 전달할 수 있는데, 순서대로 매칭해서 처음 매칭되는 메시지가 사용됨
    * arguments: Object[]{1000, 1000000} 를 사용해서 코드의 {0}, {1} 로 치환할 값을 전달

  <br>

  * `rejectValue(), reject()`
    * 컨트롤러에서 BindingResult 는 검증해야 할 객체인 target 바로 다음에 온다. 
    * 따라서 BindingResult 는 이미 본인이 검증해야 할 객체인 target 을 알고 있다.
    * ➡️ BindingResult가 제공하는 rejectValue(), reject()를 사용하면 FieldError, ObjectError를 직접 생성하지 않아도 됨

      ```java 
        void rejectValue(@Nullable String field, String errorCode, @Nullable Object[] errorArgs, @Nullable String defaultMessage);
        
        void reject(String errorCode, @Nullable Object[] errorArgs, @Nullable String defaultMessage);
      ```

      * field: 오류 필드명
      * errorCode: 오류 코드(messageResolver를 위한 오류 코드)
      * errorArgs: 오류 메시지에서 {0}을 치환하기 위한 값
      * defaultMessage: 오류 메시지를 찾을 수 없을 때 사용하는 기본 메시지
    
    <br>

    * `ValidationUtils`
      * 조건문 대신 사용할 수 있음
      * Empty, 공백 같은 단순한 기능만 제공

    <details>
    <summary>ValidationItemControllerV2 - addItemV4</summary>

      ```java
        //@PostMapping("/add")
        public String addItemV4(@ModelAttribute Item item, BindingResult bindingResult,
                                RedirectAttributes redirectAttributes, Model model) {
            // 검증 오류 결과를 보관
            // 검증 로직
            ValidationUtils.rejectIfEmptyOrWhitespace(bindingResult, "itemName", "required");

        //        if (!StringUtils.hasText(item.getItemName())) {
        //            bindingResult.rejectValue("itemName", "required");
        //        }

            if (item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() >1000000) {
                bindingResult.rejectValue("price", "range", new Object[]{1000, 1000000}, null);
            }
            if (item.getQuantity() == null || item.getQuantity() >= 9999) {
                bindingResult.rejectValue("quantity", "max", new Object[]{9999}, null);
            }

            // 특정 필드가 아닌 복합 룰 검증
            if (item.getPrice() != null && item.getQuantity() != null) {
                int resultPrice = item.getPrice() * item.getQuantity();
                if (resultPrice < 10000) {
                    bindingResult.reject("totalPriceMin", new Object[]{10000, resultPrice}, null);
                }
            }

            // 검증에 실패하면 다시 입력 폼으로
            if (bindingResult.hasErrors()) {
                log.info("bindingResult = {}", bindingResult.toString());
                return "validation/v2/addForm";
            }

            Item savedItem = itemRepository.save(item);
            redirectAttributes.addAttribute("itemId", savedItem.getId());
            redirectAttributes.addAttribute("status", true);
            return "redirect:/validation/v2/items/{itemId}";
        }
      ```

    </details>

  * `MessageCodesResolver 오류 코드 관리`
    * 검증 오류 코드로 메시지 코드들을 생성
    * MessageCodesResolver는 인터페이스이고 DefaultMessageCodesResolver가 기본 구현체
    * 주로 ObjectError, FieldError와 함께 사용 

    <br>

    * DefaultMessageCodesResolver의 기본 메시지 생성 규칙

      ```
        객체 오류의 경우 다음 순서로 2가지 생성
        1. code + "." + object name
        2. code
        예) 오류 코드: required, object name: item
        1. required.item
        2. required

        필드 오류의 경우 다음 순서로 4가지 메시지 코드 생성
        1. code + "." + object name + "." + field
        2. code + "." + field
        3. code + "." + field type
        4. code

        예) 오류 코드: typeMismatch, object name "user", field "age", field type: int
        1. "typeMismatch.user.age"
        2. "typeMismatch.age"
        3. "typeMismatch.int"
        4. "typeMismatch"
      ```

    * DefaultMessageCodesResolver 의 동작 방식 
      * rejectValue(), reject() 는 내부에서 MessageCodesResolver로 메시지 코드들을 생성 
      * MessageCodesResolver를 통해서 생성된 순서대로 오류 코드를 보관 

    <br>

    * 오류 메시지 출력 
      * 타임리프 화면을 렌더링 할 때 th:errors가 실행
      * 만약 오류가 있다면 생성된 오류 메시지 코드를 순서대로 돌아가면서 메시지를 검색, 없으면 디폴트 메시지를 출력

    <br>

    * 오류 코드 관리 전략
      * MessageCodesResolver는 required.item.itemName처럼 구체적인 것을 먼저 만들어주고, required처럼 덜 구체적인 것을 가장 나중에 생성
      * 크게 중요하지 않은 메시지는 범용성 있는 requried 같은 메시지로 끝내고, 정말 중요한 메시지는 꼭 필요할 때 구체적으로 적어서 사용하는 방식이 더 효과적
    
    <br>
    
    * 스프링이 직접 만든 오류 메시지 처리
      * 검증 오류 코드는 다음과 같이 2가지로 나눌 수 있음
        * 개발자가 직접 설정한 오류 코드 → rejectValue()를 직접 호출
        * 스프링이 직접 검증 오류에 추가한 경우 (주로 타입 정보가 맞지 않음)
      * 스프링은 타입 오류가 발생하면 typeMismatch라는 오류 코드를 사용
      * error.properties에 아래와 같은 내용을 추가하면 소스 코드를 수정하지 않고 메시지 처리가 가능

      <br>

      ```
        #required.item.itemName=상품 이름은 필수입니다.
        #range.item.price=가격은 {0} ~ {1} 까지 허용합니다.
        #max.item.quantity=수량은 최대 {0} 까지 허용합니다.
        #totalPriceMin=가격 * 수량의 합은 {0}원 이상이어야 합니다. 현재 값 = {1}

        #==ObjectError==
        #Level1
        totalPriceMin.item=상품의 가격 * 수량의 합은 {0}원 이상이어야 합니다. 현재 값 = {1}

        #Level2 - 생략
        totalPriceMin=전체 가격은 {0}원 이상이어야 합니다. 현재 값 = {1}

        #==FieldError==
        #Level1
        required.item.itemName=상품 이름은 필수입니다.
        range.item.price=가격은 {0} ~ {1} 까지 허용합니다.
        max.item.quantity=수량은 최대 {0} 까지 허용합니다.

        #Level2 - 생략

        #Level3
        required.java.lang.String = 필수 문자입니다.
        required.java.lang.Integer = 필수 숫자입니다.
        min.java.lang.String = {0} 이상의 문자를 입력해주세요.
        min.java.lang.Integer = {0} 이상의 숫자를 입력해주세요.
        range.java.lang.String = {0} ~ {1} 까지의 문자를 입력해주세요.
        range.java.lang.Integer = {0} ~ {1} 까지의 숫자를 입력해주세요.
        max.java.lang.String = {0} 까지의 문자를 허용합니다.
        max.java.lang.Integer = {0} 까지의 숫자를 허용합니다.

        #Level4
        required = 필수 값 입니다.
        min= {0} 이상이어야 합니다.
        range= {0} ~ {1} 범위를 허용합니다.
        max= {0} 까지 허용합니다.

        #추가
        typeMismatch.java.lang.Integer=숫자를 입력해주세요.
        typeMismatch=타입 오류입니다.
      ```

    <br>

  * `Validator 분리`
    * 복잡한 검증 로직을 별도 분리 

    <br>

    * Validator 인터페이스
      ```java
        public interface Validator {
            boolean supports(Class<?> clazz);
            void validate(Object target, Errors errors);
        }
      ```

      * supports() : 해당 검증기를 지원하는 여부 확인
      * validate(Object target, Errors errors) : 검증 대상 객체와 BindingResult

  <br>

  * `@Validated`
    * 이 어노테이션이 붙으면 앞서 WebDataBinder에 등록한 검증기를 찾아서 실행
    * 그런데 여러 검증기를 등록한다면 각 검증기의 supports() 사용하여 구분
    * 아래와 같은 검증기에서는 supports(Item.class)가 호출되고, 결과가 true이므로 ItemValidator의 validate()가 호출

    * ValidationItemControllerV2 - addItemV6
      ```java
        @InitBinder
        public void init(WebDataBinder dataBinder) {
            log.info("init binder {}", dataBinder);
            dataBinder.addValidators(itemValidator);
        }

        //@PostMapping("/add")
        public String addItemV6(@Validated @ModelAttribute Item item, BindingResult bindingResult,
                                RedirectAttributes redirectAttributes, Model model) {

            // 검증에 실패하면 다시 입력 폼으로
            if (bindingResult.hasErrors()) {
                log.info("bindingResult = {}", bindingResult.toString());
                return "validation/v2/addForm";
            }

            Item savedItem = itemRepository.save(item);
            redirectAttributes.addAttribute("itemId", savedItem.getId());
            redirectAttributes.addAttribute("status", true);
            return "redirect:/validation/v2/items/{itemId}";
        }
      ```
    * ItemValidator
      ```java
        @Component
        public class ItemValidator implements Validator {
            @Override
            public boolean supports(Class<?> clazz) {
                return Item.class.isAssignableFrom(clazz);
            }

            @Override
            public void validate(Object target, Errors errors) {
                Item item = (Item) target;

                if (!StringUtils.hasText(item.getItemName())) {
                    errors.rejectValue("itemName", "required");
                }

                if (item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() >1000000) {
                    errors.rejectValue("price", "range", new Object[]{1000, 1000000}, null);
                }
                if (item.getQuantity() == null || item.getQuantity() >= 9999) {
                    errors.rejectValue("quantity", "max", new Object[]{9999}, null);
                }

                // 특정 필드가 아닌 복합 룰 검증
                if (item.getPrice() != null && item.getQuantity() != null) {
                    int resultPrice = item.getPrice() * item.getQuantity();
                    if (resultPrice < 10000) {
                        errors.reject("totalPriceMin", new Object[]{10000, resultPrice}, null);
                    }
                }
            }
        }
      ```

  <br>

  <br>


  ##### `섹션 5) 검증2 - Bean Validation`

  * `Bean Validation - 소개` 
    * 검증 기능을 지금처럼 매번 코드로 작성하는 것은 상당히 번거롭다. <br>
    이런 검증 로직을 모든 프로젝트에 적용할 수 있게 공통화하고, 표준화한 것이 바로 Bean Validation 이다. <br>
    Bean Validation 을 잘 활용하면, 어노테이션 하나로 검증 로직을 매우 편리하게 적용할 수 있다. 

    <br>

    * Bean Validation은 특정한 구현체가 아니라 Bean Validation 2.0(JSR-380)이라는 기술 표준이다.
    * 쉽게 이야기해서 검증 어노테이션과 여러 인터페이스의 모음이다. 마치 JPA가 표준 기술이고 그 구현체로 하이버네이트가 있는 것과 같다.
    * Bean Validation을 구현한 기술중에 일반적으로 사용하는 구현체는 하이버네이트 Validator이다. (이름이 하이버네이트가 붙어서 그렇지 ORM과는 관련이 없다.)

  <br>

  * `Bean Validation - 시작` 
    ```
    // build.gradle
    implementation 'org.springframework.boot:spring-boot-starter-validation'
    ```

    * jakarta.validation-api : Bean Validation 인터페이스
    * hibernate-validator : 구현체 

  <br>

  * `Bean Validation - 스프링 적용`
    ```java
      @Data
      public class Item {
        
          private Long id;
      
          @NotBlank
          private String itemName;
          
          @NotNull
          @Range(min = 1000, max = 1000000)
          private Integer price;
          
          @NotNull
          @Max(9999)
          private Integer quantity;
          
          public Item() {
          
          }
          
          public Item(String itemName, Integer price, Integer quantity) {
              this.itemName = itemName;
              this.price = price;
              this.quantity = quantity;
          }
      }
    ```
  
    * 검증 어노테이션 종류 
      * @NotBlank : 빈값 + 공백만 있는 경우를 허용하지 않는다.
      * @NotNull : null 을 허용하지 않는다.
      * @Range(min = 1000, max = 1000000) : 범위 안의 값이어야 한다.
      * @Max(9999) : 최대 9999까지만 허용한다.

    * ValidationItemControllerV3 - addItem
      ```java
        //@PostMapping("/add")
        public String addItem(@Validated @ModelAttribute Item item, BindingResult bindingResult,
                                RedirectAttributes redirectAttributes, Model model) {

            // 검증에 실패하면 다시 입력 폼으로
            if (bindingResult.hasErrors()) {
                log.info("bindingResult = {}", bindingResult.toString());
                return "validation/v3/addForm";
            }

            Item savedItem = itemRepository.save(item);
            redirectAttributes.addAttribute("itemId", savedItem.getId());
            redirectAttributes.addAttribute("status", true);
            return "redirect:/validation/v3/items/{itemId}";
        }
      ```

    * 검증 순서 
      * 스프링 부트가 spring-boot-starter-validation 라이브러리를 넣으면 자동으로 Bean Validator를 인지하고 스프링에 통합한다.
      * LocalValidatorFactoryBean 을 글로벌 Validator로 등록한다. 이 Validator는 @NotNull 같은 어노테이션을 보고 검증을 수행한다. 
      * 이렇게 글로벌 Validator가 적용되어 있기 때문에, @Valid, @Validated 만 적용하면 된다. 검증 오류가 발생하면, FieldError, ObjectError 를 생성해서 BindingResult 에 담아준다.
      * @ModelAttribute 각각의 필드에 타입 변환 시도를 한다. 
        * 성공하면 다음으로, 
        * 실패하면 typeMismatch 로 FieldError 추가 
      * Validator 적용 

    * 바인딩에 성공한 필드만 Bean Validation 적용
    * BeanValidator는 바인딩에 실패한 필드는 BeanValidation을 적용하지 않는다. 
      * ex) itemName 에 문자 "A" 입력 ➡️ 타입 변환 성공 ➡️ itemName 필드에 BeanValidation 적용
      * ex) price 에 문자 "A" 입력 ➡️ "A"를 숫자 타입 변환 시도 실패 ➡️ typeMismatch FieldError 추가 ➡️ price 필드는 BeanValidation 적용 X

    <br>

  * `Bean Validation - 에러 코드`
    * Bean Validation 을 적용하고 bindingResult 에 등록된 검증 오류 코드가 어노테이션 이름으로 등록된다. 

    <br>

    * @NotBlank
      * NotBlank.item.itemName
      * NotBlank.itemName
      * NotBlank.java.lang.String
      * NotBlank

    * @Range
      * Range.item.price
      * Range.price
      * Range.java.lang.Integer
      * Range

    <br>

    * 메시지 등록 (errors.properties)
      ```
      #Bean Validation 추가
      NotBlank={0} 공백X
      Range={0}, {2} ~ {1} 허용
      Max={0}, 최대 {1}
      ```
    
    <br>

    * BeanValidation 메시지 찾는 순서
      1. 생성된 메시지 코드 순서대로 messageSource 에서 메시지 찾기
      2. 어노테이션의 message 속성 사용 ➡️ @NotBlank(message = "공백! {0}")
      3. 라이브러리가 제공하는 기본 값 사용 ➡️ 공백일 수 없습니다.

  <br>

  * `Bean Validation - 오브젝트 오류`
    * FieldError가 아닌 ObjectError은 @ScriptAssert() 사용

    ```java
      @Data
      @ScriptAssert(lang = "javascript", script = "_this.price * _this.quantity >=
      10000")
      public class Item {
      //...
      }
    ```

    * 그런데 실제 사용해보면 제약이 많고 복잡하다. 그리고 실무에서는 검증 기능이 해당 객체의 범위를 넘어서는 경우들도 종종 등장하는데, 그런 경우 대응이 어렵다.
    * 따라서 오브젝트 오류(글로벌 오류)의 경우 @ScriptAssert 을 억지로 사용하는 것 보다는 다음과 같이 오브젝트 오류 관련 부분만 직접 자바 코드로 작성하는 것을 권장한다. 

    * ValidationItemControllerV3 - addItem 글로벌 오류 추가 
    ```java
      @PostMapping("/add")
      public String addItem(@Validated @ModelAttribute Item item, BindingResult bindingResult,
                              RedirectAttributes redirectAttributes, Model model) {

          // 특정 필드가 아닌 복합 룰 검증
          if (item.getPrice() != null && item.getQuantity() != null) {
              int resultPrice = item.getPrice() * item.getQuantity();
              if (resultPrice < 10000) {
                  bindingResult.reject("totalPriceMin", new Object[]{10000, resultPrice}, null);
              }
          }

          // 검증에 실패하면 다시 입력 폼으로
          if (bindingResult.hasErrors()) {
              log.info("bindingResult = {}", bindingResult.toString());
              return "validation/v3/addForm";
          }

          Item savedItem = itemRepository.save(item);
          redirectAttributes.addAttribute("itemId", savedItem.getId());
          redirectAttributes.addAttribute("status", true);
          return "redirect:/validation/v3/items/{itemId}";
      }
    ```

  <br>

  * `Bean Validation - 한계`
    * 데이터를 등록할 때와 수정할 때는 요구사항이 다를 수 있다.

    <br>

    * 수정시 요구사항
      * 등록시에는 quantity 수량을 최대 9999까지 등록할 수 있지만 수정시에는 수량을 무제한으로 변경할 수 있다. 
      * 등록시에는 id 에 값이 없어도 되지만, 수정시에는 id 값이 필수이다 

  <br>

  * `Bean Validation - Groups`
    * 동일한 모델 객체를 등록할 때와 수정할 때 각각 다르게 검증하는 방법 2가지
      * BeanValidation의 groups 기능을 사용한다.
      * Item을 직접 사용하지 않고, ItemSaveForm, ItemUpdateForm 같은 폼 전송을 위한 별도의 모델 객체를 만들어서 사용한다.

    * groups 적용
      ```java
        // 저장용 groups interface
        package hello.itemservice.domain.item;

        public interface SaveCheck {

        }
      ```

      <br>

      ```java
        // 수정용 groups interface
        package hello.itemservice.domain.item;

        public interface UpdateCheck {

        }
      ```

      <br>

      ```java
        @Data
        //@ScriptAssert(lang = "javascript", script = "_this.price * _this.quantity >= 10000")
        public class Item {

            @NotNull(groups = UpdateCheck.class)
            private Long id;

            @NotBlank(groups = {SaveCheck.class, UpdateCheck.class})
            private String itemName;

            @NotNull(groups = {SaveCheck.class, UpdateCheck.class})
            @Range(min = 1000, max=1000000, groups = {SaveCheck.class, UpdateCheck.class})
            private Integer price;

            @NotNull(groups = {SaveCheck.class, UpdateCheck.class})
            @Max(value = 9999, groups = SaveCheck.class)
            private Integer quantity;

            public Item() {
            }

            public Item(String itemName, Integer price, Integer quantity) {
                this.itemName = itemName;
                this.price = price;
                this.quantity = quantity;
            }
        }
      ```

      <br>

      ```java
        @PostMapping("/add")
        public String addItemV2(@Validated(SaveCheck.class) @ModelAttribute Item item, BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {

            // 특정 필드가 아닌 복합 룰 검증
            if (item.getPrice() != null && item.getQuantity() != null) {
                int resultPrice = item.getPrice() * item.getQuantity();
                if (resultPrice < 10000) {
                    bindingResult.reject("totalPriceMin", new Object[]{10000, resultPrice}, null);
                }
            }

            // 검증에 실패하면 다시 입력 폼으로
            if (bindingResult.hasErrors()) {
                log.info("bindingResult = {}", bindingResult.toString());
                return "validation/v3/addForm";
            }

            Item savedItem = itemRepository.save(item);
            redirectAttributes.addAttribute("itemId", savedItem.getId());
            redirectAttributes.addAttribute("status", true);
            return "redirect:/validation/v3/items/{itemId}";
        }

        @PostMapping("/{itemId}/edit")
        public String editV2(@PathVariable Long itemId, @Validated(UpdateCheck.class) @ModelAttribute Item item,
                          BindingResult bindingResult) {
            // 특정 필드가 아닌 복합 룰 검증
            if (item.getPrice() != null && item.getQuantity() != null) {
                int resultPrice = item.getPrice() * item.getQuantity();
                if (resultPrice < 10000) {
                    bindingResult.reject("totalPriceMin", new Object[]{10000, resultPrice}, null);
                }
            }

            // 검증에 실패하면 다시 입력 폼으로
            if (bindingResult.hasErrors()) {
                log.info("bindingResult = {}", bindingResult.toString());
                return "validation/v3/editForm";
            }


            itemRepository.update(itemId, item);
            return "redirect:/validation/v3/items/{itemId}";
        }
      ```

    <br>

    * @Valid 에는 groups를 적용할 수 있는 기능이 없다. 따라서 groups를 사용하려면 @Validated 를 사용해야 한다.
    * groups 기능을 사용해서 등록과 수정시에 각각 다르게 검증을 할 수 있었다. 
    * 그런데 groups 기능을 사용하니 Item 은 물론이고, 전반적으로 복잡도가 올라갔다.
    * 사실 groups 기능은 실제 잘 사용되지는 않는데, 그 이유는 실무에서는 주로 다음에 등장하는 등록용 폼 객체와 수정용 폼 객체를 분리해서 사용하기 때문이다.

  <br>

  * `Bean Validation - HTTP 메시지 컨버터`
    * @Valid , @Validated 는 HttpMessageConverter ( @RequestBody )에도 적용할 수 있다.

    * ValidationItemApiController
      ```java
        @Slf4j
        @RestController
        @RequestMapping("/validation/api/items")
        public class ValidationItemApiController {

            @PostMapping("/add")
            public Object addItem(@RequestBody @Validated ItemSaveForm form, BindingResult bindingResult) {
                log.info("API 컨트롤러 호출");

                if(bindingResult.hasErrors()) {
                    log.info("검증 오류 발생 errors={}", bindingResult);
                    return bindingResult.getAllErrors();
                }

                log.info("성공 로직 실행");
                return form;
            }
        }
      ```

    * 성공 요청 
    ```
      POST http://localhost:8080/validation/api/items/add
      {"itemName":"hello", "price":1000, "quantity": 10}
    ```

    * 실패 요청 
    ```
      POST http://localhost:8080/validation/api/items/add
      {"itemName":"hello", "price":"A", "quantity": 10}
    ```
      * price의 값에 숫자가 아닌 문자를 전달해서 실패

    * 실패 요청 결과 
    ```
      {
      "timestamp": "2021-04-20T00:00:00.000+00:00",
      "status": 400,
      "error": "Bad Request",
      "message": "",
      "path": "/validation/api/items/add"
      }
    ```
      * HttpMessageConverter 에서 요청 JSON을 ItemSaveForm 객체로 생성하는데 실패한다.
      * 이 경우는 ItemSaveForm 객체를 만들지 못하기 때문에 컨트롤러 자체가 호출되지 않고 그 전에 예외가 발생한다. 물론 Validator도 실행되지 않는다.

    * 검증 오류 요청
      ```
        POST http://localhost:8080/validation/api/items/add
        {"itemName":"hello", "price":1000, "quantity": 10000}
      ```

    * 검증 오류 결과 
    ```
      [
          {
              "codes": [
                  "Max.itemSaveForm.quantity",
                  "Max.quantity",
                  "Max.java.lang.Integer",
                  "Max"
              ],
              "arguments": [
                  {
                      "codes": [
                          "itemSaveForm.quantity",
                          "quantity"
                      ],
                      "arguments": null,
                      "defaultMessage": "quantity",
                      "code": "quantity"
                  },
                  9999
              ],
              "defaultMessage": "9999 이하여야 합니다",
              "objectName": "itemSaveForm",
              "field": "quantity",
              "rejectedValue": 10000,
              "bindingFailure": false,
              "code": "Max"
          }
      ]
    ```

    * return bindingResult.getAllErrors(); 는 ObjectError 와 FieldError 를 반환한다. 
    * 스프링이 이 객체를 JSON으로 변환해서 클라이언트에 전달했다. 
    * 여기서는 예시로 보여주기 위해서 검증 오류 객체들을 그대로 반환했다. 
    * 실제 개발할 때는 이 객체들을 그대로 사용하지 말고, 필요한 데이터만 뽑아서 별도의 API 스펙을 정의하고 그에 맞는 객체를 만들어서 반환해야 한다.

    <br>

  * `Form 전송 객체 분리 - 소개`
    * 복잡한 폼의 데이터를 컨트롤러까지 전달할 별도의 객체를 만들어서 전달한다

    <br>

    * 폼 데이터 전달에 Item 도메인 객체 사용 <br>
    HTML Form -> Item -> Controller -> Item -> Repository
      * 장점 : Item 도메인 객체를 컨트롤러, 리포지토리 까지 직접 전달해서 중간에 Item을 만드는 과정이 없어서 간단하다.
      * 단점 : 간단한 경우에만 적용할 수 있다. 수정시 검증이 중복될 수 있고, groups를 사용해야 한다.

    <br>

    * **폼 데이터 전달을 위한 별도의 객체 사용** <br>
    HTML Form -> ItemSaveForm -> Controller -> Item 생성 -> Repository
      * 장점 : 전송하는 폼 데이터가 복잡해도 거기에 맞춘 별도의 폼 객체를 사용해서 데이터를 전달 받을 수 있다. 보통 등록과, 수정용으로 별도의 폼 객체를 만들기 때문에 검증이 중복되지 않는다. 
      * 단점 : 폼 데이터를 기반으로 컨트롤러에서 Item 객체를 생성하는 변환 과정이 추가된다.

  <br>

  * `Form 전송 객체 분리 - 개발`
    * Item 도메인 코드 원복 
      ```java
        @Data
        public class Item {

            private Long id;

            private String itemName;

            private Integer price;

            private Integer quantity;

            public Item() {
            }

            public Item(String itemName, Integer price, Integer quantity) {
                this.itemName = itemName;
                this.price = price;
                this.quantity = quantity;
            }
        }
      ```

    * ItemSaveForm (Item 저장용 폼)
      ```java
        @Data
        public class ItemSaveForm {

            @NotBlank
            private String itemName;

            @NotNull
            @Range(min = 1000, max = 1000000)
            private Integer price;

            @NotNull
            @Max(value = 9999)
            private Integer quantity;
        }
      ```

    * ItemUpdateForm - Item 수정용 폼
      ```java
        @Data
        public class ItemUpdateForm {

            @NotNull
            private Long id;

            @NotBlank
            private String itemName;

            @NotNull
            @Range(min = 1000, max = 1000000)
            private Integer price;

            private Integer quantity;
        }
      ```

    * ValidationItemControllerV4
      ```java
        @PostMapping("/add")
        public String addItem(@Validated @ModelAttribute("item") ItemSaveForm form, BindingResult bindingResult,
                              RedirectAttributes redirectAttributes, Model model) {

            // 특정 필드가 아닌 복합 룰 검증
            if (form.getPrice() != null && form.getQuantity() != null) {
                int resultPrice = form.getPrice() * form.getQuantity();
                if (resultPrice < 10000) {
                    bindingResult.reject("totalPriceMin", new Object[]{10000, resultPrice}, null);
                }
            }

            // 검증에 실패하면 다시 입력 폼으로
            if (bindingResult.hasErrors()) {
                log.info("bindingResult = {}", bindingResult.toString());
                return "validation/v4/addForm";
            }

            //성공 로직
            Item item = new Item();
            item.setItemName(form.getItemName());
            item.setPrice(form.getPrice());
            item.setQuantity(form.getQuantity());

            Item savedItem = itemRepository.save(item);
            redirectAttributes.addAttribute("itemId", savedItem.getId());
            redirectAttributes.addAttribute("status", true);
            return "redirect:/validation/v4/items/{itemId}";
        }

        @PostMapping("/{itemId}/edit")
        public String edit(@PathVariable Long itemId, @Validated @ModelAttribute("item") ItemUpdateForm form,
                          BindingResult bindingResult) {
            // 특정 필드가 아닌 복합 룰 검증
            if (form.getPrice() != null && form.getQuantity() != null) {
                int resultPrice = form.getPrice() * form.getQuantity();
                if (resultPrice < 10000) {
                    bindingResult.reject("totalPriceMin", new Object[]{10000, resultPrice}, null);
                }
            }

            // 검증에 실패하면 다시 입력 폼으로
            if (bindingResult.hasErrors()) {
                log.info("bindingResult = {}", bindingResult.toString());
                return "validation/v4/editForm";
            }

            //성공 로직
            Item itemParam = new Item();
            itemParam.setItemName(form.getItemName());
            itemParam.setPrice(form.getPrice());
            itemParam.setQuantity(form.getQuantity());

            itemRepository.update(itemId, itemParam);
            return "redirect:/validation/v4/items/{itemId}";
        }
      ```

  <br>

  <br>

  ##### `섹션 6) 로그인 처리 1 - 쿠키, 세션`

  * `쿠키 사용 - 로그인 상태 유지하기` 
    * 서버에서 로그인에 성공하면 HTTP 응답에 쿠키를 담아서 브라우저에 전달
    * 브라우저는 해당 쿠키를 지속적으로 요청에 담음

    * 쿠키 생성 <br>
      <img width="600" alt="쿠키 생성" src="https://github.com/chujaeyeong/Inflearn-Spring/assets/123634960/3fdb8e4c-8d3a-492e-9786-96cce1600adb">

    <br>

    * 클라이언트 쿠키 전달 1 <br>
      <img width="600" alt="클라이언트 쿠키 전달 1" src="https://github.com/chujaeyeong/Inflearn-Spring/assets/123634960/7c12afaf-0088-4c9f-b17b-ce2b18d5f90c">

    <br>

    * 클라이언트 쿠키 전달 2 <br>
      <img width="600" alt="클라이언트 쿠키 전달 2" src="https://github.com/chujaeyeong/Inflearn-Spring/assets/123634960/216a2596-efa3-41e3-bc29-42bd8243f5c3">

      * 영속 쿠키 : 만료 날짜를 입력하면 해당 날짜까지 유지
      * 세션 쿠키 : 만료 날짜를 생략하면 브라우저 종료시 까지만 유지 

  <br>

  * `쿠키 생성 로직`
    ```java
      Cookie idCookie = new Cookie("memberId", String.valueOf(loginMember.getId()));
      response.addCookie(idCookie);
    ```
    * 로그인에 성공하면 쿠키를 생성하고, HttpServletresponse 에 포함 

  <br>

  * `로그인 처리`
    ```java
      @GetMapping("/")
      public String homeLogin(
      @CookieValue(name = "memberId", required = false) Long memberId, Model model)
    ```
    * @CookieValue 를 사용하여 쿠키 조회
    * 로그인하지 않은 사용자도 접근할 수 있도록 required = false

  * `로그아웃`
    ```java
      @PostMapping("/logout")
      public String logout(HttpServletResponse response) {
          expireCookie(response, "memberId");
          return "redirect:/";
      }

      private void expireCookie(HttpServletResponse response, String cookieName) {
          Cookie cookie = new Cookie(cookieName, null);
          cookie.setMaxAge(0);
          response.addCookie(cookie);
      }
    ```
    * 세션 쿠키이므로 웹 브라우저 종료시 서버에서 해당 쿠키의 종료 날짜를 0으로 지정 
    * Max-Age=0으로 설정하여 해당 쿠키는 즉시 종료 

  * `쿠키와 보안 문제`
    * 쿠키 값은 임의로 변경할 수 있음
      * 클라이언트가 쿠키를 강제로 변경하면 다른 사용자가 됨
      * Cookie: memberId=1 → Cookie: memberId=2 (다른 사용자의 이름이 보임) 
    * 쿠키에 보관된 정보는 훔쳐갈 수 있음
      * 만약 쿠키에 개인정보나, 신용카드 정보가 있다면?
      * 이 정보가 웹 브라우저에도 보관되고, 네트워크 요청마다 계속 클라이언트에서 서버로 전달
    * 해커가 쿠키를 한번 훔쳐가면 평생 사용할 수 있음
      * 해커가 쿠키를 훔쳐가서 그 쿠키로 악의적인 요청을 계속 시도할 수 있음

  * `쿠키와 보안 문제 - 대안`
    * 쿠키에 중요한 값을 노출하지 않고, 사용자 별로 예측 불가능한 임의의 토큰(랜덤 값)을 노출하고, 서버에서 토큰과 사용자 id를 매핑해서 인식, 그리고 서버에서 토큰을 관리
    * 토큰은 해커가 임의의 값을 넣어도 찾을 수 없도록 예상 불가능 해야 함
    * 해커가 토큰을 털어가도 시간이 지나면 사용할 수 없도록 서버에서 해당 토큰의 만료시간을 짧게(예: 30분) 유지
    * 해킹이 의심되는 경우 서버에서 해당 토큰을 강제로 제거

  <br>

  * `로그인 처리하기 - 세션 동작 방식`
    
    <img width="600" alt="로그인 처리하기 - 세션 동작 방식 1" src="https://github.com/chujaeyeong/Inflearn-Spring/assets/123634960/41b23d98-d6e4-4ddd-8597-ed18a32775fc">

    * 사용자가 loginId, password 정보를 전달하면 서버에서 해당 사용자가 맞는지 확인 <br>

    <img width="600" alt="로그인 처리하기 - 세션 동작 방식 2" src="https://github.com/chujaeyeong/Inflearn-Spring/assets/123634960/0e8ffaf5-5a18-4082-897d-03576c67df93">

    * 세션 ID를 생성하는데, 추정 불가능해야 함
    * UUID는 추정이 불가능: Cookie: mySessionId=zz0101xx-bab9-4b92-9b32-dadb280f4b61
    * 생성된 세션 ID와 세션에 보관할 값(memberA)을 서버의 세션 저장소에 보관 <br>

    <img width="600" alt="로그인 처리하기 - 세션 동작 방식 3" src="https://github.com/chujaeyeong/Inflearn-Spring/assets/123634960/af7c3b91-3316-45e2-8736-0cf95f1a881b">

    * 서버는 클라이언트에 mySessionId 라는 이름으로 세션ID 만 쿠키에 담아서 전달
    * 클라이언트는 쿠키 저장소에 mySessionId 쿠키를 보관
    * 회원과 관련된 정보는 전혀 클라이언트에 전달하지 않음
    * 추정 불가능한 세션 ID만 쿠키를 통해 클라이언트에 전달

    <img width="600" alt="로그인 처리하기 - 세션 동작 방식 4" src="https://github.com/chujaeyeong/Inflearn-Spring/assets/123634960/4cd75581-c60f-4b0f-a575-cff91dd2fdde">

    * 클라이언트는 요청시 항상 mySessionId 쿠키를 전달 서버에서는 클라이언트가 전달한 mySessionId 쿠키 정보로 세션 저장소를 조회해서 로그인시 보관한 세션 정보를 사용

  <br>

  * `세션과 보안 문제`
    * 쿠키 값을 변조 가능 → 예상 불가능한 복잡한 세션Id를 사용
    * 쿠키에 보관하는 정보는 클라이언트 해킹시 털릴 가능성 → 세션Id가 털려도 여기에는 중요한 정보가 없음
    * 쿠키 탈취 후 사용 → 해커가 토큰을 털어가도 시간이 지나면 사용할 수 없도록 서버에서 세션의 만료시간을 짧게(예: 30분) 유지하거나 해킹이 의심되는 경우 서버에서 해당 세션을 강제로 제거 

  <br>

  * `로그인 처리하기 - 세션 직접 만들기 - 세션 요구 기능`
    * 세션 생성
      * sessionId 생성 (임의의 추정 불가능한 랜덤 값)
      * 세션 저장소에 sessionId와 보관할 값 저장
      * sessionId로 응답 쿠키를 생성해서 클라이언트에 전달
    * 세션 조회
      * 클라이언트가 요청한 sessionId 쿠키의 값으로, 세션 저장소에 보관한 값 조회
    * 세션 만료
      * 클라이언트가 요청한 sessionId 쿠키의 값으로, 세션 저장소에 보관한 sessionId와 값 제거

  <br>

  * `로그인 처리하기 - 세션 직접 만들기 - SessionManager`
    * @Component : 스프링 빈으로 자동 등록
    * ConcurrentHashMap: HashMap 대신 동시 요청에 안전한 ConcurrentHashMap 사용
    ```java
      @Component
      public class SessionManager {
        
          public static final String SESSION_COOKIE_NAME = "mySessionId";
        
          private Map<String, Object> sessionStore = new ConcurrentHashMap<>();
        
          /**
          * 세션 생성
          */
          public void createSession(Object value, HttpServletResponse response) {
            
              //세션 id를 생성하고, 값을 세션에 저장
              String sessionId = UUID.randomUUID().toString();
              sessionStore.put(sessionId, value);
            
              //쿠키 생성
              Cookie mySessionCookie = new Cookie(SESSION_COOKIE_NAME, sessionId);
              response.addCookie(mySessionCookie);
          }
        
          /**
          * 세션 조회
          */
          public Object getSession(HttpServletRequest request) {
              Cookie sessionCookie = findCookie(request, SESSION_COOKIE_NAME);
              if (sessionCookie == null) {
                  return null;
              }
              return sessionStore.get(sessionCookie.getValue());
          }
        
          /**
          * 세션 만료
          */
          public void expire(HttpServletRequest request) {
              Cookie sessionCookie = findCookie(request, SESSION_COOKIE_NAME);
              if (sessionCookie != null) {
                  sessionStore.remove(sessionCookie.getValue());
              }
          }
        
          private Cookie findCookie(HttpServletRequest request, String cookieName) {
              if (request.getCookies() == null) {
                  return null;
              }
              return Arrays.stream(request.getCookies())
                  .filter(cookie -> cookie.getName().equals(cookieName))
                  .findAny()
                  .orElse(null);
          }
      }
    ```

  <br>

  * `로그인 처리하기 - 세션 직접 만들기 - SessionManagerTest`
    ```java
      class SessionManagerTest {
        
          SessionManager sessionManager = new SessionManager();
        
          @Test
          void sessionTest() {
            
              //세션 생성
              MockHttpServletResponse response = new MockHttpServletResponse(); // response mocking
              Member member = new Member();
              sessionManager.createSession(member, response);
            
              //요청에 응답 쿠키 저장
              MockHttpServletRequest request = new MockHttpServletRequest(); // request mocking
              request.setCookies(response.getCookies());
            
              //세션 조회
              Object result = sessionManager.getSession(request);
              assertThat(result).isEqualTo(member);
            
              //세션 만료
              sessionManager.expire(request);
              Object expired = sessionManager.getSession(request);
              assertThat(expired).isNull();
          }
      }
    ```
  
  <br>

  * `로그인 처리하기 - 직접 만든 세션 적용`
    * LoginController - loginV2()
      ```java
        @PostMapping("/login")
        public String loginV2(@Valid @ModelAttribute LoginForm form, BindingResult bindingResult, HttpServletResponse response) {
            if (bindingResult.hasErrors()) {
                return "login/loginForm";
            }
          
            Member loginMember = loginService.login(form.getLoginId(),     form.getPassword());
            log.info("login? {}", loginMember);
          
            if (loginMember == null) {
                bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
                return "login/loginForm";
            }
          
            //로그인 성공 처리
            //세션 관리자를 통해 세션을 생성하고, 회원 데이터 보관
            sessionManager.createSession(loginMember, response);
          
            return "redirect:/";
        }
      ```
      * private final SessionManager sessionManager; 주입
      * sessionManager.createSession(loginMember, response); 로그인 성공시 세션을 등록. 세션에 loginMember를 저장해두고, 쿠키도 함께 발행

    <br>

    * LoginController - logoutV2()
      ```java
        @PostMapping("/logout")
        public String logoutV2(HttpServletRequest request) {
            sessionManager.expire(request);
            return "redirect:/";
        }
      ```
      * 로그아웃시 해당 세션의 정보를 제거 

    <br>

    * HomeController - homeLoginV2()
      ```java
        @GetMapping("/")
        public String homeLoginV2(HttpServletRequest request, Model model) {
          
            //세션 관리자에 저장된 회원 정보 조회
            Member member = (Member)sessionManager.getSession(request);
            if (member == null) {
                return "home";
            }
          
            //로그인
            model.addAttribute("member", member);
            return "loginHome";
        }
      ```
      * private final SessionManager sessionManager; 주입
      * 세션 관리자에서 저장된 회원 정보를 조회
      * 만약 회원 정보가 없으면, 쿠키나 세션이 없는 것이므로 로그인 되지 않은 것으로 처리

  <br>

  * `로그인 처리하기 - 서블릿 HTTP 세션 - HttpSession`
    * 세션 생성과 조회
      * 세션 생성과 조회 : public HttpSession getSession(boolean create);
    * request.getSession(true)
      * 세션이 있으면 기존 세션을 반환
      * 세션이 없으면 새로운 세션을 생성해서 반환
    * request.getSession(false)
      * 세션이 있으면 기존 세션을 반환
      * 세션이 없으면 새로운 세션을 생성하지 않고 null 을 반환
    * request.getSession() : 신규 세션을 생성하는 request.getSession(true)와 동일

    <br>

    * 세션에 로그인 회원 정보 보관
      * session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember); 
      * 세션에 데이터를 보관하는 방법은 request.setAttribute(..) 와 유사 하나의 세션에 여러 값을 저장할 수 있음 

  <br>

  * `@SessionAttribute`
    * 스프링에서 이미 로그인 된 사용자를 찾을 때는 다음과 같이 사용 이 기능은 세션을 생성하지않음
    * @SessionAttribute(name = "loginMember", required = false) Member loginMember

    <br>

    * TrackingModes
      * 서버 입장에서 웹 브라우저가 쿠키를 지원하는지 하지 않는지 최초에는 판단하지 못하므로, 쿠키 값도 전달하고, URL에 jsessionid도 함께 전달
      * 타임리프 같은 템플릿은 엔진을 통해서 링크를 걸면 jsessionid를 URL에 자동으로 포함
      * URL 전달 방식을 끄고 항상 쿠키를 통해서만 세션을 유지하고 싶으면 아래와 같이 설정
      ```
        server.servlet.session.tracking-modes=cookie
      ```

  <br>

  * `세션 정보와 타임아웃 설정 - 세션 정보`
    * sessionId : 세션Id, JSESSIONID의 값 ex) 34B14F008AA3527C9F8ED620EFD7A4E1
    * maxInactiveInterval : 세션의 유효 시간, ex) 1800초, (30분)
    * creationTime : 세션 생성일시
    * lastAccessedTime : 세션과 연결된 사용자가 최근에 서버에 접근한 시간, 클라이언트에서 서버로 sessionId( JSESSIONID )를 요청한 경우에 갱신
    * isNew : 새로 생성된 세션인지, 아니면 이미 과거에 만들어졌고, 클라이언트에서 서버로 sessionId ( JSESSIONID )를 요청해서 조회된 세션인지 여부

  <br>

  * `세션 정보와 타임아웃 설정 - 세션 타임아웃 설정`
    * 세션은 사용자가 로그아웃을 직접 호출해서 session.invalidate()가 호출 되는 경우에 삭제
    * 그러나 대부분의 사용자는 로그아웃을 선택하지 않고, 그냥 웹 브라우저를 종료
    * HTTP는 비연결성(ConnectionLess)이므로 서버 입장에서는 해당 사용자가 웹 브라우저를 종료한 것인지 아닌지를 인식할 수 없음
    * 이 경우 남아있는 세션을 무한정 보관하면 다음과 같은 문제가 발생
      * 세션과 관련된 쿠키( JSESSIONID )를 탈취 당했을 경우 오랜 시간이 지나도 해당 쿠키로 악의적인 요청 가능
      * 세션은 기본적으로 메모리에 생성되므로 메모리의 크기가 무한하지 않기 때문에 꼭 필요한 경우만 생성해서 사용해야 함

    * 스프링 부트로 글로벌 설정 - application.properties
      ```
        server.servlet.session.timeout=60 // 기본 1800초
      ```

    * 특정 세션 단위로 시간 설정
      ```
      session.setMaxInactiveInterval(1800); // 1800초
      ```

  <br>

  * `세션 정보와 타임아웃 설정 - 세션의 종료 시점`
    * 세션 생성 시점으로부터 30분
      * 30분이 지나면 세션이 삭제되기 때문에 30분 마다 계속 로그인해야 하는 번거로움이 발생
    * 더 나은 대안은 세션 생성 시점이 아니라 사용자가 서버에 최근에 요청한 시간을 기준으로 30분 정도를 유지해주는 것
      * HttpSession은 이 방식을 사용

  <br>

  * `세션 정보와 타임아웃 설정 - 세션 타임아웃 발생`
    * 세션의 타임아웃 시간은 해당 세션과 관련된 JSESSIONID를 전달하는 HTTP 요청이 있으면 현재 시간으로 다시 초기화
    * 이렇게 초기화 되면 세션 타임아웃으로 설정한 시간동안 세션을 추가로 사용할 수 있음
    * session.getLastAccessedTime() : 최근 세션 접근 시간
    * LastAccessedTime 이후로 timeout 시간이 지나면, WAS가 내부에서 해당 세션을 제거

  <br>

  <br>

  ##### `섹션 7) 로그인 처리 2 - 필터, 인터셉터`



  ##### `섹션 8) 예외 처리와 오류 페이지`



  ##### `섹션 9) API 예외처리`



  ##### `섹션 10) 스프링 타입 컨버터`



  ##### `섹션 11) 파일 업로드`
  
  
</details>

<details>
  
  <summary>📖 수강 후기</summary>


  
</details>

#

#### `06. 자바 ORM 표준 JPA 프로그래밍 - 기본편`

<details>
  <summary>✏️ 학습 내용 정리</summary>

</details>

<details>
  <summary>📖 수강 후기</summary>

</details>

#

#### `07. 실전 스프링 부트와 JPA 활용 1 - 웹 애플리케이션 개발`

<details>
  <summary>✏️ 학습 내용 정리</summary>

</details>

<details>
  <summary>📖 수강 후기</summary>

</details>

#

#### `08. 실전 스프링 부트와 JPA 활용 2 - API 개발과 성능 최적화`

<details>
  <summary>✏️ 학습 내용 정리</summary>

</details>

<details>
  <summary>📖 수강 후기</summary>

</details>

#

#### `09. 실전 스프링 데이터 JPA`

<details>
  <summary>✏️ 학습 내용 정리</summary>

</details>

<details>
  <summary>📖 수강 후기</summary>

</details>
