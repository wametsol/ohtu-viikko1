package ohtu.services;

import ohtu.domain.User;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import ohtu.data_access.UserDao;

public class AuthenticationService {

    private UserDao userDao;

    public AuthenticationService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean logIn(String username, String password) {
        for (User user : userDao.listAll()) {
            if (user.getUsername().equals(username)
                    && user.getPassword().equals(password)) {
                return true;
            }
        }

        return false;
    }

    public boolean createUser(String username, String password) {
        if (userDao.findByName(username) != null) {
            return false;
        }

        if (!invalid(username, password)) {
            return false;
        }

        userDao.add(new User(username, password));

        return true;
    }

    private boolean invalid(String username, String password) {
        if (3>username.length() || 8>password.length()){
            return false;
        }
        if (userDao.findByName(username) != null) {
            return false;
        }
        
        if(!Pattern.matches("[a-z]+", username)){
            return false;
        }

        int spec = 0;
        int num = 0;
        for(int i=0;i<password.length();i++){
            char c =password.charAt(i);
            
            if(!Character.isLetterOrDigit(c)||Character.isDigit(c)){
                spec++;
            }
        }

        // validity check of username and password
        

        return spec != 0;
    }
}
