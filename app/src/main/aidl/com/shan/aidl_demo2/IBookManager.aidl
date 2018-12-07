package com.shan.aidl_demo2;
import com.shan.aidl_demo2.Book;

interface IBookManager{
    List<Book> getBookList();
    void addBook(in Book book);
}