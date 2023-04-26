package com.andiv.example.springdataelasticsearch.entity;

import com.andiv.example.springdataelasticsearch.event.UserSavedEvent;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;
import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.data.domain.DomainEvents;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.andiv.example.springdataelasticsearch.entity.User.INDEX_NAME;

@Entity
@Table
@Document(indexName = INDEX_NAME)
@Data
public class User /*extends AbstractAggregateRoot<User>*/ {

    public final static String INDEX_NAME = "search_user";
    @Transient
    private final List<UserSavedEvent> events = new ArrayList<>();

    @Id
    @org.springframework.data.annotation.Id
    Integer id;

    @Field(type = FieldType.Text)
    String username;

    @Field(type = FieldType.Text)
    String firstName;

    @Field(type = FieldType.Text)
    String lastName;

    @Field(type = FieldType.Text)
    String email;

    public User save() {
        //registerEvent(UserSavedEvent.builder().user(this).build());
        events.add(UserSavedEvent.builder().username(this.getUsername()).build());
        return this;
    }

    @DomainEvents
    public Collection<UserSavedEvent> events() {
        return events;
    }

    @AfterDomainEventPublication
    public void clearEvents() {
        events.clear();
    }
}
