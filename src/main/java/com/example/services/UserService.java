package com.example.services;

import com.example.enums.Status;
import com.example.models.User;
import com.example.responses.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserService {

    private final MongoTemplate mongoTemplate;

    public UserService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public UserResponse createUser(User user) throws Exception {
        Query query = new Query(Criteria.where(User.Fields.userName).is(user.getUserName()));
        User existingUser = mongoTemplate.findOne(query, User.class);
        if(existingUser!=null){
            log.info(String.valueOf(user));
            throw new Exception("Username already exists");
        }
        User createdUser = mongoTemplate.save(user);
        return new UserResponse(createdUser, Status.SUCCESS, "");
    }

    public UserResponse getUser(String userName, String password) throws Exception {
        Criteria matchCriteria = new Criteria().andOperator(Criteria.where(User.Fields.userName).is(userName),
                Criteria.where(User.Fields.password).is(password)
                );

        Query query = new Query(matchCriteria);

        User result = mongoTemplate.findOne(query, User.class);
        if(result!=null){
            return new UserResponse(result, Status.SUCCESS, "");
        }
        throw new Exception("User Not Found");
    }
}
