package by.alexeysavchic.voter_pet_project.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Entity
@Table(name = "options")
@Getter
@Setter
@NoArgsConstructor
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

    public Option(String text, Poll poll, List<Vote> votes) {
        this.text = text;
        this.poll = poll;
        this.votes = votes;
    }
}
