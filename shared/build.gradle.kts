import org.jetbrains.kotlin.gradle.plugin.KotlinPluginWrapper
import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import java.util.Properties

val iOSBinaryName = "shared"

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinxSerialization)
    alias(libs.plugins.sqldelight)
    alias(libs.plugins.nativeCocaopods)
    id ("maven-publish")
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "0.0.2"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
        }
    }

    val xcf = XCFramework()
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = iOSBinaryName
            //isStatic = true
            xcf.add(this)
        }
    }
    
    sourceSets {
        commonMain.dependencies {
            // put your Multiplatform dependencies here
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.negotiation)
            implementation(libs.ktor.json.serialization)
            implementation(libs.coroutines.core)
            api(libs.koin.core)
            implementation(libs.ktor.client.logger)
            implementation(libs.runtime)
        }

        androidMain.dependencies {
            implementation(libs.ktor.client.android)
            implementation(libs.android.driver)
            implementation(libs.koin.android)
        }

        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
            implementation(libs.native.driver)
        }
    }
}

android {
    namespace = "org.example.firstkmmproject.shared"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}

sqldelight {
    databases {
        create("ArticleDB") {
            packageName.set("db")
        }
    }
}

group = "com.eskhata.kmm"
version = "0.0.1"

publishing {
//    publications {
//        release(MavenPublication) {
//            from(components.release)
//
//                    // Replace with your own group ID.
//                    groupId 'com.eskhata'
//
//            // Replace with the name of your library
//            artifactId 'charts'
//            version '0.0.5'
//        }
//    }

    repositories {
        maven {
            name = "GitHubPackages"

            // Replace GITHUB_USERID with your personal or organisation user ID and
            // REPOSITORY with the name of the repository on GitHub

            //./gradlew build
            //./gradlew publish --release or --debug

            url = uri("https://maven.pkg.github.com/Amirjon0701/FirstKMMLibrary")

            credentials {
                username = Properties().getProperty("username")
                password = Properties().getProperty("password")
            }
        }
    }
}

//tasks.register("prepareReleaseOfiOSXCFramework") {
//    description = "Publish iOS framework to the Cocoa Repo"
//
//    // Create Release Framework for Xcode
//    dependsOn("assembleXCFramework", "packageDistribution")
//
//    doLast {
//
//        // Update Podspec Version
//        val poddir = File("$rootDir/$iOSBinaryName.podspec")
//        val podtempFile = File("$rootDir/$iOSBinaryName.podspec.new")
//
//        val podreader = poddir.bufferedReader()
//        val podwriter = podtempFile.bufferedWriter()
//        var podcurrentLine: String?
//
//        while (podreader.readLine().also { currLine -> podcurrentLine = currLine } != null) {
//            if (podcurrentLine?.trim()?.startsWith("spec.version") == true) {
//                podwriter.write("    spec.version       = \"${version}\"" + System.lineSeparator())
//            } else if (podcurrentLine?.trim()?.startsWith("spec.source") == true) {
//                podwriter.write("    spec.source       = { :http => \"https://github.com/rakeshchander/CachingLibrary-KMM/releases/download/${version}/${iOSBinaryName}.xcframework.zip\"}" + System.lineSeparator())
//            } else {
//                podwriter.write(podcurrentLine + System.lineSeparator())
//            }
//        }
//        podwriter.close()
//        podreader.close()
//        podtempFile.renameTo(poddir)
//
//        // Update Cartfile Version
//        val cartdir = File("$rootDir/Carthage/$iOSBinaryName.json")
//        val carttempFile = File("$rootDir/Carthage/$iOSBinaryName.json.new")
//
//        val cartreader = cartdir.bufferedReader()
//        val cartwriter = carttempFile.bufferedWriter()
//        var cartcurrentLine: String?
//
//        while (cartreader.readLine().also { currLine -> cartcurrentLine = currLine } != null) {
//            if (cartcurrentLine?.trim()?.startsWith("{") == true) {
//                cartwriter.write("{"+ System.lineSeparator())
//                cartwriter.write("    \"${version}\":\"https://github.com/rakeshchander/CachingLibrary-KMM/releases/download/${version}/${iOSBinaryName}.xcframework.zip\","+ System.lineSeparator())
//            } else if (cartcurrentLine?.trim()?.startsWith("\"${version}\"") == true) {
//                continue
//            } else {
//                cartwriter.write(cartcurrentLine + System.lineSeparator())
//            }
//        }
//        cartwriter.close()
//        cartreader.close()
//        carttempFile.renameTo(cartdir)
//
//        // Update Package.swift Version
//
//        // Calculate Checksum
//        val checksumValue: String = org.apache.commons.io.output.ByteArrayOutputStream()
//            .use { outputStream ->
//                // Calculate checksum
//                project.exec {
//                    workingDir = File("$rootDir")
//                    commandLine("swift", "package", "compute-checksum", "${iOSBinaryName}.xcframework.zip")
//                    standardOutput = outputStream
//                }
//
//                outputStream.toString()
//            }
//
//
//
//        val spmdir = File("$rootDir/Package.swift")
//        val spmtempFile = File("$rootDir/Package.swift.new")
//
//        val spmreader = spmdir.bufferedReader()
//        val spmwriter = spmtempFile.bufferedWriter()
//        var spmcurrentLine: String?
//
//        while (spmreader.readLine().also { currLine -> spmcurrentLine = currLine } != null) {
//            if (spmcurrentLine?.trim()?.startsWith("url") == true) {
//                spmwriter.write("    url: \"<GIT_REPO_URL>/releases/download/${version}/${iOSBinaryName}.xcframework.zip\"," + System.lineSeparator())
//            } else if (spmcurrentLine?.trim()?.startsWith("checksum") == true) {
//                spmwriter.write("    checksum: \"${checksumValue.trim()}\"" + System.lineSeparator())
//            } else {
//                spmwriter.write(spmcurrentLine + System.lineSeparator())
//            }
//        }
//        spmwriter.close()
//        spmreader.close()
//        spmtempFile.renameTo(spmdir)
//    }
//}
//
//tasks.create<Zip>("packageDistribution") {
//    // Delete existing XCFramework
//    delete("$rootDir/XCFramework")
//
//    // Replace XCFramework File at root from Build Directory
//    copy {
//        from("$buildDir/XCFrameworks/release")
//        into("$rootDir/XCFramework")
//    }
//
//    // Delete existing ZIP, if any
//    delete("$rootDir/${iOSBinaryName}.xcframework.zip")
//    // ZIP File Name - as per Carthage Nomenclature
//    archiveFileName.set("${iOSBinaryName}.xcframework.zip")
//    // Destination for ZIP File
//    destinationDirectory.set(layout.projectDirectory.dir("../"))
//    // Source Directory for ZIP
//    from(layout.projectDirectory.dir("../XCFramework"))
//}
