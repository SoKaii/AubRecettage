package aubervilliers.orange.aubrecettage.ui.fiches;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import aubervilliers.orange.aubrecettage.R;
import aubervilliers.orange.aubrecettage.model.Question;
import aubervilliers.orange.aubrecettage.model.Recette;
import aubervilliers.orange.aubrecettage.ui.RecapActivity;

public class CablageSimpleActivity extends AppCompatActivity {

    private Recette recette;
    private String numTicket = "";
    private String realTicket = "";
    private String nomSalle = "";
    private String callBaie = "";
    private String numEquip = "";
    private Button buttonNext;

    private LinearLayout ll;
    private List<String> titleList = new ArrayList<>();
    private List<RadioButton> yesBtList = new ArrayList<>();
    private List<RadioButton> noBtList = new ArrayList<>();
    private List<EditText> commentList = new ArrayList<>();
    private List<Boolean> isOpenQuestionList = new ArrayList<>();
    private List<Question> questions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_cablage_simple);

        buttonNext = findViewById(R.id.saveNext);

        Intent intent = getIntent();

        if (intent != null) {

            if(intent.hasExtra("nTicket")) {
                numTicket = intent.getStringExtra("nTicket");
                realTicket = intent.getStringExtra("realTicket");
                nomSalle = intent.getStringExtra("nomSalle");
                callBaie = intent.getStringExtra("callBaie");
                numEquip = intent.getStringExtra("numEquip");

            }

            TextView nTicket = findViewById(R.id.numTicket);
            nTicket.setText(numTicket);

            TextView rTicket = findViewById(R.id.realTicket);
            rTicket.setText(realTicket);

            TextView nSalle = findViewById(R.id.nomSalle);
            nSalle.setText(nomSalle);

            TextView cBaie = findViewById(R.id.callBaie);
            cBaie.setText(callBaie);

            TextView nEquip = findViewById(R.id.numEquip);
            nEquip.setText(numEquip);
        }

        ll = findViewById(R.id.questions);
        addQuestion("Les informations «équipement/constructeur/modèle» sont en cohérence avec le Terrain?", true);
        addQuestion("La localisation «Salle/Baie» est en cohérence avec le «Terrain»?", true);
        addQuestion("Le hostname des équipements décrits dans le document de référence sont en cohérence avec le terrain ?",true);
        addQuestion("L'étiquetage des hostname est correct ?",true);
        addQuestion("La présence de l’étiquetage 26E et son emplacement est correct ?",true);
        addQuestion("Le n°26e indiqué sur l’équipement est en cohérence avec le document de référence ?",true);
        addQuestion("Le n° de série de l’équipement est en cohérence avec le document de référence?",true);
        addQuestion("Le câblage physique des liaisons est en cohérence avec le plan de câblage Transplan ?",true);
        addQuestion("Les passages de câbles présentent un rayon de courbure suffisant sur les toits modulaires ?",true);
        addQuestion("La connexion des cordons sur les bandeaux et équipements est correcte ?",true);
        addQuestion("Les longueurs des cordons utilisés pour le CFA est correcte sur les toits modulaires ?",true);
        addQuestion("L’utilisation des scratchs est correcte sur les toits modulaires ?",true);
        addQuestion("Le passage des liaisons à l’état «réalisées» est effectif dans l’outil Transplan ?",true);
        addQuestion("La polarité Rx,Tx ainsi que le type de module SFP sont respectés ?",true);
        addQuestion("L’étiquetage des liaisons est correct ?",true);
        addQuestion("Anomalies à corriger :",false);
        addQuestion("Anomalies constatées lors de la recette initiale,correctifs apportés :",false);
        addQuestion("Anomalies non bloquantes à prendre en compte :",false);


        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //TODO Look for the Dialog if all the not opened questions are answered
                getInfos();
                for (Question question: questions) {
                    if(!question.getButtonYesSelected() && !question.getButtonNoSelected()) {
                        AlertDialog.Builder notAnswered = new AlertDialog.Builder(CablageSimpleActivity.this);
                        notAnswered.setMessage("Vous n'avez pas complété toutes les questions")
                                   .setPositiveButton("Continuer", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                                   .setTitle("Erreur")
                                   .create();
                    }
                }


                Intent intent1 = new Intent(CablageSimpleActivity.this, RecapActivity.class);
                intent1.putExtra(RecapActivity.EXTRA_RECETTE_KEY, recette);
                startActivity(intent1);
            }
        });

    }

    private void addQuestion(String title, boolean hasRadioButtons) {
        View questionLayout = View.inflate(this, R.layout.layout_question, null);
        titleList.add(title);
        TextView tv = questionLayout.findViewById(R.id.questionTitle);
        tv.setText(title);
        yesBtList.add((RadioButton) questionLayout.findViewById(R.id.questionYes));
        noBtList.add((RadioButton) questionLayout.findViewById(R.id.questionNo));
        commentList.add((EditText) questionLayout.findViewById(R.id.questionComment));
        if (!hasRadioButtons) {
            questionLayout.findViewById(R.id.questionRadioGroup).setVisibility(View.GONE);
            questionLayout.findViewById(R.id.questionCommentTV).setVisibility(View.GONE);
        }
        isOpenQuestionList.add(!hasRadioButtons);
        ll.addView(questionLayout);
    }

    private void getInfos() {

        int index = 0;
        for (String title : titleList) {
            Question question = new Question();
            question.setQuestionLabel(title);
            boolean openQuestion = isOpenQuestionList.get(index);
            question.setOpenQuestion(openQuestion);
            if (!openQuestion) {
                RadioButton yesButton = yesBtList.get(index);
                RadioButton noButton = noBtList.get(index);

                question.setButtonYesSelected(yesButton.isSelected());
                question.setButtonNoSelected(noButton.isSelected());
            }
            EditText commentEt = commentList.get(index);
            question.setCommentary(commentEt.getText().toString());
            questions.add(question);
            index++;
        }

        recette = new Recette("Câblage simple", numTicket, realTicket, nomSalle, callBaie, numEquip, questions, null);

    }
}
