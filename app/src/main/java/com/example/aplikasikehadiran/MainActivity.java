package com.example.aplikasikehadiran;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText etId;
    ImageView imageView;
    Button btnAmbilFoto, btnVerifikasi;

    Bitmap bitmap;

    int REQUEST_CAMERA = 1;
    int REQUEST_PERMISSION = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etId = findViewById(R.id.etId);
        imageView = findViewById(R.id.imageView);
        btnAmbilFoto = findViewById(R.id.btnAmbilFoto);
        btnVerifikasi = findViewById(R.id.btnVerifikasi);

        // Permission Camera
        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, REQUEST_PERMISSION);
        }

        // Ambil Foto
        btnAmbilFoto.setOnClickListener(v -> {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(intent, REQUEST_CAMERA);
            }
        });

        // Verifikasi
        btnVerifikasi.setOnClickListener(v -> {
            String id = etId.getText().toString().trim();

            if (id.isEmpty()) {
                etId.setError("ID wajib diisi");
                return;
            }

            if (bitmap == null) {
                Toast.makeText(this, "Ambil foto dulu", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra("id", id);
            intent.putExtra("foto", bitmap);
            intent.putExtra("time", System.currentTimeMillis());

            startActivity(intent);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CAMERA && resultCode == RESULT_OK && data != null) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            bitmap = photo;
            imageView.setImageBitmap(photo);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}