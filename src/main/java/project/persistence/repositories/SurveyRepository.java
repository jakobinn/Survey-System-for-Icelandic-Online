package project.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import project.persistence.entities.Survey;

import java.util.List;

/**
 * By extending the {@link JpaRepository} we have access to powerful methods.
 * For detailed information, see:
 * http://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/CrudRepository.html
 * http://docs.spring.io/spring-data/data-commons/docs/1.6.1.RELEASE/reference/html/repositories.html
 *
 */
public interface SurveyRepository extends JpaRepository<Survey, Long> {

    Survey save(Survey survey);

    void delete(Survey survey);

    List<Survey> findAll();

    List<Survey> findAllByOrderByIdDesc();

    Survey findOne(Long id);

    List<Survey> findByName(String name);
}
