# Required for SDK Mediation

# For Adcolony
-dontwarn android.webkit.**
-dontwarn com.adcolony.**
-keep class com.adcolony.sdk.* { *; }

# For Admob
-keep class com.google.android.gms.ads.** { *; }

# For Applovin
-dontwarn com.applovin.**
-keep class com.applovin.** { *; }

# For AppNext
-keep class com.appnext.** { *; }
-dontwarn com.appnext.**

# For Chartboost
-dontwarn com.chartboost.sdk.**
-keep class com.chartboost.sdk.** { *; }

# For Facebook
-dontwarn com.facebook.ads.**
-keep class com.facebook.ads.** { *; }

# For InMobi
-keep class com.squareup.picasso.** {*;}
-dontwarn com.squareup.picasso.**
-dontwarn com.squareup.okhttp.**
-dontwarn com.inmobi.**
-keep class com.inmobi.** { *; }

# For Millennial Media
-dontwarn com.millennialmedia.**
-keep class com.millennialmedia.** { *; }

# For Mopub
-dontwarn com.mopub.**
-keep class com.mopub.** { *; }

# For MyTarget
-dontwarn com.my.target.**
-keep class com.my.target.** { *; }

# For RhythmOne
-dontwarn com.rhythmone.ad.sdk.**
-keep class com.rhythmone.ad.sdk.** { *; }

# For Tremor
-dontwarn com.tremorvideo.sdk.**
-keep class com.tremorvideo.sdk.android.videoad.** { *; }
-dontwarn com.doubleverify.dvsdk.termor.**
-keep class com.doubleverify.dvsdk.termor.** { *; }

# For Unity Ads
-dontwarn com.unity3d.ads.**
-keep class com.unity3d.ads.** { *; }

# For Vungle
-dontwarn com.vungle.**
-dontnote com.vungle.**
-keep class com.vungle.** { *; }
-keep class javax.inject.*

# GreenRobot
-dontwarn de.greenrobot.event.util.**

# RxJava
-dontwarn rx.internal.util.unsafe.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
   long producerIndex;
   long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
   rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
   rx.internal.util.atomic.LinkedQueueNode consumerNode;
}
-keep class rx.schedulers.Schedulers { public static <methods>; }
-keep class rx.schedulers.ImmediateScheduler { public <methods>; }
-keep class rx.schedulers.TestScheduler { public <methods>; }
-keep class rx.schedulers.Schedulers { public static ** test(); }

# MOAT
-keep class com.moat.** { public protected private *; }

# Retrofit
-dontwarn okio.**
-dontwarn retrofit2.Platform$Java8

# for GSON (Vungle)
-keepattributes Signature
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.examples.android.model.** { *; }

# For Flurry by Yahoo
-dontwarn android.support.customtabs.**
-dontwarn com.flurry.android.**
-dontwarn com.flurry.sdk.**
-keep class com.flurry.** { *; }

# For A9
-dontwarn com.amazon.device.ads.**
-keep class com.amazon.device.ads.** { *; }