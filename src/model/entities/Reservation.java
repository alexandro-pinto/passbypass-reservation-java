package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Reservation {

	private Integer roomNumber;
	private Date checkIn;
	private Date checkOut;
	
	private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public Reservation(Integer roomNumber, Date checkIn, Date checkOut) {
		this.roomNumber = roomNumber;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}

	public Integer getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Date getCheckIn() {
		return checkIn;
	}

	private void setCheckIn(Date checkIn) {
		this.checkIn = checkIn;
	}

	public Date getCheckOut() {
		return checkOut;
	}

	private void setCheckOut(Date checkOut) {
		this.checkOut = checkOut;
	}

	public final long duration() {
		long diff = this.checkOut.getTime() - this.checkIn.getTime();
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}

	public final void updateDates(Date checkIn, Date checkOut) {
		this.setCheckIn(checkIn);
		this.setCheckOut(checkOut);
	}

	@Override
	public String toString() {
		return "Reservation: Room " + roomNumber + ", check-in: " +   sdf.format(this.getCheckIn()) + ", check-out:" + sdf.format(this.getCheckOut()) + ", " + this.duration() + " nights";
	}	
}
