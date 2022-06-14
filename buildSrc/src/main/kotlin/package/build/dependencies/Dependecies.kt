import org.gradle.kotlin.dsl.DependencyHandlerScope

fun DependencyHandlerScope.appDependencies() {
    implementation(
        "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Version.kotlin}",
        "androidx.activity:activity-compose:${Version.activity_compose}",
        "androidx.appcompat:appcompat:${Version.app_compat}",
        "androidx.core:core-ktx:${Version.core_ktx}",
        "androidx.compose.ui:ui:${Version.compose_version}",
        "androidx.compose.material:material:${Version.compose_version}",
        "androidx.compose.ui:ui-tooling-preview:${Version.compose_version}",
        "androidx.lifecycle:lifecycle-runtime-ktx:${Version.lifecycle_runtime}",
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.kotlinx_coroutines}",
        "com.jakewharton.timber:timber:${Version.timber}"
    )
}

fun DependencyHandlerScope.blueLibDependencies() {
    implementation(
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.kotlinx_coroutines}",
        "com.benasher44:uuid:${Version.uuid}",
        "com.jakewharton.timber:timber:${Version.timber}"
    )
}

fun DependencyHandlerScope.unitTestDependencies() {
    testImplementation(
        "junit:junit:${Version.junit}"
    )
}

fun DependencyHandlerScope.androidTestDependencies() {
    androidTestImplementation(
        "androidx.test.ext:junit:${Version.androidx_junit}",
        "androidx.test.espresso:espresso-core:${Version.espresso_core}",
        "androidx.compose.ui:ui-test-junit4:${Version.compose_version}",
        "androidx.compose.ui:ui-tooling:${Version.compose_version}"
    )
}

private fun DependencyHandlerScope.implementation(vararg dependencies: String) =
    dependencies.forEach {
        add("implementation", it)
    }

private fun DependencyHandlerScope.testImplementation(vararg dependencies: String) =
    dependencies.forEach {
        add("testImplementation", it)
    }

private fun DependencyHandlerScope.androidTestImplementation(vararg dependencies: String) =
    dependencies.forEach { add("androidTestImplementation", it) }
