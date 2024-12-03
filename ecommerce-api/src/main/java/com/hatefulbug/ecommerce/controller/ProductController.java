package com.hatefulbug.ecommerce.controller;

import com.hatefulbug.ecommerce.dto.ProductDto;
import com.hatefulbug.ecommerce.model.Product;
import com.hatefulbug.ecommerce.request.AddProductRequest;
import com.hatefulbug.ecommerce.request.ProductUpdateRequest;
import com.hatefulbug.ecommerce.response.ApiResponse;
import com.hatefulbug.ecommerce.service.product.IProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/products")
@Tag(name = "Product")
public class ProductController {
    private final IProductService productService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        List<ProductDto> convertedProducts = productService.getConvertedProducts(products);
        return  ResponseEntity.ok(new ApiResponse("success", convertedProducts));
    }

    @GetMapping("product/{productId}")
    public ResponseEntity<ApiResponse> getProductById(@PathVariable Long productId) {
        Product product = productService.getProductById(productId);
        ProductDto productDto = productService.convertToDto(product);
        return  ResponseEntity.ok(new ApiResponse("success", productDto));
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody AddProductRequest product) {
        Product theProduct = productService.addProduct(product);
        ProductDto productDto = productService.convertToDto(theProduct);
        return ResponseEntity.ok(new ApiResponse("Add product success!", productDto));
    }

    @PutMapping("/product/{productId}/update")
    public  ResponseEntity<ApiResponse> updateProduct(@RequestBody ProductUpdateRequest request, @PathVariable Long productId) {
        Product theProduct = productService.updateProduct(request, productId);
        ProductDto productDto = productService.convertToDto(theProduct);
        return ResponseEntity.ok(new ApiResponse("Update product success!", productDto));
    }

    @DeleteMapping("/product/{productId}/delete")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long productId) {
        productService.deleteProductById(productId);
        return ResponseEntity.ok(new ApiResponse("Delete product success!", productId));
    }

    @GetMapping("/products/by/brand-and-name")
    public ResponseEntity<ApiResponse> getProductByBrandAndName(@RequestParam String brandName, @RequestParam String productName) {
        List<Product> products = productService.getProductsByBrandAndName(brandName, productName);
        List<ProductDto> convertedProducts = productService.getConvertedProducts(products);
        return  ResponseEntity.ok(new ApiResponse("success", convertedProducts));
    }

    @GetMapping("/products/by/category-and-brand")
    public ResponseEntity<ApiResponse> getProductByCategoryAndBrand(@RequestParam String category, @RequestParam String brand){
        List<Product> products = productService.getProductsByCategoryAndBrand(category, brand);
        List<ProductDto> convertedProducts = productService.getConvertedProducts(products);
        return  ResponseEntity.ok(new ApiResponse("success", convertedProducts));
    }

    @GetMapping("/products/{name}")
    public ResponseEntity<ApiResponse> getProductByName(@PathVariable String name){
        List<Product> products = productService.getProductsByName(name);
        List<ProductDto> convertedProducts = productService.getConvertedProducts(products);
        return  ResponseEntity.ok(new ApiResponse("success", convertedProducts));
    }

    @GetMapping("/product/by-brand")
    public ResponseEntity<ApiResponse> findProductByBrand(@RequestParam String brand) {
        List<Product> products = productService.getProductsByBrand(brand);
        List<ProductDto> convertedProducts = productService.getConvertedProducts(products);
        return  ResponseEntity.ok(new ApiResponse("success", convertedProducts));
    }

    @GetMapping("/product/{category}/all")
    public ResponseEntity<ApiResponse> findProductByCategory(@PathVariable String category) {
        List<Product> products = productService.getProductsByCategory(category);
        List<ProductDto> convertedProducts = productService.getConvertedProducts(products);
        return  ResponseEntity.ok(new ApiResponse("success", convertedProducts));
    }

    @GetMapping("/product/count/by-brand/and-name")
    public ResponseEntity<ApiResponse> countProductsByBrandAndName(@RequestParam String brand, @RequestParam String name) {
        var productCount = productService.countProductsByBrandAndName(brand, name);
        return ResponseEntity.ok(new ApiResponse("Product count!", productCount));
    }
}