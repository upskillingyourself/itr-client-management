package com.toratax.repository;

import com.toratax.model.domain.DocumentType;
import com.toratax.model.domain.UserPermanentData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentTypeRepository  extends CrudRepository<DocumentType, Integer> {
}
