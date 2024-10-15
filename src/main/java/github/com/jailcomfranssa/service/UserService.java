package github.com.jailcomfranssa.service;


import github.com.jailcomfranssa.model.User;

public interface UserService {

    public User findUserByJwtToken(String jwt) throws Exception;
    public User findUserByEmail(String email) throws Exception;
}
