package com.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookstore.model.BookType;

@Repository
public interface BookTypeRepository extends JpaRepository<BookType, Long>{

}
