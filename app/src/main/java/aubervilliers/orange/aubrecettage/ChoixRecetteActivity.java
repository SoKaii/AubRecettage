package aubervilliers.orange.aubrecettage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import aubervilliers.orange.aubrecettage.infos_tickets.InfosCablageComplexeActivity;
import aubervilliers.orange.aubrecettage.infos_tickets.InfosCablageSimpleActivity;
import aubervilliers.orange.aubrecettage.infos_tickets.InfosE26Activity;
import aubervilliers.orange.aubrecettage.infos_tickets.InfosIncidentActivity;
import aubervilliers.orange.aubrecettage.infos_tickets.InfosJ4Activity;
import aubervilliers.orange.aubrecettage.infos_tickets.InfosRackageActivity;

public class ChoixRecetteActivity extends AppCompatActivity {

    private Button mbuttonCableSimple;
    private Button mbuttonCableComplexe;
    private Button mbuttonRackage;
    private Button mbuttonJ4;
    private Button mbutton26e;
    private Button mbuttonIncident;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_choix_recette);

        mbuttonCableSimple = (Button) findViewById(R.id.buttonCableSimple);
        mbuttonCableComplexe = (Button) findViewById(R.id.buttonCableComplexe);
        mbuttonRackage = (Button) findViewById(R.id.buttonRackage);
        mbuttonJ4 = (Button) findViewById(R.id.buttonJ4);
        mbutton26e = (Button) findViewById(R.id.button26e);
        mbuttonIncident = (Button) findViewById(R.id.buttonIncident);
        
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

