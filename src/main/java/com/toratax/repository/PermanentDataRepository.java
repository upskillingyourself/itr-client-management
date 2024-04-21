package com.toratax.repository;

import com.toratax.model.domain.UserPermanentData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermanentDataRepository extends CrudRepository<UserPermanentData, Integer> {
    UserPermanentData findByUserId(String userId);
}
