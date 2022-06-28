package com.github.lzj960515.codegenerator.services

import com.intellij.openapi.project.Project
import com.github.lzj960515.codegenerator.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
