package kr.co.company.threadbasic601;

import androidx.annotation.WorkerThread;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    WorkerThread wt;
    Thread wr;
//    Runnable wrun;
    boolean running = true;

    Button btn;
    ImageView iv;

    String TAG = "THREAD";

    class WorkerThread extends Thread {
        public void run() {
            int i = 0;
            for (i = 0; i < 20 && running; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
                Log.v(TAG, "Thread time=" + i);
            }
        }
    }
    class WorkerRunnable implements Runnable{

        @Override
        public void run() {
/*            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.lena);
                }
            });*/
/*            int i = 0;
            for (i = 0; i < 20 && running; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
                Log.v(TAG, "Runnable time=" + i);
            }*/
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v(TAG, "Now I am in onCreate");

        btn = findViewById(R.id.button);
        iv = findViewById(R.id.imageView);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            iv.setImageResource(R.drawable.lena);
                        }catch(Exception e){}
                    }
                }).start();

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        running = true;
        wt = new WorkerThread();
        wt.start();
//        new WorkerThread().start();

/*        wr = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                for (i = 0; i < 20 && running; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                    }
                    Log.v(TAG, "Runnable time=" + i);
                }
            }
        });
        wr.start();*/
/*        new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                for (i = 0; i < 20 && running; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                    }
                    Log.v(TAG, "Runnable time=" + i);
                }
            }
        }).start();*/
        new Thread(new WorkerRunnable()).start();
/*        wrun = new WorkerRunnable();
        wrun.run();*/

        Log.v(TAG, "Now I am in onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        running = false;
        Log.v(TAG, "Now I am in onStop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v(TAG, "Now I am in onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v(TAG, "Now I am in onResume");
    }
}