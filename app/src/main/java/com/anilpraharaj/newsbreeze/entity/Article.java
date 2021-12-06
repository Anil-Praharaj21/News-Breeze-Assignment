package com.anilpraharaj.newsbreeze.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.anilpraharaj.newsbreeze.baseClass.BaseArticle;

/**
 * @author anilpraharaj on 05/12/21
 */
@Entity(tableName = "article")
public class Article extends BaseArticle implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "bookmarked")
    private boolean bookmarked = false;

    public Article(int uid, String author, Source source, String title, String description, String url, String urlToImage, String publishedAt, String content, boolean bookmarked) {
        super(author, source, title, description, url, urlToImage, publishedAt, content);
        this.uid = uid;
        this.bookmarked = bookmarked;
    }

    public Article(Parcel in) {
        super(in);
        uid = in.readInt();
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public boolean isBookmarked() {
        return bookmarked;
    }

    public void setBookmarked(boolean bookmarked) {
        this.bookmarked = bookmarked;
    }

    public static final Creator<Article> CREATOR = new Creator<Article>() {
        @Override
        public Article createFromParcel(Parcel in) {
            return new Article(in);
        }

        @Override
        public Article[] newArray(int size) {
            return new Article[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(uid);
        parcel.writeString(getAuthor());
        parcel.writeString(getTitle());
        parcel.writeString(getDescription());
        parcel.writeString(getUrl());
        parcel.writeString(getUrlToImage());
        parcel.writeString(getPublishedAt());
        parcel.writeString(getContent());
//        parcel.writeTypedObject(getSource(), i);

    }
}
