package by.alexeysavchic.voter_pet_project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "polls")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Poll
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "question")
    @Size(min = 2, max = 100, message = "question must be between 2 and 100 symbols")
    private String question;

    @Column(name = "description")
    @Size(max = 300, message = "description must be less than 300 symbols")
    private String description;

    @Column(name = "creationTime")
    private LocalDateTime creationTime;

    @Column(name = "endingTime")
    private LocalDateTime endingTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User createdBy;

    @OneToMany(mappedBy = "poll", cascade = CascadeType.ALL)
    private List<Option> options;

    public boolean isActive()
    {
        if (LocalDateTime.now().isBefore(endingTime))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
