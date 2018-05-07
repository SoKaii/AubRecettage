package aubervilliers.orange.aubrecettage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ChoixRecetteActivity extends AppCompatActivity {
    
    private TextView mtitreChoixRecette;
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

            Intent intent = new Intent(ChoixRecetteActivity.this , CablageSimpleActivity.class);
            startActivity(intent);
        }
        });

        mbuttonRackage.setOnClickListener(new View.OnClickListener()
        { @Override
        public void onClick(View view) {
            Intent intent = new Intent(ChoixRecetteActivity.this , RackageActivity.class);
            startActivity(intent);
        }
        });

        mbuttonJ4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChoixRecetteActivity.this, J4Activity.class);
                startActivity(intent);
            }
        });


        
        
    }
    
    
}

