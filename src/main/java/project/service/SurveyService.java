package project.service;

import project.persistence.entities.Survey;

import java.util.List;

public interface SurveyService {

    /**
     * Save a {@link Survey}
     * @param survey {@link Survey} to be saved
     * @return {@link Survey} that was saved
     */
    Survey save(Survey survey);

    /**
     * Delete {@link Survey}
     * @param survey {@link Survey} to be deleted
     */
    void delete(Survey survey);

    /**
     * Get all {@link Survey}s
     * @return A list of {@link Survey}s
     */
    List<Survey> findAll();

    /**
     * Get all {@link Survey}s in a reverse order
     * @return A reversed list of {@link Survey}s
     */
    List<Survey> findAllReverseOrder();

    /**
     * Find a {@link Survey} based on {@link Long id}
     * @param id {@link Long}
     * @return A {@link Survey} with {@link Long id}
     */
    Survey findOne(Long id);

    /**
     * Find all {@link Survey}s with {@link String name}
     * @param name {@link String}
     * @return All {@link Survey}s with the {@link String name} passed
     */
    List<Survey> findByName(String name);

}
