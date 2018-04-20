package com.lemee.ewan.aubrecettage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class choixRecette_activity extends AppCompatActivity
{
    private TextView mtitreChoixRecette;
    private Button mbuttonCableSimple;
    private Button mbuttonCableComplexe;
    private Button mbuttonRackage;
    private Button mbuttonJ4;
    private Button mbutton26e;
    private Button mbuttonIncident;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_choix_recette);
        mtitreChoixRecette = (TextView) findViewById(R.id.titreChoixRecette);
        mbuttonCableSimple = (Button) findViewById(R.id.buttonCableSimple);
        mbuttonCableComplexe = (Button) findViewById(R.id.buttonCableComplexe);
        mbuttonRackage = (Button) findViewById(R.id.buttonRackage);
        mbuttonJ4 = (Button) findViewById(R.id.buttonJ4);
        mbutton26e = (Button) findViewById(R.id.button26e);
        mbuttonIncident = (Button) findViewById(R.id.buttonIncident);
        
        mbuttonCableSimple.setOnClickListener(new View.OnClickListener()
                                              {
            @Override
            public void onClick(View v)
            {
                System.exit(0);
            }
        });
        
        
    }
    
    
}

