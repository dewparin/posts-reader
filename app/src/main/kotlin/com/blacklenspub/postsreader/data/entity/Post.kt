package com.blacklenspub.postsreader.data.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "post")
data class Post(

        @PrimaryKey
        @ColumnInfo(name = "id")
        @SerializedName("id")
        val id: String,

        @ColumnInfo(name = "title")
        @SerializedName("title")
        val title: String,

        @ColumnInfo(name = "body")
        @SerializedName("body")
        val body: String
)