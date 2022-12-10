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
@ToString(exclude = {"services","type"},includeFieldNames = false)
@Entity
@Table(name="supplies")
public class Supplies {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Supply name should not be empty")
    @Column(name = "name")
    @NonNull
    private String name;

    @ManyToOne
    @JoinColumn(name="type_id", referencedColumnName = "id")
    private TypeOfSupplies type;

    @OneToMany(mappedBy = "supplies")
    private List<Service> services;
}
