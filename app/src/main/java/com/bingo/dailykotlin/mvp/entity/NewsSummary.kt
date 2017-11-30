package com.bingo.dailykotlin.mvp.entity

/**
 * @author bingo.
 * @date Create on 2017/11/29.
 * @Description
 */
class NewsSummary {
    var postid: String? = null
    var isHasCover: Boolean = false
    var hasHead: Int = 0
    var replyCount: Int = 0
    var hasImg: Int = 0
    var digest: String? = null
    var isHasIcon: Boolean = false
    var docid: String? = null
    var title: String? = null
    var ltitle: String? = null
    var order: Int = 0
    var priority: Int = 0
    var lmodify: String? = null
    var boardid: String? = null
    var photosetID: String? = null
    var template: String? = null
    var votecount: Int = 0
    var skipID: String? = null
    var alias: String? = null
    var skipType: String? = null
    var cid: String? = null
    var hasAD: Int = 0
    var source: String? = null
    var ename: String? = null
    var imgsrc: String? = null
    var tname: String? = null
    var ptime: String? = null
    /**
     * title : "悬崖村" 孩子上学需爬800米悬崖
     * tag : photoset
     * imgsrc : http://img1.cache.netease.com/3g/2016/5/24/2016052421435478ea5.jpg
     * subtitle :
     * url : 00AP0001|119327
     */

    var ads: List<AdsBean>? = null
    /**
     * imgsrc : http://img3.cache.netease.com/3g/2016/5/24/2016052416484243560.jpg
     */

    var imgextra: List<ImgextraBean>? = null

    class AdsBean {
        var title: String? = null
        var tag: String? = null
        var imgsrc: String? = null
        var subtitle: String? = null
        var url: String? = null
    }

    class ImgextraBean {
        var imgsrc: String? = null
    }
}