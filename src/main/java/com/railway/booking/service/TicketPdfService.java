package com.railway.booking.service;

import com.railway.booking.dto.TicketResponse;
import lombok.RequiredArgsConstructor;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
@RequiredArgsConstructor
public class TicketPdfService {

    private final BookingService bookingService;

    public byte[] generateTicketPdf(String ticketNumber) {

        TicketResponse ticket =
                bookingService.viewTicket(ticketNumber);

        try {

            ByteArrayOutputStream outputStream =
                    new ByteArrayOutputStream();

            Document document = new Document();

            PdfWriter.getInstance(
                    document,
                    outputStream
            );

            document.open();

            document.add(
                    new Paragraph("RAILWAY TICKET")
            );

            document.add(
                    new Paragraph("------------------------------")
            );

            document.add(
                    new Paragraph(
                            "Ticket Number: "
                                    + ticket.getTicketNumber()
                    )
            );

            document.add(
                    new Paragraph(
                            "Passenger ID: "
                                    + ticket.getPassengerId()
                    )
            );

            document.add(
                    new Paragraph(
                            "Passenger Name: "
                                    + ticket.getPassengerName()
                    )
            );

            document.add(
                    new Paragraph(
                            "Train Number: "
                                    + ticket.getTrainNumber()
                    )
            );

            document.add(
                    new Paragraph(
                            "Train Name: "
                                    + ticket.getTrainName()
                    )
            );

            document.add(
                    new Paragraph(
                            "From: "
                                    + ticket.getSource()
                    )
            );

            document.add(
                    new Paragraph(
                            "To: "
                                    + ticket.getDestination()
                    )
            );

            document.add(
                    new Paragraph(
                            "Seat Number: "
                                    + ticket.getSeatNumber()
                    )
            );

            document.add(
                    new Paragraph(
                            "Status: "
                                    + ticket.getStatus()
                    )
            );

            document.add(
                    new Paragraph(
                            "Booking Date: "
                                    + ticket.getBookingDate()
                    )
            );

            document.close();

            return outputStream.toByteArray();

        } catch (Exception e) {

            throw new RuntimeException(
                    "Failed to generate ticket PDF",
                    e
            );
        }
    }
}