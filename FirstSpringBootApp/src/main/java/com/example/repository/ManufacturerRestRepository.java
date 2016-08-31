package com.example.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.model.Manufacturer;

@RepositoryRestResource(path="/datarest")
public interface ManufacturerRestRepository extends MongoRepository<Manufacturer, Integer> {

}
