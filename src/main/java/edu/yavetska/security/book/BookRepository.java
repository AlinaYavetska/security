package edu.yavetska.security.book;

/*
@author   Admin
@project   security
@class  BookRepository
@version  1.0.0
@since 21.03.2025 - 16.25
*/

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {
}
