package org.coursera.formulariocontacto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class ActividadPrincipal extends AppCompatActivity {

    //Atributos gráficos
    private EditText tietIngresoNombreContacto, tietIngresoTelefonoContacto,
            tietIngresoEmailContacto, tietIngresoDescripcionContacto;

    private DatePicker dpFechaNacimientoContacto;

    //Atributos logicos
    private int anho, mes, diaMes;
    private String nom, tel, email, des;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_principal);

        iniciarComponentes();
    }

    /**
     * Método que permite inicializar los componentes establecidos en el entorno.
     */
    private void iniciarComponentes() {

        tietIngresoNombreContacto       = (EditText) findViewById(R.id.tietIngresoNombreContacto);
        tietIngresoTelefonoContacto     = (EditText) findViewById(R.id.tietIngresoTelefonoContacto);
        tietIngresoEmailContacto        = (EditText) findViewById(R.id.tietIngresoEmailContacto);
        tietIngresoDescripcionContacto  = (EditText) findViewById(R.id.tietIngresoDescripcionContacto);
        dpFechaNacimientoContacto       = (DatePicker) findViewById(R.id.dpFechaNacimientoContacto);

        nom     = "";
        tel     = "";
        email   = "";
        des     = "";

        capturarExtras();
    }

    /**
     * Método que permite el envio del formulario para su confirmación.
     * @param view, vista desde la cual se realiza el llamado del método.
     */
    public void siguiente(View view) {

        anho    = dpFechaNacimientoContacto.getYear();
        mes     = dpFechaNacimientoContacto.getMonth();
        diaMes  = dpFechaNacimientoContacto.getDayOfMonth();

        nom      = tietIngresoNombreContacto.getText().toString();
        tel      = tietIngresoTelefonoContacto.getText().toString();
        email    = tietIngresoEmailContacto.getText().toString();
        des      = tietIngresoDescripcionContacto.getText().toString();

        Intent intent = new Intent(this, ActividadConfirmarDatos.class);
        intent.putExtra(getResources().getString(R.string.key_nombre_contacto), nom);
        intent.putExtra(getResources().getString(R.string.key_fecha_nacimiento_contacto_dia), diaMes);
        intent.putExtra(getResources().getString(R.string.key_fecha_nacimiento_contacto_mes), mes);
        intent.putExtra(getResources().getString(R.string.key_fecha_nacimiento_contacto_anho), anho);
        intent.putExtra(getResources().getString(R.string.key_telefono_contacto), tel);
        intent.putExtra(getResources().getString(R.string.key_email_contacto), email);
        intent.putExtra(getResources().getString(R.string.key_descripcion_contacto), des);

        startActivity(intent);

        finish();
    }

    /**
     * Método que permite extraer los parámetro que vienen de la vista anterior.
     */
    private void capturarExtras() {

        try {
            Bundle parametros = getIntent().getExtras();
            nom     = parametros.getString(getResources().getString(R.string.key_nombre_contacto));
            tel     = parametros.getString(getResources().getString(R.string.key_telefono_contacto));
            email   = parametros.getString(getResources().getString(R.string.key_email_contacto));
            des     = parametros.getString(getResources().getString(R.string.key_descripcion_contacto));

            anho    = parametros.getInt(getResources().getString(R.string.key_fecha_nacimiento_contacto_anho));
            mes     = parametros.getInt(getResources().getString(R.string.key_fecha_nacimiento_contacto_mes));
            diaMes  = parametros.getInt(getResources().getString(R.string.key_fecha_nacimiento_contacto_dia));

            tietIngresoNombreContacto.setText(nom);
            tietIngresoTelefonoContacto.setText(tel);
            tietIngresoEmailContacto.setText(email);
            tietIngresoDescripcionContacto.setText(des);

            dpFechaNacimientoContacto.updateDate(anho, mes, diaMes);
        }
        catch (NullPointerException e) {
            tietIngresoNombreContacto.setText("");
            tietIngresoTelefonoContacto.setText("");
            tietIngresoEmailContacto.setText("");
            tietIngresoDescripcionContacto.setText("");
        }
    }
}
