package com.mysite.sbb.question;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    Question findBySubject(String subject);

    Question findBySubjectAndContent(String subject, String content);
//    Question findBySubjectAndContent(String content, String subject);
    List<Question> findBySubjectLike(String subject);

}
