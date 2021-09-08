package br.com.udemy.festafimdeano.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import br.com.udemy.festafimdeano.R;
import br.com.udemy.festafimdeano.constant.FimDeAnoConstants;
import br.com.udemy.festafimdeano.data.SecurityPreferences;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private SecurityPreferences mSecurityPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        this.mSecurityPreferences = new SecurityPreferences(this);

        this.mViewHolder.checkParticipate = findViewById(R.id.check_partipate);

        //monitorar o evento de click
        this.mViewHolder.checkParticipate.setOnClickListener(this);

        // Ira carregar qualquer dado que estiver vindo por parametro na activity
        this.loadDataFromActivity();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.check_partipate) {
            if (this.mViewHolder.checkParticipate.isChecked()) {
                // salvar a presença
                this.mSecurityPreferences.storeString(FimDeAnoConstants.PRESENCE, FimDeAnoConstants.CONFIRMED_WILL_GO);
            } else {
                //salvar a ausencia
                this.mSecurityPreferences.storeString(FimDeAnoConstants.PRESENCE, FimDeAnoConstants.CONFIRMED_WONT_GO);
            }
        }
    }

    private void loadDataFromActivity() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String presence = extras.getString(FimDeAnoConstants.PRESENCE);
            if (presence != null && presence.equals(FimDeAnoConstants.CONFIRMED_WILL_GO)) {
                this.mViewHolder.checkParticipate.setChecked(true);
            } else {
                this.mViewHolder.checkParticipate.setChecked(false);
            }
        }
    }

    private static class ViewHolder {
        CheckBox checkParticipate;
    }
}