# 스프링부트로 was만들기


## 목표
서비스 회사의 기술을 이해하고, 어떤 방식으로 기술을 적용하는지, 그들과 함께 일하기 위해서 비슷한 사고로 프로그래밍을 하고 보완점을 찾기 위해 노력

 서비스 기업이 사용하고 있는 기술에 대한 탐구
 - 왜 이 기술을 사용했는지, 사용하면서 생기는 장점은 무엇인지, 보완할 수 있는 부분이 있는지 고민 필요
 

### MISSION
1. indent가 3 이상의 코드를 만들지 않는다.
2. method의 길이가 10줄 이상인 코드를 만들지 않는다.
3. Test를 도입하고 coverage를 관리한다. (정확한 수치 목표는 진행하면서 정한다.)
4. 커밋메시지로 히스토리를 관리할 수 있도록 명확하게 분리하여 커밋하고 커밋메시지를 작성한다.


진행 시, 주의 사항이나 얻은 지식들을 이 파일로 요약 관리한다.

<hr>

### 꿀팁

#### 어노테이션
##### 주요 어노테이션을 클래스에 가깝게 둡니다. 이렇게 어노테이션을 정렬하는 기준은 다음과 같다.
- @Entity는 JPA의 어노테이션, @Getter, @NoArgsConstructor는 롬복의 어노테이션이다. -> 롬복은 코드 단순화가 목적, 필수 어노테이션이 아님
이후에 코틀린 등의 새 언어 전환으로 롬복이 필요 없을 경우 쉽게 삭제할 수 있다.

<br>
<br>
<br>


<hr>
[ 참고자료 ] 스프링부트와 AWS로 혼자 구현하는 웹 서비스 - 이동욱님
