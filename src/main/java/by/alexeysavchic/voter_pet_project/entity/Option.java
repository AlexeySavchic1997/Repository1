package by.alexeysavchic.voter_pet_project.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "options")
public class Option
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "text")
    private String text;

    @ManyToOne
    @JoinColumn(name = "poll_id")
    private Poll poll;

    @OneToMany(mappedBy = "option")
    private List<Vote> votes;

    public Option() {
    }

    public Option(String text, Poll poll, List<Vote> votes) {
        this.text = text;
        this.poll = poll;
        this.votes = votes;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Poll getPoll() {
        return poll;
    }

    public void setPoll(Poll poll) {
        this.poll = poll;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }
}
