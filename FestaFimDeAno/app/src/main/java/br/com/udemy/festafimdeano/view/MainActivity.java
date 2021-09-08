package br.com.udemy.festafimdeano.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import br.com.udemy.festafimdeano.R;
import br.com.udemy.festafimdeano.constant.FimDeAnoConstants;
import br.com.udemy.festafimdeano.data.SecurityPreferences;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private SecurityPreferences mSecurityPreferences;

    //formatar a data
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mSecurityPreferences = new SecurityPreferences(this);

        //elementos de interface
        this.mViewHolder.textToday = findViewById(R.id.text_today);
        this.mViewHolder.textDaysLeft = findViewById(R.id.text_days_left);
        this.mViewHolder.buttonConfirm = findViewById(R.id.button_confirm);

        // implementação dos eventos de click
        this.mViewHolder.buttonConfirm.setOnClickListener(this);

        // Obter a data de hoje
        this.mViewHolder.textToday.setText(SIMPLE_DATE_FORMAT.format(Calendar.getInstance().getTime()));

        // Obter os dias restantes
        String daysLeft = String.format("%s %s", String.valueOf(this.getDaysLeftToEndOfYear()), getString(R.string.dias));
        this.mViewHolder.textDaysLeft.setText(daysLeft);

    }

    /* @Override
    protected void onStart() {
        super.onStart();
    }*/

    @Override
    protected void onResume() {
        super.onResume();
        // Verificar a presença do usuario
        this.verifyPresence();
    }

    /*
        @Override
        protected void onPause() {
            super.onPause();
        }

        @Override
        protected void onStop() {
            super.onStop();
        }

        @Override
        protected void onDestroy() {
            super.onDestroy();
        }
    */

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.button_confirm) {

            String presence = this.mSecurityPreferences.getStoredString(FimDeAnoConstants.PRESENCE);

            // Lógica de navegação
            Intent intent = new Intent(this, DetailsActivity.class);
            intent.putExtra(FimDeAnoConstants.PRESENCE, presence);
            startActivity(intent);
        }
    }

    private int getDaysLeftToEndOfYear() {
        // Inicializa a instância do calendário e obtém o dia do ano - data de hoje
        Calendar calendarToday = Calendar.getInstance();
        int today = calendarToday.get(Calendar.DAY_OF_YEAR);

        //if(Calendar.MONTH == Calendar.DECEMBER){}

        // Dia maximo do ano (1-365)
        // Pega o dia máximo do ano - de 1 até 365, podem existir anos bissextos.
        Calendar calendarLastDay = Calendar.getInstance();
        int dayDecember31 = calendarLastDay.getActualMaximum(Calendar.DAY_OF_YEAR);

        // Calcula quantidade de dias restantes para o fim do ano
        return dayDecember31 - today;
    }

    // Verificar na SecurityPreferences se o usuario tem ou nao confirmacao de presença
    private void verifyPresence() {
        // nao confirmado (sem resposta), sim, nao
        String presence = this.mSecurityPreferences.getStoredString(FimDeAnoConstants.PRESENCE);
        // String vazia = não confirmado
        if (presence.equals("")) {
            this.mViewHolder.buttonConfirm.setText(R.string.nao_confirmado);
            // String com confirmação = sim
        } else if (presence.equals(FimDeAnoConstants.CONFIRMED_WILL_GO)) {
            this.mViewHolder.buttonConfirm.setText(R.string.sim);
            // String com confirmação = nao
        } else {
            this.mViewHolder.buttonConfirm.setText(R.string.nao);
        }
    }

    // Padrão ViewHolder
    private static class ViewHolder {

        //manipular os elementos
        TextView textToday;
        TextView textDaysLeft;
        Button buttonConfirm;

    }
}