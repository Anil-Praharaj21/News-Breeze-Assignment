package com.anilpraharaj.newsbreeze.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anilpraharaj.newsbreeze.R;
import com.anilpraharaj.newsbreeze.callback.AdapterItemOnClickCb;
import com.anilpraharaj.newsbreeze.constant.Constant;
import com.anilpraharaj.newsbreeze.entity.Article;
import com.anilpraharaj.newsbreeze.entity.BookmarkArticle;
import com.anilpraharaj.newsbreeze.utils.DateFormatter;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * @author anilpraharaj on 05/12/21
 */
public class BookmarkAdapter extends RecyclerView.Adapter<BookmarkAdapter.BookmarkViewHolder> {

    private ArrayList<BookmarkArticle> mBookmarkArrayList;
    private AdapterItemOnClickCb mItemOnClickCb;

    public BookmarkAdapter() {
        this.mBookmarkArrayList = new ArrayList<>();
    }

    public void setmBookmarkArrayList(ArrayList<BookmarkArticle> mBookmarkArrayList, AdapterItemOnClickCb itemOnClickCb) {
        this.mBookmarkArrayList = mBookmarkArrayList;
        this.mItemOnClickCb = itemOnClickCb;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BookmarkAdapter.BookmarkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(Constant.BOOKMARK_LIST_ITEM_ID, parent,false);
        return new BookmarkAdapter.BookmarkViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull BookmarkAdapter.BookmarkViewHolder holder, int position) {
        BookmarkArticle bookmarkArticle = mBookmarkArrayList.get(position);
        if (bookmarkArticle != null) {
            Glide.with(holder.itemView.getContext())
                    .load(bookmarkArticle.getUrlToImage())
                    .placeholder(R.drawable.image_placeholder)
                    .centerCrop()
                    .into(holder.bookmarkImage);

            holder.bookmarkTitle.setText("#" + (bookmarkArticle.getSource().id != null ? bookmarkArticle.getSource().id : "NA"));
            holder.bookmarkDescription.setText(bookmarkArticle.getDescription());
            holder.bookmarkDate.setText(new DateFormatter(bookmarkArticle.getPublishedAt(),
                    DateFormatter.DEFAULT_FORMAT)
                    .format("dd-MM-yy") + " . " + (bookmarkArticle.getAuthor() != null ? bookmarkArticle.getAuthor() : "NA"));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Article article = new Article(bookmarkArticle.getUid(),
                            bookmarkArticle.getAuthor(),
                            bookmarkArticle.getSource(),
                            bookmarkArticle.getTitle(),
                            bookmarkArticle.getDescription(),
                            bookmarkArticle.getUrl(),
                            bookmarkArticle.getUrlToImage(),
                            bookmarkArticle.getPublishedAt(),
                            bookmarkArticle.getContent(),
                            true);
                    mItemOnClickCb.onClickListener(article);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mBookmarkArrayList != null ? mBookmarkArrayList.size() : 0;
    }

    class BookmarkViewHolder extends RecyclerView.ViewHolder {

        ImageView bookmarkImage;
        TextView bookmarkTitle, bookmarkDescription, bookmarkDate;

        public BookmarkViewHolder(@NonNull View itemView) {
            super(itemView);

            bookmarkImage = itemView.findViewById(Constant.BOOKMARK_ITEM_IMAGE);
            bookmarkTitle = itemView.findViewById(Constant.BOOKMARK_ITEM_TITLE);
            bookmarkDescription = itemView.findViewById(Constant.BOOKMARK_ITEM_DESCRIPTION);
            bookmarkDate = itemView.findViewById(Constant.BOOKMARK_ITEM_DATE);
        }
    }
}
