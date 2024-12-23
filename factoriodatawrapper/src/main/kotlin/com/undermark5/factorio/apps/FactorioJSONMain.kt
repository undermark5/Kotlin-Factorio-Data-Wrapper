package com.undermark5.factorio.apps

import com.demod.factorio.DataTable
import com.demod.factorio.FactorioData
import com.demod.factorio.ModInfo
import com.demod.factorio.Utils
import org.json.JSONObject
import java.awt.Desktop
import java.io.File
import java.io.FileInputStream
import java.io.PrintWriter

private fun recipes(table: DataTable, pw: PrintWriter) {
    val json = JSONObject().apply(Utils::terribleHackToHaveOrderedJSONObject)

    table.recipes.entries.sortedBy { (key) -> key }.forEach { (key, recipe) ->
        val recipeJson = JSONObject().apply(Utils::terribleHackToHaveOrderedJSONObject)
        json.put(recipe.name, recipeJson)

        recipeJson.put("wiki-name", table.getWikiRecipeName(recipe.name))
        recipeJson.put("type", recipe.type)
        recipeJson.put("energy-required", recipe.energyRequired)

        val inputJson = JSONObject().apply(Utils::terribleHackToHaveOrderedJSONObject)
        recipeJson.put("inputs", inputJson)
        recipe.inputs.entries.sortedBy { (key) -> key }.forEach { (key, value) ->
            inputJson.put(key, value)
        }

        val outputsJson = JSONObject().apply(Utils::terribleHackToHaveOrderedJSONObject)
        recipeJson.put("outputs", outputsJson)
        recipe.outputs.entries.sortedBy { (key) -> key }.forEach { (key, value) ->
            outputsJson.put(key, value)
        }

        pw.println(json.toString(2))
    }
}


fun main() {
    val dataTable = FactorioData.getTable()
    val baseInfo = ModInfo(
        Utils.readJsonFromStream(FileInputStream(File(FactorioData.factorio, "/data/base/info.json")))
    )

    val outputFolder = File("output/${baseInfo.version}")
    outputFolder.mkdirs()

    PrintWriter(File(outputFolder, "json-recipes-${baseInfo.version}.txt")).use { pw ->
        recipes(dataTable, pw)
    }

    Desktop.getDesktop().open(outputFolder);
}