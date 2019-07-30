package e.shashij.outlab9;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RepoAdapter extends ArrayAdapter<Repo> {

    private Context mContext;
    private List<Repo> repoList = new ArrayList<>();

    public RepoAdapter(@NonNull Context context, ArrayList<Repo> list) {
        super(context, 0 , list);
        mContext = context;
        repoList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.adapter,parent,false);

        Repo currentRepo = repoList.get(position);
        TextView repoName = listItem.findViewById(R.id.repo);
        TextView repoAge = listItem.findViewById(R.id.age);
        TextView repoDescription = listItem.findViewById(R.id.desc);
        repoName.setText(currentRepo.getName());
        repoAge.setText(currentRepo.getAge());
        repoDescription.setText(currentRepo.getDescription());

        return listItem;
    }
}
