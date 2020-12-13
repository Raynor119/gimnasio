package com.pixels.gimnasio;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.transition.Transition;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.pixels.gimnasio.agregarusu;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/**
 * A fragment representing a single administrador detail screen.
 * This fragment is either contained in a {@link administradorListActivity}
 * in two-pane mode (on tablets) or a {@link administradorDetailActivity}
 * on handsets.
 */
public class vpagos extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
	private static final String CERO = "0";
    private static final String DOS_PUNTOS = ":";
    private static final String BARRA = "/";
	 
	public final Calendar c = Calendar.getInstance();
	Spinner tipou;

    //Fecha
     int mes = c.get(Calendar.MONTH);
     int dia = c.get(Calendar.DAY_OF_MONTH);
     int anio = c.get(Calendar.YEAR);
	 int totald=0;
	ArrayList<Integer> mUserItems ;
    //Hora
     int hora = c.get(Calendar.HOUR_OF_DAY);
    int minuto = c.get(Calendar.MINUTE);
	
	 
	 
    public static final String ARG_ITEM_ID = "item_id";
	List<vpag> vs = new ArrayList<>();
	JsonArrayRequest jsonArrayRequest1;
	RecyclerView recyclerView1;
	RequestQueue requestQueue;
	TextView totat;
    /**
     * The dummy content this fragment is presenting.
     */


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public vpagos() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.


            Activity activity = this.getActivity();




			//      CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
			//    if (appBarLayout != null) {
			//      appBarLayout.setTitle("hola");
            //}
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.vpagos, container, false);
		recyclerView1 = rootView.findViewById(R.id.listusu);
		totat=rootView.findViewById(R.id.total);
		FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					obtenerFecha();

				}
			});
		assert recyclerView1 != null;
        otra();
		//requestQueue.add(jsonArrayRequest1);
		//obtenerFecha();


        return rootView;
    }
	public void otra(){
		vs.clear();
		recyclerView1.setAdapter(null);
		ip i=new ip();		
		String ip=i.ip();		
		String Url="http://"+ip+"/cond.php?fecha="+anio+"-"+(mes+1)+"-"+dia;


		JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Url, new Response.Listener<JSONArray>() {

				@Override
				public void onResponse(JSONArray response) {
					JSONObject jo = null;
					int cont=0;
					for (int i = 0; i < response.length(); i++) {
						try {
							jo = response.getJSONObject(i);
							vs.add(new vpag(jo.getString("id_fac"), jo.getString("id_usua"), jo.getString("nombred"), jo.getString("preciod"), jo.getString("nombre"), jo.getString("dias"), jo.getString("precio"), jo.getString("monto"), jo.getString("fecha_inicial"), jo.getString("estado"), jo.getString("fecha_final"), jo.getString("cedula")));



						}
						catch (JSONException e) {
							//Toast.makeText(getActivity(), "error de Bd", Toast.LENGTH_LONG).show();

						}
					}

					ip i=new ip();		
					String ip=i.ip();		
					String Url="http://"+ip+"/sind.php?fecha="+anio+"-"+(mes+1)+"-"+dia;

					jsonArrayRequest1=new JsonArrayRequest(Url, new Response.Listener<JSONArray>() {

							@Override
							public void onResponse(JSONArray response) {
								JSONObject jo = null;
								int cont=0;
								for (int i = 0; i < response.length(); i++) {
									try {
										jo = response.getJSONObject(i);
										vs.add(new vpag(jo.getString("id_fac"), jo.getString("id_usua"), "", "", jo.getString("nombre"), jo.getString("dias"), jo.getString("precio"), jo.getString("monto"), jo.getString("fecha_inicial"), jo.getString("estado"), jo.getString("fecha_final"), jo.getString("cedula")));


									}
									catch (JSONException e) {
										//Toast.makeText(getActivity(), "error de Bd", Toast.LENGTH_LONG).show();

									}
								}

								recyclerView1.setAdapter(new SimpleItemRecyclerViewAdapter(vpagos.this, vs));
								animacion(recyclerView1);
								
								for(int i=0;i<vs.size();i++){
									totald=totald+Integer.parseInt(vs.get(i).getMonto());
								}
								
								totat.setText("El Total del Dia es: "+totald);
								
								



							}

						}, new Response.ErrorListener() {
							@Override
							public void onErrorResponse(VolleyError error) {
								new android.os.Handler().postDelayed(new Runnable() {


										@Override
										public void run() {
											//Toast.makeText(getActivity(), "Error de Conexion Verifique su conexion a Internet",Toast.LENGTH_LONG).show();

										}},2000);
							}
						});



					//requestQueue= Volley.newRequestQueue(getActivity());
					requestQueue.add(jsonArrayRequest1);




				}



			}, new Response.ErrorListener() {
				@Override
				public void onErrorResponse(VolleyError error) {
					new android.os.Handler().postDelayed(new Runnable() {


							@Override
							public void run() {
								//Toast.makeText(getActivity(), "Error de Conexion Verifique su conexion a Internet",Toast.LENGTH_LONG).show();

							}},2000);
				}
			});

		requestQueue= Volley.newRequestQueue(getActivity());
		requestQueue.add(jsonArrayRequest);
		
		
		
	}
	
	private void obtenerFecha(){
        DatePickerDialog recogerFecha = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
				@Override
				public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

					final int mesActual = month + 1;

					String diaFormateado = (dayOfMonth < 10)? CERO + String.valueOf(dayOfMonth):String.valueOf(dayOfMonth);
					String mesFormateado = (mesActual < 10)? CERO + String.valueOf(mesActual):String.valueOf(mesActual);

					//etFecha.setText(mesFormateado + BARRA + diaFormateado + BARRA + year);
					
					anio=year;
					mes=Integer.parseInt(mesFormateado)-1;
					dia=Integer.parseInt(diaFormateado);
					totald=0;
					totat.setText("El Total del Dia es: "+totald);
					otra();


				}
			},anio, mes, dia);
		final int mesActual = mes + 1;

		String diaFormateado = (dia < 10)? CERO + String.valueOf(dia):String.valueOf(dia);
		String mesFormateado = (mesActual < 10)? CERO + String.valueOf(mesActual):String.valueOf(mesActual);

		//etFecha.setText(mesFormateado + BARRA + diaFormateado + BARRA + anio);
		//Toast.makeText(getActivity(), "ano: "+anio+" mes: "+(mes+1)+" dia: "+dia,Toast.LENGTH_LONG).show();


       recogerFecha.show();

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
		private final vpagos mParentActivity; 
		private final List<vpag> vusa;		
		private Transition transicion;		

		private final View.OnClickListener mOnClickListener = new View.OnClickListener() { 
			@Override public void onClick(View view) {
				TextView cc;
				//cc=(TextView) view.findViewById(R.id.cc);

				

				//Toast.makeText(getActivity(), ""+cc.getText(),Toast.LENGTH_LONG).show();

			}
		};

		SimpleItemRecyclerViewAdapter(vpagos parent,
									  List<vpag> items) {
			vusa = items;
			mParentActivity = parent;

		}

		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			View view = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.vprep, parent, false);
			return new ViewHolder(view);
		}

		@Override
		public void onBindViewHolder(final ViewHolder holder, int position) {
			//totald=totald+Integer.parseInt(vusa.get(position).getMonto());
			//holder.total.setText("El Total del Dia es:"+totald);
			holder.nombre.setText(vusa.get(position).getNombre());
			holder.monto.setText("Monto Total de la Factura: "+vusa.get(position).getMonto());
			holder.cc.setText(vusa.get(position).getCedula());
			holder.d.setText("Duracion de la "+vusa.get(position).getNombre()+": "+vusa.get(position).getDias()+" Dias");
			holder.itemView.setTag(vusa.get(position));
			holder.itemView.setOnClickListener(mOnClickListener);
		}

		@Override
		public int getItemCount() {
			return vusa.size();
		}

		class ViewHolder extends RecyclerView.ViewHolder {
			final TextView nombre,monto,cc,d;



			ViewHolder(View view) {
				super(view);

				nombre=(TextView) view.findViewById(R.id.nombre);
				monto=(TextView) view.findViewById(R.id.monto);
				cc=(TextView) view.findViewById(R.id.cc);
				d=(TextView) view.findViewById(R.id.d);
				//total=(TextView) view.findViewById(R.id.total);


			}
		}
	}

}
