package aubervilliers.orange.aubrecettage.ui.fiches;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO verify all qeustions are answered
                getInfos();

                Intent intent1 = new Intent(CablageSimpleActivity.this, RecapActivity.class);
                intent1.putExtra(RecapActivity.EXTRA_RECETTE_KEY, recette);
                startActivity(intent1);
            }
        });

    }

    private void getInfos() {

        int index = 1;


        TextView questionLabelTv1 = findViewById(R.id.question1);
        Question question1 = new Question();
        question1.setQuestionLabel(questionLabelTv1.getText().toString());
        RadioButton yesBt1 = findViewById(R.id.question1_yes);
        question1.setButtonYesSelected(yesBt1.isSelected());
        EditText questionCommentEt1 = findViewById(R.id.question1_comment);
        question1.setCommentary(questionCommentEt1.getText().toString());


        TextView questionLabelTv2 = findViewById(R.id.question2);
        Question question2 = new Question();
        question1.setQuestionLabel(questionLabelTv2.getText().toString());
        RadioButton yesBt2 = findViewById(R.id.question2_yes);
        question1.setButtonYesSelected(yesBt2.isSelected());
        EditText questionCommentEt2 = findViewById(R.id.question2_comment);
        question2.setCommentary(questionCommentEt2.getText().toString());


        TextView questionLabelTv3 = findViewById(R.id.question3);
        Question question3 = new Question();
        question1.setQuestionLabel(questionLabelTv3.getText().toString());
        RadioButton yesBt3 = findViewById(R.id.question3_yes);
        question1.setButtonYesSelected(yesBt3.isSelected());
        EditText questionCommentEt3 = findViewById(R.id.question3_comment);
        question3.setCommentary(questionCommentEt3.getText().toString());

        TextView questionLabelTv4 = findViewById(R.id.question4);
        Question question4 = new Question();
        question1.setQuestionLabel(questionLabelTv4.getText().toString());
        RadioButton yesBt4 = findViewById(R.id.question4_yes);
        question1.setButtonYesSelected(yesBt4.isSelected());
        EditText questionCommentEt4 = findViewById(R.id.question4_comment);
        question4.setCommentary(questionCommentEt4.getText().toString());

        TextView questionLabelTv5 = findViewById(R.id.question5);
        Question question5 = new Question();
        question1.setQuestionLabel(questionLabelTv5.getText().toString());
        RadioButton yesBt5 = findViewById(R.id.question5_yes);
        question1.setButtonYesSelected(yesBt5.isSelected());
        EditText questionCommentEt5 = findViewById(R.id.question5_comment);
        question5.setCommentary(questionCommentEt5.getText().toString());

        List<Question> questions = new ArrayList<>();
        questions.add(question1);
        questions.add(question2);
        questions.add(question3);
        questions.add(question4);
        questions.add(question5);


        recette = new Recette("Câblage simple", numTicket, realTicket, nomSalle, callBaie, numEquip, questions, null);

    }
}
