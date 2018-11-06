package net.chuisk.demo.model;

import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Person {
    @Email(message = "Please check Email")
    @NotEmpty(message = "Please check Email")
    private String email;
    @NotEmpty(message = "Please check Name")
    private String name;
    @Range(min = 0, max = 10, message = "Please check Age")
    private int age;
}
