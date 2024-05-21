package com.example.karta.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Endereco {
    @PrimaryKey(autoGenerate = true)
    int enderecoId;
    String descricao;
    double latitude;
    double longitude;
}
