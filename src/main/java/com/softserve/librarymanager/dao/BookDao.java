package com.softserve.librarymanager.dao;

import com.softserve.librarymanager.model.Author;
import com.softserve.librarymanager.model.Book;

import java.util.List;

public interface BookDao extends Dao<Book> {

    List<Book> findAllBooksByAuthorId(int authorId);

    List<Book> findTenLatestBooks();

    void saveBookAuthor(Author author, Book book);
}
