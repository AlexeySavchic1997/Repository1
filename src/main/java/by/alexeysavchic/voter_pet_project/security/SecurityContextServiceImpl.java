package by.alexeysavchic.voter_pet_project.security;

import by.alexeysavchic.voter_pet_project.entity.User;
import by.alexeysavchic.voter_pet_project.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SecurityContextServiceImpl implements SecurityContextService {
    private final UserRepository userRepository;

    public SecurityContextServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
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
