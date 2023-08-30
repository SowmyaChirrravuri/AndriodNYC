import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.satnyc.repo.DataRepository
import com.example.satnyc.data.SatScores
import com.example.satnyc.data.School
import com.example.satnyc.viewmodel.SchoolViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class SchoolViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var dataRepository: DataRepository

    private lateinit var viewModel: SchoolViewModel
    private lateinit var testDispatcher: TestCoroutineDispatcher
    private lateinit var testScope: TestCoroutineScope

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        testDispatcher = TestCoroutineDispatcher()
        testScope = TestCoroutineScope(testDispatcher)
        viewModel = SchoolViewModel(dataRepository)
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        testScope.cleanupTestCoroutines()
    }

    @Test
    fun `test getSchools() success`() = testScope.runBlockingTest {
        // Given
        val mockSchoolList = listOf(School("1", "GreatSchool", "Great School of excellence"
                                                    ,"123","400"))

        Mockito.`when`(dataRepository.getSchoolData()).thenReturn(mockSchoolList)

        // When
        viewModel.getSchools()

        // Then
        assert(viewModel.schoolState.value is SchoolViewModel.ViewState.Success)
        assert((viewModel.schoolState.value as SchoolViewModel.ViewState.Success).data == mockSchoolList)
    }

    @Test
    fun `test getSatScores() success`() = testScope.runBlockingTest {
        // Given
        val dbn = "123456"
        val mockSatScoresList = listOf(SatScores("2","What a fantastic school",
                                                   "123","456","789","100" ))
        Mockito.`when`(dataRepository.getSatScore(dbn)).thenReturn(mockSatScoresList)

        // When
        viewModel.getSatScores(dbn)

        // Then
        assert(viewModel.satScoreState.value is SchoolViewModel.ViewState.Success)
        assert((viewModel.satScoreState.value as SchoolViewModel.ViewState.Success).data == mockSatScoresList)
    }
}
