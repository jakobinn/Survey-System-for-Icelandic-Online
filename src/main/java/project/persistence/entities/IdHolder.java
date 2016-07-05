package project.persistence.entities;

import java.util.ArrayList;
import org.springframework.util.AutoPopulatingList;


/**
 * Created by gudkj on 11/24/2015.
 */
public class IdHolder {
    private Long questionId;
    private Long userId;
    public ArrayList<Long> optionIds = new ArrayList<Long>();
    private String text;

    public IdHolder() {}

    public Long getQuestionId() { return questionId; }

    public void setQuestionId(Long questionId) { this.questionId = questionId; }

    public ArrayList<Long> getOptionIds() { return optionIds; }

    public void setOptionIds(ArrayList<Long> optionIds) { this.optionIds = optionIds; }

    public Long getUserId() { return userId; }

    public void setUserId(Long userId) { this.userId = userId; }

    public String getText() { return text; }

    public void setText(String text) { this.text = text;}
}
