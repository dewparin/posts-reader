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

// Kotlin Data Class Issue

// room need empty constructor or constructor with fields. So, data class cannot be used.
// Error:Entities and Pojos must have a usable public constructor. You can have an empty constructor or a constructor whose parameters match the fields (by name and type).
// Possibly solution
// - Make sure kotlin gens result for Post class before Room compiler start validation.
//@Entity(tableName = "post")
//data class Post(
//
//        @PrimaryKey
//        @ColumnInfo(name = "id")
//        @SerializedName("id")
//        val id: String = "",
//
//        @ColumnInfo(name = "title")
//        @SerializedName("title")
//        val title: String = "",
//
//        @ColumnInfo(name = "body")
//        @SerializedName("body")
//        val body: String = ""
//)
