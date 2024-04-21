package com.toratax.repository;

import com.toratax.model.domain.TorataxUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TorataxUserRepository extends CrudRepository<TorataxUser,Long> {

    TorataxUser findByUserName(String userName);
}
