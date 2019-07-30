package e.shashij.outlab9;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class UserActivity extends AppCompatActivity {
    public static String name;
    public static String user;
    public static String company;
    public static String location;
    public static int number;
    private Context context = this;

    TextView tv1;
    TextView tv2;
    TextView tv3;
    ListView listView;

    //  public static ArrayList<String> names = new ArrayList<String>();
    // public static ArrayList<String> names = new ArrayList<String>()
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Toast.makeText(getApplicationContext(),"abc", Toast.LENGTH_SHORT).show();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        user = getIntent().getStringExtra("username");
        new DownloadAndroidRepos(getApplicationContext()).execute(user);


        // JSONObject re = new JSONObject(responseStrBuilder1.toString());
        tv1 = (TextView) findViewById(R.id.name);
        tv2 = (TextView) findViewById(R.id.company);
        tv3 = (TextView) findViewById(R.id.location);
//in your OnCreate() method
        listView = (ListView) findViewById(R.id.listview2);
        new ReposSec(getApplicationContext()).execute(user);


    }

    class DownloadAndroidRepos extends AsyncTask<String, Void, String> {
        @SuppressLint("StaticFieldLeak")
        private Context contextRef;

        public DownloadAndroidRepos(Context context) {
            contextRef = (context);
        }

        @Override
        protected String doInBackground(String... q) {
            try {
//            Toast.makeText(new SearchActivity().getApplicationContext(),"doinbg", Toast.LENGTH_SHORT).show();
                String s = "https://api.github.com/users/" + q[0];
                URL url = new URL(s);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStreamObject = new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader streamReader = new BufferedReader(new InputStreamReader(inputStreamObject, "UTF-8"));
                StringBuilder responseStrBuilder = new StringBuilder();
                String inputStr;
                while ((inputStr = streamReader.readLine()) != null)
                    responseStrBuilder.append(inputStr);

                return (responseStrBuilder.toString());


            } catch (Exception e) {
//            Toast.makeText(new SearchActivity().getApplicationContext(),"doinbg_exception", Toast.LENGTH_SHORT).show();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String X) {
            // SearchActivity...
            //= new SearchActivity();
            try {
                JSONObject js = new JSONObject(X);
                name = (String) js.getString("name");
                company = (String) js.getString("company");
                location = (String) js.getString("location");
                number = (int) js.getInt("public_repos");
                // Toast.makeText(contextRef,name,Toast.LENGTH_LONG).show();
                tv1.setText(name);
                tv2.setText(company);
                tv3.setText(location);

            } catch (Exception e) {
                Toast.makeText(contextRef, "JSON Haga", Toast.LENGTH_SHORT).show();
            }
            //showDialog("Downloaded " + result + " bytes");
            //  return null;
            // SearchActivity.names=null;
        }

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onProgressUpdate(Void... p) {

        }

    }

    class ReposSec extends AsyncTask<String,Void,String> {
        @SuppressLint("StaticFieldLeak")
        private Context contextRef;

        public ReposSec(Context context) {
            contextRef = (context);
        }
        public String convert_date(String s){
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date endDate = new Date();
            Calendar startCalendar = new GregorianCalendar();
            s = s.substring(0,10);
            int year = Integer.parseInt(s.substring(0,4));
            int month = Integer.parseInt(s.substring(5,7));
            int day = Integer.parseInt(s.substring(8,10));
            startCalendar.set(year, month-1, day, 0, 0);
            Calendar endCalendar = new GregorianCalendar();
            endCalendar.setTime(endDate);

            int diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
            int diffMonth = endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
            int diffday = endCalendar.get(Calendar.DAY_OF_MONTH) - startCalendar.get(Calendar.DAY_OF_MONTH);
            if(diffday<0){
                diffMonth = diffMonth - 1;
                diffday = diffday + 30;
            }
            if(diffMonth<0){
                diffYear = diffYear - 1;
                diffMonth = diffMonth + 12;
            }

            String y = String.valueOf(diffYear);
            String m = String.valueOf(diffMonth);
            String d = String.valueOf(diffMonth);
            if(diffday<10){
                d = "0" + d;
            }
            if(diffMonth<10){
                m = "0" + m;
            }
            if(diffYear<10){
                y = "0" + y;
            }
            String x = y + " years, " + m + " months, " + d + " days";
            return x;

        }

        @Override
        protected String doInBackground(String... q) {
            try {
                String s1 = "https://api.github.com/users/" + q[0] + "/repos" ;
                URL url1 = new URL(s1);
                HttpURLConnection urlConnection1 = (HttpURLConnection) url1.openConnection();
                InputStream inputStreamObject1 = new BufferedInputStream(urlConnection1.getInputStream());
                BufferedReader streamReader = new BufferedReader(new InputStreamReader(inputStreamObject1, "UTF-8"));
                StringBuilder responseStrBuilder = new StringBuilder();
//            Toast.makeText(new SearchActivity().getApplicationContext(),"strbuilderdone", Toast.LENGTH_SHORT).show();
                String inputStr1;
                while ((inputStr1 = streamReader.readLine()) != null)
                    responseStrBuilder.append(inputStr1);

                return (responseStrBuilder.toString());


            } catch (Exception e) {
//            Toast.makeText(new SearchActivity().getApplicationContext(),"doinbg_exception", Toast.LENGTH_SHORT).show();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String X){
            // SearchActivity...
            //= new SearchActivity();

            try {
                JSONArray js = new JSONArray(X);
                ArrayList<Repo> resultList = new ArrayList<Repo>();
                for(int i=0;i<js.length();i++){
                    JSONObject t = js.getJSONObject(i);
                    String[] yo = new String[3];
                    yo[0] =(String) t.getString("name");
                    yo[1] =(String)  t.getString("created_at");
                    yo[2] =(String)  t.getString("description");
                    yo[1] = convert_date(yo[1]);
                    resultList.add(new Repo(yo[0],yo[1],yo[2]));
                }
                RepoAdapter adapter = new RepoAdapter(context, resultList);
                listView.setAdapter(adapter);

            }
            catch(Exception e){
                Toast.makeText(contextRef, "JSON Haga", Toast.LENGTH_SHORT).show();
            }
            //showDialog("Downloaded " + result + " bytes");
            //  return null;
            // SearchActivity.names=null;
        }
        @Override
        protected void onPreExecute(){

        }

        @Override
        protected void onProgressUpdate(Void... p) {

        }

    }

}

