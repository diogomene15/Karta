package com.example.karta.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.karta.R;
import com.example.karta.entities.Usuario;

public class EditUsuarioActivity extends AppCompatActivity {

    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_usuario);

        Intent intent = getIntent();

        usuario = (Usuario) intent.getSerializableExtra("usuario");

        if(usuario!= null){
            EditText nomeInput = (EditText) findViewById(R.id.nomeTextInput);
            EditText emailInput = (EditText) findViewById(R.id.emailTextInput);
            EditText novaSenha = (EditText) findViewById(R.id.passwordTextInput);
            EditText oldSenha = (EditText) findViewById(R.id.oldPasswordTextInput);

            nomeInput.setText(usuario.getNome());
            emailInput.setText(usuario.getEmail());

        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}