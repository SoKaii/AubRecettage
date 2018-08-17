package aubervilliers.orange.aubrecettage.ui;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;


import java.util.Calendar;

import aubervilliers.orange.aubrecettage.R;
import aubervilliers.orange.aubrecettage.model.Recette;

public class RecapActivity extends AppCompatActivity{


    private Button dateRecetteI;
    private Button dateRecetteD;
    private Recette recette;

    private int year;
    private int month;
    private int day;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_recap);

        Calendar calendar = Calendar.getInstance();
        Button mSavePDF = findViewById(R.id.SavePDF);
        dateRecetteD = findViewById(R.id.dateRecetteD);
        dateRecetteI = findViewById(R.id.dateRecetteI);

        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDateI(year, month + 1, day);
        showDateD(year, month + 1, day);
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
                showDialog(999);
                index = 0;
                Toast.makeText(getApplicationContext(), "ca",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });

        dateRecetteD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(999);
                index = 1;
                Toast.makeText(getApplicationContext(), "ca",
                        Toast.LENGTH_SHORT)
                        .show();
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

    protected Dialog onCreateDialog(int id) {
        if (id == 999) {
            return new DatePickerDialog(this,
                    myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
                    if (index == 0)
                        showDateI(arg1, arg2+1, arg3);
                    else if (index == 1)
                        showDateD(arg1,arg2+1,arg3);
                }
            };

    private void showDateI(int year, int month, int day) {
        dateRecetteI.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }

    private void showDateD(int year, int month, int day) {
        dateRecetteD.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }

}

//TODO Recuperate dates && configure radio buttons for TOTALE/PARTIELLE