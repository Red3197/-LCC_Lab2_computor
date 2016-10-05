package com.example.labcalculator;

//import android.animation.AnimatorSet;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {
	
	TextView txvAns , txvBuf , txvOpe;
	String ans;
	Button[] btns;
	int[] ids;
	final int TOTLE_BUTTON = 18 ;
	int operator;
	boolean reFlag;
	double buffer;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ids = new int[TOTLE_BUTTON];
		btns= new Button[TOTLE_BUTTON];
		ans= "0";
		operator =0;
		reFlag =false;
		buffer =0;
		setId();
		findV();
		listener();
	}
	
	@Override
	public void onClick(View v){
		switch(v.getId()){
		case R.id.btn0:
		case R.id.btn1:
		case R.id.btn2:
		case R.id.btn3:
		case R.id.btn4:
		case R.id.btn5:
		case R.id.btn6:
		case R.id.btn7:
		case R.id.btn8:
		case R.id.btn9:
		case R.id.btn00:
			showNumber(v.getId());
			break;
		case R.id.btnDot:
			if(ans.indexOf('.')==-1)
				showNumber(v.getId());
			break;
		case R.id.btnClr:
			clear();
			break;
		case R.id.btnAdd:
			operator= 1;
			push(operator);
			break;
		case R.id.btnSub:
			operator= 2;
			push(operator);
			break;
		case R.id.btnMul:
			operator= 3;
			push(operator);
			break;
		case R.id.btnDiv:
			operator= 4;
			push(operator);
			break;
		case R.id.btnEqu:
			compute(operator);
			
			break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void push(int operator){
		reFlag =false;
		txvBuf.setText(ans);
		switch(operator){
		case 1:
			txvOpe.setText(" +");
			break;
		case 2:
			txvOpe.setText(" -");
			break;
		case 3:
			txvOpe.setText(" x");
			break;
		case 4:
			txvOpe.setText(" กา");
		}
		ans= "0";
		txvAns.setText(ans);
		buffer =Double.parseDouble( txvBuf.getText().toString() );
	}
	
	public void compute(int operator){
		reFlag =true;
		txvOpe.setText("");
		switch(operator){
		case 1:
			buffer += Double.parseDouble(ans);
			ans = String.valueOf(buffer);
			txvAns.setText(ans);
			break;
		case 2:
			buffer -= Double.parseDouble(ans);
			ans = String.valueOf(buffer);
			txvAns.setText(ans);
			break;
		case 3:
			buffer *= Double.parseDouble(ans);
			ans = String.valueOf(buffer);
			txvAns.setText(ans);
			break;
		case 4:
			buffer /= Double.parseDouble(ans);
			ans = String.valueOf(buffer);
			txvAns.setText(ans);
			break;
		}
	}
	
	public void clear(){
		ans = "0" ;
		operator =0 ;
		reFlag =false;
		buffer =0;
		txvAns.setText(ans);
		txvBuf.setText("");
		txvOpe.setText("");
	}
	
	public void showNumber(int id){
		if(reFlag){
			clear();
			reFlag =false;
		}
		
		if(Math.abs( Double.parseDouble(ans) - 0 ) < 0.000001 
				&& ans.indexOf('.') == -1 ){
			for(int i=0; i<TOTLE_BUTTON ; i++){
				if(ids[i] == id){
					if(i>0 && i<10)
						ans = String.valueOf( i ) ;
					else if(i==11)
						ans = "0." ;
				}
			}
		}
		else{
			for(int i=0; i<TOTLE_BUTTON ; i++){
				if(ids[i] == id){
					if(i==10)
						ans += "00" ;
					else if(i==11)
						ans += "." ;
					else
						ans += String.valueOf( i );
				}
			}
		}
		txvAns.setText(ans);
	}
	
	public void listener(){
		for(int i=0; i<TOTLE_BUTTON ; i++){
			btns[i].setOnClickListener(this);
		}
	}
	
	public void findV(){
		txvAns= (TextView) findViewById(R.id.txvAns);
		txvBuf= (TextView) findViewById(R.id.txvBuffer);
		txvOpe= (TextView) findViewById(R.id.txvOperator);
		for(int i=0; i<TOTLE_BUTTON ; i++)
			btns[i]= (Button) findViewById(ids[i]);
	}
	
	public void setId(){
		ids[0]  = R.id.btn0 ;
		ids[1]  = R.id.btn1 ;
		ids[2]  = R.id.btn2 ;
		ids[3]  = R.id.btn3 ;
		ids[4]  = R.id.btn4 ;
		ids[5]  = R.id.btn5 ;
		ids[6]  = R.id.btn6 ;
		ids[7]  = R.id.btn7 ;
		ids[8]  = R.id.btn8 ;
		ids[9]  = R.id.btn9 ;
		ids[10] = R.id.btn00 ;
		ids[11] = R.id.btnDot;
		ids[12] = R.id.btnClr ;
		ids[13] = R.id.btnAdd ;
		ids[14] = R.id.btnSub ;
		ids[15] = R.id.btnMul ;
		ids[16] = R.id.btnDiv ;
		ids[17] = R.id.btnEqu ;
	}
}
