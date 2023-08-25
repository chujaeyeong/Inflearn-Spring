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

<br>

<br>

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
            <div markdown="1">    
      
            @Bean
            public OrderService orderService() {
                System.out.println("call AppConfig.orderService");
            //  return new OrderServiceImpl(memberRepository(), discountPolicy());
                return null;
            }
      
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
    * <img width="576" alt="image" src="https://github.com/chujaeyeong/Inflearn-Spring/assets/123634960/43c34c11-c830-42cf-b465-3a2f929086e6">
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
      * <img width="573" alt="image" src="https://github.com/chujaeyeong/Inflearn-Spring/assets/123634960/c6e38b96-b749-425a-89fa-cde4b8d17a1a">
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
      * <img width="575" alt="image" src="https://github.com/chujaeyeong/Inflearn-Spring/assets/123634960/b8bcfbe0-dfa5-4ff8-ab85-64d7c5d52cea">
      
      * Provider (ObjectProvider) 를 활용하면 ObjectProvider.getObject() 를 호출하는 시점까지 request scope 빈의 생성을 지연시킬 수 있다. Controller와 Service 에서 각각 ObjectProvider.getObject() 을 호출해도 같은 HTTP 요청이면 같은 스프링 빈이 반환됨.
      * 프록시 방식을 사용하면, Provider 방식을 활용하는 것 보다 코드를 더 간결하게 구성할 수 있다.
      * <img width="577" alt="image" src="https://github.com/chujaeyeong/Inflearn-Spring/assets/123634960/2c7a4adf-b50c-4305-90e9-f48bd6aea125">
      * CGLIB라는 라이브러리로 내 클래스를 상속 받은 가짜 프록시 객체를 만들어서 주입한다. 가짜 프록시 객체는 요청이 오면, 그때 내부에서 진짜 빈을 요청하는 위임 로직이 들어있다. 가짜 프록시 객체는 원본 클래스를 상속 받아서 만들어졌기 때문에, 이 객체를 사용하는 클라이언트 입장에서는 사실 원본인지 아닌지도 모르게 동일하게 사용할 수 있다. (다형성)
      * 프록시의 장점 : 프록시 객체 적분에 클라이언트는 마치 싱글톤 빈을 사용하듯이 편리하게 request scope를 사용할 수 있다. 진짜 객체 조회를 꼭 필요한 시점까지 지연처리하는것이 가능하다. 또한, 꼭 웹 스코프가 아니어도 프록시는 사용할 수 있다.
      * 프록시 사용의 주의점 : 프록시는 마치 싱글톤을 사용하는 것 같지만, 다르게 동작하기 때문에 결국 사용에 주의가 필요함. 무분별한 프록시 스코프 사용은 유지보수에 어려움을 줄 수 있다. 


</details>

<details>
    
  <summary>📖 수강 후기</summary>
  
  * 객체 지향 설계를 기본으로, 스프링의 원리와 스프링이 제공하는 핵심 기능을 학습할 수 있었다.
  * 싱글톤 컨테이너나 의존성 주입같이 어렴풋이 알고 있던 핵심 원리는 완전하게 익혔고, 빈 생명주기나 콜백, 스코프에 대해서는 이번에 처음 배웠는데, 이런 실무에서 자주 사용하는 기능까지 깊이 있는 학습하여 스프링의 전체 이해도를 높일 수 있었다. 😎 
      
      
</details>

<br>

<br>


#### `03. 모든 개발자를 위한 HTTP 웹 기본지식`

<details>
  
  <summary>✏️ 학습 내용 정리</summary>

  * ##### `1) 인터넷 네트워크`
    * 클라이언트와 서버는 각 IP 주소를 가진다. IP는 인터넷 프로토콜 역할을 함. (지정한 IP 주소에 패킷 이라는 통신 단위로 데이터 전달)
    * 클라이언트는 서버에세 전송하고자 하는 데이터를 출발지 IP, 목적지 IP 등의 정보로 구성된 IP 패킷으로 한 번 감싸서 서버로 전달하는 형식
    * IP 프로토콜의 한계 : 비연결성, 비신뢰성, 프로그램 구분
    
    <br>
    
    * TCP, UDP는 인터넷 프로토콜 스택의 4계층에서 전송계층 을 담당
    * <img width="816" alt="image" src="https://github.com/chujaeyeong/Inflearn-Spring/assets/123634960/11b5ab02-2f82-48bb-bf14-ee71d5fdd623">
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
    * <img width="563" alt="image" src="https://github.com/chujaeyeong/Inflearn-Spring/assets/123634960/ac17136f-f5e0-4304-95d6-00096fb49910">
    * <img width="856" alt="image" src="https://github.com/chujaeyeong/Inflearn-Spring/assets/123634960/6415b7fc-1620-4406-8ca2-d504126f7d22">
    * URI와 URL은 같은 의미라고 보면 되고, URN은 리소스에 이름을 부여한 것 (위치는 변할 수 있지만, 이름은 변하지 않는다)
    * URL의 전체 문법 구성 : scheme://[userinfo@]host[:port][/path][?query][#fragment]
    
    <br>

    * 웹 브라우저 요청 흐름 - HTTP 메시지 전송
    * <img width="765" alt="image" src="https://github.com/chujaeyeong/Inflearn-Spring/assets/123634960/162554b4-d99a-4d9e-aa9c-7d7cc9361833">
    * 서버에 HTTP 메시지가 도착하면, 서버는 HTTP 응답 메시지를 다시 클라이언트한테 보내준다

    <br>

  * ##### `3) HTTP`
    * 



</details>  
