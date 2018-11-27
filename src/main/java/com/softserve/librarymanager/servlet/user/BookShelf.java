package com.softserve.librarymanager.servlet.user;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.softserve.librarymanager.model.Book;
import com.softserve.librarymanager.service.BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/bookshelf")
@Singleton
public class BookShelf extends HttpServlet {
    @Inject
    private BookService bookService;

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Book> books = bookService.findAll();
        request.setAttribute("books", books);
        request.setAttribute("bookShelfName", "All books");
        request.getRequestDispatcher("/view/user/Bookshelves.jsp").forward(request, response);
    }
}
