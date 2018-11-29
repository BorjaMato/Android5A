package com.example.borja.u5_menucamara_a17borjama;


import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class U5_MenuCamara_a17borjama extends AppCompatActivity {

    private TextView tv;
    private final int REQUEST_CODE_CAMARA = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_u5__menu_camara_a17borjama);

        tv=findViewById(R.id.tv);

        registerForContextMenu(tv);
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v , menuInfo);
        MenuInflater inflater=getMenuInflater();

        inflater.inflate(R.menu.menu_listview,menu);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_layout, menu);
        return true;
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();

        switch (item.getItemId()){
            case R.id.sacarFoto:
                sacarFoto();
                return true;

            case R.id.salir:
                System.exit(0);
                return false;

            default:
                return super.onContextItemSelected(item);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.sacarFoto) {
            sacarFoto();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void sacarFoto(){
        Intent intento = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intento, REQUEST_CODE_CAMARA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        if (requestCode == REQUEST_CODE_CAMARA) {
            if (resultCode == RESULT_OK) {

                if (data == null) {
                    Toast.makeText(this, "NON HAI IMAXE", Toast.LENGTH_LONG).show();
                    return;
                }

                ImageView imgview =findViewById(R.id.iv);
                imgview.setImageBitmap((Bitmap) data.getExtras().get("data"));

            } else if (resultCode == RESULT_CANCELED) {
                // Foto cancelada
            } else {
                // Fallo na captura da foto.
            }
        }

    }

}
