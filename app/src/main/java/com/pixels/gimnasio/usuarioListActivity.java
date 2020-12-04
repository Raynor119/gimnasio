package com.pixels.gimnasio;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.pixels.gimnasio.dummy.DummyContentU;
import java.util.List;

/**
 * An activity representing a list of usuarios. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link usuarioDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class usuarioListActivity extends AppCompatActivity {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
	String user,cont,cod;
	TextView usern;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_list);
		Bundle extra = getIntent().getExtras();	
		user=extra.getString("Usuario");		
		cont=extra.getString("Contraseña");		
		cod=extra.getString("Codigo");
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        //toolbar.setTitle(getTitle());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        if (findViewById(R.id.usuario_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
			usern=(TextView) findViewById(R.id.username);
			usern.setText(user);
			Bundle arguments = new Bundle();
			arguments.putString(usuarioDetailFragment.ARG_ITEM_ID, cod);
			usuarioDetailFragment fragment = new usuarioDetailFragment();
			fragment.setArguments(arguments);
			this.getSupportFragmentManager().beginTransaction()
				.replace(R.id.usuario_detail_container, fragment)
				.commit();
			fragment.ARG_ITEM_ID=cod;
        }

        View recyclerView = findViewById(R.id.usuario_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(this, DummyContentU.ITEMS, mTwoPane));
    }

    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final usuarioListActivity mParentActivity;
        private final List<DummyContentU.DummyItem> mValues;
        private final boolean mTwoPane;
        private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DummyContentU.DummyItem item = (DummyContentU.DummyItem) view.getTag();
                if (mTwoPane) {
                    Bundle arguments = new Bundle();
					
					
					
					if( item.id.equals("4")){
						basedeinicio n=new basedeinicio(getApplicationContext());
						n.inic("1","nada","nada","nada","nada");
						Intent intent = new Intent(usuarioListActivity.this, MainActivity.class);
						startActivity(intent);

						finish();
					}
					if(item.id.equals("1")){
						Context context = view.getContext();
						Intent intent = new Intent(context, ruiusu.class);
						intent.putExtra("codigo",cod);
						context.startActivity(intent);
					}
					
					if(item.id.equals("3")){
						arguments.putString(usuarioDetailFragment.ARG_ITEM_ID, cod);
						usuarioDetailFragment fragment = new usuarioDetailFragment();
						 fragment.setArguments(arguments);
						 mParentActivity.getSupportFragmentManager().beginTransaction()
						        .replace(R.id.usuario_detail_container, fragment)
						       .commit();
						fragment.ARG_ITEM_ID=cod;
					}
					
					
					
					
					
					
                  //  
                } else {
					
					if( item.id.equals("4")){

						basedeinicio n=new basedeinicio(getApplicationContext());
						n.inic("1","nada","nada","nada","nada");
						Intent intent = new Intent(usuarioListActivity.this, MainActivity.class);
						startActivity(intent);

						finish();

					}
					if(item.id.equals("1")){
						Context context = view.getContext();
						Intent intent = new Intent(context, ruiusu.class);
						intent.putExtra("codigo",cod);
						context.startActivity(intent);
					}
					if(item.id.equals("3")){
						Context context = view.getContext();
						Intent intent = new Intent(context, usuarioDetailActivity.class);
						intent.putExtra(usuarioDetailFragment.ARG_ITEM_ID, item.id);
						intent.putExtra("codigo",cod);
                        context.startActivity(intent);
					}
					
					
					
                    //
                }
            }
        };

        SimpleItemRecyclerViewAdapter(usuarioListActivity parent,
                                      List<DummyContentU.DummyItem> items,
                                      boolean twoPane) {
            mValues = items;
            mParentActivity = parent;
            mTwoPane = twoPane;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.usuario_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
           // holder.mIdView.setText(mValues.get(position).id);
            holder.mContentView.setText(mValues.get(position).content);
			if(mValues.get(position).id.equals("1")){
				holder.imagen.setImageResource(R.drawable.rutii);
			}
			if(mValues.get(position).id.equals("2")){
				holder.imagen.setImageResource(R.drawable.realipago);
			}
			if(mValues.get(position).id.equals("3")){
				holder.imagen.setImageResource(R.drawable.darosp);
			}
			if(mValues.get(position).id.equals("4")){
				holder.imagen.setImageResource(R.drawable.cera);
			}
			
            holder.itemView.setTag(mValues.get(position));
            holder.itemView.setOnClickListener(mOnClickListener);
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
           // final TextView mIdView;
            final TextView mContentView;
			final ImageView imagen;
			

            ViewHolder(View view) {
                super(view);
				imagen=(ImageView) view.findViewById(R.id.image);
               // mIdView = (TextView) view.findViewById(R.id.id_text);
                mContentView = (TextView) view.findViewById(R.id.content);
            }
        }
    }
}
