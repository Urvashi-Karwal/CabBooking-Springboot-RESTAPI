package com.infy.cabbooking;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.infy.cabbooking.repository.BookingRepository;
import com.infy.cabbooking.repository.CabRepository;
import com.infy.cabbooking.service.CabBookingService;
import com.infy.cabbooking.service.CabBookingServiceImpl;

@SpringBootTest
public class CabBookingApplicationTests {

    @Mock
	private CabRepository cabRepository;
	
    @Mock
	private BookingRepository bookingRepository;
	
	@InjectMocks
	private CabBookingService cabBookingService = new CabBookingServiceImpl();
	
	@Test
	public void bookCabInvalidCabNoTest() {
	
	}
	
	@Test
	public void getDetailsByBookingTypeNoDetailsFound() {

	}
	

}
