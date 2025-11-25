package by.alexeysavchic.voter_pet_project.dto.request;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GetUsersRequest
{
    FilterUserRequest filterUserRequest;

    String condition;
}
