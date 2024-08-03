package com.so.quizzes.core.domain.qAndA;

import com.so.quizzes.core.enums.DifficultyLevel;
import com.so.quizzes.dto.basic.qAndA.QuestionDto;
import jakarta.persistence.*;
import org.springframework.beans.BeanUtils;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "questions")
@DiscriminatorColumn(name = "question_type", discriminatorType = DiscriminatorType.STRING)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "question_type", insertable=false, updatable=false)
    private String questionType;
    @Column(name = "pronunciation")
    private String pronunciation = "Question X";
    @Enumerated(EnumType.STRING)
    @Column(name = "difficulty_level")
    private DifficultyLevel difficultyLevel = DifficultyLevel.EASY;
    @ElementCollection(targetClass = String.class, fetch = FetchType.LAZY)
    @CollectionTable(name = "questions_tags", joinColumns = @JoinColumn(name = "question_id"))
    @Column(name = "tag")
    // TODO: 4/12/2023 ManyToMany relationship, with tag to be an entity
    private Set<String> tags = new HashSet<>();

    public Question() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPronunciation() {
        return pronunciation;
    }

    public void setPronunciation(String pronunciation) {
        this.pronunciation = pronunciation;
    }

    public DifficultyLevel getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(DifficultyLevel difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public QuestionDto toQuestionDto() {
        var questionDto = new QuestionDto();
        BeanUtils.copyProperties(this, questionDto);
        return questionDto;
    }
}
