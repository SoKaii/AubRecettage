package aubervilliers.orange.aubrecettage.Fiches;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import aubervilliers.orange.aubrecettage.R;

public class CablageSimpleActivity extends AppCompatActivity {

    private String numTicket = "";
    private String realTicket = "";
    private String nomSalle = "";
    private String callBaie = "";
    private String numEquip = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_cablage_simple);

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

    }

    private void getInfos() {
        //TODO create model to store data

        //TODO store numTicket, realTicket, salle, baie and equipment in data model

        RadioButton infosEquipYes = findViewById(R.id.infos_equip_yes);
        // not necessary to have the state of the other button, when one is checked, the other is unchecked because of RadioGroup
        // model.infosEquipCoherent = infosEquipYes.isChecked()

        EditText infosEquipEt = findViewById(R.id.infos_equip_comment);
        // model.setInfosEquipComment = infosEquipEt.getText().toString();
    }
}
