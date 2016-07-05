package project.persistence.entities;

import javax.persistence.*;
import java.util.List;

/**
 * The class for the Postit author itself.
 * The system generates a table schema based on this class for this entity.
 * Be sure to annotate any entities you have with the @Entity annotation.
 */
@Entity
@Table(name = "survey") // If you want to specify a table name, you can do so here
public class Survey {

    // Declare that this attribute is the id
    @Id
    @Column(name = "surveyId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String author;
    private String category;
    private Boolean isTest;
    private Double totalWeight = 0.0;

    @OneToMany(mappedBy = "survey",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Question> questions;

    // Notice the empty constructor, because we need to be able to create an empty Survey to add
    // to our model so we can use it with our form
    public Survey() {

    }

    public Survey(String name, String author) {
        this.name = name;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() { return category; }

    public void setCategory(String category) { this.category = category; }

    public Boolean getIsTest() { return isTest; }

    public void setIsTest(boolean isTest) { this.isTest = isTest; }

    public List<Question> getQuestions() { return questions; }

    public void setQuestions(List<Question> questions) { this.questions = questions; }

    public void addQuestion(Question question) {
        if (!getQuestions().contains(question)) {
            getQuestions().add(question);
            if (question.getSurvey() != null) {
                question.getSurvey().getQuestions().remove(question);
            }
            question.setSurvey(this);
        }
    }

    public Double getTotalWeight() { return totalWeight; }

    public void setTotalWeight( Double totalWeight ) { this.totalWeight = totalWeight; }

    // This is for easier debug.
    @Override
    public String toString() {
        return String.format(
                "Survey [name=%s, author=%s]",
                name,author);
    }
}
