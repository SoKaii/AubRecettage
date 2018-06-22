package aubervilliers.orange.aubrecettage.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cete.dynamicpdf.*;
import com.cete.dynamicpdf.pageelements.Label;

import aubervilliers.orange.aubrecettage.R;
import aubervilliers.orange.aubrecettage.model.Question;
import aubervilliers.orange.aubrecettage.model.Recette;

public class ExportPDFActivity extends Activity {

    public static final String EXTRA_RECETTE_KEY = "extra-recette-key";
    private EditText editText;
    private Recette recette;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_export_pdf);

        Intent intent = getIntent();

        if (intent != null) {

            if(intent.hasExtra(EXTRA_RECETTE_KEY)) {

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
        String pdfFileName = Environment.getExternalStorageDirectory()
                + "/" + fileName + ".pdf";

        // Create a document and set it's properties
        Document objDocument = new Document();
        objDocument.setCreator("AubRecettage");
        objDocument.setAuthor("AubRecettage");
        objDocument.setTitle("AubRecettage");

        // Create a page to add to the document
        Page objPage = new Page(PageSize.LETTER, PageOrientation.PORTRAIT,
                54.0f);

        // Create a Label to add to the page
        String titre = "Recette de Câblage Simple du ticket n°"+recette.getTicketNumber();

        Label title = new Label(titre, 0, 0, 504, 100,
                Font.getHelvetica(), 18, TextAlign.CENTER);

        // Add label to page

        for (Question question : recette.getTabQuestions()) {
            String questionLabel = question.getQuestionLabel();

            String questionBoolLabel;
            if (question.getButtonYesSelected())
            {
                questionBoolLabel = "Oui";
            }
            else
            {
                questionBoolLabel = "Non";
            }

            String questionComment = question.getCommentary();

            Label QuestionTitle = new Label(questionLabel,0,0,504,100,Font.getHelvetica(),18,TextAlign.LEFT);
            Label QuestionBool = new Label(questionBoolLabel,0,0,504,100,Font.getHelvetica(),18,TextAlign.LEFT);
            Label QuestionComment = new Label(questionComment,0,0,504,100,Font.getHelvetica(),18,TextAlign.LEFT);

            objPage.getElements().add(QuestionTitle);
            objPage.getElements().add(QuestionBool);
            objPage.getElements().add(QuestionComment);
        }

        objPage.getElements().add(title);

        // Add page to document
        objDocument.getPages().add(objPage);

        try {
            // Outputs the document to file
            objDocument.draw(pdfFileName);
            Toast.makeText(this, "File has been written to :" + pdfFileName,
                    Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(this,
                    "Error, unable to write to file\n" + e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
    }
}