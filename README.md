# 스프링부트로 was만들기

## 프로젝트 spec
 ![https://jojoldu.tistory.com/267](https://t1.daumcdn.net/cfile/tistory/996F763D5A73F91E26)


서버 환경 구성 (aws) : EC2인스턴스(ubuntu), RDS, S3, CodeDeploy, Travis CI, nginX
<br> 

 Framework: SpringBoot <br>
 DB: MariaDB, Spring Data JPA <br>
 TEST: JUnit4 <br>
 Java8, Gradle 4.10.2
 

## 목표
서비스 회사의 기술을 이해하고 적용하기


## 적용
 웹 개발에 필요한 전반적인 사이클을 적용해보고 그 기술에 대한 적용 이유를 생각해보기
 1. jpa : 객체지향적으로 프로그램을 만드는데 큰 역할을 수행한다. 테이블 모델링에 집중하는 것이 아니라 **객체 모델링에 집중**할 수 있는 ORM(Object Relational Mapping)기술이다.<br> 
 데이터의 CRUD를 Query없이 애노테이션만으로 관리할 수 있다.**(단순 반복 작업 제거)**<br>
 관계형 데이터베이스의 경우 어떻게 데이터를 저장할 지에 초점을 맞춘 기술, 객체지향적 프로그래밍을 하고 JPA가 이를 관계형 데이터베이스에 맞게 SQL을 대신 생성해서 실행한다.<br>
 
 2. JPA Auditing : Entity의 상위클래스로 BaseTimeEntity클래스를 만들고 JPA Auditing을 할 수 있도록 추가하게 되면 각각의 Entity의 createdDate, modifiedDate를 자동으로 관리해준다.<br>
 이는 서비스를 제공하면서 대부분의 데이터가 생성되거나 수정되는 시점에 대한 정보가 필요하고 반복적인 작업을 피하기 위해 사용한 것이라 생각된다. <br>또한 각 Entity의 시간 관련 데이터를 일괄적으로 관리하여 **관리포인트를 줄여주는 역할**도 할 수 있다. <br>
 
 3. CI : VCS 시스템에 push되면 자동으로 테스트와 빌드가 수행되며 배포파일을 만드는 과정을 CI(Continuous Integration)이라 한다.<br> 서버의 총 댓수가 많아지거나 긴박하게 배포를 해야하는 상황에 수동배포는 불리하다. 또, 수동으로 배포할 경우 human error를 일으킬 위험이 있어 배포 과정을 획일화하여 스크립트로 만들어 배포한다면 **인간의 실수를 막을 수 있다.**<br> 다른 중요한 부분은 테스팅 자동화이다. 지속적으로 통합하기 위해 이 **프로젝트가 완전한 상태임을 보장**해야한다. 이를 위한 **최소한의 보장을 테스트가 해줄 수 있다.**<br>
 
 4. 무중단 배포 : 24시간 지속적으로 운영되는 서비스의 경우 배포를 새로 하게 될 경우 중단을 했을 때 발생하는 문제들이 있을 수 있다.<br> 더 나아가서 새 버전의 배포마다 사용자가 서비스를 사용할 수 없다는 것은 사용자 경험에 악영향을 끼칠 수 있다. <br>사용자의 만족을 최대한으로 이끌 수 있는 서비스를 만들기 위해서도 무중단 배포는 중요하다.
 
 - 이전 회사에서 안드로이드 개발을 하면서 QA하기 위해 내부에만 배포를 할 때 APK를 만들어서 슬랙이나 url로 공유하는 과정을 통해 수동으로 진행했었다.<br> 이 책은 서버 사이드의 서비스 제공 전반에 관련한 내용을 전달해주고 있는데 **클라이언트 배포도 비슷한 원리로 적용**할 수 있을 것이라 생각한다.<br> S3에 버전별 업로드, 슬랙의 QA channel에 apk배포를 구현하는 것이 가능할 것이라 생각된다.<br> 또한 그 과정에서 테스트까지 수행하여 보장된 빌드 결과물을 전달한다면 업무 외적인 시간 소요가 줄어들 수 있었을 것이다. <br>*지난 시행착오를 잊지말고 앞으로 만들 시스템에 개선할 것들을 정리하는 습관을 길러야 한다.*
 
 
### MISSION
1. indent가 3 이상의 코드를 만들지 않는다.
2. method의 길이가 10줄 이상인 코드를 만들지 않는다.
3. Test를 도입하고 coverage를 관리한다. (정확한 수치 목표는 진행하면서 정한다.)
4. 커밋메시지로 히스토리를 관리할 수 있도록 명확하게 분리하여 커밋하고 커밋메시지를 작성한다.


진행 시, 주의 사항이나 얻은 지식들을 이 파일로 요약 관리한다.

<hr>

## 꿀팁

### 어노테이션
##### 주요 어노테이션을 클래스에 가깝게 둡니다. 이렇게 어노테이션을 정렬하는 기준은 다음과 같다.
- @Entity는 JPA의 어노테이션, @Getter, @NoArgsConstructor는 롬복의 어노테이션이다. -> 롬복은 코드 단순화가 목적, 필수 어노테이션이 아님
이후에 코틀린 등의 새 언어 전환으로 롬복이 필요 없을 경우 쉽게 삭제할 수 있다.
<br><br>

### User클래스를 바로 사용하지 않은 이유(Dto를 따로 만드는 이유)
- 세션에서 User클래스를 저장하려 한다면 User클래스를 직렬화하지 않았다는 에러를 뱉음.<br>
-> 오류를 해결하기 위해 Entity클래스에 직렬화를 넣는다면? <br>
--> @OneToMany, @ManyToMany 등 자식 엔티티를 가질 경우 직렬화 대상에 자식들까지 포함 <br>
---> 성능이슈, 부수 효과가 발생할 확률 높다.

안드로이드 개발 때 비슷한 상황이 발생했던 적이 있다. 
안드로이드에서 객체를 다른 Activity로 보낼 경우 직렬화하여 Intent로 전달하는 경우가 있다.
dto와 entity를 분리해서 사용하지 않아 entity와 관계가 있는 다른 클래스들도 직렬화를 하거나 상속을 끊어서 상황을 모면했다.
공부를 하면서 시간상의 압박으로 상황을 모면하는 것이 아닌, 해결을 위한 고민과 과정으로 더 나은 서비스를 만들 수 있겠다는 생각을 했다. 

직렬화란 무엇인가에 대해 구글링하는 것은 어렵지 않지만, 
* 직렬화를 사용하지 말아야 하는 이유, 
* 사용할 때의 조심해야하는 이유와 대처법

에 대해 이 책을 보면서 배울 수 있었다.
 직렬화 기능을 가진 dto객체를 만들어 이용하는 것이 운영 및 유지보수 때 큰 도움이 될 수 있다.
 내가 앞으로도 수많은 문제들을 만나게 될텐데, 이 해결법처럼 더 근본적인 문제를 파악하고 해결하려 노력해야겠다.
<br>
<br>
<br>
<hr>

## 번외

### jacoco 테스트 커버리지 검사

책의 소스를 토대로 작성했을 경우 test coverage는 43%

연습 용도로 테스트할 부분을 찾아 추가로 테스트 작성하여 coverage를 올리려함

_목적은 테스트 연습, 지치지않게 무리하지 않고 테스트 추가할 예정_


<hr>
[ 참고자료 ] 스프링부트와 AWS로 혼자 구현하는 웹 서비스 - 이동욱님
