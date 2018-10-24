package com.rohmats.kotlinsport.api

import java.net.URL

/**
 * Created by Rohmats on 21/10/18.
 */

class ApiRepository {
    fun doRequest(url: String): String {
        return URL(url).readText()
    }
}