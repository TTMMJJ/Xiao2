package com.bwie.bean;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import java.util.List;
/**
 * Created by T_baby on 17/11/11.
 */
public class Myadapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<News.DataBean> news;
    Context context;
    ItemListion itemListion;
    public void SetItemListion(ItemListion itemListion) {
        this.itemListion = itemListion;
    }
    public Myadapter(List<News.DataBean> news, Context context) {
        this.news = news;
        this.context = context;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item,null,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        News.DataBean bean = news.get(position);
        ViewHolder holder1 = (ViewHolder) holder;
        holder1.img.setImageURI(bean.getImg());
        holder1.name.setText(bean.getOtime());
        holder1.title.setText(bean.getTitle());
    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView img;
        TextView name, title;

        public ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            name = itemView.findViewById(R.id.name);
            title = itemView.findViewById(R.id.title);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemListion.OnitemListion(v, getPosition());
                }
            });
        }
    }

    public interface ItemListion {
        void OnitemListion(View view, int Postion);
    }
}
