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
@ToString(exclude = {"supplies"},includeFieldNames = false)
@Entity
@Table(name="type_of_supplies")
public class TypeOfSupplies {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "name should not be empty")
    @Column(name = "name")
    @NonNull
    private String name;

    @OneToMany(mappedBy = "type")
    private List<Supplies> supplies;
}
