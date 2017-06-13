package com.blacklenspub.postsreader.data.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "post")
class Post {

    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id")
    lateinit var id: String

    @ColumnInfo(name = "title")
    @SerializedName("title")
    lateinit var title: String

    @ColumnInfo(name = "body")
    @SerializedName("body")
    lateinit var body: String
}
