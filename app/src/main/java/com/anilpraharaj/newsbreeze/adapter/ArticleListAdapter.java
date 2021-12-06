package com.anilpraharaj.newsbreeze.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anilpraharaj.newsbreeze.R;
import com.anilpraharaj.newsbreeze.callback.AdapterItemOnClickCb;
import com.anilpraharaj.newsbreeze.callback.BookmarkCb;
import com.anilpraharaj.newsbreeze.constant.Constant;
import com.anilpraharaj.newsbreeze.entity.Article;
import com.anilpraharaj.newsbreeze.utils.DateFormatter;
import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

/**
 * @author anilpraharaj on 05/12/21
 */
public class ArticleListAdapter extends RecyclerView.Adapter<ArticleListAdapter.ArticleViewHolder> {

    private ArrayList<Article> mArticleArrayList;
    private ArrayList<String> mBookmarkArticles;

    private BookmarkCb mBookmarkCb;
    private AdapterItemOnClickCb mItemOnClickCb;

    public ArticleListAdapter(BookmarkCb mBookmarkCb, AdapterItemOnClickCb mItemOnClickCb) {
        this.mBookmarkCb = mBookmarkCb;
        this.mItemOnClickCb = mItemOnClickCb;
        this.mArticleArrayList = new ArrayList<>();
    }

    public void setmArticleArrayList(ArrayList<Article> mArticleArrayList) {
        this.mArticleArrayList = mArticleArrayList;
        notifyDataSetChanged();
    }

    public void setmBookmarkArticles(ArrayList<String> mBookmarkArticles) {
        this.mBookmarkArticles = mBookmarkArticles;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(Constant.ARTICLE_LIST_ITEM_ID, parent,false);
        return new ArticleViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        Article article = mArticleArrayList.get(position);
        if (article != null) {
            Glide.with(holder.itemView.getContext())
                    .load(article.getUrlToImage())
                    .placeholder(R.drawable.image_placeholder)
                    .centerCrop()
                    .into(holder.articleImage);

            holder.articleTitle.setText(article.getTitle());
            holder.articleDescription.setText(article.getDescription());
            holder.articleDate.setText(new DateFormatter(article.getPublishedAt(), DateFormatter.DEFAULT_FORMAT).format(DateFormatter.STANDARD_DATE_FORMAT));

            if (mBookmarkArticles != null && mBookmarkArticles.size() > 0) {
                article.setBookmarked(mBookmarkArticles.contains(article.getTitle()));
            }
            holder.articleBookmark.setChecked(article.isBookmarked());
            holder.articleBookmark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    article.setBookmarked(!article.isBookmarked());
                    mBookmarkCb.onClickBookmarkButton(article);
                }
            });

            holder.articleSaveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    article.setBookmarked(!article.isBookmarked());
                    mBookmarkCb.onClickBookmarkButton(article);
                }
            });

            holder.articleReadButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemOnClickCb.onClickListener(article);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mArticleArrayList != null ? mArticleArrayList.size() : 0;
    }

    class ArticleViewHolder extends RecyclerView.ViewHolder {

        ImageView articleImage;
        TextView articleTitle, articleDescription, articleDate;
        CheckBox articleBookmark;
        MaterialButton articleReadButton, articleSaveButton;

        public ArticleViewHolder(@NonNull View itemView) {
            super(itemView);

            articleImage = itemView.findViewById(Constant.ARTICLE_LIST_IMAGE_ID);
            articleTitle = itemView.findViewById(Constant.ARTICLE_LIST_TITLE_ID);
            articleDescription = itemView.findViewById(Constant.ARTICLE_LIST_DESCRIPTION_ID);
            articleDate = itemView.findViewById(Constant.ARTICLE_LIST_DATE_ID);
            articleBookmark = itemView.findViewById(Constant.ARTICLE_LIST_CHECK_BOX_ID);
            articleReadButton = itemView.findViewById(Constant.ARTICLE_LIST_READ_BUTTON_ID);
            articleSaveButton = itemView.findViewById(Constant.ARTICLE_LIST_SAVE_BUTTON);
        }
    }
}
