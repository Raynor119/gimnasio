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


public class ruiusu extends AppCompatActivity
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
		setContentView(R.layout.rutia);


		
		Bundle extra = getIntent().getExtras();		
		codi=extra.getString("codigo");
		recyclerView1 =(RecyclerView) findViewById(R.id.listru);
		assert recyclerView1 != null;
        reclicle();
		
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


					recyclerView1.setAdapter(new SimpleItemRecyclerViewAdapter(ruiusu.this, vs));
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
		private final ruiusu mParentActivity; 
		private final List<rutina> vusa;		
		private Transition transicion;		

		private final View.OnClickListener mOnClickListener = new View.OnClickListener() { 
			@Override public void onClick(View view) {
				TextView cc;
				final View vie=view;
				
				
				String time=vusa.get(recyclerView1.getChildAdapterPosition(view)).getRutina();
				
				//horas
				int horas;
				char a=time.charAt(0);
				if((a+"").equals("0")){
					a=time.charAt(1);
					String n=""+a;
					horas=Integer.parseInt(n);
				}else{
					char b=time.charAt(0);
					a=time.charAt(1);
					String n=""+b+a;
					horas=Integer.parseInt(n);
				}
				//minutos
				int mun;
				char c=time.charAt(3);
				if((c+"").equals("0")){
					c=time.charAt(4);
					String n=""+c;
					mun=Integer.parseInt(n);
				}else{
					char b=time.charAt(3);
					c=time.charAt(4);
					String n=""+b+c;
					mun=Integer.parseInt(n);
				}
				
				String timee=""+((horas*60)+mun);
				
				
				Context context = view.getContext();
				Intent intent = new Intent(context, CronometroActivity.class);
				intent.putExtra("tiempo",timee);
				intent.putExtra("ruti",vusa.get(recyclerView1.getChildAdapterPosition(view)).getNombre());
				context.startActivity(intent);
				
				
				


			}
		};


		SimpleItemRecyclerViewAdapter(ruiusu parent,
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
