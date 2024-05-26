package com.example.karta.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.karta.dao.CidadeDAO;
import com.example.karta.dao.EnderecoDAO;
import com.example.karta.database.AppDatabase;
import com.example.karta.databinding.CadastroCidadeFragmentBinding;
import com.example.karta.databinding.CadastroEnderecoFragmentBinding;
import com.example.karta.entities.Cidade;
import com.example.karta.entities.Endereco;

public class CadastroEnderecoFragment extends Fragment {

    private CadastroEnderecoFragmentBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = CadastroEnderecoFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.spinnerCidades.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        binding.buttonAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double enderecoLatitude = Double.parseDouble(binding.inputLatitude.getText().toString());
                Double enderecoLongitude = Double.parseDouble(binding.inputLongitude.getText().toString());
                String enderecoDescricao = binding.inputDescricao.getText().toString();

                if (enderecoLatitude == null && enderecoLongitude == null && !enderecoDescricao.isEmpty()) {
                    new InsertEnderecoTask().execute(new Endereco(enderecoDescricao, enderecoLatitude, enderecoLongitude));
                } else {
                    Toast.makeText(getActivity(), "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private class InsertEnderecoTask extends AsyncTask<Endereco, Void, Endereco> {
        @Override
        protected Endereco doInBackground(Endereco... cidades) {
            AppDatabase db = AppDatabase.getDatabase(getContext());
            EnderecoDAO enderecoDAO = db.enderecoDao();
            Endereco enderecoExistente = enderecoDAO.getByLatLong(cidades[0].getLatitude(), cidades[0].getLongitude());
            if (enderecoExistente != null){
                return enderecoExistente;
            }
            enderecoDAO.insert(cidades[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Endereco enderecoExistente) {
            if (enderecoExistente == null){
                Toast.makeText(getActivity(), "Endereco cadastrado com sucesso", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), "Endereco já cadastrado", Toast.LENGTH_SHORT).show();
            }
        }
    }

}