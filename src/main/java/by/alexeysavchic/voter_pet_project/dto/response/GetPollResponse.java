package by.alexeysavchic.voter_pet_project.dto.response;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class GetPollResponse
{
    private long id;

    private String question;

    private String description;

    private LocalDateTime creationTime;

    private LocalDateTime endingTime;

    private GetUserResponse createdBy;

    private List<String> options;
}
