package mirea.barbershop.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@NoArgsConstructor
@Data
@ToString(exclude = {"services"},includeFieldNames = false)
@Entity
@Table(name="barber")
public class Barber {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Barber name should not be empty")
    @Column(name = "name")
    @NonNull
    private String name;

    @NotEmpty(message = "Client surname should not be empty")
    @Column(name = "second_name")
    @NonNull
    private String secondName;

    @Column(name = "patronymic")
    @NonNull
    private String patronymic;

    @Column(name = "telephone_number")
    @NonNull
    private String telephoneNumber;

    @OneToMany(mappedBy = "barber")
    private List<Service> services;
}
