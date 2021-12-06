package com.anilpraharaj.newsbreeze.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.anilpraharaj.newsbreeze.adapter.BookmarkAdapter;
import com.anilpraharaj.newsbreeze.baseClass.BaseArticle;

/**
 * @author anilpraharaj on 05/12/21
 */
@Entity(tableName = "bookmark")
public class BookmarkArticle extends BaseArticle implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int uid;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public BookmarkArticle(String author, Source source, String title, String description, String url, String urlToImage, String publishedAt, String content, int uid) {
        super(author, source, title, description, url, urlToImage, publishedAt, content);
        this.uid = uid;
    }

    public BookmarkArticle(Parcel in) {
        super(in);
        uid = in.readInt();
    }

    public static final Creator<BookmarkArticle> CREATOR = new Creator<BookmarkArticle>() {
        @Override
        public BookmarkArticle createFromParcel(Parcel in) {
            return new BookmarkArticle(in);
        }

        @Override
        public BookmarkArticle[] newArray(int size) {
            return new BookmarkArticle[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }
}
