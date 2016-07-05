package project.service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.persistence.entities.Result;
import project.persistence.repositories.ResultRepository;
import project.service.ResultService;

import java.util.Collections;
import java.util.List;

@Service
public class ResultServiceImplementation implements ResultService {

    // Instance Variables
    ResultRepository repository;

    // Dependency Injection
    @Autowired
    public ResultServiceImplementation(ResultRepository repository) {
        this.repository = repository;
    }

    @Override
    public Result save(Result result) {
        return repository.save(result);
    }

    @Override
    public void delete(Result result) {
        repository.delete(result);
    }

    @Override
    public List<Result> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Result> findAllReverseOrder() {
        // Get all the Postit notes
        //List<Result> results = repository.findAll();

        // Reverse the list
        //Collections.reverse(results);
        List<Result> results = repository.findAllByOrderByIdDesc();

        return results;
    }

    @Override
    public Result findOne(Long id) {
        return repository.findOne(id);
    }

    @Override
    public List<Result> findByUserId(Long userId) { return repository.findByUserId(userId); }
}
