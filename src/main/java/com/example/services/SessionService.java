package com.example.services;

import com.example.enums.SessionStatus;
import com.example.models.Session;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class SessionService {
    private final MongoTemplate mongoTemplate;

    public SessionService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public Session createSession(String userId){
        //delete existing active sessions
        deleteExistingSessions(userId);

        String sessionId = UUID.randomUUID().toString();
        LocalDateTime startTime = LocalDateTime.now();
        Session session = new Session(sessionId, startTime,null, SessionStatus.ACTIVE, userId);
        Session savedResponse = mongoTemplate.save(session);

        return savedResponse;
    }

    public Session getSession(String userId){
        Criteria criteria = new Criteria().andOperator(
                Criteria.where(Session.Fields.hostId).is(userId),
                Criteria.where(Session.Fields.sessionStatus).is(SessionStatus.ACTIVE)
        );
        Query query = new Query(criteria);

        Session session = mongoTemplate.findOne(query, Session.class);
        return session;
    }

    public void deleteExistingSessions(String userId){
        Criteria criteria = new Criteria().andOperator(Criteria.where(Session.Fields.hostId).is(userId),
                Criteria.where(Session.Fields.sessionStatus).is(SessionStatus.ENDED));

        Query query = new Query(criteria);

        Update update = new Update().set(Session.Fields.sessionStatus, SessionStatus.ENDED)
                .set(Session.Fields.end, LocalDateTime.now());

        mongoTemplate.updateMulti(query, update, Session.class);
    }
}
