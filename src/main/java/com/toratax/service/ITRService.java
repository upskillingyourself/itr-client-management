package com.toratax.service;

import com.toratax.model.dto.ItrDetails;
import com.toratax.model.dto.ItrRequest;
import com.toratax.model.dto.TorataxItrDetails;

public interface ITRService {

    TorataxItrDetails requestITR(ItrRequest itrRequest);

}
