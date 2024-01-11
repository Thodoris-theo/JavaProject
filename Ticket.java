/*
 Spyros Kanakopoylos 3212018071 
   Thodoris Theodorellis 3212018061
 */

package project;

import java.util.Date;

public class Ticket {
    private String ticketCode;
    private Movie movie;
    private Date issueDate;
    private String ticketType;
    private String seat;
    private double price;
   int counter=100;
    public Ticket( Movie movie, Date issueDate, String ticketType, String seat, double price) {
       this.movie = movie; 
        this.ticketCode = setTicketCode(movie.getTitle());
        this.issueDate = issueDate;
        this.ticketType = ticketType;
        this.seat = seat;
        this.price = price;
    }

    public String getTicketCode() {
        
        return ticketCode;
    }

 public String setTicketCode(String movie) {
   
   return movie.charAt(0) + Integer.toString(++counter);
}

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}