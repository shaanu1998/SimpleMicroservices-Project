package com.project.userservice.service;

import com.project.userservice.VO.Department;
import com.project.userservice.VO.ResponseTemplateVO;
import com.project.userservice.entity.User;
import com.project.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public ResponseTemplateVO getUserWithDepartment(long userId) {
        ResponseTemplateVO vo = new ResponseTemplateVO();
        User user = userRepository.findByUserId(userId);
        Department department= restTemplate.getForObject( "http://DEPARTMENT-SERVICE/departments/" + user.getDepartmentId()
                , Department.class);

        vo.setUser(user);
        vo.setDepartment(department);

        return vo;


    }
}
