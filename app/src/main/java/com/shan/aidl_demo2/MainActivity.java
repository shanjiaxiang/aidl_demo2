package com.shan.aidl_demo2;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button bt1, bt2;
    TextView mText;
    private Book book;



    private IIBookManager manager;
    private ServiceConnection mConnect = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            manager = BookManagerImpl.asInterface(iBinder);
//            manager = IBookManager.Stub.asInterface(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            manager = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt1 = (Button)findViewById(R.id.button_1);
        bt2 = (Button)findViewById(R.id.button_2);
        mText = (TextView)findViewById(R.id.aidl_text);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    manager.addBook(new Book(1, "helloworld"));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    book = manager.getBookList().get(0);
                    mText.setText(book.bookName);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        Intent intent = new Intent(getApplicationContext(), MyAidlService.class);
        bindService(intent, mConnect, BIND_AUTO_CREATE);
    }
}
