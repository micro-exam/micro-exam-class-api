package com.microexam.classapi.services;

import com.microexam.classapi.dto.ClassDTO;
import com.microexam.classapi.entities.Class;
import com.microexam.classapi.exceptions.ClassException;
import com.microexam.classapi.exceptions.ClassNotFoundException;
import com.microexam.classapi.repositories.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClassServiceImpl implements ClassService{

    @Autowired
    private ClassRepository classRepository;

    @Override
    public void createClass(ClassDTO classDTO) {
        Class classObj= new Class();
        classObj.setClassName(classDTO.getClassName());
        classObj.setClassDescription((classDTO.getClassDescription()));
        classRepository.save(classObj);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void updateClass(ClassDTO classDTO, int classId) {
        Optional<Class> classObj = classRepository.findById(classId);
        if(classObj.isPresent()){
            classObj.get().setClassDescription(classDTO.getClassDescription());
            classObj.get().setClassName(classDTO.getClassName());
        }
        else{
            throw new ClassNotFoundException("Class with id " + classId + " does not exist");
        }
    }

    @Override
    public void deleteClass(int classId) {
        Optional<Class> classObj = classRepository.findById(classId);
        if(classObj.isPresent()){
            classRepository.delete(classObj.get());
        }
        else{
            throw new ClassNotFoundException("Class with id " + classId + " does not exist");
        }
    }

    @Override
    public List<Class> fetchAllClasses() {
        List<Class> classes = new ArrayList<>();
        classRepository.findAll().forEach(classes::add);
        if(classes.size() > 0){
            return classes;
        }
        else{
            throw new ClassException("No classes were found!");
        }
    }

    @Override
    public Class fetchClassById(int classId) {
        Optional<Class> classObj = classRepository.findById(classId);
        if(classObj.isPresent()){
            return classObj.get();
        }
        else{
            throw new ClassNotFoundException("Class with id " + classId + " does not exist");
        }
    }

    @Override
    public List<Class> fetchClassByName(String className) {
        List<Class> classes = classRepository.findByclassName(className);
        if(classes.size() > 0) {
            return classes;
        }
        else{
            throw new ClassException("No " + className + " classes were found!");
        }
    }
}
