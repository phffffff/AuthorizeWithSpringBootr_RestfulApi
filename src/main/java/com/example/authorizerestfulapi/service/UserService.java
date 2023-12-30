package com.example.authorizerestfulapi.service;

import com.example.authorizerestfulapi.entity.Users;
import com.example.authorizerestfulapi.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

@Service
public class UserService extends AbsService<Users>{
    @Autowired
    private UserRepo userRepo;

    @Override
    public List<Users> getAll() {
        List<Users> users = userRepo.findAll();
        if (users == null){
            return null;
        }
        return users;
    }

    @Override
    public Optional<Users> get(int id) {
        Optional<Users> user = userRepo.findById(id);
        if(user.isEmpty()){
            return null;
        }
        return user;
    }

    @Override
    public Users add(Users u) {
        if (u == null){
            return null;
        }
        Optional<Users> ou = userRepo.findUsersByUsernameAndEmail(u.getUsername(), u.getEmail());
        if (!ou.isEmpty()){
            return null;
        }
        u.setStatus(true);
        Users newUser = userRepo.save(u);
        if (newUser == null){
            return null;
        }
        return newUser;
    }

    @Override
    public Users update(int id, Users user) {
        Optional<Users> ou = userRepo.findById(id);
        if(ou.isEmpty()){
            return null;
        }
        Users u = ou.get();
        // Sử dụng reflection để lấy danh sách trường
        Field[] fields = Users.class.getDeclaredFields();

        for (Field field : fields) {
            try {
                field.setAccessible(true);
                Object updatedValue = field.get(user);

                // Kiểm tra và cập nhật nếu giá trị mới khác null, 0, và rỗng
                if (updatedValue != null && !updatedValue.equals("") && !updatedValue.equals(0)) {
                    field.set(u, updatedValue);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace(); // Xử lý lỗi nếu có
            }
        }

        return userRepo.save(u);
    }

    @Override
    public boolean delete(int id) {
        Users u = userRepo.findById(id).get();
        if(u == null){
            return false;
        }
        userRepo.deleteById(u.getId());
        return true;
    }
}
