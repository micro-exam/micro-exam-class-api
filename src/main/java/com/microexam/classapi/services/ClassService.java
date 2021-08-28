package com.microexam.classapi.services;

import com.microexam.classapi.dto.ClassDTO;
import com.microexam.classapi.entities.Class;

import java.util.List;
import java.util.Optional;

public interface ClassService {
    public void createClass(ClassDTO classDTO);
    public void updateClass(ClassDTO classDTO, int classId);
    public void deleteClass(int classId);
    public List<Class> fetchAllClasses();
    public Class fetchClassById(int classId);
    public List<Class> fetchClassByName(String className);
}
