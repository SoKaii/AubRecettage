package aubervilliers.orange.aubrecettage.ui;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;

import aubervilliers.orange.aubrecettage.R;
import aubervilliers.orange.aubrecettage.model.Question;
import aubervilliers.orange.aubrecettage.model.Recette;

public class ExportPDFActivity extends Activity {

    public static final String TAG = "ExportPDFActivity";

    public static final String EXTRA_RECETTE_KEY = "extra-recette-key";
    private EditText editText;
    private Recette recette;
    private String pdfFileName;
    Document document = new Document();

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_export_pdf);

        Intent intent = getIntent();

        if (intent != null) {

            if (intent.hasExtra(EXTRA_RECETTE_KEY)) {

                recette = (Recette) intent.getSerializableExtra(EXTRA_RECETTE_KEY);

            }

            Toast.makeText(this,
                    "Intent " + recette.getQuestion1().getCommentary() + " récupéré",
                    Toast.LENGTH_LONG).show();

        }
        editText = findViewById(R.id.nomFichier);

        Button bt = findViewById(R.id.exportButton);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pdfFileName = Environment.getExternalStorageDirectory() + "/" + editText.getText().toString() + ".pdf";
                if (isStoragePermissionGranted()) {
                    exportPDF();
                }
            }
        });


    }

    public void exportPDF() {
        try {
            PdfWriter.getInstance(document, new FileOutputStream(pdfFileName));
            document.open();
            document.addAuthor("AubRecettage");
            document.addCreator("AubRecettage");
            document.addTitle("Recette du ticket n°" + recette.getTicketNumber());

            for (Question question : recette.getTabQuestions()) {

                document.add(new Paragraph("Question : " + question.getQuestionLabel()));

                if (question.getButtonYesSelected()) {
                    document.add(new Paragraph("Validation : Oui"));
                } else {
                    document.add(new Paragraph("Validation : Non"));
                }

                document.add(new Paragraph("Commentaire : " + question.getCommentary()));

                document.add(new Paragraph("\n \n"));
            }
            document.close();

            sendEmail(pdfFileName);
            Toast.makeText(this, "File has been written to :" + pdfFileName,
                    Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            Log.e("ExportPDF", e.getMessage(), e);
            Toast.makeText(this,
                    "Error, unable to write to file\n" + e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
    }

    public boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG, "Permission is granted");
                return true;
            } else {

                Log.v(TAG, "Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG, "Permission is granted");
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.v(TAG, "Permission: " + permissions[0] + "was " + grantResults[0]);
            exportPDF();
        }
    }

    private void sendEmail(String pdfFileName) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("text/plain");
        //emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"aurore.penault@orange.com"});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Résultat de la recette du ticket n°" + recette.getTicketNumber());
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Veuillez trouver en pièce jointe, le résultat de la recette au format PDF.");
        File file = new File(pdfFileName);
        if (!file.exists() || !file.canRead()) {
            Log.e(TAG, "The following file does not exist: " + pdfFileName);
            return;
        }
        Uri uri = Uri.fromFile(file);
        emailIntent.putExtra(Intent.EXTRA_STREAM, uri);
        startActivity(Intent.createChooser(emailIntent, "Pick an Email provider"));
    }
}