package com.ms.user.repositories;

import com.ms.user.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID> {

    List<UserModel> findByEmail(String email);

    Optional<UserModel> findByPassword(String password);

    Optional<UserModel> findByName(String name);
}
