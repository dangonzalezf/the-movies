package com.example.themoviedbapp.domain.region.data

import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class RegionRepositoryTest {

    @Test
    fun `FindLastRegion should call region data source` (): Unit = runBlocking {
        // Given
        val regionExpected = "ES"
        val mockRegionDataSource: RegionDataSource = mock()
        whenever(mockRegionDataSource.findLastRegion()).thenReturn(regionExpected)
        val regionRepository = RegionRepository(mockRegionDataSource)

        // When
        val result = regionRepository.findLastRegion()

        // Then
        assertEquals(regionExpected, result)
        verify(mockRegionDataSource, times(1)).findLastRegion()
    }
}
