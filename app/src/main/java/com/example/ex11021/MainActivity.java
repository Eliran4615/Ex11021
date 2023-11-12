package com.example.ex11021;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    TextView tV;
    EditText eT;
    private final String fliename = "rawtext.txt";
    String fileName;
    int resourceId;
    InputStream iS;
    InputStreamReader iSR;
    BufferedReader bR;
    StringBuilder sB;
    Intent si;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tV = (TextView) findViewById(R.id.tV);
        eT = (EditText) findViewById(R.id.eT);

        fileName = fliename.substring(0, fliename.length() - 4);
        resourceId = this.getResources().getIdentifier(fileName, "raw",
                this.getPackageName());
    }


    public void readFile(View view) {
        try {

            iS = this.getResources().openRawResource(resourceId);
            iSR = new InputStreamReader(iS);
            bR = new BufferedReader(iSR);
            sB = new StringBuilder();


           String line = bR.readLine();
            while (line != null) {
                sB.append(line+'\n');
                line = bR.readLine();
            }
            bR.close();
            iSR.close();
            iS.close();
            tV.setText(sB.toString());
        }
        catch (IOException e){
            Toast.makeText(this, "Error reading file", Toast.LENGTH_LONG).show();
            Log.e("MainActivity", "Error reading file");
        }
    }


    public void readEt(View view) {
        tV.setText(eT.getText().toString());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main, menu);

        return super.onCreateOptionsMenu(menu);
    }



}