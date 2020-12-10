package com.pixels.gimnasio;
import android.content.SharedPreferences;

import android.os.CountDownTimer;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;

import android.view.inputmethod.InputMethodManager;

import android.widget.Button;

import android.widget.EditText;

import android.widget.TextView;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.Snackbar;
import android.view.ViewGroup;
import android.support.design.widget.FloatingActionButton;
import android.widget.Button;
import android.support.v7.widget.RecyclerView;
import android.app.Activity;import android.os.Bundle;import android.support.design.widget.CollapsingToolbarLayout;import android.support.v4.app.Fragment;import android.view.LayoutInflater;import android.view.View;import android.view.ViewGroup;import android.widget.TextView;import android.content.DialogInterface;import android.content.Intent;import android.support.design.widget.NavigationView;import android.support.v4.view.GravityCompat;import android.support.v4.widget.DrawerLayout;import android.support.v7.app.ActionBarDrawerToggle;import android.support.v7.app.AlertDialog;import android.support.v7.app.AppCompatActivity;import android.os.Bundle;import android.support.v7.widget.LinearLayoutManager;import android.support.v7.widget.RecyclerView;import android.support.v7.widget.Toolbar;import android.view.Menu;import android.view.MenuItem;import android.view.View;import android.widget.TextView;import android.widget.Toast; import com.android.volley.RequestQueue;import com.android.volley.Response;import com.android.volley.VolleyError;import com.android.volley.toolbox.JsonArrayRequest;import com.android.volley.toolbox.Volley; import org.json.JSONArray;import org.json.JSONException;import org.json.JSONObject; import java.util.ArrayList;import java.util.List; import android.widget.*;import android.view.View.OnClickListener;import android.transition.Transition;import android.content.Context;import android.view.animation.LayoutAnimationController;import android.view.animation.AnimationUtils;

import android.widget.Toast;

import java.util.Locale;
import android.widget.ProgressBar;


public class CronometroActivity extends AppCompatActivity {
	

	 TextView mTextViewCountDown,ruti;



	private Button mButtonStartPause;

	String mintos;

	private CountDownTimer mCountDownTimer;

	private boolean mTimerRunning;

	private long mStartTimeInMillis;

	private long mTimeLeftInMillis;

