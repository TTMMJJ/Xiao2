package com.bwie.bean;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by T_baby on 17/11/18.
 */

public class Myadapter2 extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<Movie.DataBean> list;
    Onclicklistion setOnclickListion;
    public Myadapter2(List<Movie.DataBean> list) {
        this.list = list;
    }
   public void setSetOnclickListion(Onclicklistion onclickListion){
        this.setOnclickListion=onclickListion;
   }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(Myapp.GetContext()).inflate(R.layout.layout,null,false);
      ViewHolder viewHolder=  new ViewHolder(view);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Movie.DataBean bean= list.get(position);
       ViewHolder ho= (ViewHolder) holder;
       ho.img.setImageURI(bean.getImage_url());
       ho.type.setText(bean.getTitle());
       ho.titile.setText(bean.getContent());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        TextView titile,type;
        SimpleDraweeView img;
        public ViewHolder(View itemView) {
            super(itemView);
           titile=itemView.findViewById(R.id.title);
           type=itemView.findViewById(R.id.type);
           img=itemView.findViewById(R.id.img);
           itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   setOnclickListion.ClickListion(v,getPosition());
               }
           });
        }

    }
    public  interface Onclicklistion{
        void ClickListion(View v, int positin);
    }
}
