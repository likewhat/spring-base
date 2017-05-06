package io.github.likewhat.springbase.service.impl;


import io.github.likewhat.springbase.model.AbstractAuditingEntity;
import io.github.likewhat.springbase.service.BaseService;

import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;


abstract class BaseServiceImpl<T extends AbstractAuditingEntity, PK extends Serializable> implements BaseService<T, PK> {

    /**
     * Get repository for model.
     *
     * @return The repository of model
     */
    abstract CrudRepository<T, PK> getRepository();

    /**
     * Get model or throw exception with given id.
     *
     * @param id The id used to get model
     * @return The model which id is given id
     */
    abstract T getOrThrow(PK id);

    @Override
    public T findOne(PK id) {
        return getRepository().findOne(id);
    }

    private T save(T entity) {
        return getRepository().save(entity);
    }

    @Override
    public void delete(PK id, Long deleter) {
        T model = getOrThrow(id);
        model.markDeleted(deleter);
        save(model);
    }

    @Override
    public T create(T model, Long creator) {
        model.markCreated(creator);
        return save(model);
    }

    @Override
    public T update(T model, Long modifier) {
        model.markModified(modifier);
        return save(model);
    }
}
