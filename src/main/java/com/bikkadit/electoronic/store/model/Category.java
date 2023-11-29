package com.bikkadit.electoronic.store.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="categories")
public class Category {
    @Id
    @Column(name="id")
    private String categoryId;
    @Column(name="category_title",length=60)
    private String title;
    @Column(name="category_desc",length=50)
    private String description;

    private String coverImage;
}
