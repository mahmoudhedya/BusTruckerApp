package com.inspirecore.bustruckerapp.controls;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.inspirecore.bustruckerapp.R;
import com.inspirecore.bustruckerapp.database.Organization;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZAFLOUT on 8/28/2014.
 */

public class LoginControl {

    Activity activity;
    public ProgressDialog progress;
    SharedPreferences myPrefs;
    SharedPreferences.Editor prefsEditor;

    public LoginControl(Activity activity) {
        this.activity = activity;

        myPrefs =  activity.getSharedPreferences("bustrucker",0);
        prefsEditor = myPrefs.edit();

        /*
		boolean Connected = NetworkUtil.getConnectivityStatusString(context);
		if (!Connected) {
			((Activity) context).runOnUiThread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					Toast.makeText(context, "check_internet", Toast.LENGTH_SHORT).show();
				}
			});
			return "";
		}else{
		*/
    }

    // local api ========================== local means in our api =================================
    // login ================================================================================
    public void doLogin(String Username, String Password){

        // {Username: 'Azzam', Password: '12345678'}

        /*
        {"Success":true,
        "Data":
            {"UserId":3,
            "UserName":"Azzam",
            "FullName":"Azzam",
            "RoleId":3,
            "RoleName":"Organization",
            "OrganizationId":1,
            "OrganizationName":"Azzam Car"},
        "ErrorMsg":""}
         */

        final List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("Username", Username));
        nameValuePairs.add(new BasicNameValuePair("Password", Password));

        progress = ProgressDialog.show(activity, "Login", "Please Wait ...", true);

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                String login_string = ServerCall.postData(activity.getString(R.string.link) +
                        activity.getString(R.string.link_login), nameValuePairs);
                try {
                    if(login_string != null) {

                        final JSONObject login_json = new JSONObject(login_string);
                        if (login_json != null) {
                            activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        progress.dismiss();

                                        if (Boolean.parseBoolean(login_json.getString("Success"))){
                                            // true login
                                            Toast.makeText(activity, "Login Success"
                                                    , Toast.LENGTH_SHORT).show();

                                            JSONObject data = login_json.getJSONObject("Data");

                                            GlobalVariabls.organization = new Organization();
                                            GlobalVariabls.organization.setUserId(data.getInt("UserId"));
                                            GlobalVariabls.organization.setUserName(data.getString("UserName"));

                                            GlobalVariabls.organization.setFullName(data.getString("FullName"));
                                            GlobalVariabls.organization.setRoleId(data.getInt("RoleId"));
                                            GlobalVariabls.organization.setRoleName(data.getString("RoleName"));
                                            GlobalVariabls.organization.setOrganizationId(data.getInt("OrganizationId"));
                                            GlobalVariabls.organization.setOrganizationName(data.getString("OrganizationName"));
                                        }
                                        else {
                                            Toast.makeText(activity, login_json.getString("ErrorMsg"), Toast.LENGTH_SHORT).show();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    progress.dismiss();
                                }
                            });
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }

}
