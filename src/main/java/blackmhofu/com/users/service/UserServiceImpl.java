package blackmhofu.com.users.service;

import blackmhofu.com.organisation.model.Organisation;
import blackmhofu.com.organisation.service.OrganisationServiceImpl;
import blackmhofu.com.users.dto.UserReqDTO;
import blackmhofu.com.users.dto.UserResDTO;
import blackmhofu.com.users.dto.UserUpdateReqDTO;
import blackmhofu.com.users.mapper.UserMapper;
import blackmhofu.com.users.model.User;
import blackmhofu.com.users.repository.UserRepository;
import blackmhofu.com.utils.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private OrganisationServiceImpl organisationService;

    @Override
    public UserResDTO    saveUser(UserReqDTO userReqDTO) {

        Organisation foundOrganisation = null;
        if (userReqDTO.getOrganisationId() != null) {
            foundOrganisation = organisationService.findById(userReqDTO.getOrganisationId());
        }


        User userToBeSaved = User
                .builder()
                .email(userReqDTO.getEmail())
                .name(userReqDTO.getName())
                .password(userReqDTO.getPassword()) // needs hashing
                .phoneNumber(userReqDTO.getPhoneNumber())
                .address(userReqDTO.getAddress())
                .status(userReqDTO.getStatus())
                .notes(userReqDTO.getNotes())
                .role(userReqDTO.getRole())
                .organisation(foundOrganisation)
                .build();

        User savedUser = userRepository.save(userToBeSaved);

        return userMapper.toDTO(savedUser);
    }

    @Override
    public User findById(UUID userId) {

        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with id [ %S ] not found ".formatted(userId)));
    }

    @Override
    public UserResDTO findById(String userId) {
        UUID uuid = UUID.fromString(userId);

        User foundUser = findById(uuid);

        return userMapper.toDTO(foundUser);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findUserByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User with email  [ %S ] not found ".formatted(email)));

    }

    @Override
    public String update(UserUpdateReqDTO updateReqDTO) {
        return null;
    }

    @Override
    public String delete(UUID userId) {

        User foundUser = findById(userId);
        if (foundUser != null) {
            userRepository.deleteById(userId);
            return "User with id  [ %s ] Deleted Successfully ".formatted(userId.toString());
        }

        return "Failed to  delete user with id  [ %s ]".formatted(userId.toString());
    }

    @Override
    public ArrayList<UserResDTO> findAll() {
        List<User> userList = userRepository.findAll();
        List<UserResDTO> collect = userList.stream().map(user -> userMapper.toDTO(user)).collect(Collectors.toList());
        return (ArrayList<UserResDTO>) collect;
    }

    @Override
    public ArrayList<UserResDTO> findByOrganisationId(UUID organisationId) {

        List<User> userList = userRepository.findByOrganisationId(organisationId);
        List<UserResDTO> collect = userList.stream().map(user -> userMapper.toDTO(user)).collect(Collectors.toList());
        return (ArrayList<UserResDTO>) collect;

    }
}
