package io.github.likewhat.springbase.service;


import io.github.likewhat.springbase.model.AbstractAuditingEntity;


public interface BaseService<T extends AbstractAuditingEntity, PK> {

    /**
     * Find model with given ID
     *
     * @param id The id used to find model
     * @return The mode which is is given id or null
     */
    T findOne(PK id);

    /**
     * Delete model with given id.
     *
     * @param id      The id used to delete model
     * @param deleter The current user
     */
    void delete(PK id, Long deleter);

    /**
     * Create a model.
     *
     * @param model   The model used to create a new model
     * @param creator The current user
     * @return The created model
     */
    T create(T model, Long creator);

    /**
     * Update a model.
     *
     * @param model    The model used to update.
     * @param modifier The current user
     * @return The modified model
     */
    T update(T model, Long modifier);
}
