package com.example.dicegame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class index extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner PLayers,rolls,Themes;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        button=(Button)findViewById(R.id.button3);
        PLayers=(Spinner)findViewById(R.id.spinner);
        rolls=(Spinner)findViewById(R.id.spinner2);
        Themes=(Spinner)findViewById(R.id.spinner3);

        PLayers.setOnItemSelectedListener(this);
        List<String> PLayerslist=new ArrayList<>();
        PLayerslist.add("Single Player");
        PLayerslist.add("Double Player");

        rolls.setOnItemSelectedListener(this);
        List<String> rollslist=new ArrayList<>();
        rollslist.add("4");
        rollslist.add("6");
        rollslist.add("10");
        rollslist.add("20");

        Themes.setOnItemSelectedListener(this);
        List<String> Themeslist=new ArrayList<>();
        Themeslist.add("White");
        Themeslist.add("Dark");
        Themeslist.add("Blue");

        ArrayAdapter<String> dataAdapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,PLayerslist);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        PLayers.setAdapter(dataAdapter);

        ArrayAdapter<String> dataAdapter1=new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,rollslist);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        rolls.setAdapter(dataAdapter1);

        ArrayAdapter<String> dataAdapter2=new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,Themeslist);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Themes.setAdapter(dataAdapter2);


button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        if(PLayers.getSelectedItem().equals("Single Player")){

            Intent intent1=new Intent(index.this,game2.class);
            intent1.putExtra("rolls",String.valueOf(rolls.getSelectedItem()));
            intent1.putExtra("theme",String.valueOf(Themes.getSelectedItem()));
            startActivity(intent1);
        }
        else{
            Intent intent=new Intent(index.this,Game.class);
            intent.putExtra("rolls",String.valueOf(rolls.getSelectedItem()));
            intent.putExtra("theme",String.valueOf(Themes.getSelectedItem()));
            startActivity(intent);
        }
    }
});

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String item=adapterView.getItemAtPosition(i).toString();
       // Toast.makeText(adapterView.getContext(),"selected"+item,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        Toast.makeText(adapterView.getContext(),"no items selected",Toast.LENGTH_LONG).show();
    }
}
