package com.pixels.gimnasio;

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


public class rutiusuariof extends AppCompatActivity
 {
	String codi;
	
	public List<rutina> vs = new ArrayList<>();
	public List<rutina> vr = new ArrayList<>();
	ArrayList<Integer> mUserItems ;
	RecyclerView recyclerView1;
	public String [] vec;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		setContentView(R.layout.rutiusuario);
		
		
		
		Bundle extra = getIntent().getExtras();		
		codi=extra.getString("codigo");
		recyclerView1 =(RecyclerView) findViewById(R.id.listru);
		assert recyclerView1 != null;
        reclicle();
		FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					final View vie=view;
					mUserItems = new ArrayList<>();
					ip i=new ip();		
					String ip=i.ip();		
					String Url="http://"+ip+"/ruti.php";
					JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Url, new Response.Listener<JSONArray>() {

							@Override
							public void onResponse(JSONArray response) {
								JSONObject jo = null;
								int cont=0;
								for (int i = 0; i < response.length(); i++) {
									try {
										jo = response.getJSONObject(i);
										vr.add(new rutina(jo.getString("id_rut"), jo.getString("nombre"), jo.getString("tiempo")));


									}
									catch (JSONException e) {
										//Toast.makeText(getActivity(), "error de Bd", Toast.LENGTH_LONG).show();

									}
								}
								//Toast.makeText(getApplicationContext(), ""+vs.size(), Toast.LENGTH_LONG).show();
								
								for(int i=0;i<vs.size();i++){
									for(int b=0;b<vr.size();b++){
										if(vs.get(i).getId().equals(vr.get(b).getId())){
											vr.remove(b);
										}
									}
								}
								
								
								if(vr.size()==0){
									Snackbar.make(vie, "Ya tiene todas las Rutinas agregadas", Snackbar.LENGTH_LONG)
										.setAction("Action", null).show();
									vs.clear();
									recyclerView1.setAdapter(null);
									reclicle();
								}else{
								final String [] listItems = new String [vr.size()];
								for(int i=0;i<vr.size();i++){
									listItems[i]=vr.get(i).getNombre();
								}
								final boolean[] checkedItems;

								checkedItems = new boolean[listItems.length];
								AlertDialog.Builder mBuilder = new AlertDialog.Builder(rutiusuariof.this);
								mBuilder.setTitle("Rutinas");
								mBuilder.setMultiChoiceItems(listItems, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
										@Override
										public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {

											if(isChecked){
												mUserItems.add(position);
											}else{
												mUserItems.remove((Integer.valueOf(position)));
											}
										}
									});

								mBuilder.setCancelable(false);
								mBuilder.setPositiveButton("Seleccionar", new DialogInterface.OnClickListener() {
										@Override
										public void onClick(DialogInterface dialogInterface, int which) {
											
											if(mUserItems.size()==0){
												for (int i = 0; i < checkedItems.length; i++) {
													checkedItems[i] = false;
													mUserItems.clear();
													vr.clear();

												}
												Snackbar.make(vie, "Seleccion Por lo menos una Rutina", Snackbar.LENGTH_LONG)
													.setAction("Action", null).show();
												vs.clear();
												recyclerView1.setAdapter(null);
												reclicle();
											}else{
											String item = "";
											vec= new String[mUserItems.size()];

											int n=0;
											for (int i = 0; i < mUserItems.size(); i++) {
												item = item + listItems[mUserItems.get(i)];
												if (i != mUserItems.size() - 1) {
													vec[n]=item;
													item ="";
													n++;
												}
											}
											vec[n]=item;
											item="";
											
											String [] id=new String [vec.length];
											for(int i=0;i<vec.length;i++){
												for(int b=0;b<vr.size();b++){
													if(vec[i].equals(vr.get(b).getNombre())){
														id[i]=vr.get(b).getId();
													}
												}
											}

											for(int i=0;i<mUserItems.size();i++){
												item=item+"codigo #"+id[i]+": "+vec[i]+"\n\n";
											}
											
											
											
											for(int i=0;i<id.length;i++){
												ip ii=new ip();	
												String ip=ii.ip();		
												String Url="http://"+ip+"/inruti.php?codigo="+codi+"&id_rut="+id[i];
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
											}
											
											
											
											
											
											for (int i = 0; i < checkedItems.length; i++) {
												checkedItems[i] = false;
												mUserItems.clear();
												vr.clear();

											}
											Snackbar.make(vie, "Se Agregaron las rutinas", Snackbar.LENGTH_LONG)
												.setAction("Action", null).show();
											vs.clear();
											recyclerView1.setAdapter(null);
											reclicle();
											}
										}
									});

							

								mBuilder.setNeutralButton("Salir", new DialogInterface.OnClickListener() {
										@Override
										public void onClick(DialogInterface dialogInterface, int which) {
											for (int i = 0; i < checkedItems.length; i++) {
												checkedItems[i] = false;
												mUserItems.clear();
												vr.clear();
												
											}
										}
									});

								AlertDialog mDialog = mBuilder.create();
								mDialog.show();
								
								
								}
								
								
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
			});
	}
	
	public void reclicle(){
		ip i=new ip();		
		String ip=i.ip();		
		String Url="http://"+ip+"/rutinau.php?codigo="+codi;
		JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Url, new Response.Listener<JSONArray>() {

				@Override
				public void onResponse(JSONArray response) {
					JSONObject jo = null;
					int cont=0;
					for (int i = 0; i < response.length(); i++) {
						try {
							jo = response.getJSONObject(i);
							vs.add(new rutina(jo.getString("id_rut"), jo.getString("nombre"), jo.getString("tiempo")));


						}
						catch (JSONException e) {
							//Toast.makeText(getActivity(), "error de Bd", Toast.LENGTH_LONG).show();

						}
					}


					recyclerView1.setAdapter(new SimpleItemRecyclerViewAdapter(rutiusuariof.this, vs));
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
		requestQueue= Volley.newRequestQueue(this);
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
		private final rutiusuariof mParentActivity; 
		private final List<rutina> vusa;		
		private Transition transicion;		
		
		private final View.OnClickListener mOnClickListener = new View.OnClickListener() { 
			@Override public void onClick(View view) {
				TextView cc;
				final View vie=view;
				AlertDialog.Builder alert= new AlertDialog.Builder(mParentActivity);
				alert.setMessage("Quiere Eliminar esta Rutina")
					.setCancelable(false)
					.setPositiveButton("si", new DialogInterface.OnClickListener(){
						@Override
						public void onClick(DialogInterface dialog,int which){
							
							
							//Toast.makeText(mParentActivity, "",Toast.LENGTH_LONG).show();
								
							ip i=new ip();	
							String ip=i.ip();		
							String Url="http://"+ip+"/eliminalr.php?codigo="+codi+"&id_rut="+vusa.get(recyclerView1.getChildAdapterPosition(vie)).getId();
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
							requestQueue= Volley.newRequestQueue(mParentActivity);
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
				titulo.setTitle("Alerta");
				titulo.show();
				
				
			}
		};
		
		
		SimpleItemRecyclerViewAdapter(rutiusuariof parent,
									  List<rutina> items) {
			vusa = items;
			mParentActivity = parent;

		}

		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			View view = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.rutina, parent, false);
			return new ViewHolder(view);
		}

		@Override
		public void onBindViewHolder(final ViewHolder holder, int position) {
			holder.nombre.setText(vusa.get(position).getNombre());
			holder.tiempo.setText(vusa.get(position).getRutina());
			//holder.cc.setText(vusa.get(position).getCod());

			holder.itemView.setTag(vusa.get(position));
			holder.itemView.setOnClickListener(mOnClickListener);
		}

		@Override
		public int getItemCount() {
			return vusa.size();
		}

		class ViewHolder extends RecyclerView.ViewHolder {
			final TextView nombre,tiempo;



			ViewHolder(View view) {
				super(view);

				nombre=(TextView) view.findViewById(R.id.nombre);
				tiempo=(TextView) view.findViewById(R.id.tiempo);
				//cc=(TextView) view.findViewById(R.id.cc);


			}
		}
	}
	
}
