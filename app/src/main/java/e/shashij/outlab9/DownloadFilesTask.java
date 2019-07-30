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
//public class DownloadFilesTask extends AsyncTask<String,Void,String> {
//
//    // @Override
//    //  protected void onProgressUpdate(Integer... progress) {
//    //  setProgressPercent(progress[0]);
//    //
//    //}
//
//    @SuppressLint("StaticFieldLeak")
//    private Context contextRef;
//
//    public DownloadFilesTask(Context context) {
//        contextRef = (context);
//    }
//
//
//    @Override
//    protected String doInBackground(String... q) {
//        try {
////            Toast.makeText(new SearchActivity().getApplicationContext(),"doinbg", Toast.LENGTH_SHORT).show();
//            String s = "https://api.github.com/search/users?q=" + q[0] + "sort:repositories" ;
//            URL url = new URL(s);
//            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//            InputStream inputStreamObject = new BufferedInputStream(urlConnection.getInputStream());
//            BufferedReader streamReader = new BufferedReader(new InputStreamReader(inputStreamObject, "UTF-8"));
//            StringBuilder responseStrBuilder = new StringBuilder();
////            Toast.makeText(new SearchActivity().getApplicationContext(),"strbuilderdone", Toast.LENGTH_SHORT).show();
//            String inputStr;
//            while ((inputStr = streamReader.readLine()) != null)
//                responseStrBuilder.append(inputStr);
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
//            JSONObject js = new JSONObject(X);
//            SearchActivity.count = (int) js.get("total_count");
//            JSONArray l = (JSONArray) js.get("items");
//
//            for (int i = 0; i < SearchActivity.count; i++) {
//                JSONObject t = l.getJSONObject(i);
//                String x = (String) t.get("login");
//                SearchActivity.names.add(x);
//
//            }
//            Toast.makeText(contextRef, "Completed Search", Toast.LENGTH_SHORT).show();
//            return;
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
//}