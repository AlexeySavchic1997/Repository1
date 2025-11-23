package by.alexeysavchic.voter_pet_project.security;

import by.alexeysavchic.voter_pet_project.entity.User;

public interface SecurityContextService
{
    public User getCurrentUser();
}
