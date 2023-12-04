package com.bikkadit.electoronic.store.serviceimpl;

import com.bikkadit.electoronic.store.exception.ResourceNotFoundException;
import com.bikkadit.electoronic.store.helper.Helper;
import com.bikkadit.electoronic.store.model.Category;
import com.bikkadit.electoronic.store.payload.CategoryDto;
import com.bikkadit.electoronic.store.payload.PageableResponse;
import com.bikkadit.electoronic.store.repository.CategoryRepository;
import com.bikkadit.electoronic.store.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    private ModelMapper mapper;

    @Override
    public CategoryDto create(CategoryDto categoryDto) {
        Category category = mapper.map(categoryDto, Category.class);

        Category savedCategory = categoryRepository.save(category);
        return mapper.map(savedCategory, CategoryDto.class);
    }

    @Override
    public CategoryDto update(CategoryDto categoryDto, String categoryId) {
        //get catagory of given id


        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found exception!!"));

        //update category details
        category.setTitle (categoryDto.getTitle());
        category.setDescription(categoryDto.getDescription());
        category.setCoverImage(categoryDto.getCoverImage());
        Category updatedCategory = categoryRepository.save(category);
        return mapper.map(updatedCategory,CategoryDto.class);
    }

    @Override
    public void delete(String categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Catagory not Exist!!"));
        categoryRepository.delete(category);


    }



    @Override
    public PageableResponse<CategoryDto> getAll(int pageNumber, int pagesize, String sortBy, String sortDir) {
        Sort sort =(sortDir.equalsIgnoreCase("desc")) ? (Sort.by (sortBy).descending()): (Sort.by (sortBy).ascending());
        Pageable pageable = PageRequest.of(pageNumber,pagesize);
        Page<Category> page = categoryRepository.findAll(pageable);
        PageableResponse<CategoryDto> pageableResponse = Helper.getPageableResponse(page, CategoryDto.class);
        return pageableResponse;
    }

    @Override
    public CategoryDto get(String categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found exception!!"));
return mapper.map(category,CategoryDto.class);
    }
}
