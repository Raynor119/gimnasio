package com.pixels.gimnasio;




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
import android.widget.EditText;

/**
 * A fragment representing a single administrador detail screen.
 * This fragment is either contained in a {@link administradorListActivity}
 * in two-pane mode (on tablets) or a {@link administradorDetailActivity}
 * on handsets.
 */
public class descudetailfragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";
	List<descu> vs = new ArrayList<>();

	RecyclerView recyclerView1;
	int i=0;
    /**

     * The dummy content this fragment is presenting.
     */


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public descudetailfragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.


            Activity activity = this.getActivity();




			//        CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
			//      if (appBarLayout != null) {
			//        appBarLayout.setTitle("hola");
			//  }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.desc_detail, container, false);
		recyclerView1 = rootView.findViewById(R.id.listusu);
		FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					//Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
					//  .setAction("Action", null).show();
					AlertDialog.Builder alertt= new AlertDialog.Builder(getActivity());
					//alertt.setMessage("El Usuario ya se habia Registrado")
					final View vie = LayoutInflater.from(getActivity()).inflate(R.layout.datos_desc, null);

					alertt.setView(vie)
						.setCancelable(false)
						.setPositiveButton("Agregar Descuento", new DialogInterface.OnClickListener(){
							@Override
							public void onClick(DialogInterface dialog,int which){
								EditText nombre,dias,precio;

								nombre=(EditText) vie.findViewById(R.id.nom);
								
								precio=(EditText) vie.findViewById(R.id.preci);

								if(nombre.getText().toString().equals("") || precio.getText().toString().equals("")){
									Toast.makeText(getActivity(), "Llene todos los Campos", Toast.LENGTH_LONG).show();
								}else{


									ip ii=new ip();	
									String ip=ii.ip();		
									String Url="http://"+ip+"/insertdesc.php?nombre="+nombre.getText()+"&precio="+precio.getText();
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

														}},1);
											}
										});
									RequestQueue requestQueue;
									requestQueue= Volley.newRequestQueue(getActivity());
									requestQueue.add(jsonArrayRequest);


									Toast.makeText(getActivity(), "Sea Creado el Descuento", Toast.LENGTH_LONG).show();
									vs.clear();
									recyclerView1.setAdapter(null);
									recicle();
								}







							}		
						});
					alertt.setNeutralButton("Salir", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialogInterface, int which) {

							}
						});


					AlertDialog titulo=alertt.create();
					titulo.setTitle("Descuento");
					titulo.show();








				}
			});
		assert recyclerView1 != null;
        recicle();


        return rootView;
    }
	public void recicle(){
		ip i=new ip();		
		String ip=i.ip();		
		String Url="http://"+ip+"/descu.php";


		JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Url, new Response.Listener<JSONArray>() {

				@Override
				public void onResponse(JSONArray response) {
					JSONObject jo = null;
					int cont=0;
					for (int i = 0; i < response.length(); i++) {
						try {
							jo = response.getJSONObject(i);
							vs.add(new descu(jo.getString("id_desc"), jo.getString("nombre"),jo.getString("monto")));


						}
						catch (JSONException e) {
							//Toast.makeText(getActivity(), "error de Bd", Toast.LENGTH_LONG).show();

						}
					}


					recyclerView1.setAdapter(new SimpleItemRecyclerViewAdapter(descudetailfragment.this, vs));
					animacion(recyclerView1);


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
		private final descudetailfragment  mParentActivity; 
		private final List<descu> vusa;		
		private Transition transicion;		

		private final View.OnClickListener mOnClickListener = new View.OnClickListener() { 
			@Override public void onClick(View view) {
				final View vie=view;
				AlertDialog.Builder alert= new AlertDialog.Builder(getActivity());
				alert.setMessage("")
					.setCancelable(false)
					.setPositiveButton("Asignar Descuento", new DialogInterface.OnClickListener(){
						@Override
						public void onClick(DialogInterface dialog,int which){
							
							
							
							
							Intent intent =new Intent(getActivity(),agusdesc.class);
							intent.putExtra("codigo",vusa.get(recyclerView1.getChildAdapterPosition(vie)).getCod());
							getActivity().startActivity(intent);

							


						}

					})
					.setNegativeButton("Eliminar Descuento", new DialogInterface.OnClickListener(){
						@Override
						public void onClick(DialogInterface dialog,int which){
							


							ip i=new ip();	
							String ip=i.ip();		
							String Url="http://"+ip+"/eliminardesc.php?codigo="+vusa.get(recyclerView1.getChildAdapterPosition(vie)).getCod();
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
							requestQueue= Volley.newRequestQueue(getActivity());
							requestQueue.add(jsonArrayRequest);
							vs.clear();
							recyclerView1.setAdapter(null);
							recicle();


							dialog.cancel();
						}

					});
				alert.setNeutralButton("Salir", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialogInterface, int which) {
							dialogInterface.cancel();
						}
					});
				AlertDialog titulo=alert.create();
				titulo.setTitle("Opciones del Descuento");
				titulo.show();
				



			}
		};

		SimpleItemRecyclerViewAdapter(descudetailfragment  parent,
									  List<descu> items) {
			vusa = items;
			mParentActivity = parent;

		}

		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			View view = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.descuento, parent, false);
			return new ViewHolder(view);
		}

		@Override
		public void onBindViewHolder(final ViewHolder holder, int position) {
			holder.nombre.setText(vusa.get(position).getNombre());
			
			holder.precios.setText(vusa.get(position).getPrecio());

			holder.itemView.setTag(vusa.get(position));
			holder.itemView.setOnClickListener(mOnClickListener);
		}

		@Override
		public int getItemCount() {
			return vusa.size();
		}

		class ViewHolder extends RecyclerView.ViewHolder {
			final TextView nombre,precios;



			ViewHolder(View view) {
				super(view);

				nombre=(TextView) view.findViewById(R.id.nombre);
				
				precios=(TextView) view.findViewById(R.id.desc);


			}
		}
	}

}


