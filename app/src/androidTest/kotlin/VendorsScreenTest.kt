import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.youarelaunched.challenge.data.repository.VendorsRepository
import com.youarelaunched.challenge.middle.R
import com.youarelaunched.challenge.ui.screen.view.VendorsRoute
import com.youarelaunched.challenge.ui.screen.view.VendorsVM
import com.youarelaunched.challenge.ui.theme.VendorAppTheme
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import test_doubles.MockVendor

class VendorsScreenTest {
    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    lateinit var repo: VendorsRepository

    @Before
    fun before() { repo = mockk() }

    private fun setTestContext(configuredRepo: VendorsRepository) {
        composeRule.setContent {
            VendorAppTheme {
                val viewModel = viewModel<VendorsVM>(
                    factory = viewModelFactory { initializer { VendorsVM(configuredRepo) } }
                )
                VendorsRoute(viewModel = viewModel)
            }

        }
    }

    @Test
    fun if_vendors_is_empty_then_NoItemsPresent_will_be_shown() {
        //given
        coEvery { repo.getAllVendors() } returns emptyList()
        setTestContext(repo)

        val hint = composeRule.activity.getString(R.string.searchbar_hint)
        val suggestion = composeRule.activity.getString(R.string.no_result_found_suggestion)

        //when
        composeRule.onNodeWithText(hint)
            //then
            .assertExists()

        //when
        composeRule.onNodeWithText(suggestion)
            //then
            .assertExists()

    }

    @Test
    fun if_vendors_is_not_empty_then_at_least_one_vendor_item_will_be_shown() {
        //given
        coEvery { repo.getAllVendors() } returns listOf(MockVendor)
        setTestContext(repo)

        //when
        composeRule.onNodeWithText(MockVendor.companyName)
            //then
            .assertExists()

    }

}