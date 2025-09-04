package com.mysite.sbb;

import com.mysite.sbb.question.Question;
import com.mysite.sbb.question.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class SbbApplicationTests {

    @Autowired
    private QuestionRepository questionRepository;

    @Test
    void TestJpa() {
        Question q1 = new Question();
        q1.setSubject("sbb가 무엇인가요?");
        q1.setContent("sb에 대해서 알고 싶습니다.");
        q1.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(q1); //첫번째 질문 저장

        Question q2 = new Question();
        q2.setSubject("스프링부트 모델 질문입니다.");
        q2.setContent("id는 자동으로 생성되나요?");
        q2.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(q2);  // 두번째 질문 저장
    }

    @Test
    void TestFindAllTest() {
        List<Question> all = this.questionRepository.findAll();
        assertEquals(2, all.size());

        Question q = all.get(1);
        assertEquals("스프링부트 모델 질문입니다.",q.getSubject());
    }

    @Test
    void TestFindByIdTest() {
        Optional<Question> oq = this.questionRepository.findById(2);
        if (oq.isPresent()) {
            Question q = oq.get();
            assertEquals("스프링부트 모델 질문입니다.", q.getSubject());
        }
    }

    @Test
    void TestFindBySubject()  {
            Question q = this.questionRepository.findBySubject("sbb가 무엇인가요?");
            assertEquals(1, q.getId());
        }

    @Test
    void TestFindBySubjectAndContent()  {
        Question q = this.questionRepository.findBySubjectAndContent(
                "what the?", "abcdefg");
//                "abcdefg", "what the?");
        assertEquals(2, q.getId());

    }

    @Test
    void TestFindBySubjectLike()  {
        List<Question> qList = this.questionRepository.findBySubjectLike("%what%");
        Question q = qList.get(0);
        assertEquals("what the?", q.getSubject());
        assertEquals("abcdefg", q.getContent());
    }




}
