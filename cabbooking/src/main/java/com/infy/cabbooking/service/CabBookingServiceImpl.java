package com.infy.cabbooking.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infy.cabbooking.dto.BookingDTO;
import com.infy.cabbooking.dto.CabDTO;
import com.infy.cabbooking.entity.Booking;
import com.infy.cabbooking.entity.Cab;
import com.infy.cabbooking.exception.CabBookingException;
import com.infy.cabbooking.repository.BookingRepository;
import com.infy.cabbooking.repository.CabRepository;
import com.infy.cabbooking.validator.BookingValidator;


@Service(value = "cabBookingService")
@Transactional
public class CabBookingServiceImpl implements CabBookingService {

	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
	private CabRepository cabRepository;


	@Override
	public List<BookingDTO> getDetailsByBookingType(String bookingType) throws CabBookingException {
		List<Booking> bookings = bookingRepository.findByBookingType(bookingType);
		if(bookings.isEmpty()) {
			throw new CabBookingException("Service.NO_DETAILS_FOUND");
		}
		List<BookingDTO> bdto = new ArrayList<BookingDTO>();
		bookings.forEach(booking -> {
			CabDTO c = new CabDTO();
			c.setCabNo(booking.getCab().getCabNo());
			c.setAvailability(booking.getCab().getAvailability());
			c.setDriverPhoneNo(booking.getCab().getDriverPhoneNo());
			c.setModelName(booking.getCab().getModelName());
			
			BookingDTO b = new BookingDTO();
			b.setBookingId(booking.getBookingId());
			b.setBookingType(booking.getBookingType());
			b.setCustomerName(booking.getCustomerName());
			b.setPhoneNo(booking.getPhoneNo());
			b.setCabDTO(c);
			bdto.add(b);
		});
		return bdto;
		}

	
	@Override
	public Integer bookCab(BookingDTO bookingDTO) throws CabBookingException {
		BookingValidator bvalid = new BookingValidator();
		bvalid.validate(bookingDTO);
		Optional<Cab> optional = cabRepository.findById(bookingDTO.getCabDTO().getCabNo());
		Cab cab = optional.orElseThrow(() -> new CabBookingException("Service.CAB_NOT_FOUND"));
		if(cab.getAvailability() == "No") {
			throw new CabBookingException("Service.CAB_NOT_AVAILABLE");
		}
		Booking booking = new Booking();
		booking.setBookingId(bookingDTO.getBookingId());
		booking.setBookingType(bookingDTO.getBookingType());
		booking.setCustomerName(bookingDTO.getCustomerName());
		booking.setPhoneNo(bookingDTO.getPhoneNo());
		booking.setCab(cab);
		
		cab.setAvailability("No");
		Booking book = bookingRepository.save(booking);
		return book.getBookingId();
		}
	
	

}



