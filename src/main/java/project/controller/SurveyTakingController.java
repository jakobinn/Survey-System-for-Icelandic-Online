package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import project.persistence.entities.*;
import project.service.StringManipulationService;
import project.service.SurveyService;
import project.service.QuestionService;
import project.service.OptionService;
import project.service.ResultService;
import project.service.UserService;

import java.util.*;

@Controller
public class SurveyTakingController {

    // Instance Variables
    SurveyService surveyService;
    QuestionService questionService;
    OptionService optionService;
    ResultService resultService;
    UserService userService;
    StringManipulationService stringManipulationService = new StringManipulationService();

    // Dependency Injection
    @Autowired
    public SurveyTakingController(SurveyService surveyService, QuestionService questionService,
                                  OptionService optionService, ResultService resultService, UserService userService) {
        this.surveyService = surveyService;
        this.questionService = questionService;
        this.optionService = optionService;
        this.resultService = resultService;
        this.userService = userService;
    }


    @RequestMapping(value = "/survey/take", method = RequestMethod.GET)
    public String surveyViewGet(Model model) {
        model.addAttribute("surveys", surveyService.findAllReverseOrder());

        return "surveys/SurveyList";
    }

    @RequestMapping(value = "/survey/take/{surveyId}", method = RequestMethod.GET)
    public String surveyViewGet(@PathVariable Long surveyId, Model model) {
        Survey survey = surveyService.findOne(surveyId);
        ResultWrapper resultWrapper = new ResultWrapper();
        ArrayList<Question> questions = new ArrayList<Question>(new LinkedHashSet<Question>(survey.getQuestions()));
        for (int i = 0; i < questions.size(); i++) {
            resultWrapper.getIdHolders().add(new IdHolder());
            for (int j = 0; j < questions.get(i).getOptions().size(); j++) {
                resultWrapper.getIdHolders().get(i).getOptionIds().add((long) 0);
            }
        }

        model.addAttribute("survey", survey);
        model.addAttribute("questions", questions);
        model.addAttribute("ResultWrapper", resultWrapper);

        // Return the view
        return "surveys/SurveyTaker";
    }

    @RequestMapping(value = "/survey/take/{surveyId}", method = RequestMethod.POST)
    public String surveySubmit(@PathVariable Long surveyId, @ModelAttribute("ResultWrapper") ResultWrapper resultWrapper) {
        int sumPickedWeight = 0;
        for (int i = 0; i < resultWrapper.getIdHolders().size(); i++) {
            Result resultToSave = new Result();
            IdHolder currentIdHolder = resultWrapper.getIdHolders().get(i);
            Question question = questionService.findOne(currentIdHolder.getQuestionId());
            resultToSave.setQuestion(question);
            if (currentIdHolder.getText() != null) {
                resultToSave.setText(currentIdHolder.getText());
                resultService.save(resultToSave);
                continue;
            }
            ArrayList<Long> optionIds = currentIdHolder.getOptionIds();
            ArrayList<SelectedOption> selectedOptions = new ArrayList<SelectedOption>();

            boolean answeredCorrect = true;
            for (int j = 0; j < optionIds.size(); j++) {
                if (optionIds.get(j) == null) continue;

                Option option = optionService.findOne(optionIds.get(j));
                answeredCorrect = option.getIsCorrect() && answeredCorrect;

                SelectedOption selectedOption = new SelectedOption();
                selectedOption.setSelectedOptionText(option.getOptionText());
                selectedOption.setQuestionId(option.getQuestion().getId());
                selectedOption.setResult(resultToSave);
                selectedOptions.add(selectedOption);

                Long currentValue = question.getOptionCounts().get(option.getOptionText());
                question.getOptionCounts().put(option.getOptionText(), currentValue + 1);
                question.setTimesAnswered(question.getTimesAnswered() + 1);
                questionService.save(question);
            }
            if (answeredCorrect) sumPickedWeight += question.getWeight();
            //resultToSave.setQuestion(question);
            resultToSave.setSelectedOptions(selectedOptions);
            resultService.save(resultToSave);
        }
        return "redirect:/survey/submitted/" + surveyId + "/" + sumPickedWeight;
    }

    @RequestMapping(value = "/survey/submitted/{surveyId}/{sumPickedWeight}", method = RequestMethod.GET)
    public String surveySubmitView(@PathVariable Long surveyId, @PathVariable int sumPickedWeight, Model model) {
        model.addAttribute("survey",surveyService.findOne(surveyId));
        model.addAttribute("sumPickedWeight", sumPickedWeight);
        return "surveys/SubmitView";
    }
}
