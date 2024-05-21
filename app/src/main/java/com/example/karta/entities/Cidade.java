package com.example.karta.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Cidade {
    @PrimaryKey(autoGenerate = true)
    int cidadeId;
    String cidade;
    String estado;
}
