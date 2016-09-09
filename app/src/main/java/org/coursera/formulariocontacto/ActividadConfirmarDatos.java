package org.coursera.formulariocontacto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

public class ActividadConfirmarDatos extends AppCompatActivity {

    //Atributos gráficos
    private TextView tvActividadConfirmarDatosNombreContacto, tvActividadConfirmarDatosFechaNacimientoContacto,
            tvActividadConfirmarDatosTelefonoContacto, tvActividadConfirmarDatosEmailContacto,
            tvActividadConfirmarDatosDescripcionContacto;

    //Atributos lógicos
    private int anho, mes, diaMes;
    private String nom, tel, email, des;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_confirmar_datos);

        iniciarComponentes();
    }

    /**
     * Método que permite iniciar los componentes en el entorno.
     */
    private void iniciarComponentes() {
        tvActividadConfirmarDatosNombreContacto             = (TextView) findViewById(R.id.tvActividadConfirmarDatosNombreContacto);
        tvActividadConfirmarDatosFechaNacimientoContacto    = (TextView) findViewById(R.id.tvActividadConfirmarDatosFechaNacimientoContacto);
        tvActividadConfirmarDatosTelefonoContacto           = (TextView) findViewById(R.id.tvActividadConfirmarDatosTelefonoContacto);
        tvActividadConfirmarDatosEmailContacto              = (TextView) findViewById(R.id.tvActividadConfirmarDatosEmailContacto);
        tvActividadConfirmarDatosDescripcionContacto        = (TextView) findViewById(R.id.tvActividadConfirmarDatosDescripcionContacto);

        capturarExtras();
    }

    /**
     * Método que permite extraer los parámetro que vienen de la vista anterior.
     */
    private void capturarExtras() {
        Bundle parametros = getIntent().getExtras();

        nom     = parametros.getString(getResources().getString(R.string.key_nombre_contacto));
        tel     = parametros.getString(getResources().getString(R.string.key_telefono_contacto));
        email   = parametros.getString(getResources().getString(R.string.key_email_contacto));
        des     = parametros.getString(getResources().getString(R.string.key_descripcion_contacto));

        anho    = parametros.getInt(getResources().getString(R.string.key_fecha_nacimiento_contacto_anho));
        mes     = parametros.getInt(getResources().getString(R.string.key_fecha_nacimiento_contacto_mes));
        diaMes  = parametros.getInt(getResources().getString(R.string.key_fecha_nacimiento_contacto_dia));

        tvActividadConfirmarDatosNombreContacto.setText(nom);
        tvActividadConfirmarDatosFechaNacimientoContacto.setText(diaMes + " / " + (mes+1) + " / " + anho);
        tvActividadConfirmarDatosTelefonoContacto.setText(tel);
        tvActividadConfirmarDatosEmailContacto.setText(email);
        tvActividadConfirmarDatosDescripcionContacto.setText(des);
    }

    /**
     * Método que permite el envio del formulario para la edición de los datos.
     * @param view, vista desde la cual se realiza el llamado del método.
     */
    public void editarContacto(View view) {

        Intent intent = new Intent(this, ActividadPrincipal.class);
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(this, ActividadPrincipal.class);
            startActivity(intent);
        }
        return super.onKeyDown(keyCode, event);
    }
}
