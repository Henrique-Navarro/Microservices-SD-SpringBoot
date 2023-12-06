package com.ms.user.controllers;

import com.ms.user.dtos.StoreDto;
import com.ms.user.dtos.UserRecordDto;
import com.ms.user.dtos.UserUpdateDto;
import com.ms.user.models.StoreModel;
import com.ms.user.models.UserModel;
import com.ms.user.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public ResponseEntity<UserModel> saveUser(
            @RequestBody @Valid UserRecordDto userRecordDto
    ) {

        var userModel = new UserModel();
        BeanUtils.copyProperties(userRecordDto, userModel);

        UserModel savedUser = userService.save(userModel);

        HttpStatus status = HttpStatus.BAD_REQUEST;
        if (savedUser != null)
            status = HttpStatus.CREATED;

        return ResponseEntity
                .status(status)
                .body(savedUser);
    }

    @GetMapping("/list")
    public ResponseEntity<List<UserModel>> listUsers() {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.list());
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity<Optional<UserModel>> getUser(
            @PathVariable("userId") UUID userId
    ) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.get(userId));
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<UserModel> updateUser(
            @PathVariable("userId") UUID userId,
            @RequestBody @Valid UserUpdateDto userUpdateDto
    ) {
        Optional<UserModel> userOptional = userService.get(userId);

        if (userOptional.isPresent()) {
            UserModel existingUser = userOptional.get();
            BeanUtils.copyProperties(userUpdateDto, existingUser);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(userService.save(existingUser));

        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/finish")
    public ResponseEntity<StoreModel> finish(
            @RequestBody @Valid StoreDto storeDto
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.finish(storeDto));
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<Void> deleteUser(
            @PathVariable("userId") UUID userId
    ) {
        if (userService.delete(userId)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
