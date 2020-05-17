package aplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import model.entities.Reservation;

public class Program {

	public static void main(String[] args) throws ParseException {

		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		System.out.print("Room number: ");
		int roomNumber = sc.nextInt();
		System.out.print("Check-in date (dd/MM/yyyy):");
		Date checkIn = sdf.parse(sc.next());
		System.out.print("Check-out date (dd/MM/yyyy):");
		Date checkOut = sdf.parse(sc.next());

		// Verifica se a data de saida anterior a data de entrada.
		if (checkOut.before(checkIn) || checkIn.before(new Date())  ||  checkOut.getTime() == checkIn.getTime()) {
			System.out.println("Error in reservation: Errors found on booking dates");
		} else {
			Reservation reservation = new Reservation(roomNumber, checkIn, checkOut);
			System.out.println(reservation);

			System.out.println("Enter data to update the reservation:");
			System.out.print("Check-in date (dd/MM/yyyy):");
			checkIn = sdf.parse(sc.next());
			System.out.print("Check-out date (dd/MM/yyyy):");
			checkOut = sdf.parse(sc.next());
			if(checkIn.after(reservation.getCheckIn()) && checkOut.before(checkIn)) {
				reservation.updateDates(checkIn, checkOut);
				System.out.println(reservation);
			}else {
				System.out.println("Error in reservation: Reservation dates for update must be future dates");
			}
		}

		sc.close();
	}
}
