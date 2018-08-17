package aubervilliers.orange.aubrecettage.ui;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;

import aubervilliers.orange.aubrecettage.R;
import aubervilliers.orange.aubrecettage.model.Recette;

public class RecapActivity extends AppCompatActivity {

    private Recette recette;

    private DatePickerDialog datePickerDialogI;
    private DatePickerDialog datePickerDialogD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_recap);

        Calendar calendar = Calendar.getInstance();
        Button mSavePDF = findViewById(R.id.SavePDF);
        Button dateRecetteD = findViewById(R.id.dateRecetteD);
        dateRecetteD.setText(R.string.click_to_add);
        Button dateRecetteI = findViewById(R.id.dateRecetteI);
        dateRecetteI.setText(R.string.click_to_add);

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        datePickerDialogI = new DatePickerDialog(this, new MyDateSetListener(dateRecetteI), year, month, day);
        datePickerDialogD = new DatePickerDialog(this, new MyDateSetListener(dateRecetteD), year, month, day);

        Intent intent = getIntent();


        if (intent != null) {

            if (intent.hasExtra(ExportActivity.EXTRA_RECETTE_KEY)) {

                recette = (Recette) intent.getSerializableExtra(ExportActivity.EXTRA_RECETTE_KEY);

            }

            Toast.makeText(this,
                    "Intent " + recette.getRecetteType() + " récupéré",
                    Toast.LENGTH_LONG).show();
        }

        dateRecetteI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialogI.show();
            }
        });

        dateRecetteD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialogD.show();
            }
        });

        mSavePDF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecapActivity.this, ExportActivity.class);
                intent.putExtra(ExportActivity.EXTRA_RECETTE_KEY, recette);
                startActivity(intent);
            }
        });
    }


    class MyDateSetListener implements DatePickerDialog.OnDateSetListener {

        private Button button;

        MyDateSetListener(Button button) {
            this.button = button;
        }

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            button.setText(new StringBuilder().append(dayOfMonth).append("/")
                    .append(month + 1).append("/").append(year));
        }

    }

}

//TODO Recuperate dates && configure radio buttons for TOTALE/PARTIELLE