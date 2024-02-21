package net.xusage.springboot.registration.service;

import jakarta.transaction.Transactional;
import net.xusage.springboot.registration.entity.UserSchema;
import net.xusage.springboot.registration.dao.UserDao;
import net.xusage.springboot.registration.utils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean create(UserSchema user){
        boolean isSaved = false;

        // encode password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user = userDao.save(user);
        if (null != user && user.getId() > 0) {
            isSaved = true;
        }
        return isSaved;
    }

    @Transactional
    public void update(UserSchema user){
        UserSchema userSchema = userDao.findById(user.getId().intValue()).get();
        if (user.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        BeanUtils.copyNonNullProperties(user, userSchema);
    }

    public UserSchema findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    public boolean passwordMatches(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
