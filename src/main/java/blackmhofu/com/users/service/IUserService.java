package blackmhofu.com.users.service;

import blackmhofu.com.auth.dto.LoginReq;
import blackmhofu.com.auth.dto.LoginRes;
import blackmhofu.com.users.dto.UserReqDTO;
import blackmhofu.com.users.dto.UserResDTO;
import blackmhofu.com.users.dto.UserUpdateReqDTO;
import blackmhofu.com.users.model.User;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

public interface IUserService {

    public UserResDTO saveUser(UserReqDTO userReqDTO);
    public UserResDTO addCustomer(UserReqDTO userReqDTO);

    public User findById (UUID userId); // for linking with other tables
    public Optional< User> findByName (String userName);
    public UserResDTO findById (String userId);  // sending to the client
    public User findByEmail (String  email);  // for authentication

    public String update(UserUpdateReqDTO updateReqDTO);

    public  String delete( UUID userId);

    public ArrayList<UserResDTO> findAll();

    ArrayList<UserResDTO> findByAgentId(UUID agentId); // an agent is the creator of those clients


    public LoginRes login(LoginReq loginReq);
}
