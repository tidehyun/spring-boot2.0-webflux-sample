package net.chuisk.demo.model;

import lombok.*;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Document(collection = "person")
public class Person {
    @Id
    private String id;
    @Email(message = "Please check Email")
    @NotEmpty(message = "Please check Email")
    private String email;
    @NotEmpty(message = "Please check Name")
    private String name;
    @Range(min = 0, max = 10, message = "Please check Age")
    private int age;
}
