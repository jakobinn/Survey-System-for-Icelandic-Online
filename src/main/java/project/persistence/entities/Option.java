package project.persistence.entities;

import javax.persistence.*;

/**
 * The class for the Question itself.
 * The system generates a table schema based on this class for this entity.
 * Be sure to annotate any entities you have with the @Entity annotation.
 */
@Entity
@Table(name = "option") // If you want to specify a table name, you can do so here
public class Option {

    // Declare that this attribute is the id
    @Id
    @Column(name = "optionId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String optionText;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "questionId")
    private Question question;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "resultId")
    private Result result;

    private Boolean isCorrect;

    //private String[] options;

    // Notice the empty constructor, because we need to be able to create an empty Survey to add
    // to our model so we can use it with our form
    public Option() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOptionText() {
        return optionText;
    }

    public void setOptionText(String optionText) {
        this.optionText = optionText;
    }

    public Question getQuestion() {
        return question; }

    public void setQuestion(Question question) { this.question = question; }

    public Result getResult() { return result; }

    public void setResult(Result result) { this.result = result; }

    public Boolean getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(Boolean isCorrect) {
           this.isCorrect = isCorrect;
    }

    // This is for easier debug.
    @Override
    public String toString() {
        return String.format(
                "optionText [optionText=%s]",
                optionText);
    }
}
