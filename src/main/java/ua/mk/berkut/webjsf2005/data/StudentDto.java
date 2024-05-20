package ua.mk.berkut.webjsf2005.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentDto {
    private Integer id;
    private String name;
    private Integer age;
    private String facultyName;
}
