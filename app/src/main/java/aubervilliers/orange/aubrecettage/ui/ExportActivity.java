package aubervilliers.orange.aubrecettage.ui;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;

import aubervilliers.orange.aubrecettage.R;
import aubervilliers.orange.aubrecettage.model.Question;
import aubervilliers.orange.aubrecettage.model.Recette;

public class ExportActivity extends Activity {

    public static final String TAG = "ExportActivity";
    public static final String EXTRA_RECETTE_KEY = "extra-recette-key";
    public static final String EXTRA_STATE_KEY = "state_key";
    public static final int EXPORT_STATE_MAIL = 1;
    public static final int EXPORT_STATE_PDF = 2;
    public static final int EXPORT_STATE_MAIL_PDF = 3;
    static final int PICK_CONTACT_REQUEST = 1;
    Document document = new Document();
    private String pdfFileName;
    private String objetMail;
    private String mailRecipient;
    private Recette recette;
    private EditText mailObject;
    private EditText mailTo;
    private EditText editText;
    private LinearLayout linearMail;
    private CheckBox cbSave;
    private CheckBox cbSend;
    private File file;
    private int extraState = 1;

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_export);

        cbSave = findViewById(R.id.CBSave);
        cbSend = findViewById(R.id.CBSend);
        linearMail = findViewById(R.id.linearSend);
        mailObject = findViewById(R.id.mailObject);
        mailTo = findViewById(R.id.mailTo);
        editText = findViewById(R.id.nomFichier);
        final View exportButton = findViewById(R.id.exportButton);
        exportButton.setVisibility(View.INVISIBLE);

        final Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra(EXTRA_RECETTE_KEY))
                recette = (Recette) intent.getSerializableExtra(EXTRA_RECETTE_KEY);
            Toast.makeText(this,
                    "Intent " + recette.getQuestion1().getCommentary() + " récupéré",
                    Toast.LENGTH_LONG).show();
        }
        cbSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cbSend.isChecked())
                    linearMail.setVisibility(View.VISIBLE);
                else
                    linearMail.setVisibility((View.GONE));

                if (cbSave.isChecked() || cbSend.isChecked())
                    exportButton.setVisibility(View.VISIBLE);
                else
                    exportButton.setVisibility(View.GONE);
            }
        });

        cbSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cbSave.isChecked() || cbSend.isChecked())
                    exportButton.setVisibility(View.VISIBLE);
                else
                    exportButton.setVisibility(View.GONE);
            }
        });

        exportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isStoragePermissionGranted())
                    exportPDFAndSendEmailIfNecessary();

            }
        });
    }

    private void exportPDFAndSendEmailIfNecessary() {
        objetMail = mailObject.getText().toString();
        mailRecipient = mailTo.getText().toString();
        String fileName = editText.getText().toString();
        pdfFileName = Environment.getExternalStorageDirectory() + "/" + fileName + ".pdf";
        if (cbSend.isChecked()) {
            extraState = EXPORT_STATE_MAIL;
            exportPDF();
            sendMail();
        } else if (cbSave.isChecked()) {
            extraState = EXPORT_STATE_PDF;
            exportPDF();

            Intent confirmation = new Intent(ExportActivity.this, ConfirmationActivity.class);
            confirmation.putExtra(EXTRA_STATE_KEY, extraState);
            confirmation.putExtra(ConfirmationActivity.EXTRA_FILE_KEY, file);
            confirmation.putExtra("path", pdfFileName);
            startActivity(confirmation);
        } else if (!cbSave.isChecked() && !cbSend.isChecked()) {
            AlertDialog.Builder cbEmpty = new AlertDialog.Builder(ExportActivity.this);
            cbEmpty.setMessage("Aucun choix d'exportation détecté")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .setTitle("Erreur")
                    .create();
            cbEmpty.show();
        }
    }

    public void exportPDF() {
        try {
            PdfWriter.getInstance(document, new FileOutputStream(pdfFileName));
            int index = 1;
            boolean spaceQuestion;
            int compteurYes = 0;
            int compteurNo = 0;
            int compteurNoAnswered = 0;
            document.open();
            document.addAuthor("AubRecettage");
            document.addCreator("AubRecettage");
            Paragraph titleParagraph = new Paragraph("Recette du ticket n°" + recette.getTicketNumber() + "\n\n",
                    FontFactory.getFont(FontFactory.TIMES_BOLD, 18));
            titleParagraph.setAlignment(Element.ALIGN_CENTER);
            document.add(titleParagraph);

            new Font(Font.FontFamily.TIMES_ROMAN, 12);

            Paragraph infosTicketParagraph = new Paragraph("Numéro du ticket : " + recette.getTicketNumber() + "\n" +
                    "Nom de la salle : " + recette.getRoomName() + "\n" +
                    "Callepinage de l'équipement : " + recette.getBaieCall() + "\n" +
                    "N° 26E de l'équipement : " + recette.getEquipNumber() + "\n\n");
            document.add(infosTicketParagraph);
            Paragraph recapParagraph = new Paragraph("Numéro de CI2A du ticket : " + recette.getRecap().getCI2Anum() + "\n" +
                    "Validation orange : " + recette.getRecap().getValidOrange() + "\n" +
                    "Référent Orange : " + recette.getRecap().getReferentOrange() + "\n\n");
            document.add(recapParagraph);
            for (Question question : recette.getTabQuestions()) {
                if (!question.isOpenQuestion()) {
                    if (question.isButtonYesSelected()) {
                        compteurYes ++;
                    } else if (question.isButtonNoSelected()) {
                        compteurNo++;
                    }
                    else
                    {
                        compteurNoAnswered++;
                    }
                }
            }
            Paragraph compteurParagraph = new Paragraph(
                    "Nombre de validation OUI : " + compteurYes + "\n"+
                            "Nombre de validation NON : " + compteurNo + "\n\n");
            document.add(compteurParagraph);

            for (Question question : recette.getTabQuestions()) {
                spaceQuestion = false;
                if (!question.isOpenQuestion()) {
                    if (question.isButtonYesSelected()) {
                        document.add(new Paragraph(index +") " + question.getQuestionLabel() + ""));
                        document.add(new Paragraph("Validation : Oui"));
                        spaceQuestion = true;
                    } else if (question.isButtonNoSelected()) {
                        document.add(new Paragraph(index +") " + question.getQuestionLabel() + ""));
                        document.add(new Paragraph("Validation : Non"));
                        spaceQuestion = true;
                    }
                }
                else
                {
                    if (!question.getCommentary().equals("")) {
                        document.add(new Paragraph(index + ") " + question.getQuestionLabel() + ""));
                        spaceQuestion = true;
                    }
                }

                if (!question.getCommentary().equals("")) {
                document.add(new Paragraph("Commentaire : " + question.getCommentary()));
                }
                if (Boolean.TRUE.equals(spaceQuestion))
                {
                    document.add(new Paragraph("\n"));
                }

                index ++;
            }
            document.add(new Paragraph("Synthèse des commentaire"));
            index = 1;
            for (Question question : recette.getTabQuestions()) {
                if (!question.getCommentary().equals("")) {
                    document.add(new Paragraph(index + ") " + question.getCommentary()));
                }
                index++;
            }
            document.close();
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
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.v(TAG, "Permission: " + permissions[0] + "was " + grantResults[0]);
            exportPDFAndSendEmailIfNecessary();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_CONTACT_REQUEST) {
            Intent confirmation = new Intent(ExportActivity.this, ConfirmationActivity.class);
            confirmation.putExtra(EXTRA_STATE_KEY, extraState);
            confirmation.putExtra(ConfirmationActivity.EXTRA_FILE_KEY, file);
            confirmation.putExtra("path", pdfFileName);
            startActivity(confirmation);
        }
    }

    private void sendMail() {
        if (cbSave.isChecked()) {
            extraState = EXPORT_STATE_MAIL_PDF;
        }

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{mailRecipient});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, objetMail);
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Veuillez trouver en pièce jointe, le résultat de la recette au format PDF du ticket n° " + recette.getTicketNumber());

        file = new File(pdfFileName);
        if (!file.exists() || !file.canRead()) {
            Log.e(TAG, "The following file does not exist: " + pdfFileName);
            return;
        }
        Uri uri = Uri.fromFile(file);
        emailIntent.putExtra(Intent.EXTRA_STREAM, uri);
        Log.d("PICK_CONTACT_REQUEST1", "pick = " + PICK_CONTACT_REQUEST);
        startActivityForResult(Intent.createChooser(emailIntent, "Pick an Email provider"), PICK_CONTACT_REQUEST);
        Log.d("PICK_CONTACT_REQUEST2", "pick = " + PICK_CONTACT_REQUEST);
        hideKeyboard(ExportActivity.this);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.
                INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(this.getWindow().getDecorView().getRootView().getWindowToken(), 0);
        return true;
    }
}