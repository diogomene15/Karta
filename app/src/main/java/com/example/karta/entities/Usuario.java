package com.example.karta.entities;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Usuario {
    @PrimaryKey(autoGenerate = true)
    private int usuarioId;
    private String nome;
    private String email;
    private String senha;
}
