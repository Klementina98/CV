package cv

/** All CV content lives here so it can be edited without touching UI code. Replace the placeholders. */
data class Profile(
    val name: String,
    val title: String,
    val summary: String,
    val links: List<Link>,
)

data class Link(val label: String, val url: String)

data class Experience(
    val company: String,
    val role: String,
    val startYear: Int,
    val endYear: Int?, // null = present
    val highlights: List<String>,
) {
    val period: String get() = "$startYear – ${endYear ?: "Present"}"
}

data class Skill(val name: String, val category: String)

data class Project(val name: String, val description: String, val tech: List<String>, val url: String?)

object CvData {
    val profile = Profile(
        name = "Your Name",
        title = "Android Engineer",
        summary = "Replace with a 2–3 sentence pitch: what you build, what you care about, what you're looking for.",
        links = listOf(
            Link("GitHub", "https://github.com/your-handle"),
            Link("LinkedIn", "https://www.linkedin.com/in/your-handle"),
        ),
    )

    val experience = listOf(
        Experience(
            company = "Company A",
            role = "Android Engineer",
            startYear = 2022,
            endYear = null,
            highlights = listOf("Placeholder achievement with a measurable result.", "Placeholder achievement."),
        ),
        Experience(
            company = "Company B",
            role = "Junior Android Developer",
            startYear = 2020,
            endYear = 2022,
            highlights = listOf("Placeholder achievement."),
        ),
    )

    val skills = listOf(
        Skill("Kotlin", "Languages"),
        Skill("Jetpack Compose", "UI"),
        Skill("Coroutines & Flow", "Concurrency"),
        Skill("Compose Multiplatform", "UI"),
        Skill("Gradle", "Tooling"),
    )

    val projects = listOf(
        Project(
            name = "This CV",
            description = "Built with Compose Multiplatform for Web (Wasm).",
            tech = listOf("Kotlin", "Compose Multiplatform", "Wasm"),
            url = null,
        ),
    )

    /** Experience sorted newest first; current roles (no end year) come first. */
    fun experienceNewestFirst(items: List<Experience> = experience): List<Experience> =
        items.sortedWith(compareByDescending<Experience> { it.endYear == null }.thenByDescending { it.endYear ?: Int.MAX_VALUE }.thenByDescending { it.startYear })

    fun skillsByCategory(items: List<Skill> = skills): Map<String, List<String>> =
        items.groupBy({ it.category }, { it.name })
}
