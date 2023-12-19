package com.bikkadit.electoronic.store.service;
import com.bikkadit.electoronic.store.payload.PageableResponse;
import com.bikkadit.electoronic.store.payload.ProductDto;

public interface ProductService {
    public ProductDto saveProduct(ProductDto productDto);

    public ProductDto getSingleProduct(String productId);

    public ProductDto updateProduct(ProductDto productDto, String productId);

    public PageableResponse<ProductDto> getAllProducts(Integer pageNumber, Integer pageSize, String sortBy, String direction);

    public PageableResponse<ProductDto> getAllLiveProducts(Integer pageNumber, Integer pageSize, String sortBy, String direction);

    public PageableResponse<ProductDto> findProductsByTitle(Integer pageNumber, Integer pageSize, String sortBy, String direction, String keyword);

    public ProductDto createProductWithCategory(ProductDto productDto, String categoryId);

    public PageableResponse<ProductDto> getAllOfCategory(String categoryId, Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    public ProductDto updateCategory(String productId, String categoryId);

    public void deleteProduct(String productId);

    PageableResponse<ProductDto> findByLiveTrue(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
}
