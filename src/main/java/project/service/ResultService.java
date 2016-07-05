package project.service;

import project.persistence.entities.Result;

import java.util.List;

public interface ResultService {

    /**
     * Save a {@link Result}
     * @param result {@link Result} to be saved
     * @return {@link Result} that was saved
     */
    Result save(Result result);

    /**
     * Delete {@link Result}
     * @param result {@link Result} to be deleted
     */
    void delete(Result result);

    /**
     * Get all {@link Result}s
     * @return A list of {@link Result}s
     */
    List<Result> findAll();

    /**
     * Get all {@link Result}s in a reverse order
     * @return A reversed list of {@link Result}s
     */
    List<Result> findAllReverseOrder();

    /**
     * Find a {@link Result} based on {@link Long id}
     * @param id {@link Long}
     * @return A {@link Result} with {@link Long id}
     */
    Result findOne(Long id);

    /**
     * Find all {@link Result}s with {@link String name}
     * @param userId {@link Long}
     * @return All {@link Result}s with the {@link Long userId} passed
     */
    List<Result> findByUserId(Long userId);
}
