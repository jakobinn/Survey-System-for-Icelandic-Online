package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;
import project.persistence.entities.Survey;
import project.persistence.entities.Question;
import project.persistence.entities.Option;
import project.service.StringManipulationService;
import project.service.SurveyService;
import project.service.QuestionService;
import project.service.OptionService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.HashSet;

@Controller
public class SurveyEditingController {

    // Instance Variables
    SurveyService surveyService;
    QuestionService questionService;
    OptionService optionService;
    StringManipulationService stringManipulationService = new StringManipulationService();

    // Dependency Injection
    @Autowired
    public SurveyEditingController(SurveyService surveyService, QuestionService questionService, OptionService optionService) {
        this.surveyService = surveyService;
        this.questionService = questionService;
        this.optionService = optionService;
    }

    @RequestMapping(value = "/survey", method = RequestMethod.GET)
    public String surveyViewGet(Model model){
        model.addAttribute("survey",new Survey());
        model.addAttribute("surveys", surveyService.findAllReverseOrder());

        // Return the view
        return "surveys/SurveyCreator";
    }

    @RequestMapping(value = "/survey", method = RequestMethod.POST)
    public String surveyViewPost(@ModelAttribute("survey") Survey survey){
        surveyService.save(survey);
        return "redirect:/survey/" +survey.getId();
    }

    @RequestMapping(value = "/optionType", method = RequestMethod.GET)
    public String optionTypeChooser(){
        return "surveys/optionType";
    }

    @RequestMapping(value = "/optionType", method = RequestMethod.POST)
    public String optionTypeChooser2(){
        return "redirect:/optionType";
    }

    @RequestMapping(value = "/survey/{surveyId}", method = RequestMethod.GET)
    public String surveyViewQuestions(@PathVariable Long surveyId,
                                             Model model) {
        Survey survey = surveyService.findOne(surveyId);

        model.addAttribute("survey", survey);
        model.addAttribute("questions", new HashSet<Question>(survey.getQuestions()));
        model.addAttribute("question", new Question());

        return "surveys/SurveyEditor";
    }

    @RequestMapping(value = "/survey/surveyedit/delete/{surveyId}", method = RequestMethod.POST)
    public String SurveyEditorDeleteSurvey(@PathVariable Long surveyId){
        Survey survey = surveyService.findOne(surveyId);
        surveyService.delete(survey);

        return "redirect:/survey";
    }

    @RequestMapping(value = "/survey/surveyedit/{surveyId}", method = RequestMethod.POST)
    public String SurveyEditorPostQuestion(@PathVariable Long surveyId, @ModelAttribute("question")
                                           Question question) {
        Survey survey = surveyService.findOne(surveyId);
        survey.addQuestion(question);
        survey.setTotalWeight(survey.getTotalWeight()+question.getWeight());
        surveyService.save(survey);

        return "redirect:/survey/"+surveyId;
    }

    @RequestMapping(value = "/survey/surveyedit/delete/{surveyId}/{questionId}", method = RequestMethod.POST)
    public String SurveyEditorDeleteQuestion(@PathVariable Long surveyId, @PathVariable Long questionId){
        Question questionToDelete = questionService.findOne(questionId);
        Survey survey = surveyService.findOne(surveyId);
        survey.setTotalWeight(survey.getTotalWeight()-questionToDelete.getWeight());
        survey.getQuestions().remove(questionToDelete);
        surveyService.save(survey);

        return "redirect:/survey/"+surveyId;
    }

    @RequestMapping(value = "/survey/surveyedit/{surveyId}/{questionId}", method = RequestMethod.GET)
    public String SurveyEditorViewQuestion(@PathVariable Long surveyId, @PathVariable Long questionId,
                                           Model model) {
        Question question = questionService.findOne(questionId);
        model.addAttribute("question", question);
        model.addAttribute("option", new Option());

        return "surveys/QuestionEditor";
    }

    @RequestMapping(value = "/survey/surveyedit/{surveyId}/{questionId}", method = RequestMethod.POST)
    public String SurveyEditorPostOption(@PathVariable Long surveyId, @PathVariable Long questionId,
                                           @ModelAttribute("option") Option option) {
        Question question = questionService.findOne(questionId);
        question.addOption(option);
        question.getOptionCounts().put(option.getOptionText(),(long)0);
        questionService.save(question);
        return "redirect:/survey/surveyedit/"+surveyId+"/"+questionId;
    }

    @RequestMapping(value = "/survey/surveyedit/delete/{surveyId}/{questionId}/{optionId}", method = RequestMethod.POST)
    public String SurveyEditorDeleteOption(@PathVariable Long surveyId, @PathVariable Long questionId,
                                           @PathVariable Long optionId) {
        Option optionToDelete = optionService.findOne(optionId);
        Question question = questionService.findOne(questionId);
        question.getOptions().remove(optionToDelete);
        questionService.save(question);

        return "redirect:/survey/surveyedit/"+surveyId+"/"+questionId;
    }

    @RequestMapping(value = "/survey/surveyedit/predoptions/{surveyId}/{questionId}", method = RequestMethod.POST)
    public String SurveyEditorPostChosenOptions(@RequestParam String optionText,
                                                @PathVariable Long surveyId, @PathVariable Long questionId) {

        Question question = questionService.findOne(questionId);
        question.choosePredeterminedOptions(optionText);
        questionService.save(question);

        return "redirect:/survey/surveyedit/"+surveyId+"/"+questionId;
    }

}
