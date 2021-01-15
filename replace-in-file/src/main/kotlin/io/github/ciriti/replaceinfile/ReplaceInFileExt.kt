package io.github.ciriti.replaceinfile

open class ReplaceInFileExt(
    var changeLogPath: String = "CHANGELOG.md",
    var title: String? = null,
    var content: String? = null,
    var version: String = "X.Y.Z"
)
