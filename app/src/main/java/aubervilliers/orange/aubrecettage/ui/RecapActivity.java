package aubervilliers.orange.aubrecettage.ui;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import aubervilliers.orange.aubrecettage.R;
import aubervilliers.orange.aubrecettage.model.Recap;
import aubervilliers.orange.aubrecettage.model.Recette;

public class RecapActivity extends AppCompatActivity {

    private static final String TAG = "RecapActivity";

    private Recette recette;
    private Recap recap;

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
        final EditText nCI2A;
        final EditText refOrange;

        Button mSavePDF = findViewById(R.id.SavePDF);
        final Button dateRecetteD = findViewById(R.id.dateRecetteD);
        final Button dateRecetteI = findViewById(R.id.dateRecetteI);

        nCI2A = findViewById(R.id.nCI2A);
        refOrange = findViewById(R.id.refORange);
        recettePartielle = findViewById(R.id.recette_partielle);
        recetteTotale = findViewById(R.id.recette_totale);
        validOrangeYes = findViewById(R.id.valid_orange_yes);
        validOrangeNo = findViewById(R.id.valid_orange_no);


        final Calendar calendar = Calendar.getInstance();
        dateRecetteD.setText(R.string.click_to_add);
        dateRecetteI.setText(R.string.click_to_add);
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

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
                hideKeyboard(RecapActivity.this);
                datePickerDialogI.show();
            }
        });

        dateRecetteD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard(RecapActivity.this);
                Log.v(TAG, "dateRecetteI: " + dateRecetteI.getText());
                setDateMin(dateRecetteI.getText().toString(),datePickerDialogD);
                datePickerDialogD.show();
            }
        });

        mSavePDF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecapActivity.this, ExportActivity.class);

                recap = new Recap(nCI2A.getText().toString(), dateRecetteI.getText().toString(), dateRecetteD.getText().toString(), refOrange.getText().toString());

                if (recetteTotale.isChecked())
                    recap.setTypeRecette("Recette Totale");
                else if (recettePartielle.isChecked())
                    recap.setTypeRecette("Recette Partielle");
                if (validOrangeYes.isChecked())
                    recap.setValidOrange("OUI");
                else if (validOrangeNo.isChecked())
                    recap.setValidOrange("NON");

                recette.setRecap(recap);

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

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private void setDateMin(String dateMin,DatePickerDialog dp)
    {
        try {
            dp.getDatePicker().setMinDate(0);
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.FRENCH);
            Date date = formatter.parse(dateMin);
            dp.getDatePicker().setMinDate(date.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}