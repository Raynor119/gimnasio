package com.pixels.gimnasio;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import com.pixels.gimnasio.dummy.DummyContentU;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.transition.Transition;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/**
 * A fragment representing a single usuario detail screen.
 * This fragment is either contained in a {@link usuarioListActivity}
 * in two-pane mode (on tablets) or a {@link usuarioDetailActivity}
 * on handsets.
 */
public class usuarioDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
	List<datus> ll = new ArrayList<>();
    public static  String ARG_ITEM_ID = "item_id";
	EditText cod,cedula,nombre,apellido,edad,direccion,telefono,correo,username,password,datM;
    /**
     * The dummy content this fragment is presenting.
     */
    private DummyContentU.DummyItem mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public usuarioDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
           // mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));

            Activity activity = this.getActivity();
        //    CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
           // if (appBarLayout != null) {
             //   appBarLayout.setTitle(mItem.content);
            //}
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.usuario_detail, container, false);
		
		cod=(EditText) rootView.findViewById(R.id.cod);
		cedula=(EditText) rootView.findViewById(R.id.cc);
		nombre=(EditText) rootView.findViewById(R.id.nom);
		apellido=(EditText) rootView.findViewById(R.id.apelli);
		edad=(EditText) rootView.findViewById(R.id.edad);
		direccion=(EditText) rootView.findViewById(R.id.direcc);
		telefono=(EditText) rootView.findViewById(R.id.telef);
		correo=(EditText) rootView.findViewById(R.id.corre);
		username=(EditText) rootView.findViewById(R.id.usua);
		password=(EditText) rootView.findViewById(R.id.contra);
		datM=(EditText) rootView.findViewById(R.id.datos);
		
		
		ip i=new ip();
		String ip=i.ip();
	
		String Url="http://"+ip+"/datus.php?codigo="+ARG_ITEM_ID;
		//Toast.makeText(getActivity(), ARG_ITEM_ID+"",Toast.LENGTH_LONG).show();


		JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Url, new Response.Listener<JSONArray>() {

				@Override
				public void onResponse(JSONArray response) {
					JSONObject jo = null;
					for (int i = 0; i < response.length(); i++) {
						try {
							jo = response.getJSONObject(i);
							ll.add(new datus(jo.getString("id_usua"), jo.getString("dat_med"), jo.getString("cedula"), jo.getString("nombre"), jo.getString("apellido"), jo.getString("edad"), jo.getString("direccion"), jo.getString("telefono"), jo.getString("correo"), jo.getString("username"), jo.getString("password") ));

						} catch (JSONException e) {
							Toast.makeText(getActivity(), "error de Bd", Toast.LENGTH_LONG).show();

						}
					}
					
					cod.setText(ll.get(0).getId());
					cedula.setText(ll.get(0).getCedula());
					nombre.setText(ll.get(0).getNombre());
					apellido.setText(ll.get(0).getApellidfo());
					edad.setText(ll.get(0).getEdad());
					direccion.setText(ll.get(0).getDireccion());
					telefono.setText(ll.get(0).getTelefono());
					correo.setText(ll.get(0).getCorreo());
					username.setText(ll.get(0).getUsername());
					password.setText(ll.get(0).getPassword());
					datM.setText(ll.get(0).getDatom());


				}
			}, new Response.ErrorListener() {
				@Override
				public void onErrorResponse(VolleyError error) {
					new android.os.Handler().postDelayed(new Runnable() {


							@Override
							public void run() {
							//	Toast.makeText(getActivity(), "Error de Conexion Verifique su conexion a Internet",Toast.LENGTH_LONG).show();

							}},2000);
				}
			});
		RequestQueue requestQueue;
		requestQueue= Volley.newRequestQueue(getActivity());
		requestQueue.add(jsonArrayRequest);

		

        // Show the dummy content as text in a TextView.
       
           // ((TextView) rootView.findViewById(R.id.usuario_detail)).setText("1");
       

        return rootView;
    }
}
