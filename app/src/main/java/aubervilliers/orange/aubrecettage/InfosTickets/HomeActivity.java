package aubervilliers.orange.aubrecettage.InfosTickets;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import aubervilliers.orange.aubrecettage.R;

public class HomeActivity extends AppCompatActivity {

    private TextView mTitreHome;
    private ImageView mBackgroundBlack;
    private Button mNewRecette;
    private Button mConsultRecette;
    private ImageView mLogoOrange;

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_home);

        //test pull request

        //mTitreHome = (TextView) findViewById(R.id.titreHome);
        //mBackgroundBlack = (ImageView) findViewById(R.id.backgroundBlack);
        mNewRecette = (Button) findViewById(R.id.newRecette);
        //mConsultRecette = (Button) findViewById(R.id.consultRecette);
        //mLogoOrange = (ImageView) findViewById(R.id.logoOrange);

        mNewRecette.setOnClickListener(new View.OnClickListener()
        { @Override
        public void onClick(View v)
            {

                Intent intent = new Intent(HomeActivity.this , ChoixRecetteActivity.class);
                startActivity(intent);
            }
        });

    }


    //public native stringFromJNI();
}
