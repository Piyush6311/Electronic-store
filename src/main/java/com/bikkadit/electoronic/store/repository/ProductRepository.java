package com.bikkadit.electoronic.store.repository;

import com.bikkadit.electoronic.store.model.Category;
import com.bikkadit.electoronic.store.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,String> {

    Page<Product> findByCategory(Category category, PageRequest pages);

    Page<Product> findByTitleContaining(PageRequest pages, String keyword);

    Page<Product> findByLiveTrue(PageRequest pages);
}
