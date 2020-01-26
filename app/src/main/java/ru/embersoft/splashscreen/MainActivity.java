package ru.embersoft.splashscreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import ru.embersoft.splashscreen.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CardView rateCardView = findViewById(R.id.rateCardView);
        CardView privacyCardView = findViewById(R.id.privacyCardView);
        CardView termsCardView = findViewById(R.id.termsCardView);
        CardView feedbackCardView = findViewById(R.id.feedbackCardView);
        TextView versionTextView = findViewById(R.id.versionTextView);

        try {
            PackageInfo pInfo = this.getApplicationContext().getPackageManager().getPackageInfo(this.getPackageName(), 0);
            String version = pInfo.versionName;
            versionTextView.setText("Version "+version);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        rateCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rateMe();
            }
        });

        privacyCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // link to your privacy policy
                String url = "https://embersoft.ru/en/Privacy-Policy-Coffee-Space-Android/";
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        termsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // link to your terms
                String url = "https://embersoft.ru/en/Terms-and-Conditions-Coffee-Space-Android/";
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        feedbackCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", "support@embersoft.com", null));
                intent.putExtra(Intent.EXTRA_SUBJECT, "Theme of mail");
                startActivity(Intent.createChooser(intent, "Select post client"));
            }
        });

    }

    private void rateMe() {
        try {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("market://details?id=" + this.getPackageName())));
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=" + this.getPackageName())));
        }
    }
}
