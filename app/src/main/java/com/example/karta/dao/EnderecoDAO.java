package com.example.karta.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.karta.entities.Endereco;

import java.util.List;

@Dao
public interface EnderecoDAO {
    @Query("SELECT * FROM Endereco WHERE enderecoId=:idEndereco LIMIT 1")
    public Endereco getEndereco(int idEndereco);
    @Query("SELECT * FROM Endereco")
    public List<Endereco> getAll();

    @Query("SELECT * FROM Endereco WHERE latitude=:latitude AND longitude=:longitude LIMIT 1")
    public Endereco getByLatLong(Double latitude, Double longitude);

    @Insert
    void insert(Endereco endereco);

    @Update
    void update(Endereco endereco);

    @Delete
    void delete(Endereco endereco);
}
