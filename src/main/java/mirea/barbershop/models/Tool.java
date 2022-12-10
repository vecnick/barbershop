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
@Table(name="tool")
public class Tool {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Tool name should not be empty")
    @Column(name = "name")
    @NonNull
    private String name;

    @Column(name = "network")
    @NonNull
    private String network;

    @ManyToMany
    @JoinTable(
            name = "tool_to_service",
            joinColumns = @JoinColumn(name = "tool_id"),
            inverseJoinColumns = @JoinColumn(name="service_id")
    )
    private List<Service> services;
}
