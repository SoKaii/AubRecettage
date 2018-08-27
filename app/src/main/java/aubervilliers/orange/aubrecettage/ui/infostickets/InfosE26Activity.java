package aubervilliers.orange.aubrecettage.ui.infostickets;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import aubervilliers.orange.aubrecettage.R;
import aubervilliers.orange.aubrecettage.ui.fiches.E26Activity;

public class InfosE26Activity extends AppCompatActivity {

    private EditText mNomSalle;
    private EditText mCallBaie;
    private EditText mNumEquip;
    private EditText mEquipElev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_infos_26e);

        mNomSalle = findViewById(R.id.nomSalle);
        mCallBaie = findViewById(R.id.callBaie);
        mNumEquip = findViewById(R.id.numEquip);
        mEquipElev = findViewById(R.id.elevEquip);
        Button mButtonStart = findViewById(R.id.start);

        mButtonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nomSalle = mNomSalle.getText().toString();
                String callBaie = mCallBaie.getText().toString();
                String numEquip = mNumEquip.getText().toString();
                String elevEquip = mEquipElev.getText().toString();

                Intent intent = new Intent(InfosE26Activity.this , E26Activity.class);
                intent.putExtra("nomSalle",nomSalle);
                intent.putExtra("callBaie",callBaie);
                intent.putExtra("numEquip",numEquip);
                intent.putExtra("elevEquip",elevEquip);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodManager imm = (InputMethodManager)this.getSystemService(Context.
                INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(this.getWindow().getDecorView().getRootView().getWindowToken(), 0);
        return true;
    }
}

