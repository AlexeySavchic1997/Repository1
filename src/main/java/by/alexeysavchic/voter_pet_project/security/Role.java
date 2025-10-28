package by.alexeysavchic.voter_pet_project.security;

public enum Role
{
    ROLE_USER,
    ROLE_ADMIN,

    ROLE_MODERATOR;

    public String getAuthority()
    {
        return this.name();
    }
}
