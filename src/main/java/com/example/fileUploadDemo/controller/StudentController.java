package com.example.fileUploadDemo.controller;

import com.example.fileUploadDemo.service.StudentServicelmpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    final StudentServicelmpl studentServicelmpl;
    @Autowired
    public StudentController(StudentServicelmpl studentServicelmpl) {
        this.studentServicelmpl = studentServicelmpl;
    }

    @PostMapping(value = "/upload",consumes = {"multipart/form-data"})
    public ResponseEntity<Integer> uploadFile (@RequestPart("file")MultipartFile file) throws IOException {
        return ResponseEntity.ok(studentServicelmpl.uploadFile(file));
    }


}
