package com.bruine.lighthouse

@Target()
@Retention(AnnotationRetention.SOURCE)
annotation class DeepLink(
    val path: String,
    val baseUriOverride: String = ""
)
