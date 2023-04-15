import java.util.Properties

plugins {
    id("com.android.application") version "7.4.2"
}

repositories {
    google()
    mavenCentral()
    maven {
        // Used only for PhotoView
        name = "JitPack Github wrapper"
        setUrl("https://www.jitpack.io")
    }
    jcenter()
}

dependencies {
    implementation("androidx.sharetarget:sharetarget:1.2.0")
    implementation("androidx.webkit:webkit:1.5.0")
    implementation("androidx.multidex:multidex:2.0.1")
    implementation("androidx.appcompat:appcompat:1.5.1")
    implementation("com.google.android.material:material:1.7.0")
    implementation("androidx.legacy:legacy-support-v13:1.0.0")
    implementation("androidx.preference:preference:1.2.0") {
        exclude(group = "androidx.lifecycle", module = "lifecycle-viewmodel")
        exclude(group = "androidx.lifecycle", module = "lifecycle-viewmodel-ktx")
    }
    implementation("androidx.legacy:legacy-preference-v14:1.0.0")
    implementation("androidx.exifinterface:exifinterface:1.3.5")
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation("androidx.lifecycle:lifecycle-common-java8:2.5.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel:2.5.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")
    implementation("androidx.work:work-runtime:2.7.1")
    implementation("com.google.android.exoplayer:exoplayer-core:2.9.6") // plays video and audio
    implementation("com.google.android.exoplayer:exoplayer-ui:2.9.6")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.journeyapps:zxing-android-embedded:3.4.0") // QR Code scanner
    implementation("com.fasterxml.jackson.core:jackson-databind:2.11.1") // used as JSON library
    implementation("me.leolin:ShortcutBadger:1.1.16") // display messagecount on the home screen icon.
    implementation("com.jpardogo.materialtabstrip:library:1.0.9") // used in the emoji selector for the tab selection.
    implementation("com.github.chrisbanes:PhotoView:2.1.3") // does the zooming on photos / media
    implementation("com.github.penfeizhou.android.animation:glide-plugin:2.10.0") // APNG & animated webp support.
    implementation("com.caverock:androidsvg-aar:1.4") // SVG support.
    implementation("com.github.bumptech.glide:glide:4.12.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.12.0")
    annotationProcessor("androidx.annotation:annotation:1.5.0")
    implementation("com.makeramen:roundedimageview:2.1.0") // crops the avatars to circles
    implementation("com.pnikosis:materialish-progress:1.5") // used only in the "Progress Wheel" in Share Activity.
    implementation("com.soundcloud.android:android-crop:0.9.10@aar") // used in Group Select Avatar, should be unified with profile
    implementation("com.nineoldandroids:library:2.4.0") // DEPRECATED! Used to slide in the half-camera.
    implementation("mobi.upod:time-duration-picker:1.1.3")// Used to pick the time for inactivity.
    implementation("com.amulyakhare:com.amulyakhare.textdrawable:1.0.1")  // number of unread messages,
         // the one-letter circle for the contacts (when there is not avatar) and a white background.
    implementation("com.googlecode.mp4parser:isoparser:1.0.6") // MP4 recoding; upgrading eg. to 1.1.22 breaks recoding, however, i have not investigated further, just reset to 1.0.6
    implementation("com.davemorrissey.labs:subsampling-scale-image-view:3.6.0") { // for the zooming on photos / media
        exclude(group = "com.android.support", module = "support-annotations")
    }
    implementation("com.annimon:stream:1.1.8") // brings future java streams api to SDK Version < 24
    implementation("com.codewaves.stickyheadergrid:stickyheadergrid:0.9.4") // glues the current time segment text in the gallery to the top.
    implementation("org.maplibre.gl:android-sdk:9.5.2") {
        exclude(group = "com.google.android.gms")
    }

    testImplementation("junit:junit:4.13.1")
    testImplementation("org.assertj:assertj-core:1.7.1")
    testImplementation("org.mockito:mockito-core:1.9.5")
    testImplementation("org.powermock:powermock-api-mockito:1.6.1")
    testImplementation("org.powermock:powermock-module-junit4:1.6.1")
    testImplementation("org.powermock:powermock-module-junit4-rule:1.6.1")
    testImplementation("org.powermock:powermock-classloading-xstream:1.6.1")

    androidTestImplementation("androidx.test:runner:1.4.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    androidTestImplementation("androidx.test.espresso:espresso-contrib:3.4.0")
    androidTestImplementation("androidx.test:rules:1.4.0")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("com.android.support:support-annotations:28.0.0")

    androidTestImplementation("org.assertj:assertj-core:1.7.1") {
        exclude(group = "org.hamcrest", module = "hamcrest-core")
    }
}


android {
    namespace = "org.thoughtcrime.securesms"
    compileSdkVersion = "android-32"

    // Set NDK version to strip native libraries.
    // Even though we compile our libraries outside Gradle with `scripts/ndk-make.sh`,
    // without ndkVersion `./gradlew clean` followed by `./gradlew assembleDebug --warning-mode=all` emits the following warning:
    //   > Task :stripFatDebugDebugSymbols
    //   Unable to strip the following libraries, packaging them as they are: libanimation-decoder-gif.so, libmapbox-gl.so, libnative-utils.so.
    // See <https://issuetracker.google.com/issues/237187538> for details.
    ndkVersion = "23.2.8568313"

    useLibrary("org.apache.http.legacy")

    defaultConfig {
        versionCode = 651
        versionName = "1.36.2"

        applicationId = "com.b44t.messenger"
        multiDexEnabled = true

        minSdkVersion(16)
        targetSdkVersion(32)

        vectorDrawables.useSupportLibrary = true

        // base name of the generated apk
        project.ext.set("archivesBaseName", "deltachat");

        buildConfigField("boolean", "DEV_BUILD", "false")
        buildConfigField("String", "MAP_ACCESS_TOKEN", "\"pk.eyJ1IjoiZGVsdGFjaGF0IiwiYSI6ImNqc3c1aWczMzBjejY0M28wZmU0a3cwMzMifQ.ZPTH9dFJaav06RAu4rTYHw\"")

        ndk {
            abiFilters += listOf("armeabi-v7a", "arm64-v8a", "x86", "x86_64")
        }

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "TEST_ADDR", buildConfigProperty("TEST_ADDR"))
        buildConfigField("String", "TEST_MAIL_PW", buildConfigProperty("TEST_MAIL_PW"))
        buildConfigField("String", "NDK_ARCH", getNdkArch())
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    packagingOptions {
        exclude("LICENSE.txt")
        exclude("LICENSE")
        exclude("NOTICE")
        exclude("asm-license.txt")
        exclude("META-INF/LICENSE")
        exclude("META-INF/NOTICE")
        doNotStrip("*/mips/*.so")
        doNotStrip("*/mips64/*.so")
    }

    signingConfigs {
        getByName("debug") {
            // add `DC_DEBUG_STORE_FILE=/path/to/debug.keystore` to `~/.gradle/gradle.properties`
            if(project.hasProperty("DC_DEBUG_STORE_FILE")) {
                storeFile = file(project.property("DC_DEBUG_STORE_FILE"))
            }
        }
        create("release") {
            // can be defined at `~/.gradle/gradle.properties` or at "Build/Generate signed APK"
            if(project.hasProperty("DC_RELEASE_STORE_FILE")) {
                storeFile = file(project.property("DC_RELEASE_STORE_FILE"))
                storePassword = project.property("DC_RELEASE_STORE_PASSWORD") as String
                keyAlias = project.property("DC_RELEASE_KEY_ALIAS") as String
                keyPassword = project.property("DC_RELEASE_KEY_PASSWORD") as String
            }
        }
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            applicationIdSuffix = ".beta"
        }
        getByName("release") {
            // minification and proguard disabled for now.
            //
            // when enabled, it can cut down apk size about 6%,
            // however this also has the potential to break things.
            // so exceptions are needed and have to be maintained.
            // (see git-history and https://github.com/deltachat/deltachat-android/issues/905 )
            //
            // nb: it is highly recommended to use the same settings in debug+release -
            // otherwise problems might be noticed delayed only
            isMinifyEnabled = false
	    signingConfigs.findByName("release")?.let { releaseSigningConfig ->
            	signingConfig = releaseSigningConfig
	    }
        }
    }

    flavorDimensions += "none"
    productFlavors {
        create("fat") {
            dimension = "none"
        }
        create("gplay") {
            dimension = "none"
            applicationId = "chat.delta"
        }
    }

    applicationVariants.all {
        val variant = this
        variant.outputs
	    .map { it as com.android.build.gradle.internal.api.BaseVariantOutputImpl }
	    .forEach { output ->
	        val outputFileName = variant
		output.outputFileName = "deltachat-${variant.baseName}-${variant.versionName}.apk";

            }
    }

    sourceSets {
        getByName("main") {
            manifest.srcFile("AndroidManifest.xml")
	    java.srcDirs("src")
            resources.srcDirs("src")
            aidl.srcDirs("src")
            renderscript.srcDirs("src")
            res.srcDirs("res")
            assets.srcDirs("assets")
            jniLibs.srcDirs("libs")
        }
        getByName("androidTest") {
            java.srcDirs("androidTest")
        }
    }
    lint {
        abortOnError = false
    }

}

fun buildConfigProperty(name: String): String {
    return "\"${propertyOrEmpty(name)}\""
}

fun propertyOrEmpty(name: String): String {
    val p = findProperty(name)
    if (p != null) {
        return p as String
    }
    return environmentVariable(name)
}

fun environmentVariable(name: String): String {
    return System.getenv(name) ?: ""
}

fun getNdkArch(): String {
    val properties = Properties()
    val file = project.rootProject.file("ndkArch")
    if (!file.exists()) return "\"\""
    val inputStream = file.inputStream()
    inputStream.use {
        properties.load(it)
    }
    val arch = properties.getProperty("NDK_ARCH")
    if (arch == null) return "\"\""
    return "\"$arch\""
}
