
# SimpleProgressBar

⚡️A Simple,fast,Reliable, Fully controlled library for managing progress bars in android. ⚡️

⚡️ Auto timer function, auto runSteps function ... with full callbacks control. ⚡️

<img src="https://github.com/a7mdragab/SimpleProgressBar/blob/master/app/src/main/res/drawable/simpleprogressbar1.png"
     alt=""
     width="400" height="650"
     style="float: left; margin-right: 10px;" />

<img src="https://github.com/a7mdragab/SimpleProgressBar/blob/master/app/src/main/res/drawable/simpleprogressbar2.png"
     alt=""
     width="400" height="650"
     style="float: left; margin-right: 10px;" />

<img src="https://github.com/a7mdragab/SimpleProgressBar/blob/master/app/src/main/res/drawable/simpleprogressbar3.png"
     alt=""
     width="400" height="650"
     style="float: left; margin-right: 10px;" />


<img src="https://github.com/a7mdragab/SimpleProgressBar/blob/master/app/src/main/res/drawable/simpleprogressbar4.png"
     alt=""
     width="400" height="650"
     style="float: left; margin-right: 10px;" />

+ ## Setup
Gradle dependency (recommended)

Add the following to your project level build.gradle:
``` 
allprojects {
	repositories {
		maven { url "https://jitpack.io" }
	}
}
``` 


Add this to your app build.gradle:
``` 
dependencies {
	implementation 'com.github.a7mdragab:SimpleProgressBar:1.0'
}
```

+ ## Documentation
On your layout.xml put it.
```
<elshab7.engineering.simpleprogressdialog.SimpleProgressBar
        android:id="@+id/simpleProgressBarCircular"
        android:layout_width="wrap_content" //set it to match parent or specific dps on horizontal style
        android:layout_height="wrap_content"
        app:SPB_isIndeteminate="true"
        app:SPB_style="circular" //circular - circular_small - circular_large - Horizontal
        app:SPB_text="Circular..." //use if you need it only
        />
```

## On your activity.java
```
public class MainActivity extends AppCompatActivity {
    
    SimpleProgressBar simpleProgressBarCircular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        simpleProgressBarCircular =findViewById(R.id.simpleProgressBarCircular);
        
        //The full sample on the app folder
   }
}
```

## Available functions:
```
simpleProgressBarCircular.setText(String message);
simpleProgressBarCircular.setProgressText(String message);
simpleProgressBarCircular.getProgress();
simpleProgressBarCircular.setProgress(int progress);
simpleProgressBarCircular.setProgressWithText(int progress); 
simpleProgressBarCircular.setProgressWithTextPercentage(int progress);
simpleProgressBarCircular.setProgressDrawable(Drawable drawable);

simpleProgressBarCircular.------------------------------------------- //All below functions

getMax();
setMax(int max);

setIndeterminateTintColor(int color);
setIndeterminateTintColor(int color,PorterDuff.Mode mode);

setIndeterminateDrawable(Drawable drawable);
boolean isIndeterminate();
setIndeterminate(boolean indeterminate);

setTextColor(int color);
setProgressTextColor(int color);

//Get the views itself for full control
getProgressTextView();
getTextViewItself();
getProgressBarItself();

setCircularRadious(int radious);
getCircularRadious();

runIndeterminateForTime(int delaySeconds, IndeterminateTimingCallback indeterminateTimingCallback);

runSteps(int step, int delayMillies);
runSteps(int step, int delayMillies, StepsCallback stepsCallback);

startTimer(int secondsPeriod);
startTimer(int secondsPeriod,TimerCallback timerCallback);

startTimerWithSound1(int secondsPeriod);
startTimerWithSound1(int secondsPeriod,TimerCallback timerCallback);

startTimerWithSound2(int secondsPeriod);
startTimerWithSound2(int secondsPeriod,TimerCallback timerCallback);

startTimerWithCustomSound(int secondsPeriod);
startTimerWithCustomSound(int secondsPeriod,TimerCallback timerCallback);

hide();
show();
```
