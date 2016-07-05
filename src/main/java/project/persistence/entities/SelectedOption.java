package project.persistence.entities;

import project.persistence.entities.Question;
import project.persistence.entities.Result;

import javax.persistence.*;

@Entity
@Table(name = "selectedOption") // If you want to specify a table name, you can do so here
public class SelectedOption {

    // Declare that this attribute is the id
    @Id
    @Column(name = "selectedOptionId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String selectedOptionText;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "resultId")
    private Result result;

    private Long questionId;

    private Boolean isCorrect;

    //private String[] selectedOptions;

    // Notice the empty constructor, because we need to be able to create an empty Survey to add
    // to our model so we can use it with our form
    public SelectedOption() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSelectedOptionText() {
        return selectedOptionText;
    }

    public void setSelectedOptionText(String selectedOptionText) {
        this.selectedOptionText = selectedOptionText;
    }

    public Result getResult() { return result; }

    public void setResult(Result result) { this.result = result; }

    public Long getQuestionId() { return questionId; }

    public void setQuestionId(Long questionId) { this.questionId = questionId; }

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
                "selectedOptionText [selectedOptionText=%s]",
                selectedOptionText);
    }
}