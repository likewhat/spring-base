package io.github.likewhat.springbase.repository;

import io.github.likewhat.springbase.model.Email;

import org.springframework.data.repository.CrudRepository;


public interface EmailRepository extends CrudRepository<Email, Long> {
}
