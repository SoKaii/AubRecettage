package aubervilliers.orange.aubrecettage.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.File;

import aubervilliers.orange.aubrecettage.R;

public class ConfirmationActivity extends AppCompatActivity {

    private TextView validExport = findViewById(R.id.confirmationExport);
    public final static String EXTRA_FILE_KEY = "extra-file-key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_confirmation);

        final Intent intent = getIntent();
        String exportState = intent.getStringExtra("exportState");
        String path = intent.getStringExtra("path");
        File file = (File) intent.getSerializableExtra(ConfirmationActivity.EXTRA_FILE_KEY);

        if (exportState == "Send Mail") {
            validExport.setText("Mail envoyé");
            file.delete();
        }
        else if(exportState == "Save PDF")
            validExport.setText("Fichier PDF enregistré sous " + path);
        else if (exportState == "Send Mail + Save PDF")
            validExport.setText("Mail envoyé & Fichier PDF enregistré sous " + path);

    }
}
