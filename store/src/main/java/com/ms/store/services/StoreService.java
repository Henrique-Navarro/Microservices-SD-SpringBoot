package com.ms.store.services;

import com.ms.store.models.StoreModel;
import com.ms.store.producers.StoreEmailProducer;
import com.ms.store.repositories.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private StoreEmailProducer storeEmailProducer;

    @Transactional
    public StoreModel finish(StoreModel storeModel) {
        storeEmailProducer.publishEmailMessage(storeModel);
        return storeRepository.save(storeModel);
    }

    public Optional<StoreModel> get(UUID storeId) {
        return storeRepository.findById(storeId);
    }

    public List<StoreModel> list() {
        return storeRepository.findAll();
    }

    public StoreModel sendPurchase() {
        return null;
    }
}
