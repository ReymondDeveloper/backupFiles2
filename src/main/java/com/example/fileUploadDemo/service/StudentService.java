package com.example.fileUploadDemo.service;

import com.example.fileUploadDemo.model.Student;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Set;

public interface StudentService {
    public Integer uploadFile (MultipartFile multipartFile) throws IOException;

    Set<Student> parseCsv(MultipartFile file) throws IOException;
}
