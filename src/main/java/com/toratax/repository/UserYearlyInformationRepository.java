package com.toratax.repository;

import com.toratax.model.domain.UserYearlyInformation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserYearlyInformationRepository extends CrudRepository<UserYearlyInformation, Integer> {
    UserYearlyInformation findByUserId(String userId);
    UserYearlyInformation findByUserIdAndYearlyFolderPath(String userId, String folderPath);
}
