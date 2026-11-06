plugins {
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.compose.multiplatform) apply false
}

// Assembles the deployable site: static landing page at "/" + Compose app at "/app/".
tasks.register<Copy>("assembleSite") {
    group = "distribution"
    description = "Builds the Compose Wasm app and merges it with the static landing page into build/site."
    dependsOn(":composeApp:wasmJsBrowserDistribution")
    into(layout.buildDirectory.dir("site"))
    from("site")
    from(project(":composeApp").layout.buildDirectory.dir("dist/wasmJs/productionExecutable")) {
        into("app")
    }
}
