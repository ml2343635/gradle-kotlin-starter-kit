package com.monstarlab.common.cache

import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class CacheWriter(rootPath: String) {
    private val cachePath: String

    init {
        // TODO: Repalce to your cache path name
        val rootCache = File("$rootPath/starter-cache")
        rootCache.mkdir()
        cachePath = rootCache.path
    }

    fun writeTextToFile(text: String, path: String, name: String): File? {
        val cacheFile = getWritableFile(path, name) ?: return null
        cacheFile.writeText(text = text, charset = Charsets.UTF_8)
        return cacheFile
    }

    fun writeFileOutputStream(path: String, name: String, write: (FileOutputStream) -> Any): File? {
        val cacheFile = getWritableFile(path, name) ?: return null
        val fileOutputStream = FileOutputStream(cacheFile)
        write(fileOutputStream)
        fileOutputStream.close()
        return cacheFile
    }

    fun getWritableFile(path: String, name: String): File? {
        val directory = getWritableDirectory(path) ?: return null
        return File("${directory.absolutePath}/$name")
    }

    fun getWritableDirectory(path: String): File? {
        val directory = File("$cachePath/$path")
        if (!directory.exists() && !directory.mkdirs()) {
            return null
        }
        return directory
    }

    fun writeMultipartFile(multipartFile: MultipartFile, path: String): File? {
        val cacheFile = multipartFile.originalFilename?.let {
            getWritableFile(
                path = path,
                name = it,
            )
        } ?: return null
        try {
            cacheFile.createNewFile()
            val outputStream = FileOutputStream(cacheFile)
            outputStream.write(multipartFile.bytes)
            outputStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
        return cacheFile
    }
}