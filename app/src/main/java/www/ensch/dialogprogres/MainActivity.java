package www.ensch.dialogprogres;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ProgressDialog progressDialog;
    int progreso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button boton= (Button) findViewById(R.id.button1);
        boton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        showDialog(0);
        new MyAsyncTask().execute();
    }

    @Override
    protected Dialog onCreateDialog(int id){

        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setIcon(R.mipmap.ic_launcher);
        progressDialog.setTitle("Progreso...");
        return progressDialog;
    }

    class MyAsyncTask extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void...arg0){
            for(int i=0;i<100;i++){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }
                progreso=i+1;
                publishProgress();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void...progress){
            progressDialog.setProgress(progreso);
            if(progreso==100)progressDialog.dismiss();

        }

    }   // end AsyncTask


}