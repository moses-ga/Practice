package io.kenzo.learning.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "users")
public class User extends BaseEntity {

    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 2, max = 255, message = "Name must be between 2 and 255 characters")
    @Column(nullable = false)
    private String name;

    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Email is not valid")
    @Size(max = 255)
    @Column(nullable = false, unique = true)
    private String email;

    @Size(max = 50, message = "Phone is too long")
    @Column(length = 50)
    private String phone;

    @Size(max = 255, message = "Address is too long")
    private String address;
}
