package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ScorerActivity extends AppCompatActivity {
 private EditText scorerName;

 public static final String SCORER_KEY = "scorer";
 public static final int SCORER_REQUEST_CODE = 1&2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scorer);
        //mengisi variable agar ditujukan pada id
        scorerName = findViewById(R.id.scoreNameInput);
    }

  public void handleAddScore(View view) {
      String scorer = scorerName.getText().toString();
        //validasi score
      if ((scorer).equals("")){
        Toast.makeText(getApplicationContext(), "Isi nama Pencetak gol!", Toast.LENGTH_SHORT).show();

      }else{
          EditText editText = (EditText) findViewById(R.id.scoreNameInput);
          String stringToPassBack = editText.getText().toString();
        Intent intent = new Intent (this, MatchActivity.class);
          intent.putExtra("SCORER_KEY", stringToPassBack);
        intent.putExtra(SCORER_KEY, scorer);
        setResult(MatchActivity.RESULT_OK, intent);
        finish();
      }
  }
}
