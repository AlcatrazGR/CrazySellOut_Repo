package codebrains.crazysellout.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import codebrains.crazysellout.Activities.MainProducerActivity;
import codebrains.crazysellout.AsyncTasks.AttemptDisplayUserProducts;
import codebrains.crazysellout.Controllers.DisplayProductsController;
import codebrains.crazysellout.Interfaces.IAsyncResponse;
import codebrains.crazysellout.R;


public class ProducerItemsFragment extends Fragment implements IAsyncResponse{

    private ListView listView;
    private View view;
    private AttemptDisplayUserProducts asyncTask;

    private String response;

    //Constructor
    public ProducerItemsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_producer_items_fragment, container, false);
        this.view = view;

        this.listView = (ListView) this.view.findViewById(R.id.listView);

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username", MainProducerActivity.GetUsername());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        asyncTask = new AttemptDisplayUserProducts((Activity) this.view.getContext(), jsonObject);
        asyncTask.delegate = this;
        asyncTask.execute();

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    /**
     * Method that controls the display of user products to the user by setting the lis view item on
     * the activity.
     * @param response The string response of the server.
     */
    private void ProcessOutput(String response, Activity activity){

        DisplayProductsController dpc = new DisplayProductsController();
        ArrayList<String> list = dpc.SetListOfUserProductsForDisplay(response, activity);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity, R.layout.simpe_list_row_item, list);
        
        if(listView == null)
            listView = (ListView) activity.findViewById(R.id.listView);

        listView.setAdapter(adapter);

    }

    /**
     * Method that handles the refresh of product items into the list of products.
     * @param view The view created by the event onClick.
     */
    public void RefreshProducerItemsListProcess(View view){

        /*
        this.view = view;
        listView = (ListView) this.view.findViewById(R.id.listView);
        */

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username", MainProducerActivity.GetUsername());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        asyncTask = new AttemptDisplayUserProducts((Activity) view.getContext(), jsonObject);
        asyncTask.delegate = this;
        asyncTask.execute();
    }


    @Override
    public void ProcessFinish(String output, Activity activity) {

        response = output;
        this.ProcessOutput(response, activity);
    }
}
