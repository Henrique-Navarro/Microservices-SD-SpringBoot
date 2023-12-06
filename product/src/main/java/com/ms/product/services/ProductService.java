package com.ms.product.services;

import com.ms.product.dtos.BuyDto;
import com.ms.product.enums.StatusProduct;
import com.ms.product.models.ProductModel;
import com.ms.product.producers.ProductUserProducer;
import com.ms.product.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductUserProducer productUserProducer;


    @Transactional
    public ProductModel save(
            ProductModel productModel
    ) {
        productModel.setStatusProduct(StatusProduct.DISPONIVEL);
        productModel = productRepository.save(productModel);

        //productStoreProducer.publishProductMessage(productModel);

        return productModel;
    }

    @Transactional
    public ProductModel buy(
            UUID productId, UUID userId
    ) {
        Optional<ProductModel> productOptional = get(productId);

        if (productOptional.isPresent()) {
            productOptional.get().setUserId(userId);
            ProductModel product = productOptional.get();

            var buyDto = new BuyDto();
            buyDto.setUserId(product.getUserId());
            buyDto.setProductId(product.getProductId());

            productUserProducer.publishProductMessage(buyDto);

            return save(product);

        } else {
            return null;
        }
    }

    public List<ProductModel> list(
    ) {
        return productRepository.findAll();
    }

    public Optional<ProductModel> get(
            UUID productId
    ) {
        return productRepository.findById(productId);
    }

    public boolean delete(
            UUID productId
    ) {
        Optional<ProductModel> optionalProduct = productRepository.findById(productId);

        if (optionalProduct.isPresent()) {
            productRepository.delete(optionalProduct.get());
            return true;
        }
        return false;
    }
}
