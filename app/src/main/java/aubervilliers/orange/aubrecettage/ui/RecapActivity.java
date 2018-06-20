package aubervilliers.orange.aubrecettage.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import aubervilliers.orange.aubrecettage.R;
import aubervilliers.orange.aubrecettage.model.Recette;
import aubervilliers.orange.aubrecettage.ui.fiches.CablageSimpleActivity;

public class RecapActivity extends AppCompatActivity {

    public static final String EXTRA_RECETTE_KEY = "extra-recette-key";

    private Button mSavePDF;
    private Recette recette;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_recap);

        mSavePDF = findViewById(R.id.SavePDF);

        Intent intent = getIntent();


        if (intent != null) {

            if(intent.hasExtra(EXTRA_RECETTE_KEY)) {

                recette = (Recette) intent.getSerializableExtra(EXTRA_RECETTE_KEY);

            }

            Toast.makeText(this,
                    "Intent " + recette + " récupéré",
                    Toast.LENGTH_LONG).show();

        }


        mSavePDF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecapActivity.this, ExportPDFActivity.class);
                startActivity(intent);
            }
        });
    }
}
