package com.example.fileUploadDemo.service;

import com.example.fileUploadDemo.model.Student;
import com.example.fileUploadDemo.repository.StudentRepository;
import com.example.fileUploadDemo.studentCSVRepresent.StudentCsvRepresentation;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StudentServicelmpl implements StudentService{

     final StudentRepository studentRepository;
    @Autowired
    public StudentServicelmpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    @Override
    public Integer uploadFile(MultipartFile multipartFile) throws IOException {
// Finally, the method returns the size of the studentSet, indicating how many student records were processed.
        Set<Student> studentSet = parseCsv(multipartFile);
        studentRepository.saveAll(studentSet);
        return studentSet.size();
    }
    @Override
    public Set<Student> parseCsv(MultipartFile file) throws IOException {
        try(Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            HeaderColumnNameMappingStrategy<StudentCsvRepresentation> strategy =
                    new HeaderColumnNameMappingStrategy<>();
            strategy.setType(StudentCsvRepresentation.class);
            CsvToBean<StudentCsvRepresentation> csvToBean =
                    new CsvToBeanBuilder<StudentCsvRepresentation>(reader)
                            .withMappingStrategy(strategy)
                            .withIgnoreEmptyLine(true)
                            .withIgnoreLeadingWhiteSpace(true)
                            .build();
            return csvToBean.parse()
                    .stream()
                    .map(csvLine -> Student.builder()
                            .firstname(csvLine.getFname())
                            .lastname(csvLine.getLname())
                            .age(csvLine.getAge())
                            .build()
                    )
                    .collect(Collectors.toSet());
        }
    }


}
