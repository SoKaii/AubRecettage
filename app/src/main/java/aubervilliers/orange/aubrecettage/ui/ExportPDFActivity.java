package aubervilliers.orange.aubrecettage.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.cete.dynamicpdf.*;
import com.cete.dynamicpdf.pageelements.Label;

import aubervilliers.orange.aubrecettage.R;

public class ExportPDFActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_export_pdf);
    }
}
