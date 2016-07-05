/**
 * Created by Stulli on 19/11/2015.
 */

package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import project.persistence.entities.*;
import project.service.*;

import java.util.ArrayList;
import java.util.LinkedHashSet;

@Controller
public class ResultController {

    // Instance variables
    ResultService resultService;
    UserService userService;
    QuestionService questionService;
    SurveyService surveyService;

    //  Dependency injections
    @Autowired
    public ResultController(ResultService resultService, UserService userService, SurveyService surveyService, QuestionService questionService) {
        this.resultService = resultService;
        this.userService = userService;
        this.surveyService = surveyService;
        this.questionService = questionService;
    }

    @RequestMapping(value = "/results/view", method = RequestMethod.GET)
    public String returnsResultView(Model model) {

        //  Return the view
        model.addAttribute("surveys", surveyService.findAll());

        return "surveys/SurveyResultList";
    }

    @RequestMapping(value = "/results/view/{surveyId}", method = RequestMethod.GET)
    public String returnsResultSurveyView(@PathVariable Long surveyId, Model model) {
        //  Return the view
        ArrayList<Question> questions = new ArrayList<Question>(
                new LinkedHashSet<Question>(
                        surveyService.findOne(surveyId).getQuestions()
                )
        );
        ArrayList<ArrayList<Result>> results = new ArrayList<ArrayList<Result>>();
        for (int i = 0; i < questions.size(); i++) {
            results.add(new ArrayList(questions.get(i).getResults()));
        }
        ArrayList<Long> totalAnswersPerQuestion = new ArrayList<Long>();
        for (int i = 0; i < results.size(); i++) {
            ArrayList<Result> currentQuestionResults = results.get(i);
            totalAnswersPerQuestion.add((long) currentQuestionResults.size());
        }
        model.addAttribute("questions", questions);
        model.addAttribute("totalAnswersPerQuestion", totalAnswersPerQuestion);
        return "surveys/ResultView";
    }

    @RequestMapping(value = "/allResults", method = RequestMethod.GET)
    public String returnsAllResultsView(Model model) {

        //  Return the view
        return "surveys/AllResultsView";
    }

    @RequestMapping(value = "/results/view/{surveyId}/{questionId}", method = RequestMethod.GET)
    public String returnsResultSurveyView(@PathVariable Long surveyId, @PathVariable Long questionId, Model model) {
        model.addAttribute("question", questionService.findOne(questionId));
        return "surveys/TextQuestionView";
    }
}
