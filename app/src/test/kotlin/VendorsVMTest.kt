import com.youarelaunched.challenge.data.repository.VendorsRepository
import com.youarelaunched.challenge.ui.screen.view.VendorsVM
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import misc.CoroutinesTestRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
class VendorsVMTest {

    lateinit var repo: VendorsRepository

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Before
    fun before() { repo = mockk() }

    @Test
    fun test_getVendors_success_with_non_empty_list() = runTest {
        //given
        coEvery { repo.getAllVendors() } returns listOf(mockk())
        val viewmodel = VendorsVM(repo)

        //when
        advanceUntilIdle()

        //then
        assert(viewmodel.uiState.value.vendors?.isNotEmpty() == true)

        coVerify(atLeast = 1) {
            repo.getAllVendors()
        }
    }

    @Test
    fun test_getVendors_throws() = runTest {
        //given
        coEvery { repo.getAllVendors() } throws IOException()
        val viewmodel = VendorsVM(repo)

        //when
        advanceUntilIdle()

        //then
        assert(viewmodel.uiState.value.vendors == null)

        coVerify(exactly = 1) {
            repo.getAllVendors()
        }

    }
}
