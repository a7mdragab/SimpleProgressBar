package elshab7.engineering.simpleprogressdialogapp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;

import elshab7.engineering.simpleprogressdialog.SimpleProgressBar;

public class MainActivity extends AppCompatActivity {
    int x=70;
    SimpleProgressBar simpleProgressBarCircular1,simpleProgressBarCircular2,simpleProgressBarCircular3, simpleProgressBarHorizontal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        simpleProgressBarCircular1 =findViewById(R.id.simpleProgressBarCircular1);
        simpleProgressBarCircular2 =findViewById(R.id.simpleProgressBarCircular2);
        simpleProgressBarCircular3 =findViewById(R.id.simpleProgressBarCircular3);
        simpleProgressBarHorizontal =findViewById(R.id.simpleProgressBarHorizontal);


        findViewById(R.id.mainTxt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //simpleProgressBarCircular1.setText("100 Steps 40ms each");

                simpleProgressBarCircular1.setMax(100);
                simpleProgressBarCircular2.setMax(100);
                simpleProgressBarCircular3.setMax(100);
                simpleProgressBarCircular2.runSteps(5,96);
                simpleProgressBarCircular3.runSteps(5,96);

                simpleProgressBarCircular1.runSteps(5, 100, new SimpleProgressBar.StepsCallback() {
                    @Override
                    public void onProgressing(int currentProgress) {
                        Log.e("SimpleProgressBar","Main StepsCallback onProgressing: "+currentProgress);
                    }

                    @Override
                    public void onFinish() {
                        Log.e("SimpleProgressBar","Main StepsCallback onFinish");
                        simpleProgressBarCircular1.startTimer(5);
                        simpleProgressBarCircular2.startTimer(5);
                        simpleProgressBarCircular3.startTimer(5);
                        simpleProgressBarHorizontal.startTimer(5);
                    }
                });

                simpleProgressBarHorizontal.setMax(100);
                simpleProgressBarHorizontal.runSteps(5,96);
            }
        });

        findViewById(R.id.mainTxt2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*simpleProgressBarCircular1.setIndeterminateTintColor(Color.BLUE);
                simpleProgressBarCircular2.setIndeterminateTintColor(Color.BLUE);
                simpleProgressBarCircular3.setIndeterminateTintColor(Color.BLUE);
                simpleProgressBarHorizontal.setIndeterminateTintColor(Color.BLUE);*/

                simpleProgressBarCircular1.setText("");
                simpleProgressBarCircular2.setText("");
                simpleProgressBarCircular3.setText("");
                simpleProgressBarHorizontal.setText("");
                //simpleProgressBarCircular1.setProgressText("");

                simpleProgressBarCircular1.setIndeterminate(true);
                simpleProgressBarCircular2.setIndeterminate(true);
                simpleProgressBarCircular3.setIndeterminate(true);
                simpleProgressBarHorizontal.setIndeterminate(true);

                //simpleProgressBarCircular1.setCircularRadious(x);
                //simpleProgressBarCircular1.invalidate();
                //x+=30;

                simpleProgressBarCircular1.show();
                simpleProgressBarCircular2.show();
                simpleProgressBarCircular3.show();
                simpleProgressBarHorizontal.show();
            }
        });

        findViewById(R.id.mainTxt3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //simpleProgressBarCircular1.setText("Timer 1 Minute");
                simpleProgressBarCircular1.startTimerWithSound1(10, new SimpleProgressBar.TimerCallback() {
                    @Override
                    public void onTick(final int currentProgress) {
                        Log.e("SimpleProgressBar","Main TimerCallback onTick: "+currentProgress);
                    }

                    @Override
                    public void onFinish() {
                        Log.e("SimpleProgressBar","Main TimerCallback onFinish");
                    }
                });


                simpleProgressBarCircular2.startTimerWithSound1(10);
                simpleProgressBarCircular3.startTimerWithSound1(10);
                simpleProgressBarHorizontal.startTimerWithSound1(10);
            }
        });

        //------>> Show undeterminate for certain time
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                simpleProgressBarCircular1.setText("Loading1...");
                simpleProgressBarCircular2.setText("Loading2...");
                simpleProgressBarCircular3.setText("Loading3...");

                //simpleProgressBarCircular1.setIndeterminate(true);
                simpleProgressBarCircular1.runIndeterminateForTime(3, new SimpleProgressBar.IndeterminateTimingCallback() {
                    @Override
                    public void onFinish() {
                        simpleProgressBarCircular1.setIndeterminate(false);
                        simpleProgressBarCircular1.setProgressWithTextPercentage(100);
                        simpleProgressBarCircular1.setText("Done...");

                        simpleProgressBarCircular2.setIndeterminate(false);
                        simpleProgressBarCircular2.setProgressWithTextPercentage(100);
                        simpleProgressBarCircular2.setText("Done...");

                        simpleProgressBarCircular3.setIndeterminate(false);
                        simpleProgressBarCircular3.setProgressWithTextPercentage(100);
                        simpleProgressBarCircular3.setText("Done...");

                        Log.e("SimpleProgressBar","Main IndeterminateTimingCallback onFinish");
                    }
                });

                simpleProgressBarCircular2.setIndeterminate(true);
                simpleProgressBarCircular3.setIndeterminate(true);

                simpleProgressBarCircular1.show();
                simpleProgressBarCircular2.show();
                simpleProgressBarCircular3.show();

                simpleProgressBarHorizontal.setText("Loading...");
                simpleProgressBarHorizontal.setProgressText("Ok");
                simpleProgressBarHorizontal.setIndeterminate(true);
                simpleProgressBarHorizontal.show();

                TextView textView=simpleProgressBarHorizontal.getTextViewItself();
                textView.setTextColor(Color.RED);
                textView.setTextSize(30);

                ProgressBar progressBar=simpleProgressBarHorizontal.getProgressBarItself();
                progressBar.setPadding(10,10,10,10);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void ShowBars(View view) {
        simpleProgressBarCircular1.show();
        simpleProgressBarHorizontal.show();
    }

    public void HideBars(View view) {
        simpleProgressBarCircular1.hide();
        simpleProgressBarHorizontal.hide();
    }
}
