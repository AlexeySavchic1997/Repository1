package by.alexeysavchic.voter_pet_project.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "polls")
public class Poll
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "question")
    private String question;

    @Column(name = "description")
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

    public Poll() {
    }

    public Poll(String question, String description, LocalDateTime creationTime, LocalDateTime endingTime, User createdBy, List<Option> options) {
        this.question = question;
        this.description = description;
        this.creationTime = creationTime;
        this.endingTime = endingTime;
        this.createdBy = createdBy;
        this.options = options;
    }

    public Option getOption(String text)
    {
        for (Option option:options)
        {
            if (option.getText().equals(text))
            {
                return option;
            }
        }
        return null;
    }

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public LocalDateTime getEndingTime() {
        return endingTime;
    }

    public void setEndingTime(LocalDateTime endingTime) {
        this.endingTime = endingTime;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Poll poll)) return false;
        return getId() == poll.getId() && getQuestion().equals(poll.getQuestion()) && getDescription().equals(poll.getDescription()) && getCreationTime().equals(poll.getCreationTime())
                && getEndingTime().equals(poll.getEndingTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getQuestion(), getDescription(), getCreationTime(), getEndingTime(), getCreatedBy(), getOptions());
    }
}
