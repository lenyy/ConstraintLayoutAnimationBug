package pt.geomais.constraintlayoutanimationbug;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.widget.RecyclerView;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ViewHolder> {

    private String[] mDataset;
    private Context mContext;


    RVAdapter(String[] myDataset, Context context) {
        mDataset = myDataset;
        mContext = context;
    }


    @NonNull
    @Override
    public RVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                   int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item, parent, false);
        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder( @NonNull ViewHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return mDataset.length;
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener
    {
        @BindView(R.id.container) ConstraintLayout container;
        @BindView(R.id.imageView) ImageView imageView;
        @BindView(R.id.text1) TextView text1;
        @BindView(R.id.text2) TextView text2;
        @BindView(R.id.text3) TextView text3;
        @BindView(R.id.text4) TextView text4;
        @BindView(R.id.text5) TextView text5;
        @BindView(R.id.text6) TextView text6;

        boolean expand;
        private ConstraintSet normalConstraintSet;
        private ConstraintSet expandedConstraintSet;

        ViewHolder(View v)
        {
            super(v);
            v.setOnClickListener(this);
            ButterKnife.bind(this, v);
            this.expand = true;
            normalConstraintSet = new ConstraintSet();
            normalConstraintSet.clone(container);
            expandedConstraintSet = new ConstraintSet();
            expandedConstraintSet.clone(container);
            expandedConstraintSet.setVisibility(R.id.text4, View.VISIBLE);
            expandedConstraintSet.setVisibility(R.id.text5, View.VISIBLE);
            expandedConstraintSet.setVisibility(R.id.text6, View.VISIBLE);
        }


        @Override
        public void onClick( View v )
        {
            ConstraintSet setToApply = expand ? expandedConstraintSet : normalConstraintSet;
            Transition transition = expand ?  TransitionInflater.from(mContext).
                    inflateTransition(R.transition.expand) :
                    TransitionInflater.from(mContext).
                            inflateTransition(R.transition.contract);

            TransitionManager.beginDelayedTransition(container, transition);
            setToApply.applyTo(container);
            expand = !expand;
        }
    }
}
