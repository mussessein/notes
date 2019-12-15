package controller;

public class LoginServiceImpl implements LoginServiceInterface{

    @Override
    public String login(String username, String password) {

        return username+" loggend in successfully";
    }
}
