package com.roquebuarque.cpynpasta

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.roquebuarque.cpynpasta.model.RecipeDto
import com.roquebuarque.cpynpasta.model.RecipeService
import com.roquebuarque.cpynpasta.model.RecipesResponse
import com.roquebuarque.cpynpasta.presenter.RecipeListContract
import com.roquebuarque.cpynpasta.presenter.RecipeListPresenterImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class RecipeListPresenterImplTest {

    @get:Rule
    var mainCoroutineRule = CoroutineTestRule()

    //set mocks
    private val service: RecipeService = mock()
    private val view: RecipeListContract.View = mock()

    //inicializa a classe que quero testar
    private val testee = RecipeListPresenterImpl(service)

    @Before
    fun setup(){
        testee.attachView(view)
    }

    @After
    fun done(){
        testee.detachView()
    }

    @Test
    fun `test get random recipes success`() {
        runBlockingTest {
            //Given
            val list = getRecipeList()
            whenever(service.getRecipes()).thenReturn(RecipesResponse(list))

            //When
            testee.fetchRandomRecipes()

            //Then
            verify(view).displayLoading(true)
            verify(view).displayRecipes(list)
            verify(view).displayLoading(false)

        }
    }

    @Test
    fun `test get random recipes error`() {
        runBlockingTest {
            //Given
            val error = RuntimeException()
            whenever(service.getRecipes()).thenThrow(error)

            //When
            testee.fetchRandomRecipes()

            //Then
            verify(view).displayLoading(true)
            verify(view).showError(R.string.error_message)
            verify(view).displayLoading(false)

        }
    }

    private fun getRecipeList(): List<RecipeDto> {
        return listOf(
            RecipeDto(
                "title",
                "image",
                "source",
                10
            )
        )
    }

}