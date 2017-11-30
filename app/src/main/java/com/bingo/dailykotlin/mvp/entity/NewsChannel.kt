package com.bingo.dailykotlin.mvp.entity

/**
 * @author bingo.
 * @date Create on 2017/11/27.
 * @Description
 */
data class NewsChannel(val newsChannelName: String,
                       val newsChannelId: String,
                       val newsChannelType: String,
                       val newsChannelSelect: Boolean,
                       val newsChannelIndex: Int,
                       var newsChannelFixed: Boolean)