package br.com.udemy.festafimdeano.data;

import android.content.Context;
import android.content.SharedPreferences;

public class SecurityPreferences {

    /* SharedPreferences
        - armazena dados da aplicação
        - é recomendavel quando se tem dados pequenos/simples
        - dados de facil acesso na aplicação e que nao se alteram com frequencia */
    private SharedPreferences mSharedPreferences;

    /* Construtor
     - Recebe dois parametros: nome do SecurityPreferences e o modo (quem tem acesso ao SecurityPreferences fora da aplicação) */
    public SecurityPreferences(Context mContext) {
        this.mSharedPreferences = mContext.getSharedPreferences("FestaFimAno", Context.MODE_PRIVATE);
    }

    public void storeString(String key, String value) {
        //metodo - editar
        this.mSharedPreferences.edit().putString(key, value).apply();
    }

    //recuperar dados
    public String getStoredString(String key) {
        return this.mSharedPreferences.getString(key, "");
    }
}
