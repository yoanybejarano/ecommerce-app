package com.hatefulbug.ecommerce.controller;

import com.hatefulbug.ecommerce.model.Category;
import com.hatefulbug.ecommerce.response.ApiResponse;
import com.hatefulbug.ecommerce.service.category.ICategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/categories")
@Tag(name = "Category")
public class CategoryController {

    private final ICategoryService categoryService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        return  ResponseEntity.ok(new ApiResponse("Found!", categories));
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<ApiResponse> getCategoryById(@PathVariable Long id){
        Category theCategory = categoryService.getCategoryById(id);
        return  ResponseEntity.ok(new ApiResponse("Found", theCategory));
    }

    @GetMapping("/category/name/{name}")
    public ResponseEntity<ApiResponse> getCategoryByName(@PathVariable String name){
        Category theCategory = categoryService.getCategoryByName(name);
        return  ResponseEntity.ok(new ApiResponse("Found", theCategory));
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addCategory(@RequestBody Category name) {
        Category theCategory = categoryService.addCategory(name);
        return  ResponseEntity.ok(new ApiResponse("Success", theCategory));
    }

    @PutMapping("/category/{id}/update")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable Long id, @RequestBody Category category) {
        Category updatedCategory = categoryService.updateCategory(category, id);
        return ResponseEntity.ok(new ApiResponse("Update success!", updatedCategory));
    }

    @DeleteMapping("/category/{id}/delete")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long id){
        categoryService.deleteCategoryById(id);
        return  ResponseEntity.ok(new ApiResponse("Found", null));
    }


}