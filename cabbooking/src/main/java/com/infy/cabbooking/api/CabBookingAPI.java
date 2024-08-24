package com.infy.cabbooking.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infy.cabbooking.dto.BookingDTO;
import com.infy.cabbooking.exception.CabBookingException;
import com.infy.cabbooking.service.CabBookingService;

@RestController
@RequestMapping(value = "/api")
public class CabBookingAPI
{
	@Autowired
    private CabBookingService cabBookingService;
	
	@Autowired
	private Environment environment;

	@GetMapping("/cab/{bookingType}")
    public ResponseEntity<List<BookingDTO>> getDetailsByBookingType(@PathVariable String bookingType) throws CabBookingException
    {
		List<BookingDTO> bookingDTO = cabBookingService.getDetailsByBookingType(bookingType);
	    return new ResponseEntity<List<BookingDTO>>(bookingDTO, HttpStatus.OK);
    }

	@PostMapping("/cab")
    public ResponseEntity<String> bookCab(@RequestBody BookingDTO bookingDTO) throws CabBookingException
    {
		Integer bookingId = cabBookingService.bookCab(bookingDTO);
		String msg = environment.getProperty("API.BOOKING_SUCCESS")+bookingId;
	    return new ResponseEntity<String>(msg, HttpStatus.CREATED);
    }

}
