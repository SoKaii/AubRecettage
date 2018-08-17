package aubervilliers.orange.aubrecettage.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import aubervilliers.orange.aubrecettage.ui.infostickets.InfosCablageComplexeActivity;
import aubervilliers.orange.aubrecettage.ui.infostickets.InfosCablageSimpleActivity;
import aubervilliers.orange.aubrecettage.ui.infostickets.InfosE26Activity;
import aubervilliers.orange.aubrecettage.ui.infostickets.InfosIncidentActivity;
import aubervilliers.orange.aubrecettage.ui.infostickets.InfosJ4Activity;
import aubervilliers.orange.aubrecettage.ui.infostickets.InfosRackageActivity;
import aubervilliers.orange.aubrecettage.R;

public class ChoixRecetteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_choix_recette);

        Button mbuttonCableSimple = (Button) findViewById(R.id.buttonCableSimple);
        Button mbuttonCableComplexe = (Button) findViewById(R.id.buttonCableComplexe);
        Button mbuttonRackage = (Button) findViewById(R.id.buttonRackage);
        Button mbuttonJ4 = (Button) findViewById(R.id.buttonJ4);
        Button mbutton26e = (Button) findViewById(R.id.button26e);
        Button mbuttonIncident = (Button) findViewById(R.id.buttonIncident);
        
        mbuttonCableSimple.setOnClickListener(new View.OnClickListener()
        { @Override
        public void onClick(View v)
        {

            Intent intent = new Intent(ChoixRecetteActivity.this , InfosCablageSimpleActivity.class);
            startActivity(intent);
        }
        });

        mbuttonRackage.setOnClickListener(new View.OnClickListener()
        { @Override
        public void onClick(View view) {
            Intent intent = new Intent(ChoixRecetteActivity.this , InfosRackageActivity.class);
            startActivity(intent);
        }
        });

        mbuttonJ4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChoixRecetteActivity.this, InfosJ4Activity.class);
                startActivity(intent);
            }
        });

        mbuttonCableComplexe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ChoixRecetteActivity.this, InfosCablageComplexeActivity.class);
                startActivity(intent);
            }
        });

        mbutton26e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChoixRecetteActivity.this , InfosE26Activity.class);
                startActivity(intent);
            }
        });

        mbuttonIncident.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChoixRecetteActivity.this , InfosIncidentActivity.class);
                startActivity(intent);
            }
        });
    }
    
    
}

