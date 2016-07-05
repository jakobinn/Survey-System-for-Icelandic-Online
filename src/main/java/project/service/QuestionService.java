package project.service;

import project.persistence.entities.Question;

import java.util.List;

public interface QuestionService {

    /**
     * Save a {@link Question}
     * @param question {@link Question} to be saved
     * @return {@link Question} that was saved
     */
    Question save(Question question);

    /**
     * Delete {@link Question}
     * @param question {@link Question} to be deleted
     */
    void delete(Question question);

    /**
     * Get all {@link Question}s
     * @return A list of {@link Question}s
     */
    List<Question> findAll();

    /**
     * Get all {@link Question}s in a reverse order
     * @return A reversed list of {@link Question}s
     */
    List<Question> findAllReverseOrder();

    /**
     * Find a {@link Question} based on {@link Long id}
     * @param id {@link Long}
     * @return A {@link Question} with {@link Long id}
     */
    Question findOne(Long id);

}
