package com.shan.aidl_demo2;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MyAidlService extends Service {
    private final String TAG = this.getClass().getSimpleName();

    private ArrayList<Book> mBooks;


    private BookManagerImpl bookManager = new BookManagerImpl(){
        @Override
        public List<Book> getBookList() throws RemoteException {
            return mBooks;
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            mBooks.add(book);
        }
    };


    private IBinder mIBinder = new IBookManager.Stub() {
        @Override
        public List<Book> getBookList() throws RemoteException {
            return mBooks;
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            mBooks.add(book);
        }
    };


    public MyAidlService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        mBooks = new ArrayList<>();
        Log.d(TAG, "MyAidlService onBind");
        return bookManager;
    }
}
