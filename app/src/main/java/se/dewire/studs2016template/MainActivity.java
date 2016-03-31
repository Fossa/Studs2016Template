package se.dewire.studs2016template;

import android.content.DialogInterface;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Timer;
import java.util.TimerTask;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private DewireContestConnection s = null;

    private static String GROUP_TAG = "Grupp2";     //Change this to your group name

    private Button[] buttons;
    ISensor sensor;

    private int col, row;
    int ix;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button ba = (Button) findViewById(R.id.ba);
        Button bb = (Button) findViewById(R.id.bb);
        Button bc = (Button) findViewById(R.id.bc);
        Button bd = (Button) findViewById(R.id.bd);
        Button be = (Button) findViewById(R.id.be);
        Button bf = (Button) findViewById(R.id.bf);
        Button bg = (Button) findViewById(R.id.bg);
        Button bh = (Button) findViewById(R.id.bh);
        Button bi = (Button) findViewById(R.id.bi);
        Button bj = (Button) findViewById(R.id.bj);
        Button bk = (Button) findViewById(R.id.bk);
        Button bl = (Button) findViewById(R.id.bl);
        Button bm = (Button) findViewById(R.id.bm);
        Button bn = (Button) findViewById(R.id.bn);
        Button bo = (Button) findViewById(R.id.bo);
        Button bp = (Button) findViewById(R.id.bp);
        Button bq = (Button) findViewById(R.id.bq);
        Button br = (Button) findViewById(R.id.br);
        Button bs = (Button) findViewById(R.id.bs);
        Button bt = (Button) findViewById(R.id.bt);
        Button bu = (Button) findViewById(R.id.bu);
        Button bv = (Button) findViewById(R.id.bv);
        Button bw = (Button) findViewById(R.id.bw);
        Button bx = (Button) findViewById(R.id.bx);
        Button by = (Button) findViewById(R.id.by);
        Button bz = (Button) findViewById(R.id.bz);
        Button bå = (Button) findViewById(R.id.bå);
        Button bä = (Button) findViewById(R.id.bä);
        Button bö = (Button) findViewById(R.id.bö);
        Button b_ = (Button) findViewById(R.id.b_);


        final TextView tv = (TextView) findViewById(R.id.textView);

        buttons = new Button[]{ba, bb, bc, bd, be, bf, bg, bh, bi, bj, bk, bl, bm, bn, bo, bp, bq, br, bs, bt, bu, bv, bw, bx, by, bz, bå, bä, bö, b_};

        bå.setBackgroundColor(Color.BLUE);
        s = new DewireContestConnection(GROUP_TAG) {
            @Override
            public void onConnectionClosed() {
            }

            @Override
            public void onConnectionOpened() {
            }

        };
        sensor = new KJSensor(this);
        final ISensor.Observer observer = new ISensor.Observer() {
            static final int COLUMNS = 5;
            static final int ROWS = 6;

            @Override
            public void event(Event e) {
                System.out.println("Event catched "+e.name());
                ix = row * COLUMNS + col;
                buttons[ix].setBackgroundColor(Color.WHITE);
                switch (e) {
                    case UP:
                        row = (row - 1 + ROWS) % ROWS;
                        break;
                    case DOWN:
                        row = (row + 1) % ROWS;
                        break;
                    case LEFT:
                        col = (col - 1 + COLUMNS) % COLUMNS;
                        break;
                    case RIGHT:
                        col = (col + 1) % COLUMNS;
                        break;
                }
                ix = row * COLUMNS + col;
                buttons[ix].setBackgroundColor(Color.BLUE);
                switch (e) {
                    case SELECT:
                        buttons[ix].setBackgroundColor(Color.YELLOW);
                        tv.setText(tv.getText().toString() + buttons[ix].getText());
                        break;
                    case ERASE:
                        buttons[ix].setBackgroundColor(Color.RED);
                        String old = tv.getText().toString();
                        tv.setText(old.substring(0, old.length()-1));
                        break;
                }
            }
        };
        sensor.register(observer);
        /*final Handler handler = new Handler();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        ISensor.Observer.Event e = ISensor.Observer.Event.values()[(int)(Math.random()*ISensor.Observer.Event.values().length)];
                        observer.event(e);
                    }
                });
            }
        };
        new Timer().scheduleAtFixedRate(task,1000,1000);*/
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    public void updateString(String str) {
        if (s != null && s.isConnected()) {
            s.sendMessage(str);
        }
    }

    public void killapp() {
        s.disconnect();
        this.finish();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://se.dewire.studs2016template/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://se.dewire.studs2016template/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        int keyCode = event.getKeyCode();
        switch (keyCode) {
            case KeyEvent.KEYCODE_VOLUME_UP:
                System.out.println("Vol_up");
                buttons[ix].setBackgroundColor(Color.YELLOW);
                tv.setText(tv.getText().toString() + buttons[ix].getText());

            case KeyEvent.KEYCODE_VOLUME_DOWN:
                System.out.println("Vol_up");
                buttons[ix].setBackgroundColor(Color.RED);
                String old = tv.getText().toString();
                tv.setText(old.substring(0, old.length() - 1));
                return true;
            default:
                return super.dispatchKeyEvent(event);
        }
    }
}