	private long mEndTime;
	int i=0;
	ProgressBar pro;
	@Override

	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.layout_cronometro);
		Bundle extra = getIntent().getExtras();		
		mintos=extra.getString("tiempo");
		//Toast.makeText(getApplicationContext(), ""+mintos, Toast.LENGTH_LONG).show();
		ruti=(TextView)findViewById(R.id.ruti);
		ruti.setText(extra.getString("ruti"));
		
		pro=(ProgressBar)findViewById(R.id.p1);

		mTextViewCountDown=(TextView)findViewById(R.id.text_view_countdown);
		new android.os.Handler().postDelayed(new Runnable() { 		
				@Override public void run() { 
					if (mTimerRunning) {

						pauseTimer();

					}
					final long millisInput = Long.parseLong(mintos) * 60000;
					setTime(millisInput);
					resetTimer();
				}
			},1);

		mButtonStartPause=(Button)findViewById(R.id.button_start_pause);
		mTextViewCountDown.setText("00:00");
		mTextViewCountDown.setOnClickListener(new View.OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					resetTimer();
				}
				
			
			
			
		});
		pro.setProgress(0);

		
		
		mButtonStartPause.setOnClickListener(new View.OnClickListener() {

				@Override

				public void onClick(View v) {

					if (mTimerRunning) {

						pauseTimer();

					} else {
						if(i==0){
						
						i=1;
						}
						startTimer();

					}

				}

			});

			
		
	}
	
	private void setTime(long milliseconds) {

		mStartTimeInMillis = milliseconds;
		resetTimer();

		closeKeyboard();

	}

	private void startTimer() {

		mEndTime = System.currentTimeMillis() + mTimeLeftInMillis;

		mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {

			@Override

			public void onTick(long millisUntilFinished) {

				mTimeLeftInMillis = millisUntilFinished;

				updateCountDownText();

			}

			@Override

			public void onFinish() {

				mTimerRunning = false;

				updateWatchInterface();

			}

		}.start();

		mTimerRunning = true;

		updateWatchInterface();

	}

	private void pauseTimer() {

		mCountDownTimer.cancel();

		mTimerRunning = false;

		updateWatchInterface();

	}

	private void resetTimer() {

		mTimeLeftInMillis = mStartTimeInMillis;

		updateCountDownText();

		updateWatchInterface();

	}

	private void updateCountDownText() {

		int hours = (int) (mTimeLeftInMillis / 1000) / 3600;

		int minutes = (int) ((mTimeLeftInMillis / 1000) % 3600) / 60;

		int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

		String timeLeftFormatted;

		if (hours > 0) {

			timeLeftFormatted = String.format(Locale.getDefault(),

											  "%d:%02d:%02d", hours, minutes, seconds);

		} else {

			timeLeftFormatted = String.format(Locale.getDefault(),

											  "%02d:%02d", minutes, seconds);

		}
		
		
		int hours1 = (int) (mStartTimeInMillis / 1000) / 3600;

		int minutes1 = (int) ((mStartTimeInMillis/ 1000) % 3600) / 60;

		int seconds1 = (int) (mStartTimeInMillis / 1000) % 60;
		
		int nmi=((hours1*3600)+(minutes1*60)+seconds1);
		int nmf=((hours*3600))+(minutes*60)+seconds;
		
		float nmin= (float) (nmi);
		float nmff= (float) (nmf);
		float ope=((100/nmin)*nmff);
		//Toast.makeText(getApplicationContext(), "nmf= "+nmf+"nmi= "+nmi+" ope)= "+ope, Toast.LENGTH_LONG).show();
		pro.setProgress((int)ope);
		mTextViewCountDown.setText(timeLeftFormatted);

	}

	private void updateWatchInterface() {

		if (mTimerRunning) {

			

			mButtonStartPause.setText("Pause");

		} else {

			

			mButtonStartPause.setText("Start");

			if (mTimeLeftInMillis < 1000) {

				mButtonStartPause.setVisibility(View.INVISIBLE);

			} else {

				mButtonStartPause.setVisibility(View.VISIBLE);

			}

			if (mTimeLeftInMillis < mStartTimeInMillis) {

				
			} else {

				

			}

		}

	}

	private void closeKeyboard() {

		View view = this.getCurrentFocus();

		if (view != null) {

			InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

			imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

		}

	}

	@Override

	protected void onStop() {

		super.onStop();

		SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);

		SharedPreferences.Editor editor = prefs.edit();

		editor.putLong("startTimeInMillis", mStartTimeInMillis);

		editor.putLong("millisLeft", mTimeLeftInMillis);

		editor.putBoolean("timerRunning", mTimerRunning);

		editor.putLong("endTime", mEndTime);

		editor.apply();

		if (mCountDownTimer != null) {

			mCountDownTimer.cancel();

		}

	}

	@Override

	protected void onStart() {

		super.onStart();

		SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);

		mStartTimeInMillis = prefs.getLong("startTimeInMillis", 600000);

		mTimeLeftInMillis = prefs.getLong("millisLeft", mStartTimeInMillis);

		mTimerRunning = prefs.getBoolean("timerRunning", false);

		updateCountDownText();

		updateWatchInterface();

		if (mTimerRunning) {

			mEndTime = prefs.getLong("endTime", 0);

			mTimeLeftInMillis = mEndTime - System.currentTimeMillis();

			if (mTimeLeftInMillis < 0) {

				mTimeLeftInMillis = 0;

				mTimerRunning = false;

				updateCountDownText();

				updateWatchInterface();

			} else {

				startTimer();

			}

		}

	}

	

	

	
	
}

