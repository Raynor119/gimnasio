package com.pixels.gimnasio;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.support.v7.widget.RecyclerView;
import android.app.Activity;import android.os.Bundle;import android.support.design.widget.CollapsingToolbarLayout;import android.support.v4.app.Fragment;import android.view.LayoutInflater;import android.view.View;import android.view.ViewGroup;import android.widget.TextView;import android.content.DialogInterface;import android.content.Intent;import android.support.design.widget.NavigationView;import android.support.v4.view.GravityCompat;import android.support.v4.widget.DrawerLayout;import android.support.v7.app.ActionBarDrawerToggle;import android.support.v7.app.AlertDialog;import android.support.v7.app.AppCompatActivity;import android.os.Bundle;import android.support.v7.widget.LinearLayoutManager;import android.support.v7.widget.RecyclerView;import android.support.v7.widget.Toolbar;import android.view.Menu;import android.view.MenuItem;import android.view.View;import android.widget.TextView;import android.widget.Toast; import com.android.volley.RequestQueue;import com.android.volley.Response;import com.android.volley.VolleyError;import com.android.volley.toolbox.JsonArrayRequest;import com.android.volley.toolbox.Volley; import org.json.JSONArray;import org.json.JSONException;import org.json.JSONObject; import java.util.ArrayList;import java.util.List; import android.widget.*;import android.view.View.OnClickListener;import android.transition.Transition;import android.content.Context;import android.view.animation.LayoutAnimationController;import android.view.animation.AnimationUtils;

/**
 * A fragment representing a single administrador detail screen.
 * This fragment is either contained in a {@link administradorListActivity}
 * in two-pane mode (on tablets) or a {@link administradorDetailActivity}
 * on handsets.
 */
public class administradorDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";
	List<usu> vs = new ArrayList<>();
    /**
     * The dummy content this fragment is presenting.
     */
    

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public administradorDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
           

            Activity activity = this.getActivity();
			
			
			
			
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle("hola");
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.administrador_detail, container, false);
		final RecyclerView recyclerView1 = rootView.findViewById(R.id.listusu);
		assert recyclerView1 != null;
        ip i=new ip();		
		String ip=i.ip();		
		String Url="http://"+ip+"/usua.php";
		
		
		JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Url, new Response.Listener<JSONArray>() {

				@Override
				public void onResponse(JSONArray response) {
					JSONObject jo = null;
					int cont=0;
					for (int i = 0; i < response.length(); i++) {
						try {
							jo = response.getJSONObject(i);
							vs.add(new usu(jo.getString("cod_pers"), jo.getString("cedula"), jo.getString("nombre"), jo.getString("apellido")));
							
							
							}
							catch (JSONException e) {
								//Toast.makeText(getActivity(), "error de Bd", Toast.LENGTH_LONG).show();

							}
					}
					
					
					recyclerView1.setAdapter(new SimpleItemRecyclerViewAdapter(administradorDetailFragment.this, vs));
					animacion(recyclerView1);
					
					
				}


			
	}, new Response.ErrorListener() {
	@Override
	public void onErrorResponse(VolleyError error) {
		new android.os.Handler().postDelayed(new Runnable() {


				@Override
				public void run() {
					Toast.makeText(getActivity(), "Error de Conexion Verifique su conexion a Internet",Toast.LENGTH_LONG).show();

				}},2000);
	}
	});
	RequestQueue requestQueue;
	requestQueue= Volley.newRequestQueue(getActivity());
	requestQueue.add(jsonArrayRequest);
		

        return rootView;
    }
	
	private void animacion(RecyclerView recyclerView){		
	Context context=recyclerView.getContext();		
	LayoutAnimationController animacion= AnimationUtils.loadLayoutAnimation(context,R.anim.layout_animation_from_right);		
	recyclerView.setLayoutAnimation(animacion);		
	recyclerView.getAdapter().notifyDataSetChanged();		
	recyclerView.scheduleLayoutAnimation();	
	}
	
	
	public class SimpleItemRecyclerViewAdapter	
	extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {
		private final administradorDetailFragment mParentActivity; 
		private final List<usu> vusa;		
		private Transition transicion;		
		
		private final View.OnClickListener mOnClickListener = new View.OnClickListener() { 
		@Override public void onClick(View view) {
			TextView cc;
			cc=(TextView) view.findViewById(R.id.cc);
			
			Context context = view.getContext();
			Intent intent = new Intent(context, rutiusuariof.class);
			intent.putExtra("codigo",cc.getText());
			context.startActivity(intent);
			
			
			//Toast.makeText(getActivity(), ""+cc.getText(),Toast.LENGTH_LONG).show();
			
		}
	};

		SimpleItemRecyclerViewAdapter(administradorDetailFragment parent,
								  List<usu> items) {
		vusa = items;
		mParentActivity = parent;

	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext())
			.inflate(R.layout.usuarios, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(final ViewHolder holder, int position) {
		holder.nombre.setText(vusa.get(position).getNombre());
		holder.apellido.setText(vusa.get(position).getApellido());
		holder.cc.setText(vusa.get(position).getCod());
		
		holder.itemView.setTag(vusa.get(position));
		holder.itemView.setOnClickListener(mOnClickListener);
	}

	@Override
	public int getItemCount() {
		return vusa.size();
	}

	class ViewHolder extends RecyclerView.ViewHolder {
		final TextView nombre,apellido,cc;



		ViewHolder(View view) {
			super(view);

			nombre=(TextView) view.findViewById(R.id.nombre);
			apellido=(TextView) view.findViewById(R.id.apellido);
			cc=(TextView) view.findViewById(R.id.cc);
			

		}
	}
}

}
