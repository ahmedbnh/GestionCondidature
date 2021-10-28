package com.candidature.application.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.candidature.application.entities.Candidature;

@Repository
public interface CandidatureRepository extends MongoRepository<Candidature, String> {

}
