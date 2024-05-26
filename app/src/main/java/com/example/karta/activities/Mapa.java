package com.example.karta.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.karta.R;
import com.example.karta.databinding.ActivityMapaBinding;
import com.example.karta.entities.Endereco;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Mapa extends AppCompatActivity implements OnMapReadyCallback {

    private Endereco endereco;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mapa);

        //Configurando mapa na activity
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if(mapFragment!=null){
            mapFragment.getMapAsync(this);
        }

        //Buscando informações de localização de endereco na intent
        Intent intent = getIntent();
        if(intent!= null){
            endereco = (Endereco) intent.getSerializableExtra("endereco");
        }

        // Adicionando nome de endereco ao texto da activity
        if(endereco != null){
            TextView enderecoNome = findViewById(R.id.enderecoNome);
            enderecoNome.setText(endereco.getDescricao());
        }

        // Configurando botão de voltar
        Button sairMapaButton = findViewById(R.id.sairMapaButton);
        sairMapaButton.setOnClickListener(v -> {
            finish();
        });



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        if(endereco!=null){
            googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(endereco.getLatitude(), endereco.getLongitude()))
                    .title(endereco.getDescricao())
            );
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(
                    new LatLng(
                            endereco.getLatitude(),
                            endereco.getLongitude()
                    )
            ));
            googleMap.animateCamera(CameraUpdateFactory.zoomTo(16));
        }

    }
}