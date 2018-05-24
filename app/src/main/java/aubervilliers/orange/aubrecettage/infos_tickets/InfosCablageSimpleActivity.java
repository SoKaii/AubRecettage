package aubervilliers.orange.aubrecettage.infos_tickets;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import aubervilliers.orange.aubrecettage.fiches.CablageSimpleActivity;
import aubervilliers.orange.aubrecettage.R;

public class InfosCablageSimpleActivity extends AppCompatActivity {

    private Button mButtonStart;
    private EditText mNTicket;
    private EditText mRealTicket;
    private EditText mNomSalle;
    private EditText mCallBaie;
    private EditText mNumEquip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_infos_cablage_simple);

        mNTicket = (EditText) findViewById(R.id.nTicket);
        mRealTicket = (EditText) findViewById(R.id.realTicket);
        mNomSalle = (EditText) findViewById(R.id.nomSalle);
        mCallBaie = (EditText) findViewById(R.id.callBaie);
        mNumEquip = (EditText) findViewById(R.id.numEquip);

        mButtonStart = (Button) findViewById(R.id.start);

        mButtonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String numeroTicket = mNTicket.getText().toString();
                String realTicket = mRealTicket.getText().toString();
                String nomSalle = mNomSalle.getText().toString();
                String callBaie = mCallBaie.getText().toString();
                String numEquip = mNumEquip.getText().toString();

                Intent intent = new Intent(InfosCablageSimpleActivity.this , CablageSimpleActivity.class);
                intent.putExtra("nTicket",numeroTicket);
                intent.putExtra("realTicket",realTicket);
                intent.putExtra("nomSalle",nomSalle);
                intent.putExtra("callBaie",callBaie);
                intent.putExtra("numEquip",numEquip);

                startActivity(intent);
            }
        });
    }
}
