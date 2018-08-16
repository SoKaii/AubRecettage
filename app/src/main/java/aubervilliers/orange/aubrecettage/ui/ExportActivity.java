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
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import aubervilliers.orange.aubrecettage.R;
import aubervilliers.orange.aubrecettage.model.Question;
import aubervilliers.orange.aubrecettage.model.Recette;

public class ExportActivity extends Activity {

    public static final String TAG = "ExportActivity";

    public static final String EXTRA_RECETTE_KEY = "extra-recette-key";
    private EditText editText;
    private Recette recette;
    private Button sendMail;
    private EditText mailObject;
    private EditText mailTo;

    private String fileName;
    private String pdfFileName;
    private String objetMail;
    private String mailRecipient;
    Document document = new Document();

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_export);

        sendMail = findViewById(R.id.sendMail);
        mailObject = findViewById(R.id.mailObject);
        mailTo = findViewById(R.id.mailTo);
        editText = findViewById(R.id.nomFichier);

        fileName = editText.getText().toString();
        objetMail = mailObject.getText().toString();
        mailRecipient = mailTo.getText().toString();

        Intent intent = getIntent();

        if (intent != null) {

            if (intent.hasExtra(EXTRA_RECETTE_KEY)) {

                recette = (Recette) intent.getSerializableExtra(EXTRA_RECETTE_KEY);

            }

            Toast.makeText(this,
                    "Intent " + recette.getQuestion1().getCommentary() + " récupéré",
                    Toast.LENGTH_LONG).show();

        }

        Button bt = findViewById(R.id.exportButton);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pdfFileName = Environment.getExternalStorageDirectory()
                        + "/" + fileName + ".pdf";
                exportPDF();
            }
        });

        sendMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pdfFileName = Environment.getExternalStorageDirectory()
                        + "/" + fileName + ".pdf";
                exportPDF();
                sendMail(mailRecipient, objetMail);
            }
        });


    }

    public void exportPDF() {
        try {

            PdfWriter.getInstance(document, new FileOutputStream(pdfFileName));
            document.open();
            document.addAuthor("AubRecettage");
            document.addCreator("AubRecettage");
            document.add(new Paragraph("Recette du ticket n°" + recette.getTicketNumber() + "\n\n\n"));
            new Font(Font.FontFamily.TIMES_ROMAN, 12);
            for (Question question : recette.getTabQuestions()) {

                document.add(new Paragraph("Question : " + question.getQuestionLabel() + "\n\n"));

                if (!question.getOpenQuestion()) {
                    if (question.getButtonYesSelected()) {
                        document.add(new Paragraph("Validation : Oui"));
                    } else {
                        document.add(new Paragraph("Validation : Non"));
                    }
                }

                document.add(new Paragraph("Commentaire : " + question.getCommentary()));

                document.add(new Paragraph("\n \n"));
            }

            generatePDF();
        } catch (DocumentException de) {
            de.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
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
            generatePDF();
        }
    }

    private void generatePDF() {
        try {
            // Outputs the document to file
            if (isStoragePermissionGranted()) {
                // objDocument.draw(pdfFileName);
                document.close();
                Toast.makeText(this, "File has been written to :" + pdfFileName,
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Log.e("ExportPDF", e.getMessage(), e);
            Toast.makeText(this,
                    "Error, unable to write to file\n" + e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
    }

    private void sendMail(String mailRecipient, String objetMail) {

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, mailRecipient);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, objetMail);
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Veuillez trouver en pièce jointe, le résultat de la recette au format PDF du ticket n° " + recette.getTicketNumber());
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