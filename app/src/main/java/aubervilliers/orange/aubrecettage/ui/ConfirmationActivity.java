package aubervilliers.orange.aubrecettage.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.io.File;

import aubervilliers.orange.aubrecettage.R;

public class ConfirmationActivity extends AppCompatActivity {


    public final static String EXTRA_FILE_KEY = "extra-file-key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_confirmation);
        TextView validExport = findViewById(R.id.confirmationExport);

        final Intent intent = getIntent();
        int exportState = intent.getIntExtra(ExportActivity.EXTRA_STATE_KEY, ExportActivity.EXPORT_STATE_MAIL);
        String path = intent.getStringExtra("path");
        File file = (File) intent.getSerializableExtra(ConfirmationActivity.EXTRA_FILE_KEY);

        if (exportState == ExportActivity.EXPORT_STATE_MAIL) {
            validExport.setText("Mail envoyé");
            file.delete();
        } else if (exportState == ExportActivity.EXPORT_STATE_PDF) {
            validExport.setText("Fichier PDF enregistré sous " + path);
        } else if (exportState == ExportActivity.EXPORT_STATE_MAIL_PDF) {
            validExport.setText("Mail envoyé & Fichier PDF enregistré sous " + path);
        }
    }
}
