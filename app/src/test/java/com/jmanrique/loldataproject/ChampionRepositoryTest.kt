package com.jmanrique.loldataproject

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.jmanrique.loldataproject.app.repository.DefaultRepository
import com.jmanrique.loldataproject.data.repository.DragonLocalStoreImpl
import com.jmanrique.loldataproject.data.repository.DragonRemoteStoreImpl
import com.jmanrique.loldataproject.domain.entities.ChampionSummary
import com.jmanrique.loldataproject.domain.repository.DragonRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.rxjava3.core.Single
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.io.File
import java.io.FileReader
import java.io.InputStream
import java.io.InputStreamReader

@RunWith(MockitoJUnitRunner::class)
class ChampionRepositoryTest {

    @Mock
    private lateinit var mockContext: Context

    lateinit var mockRepository: DragonRepository

    lateinit var dragonDAO: DragonLocalStoreImpl
    lateinit var dragonAPI: DragonRemoteStoreImpl

    lateinit var championsForApi: List<ChampionSummary>

    @Before
    fun setUp() {
        dragonAPI = mock()
        championsForApi = listOf(
            ChampionSummary(
                id = 1,
                name = "Annie",
                alias = "Annie",
                squarePortraitPath = "/lol-game-data/assets/v1/champion-icons/1.png",
                roles = listOf("mage")
            ),
            ChampionSummary(
                id = 2,
                name = "Olaf",
                alias = "Olaf",
                squarePortraitPath = "/lol-game-data/assets/v1/champion-icons/2.png",
                roles = listOf("fighter, tank")
            )
        )

        dragonDAO = mock()
        mockRepository = DefaultRepository(dragonDAO, dragonAPI)
        whenever(mockRepository.getChampionSummary()).thenReturn(Single.just(championsForApi))
    }

    @Test
    fun returnChampionList() {
        val list = mockRepository.getChampionSummary()
        val fileList = getMockChampionsSummary("champion_summary.json")
        val test = list.test()
        test.assertValues(fileList).dispose()
    }


    private fun getMockChampionsSummary(filename: String): List<ChampionSummary> {
        val inputStream: InputStream = mockContext.javaClass.classLoader.getResourceAsStream(filename);
        val championType = object : TypeToken<List<ChampionSummary?>?>() {}.type
        val reader = JsonReader(InputStreamReader(inputStream, "UTF-8"))
        return Gson().fromJson(reader, championType)
    }


}