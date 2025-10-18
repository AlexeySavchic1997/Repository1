package by.alexeysavchic.voter_pet_project.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public class PollResponse
{
    private long id;

    private String question;

    private String description;

    private LocalDateTime creationTime;

    private LocalDateTime endingTime;

    private UserResponse createdBy;

    private List<OptionResponse> options;
}
