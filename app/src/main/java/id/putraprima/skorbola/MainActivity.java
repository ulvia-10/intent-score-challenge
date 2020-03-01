package id.putraprima.skorbola;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    //image view
    ImageView homeIconImage, awayIconImage;
    EditText homeText, awayText;
    //uri load gambar
    Uri imageUriHome;
    Uri imageUriAway;
    //Bitmap
    Bitmap bitmapHome;
    Bitmap bitmapAway;
    //TAG
    private static final String TAG = MainActivity.class.getCanonicalName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Find Id edit text
        homeText = findViewById(R.id.home_team);
        awayText = findViewById(R.id.away_team);
        //Find id image view
        homeIconImage = findViewById(R.id.home_logo);
        awayIconImage = findViewById(R.id.away_logo);

    }

    //3. Ganti Logo Home Team

    public void handleHomeLogo(View view) {

        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 1);
    }
    //4. Ganti Logo Away Team

    public void handleAwayLogo(View view) {

        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 2);

    }
    //Load gallery atau kamera


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED) {
            return;
        }
        if (requestCode == 1) {
            if (data != null) {
                try {
                    imageUriHome = data.getData();
                    bitmapHome = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUriHome);
                    homeIconImage.setImageBitmap(bitmapHome);
                } catch (IOException e) {
                    Toast.makeText(this, "Tak bisa load gambar", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, e.getMessage());
                }
            }
        } else if (requestCode == 2) {
            if (data != null) {
                try {
                    imageUriAway = data.getData();
                    bitmapAway = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUriAway);
                    awayIconImage.setImageBitmap(bitmapAway);
                } catch (IOException e) {
                    Toast.makeText(this, "Tak bisa load gambar", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, e.getMessage());
                }
            }
        }
    }

    //5. Next Button Pindah Ke MatchActivity
    public void handleNext(View view) {

        //Input Edit Text
        String inputHome = homeText.getText().toString();
        String inputAway = awayText.getText().toString();
        if (!inputHome.equals("") && !inputAway.equals("") && bitmapHome != null && bitmapAway != null) {

            Intent intent = new Intent(this, MatchActivity.class);
            intent.putExtra("inputHome", inputHome);
            intent.putExtra("inputAway", inputAway);
            intent.putExtra("iconHome", imageUriHome.toString());
            intent.putExtra("iconAway", imageUriAway.toString());
            startActivity(intent);
        } else {
            Toast.makeText(this, "Isi seluruh data terlebih dahulu!", Toast.LENGTH_SHORT).show();
        }

    }


    //TODO
    //Fitur Main Activity
    //1. Validasi Input Home Team
    //2. Validasi Input Away Team
    //3. Ganti Logo Home Team
    //4. Ganti Logo Away Team
    //5. Next Button Pindah Ke MatchActivity

}
