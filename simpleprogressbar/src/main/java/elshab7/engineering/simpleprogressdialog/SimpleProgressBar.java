package elshab7.engineering.simpleprogressdialog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.widget.Toast;

public class SimpleProgressBar extends ConstraintLayout {
    private TextView mTextView,mProgressTxt;
    private ProgressBar mProgressBar;
    private boolean isIndeterminate=true, isHorizontal =false;
    private MediaPlayer mMediaPlayer;
    private CountDownTimer mCountDownTimer;
    private Handler mHandler=new Handler();
    private Context mContext;
    private String textCaption;
    private String style="0";

    public SimpleProgressBar(Context context) {
        super(context);
        mContext=context;
        init();
    }

    public SimpleProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext=context;

        TypedArray arr = context.obtainStyledAttributes(attrs, R.styleable.SimpleProgressBar);
        readArray(arr);
        arr.recycle();

        init();
    }


    private void readArray(TypedArray arr) {
        if(arr!=null) {
            isIndeterminate = arr.getBoolean(R.styleable.SimpleProgressBar_SPB_isIndeteminate, true);
            if(arr.hasValue(R.styleable.SimpleProgressBar_SPB_text))textCaption=arr.getString(R.styleable.SimpleProgressBar_SPB_text);
            if(arr.hasValue(R.styleable.SimpleProgressBar_SPB_style)){
                style=arr.getString(R.styleable.SimpleProgressBar_SPB_style);
            }
        }
    }

    private void init() {
        int layout = R.layout.dialog_view;
        int textSize=16;
        removeView(this);
        switch (style){
            case "0":
                layout=R.layout.dialog_view;
                break;
            case "1":
                layout=R.layout.dialog_view_small;
                textSize=12;
                break;
            case "2":
                layout=R.layout.dialog_view_large;
                textSize=18;
                break;
            case "3":
                layout=R.layout.dialog_view_horizontal;
                isHorizontal=true;
                break;
        }
        LayoutInflater.from(mContext).inflate(layout,this);
        mTextView = findViewById(R.id.textView);
        mProgressTxt = findViewById(R.id.progressTxt);
        mProgressBar = findViewById(R.id.progressBar);
        setIndeterminatePrivate(isIndeterminate);
        if(textCaption!=null)setText(textCaption);
        mProgressTxt.setTextSize(textSize);
        clear();
    }

    public SimpleProgressBar setIndeterminateTintColor(int color){
        mProgressBar.getIndeterminateDrawable().setColorFilter(color, PorterDuff.Mode.SRC_IN);
        return this;
    }

    public SimpleProgressBar setIndeterminateTintColor(int color,PorterDuff.Mode mode){
        mProgressBar.getIndeterminateDrawable().setColorFilter(color, mode);
        return this;
    }

    public SimpleProgressBar setCircularRadious(int radious){
        if(!isHorizontal) {
            int radious2=Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, radious,mContext.getResources().getDisplayMetrics()));

            //radious=(int) (radious / ((float) mContext.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT));
            //radious=(int) (radious / ((float) mContext.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT));
            mProgressBar.setLayoutParams(new FrameLayout.LayoutParams(radious2, radious2));
        }
        else
            Toast.makeText(mContext, "Radious isn't allowed in horizontal bar!", Toast.LENGTH_SHORT).show();
        return this;
    }

    public int getCircularRadious(){
        if(!isHorizontal)
            return mProgressBar.getLayoutParams().height;
        else
        {
            Toast.makeText(mContext, "Radious isn't allowed in horizontal bar!", Toast.LENGTH_SHORT).show();
            return -1;
        }
    }

    private void clear(){
        //try {
        //mHandler.removeCallbacksAndMessages(null);
        //mHandler=null;
        mHandler=new Handler();

        if (mCountDownTimer != null) {
            //mCountDownTimer.onFinish();
            mCountDownTimer.cancel();
            mCountDownTimer = null;
            mProgressTxt.setVisibility(GONE);
        }

        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
        System.gc();
        //}catch (Exception ignored){}
    }

    public SimpleProgressBar setText(final String message) {
        try {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    if (message == null || TextUtils.isEmpty(message)) {
                        mTextView.setVisibility(View.GONE);
                        return;
                    }
                    mTextView.setText(message);
                    mTextView.setVisibility(View.VISIBLE);
                }
            });
        }catch (Exception ignored){}

        return this;
    }

    public SimpleProgressBar setProgressText(final String message) {
        try {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    if (message == null || TextUtils.isEmpty(message)) {
                        mProgressTxt.setVisibility(View.GONE);
                        return;
                    }
                    mProgressTxt.setText(message);
                    mProgressTxt.setVisibility(View.VISIBLE);
                }
            });
        }catch (Exception ignored){}

        return this;
    }

    public SimpleProgressBar setProgressWithText(final int progress) {
        try {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    setProgress(progress);
                    mProgressTxt.setText(String.valueOf(progress));
                    mProgressTxt.setVisibility(View.VISIBLE);
                }
            });
        }catch (Exception ignored){}

        return this;
    }

    public SimpleProgressBar setProgressWithTextPercentage(final int progress) {
        try {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    setProgress(progress);
                    mProgressTxt.setText(String.valueOf(progress)+" %");
                    mProgressTxt.setVisibility(View.VISIBLE);
                }
            });
        }catch (Exception ignored){}

        return this;
    }

    public SimpleProgressBar setTextColor(final int colorID) {
        try {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    mTextView.setTextColor(colorID);
                }
            });
        }catch (Exception ignored){}

        return this;
    }

    public SimpleProgressBar setProgressTextColor(final int colorID) {
        try {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    mProgressTxt.setTextColor(colorID);
                }
            });
        }catch (Exception ignored){}

        return this;
    }

    public TextView getProgressTextView() {
        return mProgressTxt;
    }

    public int getProgress() {
        return mProgressBar.getProgress();
    }

    public SimpleProgressBar setProgress(final int progress) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                int mProgress=progress;
                if(mProgress>mProgressBar.getMax()) mProgress = mProgressBar.getMax();
                mProgressBar.setProgress(mProgress);
            }
        });
        return this;
    }

    public int getMax() {
        return mProgressBar.getMax();
    }

    public SimpleProgressBar setMax(int max) {
        mProgressBar.setMax(max);
        return this;
    }

    public SimpleProgressBar setProgressDrawable(Drawable drawable) {
        mProgressBar.setProgressDrawable(drawable);
        return this;
    }

    public SimpleProgressBar setIndeterminateDrawable(Drawable drawable) {
        mProgressBar.setIndeterminateDrawable(drawable);
        return this;
    }

    public boolean isIndeterminate() {
        return mProgressBar.isIndeterminate();
    }

    private void setIndeterminatePrivate(boolean indeterminate) {
        isIndeterminate=indeterminate;
        mProgressBar.setIndeterminate(indeterminate);
        mProgressTxt.setVisibility(indeterminate?GONE:VISIBLE);
    }

    public SimpleProgressBar setIndeterminate(boolean indeterminate) {
        clear();
        setIndeterminatePrivate(indeterminate);
        setIndeterminatePrivate(indeterminate);
        return this;
    }


    public TextView getTextViewItself(){
        return mTextView;
    }

    public ProgressBar getProgressBarItself(){
        return mProgressBar;
    }

    public void runIndeterminateForTime(final int delaySeconds, final IndeterminateTimingCallback indeterminateTimingCallback) {
        clear();
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                setProgress(0);
                setIndeterminatePrivate(true);
            }
        });
        mCountDownTimer= new CountDownTimer(delaySeconds * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {}

            @Override
            public void onFinish() {
                indeterminateTimingCallback.onFinish();
            }
        }.start();
    }

    public interface IndeterminateTimingCallback {
        void onFinish();
    }

    public void runSteps(final int step, final int delayMillies) {
        runSteps(step,delayMillies,null);
    }
    public void runSteps(final int step, final int delayMillies, final StepsCallback stepsCallback) {
        clear();
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                setProgress(0);
                setProgressText("0 %");
                setIndeterminatePrivate(false);
            }
        });
        mCountDownTimer=new CountDownTimer(getMax() * delayMillies / step, delayMillies) {
            // 500 means, onTick function will be called at every 500 milliseconds

            @SuppressLint("SetTextI18n")
            @Override
            public void onTick(long leftTimeInMilliseconds) {
                long seconds = leftTimeInMilliseconds / delayMillies;
                mProgressBar.setProgress(getMax()-(int)seconds*step);
                mProgressTxt.setText(String.valueOf(getProgress())+" %");
                if(stepsCallback!=null)stepsCallback.onProgressing(getProgress());
            }
            @Override
            public void onFinish() {
                if(getProgress()<getMax()&&mCountDownTimer!=null){
                    mProgressBar.setProgress(getMax());
                    setProgressText(String.valueOf(getMax())+" %");
                    if(stepsCallback!=null)stepsCallback.onProgressing(getProgress());
                }
                if(mCountDownTimer!=null) {
                    mCountDownTimer.cancel();
                    mCountDownTimer=null;
                }
                if(stepsCallback!=null)stepsCallback.onFinish();
            }
        }.start();
    }

    public interface StepsCallback {
        void onProgressing(int currentProgress);
        void onFinish();
    }


    public void startTimer(final int secondsPeriod) {
        clear();
        startTimer(secondsPeriod,false,null);
    }
    public void startTimer(final int secondsPeriod,TimerCallback timerCallback) {
        clear();
        startTimer(secondsPeriod,false,timerCallback);
    }
    public void startTimerWithSound1(final int secondsPeriod) {
        clear();
        mMediaPlayer=MediaPlayer.create(mContext, R.raw.tick_sound);
        startTimer(secondsPeriod,true,null);
    }
    public void startTimerWithSound1(final int secondsPeriod,TimerCallback timerCallback) {
        clear();
        mMediaPlayer=MediaPlayer.create(mContext, R.raw.tick_sound);
        startTimer(secondsPeriod,true,timerCallback);
    }
    public void startTimerWithSound2(final int secondsPeriod) {
        clear();
        mMediaPlayer=MediaPlayer.create(mContext, R.raw.ticking_clock);
        startTimer(secondsPeriod,true,null);
    }
    public void startTimerWithSound2(final int secondsPeriod,TimerCallback timerCallback) {
        clear();
        mMediaPlayer=MediaPlayer.create(mContext, R.raw.ticking_clock);
        startTimer(secondsPeriod,true,timerCallback);
    }
    public void startTimerWithCustomSound(final int secondsPeriod,int soundResID) {
        clear();
        mMediaPlayer=MediaPlayer.create(mContext, soundResID);
        startTimer(secondsPeriod,true,null);
    }
    public void startTimerWithCustomSound(final int secondsPeriod,int soundResID,TimerCallback timerCallback) {
        clear();
        mMediaPlayer=MediaPlayer.create(mContext, soundResID);
        startTimer(secondsPeriod,true,timerCallback);
    }

    private void startTimer(final int secondsPeriod, final boolean withSound,final TimerCallback timerCallback) {
        mHandler.post(new Runnable() {
            @SuppressLint("DefaultLocale")
            @Override
            public void run() {
                setIndeterminatePrivate(false);
                setProgressText(String.format("%02d", secondsPeriod/60) + ":" + String.format("%02d", secondsPeriod%60));
                setMax(secondsPeriod);
                mProgressBar.setProgress(secondsPeriod);
            }
        });
        mCountDownTimer=new CountDownTimer(secondsPeriod * 1000, 1000) {
            // 500 means, onTick function will be called at every 500 milliseconds

            @SuppressLint("DefaultLocale")
            @Override
            public void onTick(long leftTimeInMilliseconds) {
                long seconds = leftTimeInMilliseconds / 1000;
                mProgressBar.setProgress(secondsPeriod-(int)seconds);
                setProgressText(String.format("%02d", seconds/60) + ":" + String.format("%02d", seconds%60));
                // format the textview to show the easily readable format
                if(withSound)startSound();
                if(timerCallback!=null){
                    timerCallback.onTick(secondsPeriod-(int)seconds);
                }
            }
            @Override
            public void onFinish() {
                if(withSound)stopSound();
                if(mCountDownTimer!=null) {
                    mCountDownTimer.cancel();
                    mCountDownTimer=null;
                }
                if(timerCallback!=null){
                    timerCallback.onFinish();
                }
            }
        }.start();

    }

    public interface TimerCallback {
        void onTick(int currentProgress);
        void onFinish();
    }

    private void startSound(){
        try {
            if (mMediaPlayer != null) mMediaPlayer.start();
        }catch (Exception ignored){}
    }
    private void stopSound(){
        try {
            if (mMediaPlayer != null) mMediaPlayer.stop();
        }catch (Exception ignored){}
    }

    public void hide(){
        this.setVisibility(GONE);
    }

    public void show(){
        //clear();
        this.setVisibility(VISIBLE);
    }
}
