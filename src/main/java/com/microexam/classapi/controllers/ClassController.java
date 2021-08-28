package com.microexam.classapi.controllers;

import com.microexam.classapi.dto.ClassDTO;
import com.microexam.classapi.entities.Class;
import com.microexam.classapi.services.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/classes")
public class ClassController {
    @Autowired
    private ClassService classService;
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Class> fetchAllClasses(){
        return classService.fetchAllClasses();
    }
    @GetMapping(value = "id/{classId}")
    @ResponseStatus(HttpStatus.OK)
    public Class fetchClassById(@PathVariable int classId){
        return classService.fetchClassById(classId);
    }
    @GetMapping(value = "name/{className}")
    @ResponseStatus(HttpStatus.OK)
    public List<Class> fetchClassesByName(@PathVariable String className){
        return classService.fetchClassByName(className);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createClass(@RequestBody @Valid ClassDTO classDTO){
        classService.createClass(classDTO);
    }
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateClass(@RequestBody @Valid ClassDTO classDTO, @PathVariable("id") int classId){
        classService.updateClass(classDTO, classId);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteClass(@PathVariable("id") int classId){
        classService.deleteClass(classId);
    }
}
