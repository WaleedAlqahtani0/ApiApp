package com.example.apiapp.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class Movie(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id:Int,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "overview")
    val overview:String,
    @ColumnInfo(name = "poster_path")
    val posterPath:String,
    @ColumnInfo(name = "backdrop_path")
    val backdropPath:String,
    @ColumnInfo(name = "vote_average")
    val voteAverage:Double? = null,
    @ColumnInfo(name = "vote_count")
    val voteCount:Int? = null,
    @ColumnInfo(name = "page")
    val page:Int? = null,

)