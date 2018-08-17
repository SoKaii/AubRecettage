package aubervilliers.orange.aubrecettage.ui.infostickets;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import aubervilliers.orange.aubrecettage.R;
import aubervilliers.orange.aubrecettage.ui.fiches.CablageComplexeActivity;
import aubervilliers.orange.aubrecettage.ui.fiches.CablageSimpleActivity;

public class InfosCablageComplexeActivity extends AppCompatActivity {

    private EditText mNTicket;
    private EditText mRealTicket;
    private EditText mNomSalle;
    private EditText mCallBaie;
    private EditText mNumEquip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_infos_cablage_complexe);
        mNTicket = findViewById(R.id.nTicket);
        mRealTicket = findViewById(R.id.realTicket);
        mNomSalle = findViewById(R.id.nomSalle);
        mCallBaie = findViewById(R.id.callBaie);
        mNumEquip = findViewById(R.id.numEquip);

        Button mButtonStart = findViewById(R.id.start);

        mButtonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String numeroTicket = mNTicket.getText().toString();
                String realTicket = mRealTicket.getText().toString();
                String nomSalle = mNomSalle.getText().toString();
                String callBaie = mCallBaie.getText().toString();
                String numEquip = mNumEquip.getText().toString();

                Intent intent = new Intent(InfosCablageComplexeActivity.this , CablageComplexeActivity.class);
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

