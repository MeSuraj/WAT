package learn.zero.say.wat.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.rilixtech.widget.countrycodepicker.CountryCodePicker;

import learn.zero.say.wat.R;

public class DirectChatActivity extends AppCompatActivity {

    private static final String FILE_NAME = "WhatsApp_file" ;
    private static final String NAME_KEY = "UserName_key";
    private static final String FIRST_TIME_SHOW_KEY = "FirstTimeShow_key";
    String yourName;

    final Context context = this;
    CountryCodePicker ccp;
    static EditText userPhoneNumber;
    EditText message;
    TextView fastReplayOne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.direct_msg_activity_main);

        //To hide action Bar(Tool Bar)
        getSupportActionBar().hide();

        ccp =findViewById(R.id.countryCode);
        userPhoneNumber = findViewById(R.id.phone_number_edt);
        message = findViewById(R.id.writeMessage);
        fastReplayOne = findViewById(R.id.nameBtn);

        checkAlertBox();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("yst","ss");
        if(resultCode==RESULT_OK){
            Log.i("yst","ss");
            userPhoneNumber.setText(data.getStringExtra("number"));
        }
    }

    public void checkAlertBox(){
        // Show AlertBox :-
        SharedPreferences sharedPreferences = getSharedPreferences(FILE_NAME,MODE_PRIVATE);
        int check = sharedPreferences.getInt(FIRST_TIME_SHOW_KEY,0);

        if (check == 0){
            makeAlertBox();
        }else {
            setTextInButton();
        }
    }

    public static void setContactNumber(String ph){
        userPhoneNumber.setText(ph);
    }

    public void makeAlertBox(){

        // load the dialog_promt_user.xml layout and inflate to view
        LayoutInflater layoutinflater = LayoutInflater.from(context);
        View promptUserView = layoutinflater.inflate(R.layout.direct_msg_dialog, null);

        MaterialAlertDialogBuilder alertDialogBuilder = new MaterialAlertDialogBuilder(context);
        alertDialogBuilder.setIcon(R.drawable.ic_person);
        alertDialogBuilder.setCancelable(false);

        alertDialogBuilder.setView(promptUserView);

        final EditText userAnswer = (EditText) promptUserView.findViewById(R.id.username);

        alertDialogBuilder.setTitle("What's your name?");

        // prompt for username
        alertDialogBuilder.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                // Shared Preferences :
                SharedPreferences.Editor editor = getSharedPreferences(FILE_NAME,MODE_PRIVATE).edit();
                editor.putString(NAME_KEY,userAnswer.getText().toString());
                editor.putInt(FIRST_TIME_SHOW_KEY,1);
                editor.apply();

                setTextInButton();
            }
        });

        // all set and time to build and show up!
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void setTextInButton(){
        //set Values
        SharedPreferences sharedPreferences = getSharedPreferences(FILE_NAME,MODE_PRIVATE);
        fastReplayOne.setText("Hello! I'm " + sharedPreferences.getString(NAME_KEY," ")+ ".");
        yourName = sharedPreferences.getString(NAME_KEY," ");
    }

    public void sendbtn(View view) {

        if(TextUtils.isEmpty(userPhoneNumber.getText().toString())){
            Toast.makeText(context, "Enter Phone Number", Toast.LENGTH_SHORT).show();
        }else {
            ccp.registerPhoneNumberTextView(userPhoneNumber);
            userPhoneNumber.setHint("Enter Number");

            String messageText = message.getText().toString();

            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse(
                            "https://api.whatsapp.com/send?phone=" + ccp.getFullNumberWithPlus() +
                                    "&text=" + messageText
                    )));
        }
    }

    public void nameBtn(View view) {

        if(TextUtils.isEmpty(userPhoneNumber.getText().toString())){
            Toast.makeText(context, "Enter Phone Number", Toast.LENGTH_SHORT).show();
        }else {
            ccp.registerPhoneNumberTextView(userPhoneNumber);
            userPhoneNumber.setHint("Enter Number");

            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse(
                            "https://api.whatsapp.com/send?phone=" + ccp.getFullNumberWithPlus()
                                    + "&text=" + "Hello! I'm " + yourName+ "."
                    )));
        }

    }

    public void btnOne(View view) {

        if(TextUtils.isEmpty(userPhoneNumber.getText().toString())){
            Toast.makeText(context, "Enter Phone Number", Toast.LENGTH_SHORT).show();
        }else {
            ccp.registerPhoneNumberTextView(userPhoneNumber);
            userPhoneNumber.setHint("Enter Number");

            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse(
                            "https://api.whatsapp.com/send?phone=" + ccp.getFullNumberWithPlus()
                                    + "&text=Hi..."
                    )));
        }
    }

    public void btnTwo(View view) {

        if(TextUtils.isEmpty(userPhoneNumber.getText().toString())){
            Toast.makeText(context, "Enter Phone Number", Toast.LENGTH_SHORT).show();
        }else {
            ccp.registerPhoneNumberTextView(userPhoneNumber);
            userPhoneNumber.setHint("Enter Number");

            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse(
                            "https://api.whatsapp.com/send?phone=" + ccp.getFullNumberWithPlus()
                                    + "&text=Call Me!"
                    )));
        }
    }

    public void btnThree(View view) {

        if(TextUtils.isEmpty(userPhoneNumber.getText().toString())){
            Toast.makeText(context, "Enter Phone Number", Toast.LENGTH_SHORT).show();
        }else {
            ccp.registerPhoneNumberTextView(userPhoneNumber);
            userPhoneNumber.setHint("Enter Number");

            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse(
                            "https://api.whatsapp.com/send?phone=" + ccp.getFullNumberWithPlus() +
                                    "&text=What's up?"
                    )));
        }
    }

    public void btnFour(View view) {

        if(TextUtils.isEmpty(userPhoneNumber.getText().toString())){
            Toast.makeText(context, "Enter Phone Number", Toast.LENGTH_SHORT).show();
        }else {
            ccp.registerPhoneNumberTextView(userPhoneNumber);
            userPhoneNumber.setHint("Enter Number");

            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse(
                            "https://api.whatsapp.com/send?phone=" + ccp.getFullNumberWithPlus() +
                                    "&text=How Are You?"
                    )));
        }
    }

    public void btnAttach(View view) {

        if(TextUtils.isEmpty(userPhoneNumber.getText().toString())){
            Toast.makeText(context, "Enter Phone Number", Toast.LENGTH_SHORT).show();
        }else {
            ccp.registerPhoneNumberTextView(userPhoneNumber);
            userPhoneNumber.setHint("Enter Number");

            String messageText = message.getText().toString();

            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse(
                            "https://api.whatsapp.com/send?phone=" + ccp.getFullNumberWithPlus() +
                                    "&text=" + messageText
                    )));
        }
    }
}
