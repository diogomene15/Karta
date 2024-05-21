package com.example.karta.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.karta.entities.Usuario;

import java.util.List;

@Dao
public interface UsuarioDAO {
    @Query("SELECT * FROM Usuario WHERE usuarioId=:idUsuario LIMIT 1")
    public Usuario getUser(int idUsuario);
    @Query("SELECT * FROM Usuario")
    public List<Usuario> getAll();

    @Insert
    void insert(Usuario usuario);

    @Update
    void update(Usuario usuario);

    @Delete
    void delete(Usuario usuario);
}