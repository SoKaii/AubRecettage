package aubervilliers.orange.aubrecettage.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import aubervilliers.orange.aubrecettage.R;

public class RecapActivity extends AppCompatActivity {

    private Button mSavePDF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_recap);

        mSavePDF = findViewById(R.id.SavePDF);

        mSavePDF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecapActivity.this, ExportPDFActivity.class);
                startActivity(intent);
            }
        });
    }
}
