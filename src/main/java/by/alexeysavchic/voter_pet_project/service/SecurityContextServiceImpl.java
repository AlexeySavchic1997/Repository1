package by.alexeysavchic.voter_pet_project.service;

import by.alexeysavchic.voter_pet_project.entity.User;
import by.alexeysavchic.voter_pet_project.repository.UserRepositoy;
import by.alexeysavchic.voter_pet_project.security.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

public class SecurityContextServiceImpl implements SecurityContextService {
    private final UserRepositoy userRepositoy;

    public SecurityContextServiceImpl(UserRepositoy userRepositoy) {
        this.userRepositoy = userRepositoy;
    }

    @Override
    @Transactional
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated())
        {
            return null;
        }
        Object principal = authentication.getPrincipal();
        if(principal instanceof CustomUserDetails)
        {
            return ((CustomUserDetails)principal).getUser();
        }
        return null;
        }
    }
