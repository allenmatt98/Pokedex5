package com.example.pokedex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText pok;
    Button go;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pok=(EditText)findViewById(R.id.editText);
        go=(Button)findViewById(R.id.button);
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=pok.getText().toString();
                Intent intent=new Intent(MainActivity.this,Pokemon.class);
                intent.putExtra("keyname",name);
                startActivity(intent);
            }
        });







    }
}
