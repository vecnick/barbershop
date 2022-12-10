package mirea.barbershop.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Data
@Entity
@Table(name="Person")
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "логин не должен быть пустым")
    @Size(min=2,max=100, message = "Имя должно быть от 2 до 100 символов")
    @Column(name = "username")
    @NonNull
    private String username;

    @Column(name = "year_of_birth")
    @Min(value = 1900, message = "Не врите, вам не 120 лет")
    @NonNull
    private int year;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;
}
