package com.bwck.mytest.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.bwck.mytest.R;
import com.bwck.mytest.control.ServerManager;

public class MainActivity extends AppCompatActivity {
    ServerManager serverManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        serverManager = new ServerManager();
        serverManager.Stop();
        serverManager.Start(9001);
        findViewById(R.id.to_view_pager).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewPagerTestActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.send_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                serverManager.SendMessageToAll("1");
                serverManager.SendMessageToUser("1","1");
            }
        });

        findViewById(R.id.stop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                serverManager.Stop();
            }
        });



    }
}
