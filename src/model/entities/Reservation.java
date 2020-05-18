package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import model.exceptions.DomainExceptions;

public class Reservation {

	private Integer roomNumber;
	private Date checkIn;
	private Date checkOut;

	private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public Reservation(Integer roomNumber, Date checkIn, Date checkOut)  {
		if (!checkOut.after(getCheckIn()))
			throw new DomainExceptions("check-out date must be after check-in date");

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

	public Date getCheckOut() {
		return checkOut;
	}

	public long duration() {
		long diff = this.checkOut.getTime() - this.checkIn.getTime();
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}

	public void updateDates(Date checkIn, Date checkOut)  {

		Date now = new Date();

		if (checkIn.before(now) || checkOut.before(now)) {
			// throw new IllegalArgumentException("Reservation dates for update must be
			// future dates");
			throw new DomainExceptions("Reservation dates for update must be future dates");
		}

		if (!checkOut.after(getCheckIn())) {
			throw new DomainExceptions("check-out date must be after check-in date");
		}

		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}

	@Override
	public String toString() {
		return "Reservation: Room " + roomNumber + ", check-in: " + sdf.format(this.getCheckIn()) + ", check-out:"
				+ sdf.format(this.getCheckOut()) + ", " + this.duration() + " nights";
	}
}
