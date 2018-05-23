package aubervilliers.orange.aubrecettage.Fiches;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import aubervilliers.orange.aubrecettage.R;

public class CablageSimpleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_cablage_simple);

        Intent intent = getIntent();

        if(intent != null) {
            String numTicket = "";
            String realTicket = "";
            String nomSalle = "";
            String callBaie = "";
            String numEquip = "";

            if(intent.hasExtra("nTicket")) {
                numTicket = intent.getStringExtra("nTicket");
                realTicket = intent.getStringExtra("realTicket");
                nomSalle = intent.getStringExtra("nomSalle");
                callBaie = intent.getStringExtra("callBaie");
                numEquip = intent.getStringExtra("numEquip");

            }
            TextView nTicket = (TextView) findViewById(R.id.numTicket);
            nTicket.setText(numTicket);

            TextView rTicket = (TextView) findViewById(R.id.realTicket);
            rTicket.setText(realTicket);

            TextView nSalle = (TextView) findViewById(R.id.nomSalle);
            nSalle.setText(nomSalle);

            TextView cBaie = (TextView) findViewById(R.id.callBaie);
            cBaie.setText(callBaie);

            TextView nEquip = (TextView) findViewById(R.id.numEquip);
            nEquip.setText(numEquip);
        }


    }
}
