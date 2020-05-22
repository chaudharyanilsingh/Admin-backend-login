package com.testing.model;
import javax.persistence.*;
import javax.validation.constraints.*;
import com.testing.enums.AllEnums.QuestionType;

import lombok.Data;

@Entity
@Table(name = "questions")
@Data
public class Questions extends Basetimes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "question", nullable = false)
    private String question;
    
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private QuestionType questionType;
	
	@OneToOne(cascade = CascadeType.ALL)
	private UserQuestionOptions userQuestionOptions;
}
