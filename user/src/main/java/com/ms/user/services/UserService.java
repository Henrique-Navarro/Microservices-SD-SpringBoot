package com.ms.user.services;

import com.ms.user.dtos.BuyDto;
import com.ms.user.dtos.StoreDto;
import com.ms.user.models.StoreModel;
import com.ms.user.models.UserModel;
import com.ms.user.producers.UserProducer;
import com.ms.user.producers.UserStoreProducer;
import com.ms.user.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserStoreProducer userStoreProducer;
    @Autowired
    private UserProducer userProducer;

    @Transactional
    public UserModel save(UserModel userModel) {
        userModel = userRepository.save(userModel);
        userProducer.publishMessageEmail(userModel);
        return userModel;
    }

    public UserModel buyProduct(BuyDto buyDto) {
        UUID productId = buyDto.getProductId();
        UUID userId = buyDto.getUserId();

        Optional<UserModel> userOptional = get(userId);

        if (userOptional.isPresent()) {

            UserModel user = userOptional.get();
            //System.out.println(user.getUserId() + " " + user.getEmail() + " " + user.getName());

            List<UUID> productsUser = user.getProductsId();
            List<UUID> productsList = new ArrayList<>();
            productsList = productsUser;
            productsList.add(productId);

            user.setProductsId(productsList);

            return save(user);

        } else {
            return null;
        }
    }

    public StoreModel finish(
            StoreDto storeDto
    ) {
        var storeModel = new StoreModel();
        BeanUtils.copyProperties(storeDto, storeModel);

        Optional<UserModel> user = get(storeModel.getUserID());

        if (user.isPresent()) {
            storeModel.setProductsId(user.get().getProductsId());

            storeModel.setQtd_products(user.get().getProductsId().size());

            storeModel.setTotalValue(0.0f);

            storeModel.setUserEmail(user.get().getEmail());
        }

        userStoreProducer.publishStoreMessage(storeModel);
        return storeModel;
    }

    public boolean delete(UUID userId) {
        Optional<UserModel> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            userRepository.delete(userOptional.get());
            return true;
        }
        return false;
    }

    public List<UserModel> list() {
        return userRepository.findAll();
    }

    public Optional<UserModel> get(UUID userId) {

        return userRepository.findById(userId);
    }

    /*public boolean isEqual(LoginModel loginModel) {
        try {
            List<UserModel> userOptional = userRepository.findByEmail(loginModel.getEmail());

            UserModel user = userOptional.get(0);

            return user.getPassword().equals(loginModel.getPassword()) && user.getName().equals(loginModel.getName()) ? true : false;
        } catch (Exception e) {
            return false;
        }
    }*/
}
