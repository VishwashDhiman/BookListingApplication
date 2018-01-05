package com.example.android.booklistingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((Button) findViewById(R.id.search)).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        EditText Title=(EditText) findViewById(R.id.title);
        EditText Author = (EditText) findViewById(R.id.Author);
        if(v.getId()== R.id.search){
            String Title1= modifyText(Title.getText().toString());
            String Author1 = modifyText(Author.getText().toString());
            Intent intent = new Intent(MainActivity.this,BookDetails.class);
            intent.putExtra("title",Title1);
            intent.putExtra("author",Author1);
            startActivity(intent);
        }
        else{
            Title.setText("");
            Author.setText("");
        }
    }
    private String modifyText(String modify){
        String newString = modify.trim();
        do {
            newString = newString.replace(" ", "+");
        } while (newString.contains(" "));
        return newString;
    }
}
