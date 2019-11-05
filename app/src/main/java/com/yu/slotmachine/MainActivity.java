package com.yu.slotmachine;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int rnd;
    private ImageView imageL;
    private ImageView imageC;
    private ImageView imageR;
    private TextView point;
    private ImageView[] imageAry;
    private int[] ary;
    private int gold;
    private FloatingActionButton fab;
    private Button add;
    private TextView addGold;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        findView();
        ary= new int[]{0, 0, 0};
        gold = Integer.parseInt(point.getText().toString());
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slot();
                culPoint();

            }
        });
    }

    public void add(View view){
        gold+=1000;
        point.setText(gold+"");
    }

    public void slot(){
        if(gold>=150) {
            for (int i = 0; i < imageAry.length; i++) {
                rnd = new Random().nextInt(4);
                switch (rnd) {
                    case 0:
                        imageAry[i].setImageResource(R.drawable.seven);
                        ary[i] = 0;
                        break;
                    case 1:
                        imageAry[i].setImageResource(R.drawable.bell);
                        ary[i] = 1;
                        break;
                    case 2:
                        imageAry[i].setImageResource(R.drawable.apple);
                        ary[i] = 2;
                        break;
                    case 3:
                        imageAry[i].setImageResource(R.drawable.cherry);
                        ary[i] = 3;
                        break;
                }
            }
        }
    }

    public void culPoint(){
        if(gold>=150) {
            gold-=150;
            if (ary[0] == ary[1] && ary[1] == ary[2]) {
                switch (ary[0]) {
                    case 0:
                        gold += 1000;
                        break;
                    case 1:
                        gold += 500;
                        break;
                    case 2:
                        gold += 300;
                        break;
                    case 3:
                        gold += 100;
                        break;
                }
            }
            point.setText(gold + "");
        }else{
            Toast.makeText(MainActivity.this,"你破產了",Toast.LENGTH_LONG).show();
            add.setVisibility(View.VISIBLE);
            addGold.setVisibility(View.VISIBLE);
        }
    }

    private void findView() {
        addGold = findViewById(R.id.addGold);
        add = findViewById(R.id.add);
        imageL = findViewById(R.id.imageLeft);
        imageC = findViewById(R.id.imageCenter);
        imageR = findViewById(R.id.imageRight);
        imageAry = new ImageView[]{imageL,imageC,imageR};
        point = findViewById(R.id.point);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
