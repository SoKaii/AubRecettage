package aubervilliers.orange.aubrecettage.ui.fiches;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import aubervilliers.orange.aubrecettage.R;

public class RackageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_rackage);

        Intent intent = getIntent();

        if(intent != null) {
            String numTicket = "";
            if(intent.hasExtra("nTicket")) {
                numTicket = intent.getStringExtra("nTicket");
            }

            TextView textView = (TextView) findViewById(R.id.numTicket);
            textView.setText(numTicket);
        }


    }
}
