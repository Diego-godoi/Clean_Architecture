package com.diego.cleanArch.infra.persistence.entity;

import com.diego.cleanArch.core.domain.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "users")
public class UserJpaEntity {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String email;
    private String password;

    public UserJpaEntity() {
    }
    public UserJpaEntity(User user){
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
    }

    public User toDomain(){
        return new User(this.id, this.name, this.password, this.email);
    }
}
