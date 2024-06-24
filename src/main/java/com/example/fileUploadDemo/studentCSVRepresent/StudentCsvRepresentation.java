package com.example.fileUploadDemo.studentCSVRepresent;

import com.opencsv.bean.CsvBindByName;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class StudentCsvRepresentation {
    @CsvBindByName(column = "firstname")
    private String fname;
    @CsvBindByName(column = "lastname")
    private String lname;
    @CsvBindByName(column = "age")
    private int age;

}
