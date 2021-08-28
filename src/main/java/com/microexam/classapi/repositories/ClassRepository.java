package com.microexam.classapi.repositories;

import com.microexam.classapi.entities.Class;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ClassRepository extends CrudRepository<Class, Integer> {
    List<Class> findByclassName(String className);
}
