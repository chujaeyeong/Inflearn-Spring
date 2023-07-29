package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 테스트 하나 실행할때마다 스토어를 싹 비워주자
    // 클래스 전체 테스트를 돌릴 때는 객체마다 순서를 정할 수가 없다 (지맘대로 후루루짭짭 돌려줌)
    // 그래서 각 객체마다 테스트를 해서 문제 없었어도, 전체 클래스를 돌릴 때 순서때문에 테스트가 원활하게 진행되지 않을 수도 있어서
    // 스토어를 비워주는 작업을 추가하는것이다
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        // get으로 바로 꺼내는건 사실 비추방법인데 테스트코드니까 걍 ㄱㄱ
        // System.out.println("result = " + (result == member)); 글자로 계속 볼 수는 없으니까 우리는 어썰트 (Assertions) 라는 기능을 쓰자
        // Assertions.assertEquals(member, result);
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);

    }

}
