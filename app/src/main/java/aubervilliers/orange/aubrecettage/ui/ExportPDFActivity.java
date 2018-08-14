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

import com.cete.dynamicpdf.Document;
import com.cete.dynamicpdf.Font;
import com.cete.dynamicpdf.Page;
import com.cete.dynamicpdf.PageOrientation;
import com.cete.dynamicpdf.PageSize;
import com.cete.dynamicpdf.TextAlign;
import com.cete.dynamicpdf.pageelements.Label;

import java.io.File;

import aubervilliers.orange.aubrecettage.R;
import aubervilliers.orange.aubrecettage.model.Question;
import aubervilliers.orange.aubrecettage.model.Recette;

public class ExportPDFActivity extends Activity {

    public static final String TAG = "ExportPDFActivity";

    public static final String EXTRA_RECETTE_KEY = "extra-recette-key";
    private EditText editText;
    private Recette recette;
    private Document objDocument;
    private String pdfFileName;

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
                exportPDF(editText.getText().toString());
            }
        });


    }

    public void exportPDF(String fileName) {

        int i = 0;
        pdfFileName = Environment.getExternalStorageDirectory()
                + "/" + fileName + ".pdf";

        // Create a document and set it's properties
        objDocument = new Document();
        objDocument.setCreator("AubRecettage");
        objDocument.setAuthor("AubRecettage");
        objDocument.setTitle("AubRecettage");

        // Create a page to add to the document
        Page objPage = new Page(PageSize.LETTER, PageOrientation.PORTRAIT,
                54.0f);

        // Create a Label to add to the page
        String titre = "Recette de Câblage Simple du ticket n°" + recette.getTicketNumber();

        Label title = new Label(titre, 0, 0, 504, 100,
                Font.getHelvetica(), 18, TextAlign.CENTER);

        // Add label to page

        for (Question question : recette.getTabQuestions()) {
            String questionLabel = question.getQuestionLabel();

            String questionBoolLabel;
            if (question.getButtonYesSelected()) {
                questionBoolLabel = "Oui";
            } else {
                questionBoolLabel = "Non";
            }

            String questionComment = question.getCommentary();

            Label QuestionTitle = new Label(questionLabel, 0, 0, 504, 100, Font.getHelvetica(), 18, TextAlign.LEFT);
            Label QuestionBool = new Label(questionBoolLabel, 0, 0, 504, 100, Font.getHelvetica(), 18, TextAlign.LEFT);
            Label QuestionComment = new Label(questionComment, 0, 0, 504, 100, Font.getHelvetica(), 18, TextAlign.LEFT);

            objPage.getElements().add(QuestionTitle);
            objPage.getElements().add(QuestionBool);
            objPage.getElements().add(QuestionComment);
        }

        objPage.getElements().add(title);

        // Add page to document
        objDocument.getPages().add(objPage);

        generatePDF();
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
            generatePDF();
        }
    }

    private void generatePDF() {
        try {
            // Outputs the document to file
            if (isStoragePermissionGranted()) {
                objDocument.draw(pdfFileName);
                sendEmail(pdfFileName);
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

    private void sendEmail(String pdfFileName) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("text/plain");
        //emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"aurore.penault@orange.com"});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Résultat de la campagne de tests");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Veuillez trouver en pièce jointe, le résultat de la campagne de tests au format PDF.");
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