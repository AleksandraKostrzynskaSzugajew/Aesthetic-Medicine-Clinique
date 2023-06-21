package pl.alekosszu.KME.service;



public interface UserService {

    User findByUserName(String name);

    void saveUser(User user);
}