package com.ms.product.controllers;

import com.ms.product.dtos.BuyDto;
import com.ms.product.dtos.ProductRecordDto;
import com.ms.product.dtos.ProductUpdateDto;
import com.ms.product.models.ProductModel;
import com.ms.product.services.ProductService;
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
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/products")
    public ResponseEntity<ProductModel> saveProduct(
            @RequestBody @Valid ProductRecordDto productRecordDto
    ) {

        var productModel = new ProductModel();
        BeanUtils.copyProperties(productRecordDto, productModel);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productService.save(productModel));
    }

    @GetMapping("/list")
    public ResponseEntity<List<ProductModel>> listProducts(
    ) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productService.list());
    }

    @GetMapping("/get/{productId}")
    public ResponseEntity<Optional<ProductModel>> getProduct(
            @PathVariable("productId") UUID productId
    ) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productService.get(productId));
    }

    @PutMapping("/update/{productId}")
    public ResponseEntity<ProductModel> updateProduct(
            @PathVariable("productId") UUID productId,
            @RequestBody @Valid ProductUpdateDto productUpdateDto
    ) {
        Optional<ProductModel> productOptional = productService.get(productId);

        if (productOptional.isPresent()) {
            ProductModel existingProduct = productOptional.get();
            BeanUtils.copyProperties(productUpdateDto, existingProduct);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(productService.save(existingProduct));

        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<Void> deleteProduct(
            @PathVariable("productId") UUID productId
    ) {
        if (productService.delete(productId)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/buy")
    public ResponseEntity<ProductModel> buyProduct(
            @RequestBody @Valid BuyDto buyDto
    ) {
        //System.out.println(buyDto.getProductId() + " " + buyDto.getUserId());

        ProductModel boughtProduct = productService.buy(
                buyDto.getProductId(),
                buyDto.getUserId());

        if (boughtProduct != null) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(boughtProduct);
        } else {
            return ResponseEntity
                    .notFound()
                    .build();
        }
    }
}
