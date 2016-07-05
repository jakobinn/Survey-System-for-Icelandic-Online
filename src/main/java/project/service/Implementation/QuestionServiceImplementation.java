package project.service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.persistence.entities.Question;
import project.persistence.repositories.QuestionRepository;
import project.service.QuestionService;

import java.util.Collections;
import java.util.List;

@Service
public class QuestionServiceImplementation implements QuestionService {

    // Instance Variables
    QuestionRepository repository;

    // Dependency Injection
    @Autowired
    public QuestionServiceImplementation(QuestionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Question save(Question question) {
        return repository.save(question);
    }

    @Override
    public void delete(Question question) {
        repository.delete(question);
    }

    @Override
    public List<Question> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Question> findAllReverseOrder() {
        // Get all the Postit notes
        List<Question> questions = repository.findAll();

        // Reverse the list
        Collections.reverse(questions);

        return questions;
    }

    @Override
    public Question findOne(Long id) {
        return repository.findOne(id);
    }
}
