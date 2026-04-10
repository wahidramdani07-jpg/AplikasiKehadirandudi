package com.example.aplikasikehadiran;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DetailActivity extends AppCompatActivity {

    ImageView imageView;
    TextView tvId, tvTime;
    Button btnMaps, btnSelesai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imageView = findViewById(R.id.imageView);
        tvId = findViewById(R.id.tvId);
        tvTime = findViewById(R.id.tvTime);
        btnMaps = findViewById(R.id.btnMaps);
        btnSelesai = findViewById(R.id.btnSelesai);

        String id = getIntent().getStringExtra("id");
        Bitmap foto = getIntent().getParcelableExtra("foto");
        long time = getIntent().getLongExtra("time", 0);

        tvId.setText("ID: " + id);
        imageView.setImageBitmap(foto);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        tvTime.setText("Waktu: " + sdf.format(new Date(time)));

        btnMaps.setOnClickListener(v -> {
            Uri uri = Uri.parse("geo:-6.200000,106.816666");
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(mapIntent);
        });

        btnSelesai.setOnClickListener(v -> finish());
    }
}