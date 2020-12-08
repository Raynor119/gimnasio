package com.pixels.gimnasio;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.Snackbar;
import android.view.ViewGroup;
import android.support.design.widget.FloatingActionButton;
import android.widget.Button;
import android.support.v7.widget.RecyclerView;
import android.app.Activity;import android.os.Bundle;import android.support.design.widget.CollapsingToolbarLayout;import android.support.v4.app.Fragment;import android.view.LayoutInflater;import android.view.View;import android.view.ViewGroup;import android.widget.TextView;import android.content.DialogInterface;import android.content.Intent;import android.support.design.widget.NavigationView;import android.support.v4.view.GravityCompat;import android.support.v4.widget.DrawerLayout;import android.support.v7.app.ActionBarDrawerToggle;import android.support.v7.app.AlertDialog;import android.support.v7.app.AppCompatActivity;import android.os.Bundle;import android.support.v7.widget.LinearLayoutManager;import android.support.v7.widget.RecyclerView;import android.support.v7.widget.Toolbar;import android.view.Menu;import android.view.MenuItem;import android.view.View;import android.widget.TextView;import android.widget.Toast; import com.android.volley.RequestQueue;import com.android.volley.Response;import com.android.volley.VolleyError;import com.android.volley.toolbox.JsonArrayRequest;import com.android.volley.toolbox.Volley; import org.json.JSONArray;import org.json.JSONException;import org.json.JSONObject; import java.util.ArrayList;import java.util.List; import android.widget.*;import android.view.View.OnClickListener;import android.transition.Transition;import android.content.Context;import android.view.animation.LayoutAnimationController;import android.view.animation.AnimationUtils;


public class agusdesc extends AppCompatActivity
{

	public List<usu> vs = new ArrayList<>();
	public List<rutina> vr = new ArrayList<>();
	ArrayList<Integer> mUserItems ;
	RecyclerView recyclerView1;
	String codi;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_agsinar);
		Bundle extra = getIntent().getExtras();		
		codi=extra.getString("codigo");
		recyclerView1 =(RecyclerView) findViewById(R.id.listru);
		assert recyclerView1 != null;
       reclicle();
		
	}
	public void reclicle(){
		ip i=new ip();		
		String ip=i.ip();		
		String Url="http://"+ip+"/agsin.php?codigo="+codi;


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


					recyclerView1.setAdapter(new SimpleItemRecyclerViewAdapter(agusdesc.this, vs));
					animacion(recyclerView1);


				}



			}, new Response.ErrorListener() {
				@Override
				public void onErrorResponse(VolleyError error) {
					new android.os.Handler().postDelayed(new Runnable() {


							@Override
							public void run() {
								//Toast.makeText(getApplicationContext(), "Error de Conexion Verifique su conexion a Internet",Toast.LENGTH_LONG).show();

							}},2000);
				}
			});
		RequestQueue requestQueue;
		requestQueue= Volley.newRequestQueue(getApplicationContext());
		requestQueue.add(jsonArrayRequest);
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
		private final agusdesc mParentActivity; 
		private final List<usu> vusa;		
		private Transition transicion;		

		private final View.OnClickListener mOnClickListener = new View.OnClickListener() { 
			@Override public void onClick(View view) {
				
				final View vie=view;
				AlertDialog.Builder alert= new AlertDialog.Builder(mParentActivity);
				alert.setMessage("")
					.setCancelable(false)
					.setPositiveButton("si", new DialogInterface.OnClickListener(){
						@Override
						public void onClick(DialogInterface dialog,int which){
							
							//vusa.get(recyclerView1.getChildAdapterPosition(vie)).getCod();

							ip i=new ip();	
							String ip=i.ip();		
							String Url="http://"+ip+"/upagsin.php?codigo="+vusa.get(recyclerView1.getChildAdapterPosition(vie)).getCod()+"&desc="+codi;
							JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Url, new Response.Listener<JSONArray>() {

									@Override
									public void onResponse(JSONArray response) {

									}



								}, new Response.ErrorListener() {
									@Override
									public void onErrorResponse(VolleyError error) {
										new android.os.Handler().postDelayed(new Runnable() {


												@Override
												public void run() {
													//Toast.makeText(getApplicationContext(), "Error de Conexion Verifique su conexion a Internet",Toast.LENGTH_LONG).show();

												}},2000);
									}
								});
							RequestQueue requestQueue;
							requestQueue= Volley.newRequestQueue(getApplicationContext());
							requestQueue.add(jsonArrayRequest);
							vs.clear();
							recyclerView1.setAdapter(null);
							reclicle();




							



						}

					})
					.setNegativeButton("no", new DialogInterface.OnClickListener(){
						@Override
						public void onClick(DialogInterface dialog,int which){



							

							dialog.cancel();
						}

					});
				
				AlertDialog titulo=alert.create();
				titulo.setTitle("Quieres Asignar el Descuento a este Usuario");
				titulo.show();
				
			}
		};


		SimpleItemRecyclerViewAdapter(agusdesc parent,
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
