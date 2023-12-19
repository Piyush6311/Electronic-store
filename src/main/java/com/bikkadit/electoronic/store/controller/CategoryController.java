package com.bikkadit.electoronic.store.controller;

import com.bikkadit.electoronic.store.model.User;
import com.bikkadit.electoronic.store.payload.ApiResponse;
import com.bikkadit.electoronic.store.payload.CategoryDto;
import com.bikkadit.electoronic.store.payload.PageableResponse;
import com.bikkadit.electoronic.store.repository.UserRepository;
import com.bikkadit.electoronic.store.service.CategoryService;
import com.bikkadit.electoronic.store.serviceimpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/catagories")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @Autowired
    UserRepository userRepository;
    //create
    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCatagory(@RequestBody CategoryDto categoryDto){
        CategoryDto categoryDto1 = categoryService.create(categoryDto);
        return new ResponseEntity<>(categoryDto1, HttpStatus.CREATED);
    }

    //update
    @PutMapping("/{catagoryId}")
    public ResponseEntity<CategoryDto> upDateCategory(@RequestBody CategoryDto categoryDto, @PathVariable String categoryId){
        CategoryDto updatedCategory = categoryService.update(categoryDto, categoryId);
        return new ResponseEntity<>(updatedCategory, OK);
    }
   // delete

    @DeleteMapping("/{catagoryId}")
    public ResponseEntity<ApiResponse> delete(@PathVariable String categoryId){
        categoryService.delete(categoryId);
        ApiResponse responseMessage = ApiResponse.builder().message("Catagory deleted with id"+categoryId).build();
        return new ResponseEntity<> (responseMessage, HttpStatus. OK);
    }
    //get all
    @GetMapping
    public ResponseEntity<PageableResponse<CategoryDto>> getAll(
            @RequestParam (value="pageNumber", defaultValue="0", required= false) int pageNumber,
            @RequestParam (value="pageSize", defaultValue="0", required= false) int pageSize,
            @RequestParam (value="sortBy", defaultValue="title", required= false) String sortBy,
            @RequestParam (value="sortDir", defaultValue="asc", required= false) String sortDir){
      PageableResponse<CategoryDto> pageableResponse=  categoryService.getAll(pageNumber,pageSize,sortBy,sortDir);
      return new ResponseEntity<>(pageableResponse, OK);
    }
    //get single
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getSingle(@PathVariable String categoryId){
        CategoryDto categoryDto = categoryService.get(categoryId);
        return ResponseEntity.ok(categoryDto);


    }

}
