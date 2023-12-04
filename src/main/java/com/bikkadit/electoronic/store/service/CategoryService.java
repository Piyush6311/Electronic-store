package com.bikkadit.electoronic.store.service;

import com.bikkadit.electoronic.store.payload.CategoryDto;
import com.bikkadit.electoronic.store.payload.PageableResponse;

public interface CategoryService {
    //create
   CategoryDto create(CategoryDto categoryDto);
//update
   CategoryDto update (CategoryDto categorypto, String categoryId);

//delete
  void delete (String categoryId);


//getAll
    PageableResponse<CategoryDto> getAll(int pageNumber, int pagesize, String sortBy, String sortDir);

    //get single category detail
CategoryDto get(String categoryId);
//search:
}
