package by.alexeysavchic.voter_pet_project.service;

import by.alexeysavchic.voter_pet_project.dto.request.ChangeCredentialsRequest;
import by.alexeysavchic.voter_pet_project.dto.request.GetUsersRequest;
import by.alexeysavchic.voter_pet_project.dto.response.GetUserResponse;
import by.alexeysavchic.voter_pet_project.entity.User;
import by.alexeysavchic.voter_pet_project.exception.OperationDeniedException;
import by.alexeysavchic.voter_pet_project.exception.UserNotFoundException;
import by.alexeysavchic.voter_pet_project.exception.WrongPasswordException;
import by.alexeysavchic.voter_pet_project.mapper.UserMapper;
import by.alexeysavchic.voter_pet_project.repository.UserRepository;
import by.alexeysavchic.voter_pet_project.security.Role;
import by.alexeysavchic.voter_pet_project.security.SecurityContextService;
import by.alexeysavchic.voter_pet_project.serviceInterfaces.UserService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService
{

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final SecurityContextService securityContextService;


    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder, SecurityContextService securityContextService)
    {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.securityContextService = securityContextService;
    }

    @Override
    public GetUserResponse findUserById(Long id)
    {
        User user = userRepository.findUserById(id).orElseThrow(()->
                new UsernameNotFoundException("User not found with username: " + id));

        GetUserResponse getUserResponse=userMapper.userToGetUserResponse(user);

        return getUserResponse;
    }

    @Override
    public List<GetUserResponse> getUsers(GetUsersRequest request)
    {
        Specification<User> specification=null;
        if (request.getId()!=null)
        {
            specification=specification.and(getIdSpecification(request));
        }
        if (request.getUsername()!=null)
        {
            specification=specification.and(getUsernameSpecification(request));
        }
        if (request.getUsername()!=null)
        {
            specification=specification.and(getEmailSpecification(request));
        }
        if(specification==null)
        {
            return userMapper.ListUsersToListGetUsersResponse(userRepository.findAll());
        }
        else
        {
            return userMapper.ListUsersToListGetUsersResponse(userRepository.findAll(specification));
        }
    }

    @Override
    public GetUserResponse changeCredentials(ChangeCredentialsRequest request)
    {
        User user = userRepository.findUserById(securityContextService.getCurrentUser().getId()).orElseThrow(()->
                new UserNotFoundException());

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());

        if (passwordEncoder.matches(request.getOldPassword(), user.getPassword()))
        {
            user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        }
        else
        {
            throw new WrongPasswordException();
        }

        userRepository.save(user);

        return userMapper.userToGetUserResponse(user);
    }

    @Override
    public void deleteUser(Long id)
    {
        User user = userRepository.findUserById(id).orElseThrow(()->
                new UserNotFoundException());

        if ((securityContextService.getCurrentUser().getRoles().contains(Role.ROLE_ADMIN))||
        securityContextService.getCurrentUser().equals(user))
        {
            userRepository.delete(user);
        }
        else
        {
            throw new OperationDeniedException();
        }
    }

    private Specification<User> getIdSpecification(GetUsersRequest request)
    {
        return (root, query, criteriaBuilder) ->
        {return criteriaBuilder.equal(root.get("id"),
                request.getId());
        };
    }

    private Specification<User> getUsernameSpecification(GetUsersRequest request)
    {
        return (root, query, criteriaBuilder) ->
        {return criteriaBuilder.like(root.get("username"),
                "%"+request.getUsername()+"%");
        };
    }

    private Specification<User> getEmailSpecification(GetUsersRequest request)
    {
        return (root, query, criteriaBuilder) ->
        {return criteriaBuilder.like(root.get("email"),
                "%"+request.getEmail()+"%");
        };
    }


}
