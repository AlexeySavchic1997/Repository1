package by.alexeysavchic.voter_pet_project.dto.request;


import by.alexeysavchic.voter_pet_project.entity.Option;
import by.alexeysavchic.voter_pet_project.entity.User;

import java.util.List;

public class PollRequset
{
    private String question;

    private String description;

    private List<Option> options;
}
