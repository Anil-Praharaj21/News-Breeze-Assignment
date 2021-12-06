package com.anilpraharaj.newsbreeze.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * @author anilpraharaj on 05/12/21
 */
@Entity(tableName = "source")
public class Source {

    @ColumnInfo(name = "id")
    public String id;

    @ColumnInfo(name = "name")
    public String name;
}
