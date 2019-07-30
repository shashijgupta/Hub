//package e.shashij.outlab9;
//
//
//import android.annotation.SuppressLint;
//import android.content.Context;
//import android.os.AsyncTask;
//import android.widget.Toast;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.BufferedInputStream;
//import java.io.BufferedReader;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.lang.ref.WeakReference;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.List;
//
//import static e.shashij.outlab9.UserActivity.user;
//
//
//public class ReposSec extends AsyncTask<String,Void,String> {
//    @SuppressLint("StaticFieldLeak")
//    private Context contextRef;
//
//    public ReposSec(Context context) {
//        contextRef = (context);
//    }
//
//
//    @Override
//    protected String doInBackground(String... q) {
//        try {
//            String s1 = "https://api.github.com/users/" + q[0] + "/repos" ;
//            URL url1 = new URL(s1);
//            HttpURLConnection urlConnection1 = (HttpURLConnection) url1.openConnection();
//            InputStream inputStreamObject1 = new BufferedInputStream(urlConnection1.getInputStream());
//            BufferedReader streamReader = new BufferedReader(new InputStreamReader(inputStreamObject1, "UTF-8"));
//            StringBuilder responseStrBuilder = new StringBuilder();
////            Toast.makeText(new SearchActivity().getApplicationContext(),"strbuilderdone", Toast.LENGTH_SHORT).show();
//            String inputStr1;
//            while ((inputStr1 = streamReader.readLine()) != null)
//                responseStrBuilder.append(inputStr1);
//
//            return (responseStrBuilder.toString());
//
//
//        } catch (Exception e) {
////            Toast.makeText(new SearchActivity().getApplicationContext(),"doinbg_exception", Toast.LENGTH_SHORT).show();
//        }
//        return null;
//    }
//
//    @Override
//    protected void onPostExecute(String X){
//        // SearchActivity...
//        //= new SearchActivity();
//        try {
//            JSONArray js = new JSONArray(X);
//            for(int i=0;i<js.length();i++){
//                JSONObject t = js.getJSONObject(i);
//                String[] yo = new String[3];
//                yo[0] =(String) t.getString("name");
//                yo[1] =(String)  t.getString("created_at");
//                yo[2] =(String)  t.getString("description");
//                UserActivity.resultList.add(yo);
//            }
//
//        }
//        catch(Exception e){
//            Toast.makeText(contextRef, "JSON Haga", Toast.LENGTH_SHORT).show();
//        }
//        //showDialog("Downloaded " + result + " bytes");
//        //  return null;
//        // SearchActivity.names=null;
//    }
//    @Override
//    protected void onPreExecute(){
//
//    }
//
//    @Override
//    protected void onProgressUpdate(Void... p) {
//
//    }
//
//}
