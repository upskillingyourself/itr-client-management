package com.toratax.repository;

import com.toratax.model.domain.PermanentDocument;
import com.toratax.model.domain.UserPermanentData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermanentDocumentsRepository extends CrudRepository<PermanentDocument, Integer> {
    PermanentDocument findByUserPermanentDataAndDocumentTypeId(UserPermanentData userPermanentData, int documentTypeId);
}
