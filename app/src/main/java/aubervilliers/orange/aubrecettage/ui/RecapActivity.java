package aubervilliers.orange.aubrecettage.ui;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.Calendar;

import aubervilliers.orange.aubrecettage.R;
import aubervilliers.orange.aubrecettage.model.Recette;

public class RecapActivity extends AppCompatActivity {

    private Recette recette;

    private DatePickerDialog datePickerDialogI;
    private DatePickerDialog datePickerDialogD;
    private RadioButton recettePartielle;
    private RadioButton recetteTotale;
    private RadioButton validOrangeYes;
    private RadioButton validOrangeNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_recap);

        EditText nCI2A;
        EditText refOrange;

        Button mSavePDF = findViewById(R.id.SavePDF);
        final Button dateRecetteD = findViewById(R.id.dateRecetteD);
        final Button dateRecetteI = findViewById(R.id.dateRecetteI);

        nCI2A = findViewById(R.id.nCI2A);
        refOrange = findViewById(R.id.refORange);
        recettePartielle = findViewById(R.id.recette_partielle);
        recetteTotale = findViewById(R.id.recette_totale);
        validOrangeYes = findViewById(R.id.valid_orange_yes);
        validOrangeNo = findViewById(R.id.valid_orange_no);

        Calendar calendar = Calendar.getInstance();
        dateRecetteD.setText(R.string.click_to_add);
        dateRecetteI.setText(R.string.click_to_add);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        datePickerDialogI = new DatePickerDialog(this, new MyDateSetListener(dateRecetteI), year, month, day);
        datePickerDialogD = new DatePickerDialog(this, new MyDateSetListener(dateRecetteD), year, month, day);

        final String CI2ANumber = nCI2A.getText().toString();
        final String orangeReferent = refOrange.getText().toString();

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
                intent.putExtra("DateRecetteI",dateRecetteI.getText().toString());
                intent.putExtra("DateRecetteD",dateRecetteD.getText().toString());
                intent.putExtra("CI2ANumber",CI2ANumber);
                intent.putExtra("referentOrange",orangeReferent);


                if (validOrangeYes.isSelected())
                    intent.putExtra("validationOrange",validOrangeYes.getText().toString());
                else if (validOrangeNo.isSelected())
                    intent.putExtra("validationOrange",validOrangeNo.getText().toString());
                if (recettePartielle.isSelected())
                    intent.putExtra("recetteType",recettePartielle.getText().toString());
                else if (recetteTotale.isSelected())
                    intent.putExtra("recetteType",recetteTotale.getText().toString());

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