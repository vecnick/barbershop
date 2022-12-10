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
@ToString(exclude = {"client","barber","supplies","tools"},includeFieldNames = false)
@Entity
@Table(name="service")
public class Service {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Tool name should not be empty")
    @Column(name = "name")
    @NonNull
    private String name;

    @Column(name = "price")
    @NonNull
    private Integer price;

    @ManyToOne
    @JoinColumn(name="client_id", referencedColumnName = "id")
    private Client client;

    @ManyToOne
    @JoinColumn(name="barber_id", referencedColumnName = "id")
    private Barber barber;

    @ManyToOne
    @JoinColumn(name="supplies_id", referencedColumnName = "id")
    private Supplies supplies;

    @ManyToMany(mappedBy = "services")
    private List<Tool> tools;
}
