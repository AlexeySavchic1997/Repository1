package by.alexeysavchic.voter_pet_project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Tolerate;

import java.util.List;

@Entity
@Table(name = "options")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Option
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @EqualsAndHashCode.Include
    private long id;

    @Column(name = "text")
    @EqualsAndHashCode.Include
    private String text;

    @ManyToOne
    @JoinColumn(name = "poll_id")
    @EqualsAndHashCode.Include
    private Poll poll;

    @OneToMany(mappedBy = "option", cascade = CascadeType.REMOVE)
    private List<Vote> votes;
}
