package com.ms.store.controllers;

import com.ms.store.models.StoreModel;
import com.ms.store.services.StoreService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class StoreController {

    @Autowired
    private StoreService storeService;

    @GetMapping("/get/{storeId}")
    public ResponseEntity<Optional<StoreModel>> get(
            @PathVariable("storeId") UUID storeId
    ) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(storeService.get(storeId));
    }

    @GetMapping("/list")
    public ResponseEntity<List<StoreModel>> list() {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(storeService.list());
    }

    /*@PostMapping("/sendPurchase")
    public ResponseEntity<StoreModel>sendPurchase(
            @RequestBody @Valid ){

    }*/

}
