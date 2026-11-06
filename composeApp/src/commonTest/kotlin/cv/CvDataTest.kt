package cv

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class CvDataTest {
    @Test
    fun periodShowsPresentWhenNoEndYear() {
        assertEquals("2022 – Present", Experience("C", "R", 2022, null, emptyList()).period)
    }

    @Test
    fun experienceIsSortedCurrentFirstThenNewest() {
        val old = Experience("A", "R", 2015, 2018, emptyList())
        val recent = Experience("B", "R", 2018, 2021, emptyList())
        val current = Experience("C", "R", 2021, null, emptyList())
        assertEquals(listOf(current, recent, old), CvData.experienceNewestFirst(listOf(old, current, recent)))
    }

    @Test
    fun skillsAreGroupedByCategory() {
        val grouped = CvData.skillsByCategory(listOf(Skill("Kotlin", "Languages"), Skill("Compose", "UI"), Skill("Java", "Languages")))
        assertEquals(listOf("Kotlin", "Java"), grouped["Languages"])
        assertEquals(listOf("Compose"), grouped["UI"])
    }

    @Test
    fun profileContentIsFilledIn() {
        assertTrue(CvData.profile.name.isNotBlank())
        assertTrue(CvData.profile.links.all { it.url.startsWith("https://") })
    }
}
