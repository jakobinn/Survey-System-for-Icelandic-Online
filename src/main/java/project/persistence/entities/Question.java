package project.persistence.entities;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

/**
 * The class for the Question itself.
 * The system generates a table schema based on this class for this entity.
 * Be sure to annotate any entities you have with the @Entity annotation.
 */
@Entity
@Table(name = "question") // If you want to specify a table name, you can do so here
public class Question {

    // Declare that this attribute is the id
    @Id
    @Column(name="questionId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "surveyId")
    private Survey survey;

    @OneToMany(mappedBy = "question",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Option> options;

    @OneToMany(mappedBy = "question",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Result> results;

    @ElementCollection
    @MapKeyColumn(name="optionText")
    @Column(name="value")
    @CollectionTable(name="question_counts", joinColumns=@JoinColumn(name="questionId"))
    Map<String, Long> optionCounts = new HashMap<String, Long>();

    private String questionText;
    private String type;

    private Long timesAnswered = (long)0;

    private int weight = 1;

    // Notice the empty constructor, because we need to be able to create an empty Survey to add
    // to our model so we can use it with our form
    public Question() {
        this.results = new ArrayList<Result>();
    }

    public Question(String questionText, String type) {
        this.questionText = questionText;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String question) {
        this.questionText = question;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() { return type; }

    public Survey getSurvey() { return survey; }

    public void setSurvey(Survey survey) { this.survey = survey; }

    public List<Option> getOptions() { return options; }

    public void setOptions(List<Option> options) { this.options = options; }

    public void addOption(Option option) {
        if (!getOptions().contains(option)) {
            getOptions().add(option);
            if (option.getQuestion() != null) {
                option.getQuestion().getOptions().remove(option);
            }
            option.setQuestion(this);
        }
    }


    public void choosePredeterminedOptions(String optionText){
        String[] options = new String[]{};

        if (optionText.equals("pred1")){
            options = new String[]{"Yes", "No"};
        }
        if (optionText.equals("pred2")){
            options = new String[]{"True", "False"};
        }
        if (optionText.equals("pred3")){
            options = new String[]{"Easy", "Medium", "Hard"};
        }
        if (optionText.equals("pred4")){
            options = new String[]{"Bad", "Good", "Great"};
        }
        addPredeterminedOptions(options);
    }

    public void addPredeterminedOptions(String[] options){
        for(int i = 0; i < options.length; i++){
            Option option = new Option();
            option.setOptionText(options[i]);
            getOptions().add(option);
            option.setQuestion(this);
        }
    }

    public Map<String, Long> getOptionCounts() { return optionCounts; }

    public void setOptionCounts(Map<String, Long> optionCounts) { this.optionCounts = optionCounts; }

    public List<Result> getResults() { return results; }

    public void setResults(List<Result> results) { this.results = results; }

    public void addResult(Result result) {
        if (!getResults().contains(result)) {
            getResults().add(result);
            if (result.getQuestion() != null) {
                result.getQuestion().getResults().remove(result);
            }
            result.setQuestion(this);
        }
    }

    public Long getTimesAnswered() { return timesAnswered; }

    public void setTimesAnswered(Long timesAnswered) { this.timesAnswered = timesAnswered; }

    public int getWeight() { return weight; }

    public void setWeight(int weight) { this.weight = weight; }
    // This is for easier debug.
    @Override
    public String toString() {
        return String.format(
                "Question [question=%s, type=%s]",
                questionText, type);
    }
}
