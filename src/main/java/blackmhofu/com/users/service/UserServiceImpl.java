package blackmhofu.com.users.service;

import blackmhofu.com.auth.dto.LoginReq;
import blackmhofu.com.auth.dto.LoginRes;
import blackmhofu.com.auth.service.JwtService;

import blackmhofu.com.auth.utils.CurrentLoggedInUser;
import blackmhofu.com.users.dto.UserReqDTO;
import blackmhofu.com.users.dto.UserResDTO;
import blackmhofu.com.users.dto.UserUpdateReqDTO;
import blackmhofu.com.users.mapper.UserMapper;
import blackmhofu.com.users.model.User;
import blackmhofu.com.users.repository.UserRepository;
import blackmhofu.com.utils.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;



    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private CurrentLoggedInUser currentLoggedInUser;


    @Override
    public UserResDTO    saveUser(UserReqDTO userReqDTO) {



        User userToBeSaved = User
                .builder()
                .email(userReqDTO.getEmail())
                .name(userReqDTO.getName())
                .password(passwordEncoder.encode(userReqDTO.getPassword()))

                .phoneNumber(userReqDTO.getPhoneNumber())
                .address(userReqDTO.getAddress())
                .status(userReqDTO.getStatus())
                .notes(userReqDTO.getNotes())
                .role(userReqDTO.getRole())

                .build();

        User savedUser = userRepository.save(userToBeSaved);

        return userMapper.toDTO(savedUser);
    }

    @Override
    public UserResDTO addCustomer(UserReqDTO userReqDTO) {

        User currentLoginUser = currentLoggedInUser.getCurrentLoginUser();

        User userToBeSaved = User
                .builder()
                .email(userReqDTO.getEmail())
                .name(userReqDTO.getName())
                .password(passwordEncoder.encode(userReqDTO.getPassword()))
                .whoseCustomer(currentLoginUser)
                .phoneNumber(userReqDTO.getPhoneNumber())
                .address(userReqDTO.getAddress())
                .status(userReqDTO.getStatus())
                .notes(userReqDTO.getNotes())
                .role(userReqDTO.getRole())

                .build();

        User savedUser = userRepository.save(userToBeSaved);

        return userMapper.toDTO(savedUser);



    }

    @Override
    public User findById(UUID userId) {

        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with id [ %S ] not found ".formatted(userId)));
    }

    @Override
    public Optional<User> findByName(String userName) {
        return userRepository.findUserByName(userName);
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

        return null;

    }

    @Override
    public LoginRes login(LoginReq loginReq) {

//        System.out.println(loginReq.toString());

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginReq.getUserName(), loginReq.getPassword()));
        if (authentication.isAuthenticated()) {

            // get the user profile and user details
            User foundUser = findByEmail(loginReq.getUserName());

            UserResDTO userResDTO = userMapper.toDTO(foundUser);

            var token =  jwtService.generateToken(loginReq.getUserName() ,userResDTO);
            return  LoginRes.builder()
                    .token(token)
                    .build();

        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }

    }
}
