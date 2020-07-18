package com.example.myfinalappsproject;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputLayout;

import org.odata4j.consumer.ODataClientRequest;
import org.odata4j.consumer.ODataConsumer;
import org.odata4j.consumer.ODataConsumers;
import org.odata4j.consumer.behaviors.OClientBehavior;
import org.odata4j.core.ODataVersion;
import org.odata4j.core.OEntity;

public class SignIn extends AppCompatActivity {
    // Variables
    Button signInBtn;
    TextView mHeader,mDesc;
    LinearLayout mCard;
    Animation mTopAnim,mRightAnim;
    TextInputLayout mUserID,mUserPassword;
    ODataConsumer consumer;
    String UserID,UserPassWord;
    UserClass tempUserClass;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        sharedPreferences = getSharedPreferences("DataFromUserSignIn",MODE_PRIVATE);
        Hook();
        AnimationDisPlay();

        String serviceUrl = ServiceAddress.serviceURL;
        OClientBehavior bacsicAthur = new OClientBehavior() {
            @Override
            public ODataClientRequest transform(ODataClientRequest request)
            {
                //return request.header("Accept", "application/json");
                return request.header("MaxDataServiceVersion", ODataVersion.V2.asString);
            }
        };
        consumer = ODataConsumers.newBuilder(serviceUrl).setClientBehaviors(bacsicAthur).build();
        // SetUp Connection with WebService

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserID = mUserID.getEditText().getText().toString();
                UserPassWord = mUserPassword.getEditText().getText().toString();
                new CheckIDTask().execute(UserID,UserPassWord);
            }
        });
        // SignIn Button Click function
    } // Function create all acitvity view when we start activity

    private void AnimationDisPlay() {
        mTopAnim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.top_anim);
        mRightAnim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.right_anim);
        mHeader.setAnimation(mTopAnim);
        mDesc.setAnimation(mTopAnim);
        mCard.setAnimation(mRightAnim);
    } //Function display animations

    private void Hook() {
        mUserPassword = findViewById(R.id.SignIn_UserPassWord);
        mUserID = findViewById(R.id.SignIn_UserID);
        mHeader = findViewById(R.id.SignIn_Header);
        mDesc =findViewById(R.id.SignIn_Desc);
        mCard = findViewById(R.id.SignIn_Card);
        signInBtn = findViewById(R.id.SignIn_Btn);
    } // Function hook View object with their ID

    private class CheckIDTask extends AsyncTask<String,Long,Integer>{

       @Override
       protected void onPreExecute() {
           mUserID.setErrorEnabled(false);
           super.onPreExecute();
       }
       String error;
       @Override
       protected Integer doInBackground(String...params) {

         try{
               String UserID = params[0];
               String UserPassWord = params[1];
             for (OEntity DataFromDataBase : consumer.getEntities("NhanViens").filter("substring(MaSo, 4) eq '" + UserID + "' and MatKhau eq '" + UserPassWord + "' and NghiViec eq false").execute())
             {
                 tempUserClass = new UserClass();
                 tempUserClass.setID(DataFromDataBase.getProperty("Id", Integer.class).getValue());
                 tempUserClass.setIdentityName(DataFromDataBase.getProperty("MaSo", String.class).getValue());
                 tempUserClass.setPassWord(DataFromDataBase.getProperty("MatKhau", String.class).getValue());
                 tempUserClass.setUserName(DataFromDataBase.getProperty("HoTen", String.class).getValue());
                 tempUserClass.setEmail(DataFromDataBase.getProperty("Email",String.class).getValue());
                 return 1;
             }
             if(tempUserClass == null);
             return 0;
         }catch (Exception ex){
             error = ex.toString();
             return -1;
         }
       }

       @Override
       protected void onPostExecute(Integer integer) {

           switch (integer){
               case 1:{
                   Intent intent = new Intent(getApplicationContext(),DashBoard.class);
                   SharedPreferences.Editor editor = sharedPreferences.edit();
                   editor.putString("UserPassWord",tempUserClass.getPassWord());
                   editor.putString("UserIndentifyName",tempUserClass.getIdentityName());
                   editor.putString("UserEmail",tempUserClass.getEmail());
                   editor.putString("UserName",tempUserClass.getUserName());
                   editor.apply();
                   // Store data in SharedPreferences name"DataFromUserSignIn" and pass to UserProflie Acivity
                   startActivity(intent);
                   break;
               }
               case 0:{
                   mUserID.setErrorEnabled(true);
                   mUserID.setError("No such user is found");
                   break;
               }
               case -1:{
                   Toast.makeText(getApplicationContext(),"Check your Connection!",Toast.LENGTH_LONG).show();
                   break;
               }

           }
       }
   } // Check User Accounts

}
