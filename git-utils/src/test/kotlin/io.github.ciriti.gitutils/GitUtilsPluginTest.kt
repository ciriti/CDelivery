package io.github.ciriti.gitutils

import io.github.ciriti.gitutils.Constants.TASK_NAME
import org.gradle.testkit.runner.GradleRunner
import org.gradle.testkit.runner.TaskOutcome
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import java.io.File

class GitUtilsPluginTest {

    @Rule
    @JvmField
    val testProjectDir: TemporaryFolder = TemporaryFolder()

    private val buildFile: File by lazy {
        testProjectDir.newFile("build.gradle")
    }
    private val file1: File by lazy {
        testProjectDir.newFile("filetest1.txt")
    }
    private val file2: File by lazy {
        testProjectDir.newFile("filetest2.txt")
    }

    private val gradleRunner: GradleRunner by lazy {
        GradleRunner.create()
            .withDebug(true)
            .withPluginClasspath()
            .withProjectDir(testProjectDir.root)
            .withTestKitDir(testProjectDir.newFolder())
    }

    @Before
    fun setup() {
        buildFile.appendText("build.gradle.txt".readFileContent())
    }

    @Test
    fun `GIVEN an config block with title==null VERIFY the CHANGELOG content`() {

        buildFile.appendText(
            "\n" + """
            addCommitPushConfig{
                fileList = [
                        "${file1.path}"
                ]
            }
            """.trimIndent()
        )

        val res = gradleRunner
            .withArguments(TASK_NAME)
            .build()

        Assert.assertEquals(TaskOutcome.SUCCESS, res.task(":$TASK_NAME")!!.outcome)
    }

}
