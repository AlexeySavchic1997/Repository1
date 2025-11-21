package by.alexeysavchic.voter_pet_project.entity;


import by.alexeysavchic.voter_pet_project.security.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @EqualsAndHashCode.Include
    private long id;

    @Column(name = "username", nullable = false, unique = true)
    @Size(min = 2, max = 30, message = "username must be between 2 and 30 symbols")
    @EqualsAndHashCode.Include
    private String username;

    @Column(name="password", nullable = false)
    @Size(min = 6, max = 15, message = "password must be between 6 and 15 symbols")
    private String password;

    @Column(name = "email", unique = true)
    @Email(message = "wrong email pattern")
    @EqualsAndHashCode.Include
    private String email;


    @OneToMany(mappedBy ="user", cascade = CascadeType.REMOVE)
    private List<Vote> votes;

    @OneToMany(mappedBy = "createdBy", cascade = CascadeType.REMOVE)
    private List<Poll> createdPolls;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>();

    public User(String username, String password, String email, List<Vote> votes, List<Poll> createdPolls) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.votes = votes;
        this.createdPolls = createdPolls;
    }

    public void addVote(Vote vote)
    {
        votes.add(vote);
    }

    public void addRole(Role role) { this.roles.add(role); }
    public void removeRole(Role role) { this.roles.remove(role); }
    public boolean hasRole(Role role) { return this.roles.contains(role); }
}
