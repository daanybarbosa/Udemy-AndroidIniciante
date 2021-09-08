package br.com.udemy.conversormoedas;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.math.BigDecimal;
import java.math.RoundingMode;

// Implementação do onClickListener
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Contém os elementos da tela
    // Faz o carregamento uma unica vez e pode ser usado a qualquer momento dentro da classe.
    private ViewHolder mViewHolder = new ViewHolder();

    //onCreate - cria as activitys - elementos de interface
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Insere o layout na activity
        setContentView(R.layout.activity_main);

        // Busca os elementos da interface
        this.mViewHolder.editValue = this.findViewById(R.id.edit_value);
        this.mViewHolder.textDolar = this.findViewById(R.id.text_dolar);
        this.mViewHolder.textEuro = this.findViewById(R.id.text_euro);
        this.mViewHolder.buttonCalculate = this.findViewById(R.id.button_calculate);

        // Limpar os valores iniciais
        this.clearValues();

        // Adiciona evento de click no elemento
        this.mViewHolder.buttonCalculate.setOnClickListener(this);
        //this.mViewHolder.textDolar.setOnClickListener(this);

    }

    // onClick pertence a classe MainActivity
    @Override
    public void onClick(View view) {

        //Verifica se o elemento clicado não está em branco
        if (view.getId() == R.id.button_calculate) {

            String value = this.mViewHolder.editValue.getText().toString();
            if ("".equals(value)) {

                // Mostrar mensagem para o usuario
                // Toast.LENGTH_SHORT - mensagem que dura uns 3 segundos
                // Toast.LENGTH_LONG - mensagem que dura mais tempo
                Toast.makeText(this, this.getString(R.string.informe_valor), Toast.LENGTH_LONG).show();
            } else {

                // Converte o valor informado em Double
                Double real = Double.valueOf(value);

                //Converte o valor informado em Decimal
                BigDecimal realB = new BigDecimal(real);

                // Converte valores - considerando um valor fixo de dolar (U$ 4,00) e euro (€ 5,00)
                // String.format("%2.f") -> ira considerar 2 casas decimais
                this.mViewHolder.textDolar.setText(realB.divide(new BigDecimal(4)).setScale(2, RoundingMode.HALF_EVEN).toString());
                this.mViewHolder.textEuro.setText(realB.divide(new BigDecimal(5)).setScale(2, RoundingMode.HALF_EVEN).toString());

            }
        }
    }

    // Limpar os valores iniciais
    public void clearValues() {
        this.mViewHolder.textDolar.setText("");
        this.mViewHolder.textEuro.setText("");
    }

    // Padrão ViewHolder
    private static class ViewHolder {
        private EditText editValue;
        private TextView textDolar;
        private TextView textEuro;
        private Button buttonCalculate;
    }

}

